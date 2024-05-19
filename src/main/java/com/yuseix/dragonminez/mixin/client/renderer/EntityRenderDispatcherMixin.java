package com.yuseix.dragonminez.mixin.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidModel;
import com.yuseix.dragonminez.character.models.saiyan.GeoSaiyanModel;
import com.yuseix.dragonminez.character.renders.GeoSaiyanRender;
import com.yuseix.dragonminez.character.renders.bioandroid.GeoBioAndroidRender;
import com.yuseix.dragonminez.events.ModEvents;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    private Map<Integer, GeoEntityRenderer> dmzRenderers = ImmutableMap.of();

    @Shadow
    public Map<EntityType<?>, EntityRenderer<?>> renderers;
    @Shadow
    private Map<String, EntityRenderer<? extends Player>> playerRenderers;

    @Inject(at = @At("HEAD"), method = "getRenderer(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/client/renderer/entity/EntityRenderer;", cancellable = true)
    public void dmz$getRenderer(Entity entity, CallbackInfoReturnable<EntityRenderer<? super Entity>> cir) {
        if(entity instanceof Player player) {

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(cap ->{

                if(cap.getRace() == 0){
                    cir.setReturnValue(dmzRenderers.get(cap.getRace()));
                } else if(cap.getRace() == 1){
                    cir.setReturnValue(dmzRenderers.get(cap.getRace()));
                }

            });
        }

    }

    @Inject(at = @At("TAIL"), method = "onResourceManagerReload(Lnet/minecraft/server/packs/resources/ResourceManager;)V", locals = LocalCapture.CAPTURE_FAILHARD)
    public void dmz$reload(ResourceManager resourceManager, CallbackInfo ci, EntityRendererProvider.Context entityrendererprovider$context) {
        dmzRenderers = reloadDragonRenderers(entityrendererprovider$context);
    }

    private static Map<Integer, GeoEntityRenderer> reloadDragonRenderers(EntityRendererProvider.Context ctx) {
        ImmutableMap.Builder<Integer, GeoEntityRenderer> builder = ImmutableMap.builder();

        builder.put(0,new GeoBioAndroidRender(ctx, new GeoBioAndroidModel()));
        builder.put(1,new GeoSaiyanRender(ctx, new GeoSaiyanModel()));

        return builder.build();
    }

}
