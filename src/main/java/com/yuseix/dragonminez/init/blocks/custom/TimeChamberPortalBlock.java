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
        super(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).noParticlesOnBreak().strength(-1.0F, 3600000.0F).noLootTable().noOcclusion());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockpos, Player pPlayer, InteractionHand hand, BlockHitResult hit) {
        if (pPlayer.canChangeDimensions()) {

            if (pPlayer.level() instanceof ServerLevel) {
                boolean onTC = pPlayer.level().dimension() == ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY;
                ServerLevel targetWorld = ((ServerLevel) pPlayer.level()).getServer().getLevel(onTC ? Level.OVERWORLD : ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY);

                if (targetWorld != null && !pPlayer.isPassenger()) {
                    // Cambiar de dimensión primero
                    pPlayer.changeDimension(targetWorld, new ITeleporter() {
                        @Override
                        public Entity placeEntity(Entity entity, ServerLevel current, ServerLevel destination, float yaw, Function<Boolean, Entity> repositionEntity) {
                            // Mantener el reposicionamiento estándar
                            return repositionEntity.apply(false);
                        }
                    });

                    // Asignar la nueva posición después de cambiar de dimensión
                    BlockPos finalPos;
                    if (onTC) {
                        finalPos = new BlockPos(159, 226, 143); // Saliste

                    } else {
                        finalPos = new BlockPos(61, 35, -63); // Entraste

                    }

                    // Forzar el reposicionamiento después de que el jugador cambie de dimensión
                    pPlayer.teleportTo(finalPos.getX(), finalPos.getY(), finalPos.getZ());
                }
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }
}
