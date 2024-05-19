package com.yuseix.dragonminez.network.packets;

import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.network.Packet;
import com.yuseix.dragonminez.network.PacketHandler;
import com.yuseix.dragonminez.stats.StatsAttrProviderV2;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PacketStats implements Packet {

    private int keyNumber;
    private int cantidad;

    public PacketStats() {
    }

    public PacketStats(int keyNumber, int cantidad) {
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
        player.getCapability(StatsAttrProviderV2.CAPABILITY).ifPresent(playerstats -> {
            switch (this.keyNumber) {
                case 0:
                    playerstats.addStrength(this.cantidad);
                    break;
                case 1:
                    playerstats.addDefense(this.cantidad);
                    break;
                case 2:
                    playerstats.addCon(this.cantidad);
                    playerstats.addStam(this.cantidad);
                    player.getAttribute(Attributes.MAX_HEALTH).setBaseValue((playerstats.getConstitution() * 0.5) * DMCAttrConfig.MULTIPLIER_CON.get());
                    player.refreshDimensions();
                    break;
                case 3:
                    playerstats.addKipwr(this.cantidad);
                    break;
                case 4:
                    playerstats.addEnergy(this.cantidad);
                    break;
                default:
                    System.out.println("Algo salio mal !");
                    break;
            }
            PacketHandler.sendToAll(player, new PacketStatsSync(playerstats.getRace(), playerstats.getHairID(), playerstats.getBodytype(), playerstats.getEyesType(), playerstats.getStrength(), playerstats.getDefense(), playerstats.getConstitution(), playerstats.getCurBody(), playerstats.getCurStam(), playerstats.getStamina(), playerstats.getKiPower(), playerstats.getEnergy(), playerstats.getCurrentEnergy(), playerstats.getBodyColor()));
        });
    }
}