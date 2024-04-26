package com.yuseix.dragonminez.character.renders.bioandroid;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidModel;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

public class GeoBioAndroidRender extends GeoReplacedEntityRenderer<Player, GeoBioAndroidPlayer> {

    private static final String LEFT_HAND = "bipedLeftHand";
    private static final String RIGHT_HAND = "bipedRightHand";
    private static final String LEFT_BOOT = "armorbipedLeftFoot";
    private static final String RIGHT_BOOT = "armorbipedRightFoot";
    private static final String LEFT_BOOT_2 = "armorBipedLeftFoot2";
    private static final String RIGHT_BOOT_2 = "armorBipedRightFoot2";
    private static final String LEFT_ARMOR_LEG = "armorbipedLeftLeg";
    private static final String RIGHT_ARMOR_LEG = "armorbipedRightLeg";
    private static final String LEFT_ARMOR_LEG_2 = "armorbipedLeftLeg2";
    private static final String RIGHT_ARMOR_LEG_2 = "armorbipedRightLeg2";
    private static final String CHESTPLATE = "armorbipedBody";
    private static final String RIGHT_SLEEVE = "armorbipedRightArm";
    private static final String LEFT_SLEEVE = "armorbipedLeftArm";
    private static final String HELMET = "armorbipedHead";



    //BIOANDROIDE
    // Ta bien, pero te diria que crees una clase aparte que se llame TextureReferences o algo así y ahí tengas todas las texturas para no saturar esta clase
    private static final ResourceLocation B_BODY1 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase1.png");
    private static final ResourceLocation B_BODY2 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase2.png");
    private static final ResourceLocation B_BODY3 = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodybase3.png");
    private static final ResourceLocation B_BODYCOLA = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/body/bodycola.png");
    private static final ResourceLocation B_EYES = new ResourceLocation(DragonMineZ.MOD_ID, "textures/entity/races/bioandroid/imperfect/eyes/eyes_0.png");

    public GeoBioAndroidRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeoBioAndroidModel(), new GeoBioAndroidPlayer());

        this.addRenderLayer(new ItemArmorGeoLayer<>(this){

            @Override
            public void preRender(PoseStack poseStack, GeoBioAndroidPlayer animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
                Player player = GeoBioAndroidRender.this.getCurrentEntity();
                this.mainHandStack = player.getItemBySlot(EquipmentSlot.MAINHAND);
                this.offhandStack = player.getItemBySlot(EquipmentSlot.OFFHAND);
                this.helmetStack = player.getItemBySlot(EquipmentSlot.HEAD);
                this.chestplateStack = player.getItemBySlot(EquipmentSlot.CHEST);
                this.leggingsStack = player.getItemBySlot(EquipmentSlot.LEGS);
                this.bootsStack = player.getItemBySlot(EquipmentSlot.FEET);
            }

            @Nullable
            @Override
            protected ItemStack getArmorItemForBone(GeoBone bone, GeoBioAndroidPlayer animatable) {
                return switch (bone.getName()){
                    case LEFT_BOOT, RIGHT_BOOT, LEFT_BOOT_2, RIGHT_BOOT_2 -> this.bootsStack;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG, LEFT_ARMOR_LEG_2, RIGHT_ARMOR_LEG_2 -> this.leggingsStack;
                    case CHESTPLATE, RIGHT_SLEEVE, LEFT_SLEEVE -> this.chestplateStack;
                    case HELMET -> this.helmetStack;
                    default -> null;
                };
            }

            @NotNull
            @Override
            protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, GeoBioAndroidPlayer animatable) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, RIGHT_BOOT, LEFT_BOOT_2, RIGHT_BOOT_2 -> EquipmentSlot.FEET;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG, LEFT_ARMOR_LEG_2, RIGHT_ARMOR_LEG_2 -> EquipmentSlot.LEGS;
                    case RIGHT_SLEEVE -> EquipmentSlot.MAINHAND;
                    case LEFT_SLEEVE -> EquipmentSlot.OFFHAND;
                    case CHESTPLATE -> EquipmentSlot.CHEST;
                    case HELMET -> EquipmentSlot.HEAD;
                    default -> super.getEquipmentSlotForBone(bone, stack, animatable);
                };
            }

            @NotNull
            @Override
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, GeoBioAndroidPlayer animatable, HumanoidModel<?> baseModel) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, LEFT_BOOT_2, LEFT_ARMOR_LEG, LEFT_ARMOR_LEG_2 -> baseModel.leftLeg;
                    case RIGHT_BOOT, RIGHT_BOOT_2, RIGHT_ARMOR_LEG, RIGHT_ARMOR_LEG_2 -> baseModel.rightLeg;
                    case RIGHT_SLEEVE -> baseModel.rightArm;
                    case LEFT_SLEEVE -> baseModel.leftArm;
                    case CHESTPLATE -> baseModel.body;
                    case HELMET -> baseModel.head;
                    default -> super.getModelPartForBone(bone, slot, stack, animatable, baseModel);
                };
            }
        });

        this.addRenderLayer(new BlockAndItemGeoLayer<>(this){

            @Nullable
            @Override
            protected ItemStack getStackForBone(GeoBone bone, GeoBioAndroidPlayer animatable) {
                Player player = GeoBioAndroidRender.this.getCurrentEntity();
                return switch (bone.getName()) {
                    case LEFT_HAND -> player.getOffhandItem();
                    case RIGHT_HAND -> player.getMainHandItem();
                    default -> null;
                };
            }

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, GeoBioAndroidPlayer animatable) {
                return switch (bone.getName()) {
                    case LEFT_HAND, RIGHT_HAND -> ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, GeoBioAndroidPlayer animatable, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {

                Player player = GeoBioAndroidRender.this.getCurrentEntity();

                if(stack == player.getMainHandItem()){
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90f));

                    if(stack.getItem() instanceof ShieldItem){
                        poseStack.translate(0, 0.125, -0.25);
                    }

                } else if (stack == player.getOffhandItem()){
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90f));

                    if (stack.getItem() instanceof ShieldItem) {
                        poseStack.translate(0, 0.125, 0.25);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }

                }

                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });


    }

    /**
        Copia de {@link GeoReplacedEntityRenderer#actuallyRender(PoseStack, GeoAnimatable, BakedGeoModel, RenderType, MultiBufferSource, VertexConsumer, boolean, float, int, int, float, float, float, float)} y {@link GeoRenderer#actuallyRender(PoseStack, GeoAnimatable, BakedGeoModel, RenderType, MultiBufferSource, VertexConsumer, boolean, float, int, int, float, float, float, float)}
        La mayoría del código es necesario, por eso es una copia directa. Lo único que hay que modificar es el punto donde se renderiza el modelo, pero eso se hace en {@link GeoRenderer} y se sobre-escribe
        en {@link GeoReplacedEntityRenderer} entonces no queda otra más que clonar el método

        borra este comentario despues xdxd
     */
    @Override
    public void actuallyRender(PoseStack poseStack, GeoBioAndroidPlayer animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();

        LivingEntity livingEntity = this.currentEntity;
        boolean shouldSit = this.currentEntity.isPassenger() && (this.currentEntity.getVehicle() != null && this.currentEntity.getVehicle().shouldRiderSit());
        float lerpBodyRot = Mth.rotLerp(partialTick, livingEntity.yBodyRotO, livingEntity.yBodyRot);
        float lerpHeadRot = Mth.rotLerp(partialTick, livingEntity.yHeadRotO, livingEntity.yHeadRot);
        float netHeadYaw = lerpHeadRot - lerpBodyRot;

        if (shouldSit && this.currentEntity.getVehicle() instanceof LivingEntity livingentity) {
            lerpBodyRot = Mth.rotLerp(partialTick, livingentity.yBodyRotO, livingentity.yBodyRot);
            netHeadYaw = lerpHeadRot - lerpBodyRot;
            float clampedHeadYaw = Mth.clamp(Mth.wrapDegrees(netHeadYaw), -85, 85);
            lerpBodyRot = lerpHeadRot - clampedHeadYaw;

            if (clampedHeadYaw * clampedHeadYaw > 2500f)
                lerpBodyRot += clampedHeadYaw * 0.2f;

            netHeadYaw = lerpHeadRot - lerpBodyRot;
        }

        if (this.currentEntity.getPose() == Pose.SLEEPING) {
            Direction bedDirection = livingEntity.getBedOrientation();

            if (bedDirection != null) {
                float eyePosOffset = livingEntity.getEyeHeight(Pose.STANDING) - 0.1F;

                poseStack.translate(-bedDirection.getStepX() * eyePosOffset, 0, -bedDirection.getStepZ() * eyePosOffset);
            }
        }

        float ageInTicks = this.currentEntity.tickCount + partialTick;
        float limbSwingAmount = 0;
        float limbSwing = 0;

        applyRotations(animatable, poseStack, ageInTicks, lerpBodyRot, partialTick);

        if (!shouldSit && this.currentEntity.isAlive()) {
            limbSwingAmount = livingEntity.walkAnimation.speed(partialTick);
            limbSwing = livingEntity.walkAnimation.position(partialTick);

            if (livingEntity.isBaby())
                limbSwing *= 3f;

            if (limbSwingAmount > 1f)
                limbSwingAmount = 1f;
        }

        float headPitch = Mth.lerp(partialTick, this.currentEntity.xRotO, this.currentEntity.getXRot());
        float motionThreshold = getMotionAnimThreshold(animatable);
        boolean isMoving;


        Vec3 velocity = livingEntity.getDeltaMovement();
        float avgVelocity = (float)(Math.abs(velocity.x) + Math.abs(velocity.z)) / 2f;

        isMoving = avgVelocity >= motionThreshold && limbSwingAmount != 0;



        if (!isReRender) {
            AnimationState<GeoBioAndroidPlayer> animationState = new AnimationState<>(animatable, limbSwing, limbSwingAmount, partialTick, isMoving);
            long instanceId = getInstanceId(animatable);

            animationState.setData(DataTickets.TICK, animatable.getTick(this.currentEntity));
            animationState.setData(DataTickets.ENTITY, this.currentEntity);
            animationState.setData(DataTickets.ENTITY_MODEL_DATA, new EntityModelData(shouldSit, livingEntity.isBaby(), -netHeadYaw, -headPitch));
            this.model.addAdditionalStateData(animatable, instanceId, animationState::setData);
            this.model.handleAnimations(animatable, instanceId, animationState);
        }

        poseStack.translate(0, 0.01f, 0);

        this.modelRenderTranslations = new Matrix4f(poseStack.last().pose());

        if (this.currentEntity.isInvisibleTo(Minecraft.getInstance().player)) {
            if (Minecraft.getInstance().shouldEntityAppearGlowing(this.currentEntity)) {
                buffer = bufferSource.getBuffer(renderType = RenderType.outline(getTextureLocation(animatable)));
            }
            else {
                renderType = null;
            }
        }

        if (renderType != null) {
            // acá es dónde realmente se renderiza el modelo , lo previo fue aplicación de rotacioens, traslaciones, escalados, animaciones, entre otras cosas
            // lo ideal sería que tengas una referencia estática de cada parte del cuerpo, "head" "body" "rightArm", etc para no tener que estar iterando entre todas
            // las partes del modelo cada tick


            render(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight,
                    packedOverlay, red, green, blue, alpha);


            /* Esto es lo que estaba originalmente, que renderizaba el modelo completo, lo dejé comentado por si te sirve para algo xd
               Si queres probar el modelo completo, quita el comentario de esto y comentá el método de arriba render(...)
            for (GeoBone group : model.topLevelBones()) {
                renderRecursively(poseStack, animatable, group, renderType, bufferSource, buffer, isReRender, partialTick, packedLight,
                        packedOverlay, red, green, blue, alpha);
            }*/
        }

        poseStack.popPose();
    }

    /**
        Extensión de actuallyRender, para no tener una función tan larga, acá va a ser dónde realmente renderizas al jugador, hace de cuenta que actuallyRender solo se encarga de
        preparar la posición, rotacion, animacion, etc del modelo
        Dejé todos los parámetros de actuallyRender, pero la mayoría te van a resultar inútiles, pero bueno, por las dudas XD
        Para obtener una referencia al jugador es currentEntity
     */
    private void render(PoseStack poseStack, GeoBioAndroidPlayer animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        var player = currentEntity;

        var appliedRenderType = RenderType.entityTranslucent(new ResourceLocation("dragonminez", "textures/entity/skindeljugador.png"));

        // como dije en otra parte, model.getBone(...) itera entre todos los grupos del modelo para encontrar el que pedís, por eso lo ideal sería que tengas una referencia
        // estática
        // los ultimos 4 parámetros son, red, green, blue, alpha
        var skin_type1 = RenderType.entityTranslucent(B_BODY1);
        var skin_type2 = RenderType.entityCutoutNoCull(B_BODY2);
        var skin_type3 = RenderType.entityCutoutNoCull(B_BODY3);
        var bcola = RenderType.entityCutoutNoCull(B_BODYCOLA);
        var ojos = RenderType.entityCutoutNoCull(B_EYES);

        // color decimal a rgb, a menos que quieras guardar los colores en la nbt con 3 variables xdxd
        int color = 16777215; // blanco
        float r = (color >> 16) / 255.0F;
        float g = ((color >> 8) & 0xff) / 255.0f;
        float b = (color & 0xff) / 255.0f;

        var head = model.getBone("bipedHead").get();
        renderRecursively(poseStack, animatable, head, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.250f, 0.232f, 0.235f, 1.0f);
        renderRecursively(poseStack, animatable, head, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.920f, 0.920f, 0.920f, 1.0f);
        renderRecursively(poseStack, animatable, head, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, 0.760f, 0.760f, 0.760f, 1.0f);

        var body = model.getBone("bipedBody").get();
        // acá si la textura tendría que cambiar, tendrias que cambiar bufferSource.getBuffer() por el de la nueva textura,
        // bufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(....nuevatextura.png)))
        renderRecursively(poseStack, animatable, body, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.250f, 0.232f, 0.235f, 1.0f);
        renderRecursively(poseStack, animatable, body, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,0.920f, 0.920f, 0.920f, 1.0f);
        renderRecursively(poseStack, animatable, body, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,0.760f, 0.760f, 0.760f, 1.0f);
        renderRecursively(poseStack, animatable, body, bcola, bufferSource, bufferSource.getBuffer(bcola), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,1f, 1f, 1f, 1.0f);

        var brazoderecho = model.getBone("bipedRightArm").get();
        renderRecursively(poseStack, animatable, brazoderecho, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.250f, 0.232f, 0.235f, 1.0f);
        renderRecursively(poseStack, animatable, brazoderecho, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.920f, 0.920f, 0.920f, 1.0f);
        renderRecursively(poseStack, animatable, brazoderecho, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, 0.760f, 0.760f, 0.760f, 1.0f);

        var brazoizquierdo = model.getBone("bipedLeftArm").get();
        renderRecursively(poseStack, animatable, brazoizquierdo, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.250f, 0.232f, 0.235f, 1.0f);
        renderRecursively(poseStack, animatable, brazoizquierdo, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.920f, 0.920f, 0.920f, 1.0f);
        renderRecursively(poseStack, animatable, brazoizquierdo, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, 0.760f, 0.760f, 0.760f, 1.0f);

        var piernaderecha = model.getBone("bipedRightLeg").get();
        renderRecursively(poseStack, animatable, piernaderecha, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.250f, 0.232f, 0.235f, 1.0f);
        renderRecursively(poseStack, animatable, piernaderecha, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.920f, 0.920f, 0.920f, 1.0f);
        renderRecursively(poseStack, animatable, piernaderecha, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, 0.760f, 0.760f, 0.760f, 1.0f);

        var piernaizquierda = model.getBone("bipedLeftLeg").get();
        renderRecursively(poseStack, animatable, piernaizquierda, skin_type1, bufferSource, bufferSource.getBuffer(skin_type1), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.250f, 0.232f, 0.235f, 1.0f);
        renderRecursively(poseStack, animatable, piernaizquierda, skin_type2, bufferSource, bufferSource.getBuffer(skin_type2), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  0.920f, 0.920f, 0.920f, 1.0f);
        renderRecursively(poseStack, animatable, piernaizquierda, skin_type3, bufferSource, bufferSource.getBuffer(skin_type3), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY, 0.760f, 0.760f, 0.760f, 1.0f);

        poseStack.translate(0.0f,0.0f,-0.001f);
        renderRecursively(poseStack, animatable, head, ojos, bufferSource, bufferSource.getBuffer(ojos), isReRender, partialTick, packedLight, OverlayTexture.NO_OVERLAY,  1f, 1f, 1f, 1.0f);

        //System.out.println("El evento cargo bien");
    }
}
