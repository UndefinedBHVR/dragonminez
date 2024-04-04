package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.blocks.entity.Dball5BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball5BlockModel extends GeoModel<Dball5BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball5BlockEntity dball5BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball5BlockEntity dball5BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/block/custom/dballblock5.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball5BlockEntity dball5BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/dball1.animation.json");
    }
}
