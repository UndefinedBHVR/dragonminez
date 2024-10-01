package com.yuseix.dragonminez.init.blocks.custom;

import com.yuseix.dragonminez.init.MainBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class NamekGrassBlock extends Block implements BonemealableBlock {

    public NamekGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!world.isClientSide) {
            if (!world.isAreaLoaded(pos, 2)) return;

            BlockState blockAbove = world.getBlockState(pos.above());

            // Transformar a NAMEK_DIRT si hay un bloque opaco encima o la luz es insuficiente
            if (blockAbove.getLightBlock(world, pos.above()) > 2 && world.getMaxLocalRawBrightness(pos.above()) < 4) {
                world.setBlockAndUpdate(pos, MainBlocks.NAMEK_DIRT.get().defaultBlockState());
            } else {
                // Expansión del NAMEK_GRASS_BLOCK a NAMEK_DIRT adyacente si la luz es suficiente
                if (world.getMaxLocalRawBrightness(pos.above()) >= 9) {
                    for (int i = 0; i < 4; ++i) {
                        BlockPos targetPos = pos.offset(
                                random.nextInt(3) - 1,
                                random.nextInt(3) - 1,
                                random.nextInt(3) - 1
                        );
                        BlockState targetState = world.getBlockState(targetPos);
                        BlockState aboveTargetState = world.getBlockState(targetPos.above());

                        // Expande a NAMEK_GRASS_BLOCK si el bloque objetivo es NAMEK_DIRT y el bloque superior es aire
                        if (targetState.is(MainBlocks.NAMEK_DIRT.get()) &&
                                aboveTargetState.isAir() &&
                                world.getMaxLocalRawBrightness(targetPos.above()) >= 4) {
                            world.setBlockAndUpdate(targetPos, MainBlocks.NAMEK_GRASS_BLOCK.get().defaultBlockState());
                        }
                    }
                }
            }
        }
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos pos, BlockState state, boolean isClient) {
        return levelReader.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        BlockState namekGrassState = MainBlocks.NAMEK_GRASS.get().defaultBlockState();

        for (int i = 0; i < 128; ++i) {
            BlockPos currentPos = abovePos;

            for (int j = 0; j < i / 16; ++j) {
                currentPos = currentPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(currentPos.below()).is(this) || level.getBlockState(currentPos).isCollisionShapeFullBlock(level, currentPos)) {
                    continue;
                }
            }

            BlockState targetState = level.getBlockState(currentPos);
            BlockState belowState = level.getBlockState(currentPos.below());

            // Verifica que el bloque debajo sea NAMEK_GRASS_BLOCK antes de generar NAMEK_GRASS
            if (targetState.isAir() && belowState.is(MainBlocks.NAMEK_GRASS_BLOCK.get())) {
                level.setBlockAndUpdate(currentPos, namekGrassState);
            }
        }
    }

}
