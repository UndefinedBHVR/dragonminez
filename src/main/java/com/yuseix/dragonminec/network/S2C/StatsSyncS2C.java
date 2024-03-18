package com.yuseix.dragonminec.network.S2C;

import com.yuseix.dragonminec.events.ModEvents;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StatsSyncS2C {

    private CompoundTag nbt;
    public StatsSyncS2C(Player player) {
        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(cap -> nbt = cap.saveNBTData());


    }

    public StatsSyncS2C(FriendlyByteBuf buf) {

        nbt = buf.readNbt();

    }

    public void toBytes(FriendlyByteBuf buf){

        buf.writeNbt(nbt);

    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClient(ctx,nbt));

        });

        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public static void handleClient(Supplier<NetworkEvent.Context> ctx,CompoundTag nbt){

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,Minecraft.getInstance().player) .ifPresent(cap -> cap.loadNBTData(nbt));

    }



}
