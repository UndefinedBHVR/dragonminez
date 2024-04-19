package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class NamekiumItem extends Item {
    public NamekiumItem() {
        super(new Item.Properties());
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.namekium");
    }

}
