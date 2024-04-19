package com.yuseix.dragonminez.init.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class GeteOreBlock extends Block {

    public static final BooleanProperty LIT;

    public GeteOreBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    public void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        interact(pState, pLevel, pPos);
        super.attack(pState, pLevel, pPos, pPlayer);
    }

    private static void interact(BlockState pState, Level pLevel, BlockPos pPos) {
        spawnParticles(pLevel, pPos);
        if (!(Boolean) pState.getValue(LIT)) {
            pLevel.setBlock(pPos, pState.setValue(LIT, true), 3);
        }

    }

    private static void spawnParticles(Level pLevel, BlockPos pPos) {
        RandomSource randomsource = pLevel.random;
        Direction[] var5 = Direction.values();

        for (Direction direction : var5) {
            BlockPos blockpos = pPos.relative(direction);
            if (!pLevel.getBlockState(blockpos).isSolidRender(pLevel, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5 + 0.5625 * (double) direction.getStepX() : (double) randomsource.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double) direction.getStepY() : (double) randomsource.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double) direction.getStepZ() : (double) randomsource.nextFloat();
                pLevel.addParticle(DustParticleOptions.REDSTONE, (double) pPos.getX() + d1, (double) pPos.getY() + d2, (double) pPos.getZ() + d3, 0.0, 0.0, 0.0);
            }
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
    }

    static {
        LIT = RedstoneTorchBlock.LIT;
    }
}