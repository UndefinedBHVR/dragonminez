package com.yuseix.dragonminez.network.packets;

import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.network.Packet;
import com.yuseix.dragonminez.stats.StatsAttrProviderV2;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class PacketStatsSync implements Packet {

    private int keyNumber;
    private int cantidad;

    public PacketStatsSync() {
    }

    public PacketStatsSync(int keyNumber, int cantidad) {
        this.keyNumber = keyNumber;
        this.cantidad = cantidad;
    }

    @Override
    public void read(FriendlyByteBuf buf) {
        keyNumber = buf.readInt();
        cantidad = buf.readInt();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(keyNumber);
        buf.writeInt(cantidad);
    }

    //En este método, player SIEMPRE es null, ya que en Packets Handler, automáticamente ya se tiene el jugador
    //O sea que se procesa el jugador en el método handle de Packets Handler, y no acá.
    @Override
    public void handle(ServerPlayer player) {
        StatsAttrProviderV2.getCap(ModEvents.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> cap.loadNBTData(nbt));
        Minecraft.getInstance().player.refreshDimensions();
    }
}