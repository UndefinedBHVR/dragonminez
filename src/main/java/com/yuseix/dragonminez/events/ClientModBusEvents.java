package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.AuraModel;
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
import com.yuseix.dragonminez.client.hud.spaceship.SaiyanSpacePodOverlay;
import com.yuseix.dragonminez.init.MainBlockEntities;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.MainParticles;
import com.yuseix.dragonminez.init.armor.client.model.ArmorBaseModel;
import com.yuseix.dragonminez.init.armor.client.model.ArmorPiccoloModel;
import com.yuseix.dragonminez.init.armor.client.model.ArmorSaiyanModel;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.model.namek.NamekNPCModel;
import com.yuseix.dragonminez.init.entity.client.model.projectil.KiBallModel;
import com.yuseix.dragonminez.init.entity.client.renderer.*;
import com.yuseix.dragonminez.init.entity.client.renderer.fpcharacters.*;
import com.yuseix.dragonminez.init.entity.client.renderer.namek.*;
import com.yuseix.dragonminez.init.entity.client.renderer.projectil.KiSmallBallRenderer;
import com.yuseix.dragonminez.init.items.models.BaculoEmptyModel;
import com.yuseix.dragonminez.init.particles.AjissaLeavesParticle;
import com.yuseix.dragonminez.init.particles.SacredLeavesParticle;
import com.yuseix.dragonminez.network.C2S.MenuC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

//Anteriormente llamado ModListener o ClientEvents
//ACTUALMENTE LOS ModBusEvents son eventos que se ejecutan en el bus IModBusEvent
//Si un evento tiene "class x implements IMobBusEvent" TIENE que estar acá.
//Adicionalmente, estos eventos son aquellos que son lanzados al inicio del juego.

public class ClientModBusEvents {

	@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = Dist.CLIENT)
	public static class ClientForgeEvents {

		@SubscribeEvent
		public static void onKeyInput(InputEvent.Key event) {
			if (Keys.STATS_MENU.consumeClick()) {
				ModMessages.sendToServer(new MenuC2S());
			}
		}



	}

	@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ClientModBusEvents2 {

		@SubscribeEvent
		public static void RenderHealthBar(RenderGuiOverlayEvent.Pre event) {
			if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
				event.setCanceled(true);
			}
		}

		@SubscribeEvent
		public static void registerGuiOverlays(RegisterGuiOverlaysEvent e) {
			//e.registerAboveAll("playerhud", PlayerHudOverlay.HUD_PLAYER);
			e.registerAboveAll("spaceshiphud", SaiyanSpacePodOverlay.HUD_SAIYAN);
		}


		@SubscribeEvent
		public static void registerParticles(RegisterParticleProvidersEvent event) {
			event.registerSpriteSet(MainParticles.AJISSA_LEAVES_PARTICLE.get(), AjissaLeavesParticle.Provider::new);
			event.registerSpriteSet(MainParticles.SACRED_LEAVES_PARTICLE.get(), SacredLeavesParticle.Provider::new);
		}

		@SubscribeEvent
		public static void onKeyRegister(RegisterKeyMappingsEvent event) {
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
				//System.out.println("Error al intentar registrar una tecla! " + e.getMessage());
			}
		}

		@SubscribeEvent
		public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
			//RAZAS
			e.registerLayerDefinition(HumanSaiyanModel.LAYER_LOCATION, HumanSaiyanModel::createBodyLayer);
			e.registerLayerDefinition(SlimHumanSaiyanModel.LAYER_LOCATION, SlimHumanSaiyanModel::createBodyLayer);
			e.registerLayerDefinition(NamekianModel.LAYER_LOCATION, NamekianModel::createBodyLayer);
			e.registerLayerDefinition(BioAndroideModelo.LAYER_LOCATION, BioAndroideModelo::createBodyLayer);
			e.registerLayerDefinition(MajinGordoModel.LAYER_LOCATION, MajinGordoModel::createBodyLayer);
			e.registerLayerDefinition(MajinFemaleModel.LAYER_LOCATION, MajinFemaleModel::createBodyLayer);
			e.registerLayerDefinition(DemonColdModel.LAYER_LOCATION, DemonColdModel::createBodyLayer);

			e.registerLayerDefinition(AuraModel.LAYER_LOCATION, AuraModel::createBodyLayer);
			e.registerLayerDefinition(KiBallModel.LAYER_LOCATION, KiBallModel::createBodyLayer);

			//CABELLOS
			e.registerLayerDefinition(GokuHairModel.LAYER_LOCATION, GokuHairModel::createBodyLayer);
			e.registerLayerDefinition(FemHairModel.LAYER_LOCATION, FemHairModel::createBodyLayer);
			e.registerLayerDefinition(VegetaHairModel.LAYER_LOCATION, VegetaHairModel::createBodyLayer);
			e.registerLayerDefinition(GohanDBSHairModel.LAYER_LOCATION, GohanDBSHairModel::createBodyLayer);

			//ARMADURAS
			e.registerLayerDefinition(ArmorBaseModel.LAYER_LOCATION, ArmorBaseModel::createBodyLayer);
			e.registerLayerDefinition(ArmorSaiyanModel.LAYER_LOCATION, ArmorSaiyanModel::createBodyLayer);
			e.registerLayerDefinition(ArmorPiccoloModel.LAYER_LOCATION, ArmorPiccoloModel::createBodyLayer);

			//ENTIDADES CUSTOM EN BASE A MODELOS DE JAVA
			e.registerLayerDefinition(NamekNPCModel.LAYER_LOCATION, NamekNPCModel::createBodyLayer);

			//Armas en espalda
			e.registerLayerDefinition(BaculoEmptyModel.LAYER_LOCATION, BaculoEmptyModel::createBodyLayer);
		}


	}









}
