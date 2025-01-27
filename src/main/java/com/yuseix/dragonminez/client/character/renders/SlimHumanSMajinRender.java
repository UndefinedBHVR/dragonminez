package com.yuseix.dragonminez.client.character.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.character.RenderManos;
import com.yuseix.dragonminez.client.character.layer.ArmasLayer;
import com.yuseix.dragonminez.client.character.layer.SlimArmorLayer;
import com.yuseix.dragonminez.client.character.layer.HairsLayer;
import com.yuseix.dragonminez.client.character.models.AuraModel;
import com.yuseix.dragonminez.client.character.models.SlimHumanSaiyanModel;
import com.yuseix.dragonminez.client.character.models.kiweapons.KiScytheModel;
import com.yuseix.dragonminez.client.character.models.kiweapons.KiSwordModel;
import com.yuseix.dragonminez.client.character.models.majin.MajinFemaleModel;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.Event;

@OnlyIn(Dist.CLIENT)
public class SlimHumanSMajinRender extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> implements DmzRenderer {

    private float colorR, colorG, colorB;
    public static final KiScytheModel kiScytheModel = new KiScytheModel(KiScytheModel.createBodyLayer().bakeRoot());
    public static final KiSwordModel kiSwordModel = new KiSwordModel(KiSwordModel.createBodyLayer().bakeRoot());

    public SlimHumanSMajinRender(EntityRendererProvider.Context pContext, PlayerModel<AbstractClientPlayer>model) {
        super(pContext,model, 0.5f);
        this.addLayer(new SlimArmorLayer(this, new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidArmorModel(pContext.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), pContext.getModelManager()));
        this.addLayer(new PlayerItemInHandLayer(this, pContext.getItemInHandRenderer()));
        this.addLayer(new ElytraLayer(this, pContext.getModelSet()));
        this.addLayer(new ParrotOnShoulderLayer(this, pContext.getModelSet()));
        this.addLayer(new SpinAttackEffectLayer(this, pContext.getModelSet()));
        this.addLayer(new BeeStingerLayer(this));
        this.addLayer(new HairsLayer(this));
        this.addLayer(new ArmasLayer(this));


    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer abstractClientPlayer) {
        return new ResourceLocation(DragonMineZ.MOD_ID,"textures/entity/prueba.png");
    }
    @Override
    public void render(AbstractClientPlayer pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.setModelProperties(pEntity);

        var playermodel = this.getModel();

        RenderNameTagEvent renderNameTagEvent = new RenderNameTagEvent(pEntity, pEntity.getDisplayName(), this, pPoseStack, pBuffer, pPackedLight, pPartialTicks);

        pPoseStack.pushPose();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {
            int transformacion = cap.getDmzState();

            if(transformacion == 0){
                pPoseStack.scale(0.9375F, 0.9375F, 0.9375F); //Tamano default de jugador
                //pPoseStack.scale(1.01F, 1.03F, 1.01F);

            }
        });

        playermodel.attackTime = this.getAttackAnim(pEntity, pPartialTicks);
        boolean shouldSit = pEntity.isPassenger() && pEntity.getVehicle() != null && pEntity.getVehicle().shouldRiderSit();
        playermodel.riding = shouldSit;
        playermodel.young = pEntity.isBaby();
        float f = Mth.rotLerp(pPartialTicks, pEntity.yBodyRotO, pEntity.yBodyRot);
        float f1 = Mth.rotLerp(pPartialTicks, pEntity.yHeadRotO, pEntity.yHeadRot);
        float f2 = f1 - f;
        float f7;
        if (shouldSit && pEntity.getVehicle() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)pEntity.getVehicle();
            f = Mth.rotLerp(pPartialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
            f2 = f1 - f;
            f7 = Mth.wrapDegrees(f2);
            if (f7 < -85.0F) {
                f7 = -85.0F;
            }

            if (f7 >= 85.0F) {
                f7 = 85.0F;
            }

            f = f1 - f7;
            if (f7 * f7 > 2500.0F) {
                f += f7 * 0.2F;
            }

            f2 = f1 - f;
        }

        float f6 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
        if (isEntityUpsideDown(pEntity)) {
            f6 *= -1.0F;
            f2 *= -1.0F;
        }

        float f8;
        if (pEntity.hasPose(Pose.SLEEPING)) {
            Direction direction = pEntity.getBedOrientation();
            if (direction != null) {
                f8 = pEntity.getEyeHeight(Pose.STANDING) - 0.1F;
                pPoseStack.translate((float)(-direction.getStepX()) * f8, 0.0F, (float)(-direction.getStepZ()) * f8);
            }
        }

        f7 = this.getBob(pEntity, pPartialTicks);
        this.setupRotations(pEntity, pPoseStack, f7, f, pPartialTicks);
        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(pEntity, pPoseStack, pPartialTicks);
        pPoseStack.translate(0.0F, -1.501F, 0.0F);
        f8 = 0.0F;
        float f5 = 0.0F;
        if (!shouldSit && pEntity.isAlive()) {
            f8 = pEntity.walkAnimation.speed(pPartialTicks);
            f5 = pEntity.walkAnimation.position(pPartialTicks);
            if (pEntity.isBaby()) {
                f5 *= 3.0F;
            }

            if (f8 > 1.0F) {
                f8 = 1.0F;
            }
        }

        playermodel.prepareMobModel(pEntity, f5, f8, pPartialTicks);
        playermodel.setupAnim(pEntity, f5, f8, f7, f2, f6);
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = this.isBodyVisible(pEntity);
        boolean flag1 = !flag && !pEntity.isInvisibleTo(minecraft.player);
        boolean flag2 = minecraft.shouldEntityAppearGlowing(pEntity);

        RenderType rendertype = getRenderType(pEntity,flag,flag1,flag2);

        if (!pEntity.isSpectator()) {
            for (RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderLayer : this.layers) {
                renderLayer.render(pPoseStack, pBuffer, pPackedLight, pEntity, f5, f8, pPartialTicks, f7, f2, f6);
            }
        }

        if (rendertype != null) {
            int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

                int bodyType = cap.getBodytype();
                var raza = cap.getRace();
                int transformacion = cap.getDmzState();
                boolean isMajinOn = cap.hasDMZPermaEffect("majin");

                switch (transformacion){
                    case 0:
                        if(raza == 0 || raza == 1){
                            if (bodyType == 0) {
                                if(pEntity.getModelName().equals("default")){

                                } else {
                                    renderFEMBodyType0(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                                }

                            } else if (bodyType > 0) {
                                pPoseStack.translate(0f,0f,0f);

                                //CUERPO CUSTOM 1
                                if (bodyType == 1) {
                                    renderFEMBodyType1(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                                }

                                //RENDER EYES

                                renderFEMALEEyes(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);

                            }

                            if(isMajinOn){
                                renderMajinMarca(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                            }

                        } else {

                            if (bodyType == 0) {
                                renderMajinFEMBodyType0(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                            }

                            renderMAJINFEyes(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);

                            if(isMajinOn){
                                renderMajinMarcaMajinRace(pEntity, pPoseStack, pBuffer, pPackedLight, i, flag1);
                            }

                        }


                        break;
                    case 1:

                        break;
                }





            });

        }



        pPoseStack.popPose();

        if (renderNameTagEvent.getResult() != Event.Result.DENY && (renderNameTagEvent.getResult() == Event.Result.ALLOW || this.shouldShowName(pEntity))) {
            this.renderNameTag(pEntity, renderNameTagEvent.getContent(), pPoseStack, pBuffer, pPackedLight);
        }


    }

    /**
     * Este metodo puede utilizarse para renderizar cualquier cosa en el mundo pero se aplicarán las rotaciones correspondientes al jugador
     * {@link  com.yuseix.dragonminez.events.ClientEvents#onRenderLevelLast(RenderLevelStageEvent)}
     */
    public void renderOnWorld(AbstractClientPlayer entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        poseStack.pushPose();

        boolean isLocalPlayer = entity == Minecraft.getInstance().player;
        boolean isFirstPerson = Minecraft.getInstance().options.getCameraType().isFirstPerson();

        float f = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);

        setupRotations(entity, poseStack, getBob(entity, partialTicks), f, partialTicks);
        poseStack.scale(-1, -1, 1);
        poseStack.translate(0.0F, -1.501F, 0.0F);

        // A partir de acá se puede renderizar cualquier cosa
        if (!isLocalPlayer || !isFirstPerson) {
            renderKiWeapons(entity, poseStack, buffer, packedLight, partialTicks);

        }

        poseStack.popPose();
    }

    private void renderKiWeapons(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float partialTicks) {


        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

            var ki_control = cap.hasSkill("ki_control");
            var ki_manipulation = cap.hasSkill("ki_manipulation");
            var meditation = cap.hasSkill("meditation");

            var is_kimanipulation = cap.isActiveSkill("ki_manipulation");
            var kiweapon_id = cap.getKiWeaponId();

            var auraColor = cap.getAuraColor();
            var colorR = (auraColor >> 16) / 255.0F;
            var colorG = ((auraColor >> 8) & 0xff) / 255.0f;
            var colorB = (auraColor & 0xff) / 255.0f;

            if(ki_control && ki_manipulation && meditation && is_kimanipulation){
                switch (kiweapon_id){
                    case "scythe":
                        kiScytheModel.translateToHand(player.getMainArm(), poseStack);
                        getModel().rightArm.translateAndRotate(poseStack);

                        // Renderizar el modelo personalizado
                        kiScytheModel.scythe.x = 6.0f;
                        kiScytheModel.scythe.y = -1.0f;
                        VertexConsumer vertexScythe = bufferSource.getBuffer(CustomRenderTypes.energy2(RenderManos.SCYTHE_TEX));
                        kiScytheModel.renderToBuffer(poseStack, vertexScythe, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                        break;
                    case "trident":
                        break;
                    default:
                        kiSwordModel.translateToHand(player.getMainArm(), poseStack);
                        getModel().rightArm.translateAndRotate(poseStack);

                        // Renderizar el modelo personalizado
                        kiSwordModel.kisword.x = 5.6f;
                        kiSwordModel.kisword.y = -0.5f;
                        VertexConsumer vertexSword = bufferSource.getBuffer(CustomRenderTypes.energy2(RenderManos.SWORD_TEX));
                        kiSwordModel.renderToBuffer(poseStack, vertexSword, packedLight, OverlayTexture.NO_OVERLAY, colorR, colorG, colorB, 1.0f);

                        break;
                }

            }


        });
    }

    private void setModelProperties(AbstractClientPlayer pClientPlayer) {
        PlayerModel<AbstractClientPlayer> playermodel = this.getModel();

        if (pClientPlayer.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.hat.visible = true;
            playermodel.head.visible = true;
        } else {

            playermodel.setAllVisible(true);

            playermodel.hat.visible = pClientPlayer.isModelPartShown(PlayerModelPart.HAT);
            playermodel.jacket.visible = pClientPlayer.isModelPartShown(PlayerModelPart.JACKET);
            playermodel.leftPants.visible = pClientPlayer.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
            playermodel.rightPants.visible = pClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
            playermodel.leftSleeve.visible = pClientPlayer.isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            playermodel.rightSleeve.visible = pClientPlayer.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
            playermodel.crouching = pClientPlayer.isCrouching();

            HumanoidModel.ArmPose humanoidmodel$armpose = getArmPose(pClientPlayer, InteractionHand.MAIN_HAND);
            HumanoidModel.ArmPose humanoidmodel$armpose1 = getArmPose(pClientPlayer, InteractionHand.OFF_HAND);

            if (humanoidmodel$armpose.isTwoHanded()) {
                humanoidmodel$armpose1 = pClientPlayer.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
            }

            if (pClientPlayer.getMainArm() == HumanoidArm.RIGHT) {
                playermodel.rightArmPose = humanoidmodel$armpose;
                playermodel.leftArmPose = humanoidmodel$armpose1;
            } else {
                playermodel.rightArmPose = humanoidmodel$armpose1;
                playermodel.leftArmPose = humanoidmodel$armpose;
            }
        }
    }

    private static HumanoidModel.ArmPose getArmPose(AbstractClientPlayer pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (pPlayer.getUsedItemHand() == pHand && pPlayer.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();
                if (useanim == UseAnim.BLOCK) {
                    return HumanoidModel.ArmPose.BLOCK;
                }

                if (useanim == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }

                if (useanim == UseAnim.SPEAR) {
                    return HumanoidModel.ArmPose.THROW_SPEAR;
                }

                if (useanim == UseAnim.CROSSBOW && pHand == pPlayer.getUsedItemHand()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }

                if (useanim == UseAnim.SPYGLASS) {
                    return HumanoidModel.ArmPose.SPYGLASS;
                }

                if (useanim == UseAnim.TOOT_HORN) {
                    return HumanoidModel.ArmPose.TOOT_HORN;
                }

                if (useanim == UseAnim.BRUSH) {
                    return HumanoidModel.ArmPose.BRUSH;
                }
            } else if (!pPlayer.swinging && itemstack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }

            HumanoidModel.ArmPose forgeArmPose = IClientItemExtensions.of(itemstack).getArmPose(pPlayer, pHand, itemstack);
            return forgeArmPose != null ? forgeArmPose : HumanoidModel.ArmPose.ITEM;
        }
    }


    private void renderFEMALEEyes(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        SlimHumanSaiyanModel<AbstractClientPlayer> playermodel = (SlimHumanSaiyanModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            int eye1color = cap.getEye1Color();
            int eye2color = cap.getEye2Color();
            int cabellocolor = cap.getHairColor();

            if(cap.getEyesType() == 0){

                //OJOS BLANCOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //CEJAS Y COLOR DE CEJAS
                colorR = (cabellocolor >> 16) / 255.0F;
                colorG = ((cabellocolor >> 8) & 0xff) / 255.0f;
                colorB = (cabellocolor & 0xff) / 255.0f;
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1_CEJAS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 1 Y COLOR DE IRIS
                colorR = (eye1color >> 16) / 255.0F;
                colorG = ((eye1color >> 8) & 0xff) / 255.0f;
                colorB = (eye1color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_IRIS1)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 2 Y COLOR DE IRIS
                colorR = (eye2color >> 16) / 255.0F;
                colorG = ((eye2color >> 8) & 0xff) / 255.0f;
                colorB = (eye2color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_IRIS2)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

            } else if(cap.getEyesType() == 1){
                //OJOS BLANCOS
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_EYES1)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //CEJAS Y COLOR DE CEJAS
                colorR = (cabellocolor >> 16) / 255.0F;
                colorG = ((cabellocolor >> 8) & 0xff) / 255.0f;
                colorB = (cabellocolor & 0xff) / 255.0f;
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_EYES1_CEJAS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 1 Y COLOR DE IRIS
                colorR = (eye1color >> 16) / 255.0F;
                colorG = ((eye1color >> 8) & 0xff) / 255.0f;
                colorB = (eye1color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_IRIS1)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

                //IRIS 2 Y COLOR DE IRIS
                colorR = (eye2color >> 16) / 255.0F;
                colorG = ((eye2color >> 8) & 0xff) / 255.0f;
                colorB = (eye2color & 0xff) / 255.0f;
                pPoseStack.translate(0f,0f,-0.001f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_2_IRIS2)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

            }


        });
    }

    private void renderMajinMarca(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        var delineado1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/humansaiyan/eyes/mmarca_eyestype1.png");
        var delineado2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/humansaiyan/eyes/mmarca_eyestype2.png");

        SlimHumanSaiyanModel<AbstractClientPlayer> playermodel = (SlimHumanSaiyanModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            if(cap.hasDMZPermaEffect("majin")){
                //Renderizamos la marca majin para todos
                pPoseStack.translate(0f,0f,-0.002f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJINMARCA)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                //Comprobamos si no es la skin por defecto de mc, si no lo es se renderiza los delineados
                //Comprobamos que solo sea humano o saiyajin para que solo renderice a esa raza
                if(cap.getRace() == 0 || cap.getRace() == 1){
                    if(cap.getBodytype() > 0){
                        if(cap.getEyesType() == 0){

                            //DELINEADO
                            pPoseStack.translate(0f,0f,-0.0011f);
                            playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(delineado1)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                        } else if(cap.getEyesType() == 1){
                            //DELINEADO
                            pPoseStack.translate(0f,0f,-0.0011f);
                            playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(delineado2)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

                        }
                    }
                }



            }

        });
    }
    private void renderMajinMarcaMajinRace(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){
        var playermodel = this.getModel();
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            if(cap.hasDMZPermaEffect("majin")){
                //Renderizamos la marca majin para todos
                pPoseStack.translate(0f,0f,-0.002f);
                playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJINMARCA)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

            }

        });
    }


    private void renderMAJINFEyes(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        MajinFemaleModel<AbstractClientPlayer> playermodel = (MajinFemaleModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            var ojoscolorbase = cap.getEye1Color();

            //OJOS BLANCOS
            pPoseStack.translate(0f,0f,-0.001f);
            playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_FEMALE_EYES_BASE)),pPackedLight, i, 1.0f,1.0f,1.0f,flag1 ? 0.15F : 1.0F);

            //IRIS COLORES
            colorR = (ojoscolorbase >> 16) / 255.0F;
            colorG = ((ojoscolorbase >> 8) & 0xff) / 255.0f;
            colorB = (ojoscolorbase & 0xff) / 255.0f;
            pPoseStack.translate(0f,0f,-0.001f);
            playermodel.head.render(pPoseStack,pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_FEMALE_EYES_IRIS)),pPackedLight, i, colorR,colorG,colorB,flag1 ? 0.15F : 1.0F);

        });
    }
    private void renderFEMBodyType0(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        SlimHumanSaiyanModel<AbstractClientPlayer> playermodel = (SlimHumanSaiyanModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            //RENDERIZAR EL CUERPO ENTERO
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(pEntity.getSkinTextureLocation())), pPackedLight, i, 1.0f, 1.0f, 1.0f, flag1 ? 0.15F : 1.0F);

        });


    }

    private void renderMajinFEMBodyType0(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        MajinFemaleModel<AbstractClientPlayer> playermodel = (MajinFemaleModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            int bodyColor1 = cap.getBodyColor();

            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;
            //RENDERIZAR EL CUERPO ENTERO
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.MAJIN_BASE_FEMALE)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);

        });

    }
    private void renderFEMBodyType1(AbstractClientPlayer pEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight,int i, boolean flag1){

        SlimHumanSaiyanModel<AbstractClientPlayer> playermodel = (SlimHumanSaiyanModel)this.getModel();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pEntity).ifPresent(cap -> {

            int bodyColor1 = cap.getBodyColor();

            colorR = (bodyColor1 >> 16) / 255.0F;
            colorG = ((bodyColor1 >> 8) & 0xff) / 255.0f;
            colorB = (bodyColor1 & 0xff) / 255.0f;
            //RENDERIZAR EL CUERPO ENTERO
            playermodel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucent(TextureManager.SH_BODY1_FEM)), pPackedLight, i, colorR, colorG, colorB, flag1 ? 0.15F : 1.0F);

        });

    }
    @Override
    protected void setupRotations(AbstractClientPlayer pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        float f = pEntityLiving.getSwimAmount(pPartialTicks);
        float f3;
        float f2;
        if (pEntityLiving.isFallFlying()) {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
            f3 = (float)pEntityLiving.getFallFlyingTicks() + pPartialTicks;
            f2 = Mth.clamp(f3 * f3 / 100.0F, 0.0F, 1.0F);
            if (!pEntityLiving.isAutoSpinAttack()) {
                pPoseStack.mulPose(Axis.XP.rotationDegrees(f2 * (-90.0F - pEntityLiving.getXRot())));
            }

            Vec3 vec3 = pEntityLiving.getViewVector(pPartialTicks);
            Vec3 vec31 = pEntityLiving.getDeltaMovementLerped(pPartialTicks);
            double d0 = vec31.horizontalDistanceSqr();
            double d1 = vec3.horizontalDistanceSqr();
            if (d0 > 0.0 && d1 > 0.0) {
                double d2 = (vec31.x * vec3.x + vec31.z * vec3.z) / Math.sqrt(d0 * d1);
                double d3 = vec31.x * vec3.z - vec31.z * vec3.x;
                pPoseStack.mulPose(Axis.YP.rotation((float)(Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
            f3 = !pEntityLiving.isInWater() && !pEntityLiving.isInFluidType((fluidType, height) -> {
                return pEntityLiving.canSwimInFluidType(fluidType);
            }) ? -90.0F : -90.0F - pEntityLiving.getXRot();
            f2 = Mth.lerp(f, 0.0F, f3);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(f2));
            if (pEntityLiving.isVisuallySwimming()) {
                pPoseStack.translate(0.0F, -1.0F, 0.3F);
            }
        } else {
            super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        }
    }
}
