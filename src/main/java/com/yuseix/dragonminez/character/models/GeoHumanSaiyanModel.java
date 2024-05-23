package com.yuseix.dragonminez.character.models;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedGeoModel;

public class GeoHumanSaiyanModel extends DefaultedGeoModel {

    public GeoHumanSaiyanModel(String tipo) {
        super(new ResourceLocation(DragonMineZ.MOD_ID, tipo));
    }

    @Override
    protected String subtype() {
        return "entity";
    }
}
