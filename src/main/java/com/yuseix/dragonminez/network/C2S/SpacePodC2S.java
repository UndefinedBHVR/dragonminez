package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.NaveSaiyanEntity;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.rmi.registry.Registry;
import java.util.function.Supplier;

public class SpacePodC2S {
    private final ResourceKey<Level> targetDimension;

    public SpacePodC2S(ResourceKey<Level> targetDimension) {
        this.targetDimension = targetDimension;
    }

    public static void encode(SpacePodC2S msg, FriendlyByteBuf buf) {
        buf.writeResourceLocation(msg.targetDimension.location());
    }

    public static SpacePodC2S decode(FriendlyByteBuf buf) {
        ResourceLocation location = buf.readResourceLocation();
        ResourceKey<Level> targetDimension = switch (location.toString()) {
            case "minecraft:overworld" -> Level.OVERWORLD;
            case "dragonminez:namek" -> ModDimensions.NAMEK_DIM_LEVEL_KEY;
            default -> Level.OVERWORLD;
        };
        return new SpacePodC2S(targetDimension);
    }

    public static void handle(SpacePodC2S msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getSender() != null) {
                var player = ctx.get().getSender();
                ServerLevel targetWorld = player.server.getLevel(msg.targetDimension);

                if (targetWorld != null && player.level() != targetWorld) {
                    player.teleportTo(targetWorld, player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());

                    NaveSaiyanEntity naveEntity = new NaveSaiyanEntity(MainEntity.NAVE_SAIYAN.get(), targetWorld);
                    naveEntity.setPos(player.getX(), player.getY(), player.getZ());

                    targetWorld.addFreshEntity(naveEntity);

                    player.startRiding(naveEntity, true);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
