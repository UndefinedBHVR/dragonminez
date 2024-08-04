package com.yuseix.dragonminez.init.armor.client.renderer;

import com.yuseix.dragonminez.init.armor.VegetaSaiyanArmor;
import com.yuseix.dragonminez.init.armor.client.model.VegetaSaiyanArmorModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class VegetaSaiyanArmorRenderer extends GeoArmorRenderer<VegetaSaiyanArmor> {
    public VegetaSaiyanArmorRenderer() {
        super(new VegetaSaiyanArmorModel());
    }
}
