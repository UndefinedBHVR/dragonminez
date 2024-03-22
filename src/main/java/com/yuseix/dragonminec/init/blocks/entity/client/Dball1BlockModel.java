package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.blocks.entity.Dball1BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball1BlockModel extends GeoModel<Dball1BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball1BlockEntity dball1BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball1BlockEntity dball1BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "textures/block/custom/dballblock1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball1BlockEntity dball1BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "animations/dball1.animation.json");
    }
}
