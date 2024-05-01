package com.yuseix.dragonminez.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.renders.RenderBrazos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = {Dist.CLIENT})
public class CCEvents {

    private static RenderBrazos render;


    @SubscribeEvent
    public static void Hands(RenderArmEvent event){

        Minecraft mc = Minecraft.getInstance();
        
        if(render == null){
            render = new RenderBrazos(new EntityRendererProvider.Context(mc.getEntityRenderDispatcher(),mc.getItemRenderer(),mc.getBlockRenderer(),mc.getEntityRenderDispatcher().getItemInHandRenderer(), mc.getResourceManager(),mc.getEntityModels(),mc.font));
        }
        var poseStack = new PoseStack();
        poseStack.translate(0.5, -0.5, -0.6);

        if(event.getArm() == HumanoidArm.RIGHT){
            render.renderRightHand(event.getPoseStack(),event.getMultiBufferSource(),event.getPackedLight(), (AbstractClientPlayer) mc.player);
        } else {
            render.renderLeftHand(event.getPoseStack(),event.getMultiBufferSource(),event.getPackedLight(), (AbstractClientPlayer) mc.player);

        }

        event.setCanceled(true);
    }



    /*
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

    */
}
