package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.network.PacketHandler;
import com.yuseix.dragonminez.network.packets.PacketStatsSync;
import com.yuseix.dragonminez.stats.StatsAttrProviderV2;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

public class StatsCommandV2 {

    public StatsCommandV2(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("dmzstatsv2")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))


                .then(Commands.literal("set")
                        .then(Commands.argument("stat", StringArgumentType.string())
                                .suggests((commandContext, suggestionsBuilder) -> {
                                    suggestionsBuilder.suggest("strength");
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
                                    suggestionsBuilder.suggest("strength");
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
                                    suggestionsBuilder.suggest("strength");
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

            player.getCapability(StatsAttrProviderV2.CAPABILITY).ifPresent(statsAttributesV2 -> {

                switch (stat) {
                    case "strength":
                        statsAttributesV2.removeStrenght(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Strength now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":
                        statsAttributesV2.removeDefense(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":
                        statsAttributesV2.removeConstitution(cantidad);
                        statsAttributesV2.removeStamina(cantidad);

                        statsAttributesV2.setCurStam(statsAttributesV2.getStamina() + 3);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":
                        statsAttributesV2.removeKiPower(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":
                        statsAttributesV2.removeEnergy(cantidad);
                        statsAttributesV2.setCurrentEnergy((int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get());
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":
                        statsAttributesV2.removeStrenght(cantidad);
                        statsAttributesV2.removeDefense(cantidad);
                        statsAttributesV2.removeConstitution(cantidad);
                        statsAttributesV2.removeStamina(cantidad);
                        statsAttributesV2.removeKiPower(cantidad);
                        statsAttributesV2.removeEnergy(cantidad);

                        statsAttributesV2.setCurStam(statsAttributesV2.getStamina() + 3);

                        statsAttributesV2.setCurrentEnergy((int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get());
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " all Attributes now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    default:
                        player.sendSystemMessage(Component.literal("Error!").withStyle(ChatFormatting.RED));
                        break;
                }
                PacketHandler.sendToAll(player, new PacketStatsSync(statsAttributesV2.getRace(),
                        statsAttributesV2.getHairID(), statsAttributesV2.getBodytype(), statsAttributesV2.getEyesType(),
                        statsAttributesV2.getStrength(), statsAttributesV2.getDefense(), statsAttributesV2.getConstitution(), statsAttributesV2.getCurBody(),
                        statsAttributesV2.getCurStam(), statsAttributesV2.getStamina(), statsAttributesV2.getKiPower(), statsAttributesV2.getEnergy(),
                        statsAttributesV2.getCurrentEnergy(), statsAttributesV2.getBodyColor()));
            });

        }
        return players.size();
    }

    private int addStat(CommandContext<CommandSourceStack> context, String stat, int cantidad, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {

            player.getCapability(StatsAttrProviderV2.CAPABILITY).ifPresent(statsAttributesV2 -> {

                switch (stat) {
                    case "strenght":
                        statsAttributesV2.addStrength(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Strenght now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":
                        statsAttributesV2.addDefense(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":
                        statsAttributesV2.addCon(cantidad);
                        statsAttributesV2.addStam(cantidad);

                        statsAttributesV2.setCurStam(statsAttributesV2.getStamina() + 3);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":
                        statsAttributesV2.addKipwr(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":
                        statsAttributesV2.addEnergy(cantidad);
                        statsAttributesV2.setCurrentEnergy((int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get());
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":
                        statsAttributesV2.addStrength(cantidad);
                        statsAttributesV2.addDefense(cantidad);
                        statsAttributesV2.addCon(cantidad);
                        statsAttributesV2.addStam(cantidad);
                        statsAttributesV2.addKipwr(cantidad);
                        statsAttributesV2.addEnergy(cantidad);

                        statsAttributesV2.setCurStam(statsAttributesV2.getStamina() + 3);

                        statsAttributesV2.setCurrentEnergy((int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get());
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " all Attributes now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    default:
                        player.sendSystemMessage(Component.literal("Error!").withStyle(ChatFormatting.RED));
                        break;
                }
                PacketHandler.sendToAll(player, new PacketStatsSync(statsAttributesV2.getRace(),
                        statsAttributesV2.getHairID(), statsAttributesV2.getBodytype(), statsAttributesV2.getEyesType(),
                        statsAttributesV2.getStrength(), statsAttributesV2.getDefense(), statsAttributesV2.getConstitution(), statsAttributesV2.getCurBody(),
                        statsAttributesV2.getCurStam(), statsAttributesV2.getStamina(), statsAttributesV2.getKiPower(), statsAttributesV2.getEnergy(),
                        statsAttributesV2.getCurrentEnergy(), statsAttributesV2.getBodyColor()));
            });

        }
        return players.size();
    }

    private int setStat(CommandContext<CommandSourceStack> context, String stat, int cantidad, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {

            player.getCapability(StatsAttrProviderV2.CAPABILITY).ifPresent(statsAttributesV2 -> {

                int raza = statsAttributesV2.getRace();
                int energiacurrent = 0;

                switch (stat) {
                    case "strenght":

                        statsAttributesV2.setStrength(cantidad);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Strenght now is " + statsAttributesV2.getStrength()).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":

                        statsAttributesV2.setDefense(cantidad);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + statsAttributesV2.getDefense()).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":

                        statsAttributesV2.setConstitution(cantidad);
                        statsAttributesV2.setStamina(cantidad);

                        statsAttributesV2.setCurStam(statsAttributesV2.getStamina() + 3);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + statsAttributesV2.getConstitution()).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":

                        statsAttributesV2.setKiPower(cantidad);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + statsAttributesV2.getKiPower()).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":

                        statsAttributesV2.setEnergy(cantidad);


                        if (raza == 0) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 1) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 2) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 3) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 4) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 5) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        }


                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + statsAttributesV2.getEnergy()).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":

                        statsAttributesV2.setStrength(cantidad);
                        statsAttributesV2.setDefense(cantidad);
                        statsAttributesV2.setConstitution(cantidad);
                        statsAttributesV2.setStamina(cantidad);
                        statsAttributesV2.setKiPower(cantidad);
                        statsAttributesV2.setEnergy(cantidad);


                        statsAttributesV2.setCurStam(statsAttributesV2.getStamina() + 3);

                        if (raza == 0) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 1) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 2) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 3) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 4) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        } else if (raza == 5) {
                            energiacurrent = (int) (statsAttributesV2.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get();
                            statsAttributesV2.setCurrentEnergy(energiacurrent);
                        }
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " all Attributes now is " + statsAttributesV2.getStrength()).withStyle(ChatFormatting.YELLOW));

                        break;
                    default:
                        player.sendSystemMessage(Component.literal("Error!").withStyle(ChatFormatting.RED));
                        break;
                }
                PacketHandler.sendToAll(player, new PacketStatsSync(statsAttributesV2.getRace(),
                        statsAttributesV2.getHairID(), statsAttributesV2.getBodytype(), statsAttributesV2.getEyesType(),
                        statsAttributesV2.getStrength(), statsAttributesV2.getDefense(), statsAttributesV2.getConstitution(), statsAttributesV2.getCurBody(),
                        statsAttributesV2.getCurStam(), statsAttributesV2.getStamina(), statsAttributesV2.getKiPower(), statsAttributesV2.getEnergy(),
                        statsAttributesV2.getCurrentEnergy(), statsAttributesV2.getBodyColor()));
            });

        }
        return players.size();
    }
}
