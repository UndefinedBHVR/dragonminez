package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

public class StatsCommand {

    public StatsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzstats")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))


                .then(Commands.literal("set")
                        .then(Commands.argument("stat", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    suggestionsBuilder.suggest("strenght");
                                    suggestionsBuilder.suggest("defense");
                                    suggestionsBuilder.suggest("constitution");
                                    suggestionsBuilder.suggest("kipower");
                                    suggestionsBuilder.suggest("energy");
                                    suggestionsBuilder.suggest("all");
                                    return suggestionsBuilder.buildFuture();
                                })
                                .then(Commands.argument("quantity", IntegerArgumentType.integer())
                                        .executes(commandContext -> setStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> setStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), EntityArgument.getPlayers(commandContext, "player")))
                                        )
                                )
                        )
                )

                .then(Commands.literal("add")
                        .then(Commands.argument("stat", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    suggestionsBuilder.suggest("strenght");
                                    suggestionsBuilder.suggest("defense");
                                    suggestionsBuilder.suggest("constitution");
                                    suggestionsBuilder.suggest("kipower");
                                    suggestionsBuilder.suggest("energy");
                                    suggestionsBuilder.suggest("all");
                                    return suggestionsBuilder.buildFuture();
                                })
                                .then(Commands.argument("quantity", IntegerArgumentType.integer())
                                        .executes(commandContext -> addStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> addStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), EntityArgument.getPlayers(commandContext, "player")))
                                        )
                                )
                        )
                )
                .then(Commands.literal("remove")
                        .then(Commands.argument("stat", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    suggestionsBuilder.suggest("strenght");
                                    suggestionsBuilder.suggest("defense");
                                    suggestionsBuilder.suggest("constitution");
                                    suggestionsBuilder.suggest("kipower");
                                    suggestionsBuilder.suggest("energy");
                                    suggestionsBuilder.suggest("all");
                                    return suggestionsBuilder.buildFuture();
                                })
                                .then(Commands.argument("quantity", IntegerArgumentType.integer())
                                        .executes(commandContext -> removeStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> removeStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "quantity"), EntityArgument.getPlayers(commandContext, "player")))
                                        )
                                )
                        )
                )
        );

    }

    private int removeStat(CommandContext<CommandSourceStack> context, String stat, int cantidad, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
                var vidaMC = 20;
                var con = stats.getConstitution();
                var maxVIDA = 0.0;
                var raza = stats.getRace();

                switch (stat) {
                    case "strenght":
                        stats.removeStrenght(cantidad);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "defense":
                        stats.removeDefense(cantidad);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":
                        stats.removeConstitution(cantidad);

                        maxVIDA = DMZDatos.calcularCON(raza, stats.getConstitution(), vidaMC, stats.getDmzClass());
                        stats.setCurStam(DMZDatos.calcularSTM(raza, (int) maxVIDA));
                        
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.constitution")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        
                        break;
                    case "kipower":
                        stats.removeKiPower(cantidad);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "energy":
                        stats.removeEnergy(cantidad);

                        stats.setCurrentEnergy(DMZDatos.calcularENE(stats.getRace(), stats.getEnergy(), stats.getDmzClass()));
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "all":
                        stats.removeStrenght(cantidad);
                        stats.removeDefense(cantidad);
                        stats.removeConstitution(cantidad);
                        stats.removeKiPower(cantidad);
                        stats.removeEnergy(cantidad);

                        maxVIDA = DMZDatos.calcularCON(raza, stats.getConstitution(), vidaMC, stats.getDmzClass());
                        stats.setCurStam(DMZDatos.calcularSTM(raza, (int) maxVIDA));

                        stats.setCurrentEnergy(DMZDatos.calcularENE(raza, stats.getEnergy(), stats.getDmzClass()));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.all")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.decreased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    default:
                        player.sendSystemMessage(Component.translatable("command.dmzstats.error").withStyle(ChatFormatting.RED));
                        break;
                }
            });

        }
        return players.size();
    }

    private int addStat(CommandContext<CommandSourceStack> context, String stat, int cantidad, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {

                var vidaMC = 20;
                var con = stats.getConstitution();
                var maxVIDA = 0.0;
                var raza = stats.getRace();

                switch (stat) {
                    case "strenght":
                        stats.addStrength(cantidad);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "defense":
                        stats.addDefense(cantidad);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":
                        stats.addCon(cantidad);

                        maxVIDA = DMZDatos.calcularCON(raza, stats.getConstitution(), vidaMC, stats.getDmzClass());
                        stats.setCurStam(DMZDatos.calcularSTM(raza, (int) maxVIDA));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.constitution")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "kipower":
                        stats.addKipwr(cantidad);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "energy":
                        stats.addEnergy(cantidad);

                        stats.setCurrentEnergy(DMZDatos.calcularENE(raza, stats.getEnergy(), stats.getDmzClass()));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "all":
                        stats.addStrength(cantidad);
                        stats.addDefense(cantidad);
                        stats.addCon(cantidad);
                        stats.addKipwr(cantidad);
                        stats.addEnergy(cantidad);

                        maxVIDA = DMZDatos.calcularCON(raza, stats.getConstitution(), vidaMC, stats.getDmzClass());
                        stats.setCurStam(DMZDatos.calcularSTM(raza, (int) maxVIDA));

                        stats.setCurrentEnergy(DMZDatos.calcularENE(raza, stats.getEnergy(), stats.getDmzClass()));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.all")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.increased")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    default:
                        player.sendSystemMessage(Component.translatable("command.dmzstats.error").withStyle(ChatFormatting.RED));
                        break;
                }
            });

        }
        return players.size();
    }

    private int setStat(CommandContext<CommandSourceStack> context, String stat, int cantidad, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {

                int raza = stats.getRace();
                int energiacurrent = 0;
                var vidaMC = 20;
                var con = stats.getConstitution();
                var maxVIDA = 0.0;

                switch (stat) {
                    case "strenght":

                        stats.setStrength(cantidad);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.strength")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "defense":

                        stats.setDefense(cantidad);
                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.defense")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "constitution":

                        stats.setConstitution(cantidad);

                        maxVIDA = DMZDatos.calcularCON(raza, stats.getConstitution(), vidaMC, stats.getDmzClass());
                        stats.setCurStam(DMZDatos.calcularSTM(raza, (int) maxVIDA));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.constitution")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "kipower":

                        stats.setKiPower(cantidad);

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.kipower")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "energy":

                        stats.setEnergy(cantidad);

                        stats.setCurrentEnergy(DMZDatos.calcularENE(raza, stats.getEnergy(), stats.getDmzClass()));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.energy")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.nowis")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    case "all":

                        stats.setStrength(cantidad);
                        stats.setDefense(cantidad);
                        stats.setConstitution(cantidad);
                        stats.setKiPower(cantidad);
                        stats.setEnergy(cantidad);


                        maxVIDA = DMZDatos.calcularCON(raza, stats.getConstitution(), vidaMC, stats.getDmzClass());
                        stats.setCurStam(DMZDatos.calcularSTM(raza, (int) maxVIDA));

                        stats.setCurrentEnergy(DMZDatos.calcularENE(raza, stats.getEnergy(), stats.getDmzClass()));

                        player.sendSystemMessage(
                                Component.translatable("command.dmzstats.done").append(" ")
                                        .append(Component.translatable("command.dmzstats.all")).append(" ")
                                        .append(player.getName()).append(" ")
                                        .append(Component.translatable("command.dmzstats.noware")).append(" ")
                                        .append(String.valueOf(cantidad)).append(Component.literal("."))
                        );
                        break;
                    default:
                        player.sendSystemMessage(Component.translatable("command.dmzstats.error").withStyle(ChatFormatting.RED));
                        break;
                }
            });

        }
        return players.size();
    }
}
