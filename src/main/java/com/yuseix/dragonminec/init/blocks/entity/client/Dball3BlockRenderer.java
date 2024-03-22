package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.init.blocks.entity.Dball2BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball3BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball3BlockRenderer extends GeoBlockRenderer<Dball3BlockEntity> {
    public Dball3BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball3BlockModel());
    }
}

