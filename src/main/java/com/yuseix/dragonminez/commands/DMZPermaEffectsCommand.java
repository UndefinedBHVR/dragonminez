package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.arguments.EntityArgument;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class DMZPermaEffectsCommand {

    // Lista de efectos permanentes válidos
    private static final Set<String> VALID_PERMA_EFFECTS = Set.of("majin");

    public DMZPermaEffectsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzpermaeffects")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.literal("give")
                        .then(Commands.argument("effect", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    // Sugerir todos los efectos válidos al escribir el comando
                                    for (String effect : VALID_PERMA_EFFECTS) {
                                        suggestionsBuilder.suggest(effect);
                                    }
                                    return suggestionsBuilder.buildFuture();  // Completa la sugerencia
                                })
                                .executes(commandContext -> givePermaEffect(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        StringArgumentType.getString(commandContext, "effect"))
                                )
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> givePermaEffect(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                StringArgumentType.getString(commandContext, "effect"))
                                        ))
                        )
                )
                .then(Commands.literal("take")
                        .then(Commands.argument("effect", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    // Sugerir todos los efectos válidos al escribir el comando
                                    for (String effect : VALID_PERMA_EFFECTS) {
                                        suggestionsBuilder.suggest(effect);
                                    }
                                    return suggestionsBuilder.buildFuture();  // Completa la sugerencia
                                })
                                .executes(commandContext -> takePermaEffect(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException()),
                                        StringArgumentType.getString(commandContext, "effect"))
                                )
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> takePermaEffect(
                                                EntityArgument.getPlayers(commandContext, "player"),
                                                StringArgumentType.getString(commandContext, "effect"))
                                        )
                                )
                        )
                        .then(Commands.literal("all")
                                .executes(commandContext -> takeAllPermaEffects(
                                        Collections.singleton(commandContext.getSource().getPlayerOrException())
                                ))
                                .then(Commands.argument("player", EntityArgument.players())
                                        .executes(commandContext -> takeAllPermaEffects(
                                                EntityArgument.getPlayers(commandContext, "player"))
                                        )
                                )
                        )
                )
        );
    }

    // Comando para dar efectos permanentes
    private static int givePermaEffect(Collection<ServerPlayer> players, String effectName) {
        // Verifica si el efecto es válido
        if (!VALID_PERMA_EFFECTS.contains(effectName)) {
            // Si el efecto no es válido, muestra un mensaje de error con los efectos válidos
            for (ServerPlayer player : players) {
                player.sendSystemMessage(Component.translatable("command.dmzeffects.invalid_effect").append(effectName).append("\n")
                        .append(Component.translatable("command.dmzeffects.valid_effects").append(String.join(", ", VALID_PERMA_EFFECTS))));
            }
            return 0; // No ejecuta la acción
        }

        for (ServerPlayer player : players) {
            player.sendSystemMessage(Component.translatable("command.dmzeffects.give").append(effectName)
                    .append(Component.translatable("command.dmz.to")).append(player.getName()));
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.addDMZPermanentEffect(effectName, true));
        }
        return players.size();
    }

    // Comando para quitar efectos permanentes
    private static int takePermaEffect(Collection<ServerPlayer> players, String effectName) {
        // Verifica si el efecto es válido
        if (!VALID_PERMA_EFFECTS.contains(effectName)) {
            // Si el efecto no es válido, muestra un mensaje de error con los efectos válidos
            for (ServerPlayer player : players) {
                player.sendSystemMessage(Component.translatable("command.dmzeffects.invalid_effect").append(effectName).append("\n")
                        .append(Component.translatable("command.dmzeffects.valid_effects").append(String.join(", ", VALID_PERMA_EFFECTS))));
            }
            return 0; // No ejecuta la acción
        }

        for (ServerPlayer player : players) {
            player.sendSystemMessage(Component.translatable("command.dmzeffects.take").append(effectName)
                    .append(Component.translatable("command.dmz.to")).append(player.getName()));
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> playerstats.removePermanentEffect(effectName)); //playerstats.setDMZPermanentEffect(effectName, false)
        }
        return players.size();
    }

    // Comando para quitar todos los efectos permanentes
    private static int takeAllPermaEffects(Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {
            player.sendSystemMessage(Component.translatable("command.dmzeffects.perma.take.all")
                    .append(Component.translatable("command.dmz.to")).append(player.getName()));;
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
                for (String effectName : VALID_PERMA_EFFECTS) {
                    playerstats.removePermanentEffect(effectName);
                }
            });
        }
        return players.size();
    }
}