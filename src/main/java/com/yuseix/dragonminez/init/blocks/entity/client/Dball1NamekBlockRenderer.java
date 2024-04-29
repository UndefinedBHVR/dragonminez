package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.init.blocks.entity.Dball1NamekBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball1NamekBlockRenderer extends GeoBlockRenderer<Dball1NamekBlockEntity> {
    public Dball1NamekBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball1NamekBlockModel());
    }
}
