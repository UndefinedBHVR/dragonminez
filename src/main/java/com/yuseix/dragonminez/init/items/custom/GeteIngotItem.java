package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GeteIngotItem extends Item {
    public GeteIngotItem() {
        super(new Properties());
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.gete_ingot");
    }

}
