package com.yuseix.dragonminez.character;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.bioandroid.BioAndroidModel;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;

@OnlyIn(Dist.CLIENT)
public class LayerDMZBase extends RenderLayer<AbstractClientPlayer, BioAndroidModel<AbstractClientPlayer>> {

    private static final ResourceLocation B_BODY1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/races/bioandroid/imperfect/body/bodybase1.png");
    private static final ResourceLocation B_BODY2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/races/bioandroid/imperfect/body/bodybase2.png");
    private static final ResourceLocation B_BODY3 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/races/bioandroid/imperfect/body/bodybase3.png");
    private static final ResourceLocation B_BODYCOLA = new ResourceLocation(DragonMineZ.MOD_ID, "textures/races/bioandroid/imperfect/body/bodycola.png");
    private static final ResourceLocation B_EYES = new ResourceLocation(DragonMineZ.MOD_ID, "textures/races/bioandroid/imperfect/eyes/eyes_0.png");
    private static final ResourceLocation B_EYE_IRIS = new ResourceLocation(DragonMineZ.MOD_ID, "textures/races/bioandroid/imperfect/eyes/eyes_0_iris.png");

    public LayerDMZBase(RenderLayerParent<AbstractClientPlayer, BioAndroidModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float v, float v1, float v2, float v3, float v4, float v5) {


        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE,abstractClientPlayer).ifPresent(cap -> {

            int raza = cap.getRace();
            int bodyType = cap.getBodytype();

                switch(raza){
                    case 0: //HUMANO
                        if(bodyType == 0){
                            VertexConsumer skin_default = multiBufferSource.getBuffer(RenderType.entitySolid(abstractClientPlayer.getSkinTextureLocation()));

                            poseStack.pushPose();

                            this.getParentModel().head.render(poseStack, skin_default, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
                            this.getParentModel().body.render(poseStack, skin_default, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
                            this.getParentModel().leftArm.render(poseStack, skin_default, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
                            this.getParentModel().leftLeg.render(poseStack, skin_default, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
                            this.getParentModel().rightArm.render(poseStack, skin_default, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
                            this.getParentModel().rightLeg.render(poseStack, skin_default, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);


                            poseStack.popPose();


                        } else if(bodyType == 1){

                            poseStack.pushPose();

                            VertexConsumer skin_type1 = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(B_BODY1));

                            this.getParentModel().renderToBuffer(poseStack, skin_type1, i, OverlayTexture.NO_OVERLAY, 0.250f, 0.232f, 0.235f, 1.0f);

                            VertexConsumer skin_type2 = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(B_BODY2));

                            this.getParentModel().renderToBuffer(poseStack, skin_type2, i, OverlayTexture.NO_OVERLAY, 0.920f, 0.920f, 0.920f, 1.0f);

                            VertexConsumer skin_type3 = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(B_BODY3));
                            this.getParentModel().renderToBuffer(poseStack, skin_type3, i, OverlayTexture.NO_OVERLAY, 0.760f, 0.760f, 0.760f, 1.0f);

                            VertexConsumer skin_type4 = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(B_BODYCOLA));
                            this.getParentModel().body.render(poseStack, skin_type4, i, OverlayTexture.NO_OVERLAY);

                            poseStack.translate(0.0f,0.0f,-0.001f);
                            VertexConsumer ojos = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(B_EYES));
                            this.getParentModel().headwa.copyFrom(this.getParentModel().head);
                            this.getParentModel().headwa.render(poseStack, ojos, i, OverlayTexture.NO_OVERLAY);

                            poseStack.translate(0.0f,0.0f,-0.002f);
                            VertexConsumer iris = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(B_EYE_IRIS));
                            this.getParentModel().headwa.render(poseStack, iris, i, OverlayTexture.NO_OVERLAY, 0.470f,0.315f,0.341f,1.0f);

                            poseStack.popPose();

                        }

                        break;
                    case 1: //SAIYAN
                        break;
                    case 2: //NAMEK
                        break;
                    case 3: //BIOANDROIDE
                        break;
                    case 4: //COLD DEMON
                        break;
                    case 5: //MAJIN
                        break;
                    default:
                        break;
                }


        });


    }
}
