package com.yuseix.dragonminec.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.yuseix.dragonminec.events.ModEvents;
import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.network.S2C.ZPointsS2C;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class ZPointsCommand {

    public ZPointsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzpoints")
                .requires(commandSourceStack -> {
                    return commandSourceStack.hasPermission(2);
                })
                .then(Commands.argument("points", IntegerArgumentType.integer()).executes(commandContext -> {
                     return darPuntos(commandContext, Collections.singleton(((CommandSourceStack)commandContext.getSource()).getPlayerOrException()), IntegerArgumentType.getInteger(commandContext, "points"));
                                })
                        .then(Commands.argument("player", EntityArgument.players())
                                .executes(commandContext -> {
                                    return darPuntos(commandContext, EntityArgument.getPlayers(commandContext,"player"),IntegerArgumentType.getInteger(commandContext, "points") );
                                })
                        )

                )

        );

    }
    private static int darPuntos(CommandContext<CommandSourceStack> pSource, Collection<ServerPlayer> pPlayers, int puntos) {
        for (ServerPlayer player : pPlayers) {

            player.sendSystemMessage(Component.literal("Has dado " + puntos+ " puntos a " + player.getName().getString()));

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(playerstats -> {

                playerstats.addZpoints(puntos);

            });


        }
        return pPlayers.size();
    }


}
