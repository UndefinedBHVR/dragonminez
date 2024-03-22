package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.blocks.entity.Dball6BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball7BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball7BlockModel extends GeoModel<Dball7BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball7BlockEntity dball7BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball7BlockEntity dball7BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "textures/block/custom/dballblock7.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball7BlockEntity dball7BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "animations/dball1.animation.json");
    }
}
