package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SkillActivateC2S {

    private String tipo;
    private boolean value;


    public SkillActivateC2S(String tipo, boolean value) {
        this.tipo = tipo;
        this.value = value;
    }

    public SkillActivateC2S(FriendlyByteBuf buf) {
        tipo = buf.readUtf();
        value = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(tipo);
        buf.writeBoolean(value);

    }

    public static void handle(SkillActivateC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {

                playerstats.setSkillActive(packet.tipo, packet.value);

                });
            }

        });
        context.setPacketHandled(true);
    }
}
