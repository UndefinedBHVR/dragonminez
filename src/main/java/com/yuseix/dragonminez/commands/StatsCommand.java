package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
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
                                .then(Commands.argument("cantidad", IntegerArgumentType.integer())
                                        .executes(commandContext -> setStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> setStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), EntityArgument.getPlayers(commandContext, "player")))
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
                                .then(Commands.argument("cantidad", IntegerArgumentType.integer())
                                        .executes(commandContext -> addStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> addStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), EntityArgument.getPlayers(commandContext, "player")))
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
                                .then(Commands.argument("cantidad", IntegerArgumentType.integer())
                                        .executes(commandContext -> removeStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), Collections.singleton(commandContext.getSource().getPlayerOrException())))
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> removeStat(commandContext, StringArgumentType.getString(commandContext, "stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), EntityArgument.getPlayers(commandContext, "player")))
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
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Strenght now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":
                        stats.removeDefense(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":
                        stats.removeConstitution(cantidad);

                        if(raza == 0){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 1){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_SAIYAN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 2){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_NAMEK.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 3){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_BIO.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 4){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_COLD.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 5){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_MAJIN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        }

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":
                        stats.removeKiPower(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":
                        stats.removeEnergy(cantidad);

                        if(stats.getRace() == 0){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY.get() + 40));
                        } else if(stats.getRace() == 1){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
                        } else if(stats.getRace() == 2){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
                        } else if(stats.getRace() == 3){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
                        } else if(stats.getRace() == 4){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
                        } else if(stats.getRace() == 5){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
                        }
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":
                        stats.removeStrenght(cantidad);
                        stats.removeDefense(cantidad);
                        stats.removeConstitution(cantidad);
                        stats.removeKiPower(cantidad);
                        stats.removeEnergy(cantidad);

                        if(raza == 0){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 1){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_SAIYAN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 2){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_NAMEK.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 3){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_BIO.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 4){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_COLD.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 5){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_MAJIN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        }

                        if(stats.getRace() == 0){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY.get() + 40));
                        } else if(stats.getRace() == 1){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
                        } else if(stats.getRace() == 2){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
                        } else if(stats.getRace() == 3){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
                        } else if(stats.getRace() == 4){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
                        } else if(stats.getRace() == 5){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
                        }

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " all Attributes now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    default:
                        player.sendSystemMessage(Component.literal("Error!").withStyle(ChatFormatting.RED));
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
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Strenght now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":
                        stats.addDefense(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":
                        stats.addCon(cantidad);

                        if(raza == 0){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 1){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_SAIYAN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 2){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_NAMEK.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 3){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_BIO.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 4){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_COLD.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 5){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_MAJIN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        }
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":
                        stats.addKipwr(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":
                        stats.addEnergy(cantidad);
                        if(stats.getRace() == 0){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY.get() + 40));
                        } else if(stats.getRace() == 1){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
                        } else if(stats.getRace() == 2){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
                        } else if(stats.getRace() == 3){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
                        } else if(stats.getRace() == 4){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
                        } else if(stats.getRace() == 5){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
                        }

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":
                        stats.addStrength(cantidad);
                        stats.addDefense(cantidad);
                        stats.addCon(cantidad);
                        stats.addKipwr(cantidad);
                        stats.addEnergy(cantidad);

                        if(raza == 0){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 1){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_SAIYAN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 2){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_NAMEK.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 3){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_BIO.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 4){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_COLD.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 5){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_MAJIN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        }

                        if(stats.getRace() == 0){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY.get() + 40));
                        } else if(stats.getRace() == 1){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
                        } else if(stats.getRace() == 2){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
                        } else if(stats.getRace() == 3){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
                        } else if(stats.getRace() == 4){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
                        } else if(stats.getRace() == 5){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
                        }

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " all Attributes now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    default:
                        player.sendSystemMessage(Component.literal("Error!").withStyle(ChatFormatting.RED));
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

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Strenght now is " + stats.getStrength()).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":

                        stats.setDefense(cantidad);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + stats.getDefense()).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":

                        stats.setConstitution(cantidad);

                        if(raza == 0){
                            maxVIDA = Math.round(vidaMC + ((double) stats.getConstitution() * DMZGeneralConfig.MULTIPLIER_CON.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 1){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_SAIYAN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 2){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_NAMEK.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 3){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_BIO.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 4){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_COLD.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 5){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_MAJIN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        }

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + stats.getConstitution()).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":

                        stats.setKiPower(cantidad);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + stats.getKiPower()).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":

                        stats.setEnergy(cantidad);


                        if(stats.getRace() == 0){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY.get() + 40));
                        } else if(stats.getRace() == 1){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
                        } else if(stats.getRace() == 2){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
                        } else if(stats.getRace() == 3){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
                        } else if(stats.getRace() == 4){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
                        } else if(stats.getRace() == 5){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
                        }


                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + stats.getEnergy()).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":

                        stats.setStrength(cantidad);
                        stats.setDefense(cantidad);
                        stats.setConstitution(cantidad);
                        stats.setKiPower(cantidad);
                        stats.setEnergy(cantidad);


                        if(raza == 0){
                            maxVIDA = Math.round(vidaMC + ((double) stats.getConstitution() * DMZGeneralConfig.MULTIPLIER_CON.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 1){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_SAIYAN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 2){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_NAMEK.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 3){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_BIO.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 4){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_COLD.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        } else if(raza == 5){
                            maxVIDA = Math.round(vidaMC + ((double) (stats.getConstitution()) * DMZGeneralConfig.MULTIPLIER_CON_MAJIN.get()));
                            stats.setCurStam((int) Math.round(maxVIDA * 0.5));
                        }

                        if(stats.getRace() == 0){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY.get() + 40));
                        } else if(stats.getRace() == 1){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
                        } else if(stats.getRace() == 2){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
                        } else if(stats.getRace() == 3){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
                        } else if(stats.getRace() == 4){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
                        } else if(stats.getRace() == 5){
                            stats.setCurrentEnergy( (int) Math.round(stats.getEnergy() * DMZGeneralConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
                        }

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " all Attributes now is " + stats.getStrength()).withStyle(ChatFormatting.YELLOW));

                        break;
                    default:
                        player.sendSystemMessage(Component.literal("Error!").withStyle(ChatFormatting.RED));
                        break;
                }
            });

        }
        return players.size();
    }
}
