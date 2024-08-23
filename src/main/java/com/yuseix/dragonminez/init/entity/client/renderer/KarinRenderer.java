package com.yuseix.dragonminez.init.entity.client.renderer;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.KarinModel;
import com.yuseix.dragonminez.init.entity.custom.KarinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KarinRenderer extends GeoEntityRenderer<KarinEntity> {
    public KarinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KarinModel());
    }

    @Override
    public ResourceLocation getTextureLocation(KarinEntity animatable) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/karin_master.png");
    }
}
