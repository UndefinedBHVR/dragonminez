package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.arguments.EntityArgument;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
public class DMZTempEffectsCommand {

    // Lista de efectos temporales válidos
    private static final Set<String> VALID_TEMP_EFFECTS = Set.of("fruta");

    public DMZTempEffectsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmztempeffects")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.literal("give")
                        .then(Commands.argument("effect", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    // Sugerir todos los efectos válidos al escribir el comando
                                    for (String effect : VALID_TEMP_EFFECTS) {
                                        suggestionsBuilder.suggest(effect);
                                    }
                                    return suggestionsBuilder.buildFuture();  // Completa la sugerencia
                                })
                                .then(Commands.argument("seconds", IntegerArgumentType.integer(1))
                                        .executes(commandContext -> giveTempEffect(
                                                Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                                StringArgumentType.getString(commandContext, "effect"),
                                                IntegerArgumentType.getInteger(commandContext, "seconds"))
                                        )
                                )
                        )
                )
                .then(Commands.literal("take")
                        .then(Commands.argument("effect", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    // Sugerir todos los efectos válidos al escribir el comando
                                    for (String effect : VALID_TEMP_EFFECTS) {
                                        suggestionsBuilder.suggest(effect);
                                    }
                                    return suggestionsBuilder.buildFuture();  // Completa la sugerencia
                                })
                                .executes(commandContext -> takeTempEffect(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        StringArgumentType.getString(commandContext, "effect"))
                                )
                        )
                        .then(Commands.literal("all")
                                .executes(commandContext -> takeAllTempEffects(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException())
                                ))
                        )
                )
        );
    }

    // Comando para dar efectos temporales
    private static int giveTempEffect(Collection<ServerPlayer> players, String effectName, int seconds) {
        // Verifica si el efecto es válido
        if (!VALID_TEMP_EFFECTS.contains(effectName)) {
            // Si el efecto no es válido, muestra un mensaje de error
            for (ServerPlayer player : players) {
                player.sendSystemMessage(Component.translatable("command.dmzeffects.temp.invalid_effect", effectName));
            }
            return 0; // No ejecuta la acción
        }

        for (ServerPlayer player : players) {
            player.sendSystemMessage(Component.translatable("command.dmzeffects.temp.give.temporary", effectName, seconds + "s"));
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.addDMZTemporalEffect(effectName, seconds));
        }
        return players.size();
    }

    // Comando para quitar efectos temporales
    private static int takeTempEffect(Collection<ServerPlayer> players, String effectName) {
        // Verifica si el efecto es válido
        if (!VALID_TEMP_EFFECTS.contains(effectName)) {
            // Si el efecto no es válido, muestra un mensaje de error
            for (ServerPlayer player : players) {
                player.sendSystemMessage(Component.translatable("command.dmzeffects.temp.invalid_effect", effectName));
            }
            return 0; // No ejecuta la acción
        }

        for (ServerPlayer player : players) {
            player.sendSystemMessage(Component.translatable("command.dmzeffects.temp.take", effectName));
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.removeTemporalEffect(effectName));
        }
        return players.size();
    }

    // Comando para quitar todos los efectos temporales
    private static int takeAllTempEffects(Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {
            player.sendSystemMessage(Component.translatable("command.dmzeffects.temp.take.all"));
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
                for (String effectName : VALID_TEMP_EFFECTS) {
                    playerstats.removeTemporalEffect(effectName);
                }
            });
        }
        return players.size();
    }
}