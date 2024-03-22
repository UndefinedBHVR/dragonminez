package com.yuseix.dragonminec.init.blocks.entity.client;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.init.blocks.entity.Dball2BlockEntity;
import com.yuseix.dragonminec.init.blocks.entity.Dball3BlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball3BlockModel extends GeoModel<Dball3BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball3BlockEntity dball3BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball3BlockEntity dball3BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "textures/block/custom/dballblock3.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball3BlockEntity dball3BlockEntity) {
        return new ResourceLocation(DragonMineC.MODID, "animations/dball1.animation.json");
    }
}
