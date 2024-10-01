package com.yuseix.dragonminez.init.entity.client.renderer;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.DendeModel;
import com.yuseix.dragonminez.init.entity.custom.DendeEntity;
import com.yuseix.dragonminez.init.entity.custom.KarinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DendeRenderer extends GeoEntityRenderer<DendeEntity> {
    public DendeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DendeModel());
    }

    @Override
    public ResourceLocation getTextureLocation(DendeEntity animatable) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/masters/dende_master.png");
    }
}
