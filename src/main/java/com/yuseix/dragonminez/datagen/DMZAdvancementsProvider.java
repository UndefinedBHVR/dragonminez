package com.yuseix.dragonminez.datagen;

import com.yuseix.dragonminez.init.MainItems;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.advancements.critereon.*;

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
                    .addCriterion("obtain_dragon_egg", PlayerTrigger.TriggerInstance.located(EntityPredicate.Builder.entity().of(EntityType.PLAYER).build()))
                    .rewards(AdvancementRewards.Builder.experience(0)) // Recompensa de experiencia (Se pueden poner más tipos xd)
                    .save(consumer, "dragonminez:root"); // Logro "raíz" o "inicial"; el primero de todos.

            Advancement secondary = Advancement.Builder.advancement()
                    .parent(root) // Este depende de X logro (Solo es orden, no requisito)
                    .display(
                            Items.ENDER_PEARL,
                            Component.translatable("advancements.dragonminez.pearl.title"),
                            Component.translatable("advancements.dragonminez.pearl.description"),
                            null, FrameType.CHALLENGE, true, true, false
                    )
                    .addCriterion("obtain_ender_pearl",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Items.ENDER_PEARL).build())
                    )
                    .save(consumer, "dragonminez:obtain_pearl");
        }
    }
}