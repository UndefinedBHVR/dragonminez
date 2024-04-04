package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.blocks.entity.Dball4BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball4BlockModel extends GeoModel<Dball4BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball4BlockEntity dball4BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball4BlockEntity dball4BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/block/custom/dballblock4.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball4BlockEntity dball4BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/dball1.animation.json");
    }
}
