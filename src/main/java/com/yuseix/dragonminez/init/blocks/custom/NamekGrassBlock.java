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

public class NamekGrassBlock extends GrassBlock implements BonemealableBlock {

    public NamekGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!world.isClientSide) {
            if (!world.isAreaLoaded(pos, 2)) return;

            if (world.getLightEmission(pos.above()) < 4 && world.getBlockState(pos.above()).getLightBlock(world, pos.above()) > 2) {
                world.setBlockAndUpdate(pos, MainBlocks.NAMEK_DIRT.get().defaultBlockState());
            } else {
                if (world.getLightEmission(pos.above()) >= 9) {
                    for (int i = 0; i < 4; ++i) {
                        BlockPos targetPos = pos.offset(
                                random.nextInt(3) - 1,
                                random.nextInt(5) - 3,
                                random.nextInt(3) - 1
                        );
                        BlockState targetState = world.getBlockState(targetPos);
                        BlockState aboveState = world.getBlockState(targetPos.above());

                        if (targetState.is(MainBlocks.NAMEK_DIRT.get()) &&
                                world.getLightEmission(targetPos.above()) >= 4 &&
                                aboveState.isAir()) {
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
        BlockState grassState = MainBlocks.NAMEK_GRASS_BLOCK.get().defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> featureHolder = level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL);

        for (int i = 0; i < 128; ++i) {
            BlockPos currentPos = abovePos;

            for (int j = 0; j < i / 16; ++j) {
                currentPos = currentPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(currentPos.below()).is(this) || level.getBlockState(currentPos).isCollisionShapeFullBlock(level, currentPos)) {
                    continue;
                }
            }

            BlockState targetState = level.getBlockState(currentPos);
            if (targetState.is(grassState.getBlock()) && random.nextInt(10) == 0) {
                ((BonemealableBlock) grassState.getBlock()).performBonemeal(level, random, currentPos, targetState);
            }

            if (targetState.isAir()) {
                Holder<?> holder;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> flowerFeatures = ((Biome) level.getBiome(currentPos).value()).getGenerationSettings().getFlowerFeatures();
                    if (flowerFeatures.isEmpty()) {
                        continue;
                    }

                    holder = ((RandomPatchConfiguration) ((ConfiguredFeature<?, ?>) flowerFeatures.get(0)).config()).feature();
                } else {
                    if (!featureHolder.isPresent()) {
                        continue;
                    }

                    holder = featureHolder.get();
                }

                ((PlacedFeature) holder.value()).place(level, level.getChunkSource().getGenerator(), random, currentPos);
            }
        }
    }
}
