package com.yuseix.dragonminec.network.C2S;

import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.network.S2C.StatsS2C;
import com.yuseix.dragonminec.network.S2C.ZPointsS2C;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ZPointsC2S {

    private int id;
    private int cantidad;


    public ZPointsC2S(int operacion, int cantidad) {
        this.id = operacion;
        this.cantidad = cantidad;
    }

    public ZPointsC2S(FriendlyByteBuf buf) {
        id = buf.readInt();
        cantidad = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(id);
        buf.writeInt(cantidad);

    }

    public static void handle(ZPointsC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                player.getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerstats -> {

                    switch (packet.id){
                        case 0:
                            playerstats.addZpoints(packet.cantidad);
                            break;
                        case 1:
                            playerstats.removeZpoints(packet.cantidad);
                            break;
                        case 2:
                            playerstats.setZpoints(packet.cantidad);
                            break;
                        default:
                            System.out.println("Algo salio mal !");
                            break;
                    }

                    ModMessages.sendToPlayer(new ZPointsS2C(playerstats.getZpoints()), player);

                });
            }

        });
        context.setPacketHandled(true);
    }
}
