package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public class SenzuBeanItem extends Item {
    public SenzuBeanItem() {
        super(new Properties().food(
                new FoodProperties.Builder()
                        .nutrition(10)
                        .saturationMod(0.8F)
                        .fast()

                        //Dale pana que haga algo la comida che
                        //TODO: Usar capabilities con la comida (Tarea de Yuse porque yo no tengo idea) - Bruno
                        .build()
        ));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.comida_dino_raw");
    }

    //TODO: Crear sonido distinto para la Senzu Bean?

}
