package com.yuseix.dragonminec.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.network.S2C.ZPointsS2C;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
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
            // Incrementa los puntos del jugador
            //int currentPoints = player.getPersistentData().getInt("zpoints");
            //player.getPersistentData().putInt("zpoints", currentPoints + puntos);

            // Envía un mensaje al jugador
            player.sendSystemMessage(Component.literal("Has dado " + puntos+ " puntos a " + player.getName().getString()));

            player.getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerStatsAttributes -> {

                playerStatsAttributes.addZpoints(puntos);

                ModMessages.sendToPlayer(new ZPointsS2C(playerStatsAttributes.getZpoints()), player);

            });

            // Sincroniza los puntos con el cliente

            System.out.println("wa");
        }
        return pPlayers.size();
    }
    /*
    private static int darPuntos(CommandContext<CommandSourceStack> pSource, Collection<ServerPlayer> pPlayer, int puntos) {

        int var3 = 0;
        Iterator var4 = pPlayer.iterator();

        while(var4.hasNext()) {
            ServerPlayer wa = (ServerPlayer) var4.next();

            //wa.getPersistentData().putInt("zpoints", wa.getPersistentData().getInt("zpoints") + puntos);

            wa.sendSystemMessage(Component.literal("Has dado " + puntos+ " puntos a " + wa.getName().getString()));

            ModMessages.sendToPlayer(new ZPointsS2C(1,puntos), wa);
            var3++;
        }



        return var3;
    }

    */

}
