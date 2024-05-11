package com.yuseix.dragonminez.character.models;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidPlayer;
import com.yuseix.dragonminez.character.models.entity.GeoHumanEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GeoHumanModel extends DefaultedEntityGeoModel<GeoHumanEntity> {
    public GeoHumanModel() {
        super(new ResourceLocation(DragonMineZ.MOD_ID, "humanrace"));
    }

    @Override
    public void setCustomAnimations(GeoHumanEntity animatable, long instanceId, AnimationState<GeoHumanEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("bipedHead");

        if(head == null){
            return;
        }

        EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);


    }
}
