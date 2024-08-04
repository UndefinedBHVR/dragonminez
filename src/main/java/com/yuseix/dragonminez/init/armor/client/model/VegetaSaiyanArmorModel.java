package com.yuseix.dragonminez.init.armor.client.model;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.VegetaSaiyanArmor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VegetaSaiyanArmorModel extends GeoModel<VegetaSaiyanArmor> {
    @Override
    public ResourceLocation getModelResource(VegetaSaiyanArmor vegetaSaiyanArmor) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "geo/armor/vegetasaiyanarmor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VegetaSaiyanArmor vegetaSaiyanArmor) {
        return new ResourceLocation(DragonMineZ.MOD_ID, "textures/armor/vegetasaiyanarmor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VegetaSaiyanArmor vegetaSaiyanArmor) {
        return null;
    }
}
