package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

public class ResetCharacterCommand {

    public ResetCharacterCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzrestart")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .executes(commandContext -> reiniciarJugador(
                        Collections.singleton(commandContext.getSource().getPlayerOrException())))
                .then(Commands.argument("player", EntityArgument.players())
                        .executes(commandContext -> reiniciarJugador(
                                EntityArgument.getPlayers(commandContext, "player")))
                )
        );

    }

    private static int reiniciarJugador(Collection<ServerPlayer> pPlayers) {
        for (ServerPlayer player : pPlayers) {

            player.sendSystemMessage(Component.literal("The character of " + player.getName().getString() + " has been reset."));

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {

                var raza = playerstats.getRace();
                int energiacurrent = 0;

                playerstats.setAcceptCharacter(false);
                //Luego cambiar cuando decidamos las stats
                playerstats.setStrength(5);
                playerstats.setDefense(5);
                playerstats.setConstitution(5);
                playerstats.setStamina(5);
                playerstats.setCurStam(playerstats.getStamina() + 3);
                playerstats.setKiPower(5);
                playerstats.setEnergy(5);

                if (raza == 0) {
                    energiacurrent = (int) (playerstats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY.get());
                    playerstats.setCurrentEnergy(energiacurrent);
                } else if (raza == 1) {
                    energiacurrent = (int) (playerstats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get());
                    playerstats.setCurrentEnergy(energiacurrent);
                } else if (raza == 2) {
                    energiacurrent = (int) (playerstats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get());
                    playerstats.setCurrentEnergy(energiacurrent);
                } else if (raza == 3) {
                    energiacurrent = (int) (playerstats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get());
                    playerstats.setCurrentEnergy(energiacurrent);
                } else if (raza == 4) {
                    energiacurrent = (int) (playerstats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get());
                    playerstats.setCurrentEnergy(energiacurrent);
                } else if (raza == 5) {
                    energiacurrent = (int) (playerstats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get());
                    playerstats.setCurrentEnergy(energiacurrent);
                }

            });


        }
        return pPlayers.size();
    }


}
