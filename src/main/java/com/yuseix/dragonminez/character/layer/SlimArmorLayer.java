package com.yuseix.dragonminez.character.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends HumanoidArmorLayer<T, M, A> {


    public SlimArmorLayer(RenderLayerParent<T, M> pRenderer, A pInnerModel, A pOuterModel, ModelManager pModelManager) {
        super(pRenderer, pInnerModel, pOuterModel, pModelManager);
    }

    @Override
    protected void setPartVisibility(A pModel, EquipmentSlot pSlot) {
        super.setPartVisibility(pModel, pSlot);

        switch (pSlot) {
            case CHEST:
                pModel.rightArm.xScale = 0.75f;
                pModel.rightArm.yScale = 1.0f;
                pModel.rightArm.zScale = 1.0f;
                pModel.rightArm.x += 0.2f;

                pModel.leftArm.xScale = 0.75f;
                pModel.leftArm.yScale = 1.0f;
                pModel.leftArm.zScale = 1.0f;
                pModel.leftArm.x -= 0.2f;

                break;
            default:
                break;
        }

    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.render(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks, pAgeInTicks, pNetHeadYaw, pHeadPitch);
    }

}
