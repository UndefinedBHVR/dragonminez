package com.yuseix.dragonminez.character.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.init.items.models.BaculoEmptyModel;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArmasLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private BaculoEmptyModel baculoEmptyModel;

    public ArmasLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);

        this.baculoEmptyModel = new BaculoEmptyModel(BaculoEmptyModel.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float v, float v1, float v2, float v3, float v4, float v5) {

        VertexConsumer baculoCompleto = multiBufferSource.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DragonMineZ.MOD_ID, "textures/item/armas/baculosaco.png")));

        // Verificar si el jugador tiene el item en el inventario
        boolean hasBaculoInInventory = abstractClientPlayer.getInventory().contains(new ItemStack(MainItems.BACULO_SAGRADO.get()));
        boolean isHoldingBaculo = abstractClientPlayer.getMainHandItem().is(MainItems.BACULO_SAGRADO.get()) || abstractClientPlayer.getOffhandItem().is(MainItems.BACULO_SAGRADO.get());

        if (hasBaculoInInventory) {
            if (isHoldingBaculo) {
                // Renderizar baculo osea donde lo lleva vacio
                baculoEmptyModel.renderBaculo(poseStack,baculoCompleto, i, OverlayTexture.NO_OVERLAY, 1.0f,1.0f,1.0f,1.0f);
            } else {
                // Renderizar ambas partes si no lo tiene en la mano
                baculoEmptyModel.renderToBuffer(poseStack,baculoCompleto, i, OverlayTexture.NO_OVERLAY, 1.0f,1.0f,1.0f,1.0f);
            }
        }
    }
}
