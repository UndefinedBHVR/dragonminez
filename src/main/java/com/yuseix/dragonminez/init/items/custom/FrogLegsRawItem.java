package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FrogLegsRawItem extends Item {
    public FrogLegsRawItem() {
        super(new Properties().food(
                new FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.3F)

                        //Dale pana que haga algo la comida che
                        .build()
        ));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.frog_legs_raw");
    }

}
