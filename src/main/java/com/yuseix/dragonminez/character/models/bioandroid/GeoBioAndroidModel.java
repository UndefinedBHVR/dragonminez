package com.yuseix.dragonminez.character.models.bioandroid;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.DefaultedGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GeoBioAndroidModel extends DefaultedGeoModel {

    public GeoBioAndroidModel() {
        super(new ResourceLocation(DragonMineZ.MOD_ID, "bioandroidrace"));
    }

    @Override
    protected String subtype() {
        return "entity";
    }

    @Override
    public void setCustomAnimations(GeoAnimatable animatable, long instanceId, AnimationState animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if(head == null){
            return;
        }

        EntityModelData entityModelData1 = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityModelData1.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityModelData1.netHeadYaw() * Mth.DEG_TO_RAD);

    }
}
