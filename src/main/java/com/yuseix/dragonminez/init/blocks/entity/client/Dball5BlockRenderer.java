package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.init.blocks.entity.Dball5BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball5BlockRenderer extends GeoBlockRenderer<Dball5BlockEntity> {
    public Dball5BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball5BlockModel());
    }
}

