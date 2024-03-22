package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.init.blocks.entity.Dball5BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball6BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball6BlockRenderer extends GeoBlockRenderer<Dball6BlockEntity> {
    public Dball6BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball6BlockModel());
    }
}

