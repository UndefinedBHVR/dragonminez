package com.yuseix.dragonminez.init.armor.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.VegetaSaiyanArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VegetaSaiyanArmorModel extends GeoModel<VegetaSaiyanArmorItem> {
    @Override
    public ResourceLocation getModelResource(VegetaSaiyanArmorItem vegetaSaiyanArmorItem) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/armor/vegetasaiyanarmor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VegetaSaiyanArmorItem vegetaSaiyanArmorItem) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/vegetasaiyanarmor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VegetaSaiyanArmorItem vegetaSaiyanArmorItem) {
        return null;
    }
}
