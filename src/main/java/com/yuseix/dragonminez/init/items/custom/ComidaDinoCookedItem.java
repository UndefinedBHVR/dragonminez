package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ComidaDinoCookedItem extends Item {
    public ComidaDinoCookedItem() {
        super(new Properties().food(
                new FoodProperties.Builder()
                        .nutrition(6)
                        .meat()
                        .saturationMod(0.6F)
                        //Dale pana que haga algo la comida che
                        .build()
        ));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.comida_dino_cooked");
    }

}
