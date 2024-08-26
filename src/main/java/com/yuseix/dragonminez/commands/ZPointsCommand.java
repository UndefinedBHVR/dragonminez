package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
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
                .then(Commands.literal("set")
                        .then(Commands.argument("points", IntegerArgumentType.integer()).
                                executes(commandContext -> setPuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points")))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> setPuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points")))
                                )

                        ))
                .then(Commands.literal("add")
                        .then(Commands.argument("points", IntegerArgumentType.integer()).
                                executes(commandContext -> darPuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points")))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> darPuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points")))
                                )

                        ))
                .then(Commands.literal("remove")
                        .then(Commands.argument("points", IntegerArgumentType.integer()).
                                executes(commandContext -> removePuntos(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        IntegerArgumentType.getInteger(commandContext, "points")))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> removePuntos(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                IntegerArgumentType.getInteger(commandContext, "points")))
                                )

                        ))

        );

    }

    private static int setPuntos(Collection<ServerPlayer> pPlayers, int puntos) {
        for (ServerPlayer player : pPlayers) {

            player.sendSystemMessage(Component.literal("Has dado " + puntos + " puntos a " + player.getName().getString()));

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.setZpoints(puntos));


        }
        return pPlayers.size();
    }
    private static int darPuntos(Collection<ServerPlayer> pPlayers, int puntos) {
        for (ServerPlayer player : pPlayers) {

            player.sendSystemMessage(Component.literal("Has añadido " + puntos + " puntos a " + player.getName().getString()));

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.addZpoints(puntos));


        }
        return pPlayers.size();
    }
    private static int removePuntos(Collection<ServerPlayer> pPlayers, int puntos) {
        for (ServerPlayer player : pPlayers) {

            player.sendSystemMessage(Component.literal("Has removido " + puntos + " puntos a " + player.getName().getString()));

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.removeZpoints(puntos));


        }
        return pPlayers.size();
    }
}
