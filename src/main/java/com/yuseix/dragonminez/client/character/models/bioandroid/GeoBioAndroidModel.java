package com.yuseix.dragonminez.client.character.models.bioandroid;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GeoBioAndroidModel<T extends GeoAnimatable> extends DefaultedEntityGeoModel<T> {

    public GeoBioAndroidModel() {
        super(new ResourceLocation(DragonMineZ.MOD_ID, "bioandroidrace"));
    }

    @Override
    protected String subtype() {
        return "entity";
    }


}
