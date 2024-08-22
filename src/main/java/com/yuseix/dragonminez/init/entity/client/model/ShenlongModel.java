package com.yuseix.dragonminez.init.entity.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.ShenlongEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShenlongModel extends GeoModel<ShenlongEntity> {
    @Override
    public ResourceLocation getModelResource(ShenlongEntity shenlongEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/shenlong.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShenlongEntity shenlongEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/shenlong.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShenlongEntity shenlongEntity) {
        return null;
    }
}
