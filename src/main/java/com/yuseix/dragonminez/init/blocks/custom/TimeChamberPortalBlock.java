package com.yuseix.dragonminez.init.blocks.custom;

import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class TimeChamberPortalBlock extends Block {
    public TimeChamberPortalBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.END_PORTAL).noLootTable().noOcclusion());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockpos, Player pPlayer, InteractionHand hand, BlockHitResult hit) {
        if (pPlayer.canChangeDimensions()) {

            if (pPlayer.level() instanceof ServerLevel) {
                boolean onTC = pPlayer.level().dimension() == ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY;
                ServerLevel targetWorld = ((ServerLevel) pPlayer.level()).getServer().getLevel(onTC ? Level.OVERWORLD : ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY);

                if (targetWorld != null && !pPlayer.isPassenger()) {
                    pPlayer.changeDimension(targetWorld, new ITeleporter() {

                        @Override
                        public Entity placeEntity(Entity entity, ServerLevel current, ServerLevel destination, float yaw, Function<Boolean, Entity> repositionEntity) {
                            entity = repositionEntity.apply(false);

                            BlockPos pos = new BlockPos((int)entity.getX(), (int)entity.getY(), (int)entity.getZ());
                            BlockPos finalPos;

                            if (destination.dimension() == ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY) {
                                finalPos = new BlockPos(pos.getX(), 0, pos.getZ());
                                int y = destination.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, finalPos.getX(), finalPos.getZ());
                                finalPos = new BlockPos(finalPos.getX(), Math.min(y, 2), finalPos.getZ());
                            } else {
                                finalPos = new BlockPos(pos.getX(), 50, pos.getZ());
                                int i = 0;
                                while (destination.getBlockState(finalPos).getBlock() != Blocks.AIR &&
                                        destination.getBlockState(finalPos.above()).getBlock() != Blocks.AIR &&
                                        !destination.getBlockState(finalPos).canBeReplaced(Fluids.WATER) &&
                                        !destination.getBlockState(finalPos.above()).canBeReplaced(Fluids.WATER) && i < 30) {
                                    finalPos = finalPos.above(2);
                                    i++;
                                }
                            }

                            entity.setPos(finalPos.getX(), finalPos.getY(), finalPos.getZ());
                            return entity;
                        }
                    });
                }
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }
}
