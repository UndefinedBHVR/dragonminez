package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

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
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {

                    var vidaMC = 20;
                    var raza = playerstats.getRace();
                    var VidaTotal = 0.0f;
                    var con = playerstats.getConstitution();

                    switch (packet.id) {
                        case 0:
                            playerstats.addStrength(packet.cantidad);
                            break;
                        case 1:
                            playerstats.addDefense(packet.cantidad);
                            break;
                        case 2:
                            playerstats.addCon(packet.cantidad);

                            if(raza == 0){
                                VidaTotal = (float) (vidaMC + (con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
                                playerstats.setCurStam((int) Math.round(VidaTotal * 0.5));
                            } else if(raza == 1){
                                VidaTotal = (float) (vidaMC + (con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
                                playerstats.setCurStam((int) Math.round(VidaTotal * 0.5));
                            } else if(raza == 2){
                                VidaTotal = (float) (vidaMC + (con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
                                playerstats.setCurStam((int) Math.round(VidaTotal * 0.5));
                            } else if(raza == 3){
                                VidaTotal = (float) (vidaMC + (con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
                                playerstats.setCurStam((int) Math.round(VidaTotal * 0.5));
                            } else if(raza == 4){
                                VidaTotal = (float) (vidaMC + (con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
                                playerstats.setCurStam((int) Math.round(VidaTotal * 0.5));
                            } else if(raza == 5){
                                VidaTotal = (float) (vidaMC + (con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
                                playerstats.setCurStam((int) Math.round(VidaTotal * 0.5));
                            }

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

