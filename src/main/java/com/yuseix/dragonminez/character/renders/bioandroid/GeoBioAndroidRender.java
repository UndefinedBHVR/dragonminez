package com.yuseix.dragonminez.character.renders.bioandroid;


import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class GeoBioAndroidRender<T extends AbstractClientPlayer & GeoAnimatable> extends GeoEntityRenderer<T> {

    //BIOANDROIDE
    public GeoBioAndroidRender(EntityRendererProvider.Context renderManager, GeoModel model) {
        super(renderManager, model);

    }

}
