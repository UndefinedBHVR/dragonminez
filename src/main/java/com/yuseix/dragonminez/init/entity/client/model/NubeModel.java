package com.yuseix.dragonminez.init.entity.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.custom.NubeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NubeModel extends GeoModel<NubeEntity> {
    @Override
    public ResourceLocation getModelResource(NubeEntity nubeEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/cloud.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NubeEntity nubeEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/cloud.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NubeEntity nubeEntity) {
        return null;
    }
}
