package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MedicinaCorazonItem extends Item {
    public MedicinaCorazonItem() {
        super(new Properties().food(
                new FoodProperties.Builder()
                        .nutrition(20)
                        .saturationMod(1.0F)

                        //Dale pana que haga algo la comida che
                        //TODO: Usar capabilities con la comida (Tarea de Yuse porque yo no tengo idea) - Niko Bv
                        .build()
        ));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.dragonminez.heart_medicine.tooltip").withStyle(ChatFormatting.GRAY));
}
@Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.heart_medicine");
    }

}

