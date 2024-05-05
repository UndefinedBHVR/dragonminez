package com.yuseix.dragonminez.init.entity.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.FakeBioAndroidEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FakeBioAndroidModel extends GeoModel<FakeBioAndroidEntity> {
    @Override
    public ResourceLocation getModelResource(FakeBioAndroidEntity fakeBioAndroidEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/entity/bioandroidrace.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FakeBioAndroidEntity fakeBioAndroidEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/bioandroidrace.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FakeBioAndroidEntity fakeBioAndroidEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/bioandroidrace.animation.json");
    }
}
