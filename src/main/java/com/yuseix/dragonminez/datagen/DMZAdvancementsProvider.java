package com.yuseix.dragonminez.datagen;

import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import com.yuseix.dragonminez.init.MainEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DMZAdvancementsProvider extends AdvancementProvider {

    public DMZAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new DMZAdvancements()));
    }

    private static class DMZAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer) {
            Advancement root = Advancement.Builder.advancement()
                    .display(
                            MainItems.DBALL4_BLOCK_ITEM.get(), // Ítem de muestra
                            Component.translatable("advancements.dragonminez.root.title"), // Título
                            Component.translatable("advancements.dragonminez.root.description"), // Descripción
                            new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"), // Textura de fondo
                            FrameType.TASK, true, true, false
                    ) // Tipo de marco, si se muestra en la esquina superior derecha, si se muestra en el chat y si se oculta en los Logros ("Logro Oculto/Secreto")
                    .addCriterion("first_spawn_in_world", // Nombre del criterio
                            PlayerTrigger.TriggerInstance.located(EntityPredicate.Builder.entity().of(EntityType.PLAYER).build())) // Criterio
                    .rewards(AdvancementRewards.Builder.experience(0)) // Recompensa de experiencia (Se pueden poner más tipos xd)
                    .save(consumer, "dragonminez:root"); // Logro "raíz" o "inicial"; el primero de todos.

            Advancement radar = Advancement.Builder.advancement()
                    .parent(root) // Este depende de X logro (Solo es orden, no requisito)
                    .display(
                            MainItems.DBALL_RADAR_ITEM.get(),
                            Component.translatable("advancements.dragonminez.radar.title"),
                            Component.translatable("advancements.dragonminez.radar.description"),
                            null, FrameType.GOAL, true, true, false
                    )
                    .addCriterion("radar",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.DBALL_RADAR_ITEM.get())
                    )
                    .save(consumer, "dragonminez:radar");

            Advancement dball1 = Advancement.Builder.advancement()
                    .parent(radar)
                    .display(
                            MainItems.DBALL1_BLOCK_ITEM.get(),
                            Component.translatable("advancements.dragonminez.dball1.title"),
                            Component.translatable("advancements.dragonminez.dball1.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("dball1",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.DBALL1_BLOCK_ITEM.get())
                    )
                    .save(consumer, "dragonminez:dball1");

            Advancement dball7 = Advancement.Builder.advancement()
                    .parent(dball1)
                    .display(
                            MainItems.DBALL7_BLOCK_ITEM.get(),
                            Component.translatable("advancements.dragonminez.dball7.title"),
                            Component.translatable("advancements.dragonminez.dball7.description"),
                            null, FrameType.CHALLENGE, true, true, true
                    )
                    .addCriterion("dball7",
                            InventoryChangeTrigger.TriggerInstance.hasItems(
                                    MainItems.DBALL1_BLOCK_ITEM.get(),
                                    MainItems.DBALL2_BLOCK_ITEM.get(),
                                    MainItems.DBALL3_BLOCK_ITEM.get(),
                                    MainItems.DBALL4_BLOCK_ITEM.get(),
                                    MainItems.DBALL5_BLOCK_ITEM.get(),
                                    MainItems.DBALL6_BLOCK_ITEM.get(),
                                    MainItems.DBALL7_BLOCK_ITEM.get())
                    )
                    .save(consumer, "dragonminez:dball7");

            Advancement namekdim = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            MainBlocks.NAMEK_GRASS_BLOCK.get(),
                            Component.translatable("advancements.dragonminez.namekdim.title"),
                            Component.translatable("advancements.dragonminez.namekdim.description"),
                            null, FrameType.GOAL, true, true, false
                    )
                    .addCriterion("namekdim",
                            ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.NAMEK_DIM_LEVEL_KEY)
                    )
                    .save(consumer, "dragonminez:namekdim");

            Advancement radarnamek = Advancement.Builder.advancement()
                    .parent(namekdim)
                    .display(
                            MainItems.NAMEKDBALL_RADAR_ITEM.get(),
                            Component.translatable("advancements.dragonminez.radarnamek.title"),
                            Component.translatable("advancements.dragonminez.radarnamek.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("radarnamek",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.NAMEKDBALL_RADAR_ITEM.get())
                    )
                    .save(consumer, "dragonminez:radarnamek");

            Advancement namekballs = Advancement.Builder.advancement()
                    .parent(radarnamek)
                    .display(
                            MainItems.DBALL1_NAMEK_BLOCK_ITEM.get(),
                            Component.translatable("advancements.dragonminez.namekballs.title"),
                            Component.translatable("advancements.dragonminez.namekballs.description"),
                            null, FrameType.CHALLENGE, true, true, true
                    )
                    .addCriterion("namekballs",
                            InventoryChangeTrigger.TriggerInstance.hasItems(
                                    MainItems.DBALL1_NAMEK_BLOCK_ITEM.get(),
                                    MainItems.DBALL2_NAMEK_BLOCK_ITEM.get(),
                                    MainItems.DBALL3_NAMEK_BLOCK_ITEM.get(),
                                    MainItems.DBALL4_NAMEK_BLOCK_ITEM.get(),
                                    MainItems.DBALL5_NAMEK_BLOCK_ITEM.get(),
                                    MainItems.DBALL6_NAMEK_BLOCK_ITEM.get(),
                                    MainItems.DBALL7_NAMEK_BLOCK_ITEM.get())
                    )
                    .save(consumer, "dragonminez:namekballs");

            Advancement kikono = Advancement.Builder.advancement()
                    .parent(namekdim)
                    .display(
                            MainItems.KIKONO_SHARD.get(),
                            Component.translatable("advancements.dragonminez.kikono.title"),
                            Component.translatable("advancements.dragonminez.kikono.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("kikono",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.KIKONO_SHARD.get())
                    )
                    .save(consumer, "dragonminez:kikono");

            Advancement armorStation = Advancement.Builder.advancement()
                    .parent(kikono)
                    .display(
                            MainBlocks.KIKONO_ARMOR_STATION.get(),
                            Component.translatable("advancements.dragonminez.armorstation.title"),
                            Component.translatable("advancements.dragonminez.armorstation.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("armorstation",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainBlocks.KIKONO_ARMOR_STATION.get())
                    )
                    .save(consumer, "dragonminez:armorstation");

            Advancement gokuarmor = Advancement.Builder.advancement()
                    .parent(armorStation)
                    .display(
                            MainItems.GOKU_ARMOR_CHESTPLATE.get(),
                            Component.translatable("advancements.dragonminez.gokuarmor.title"),
                            Component.translatable("advancements.dragonminez.gokuarmor.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("gokuarmor",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.GOKU_ARMOR_CHESTPLATE.get(),
                                    MainItems.GOKU_ARMOR_LEGGINGS.get(),
                                    MainItems.GOKU_ARMOR_BOOTS.get())
                    )
                    .save(consumer, "dragonminez:gokuarmor");

            Advancement vegetaarmor = Advancement.Builder.advancement()
                    .parent(armorStation)
                    .display(
                            MainItems.VEGETA_SAIYAN_ARMOR_CHESTPLATE.get(),
                            Component.translatable("advancements.dragonminez.vegetaarmor.title"),
                            Component.translatable("advancements.dragonminez.vegetaarmor.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("vegetaarmor",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.VEGETA_SAIYAN_ARMOR_CHESTPLATE.get(),
                                    MainItems.VEGETA_SAIYAN_ARMOR_LEGGINGS.get(),
                                    MainItems.VEGETA_SAIYAN_ARMOR_BOOTS.get())
                    )
                    .save(consumer, "dragonminez:vegetaarmor");

            Advancement bardockarmor = Advancement.Builder.advancement()
                    .parent(armorStation)
                    .display(
                            MainItems.BARDOCK_DBZ_ARMOR_CHESTPLATE.get(),
                            Component.translatable("advancements.dragonminez.bardockarmor.title"),
                            Component.translatable("advancements.dragonminez.bardockarmor.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("bardockarmor",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.BARDOCK_DBZ_ARMOR_CHESTPLATE.get(),
                                    MainItems.BARDOCK_DBZ_ARMOR_LEGGINGS.get(),
                                    MainItems.BARDOCK_DBZ_ARMOR_BOOTS.get())
                    )
                    .save(consumer, "dragonminez:bardockarmor");

            Advancement piccoloarmor = Advancement.Builder.advancement()
                    .parent(armorStation)
                    .display(
                            MainItems.PICCOLO_ARMOR_CHESTPLATE.get(),
                            Component.translatable("advancements.dragonminez.piccoloarmor.title"),
                            Component.translatable("advancements.dragonminez.piccoloarmor.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("piccoloarmor",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.PICCOLO_ARMOR_CHESTPLATE.get(),
                                    MainItems.PICCOLO_ARMOR_LEGGINGS.get(),
                                    MainItems.PICCOLO_ARMOR_BOOTS.get())
                    )
                    .save(consumer, "dragonminez:piccoloarmor");

            Advancement demonbluegiarmor = Advancement.Builder.advancement()
                    .parent(armorStation)
                    .display(
                            MainItems.DEMON_GI_BLUE_ARMOR_CHESTPLATE.get(),
                            Component.translatable("advancements.dragonminez.demonbluegiarmor.title"),
                            Component.translatable("advancements.dragonminez.demonbluegiarmor.description"),
                            null, FrameType.TASK, true, true, false
                    )
                    .addCriterion("demonbluegiarmor",
                            InventoryChangeTrigger.TriggerInstance.hasItems(MainItems.DEMON_GI_BLUE_ARMOR_CHESTPLATE.get(),
                                    MainItems.DEMON_GI_BLUE_ARMOR_LEGGINGS.get(),
                                    MainItems.DEMON_GI_BLUE_ARMOR_BOOTS.get())
                    )
                    .save(consumer, "dragonminez:demonbluegiarmor");
        }
    }
}