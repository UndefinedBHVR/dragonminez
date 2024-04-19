package com.yuseix.dragonminez.init.blocks.entity;

import com.yuseix.dragonminez.init.MainBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NamekiumFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public NamekiumFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MainBlockEntities.NAMEKIUM_FURNACE_BLOCK_ENTITY.get(), pPos, pBlockState, RecipeType.SMELTING);
    }

    protected Component getDefaultName() {
        return Component.translatable("container.dragonminez.namekium_furnace");
    }

    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return new FurnaceMenu(pId, pPlayer, this, this.dataAccess);
    }
}