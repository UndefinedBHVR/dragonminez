package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.namek.FriezaSoldierModel;
import com.yuseix.dragonminez.init.entity.custom.namek.FriezaSoldierEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FriezaSoldierRenderer extends GeoEntityRenderer<FriezaSoldierEntity> {
    public FriezaSoldierRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FriezaSoldierModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FriezaSoldierEntity animatable) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/namekusei/soldado1.png");    }
}
