package com.yuseix.dragonminez.init.blocks.custom.dballs;

import com.google.common.collect.ImmutableMap;
import com.yuseix.dragonminez.init.blocks.entity.Dball1NamekBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class Dball1NamekBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public Dball1NamekBlock(Properties pProperties) {
        super(pProperties);
    }

    private static final VoxelShape SHAPE =
            Dball1NamekBlock.box(0, 0, 0, 8, 7, 8);

    /*TODO: Change Block Textures for the BlockEntity
     * 1. Cambiar el tamaño de la Dragon Ball?
     * 2. Mantener textura igual de la Dragon Ball de la Tierra?
     * 3. Cambiar ResourceLocations de la textura de la Dragon Ball? (Dball1NamekBlockModel.java)
     */

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {

        switch (((Direction) pState.getValue(FACING)).getAxis()) {
            case X:
                // Ajusta las coordenadas X para centrarlo en el eje X
                return Dball1NamekBlock.box((16 - 7.5) / 2.0, 0.0, (16 - 7.5) / 2.0, (16 + 7.5) / 2.0, 7.0, (16 + 7.5) / 2.0);
            case Z:
            default:
                // Ajusta las coordenadas Z para centrarlo en el eje Z
                return Dball1NamekBlock.box((16 - 8) / 2.0, 0.0, (16 - 8) / 2.0, (16 + 8) / 2.0, 7.0, (16 + 8) / 2.0);
        }

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new Dball1NamekBlockEntity(blockPos, blockState);
    }

    @Override
    protected ImmutableMap<BlockState, VoxelShape> getShapeForEachState(Function<BlockState, VoxelShape> pShapeGetter) {
        return super.getShapeForEachState(pShapeGetter);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
    /* FACING */

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }


}
