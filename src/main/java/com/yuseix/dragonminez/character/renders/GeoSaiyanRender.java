package com.yuseix.dragonminez.character.renders;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GeoSaiyanRender<T extends AbstractClientPlayer & GeoAnimatable> extends GeoEntityRenderer<T> {
    public GeoSaiyanRender(EntityRendererProvider.Context renderManager, GeoModel<T> model) {
        super(renderManager, model);
    }
}
