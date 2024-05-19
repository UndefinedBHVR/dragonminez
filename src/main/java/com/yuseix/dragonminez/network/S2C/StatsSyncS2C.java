package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
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
    private int Id;

    public StatsSyncS2C(Player player) {
        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(cap -> nbt = cap.saveNBTData());
        player.getId();
    }

    public StatsSyncS2C(FriendlyByteBuf buf) {

        nbt = buf.readNbt();
        Id = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {

        buf.writeNbt(nbt);
        buf.writeInt(Id);
    }

    public void handle(Supplier<NetworkEvent.Context> paramSupplier) {
        paramSupplier.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClient(paramSupplier, Id, nbt));
        });
        paramSupplier.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public static void handleClient(Supplier<NetworkEvent.Context> paramSupplier, int id, CompoundTag nbt) {
        var entity = Minecraft.getInstance().level.getEntity(id);
        if(entity instanceof Player player) {
            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(cap -> cap.loadNBTData(nbt));
            player.refreshDimensions();
        }
    }

}
