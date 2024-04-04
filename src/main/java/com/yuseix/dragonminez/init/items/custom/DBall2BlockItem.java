package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class DBall2BlockItem extends BlockItem {
    public DBall2BlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
        this.isFireResistant();
    }

}
