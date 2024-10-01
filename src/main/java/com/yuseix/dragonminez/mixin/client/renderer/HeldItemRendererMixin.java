package com.yuseix.dragonminez.mixin.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.character.RenderManos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;


@Mixin(ItemInHandRenderer.class)
public class HeldItemRendererMixin {

    @Shadow @Final private EntityRenderDispatcher entityRenderDispatcher;
    private Map<String, GeoEntityRenderer> dmzRendererers = ImmutableMap.of();

    private static RenderManos render;

    // Redirige el método getRenderer para devolver null, evitando el renderizado original
    @Redirect(method = "renderMapHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/HumanoidArm;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;getRenderer(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/client/renderer/entity/EntityRenderer;"))
    private <T extends Entity> EntityRenderer<? super T> injected(EntityRenderDispatcher instance, T entityrenderer) {
        return null;
    }

    // Inyecta el renderizado del brazo derecho en el método renderMapHand
    @Inject(method = "renderMapHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/HumanoidArm;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/player/PlayerRenderer;renderRightHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;)V"), cancellable = true)
    private void dmz$Right$renderMapHand(PoseStack poseStack, MultiBufferSource buffer, int light, HumanoidArm arm, CallbackInfo ci) {
        renderCustomHand(poseStack, buffer, light, arm, ci);
    }

    // Inyecta el renderizado del brazo izquierdo en el método renderMapHand
    @Inject(method = "renderMapHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/HumanoidArm;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/player/PlayerRenderer;renderLeftHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;)V"), cancellable = true)
    private void dmz$Left$renderMapHand(PoseStack poseStack, MultiBufferSource buffer, int light, HumanoidArm arm, CallbackInfo ci) {
        renderCustomHand(poseStack, buffer, light, arm, ci);
    }

    // Redirige el método getRenderer para devolver null, evitando el renderizado original
    @Redirect(method = "renderPlayerArm(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IFFLnet/minecraft/world/entity/HumanoidArm;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;getRenderer(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/client/renderer/entity/EntityRenderer;"))
    private <T extends Entity> EntityRenderer<? super T> dmz$renderPlayerArm(EntityRenderDispatcher instance, T entityrenderer) {
        return null;
    }

    // Inyecta el renderizado del brazo derecho en el método renderPlayerArm
    @Inject(method = "renderPlayerArm(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IFFLnet/minecraft/world/entity/HumanoidArm;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/player/PlayerRenderer;renderRightHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;)V"), cancellable = true)
    private void dmz$Right$renderPlayerArm(PoseStack poseStack, MultiBufferSource buffer, int light, float equippedProgress, float swingProgress, HumanoidArm arm, CallbackInfo ci) {
        renderCustomHand(poseStack, buffer, light, arm, ci);
    }

    // Inyecta el renderizado del brazo izquierdo en el método renderPlayerArm
    @Inject(method = "renderPlayerArm(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IFFLnet/minecraft/world/entity/HumanoidArm;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/player/PlayerRenderer;renderLeftHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;)V"), cancellable = true)
    private void dmz$Left$renderPlayerArm(PoseStack poseStack, MultiBufferSource buffer, int light, float equippedProgress, float swingProgress, HumanoidArm arm, CallbackInfo ci) {
        renderCustomHand(poseStack, buffer, light, arm, ci);
    }

    // Método auxiliar para renderizar el brazo personalizado
    private void renderCustomHand(PoseStack poseStack, MultiBufferSource buffer, int light, HumanoidArm arm, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayer player = mc.player;

        if (render == null) {
            render = new RenderManos(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(), mc.getItemRenderer(), mc.getBlockRenderer(), mc.getEntityRenderDispatcher().getItemInHandRenderer(), mc.getResourceManager(), mc.getEntityModels(), mc.font));
        }

        if (arm == HumanoidArm.RIGHT) {
            render.renderRightHand(poseStack, buffer, light, player);
        } else {
            render.renderLeftHand(poseStack, buffer, light, player);
        }

        ci.cancel();
    }
}