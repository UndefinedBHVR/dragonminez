package com.yuseix.dragonminez.init.items.custom;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class DbBallBlockItem extends BlockItem {

    //TODO: En un punto el código puede cambiar para diferenciar entre los distintos tipos de bolas de dragón (desde la primera a la séptima), pero por ahora se mantiene igual.
    //TODO: Se puede agregar un sonido al recoger la dragon ball o algo similar (Detector de dragon balls?).

    public DbBallBlockItem(Block pBlock, Properties pProperties) {
        //Copia las propiedades del bloque de item, actualmente sin uso.
        super(pBlock, pProperties);
    }
}
