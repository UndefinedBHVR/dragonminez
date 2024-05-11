package com.yuseix.dragonminez.character.models;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.entity.GeoBasePlayer;
import com.yuseix.dragonminez.character.models.entity.GeoHumanEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GeoBaseModel extends DefaultedEntityGeoModel<GeoBasePlayer> {
    public GeoBaseModel() {
        super(new ResourceLocation(DragonMineZ.MOD_ID, "baseplayer"));
    }

    @Override
    public void setCustomAnimations(GeoBasePlayer animatable, long instanceId, AnimationState<GeoBasePlayer> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("bipedHead");

        if(head == null){
            return;
        }

        EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);


    }

}
