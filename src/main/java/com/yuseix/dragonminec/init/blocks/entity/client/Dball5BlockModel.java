package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.blocks.entity.Dball4BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball5BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball5BlockModel extends GeoModel<Dball5BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball5BlockEntity dball5BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball5BlockEntity dball5BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "textures/block/custom/dballblock5.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball5BlockEntity dball5BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "animations/dball1.animation.json");
    }
}
