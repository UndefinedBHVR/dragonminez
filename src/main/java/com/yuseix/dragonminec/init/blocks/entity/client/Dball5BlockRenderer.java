package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.init.blocks.entity.Dball4BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball5BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball5BlockRenderer extends GeoBlockRenderer<Dball5BlockEntity> {
    public Dball5BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball5BlockModel());
    }
}

