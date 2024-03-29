package com.yuseix.dragonminec.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.events.ModEvents;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
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
                .requires(commandSourceStack -> {
                    return commandSourceStack.hasPermission(2);
                })


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
                                        .executes(commandContext -> {
                                            return setStat(commandContext, StringArgumentType.getString(commandContext,"stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), Collections.singleton(commandContext.getSource().getPlayerOrException()));
                                        })
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> {
                                                    return setStat(commandContext, StringArgumentType.getString(commandContext,"stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), EntityArgument.getPlayers(commandContext, "player"));
                                                })
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
                                        .executes(commandContext -> {
                                            return addStat(commandContext, StringArgumentType.getString(commandContext,"stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), Collections.singleton(commandContext.getSource().getPlayerOrException()));
                                        })
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> {
                                                    return addStat(commandContext, StringArgumentType.getString(commandContext,"stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), EntityArgument.getPlayers(commandContext, "player"));
                                                })
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
                                        .executes(commandContext -> {
                                            return removeStat(commandContext, StringArgumentType.getString(commandContext,"stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), Collections.singleton(commandContext.getSource().getPlayerOrException()));
                                        })
                                        .then(Commands.argument("player", EntityArgument.players())
                                                .executes(commandContext -> {
                                                    return removeStat(commandContext, StringArgumentType.getString(commandContext,"stat"), IntegerArgumentType.getInteger(commandContext, "cantidad"), EntityArgument.getPlayers(commandContext, "player"));
                                                })
                                        )
                                )
                        )
                )
        );

    }
    private int removeStat(CommandContext<CommandSourceStack> context, String stat, int cantidad, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(playerStatsAttributes -> {

                switch (stat){
                    case "strenght":
                        playerStatsAttributes.removeStrenght(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() +  " Strenght now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":
                        playerStatsAttributes.removeDefense(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":
                        playerStatsAttributes.removeConstitution(cantidad);
                        playerStatsAttributes.removeStamina(cantidad);

                        playerStatsAttributes.setCurStam(playerStatsAttributes.getStamina() + 3);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":
                        playerStatsAttributes.removeKiPower(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":
                        playerStatsAttributes.removeEnergy(cantidad);
                        playerStatsAttributes.setCurrentEnergy((int) (playerStatsAttributes.getEnergy() * 0.5)* DMCAttrConfig.MULTIPLIER_ENERGY.get());
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":
                        playerStatsAttributes.removeStrenght(cantidad);
                        playerStatsAttributes.removeDefense(cantidad);
                        playerStatsAttributes.removeConstitution(cantidad);
                        playerStatsAttributes.removeStamina(cantidad);
                        playerStatsAttributes.removeKiPower(cantidad);
                        playerStatsAttributes.removeEnergy(cantidad);

                        playerStatsAttributes.setCurStam(playerStatsAttributes.getStamina() + 3);

                        playerStatsAttributes.setCurrentEnergy((int) (playerStatsAttributes.getEnergy() * 0.5)* DMCAttrConfig.MULTIPLIER_ENERGY.get());
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

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(playerStatsAttributes -> {

                switch (stat){
                    case "strenght":
                        playerStatsAttributes.addStrength(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() +  " Strenght now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":
                        playerStatsAttributes.addDefense(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":
                        playerStatsAttributes.addCon(cantidad);
                        playerStatsAttributes.addStam(cantidad);

                        playerStatsAttributes.setCurStam(playerStatsAttributes.getStamina() + 3);

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":
                        playerStatsAttributes.addKipwr(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":
                        playerStatsAttributes.addEnergy(cantidad);
                        playerStatsAttributes.setCurrentEnergy((int) (playerStatsAttributes.getEnergy() * 0.5)* DMCAttrConfig.MULTIPLIER_ENERGY.get());
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":
                        playerStatsAttributes.addStrength(cantidad);
                        playerStatsAttributes.addDefense(cantidad);
                        playerStatsAttributes.addCon(cantidad);
                        playerStatsAttributes.addStam(cantidad);
                        playerStatsAttributes.addKipwr(cantidad);
                        playerStatsAttributes.addEnergy(cantidad);

                        playerStatsAttributes.setCurStam(playerStatsAttributes.getStamina() + 3);

                        playerStatsAttributes.setCurrentEnergy((int) (playerStatsAttributes.getEnergy() * 0.5)* DMCAttrConfig.MULTIPLIER_ENERGY.get());
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

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(playerStatsAttributes -> {

                switch (stat){
                    case "strenght":
                        playerStatsAttributes.setStrength(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() +  " Strenght now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "defense":
                        playerStatsAttributes.setDefense(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Defense now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "constitution":
                        playerStatsAttributes.setConstitution(cantidad);
                        playerStatsAttributes.setStamina(cantidad);

                        playerStatsAttributes.setCurStam(playerStatsAttributes.getStamina() + 3);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " Constitution now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "kipower":
                        playerStatsAttributes.setKiPower(cantidad);
                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " KiPower now is " + cantidad).withStyle(ChatFormatting.YELLOW));

                        break;
                    case "energy":
                        playerStatsAttributes.setEnergy(cantidad);

                        playerStatsAttributes.setCurrentEnergy((int) (playerStatsAttributes.getEnergy() * 0.5)* DMCAttrConfig.MULTIPLIER_ENERGY.get());

                        player.sendSystemMessage(Component.literal("done! " + player.getName().getString() + " MaxKi now is " + cantidad).withStyle(ChatFormatting.YELLOW));
                        break;
                    case "all":
                        playerStatsAttributes.setStrength(cantidad);
                        playerStatsAttributes.setDefense(cantidad);
                        playerStatsAttributes.setConstitution(cantidad);
                        playerStatsAttributes.setStamina(cantidad);
                        playerStatsAttributes.setKiPower(cantidad);
                        playerStatsAttributes.setEnergy(cantidad);

                        playerStatsAttributes.setCurStam(playerStatsAttributes.getStamina() + 3);

                        playerStatsAttributes.setCurrentEnergy((int) (playerStatsAttributes.getEnergy() * 0.5)* DMCAttrConfig.MULTIPLIER_ENERGY.get());

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
}
