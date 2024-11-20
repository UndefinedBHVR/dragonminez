package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.events.cc.StatsEvents;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DendeC2S {

    private int option;

    public DendeC2S(int option) {
        this.option = option;
    }
    public DendeC2S(FriendlyByteBuf buf) {
        option = buf.readInt();

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(option);

    }
    public static void handle(DendeC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
                    if(packet.option == 1){
                        resetPlayerStats(player, playerstats);
                    } else if(packet.option == 2) {
                        healPlayer(player, playerstats);
                    }
                });
            }
        });
        context.setPacketHandled(true);
    }

    public static void resetPlayerStats(ServerPlayer player, DMZStatsAttributes playerstats) {
        player.displayClientMessage(Component.translatable("lines.dende.reset.success"), true);
        var race = playerstats.getRace();
        int currentEnergy = 0;

        playerstats.setAcceptCharacter(false);
        playerstats.setStrength(5);
        playerstats.setDefense(5);
        playerstats.setConstitution(5);
        playerstats.setKiPower(5);
        playerstats.setEnergy(5);
        playerstats.setZpoints(0);

        currentEnergy = DMZDatos.calcularENE(race, playerstats.getEnergy(), playerstats.getDmzClass());
        playerstats.setCurrentEnergy(currentEnergy);
    }

    private static void healPlayer(ServerPlayer player, DMZStatsAttributes playerstats) {
        player.displayClientMessage(Component.translatable("lines.dende.heal.success"), true);

        var race = playerstats.getRace();
        var con = playerstats.getConstitution();
        var energia = playerstats.getEnergy();

        double vidaTotal = DMZDatos.calcularCON(race, con, 20, playerstats.getDmzClass());
        int energiaMax = DMZDatos.calcularENE(race, energia, playerstats.getDmzClass());
        int staminaMax = DMZDatos.calcularSTM(race, (int) vidaTotal);

        player.heal((float) vidaTotal);
        playerstats.setCurStam(staminaMax);
        playerstats.setCurrentEnergy(energiaMax);

        player.getFoodData().setFoodLevel(20);
        player.getFoodData().setSaturation(15.0F);
    }
}
