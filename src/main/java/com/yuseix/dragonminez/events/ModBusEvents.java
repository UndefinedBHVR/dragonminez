package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.character.models.HumanSaiyanModel;
import com.yuseix.dragonminez.character.models.NamekianModel;
import com.yuseix.dragonminez.character.models.SlimHumanSaiyanModel;
import com.yuseix.dragonminez.character.models.bioandroid.BioAndroideModelo;
import com.yuseix.dragonminez.character.models.demoncold.DemonColdModel;
import com.yuseix.dragonminez.character.models.hair.FemHairModel;
import com.yuseix.dragonminez.character.models.hair.GohanDBSHairModel;
import com.yuseix.dragonminez.character.models.hair.GokuHairModel;
import com.yuseix.dragonminez.character.models.hair.VegetaHairModel;
import com.yuseix.dragonminez.character.models.majin.MajinFemaleModel;
import com.yuseix.dragonminez.character.models.majin.MajinGordoModel;
import com.yuseix.dragonminez.client.gui.DMZMenuTypes;
import com.yuseix.dragonminez.client.gui.KikonoArmorStationScreen;
import com.yuseix.dragonminez.client.hud.PlayerHudOverlay;
import com.yuseix.dragonminez.init.*;
import com.yuseix.dragonminez.init.armor.client.model.ArmorBaseModel;
import com.yuseix.dragonminez.init.armor.client.model.ArmorPiccoloModel;
import com.yuseix.dragonminez.init.armor.client.model.ArmorSaiyanModel;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.renderer.*;
import com.yuseix.dragonminez.init.entity.custom.*;
import com.yuseix.dragonminez.utils.Keys;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

//Anteriormente llamado ModListener o ClientEvents
//ACTUALMENTE LOS ModBusEvents son eventos que se ejecutan en el bus IModBusEvent
//Si un evento tiene "class x implements IMobBusEvent" TIENE que estar acá.
public class ModBusEvents {

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            //ENTIDADES
            EntityRenderers.register(MainEntity.DINO1.get(), DinoRenderer::new);
            EntityRenderers.register(MainEntity.NUBE_VOLADORA.get(), NubeRenderer::new);
            EntityRenderers.register(MainEntity.MASTER_KARIN.get(), KarinRenderer::new);
            EntityRenderers.register(MainEntity.SHENLONG.get(), ShenlongRenderer::new);
            EntityRenderers.register(MainEntity.MASTER_DENDE.get(), DendeRenderer::new);

            //BLOQUES
            BlockEntityRenderers.register(MainBlockEntities.DBALL1_NAMEK_BLOCK_ENTITY.get(), Dball1NamekBlockRenderer::new);
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

            //ITEMS
            ItemBlockRenderTypes.setRenderLayer(MainFluids.SOURCE_HEALING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(MainFluids.FLOWING_HEALING.get(), RenderType.translucent());

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

            //Outline de las Dragon Balls Helper
            MinecraftForge.EVENT_BUS.addListener(DballOutlineRenderer::renderOutlineDball);
        });
    }

    @SubscribeEvent
    public void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(MainEntity.DINO1.get(), DinoEntity.setAttributes());
        event.put(MainEntity.NUBE_VOLADORA.get(), NubeEntity.createAttributes());
        event.put(MainEntity.MASTER_KARIN.get(), KarinEntity.setAttributes());
        event.put(MainEntity.SHENLONG.get(), ShenlongEntity.setAttributes());
        event.put(MainEntity.MASTER_DENDE.get(), DendeEntity.setAttributes());

    }

    @SubscribeEvent
    public void registerGuiOverlays(RegisterGuiOverlaysEvent e) {
        e.registerAboveAll("playerhud", PlayerHudOverlay.HUD_PLAYER);
    }

    @SubscribeEvent
    public void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        //RAZAS
        e.registerLayerDefinition(HumanSaiyanModel.LAYER_LOCATION, HumanSaiyanModel::createBodyLayer);
        e.registerLayerDefinition(SlimHumanSaiyanModel.LAYER_LOCATION, SlimHumanSaiyanModel::createBodyLayer);
        e.registerLayerDefinition(NamekianModel.LAYER_LOCATION, NamekianModel::createBodyLayer);
        e.registerLayerDefinition(BioAndroideModelo.LAYER_LOCATION, BioAndroideModelo::createBodyLayer);
        e.registerLayerDefinition(MajinGordoModel.LAYER_LOCATION, MajinGordoModel::createBodyLayer);
        e.registerLayerDefinition(MajinFemaleModel.LAYER_LOCATION, MajinFemaleModel::createBodyLayer);
        e.registerLayerDefinition(DemonColdModel.LAYER_LOCATION, DemonColdModel::createBodyLayer);

        //CABELLOS
        e.registerLayerDefinition(GokuHairModel.LAYER_LOCATION,GokuHairModel::createBodyLayer);
        e.registerLayerDefinition(FemHairModel.LAYER_LOCATION,FemHairModel::createBodyLayer);
        e.registerLayerDefinition(VegetaHairModel.LAYER_LOCATION,VegetaHairModel::createBodyLayer);
        e.registerLayerDefinition(GohanDBSHairModel.LAYER_LOCATION,GohanDBSHairModel::createBodyLayer);

        //ARMADURAS
        e.registerLayerDefinition(ArmorBaseModel.LAYER_LOCATION, ArmorBaseModel::createBodyLayer);
        e.registerLayerDefinition(ArmorSaiyanModel.LAYER_LOCATION, ArmorSaiyanModel::createBodyLayer);
        e.registerLayerDefinition(ArmorPiccoloModel.LAYER_LOCATION, ArmorPiccoloModel::createBodyLayer);

    }


    @SubscribeEvent
    public void onKeyRegister(RegisterKeyMappingsEvent event) {
        /*
        Usa reflection para registrar todas las teclas de la clase Keys, utilicé esto para no tener que registrar cada tecla manualmente
        También porque los fields son static
         */
        try {
            Field[] fields = Keys.class.getDeclaredFields();

            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType() == KeyMapping.class) {
                    KeyMapping keyMapping = (KeyMapping) field.get(null);
                    event.register(keyMapping);
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("Error al intentar registrar una tecla! " + e.getMessage());
        }
    }

    @SubscribeEvent
    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(DMZStatsCapabilities.class);
        event.register(DragonBallGenProvider.class);
    }

}
