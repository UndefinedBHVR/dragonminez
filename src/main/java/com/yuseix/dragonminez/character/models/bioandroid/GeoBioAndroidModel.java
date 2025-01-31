package com.yuseix.dragonminez.character.models.bioandroid;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.DefaultedGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GeoBioAndroidModel<T extends GeoAnimatable> extends DefaultedEntityGeoModel<T> {

    public GeoBioAndroidModel() {
        super(new ResourceLocation(DragonMineZ.MOD_ID, "bioandroidrace"));
    }

    @Override
    protected String subtype() {
        return "entity";
    }


}
