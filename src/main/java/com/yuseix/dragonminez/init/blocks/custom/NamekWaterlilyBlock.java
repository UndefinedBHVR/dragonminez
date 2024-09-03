package com.yuseix.dragonminez.init.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.PlantType;

public class NamekWaterlilyBlock extends WaterlilyBlock {
    public NamekWaterlilyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return PlantType.get("custom");
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        FluidState fluidstate = pLevel.getFluidState(pPos);
        FluidState fluidstate1 = pLevel.getFluidState(pPos.above());
        return (fluidstate.getType() == Fluids.WATER || pState.getBlock() instanceof IceBlock) && fluidstate1.getType() == Fluids.EMPTY;
    }
}
