package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.init.blocks.entity.Dball6BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball7BlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class Dball7BlockRenderer extends GeoBlockRenderer<Dball7BlockEntity> {
    public Dball7BlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new Dball7BlockModel());
    }
}

