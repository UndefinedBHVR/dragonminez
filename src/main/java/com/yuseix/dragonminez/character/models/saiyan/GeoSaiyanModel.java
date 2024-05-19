package com.yuseix.dragonminez.character.models.saiyan;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedGeoModel;

public class GeoSaiyanModel  extends DefaultedGeoModel {

    public GeoSaiyanModel() {
        super(new ResourceLocation(DragonMineZ.MOD_ID, "saiyanrace"));
    }

    @Override
    protected String subtype() {
        return "entity";
    }
}
