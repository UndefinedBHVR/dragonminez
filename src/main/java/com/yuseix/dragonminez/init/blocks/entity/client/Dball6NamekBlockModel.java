package com.yuseix.dragonminez.init.blocks.entity.client;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.blocks.entity.Dball6NamekBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Dball6NamekBlockModel extends GeoModel<Dball6NamekBlockEntity> {
    @Override
    public ResourceLocation getModelResource(Dball6NamekBlockEntity dball6NamekBlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/dballnamek1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dball6NamekBlockEntity dball6NamekBlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/block/custom/dballnamekblock6.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dball6NamekBlockEntity dball6NamekBlockEntity) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "animations/dball1.animation.json");
    }
}
