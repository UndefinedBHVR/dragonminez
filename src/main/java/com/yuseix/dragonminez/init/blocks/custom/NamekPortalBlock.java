package com.yuseix.dragonminez.init.blocks.custom;

import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class NamekPortalBlock extends Block{

    public NamekPortalBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.END_PORTAL).noLootTable().noOcclusion());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.canChangeDimensions()) {

            if (player.level() instanceof ServerLevel) {
                boolean onNamek = player.level().dimension() == ModDimensions.NAMEK;
                ServerLevel targetWorld = ((ServerLevel) player.level()).getServer().getLevel(onNamek ? Level.OVERWORLD : ModDimensions.NAMEK);

                if (targetWorld != null && !player.isPassenger())
                    player.changeDimension(targetWorld, new ITeleporter() {

                        @Override
                        public Entity placeEntity(Entity entity, ServerLevel current, ServerLevel destination, float yaw, Function<Boolean, Entity> repositionEntity) {
                            entity = repositionEntity.apply(false);

                            BlockPos finalPos = new BlockPos(blockpos.getX(), 50, blockpos.getZ());

                            int i = 0;
                            while (destination.getBlockState(finalPos).getBlock() != Blocks.AIR &&
                                    destination.getBlockState(finalPos.above()).getBlock() != Blocks.AIR &&
                                    !destination.getBlockState(finalPos).canBeReplaced(Fluids.WATER) &&
                                    !destination.getBlockState(finalPos.above()).canBeReplaced(Fluids.WATER) && i < 30) {

                                finalPos = finalPos.above(2);
                                i++;
                            }

                            entity.setPos(finalPos.getX(), finalPos.getY(), finalPos.getZ());

                            return entity;
                        }
                    });
            }

            return InteractionResult.SUCCESS;

        } else
            return InteractionResult.CONSUME;

    }
}
