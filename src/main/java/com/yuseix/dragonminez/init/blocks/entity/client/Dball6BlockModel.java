package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.blocks.entity.Dball6BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball6BlockModel extends GeoModel<Dball6BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball6BlockEntity dball6BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball6BlockEntity dball6BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/block/custom/dballblock6.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball6BlockEntity dball6BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/dball1.animation.json");
    }
}
