package com.yuseix.dragonminez.character.models.bioandroid;

import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GeoBioAndroidModel extends DefaultedEntityGeoModel<GeoBioAndroidPlayer> {

    public GeoBioAndroidModel() {
        super(new ResourceLocation(DragonMineZ.MOD_ID, "bioandroidrace"));
    }

    @Override
    public void setCustomAnimations(GeoBioAndroidPlayer animatable, long instanceId, AnimationState<GeoBioAndroidPlayer> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("bipedHead");

        if(head == null){
            return;
        }

        EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);


    }
}
