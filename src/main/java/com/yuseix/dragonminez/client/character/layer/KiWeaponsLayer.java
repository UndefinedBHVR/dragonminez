package com.yuseix.dragonminez.client.character.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.models.kiweapons.KiScytheModel;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class KiWeaponsLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public static final ResourceLocation SCYTHE_TEX = new ResourceLocation(DragonMineZ.MOD_ID, "textures/weapons/kiweapons/scytheweapon.png");

    private KiScytheModel kiScytheModel;

    public KiWeaponsLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);

        this.kiScytheModel = new KiScytheModel(KiScytheModel.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        pPoseStack.pushPose();

        VertexConsumer vertexConsumer = pBuffer.getBuffer(CustomRenderTypes.energy2(SCYTHE_TEX));

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pLivingEntity).ifPresent(cap -> {

            var colorKi = cap.getAuraColor();
            var ki_control = cap.hasSkill("ki_control");
            var ki_manipulation = cap.hasSkill("ki_manipulation");
            var meditation = cap.hasSkill("meditation");

            var is_kimanipulation = cap.isActiveSkill("ki_manipulation");

            var colorR = (colorKi >> 16) / 255.0F;
            var colorG = ((colorKi >> 8) & 0xff) / 255.0f;
            var colorB = (colorKi & 0xff) / 255.0f;


            if(ki_control && ki_manipulation && meditation && is_kimanipulation){
                this.getParentModel().rightArm.translateAndRotate(pPoseStack);
                this.kiScytheModel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                this.kiScytheModel.renderToBuffer(pPoseStack,vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, colorR,colorG,colorB,1.0f);
            }


        });
        pPoseStack.popPose();
    }
}
