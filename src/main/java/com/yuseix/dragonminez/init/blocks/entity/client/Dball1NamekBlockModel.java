package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.blocks.entity.Dball1NamekBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball1NamekBlockModel extends GeoModel<Dball1NamekBlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball1NamekBlockEntity dball1BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/dball1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball1NamekBlockEntity dball1BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/block/custom/dballblock1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball1NamekBlockEntity dball1BlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/dball1.animation.json");
    }
}
