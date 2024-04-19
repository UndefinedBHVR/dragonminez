package com.yuseix.dragonminez.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.renders.RenderPrueba;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.stats.PlayerStatsAttrProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = {Dist.CLIENT})
public class CCEvents {

    private static RenderPrueba render;



    /*
    @SubscribeEvent
    public static void Hands(RenderArmEvent event){

        Minecraft mc = Minecraft.getInstance();
        
            RenderPrueba wa = new RenderPrueba(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(),mc.getItemRenderer(),mc.getBlockRenderer(),mc.getEntityRenderDispatcher().getItemInHandRenderer(), mc.getResourceManager(),mc.getEntityModels(),mc.font));

        var poseStack = new PoseStack();
        poseStack.translate(0.5, -0.5, -0.6);

        wa.renderRightHand(event.getPoseStack(),event.getMultiBufferSource(),event.getPackedLight(), (AbstractClientPlayer) mc.player);

        event.setCanceled(true);
    }

    */
        

/*
    @SubscribeEvent
    public static void RenderNEW(RenderPlayerEvent.Pre event) {

        event.setCanceled(true);

        Minecraft mc = Minecraft.getInstance();
        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getEntity()).ifPresent(cap -> {

            if(cap.getRace() == 0){
                if(render == null) {
                    render = new RenderPrueba(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(),mc.getItemRenderer(),mc.getBlockRenderer(),mc.getEntityRenderDispatcher().getItemInHandRenderer(), mc.getResourceManager(),mc.getEntityModels(),mc.font));
                }
                render.render((AbstractClientPlayer) event.getEntity(),event.getEntity().getYRot(),event.getPartialTick(),event.getPoseStack(),event.getMultiBufferSource(),event.getPackedLight());

            }

        });



    }

    */
    @SubscribeEvent
    public static void changeSizePOST(RenderPlayerEvent.Post event) {

       // event.getPoseStack().popPose();
        //event.getRenderer().addLayer(new LayerDMZPost(event.getRenderer()));
    }

    @SuppressWarnings({"deprecation", "removal"})
    @SubscribeEvent
    public static void cambiarTamano(EntityEvent.Size event) {

        //Obtenemos el tamaño de los atributos maximos
        float atributosMaximos = DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get();

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getEntity()).ifPresent(cap -> {

            //Obtenemos los puntos de constitucion del jugador
            int vidaJugador = cap.getConstitution();

            //Obtenemos las razas
            int razas = cap.getRace();


            if (razas == 0) { //TODO: HUMANO
                //tamaño default de la hitbox
                float xhitbox = 0.52f;
                float yhitbox = 1.65f;

                //Calculo de la estatura camara y hitbox
                float estaturaCon = Math.min(((0.21f * vidaJugador) / atributosMaximos), 0.21f);

                //Colocamos la nueva hitbox (ESTO ES IMPORTANTE PARA EL RENDERIZADO DE MODELOS A FUTURO! )
                EntityDimensions hitboxBase = new EntityDimensions((xhitbox + estaturaCon) - 0.02f, (yhitbox + estaturaCon) + 0.02f, event.getNewSize().fixed);

                event.setNewSize(hitboxBase);

                //Ponemos que la camara se coloque.
                event.setNewEyeHeight(yhitbox + estaturaCon);

                //Obtenemos el evento si el jugador esta en shift
                if (event.getEntity().isShiftKeyDown()) {
                    EntityDimensions hitboxShift = new EntityDimensions((xhitbox + estaturaCon) - 0.02f, (yhitbox + estaturaCon) - 0.07f, event.getNewSize().fixed);
                    event.setNewSize(hitboxShift);
                    event.setNewEyeHeight((yhitbox - 0.35f) + estaturaCon);
                }

                if (event.getEntity().isSwimming()) {
                    EntityDimensions hitboxSwimming = new EntityDimensions((xhitbox + estaturaCon) + 0.02f, (yhitbox + estaturaCon) - 1.01f, event.getNewSize().fixed);
                    event.setNewSize(hitboxSwimming);
                    event.setNewEyeHeight((yhitbox - 1.35f) + estaturaCon);
                }

                if (event.getEntity().isVisuallyCrawling()) {
                    EntityDimensions hitboxCrawling = new EntityDimensions((xhitbox + estaturaCon) + 0.02f, (yhitbox + estaturaCon) - 1.01f, event.getNewSize().fixed);
                    event.setNewSize(hitboxCrawling);
                    event.setNewEyeHeight((yhitbox - 1.35f) + estaturaCon);
                }

            }


        });


    }
}
