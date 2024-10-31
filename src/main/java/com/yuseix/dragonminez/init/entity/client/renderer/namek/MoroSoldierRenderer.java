package com.yuseix.dragonminez.init.entity.client.renderer.namek;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.entity.client.model.namek.MoroSoldierModel;
import com.yuseix.dragonminez.init.entity.custom.namek.MoroSoldierEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MoroSoldierRenderer extends GeoEntityRenderer<MoroSoldierEntity> {
    public MoroSoldierRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MoroSoldierModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MoroSoldierEntity animatable) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/namekusei/soldado1_moro.png");    }
}
