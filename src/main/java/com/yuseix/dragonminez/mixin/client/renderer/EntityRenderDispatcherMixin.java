package com.yuseix.dragonminez.mixin.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.yuseix.dragonminez.character.models.GeoHumanSaiyanModel;
import com.yuseix.dragonminez.character.models.GeoMajinModel;
import com.yuseix.dragonminez.character.models.GeoNamekModel;
import com.yuseix.dragonminez.character.models.bioandroid.GeoBioAndroidModel;
import com.yuseix.dragonminez.character.renders.*;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
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

    private Map<String, GeoEntityRenderer> dmzRendererers = ImmutableMap.of();
    private Map<String, LivingEntityRenderer> dmzRendererersV2 = ImmutableMap.of();

    @Shadow
    public Map<EntityType<?>, EntityRenderer<?>> renderers;
    @Shadow
    private Map<String, EntityRenderer<? extends Player>> playerRenderers;

    @Inject(at = @At("HEAD"), method = "getRenderer(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/client/renderer/entity/EntityRenderer;", cancellable = true)
    public void dmz$getRenderer(Entity entity, CallbackInfoReturnable<EntityRenderer<? super Entity>> cir) {
        if (entity instanceof Player player) {

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {

                if(cap.getRace() == 1){
                    cir.setReturnValue(dmzRendererersV2.get("default"));
                }
                /*
                if (cap.getRace() == 2) {
                    cir.setReturnValue(dmzRendererers.get("namek"));
                } else if (cap.getRace() == 3) {
                    cir.setReturnValue(dmzRendererers.get("bioandroid_imperfect"));
                }

                if (player instanceof AbstractClientPlayer abstractClientPlayer) {
                    String modelname = abstractClientPlayer.getModelName();

                    switch (cap.getRace()) {
                        case 0:
                            if (cap.getBodytype() == 0) {
                                if ("default".equals(modelname)) {
                                    cir.setReturnValue(dmzRendererers.get(modelname));
                                } else if ("slim".equals(modelname)) {
                                    cir.setReturnValue(dmzRendererers.get(modelname));
                                }
                            } else if (cap.getBodytype() == 1) {
                                if (cap.getGender().equals("Male")) {
                                    cir.setReturnValue(dmzRendererers.get("default"));
                                } else {
                                    cir.setReturnValue(dmzRendererers.get("slim"));
                                }
                            }


                            break;

                        case 1:
                            if (cap.getBodytype() == 0) {
                                if ("default".equals(modelname)) {
                                    cir.setReturnValue(dmzRendererers.get(modelname));
                                } else if ("slim".equals(modelname)) {
                                    cir.setReturnValue(dmzRendererers.get(modelname));
                                }
                            } else if (cap.getBodytype() == 1) {
                                if (cap.getGender().equals("Male")) {
                                    cir.setReturnValue(dmzRendererersV2.get("default"));
                                } else {
                                    cir.setReturnValue(dmzRendererers.get("slim"));
                                }
                            }

                            break;
                        case 5:
                            if (cap.getGender().equals("Male")) {
                                cir.setReturnValue(dmzRendererers.get("majin_gordo"));
                            } else {
                            }
                        default:
                            break;
                    }
                }

            */
            });
        }

    }

    @Inject(at = @At("TAIL"), method = "onResourceManagerReload(Lnet/minecraft/server/packs/resources/ResourceManager;)V", locals = LocalCapture.CAPTURE_FAILHARD)
    public void dmz$reload(ResourceManager resourceManager, CallbackInfo ci, EntityRendererProvider.Context entityrendererprovider$context) {
        //dmzRendererers = reloadDMZRenderers(entityrendererprovider$context);
        dmzRendererersV2 = reloadDMZRenderersV2(entityrendererprovider$context);
    }


    private static Map<String, GeoEntityRenderer> reloadDMZRenderers(EntityRendererProvider.Context ctx) {
        ImmutableMap.Builder<String, GeoEntityRenderer> builder = ImmutableMap.builder();
        //HUMANO Y SAIYAJIN
        builder.put("default", new GeoHumanSaiyanRender(ctx, new GeoHumanSaiyanModel("stevehumansaiyanmodel")));
        builder.put("slim", new GeoHumanSaiyanRender(ctx, new GeoHumanSaiyanModel("alexhumansaiyanmodel")));
        builder.put("default", new GeoHumanSaiyanRender(ctx, new GeoHumanSaiyanModel("stevehumansaiyanmodel")));
        //HUMANO Y SAIYAJIN MODELO FEMENINO (FASE ALPHA)
        builder.put("fem", new GeoHumanSaiyanRender(ctx, new GeoHumanSaiyanModel("femhumansaiyanmodel")));
        //NAMEK BASE
        builder.put("namek", new GeoNamekRender(ctx, new GeoNamekModel()));
        //BIOANDROIDE BASE
        builder.put("bioandroid_imperfect", new GeoBioAndroidRender(ctx, new GeoBioAndroidModel()));
        //MAJIN GORDO BASE
        builder.put("majin_gordo", new GeoMajinRender(ctx, new GeoMajinModel("majinracegordo")));

        return builder.build();
    }
    private static Map<String, LivingEntityRenderer> reloadDMZRenderersV2(EntityRendererProvider.Context ctx) {
        ImmutableMap.Builder<String, LivingEntityRenderer> builder = ImmutableMap.builder();
        //HUMANO Y SAIYAJIN
        builder.put("default", new HumanSaiyanRender(ctx));

        return builder.build();
    }

}
