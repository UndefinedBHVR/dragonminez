package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.blocks.entity.Dball1BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball2BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball2BlockModel extends GeoModel<Dball2BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball2BlockEntity dball2BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball2BlockEntity dball2BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "textures/block/custom/dballblock2.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball2BlockEntity dball2BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "animations/dball1.animation.json");
    }
}
