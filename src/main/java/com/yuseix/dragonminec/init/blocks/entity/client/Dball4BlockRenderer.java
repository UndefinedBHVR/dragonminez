package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.init.blocks.entity.Dball3BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball4BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball4BlockRenderer extends GeoBlockRenderer<Dball4BlockEntity> {
    public Dball4BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball4BlockModel());
    }
}

