package com.yuseix.dragonminez.init.armor.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.GokuArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GokuArmorModel extends GeoModel<GokuArmorItem> {
    @Override
    public ResourceLocation getModelResource(GokuArmorItem wa) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/armor/gokugiarmor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GokuArmorItem wa) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/gokugiarmor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GokuArmorItem wa) {
        return null;
    }
}
