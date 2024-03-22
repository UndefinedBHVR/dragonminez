package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.init.blocks.entity.Dball1BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball2BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball2BlockRenderer extends GeoBlockRenderer<Dball2BlockEntity> {
    public Dball2BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball2BlockModel());
    }
}

