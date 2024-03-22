package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.blocks.entity.Dball5BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball6BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball6BlockModel extends GeoModel<Dball6BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball6BlockEntity dball6BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball6BlockEntity dball6BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "textures/block/custom/dballblock6.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball6BlockEntity dball6BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "animations/dball1.animation.json");
    }
}
