package com.yuseix.dragonminez.init.entity.client.renderer;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.NubeModel;
import com.yuseix.dragonminez.init.entity.custom.NubeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NubeRenderer extends GeoEntityRenderer<NubeEntity> {
    public NubeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NubeModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NubeEntity animatable) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/cloud.png");
    }
}
