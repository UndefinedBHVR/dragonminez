package com.yuseix.dragonminez.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.yuseix.dragonminez.world.StructuresCapability;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.TextColor;

public class LocationsCommand {

    private final StructuresCapability structuresCapability;

    public LocationsCommand(CommandDispatcher<CommandSourceStack> dispatcher, StructuresCapability structuresCapability) {
        this.structuresCapability = structuresCapability;

        dispatcher.register(Commands.literal("dmzlocate")
                .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                .then(Commands.argument("location", StringArgumentType.word())
                        .suggests((context, builder) -> {
                            builder.suggest("KamiLookout");
                            builder.suggest("HyperbolicTimeChamber");
                            builder.suggest("KorinTower");
                            /*
                            builder.suggest("GrandElderGuru");
                            builder.suggest("CapsuleCorp");
                            builder.suggest("KameHouse");
                             */
                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            String location = StringArgumentType.getString(context, "location");
                            CommandSourceStack source = context.getSource();
                            return showLocationMessage(source, location);
                        }))
        );
    }

    private int showLocationMessage(CommandSourceStack source, String location) {
        ServerLevel level = source.getLevel();
        ResourceKey<Level> playerDimension = level.dimension();
        Component message;

        BlockPos pos;
        boolean canTeleport = false;

        switch (location) {
            case "KamiLookout" -> {
                pos = structuresCapability.getTorreKamisamaPosition();
                if (playerDimension == Level.OVERWORLD) {
                    canTeleport = true;
                } else {
                    source.sendSuccess(() -> Component.translatable("command.dmzlocate.wrong_dimension").withStyle(ChatFormatting.RED), false);
                    return 0;
                }
                message = createLocationMessage("command.dmzlocate.kamilookout", pos, canTeleport);
            }
            case "HyperbolicTimeChamber" -> {
                pos = structuresCapability.getHabTiempoPos();
                if (playerDimension == ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY) {
                    canTeleport = true;
                } else {
                    source.sendSuccess(() -> Component.translatable("command.dmzlocate.wrong_dimension").withStyle(ChatFormatting.RED), false);
                    return 0;
                }
                message = createLocationMessage("command.dmzlocate.hyperbolictc", pos, canTeleport);
            }
            case "KorinTower" -> {
                pos = structuresCapability.getTorreKamisamaPosition();
                if (playerDimension == Level.OVERWORLD) {
                    canTeleport = true;
                } else {
                    source.sendSuccess(() -> Component.translatable("command.dmzlocate.wrong_dimension").withStyle(ChatFormatting.RED), false);
                    return 0;
                }
                message = createLocationMessage("command.dmzlocate.korintower", pos, canTeleport);
            }
            /*
            case "GrandElderGuru" -> {
                pos = structuresCapability.getGranPatriarca();
                if (playerDimension == ModDimensions.NAMEK_DIM_LEVEL_KEY) {
                    canTeleport = true;
                } else {
                    source.sendSuccess(() -> Component.translatable("command.dmzlocate.wrong_dimension").withStyle(ChatFormatting.RED), false);
                    return 0;
                }
                message = createLocationMessage("command.dmzlocate.elderguru", pos, canTeleport);
            }
            case "CapsuleCorp" -> {
                pos = structuresCapability.getCapsuleCorp();
                if (playerDimension == Level.OVERWORLD) {
                    canTeleport = true;
                } else {
                    source.sendSuccess(() -> Component.translatable("command.dmzlocate.wrong_dimension").withStyle(ChatFormatting.RED), false);
                    return 0;
                }
                message = createLocationMessage("command.dmzlocate.capsulecorp", pos, canTeleport);
            }
            case "KameHouse" -> {
                pos = structuresCapability.getKameHouse();
                if (playerDimension == Level.OVERWORLD) {
                    canTeleport = true;
                } else {
                    source.sendSuccess(() -> Component.translatable("command.dmzlocate.wrong_dimension").withStyle(ChatFormatting.RED), false);
                    return 0;
                }
                message = createLocationMessage("command.dmzlocate.kamehouse", pos, canTeleport);
            }
             */
            default -> message = Component.translatable("command.dmzlocate.unknown_location", location);
        }

        source.sendSuccess(() -> message, false);
        return 1;
    }

    private Component createLocationMessage(String structureTranslationKey, BlockPos pos, boolean canTeleport) {
        String coordsText = String.format("X %d Y %d Z %d", pos.getX(), pos.getY(), pos.getZ());

        String teleportCommand = String.format("/teleport @s %d %d %d", pos.getX(), pos.getY(), pos.getZ());

        ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, teleportCommand);
        HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("command.dmzlocate.teleport_click"));

        Style greenStyle = Style.EMPTY
                .withColor(TextColor.fromRgb(0x00FF00))  // Verde brillante
                .withClickEvent(clickEvent)
                .withHoverEvent(hoverEvent);

        Component coordsComponent = Component.literal(coordsText).setStyle(greenStyle).withStyle(ChatFormatting.UNDERLINE);

        Component message = Component.translatable(structureTranslationKey + ".location")
                .append(coordsComponent);

        if (canTeleport) {
            Component teleportMessage = Component.translatable("command.dmzlocate.teleport_click")
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFF00))); // Amarillo
            message = Component.translatable(structureTranslationKey + ".location")
                    .append(Component.literal(" "))
                    .append(coordsComponent)
                    .append(Component.literal(" "))
                    .append(teleportMessage);
        }

        return message;
    }
}
