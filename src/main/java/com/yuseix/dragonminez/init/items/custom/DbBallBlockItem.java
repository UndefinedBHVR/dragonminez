package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class DbBallBlockItem extends BlockItem {

    //TODO: En un punto el código puede cambiar para diferenciar entre los distintos tipos de bolas de dragón (desde la primera a la séptima), pero por ahora se mantiene igual.
    public DbBallBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
        this.isFireResistant();
    }

}
