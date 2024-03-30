package com.yuseix.dragonminec.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.yuseix.dragonminec.events.ModEvents;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

public class ZPointsCommand {

    public ZPointsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzpoints")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.argument("points", IntegerArgumentType.integer()).
                        executes(commandContext -> darPuntos(
                                Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                IntegerArgumentType.getInteger(commandContext, "points")))
                        .then(Commands.argument("player", EntityArgument.players())
                                .executes(commandContext -> darPuntos(
                                        EntityArgument.getPlayers(commandContext, "player"),
                                        IntegerArgumentType.getInteger(commandContext, "points")))
                        )

                )

        );

    }

    private static int darPuntos(Collection<ServerPlayer> pPlayers, int puntos) {
        for (ServerPlayer player : pPlayers) {

            player.sendSystemMessage(Component.literal("Has dado " + puntos + " puntos a " + player.getName().getString()));

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(playerstats -> playerstats.addZpoints(puntos));


        }
        return pPlayers.size();
    }


}
