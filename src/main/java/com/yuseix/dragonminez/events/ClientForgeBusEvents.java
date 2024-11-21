package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.HumanSaiyanModel;
import com.yuseix.dragonminez.character.models.SlimHumanSaiyanModel;
import com.yuseix.dragonminez.character.models.demoncold.DemonColdModel;
import com.yuseix.dragonminez.client.gui.DMZMenuTypes;
import com.yuseix.dragonminez.client.gui.KikonoArmorStationScreen;
import com.yuseix.dragonminez.client.hud.PlayerHudOverlay;
import com.yuseix.dragonminez.client.hud.spaceship.SaiyanSpacePodOverlay;
import com.yuseix.dragonminez.init.MainBlockEntities;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.renderer.*;
import com.yuseix.dragonminez.init.entity.client.renderer.fpcharacters.*;
import com.yuseix.dragonminez.init.entity.client.renderer.namek.*;
import com.yuseix.dragonminez.init.entity.client.renderer.projectil.KiSmallBallRenderer;
import com.yuseix.dragonminez.network.C2S.MenuC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeBusEvents {

    @SubscribeEvent
    public static void RenderHealthBar(RenderGuiOverlayEvent.Pre event) {
        if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        if (Keys.STATS_MENU.consumeClick()) {
            ModMessages.sendToServer(new MenuC2S());
        }

    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent e) {
        e.registerAboveAll("playerhud", PlayerHudOverlay.HUD_PLAYER);
        e.registerAboveAll("spaceshiphud", SaiyanSpacePodOverlay.HUD_SAIYAN);
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            //ENTIDADES
            EntityRenderers.register(MainEntity.DINO1.get(), DinoRenderer::new);
            EntityRenderers.register(MainEntity.NUBE_VOLADORA.get(), NubeRenderer::new);
            EntityRenderers.register(MainEntity.MASTER_KARIN.get(), KarinRenderer::new);
            EntityRenderers.register(MainEntity.SHENLONG.get(), ShenlongRenderer::new);
            EntityRenderers.register(MainEntity.PORUNGA.get(), PorungaRenderer::new);
            EntityRenderers.register(MainEntity.MASTER_DENDE.get(), DendeRenderer::new);
            EntityRenderers.register(MainEntity.NAVE_SAIYAN.get(), NaveSaiyanRenderer::new);

            EntityRenderers.register(MainEntity.NAMEK_FROG.get(), NamekFrogRenderer::new);
            EntityRenderers.register(MainEntity.PINK_FROG.get(), PinkFrogRenderer::new);
            EntityRenderers.register(MainEntity.YELLOW_FROG.get(), YellowFrogRenderer::new);
            EntityRenderers.register(MainEntity.GINYU_FROG.get(), GinyuFrogRenderer::new);

            EntityRenderers.register(MainEntity.NAMEKNPC_WARRIOR1.get(), NamekWarrior1Renderer::new);
            EntityRenderers.register(MainEntity.NAMEKNPC_WARRIOR2.get(), NamekWarrior2Renderer::new);
            EntityRenderers.register(MainEntity.NAMEKNPC_TRADER1.get(), NamekTrader1Renderer::new);
            EntityRenderers.register(MainEntity.NAMEKNPC_TRADER2.get(), NamekTrader2Renderer::new);
            EntityRenderers.register(MainEntity.NAMEKNPC_TRADER3.get(), NamekTrader3Renderer::new);
            EntityRenderers.register(MainEntity.FRIEZA_SOLDIER01.get(), FriezaSoldierRenderer::new);
            EntityRenderers.register(MainEntity.MORO_SOLDIER.get(), MoroSoldierRenderer::new);

            //FAKEPLAYERS
            EntityRenderers.register(MainEntity.FP_BIOANDROIDE.get(), FPBioAndroidRender::new);
            EntityRenderers.register(MainEntity.FP_DEMONCOLD.get(), (context) -> new FPDemonColdRender(context, new DemonColdModel<>(context.bakeLayer(DemonColdModel.LAYER_LOCATION))));
            EntityRenderers.register(MainEntity.FP_HUMANSAIYAN.get(), (context) -> new FPHumSaiRender(context, new HumanSaiyanModel<>(context.bakeLayer(HumanSaiyanModel.LAYER_LOCATION))));
            EntityRenderers.register(MainEntity.FP_SLIMSAIYANHUM.get(), (context) -> new FPSlimHumSaiRender(context, new SlimHumanSaiyanModel<>(context.bakeLayer(SlimHumanSaiyanModel.LAYER_LOCATION))));
            EntityRenderers.register(MainEntity.FP_NAMEK.get(), FPNamekianRender::new);
            EntityRenderers.register(MainEntity.FP_MAJINGORDO.get(), FPMajinGordRender::new);
            EntityRenderers.register(MainEntity.AURA.get(), AuraRenderer::new);
            EntityRenderers.register(MainEntity.KI_BLAST.get(), KiSmallBallRenderer::new);


            //BLOQUES
            BlockEntityRenderers.register(MainBlockEntities.DBALL1_NAMEK_BLOCK_ENTITY.get(), Dball1NamekBlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL2_NAMEK_BLOCK_ENTITY.get(), Dball2NamekBlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL3_NAMEK_BLOCK_ENTITY.get(), Dball3NamekBlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL4_NAMEK_BLOCK_ENTITY.get(), Dball4NamekBlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL5_NAMEK_BLOCK_ENTITY.get(), Dball5NamekBlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL6_NAMEK_BLOCK_ENTITY.get(), Dball6NamekBlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL7_NAMEK_BLOCK_ENTITY.get(), Dball7NamekBlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL1_BLOCK_ENTITY.get(), Dball1BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL2_BLOCK_ENTITY.get(), Dball2BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL3_BLOCK_ENTITY.get(), Dball3BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL4_BLOCK_ENTITY.get(), Dball4BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL5_BLOCK_ENTITY.get(), Dball5BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL6_BLOCK_ENTITY.get(), Dball6BlockRenderer::new);
            BlockEntityRenderers.register(MainBlockEntities.DBALL7_BLOCK_ENTITY.get(), Dball7BlockRenderer::new);

            ItemBlockRenderTypes.setRenderLayer(MainBlocks.KIKONO_ARMOR_STATION.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.NAMEK_AJISSA_LOG.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.NAMEK_STRIPPED_AJISSA_LOG.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.NAMEK_SACRED_LOG.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.NAMEK_STRIPPED_SACRED_LOG.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.INVISIBLE_LADDER_BLOCK.get(), RenderType.translucent());

            //MENÚS
            MenuScreens.register(DMZMenuTypes.KIKONO_ARMOR_STATION_MENU.get(), KikonoArmorStationScreen::new);

            //Vegetacion
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.CHRYSANTHEMUM_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.AMARYLLIS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.MARIGOLD_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.CATHARANTHUS_ROSEUS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.TRILLIUM_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.LOTUS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.NAMEK_FERN.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.SACRED_CHRYSANTHEMUM_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.SACRED_AMARYLLIS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.SACRED_MARIGOLD_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.SACRED_CATHARANTHUS_ROSEUS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.SACRED_TRILLIUM_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.SACRED_FERN.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.NAMEK_AJISSA_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.NAMEK_SACRED_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_CHRYSANTHEMUM_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_AMARYLLIS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_MARIGOLD_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_CATHARANTHUS_ROSEUS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_TRILLIUM_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_NAMEK_FERN.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_SACRED_CHRYSANTHEMUM_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_SACRED_AMARYLLIS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_SACRED_MARIGOLD_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_SACRED_CATHARANTHUS_ROSEUS_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_SACRED_TRILLIUM_FLOWER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_SACRED_FERN.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_AJISSA_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(MainBlocks.POTTED_SACRED_SAPLING.get(), RenderType.cutout());


            /*Outline de las Dragon Balls Helper
            MinecraftForge.EVENT_BUS.addListener(DballOutlineRenderer::renderOutlineDball);*/

        });
    }
}
