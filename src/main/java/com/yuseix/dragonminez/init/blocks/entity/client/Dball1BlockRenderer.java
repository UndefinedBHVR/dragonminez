package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.init.blocks.entity.Dball1BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball1BlockRenderer extends GeoBlockRenderer<Dball1BlockEntity> {
    public Dball1BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball1BlockModel());
    }
}
