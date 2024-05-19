package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @deprecated Esta clase es obsoleta, se recomienda usar {@link com.yuseix.dragonminez.network.packets.PacketStats}
 * Esta se eliminará pronto.
 */
@Deprecated
public class StatsC2S {

    private int id;
    private int cantidad;


    public StatsC2S(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public StatsC2S(FriendlyByteBuf buf) {
        id = buf.readInt();
        cantidad = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(cantidad);

    }

    public static void handle(StatsC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(playerstats -> {

                    switch (packet.id) {
                        case 0:
                            playerstats.addStrength(packet.cantidad);
                            break;
                        case 1:
                            playerstats.addDefense(packet.cantidad);
                            break;
                        case 2:
                            playerstats.addCon(packet.cantidad);
                            playerstats.addStam(packet.cantidad);
                            player.getAttribute(Attributes.MAX_HEALTH).setBaseValue((playerstats.getConstitution() * 0.5) * DMCAttrConfig.MULTIPLIER_CON.get());
                            player.refreshDimensions();
                            break;
                        case 3:
                            playerstats.addKipwr(packet.cantidad);
                            break;
                        case 4:
                            playerstats.addEnergy(packet.cantidad);
                            break;
                        default:
                            System.out.println("Algo salio mal !");
                            break;
                    }

                });
            }

        });
        context.setPacketHandled(true);
    }
}

