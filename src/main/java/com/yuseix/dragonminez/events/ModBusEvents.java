package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.HumanSaiyanModel;
import com.yuseix.dragonminez.character.models.SlimHumanSaiyanModel;
import com.yuseix.dragonminez.character.models.demoncold.DemonColdModel;
import com.yuseix.dragonminez.client.hud.PlayerHudOverlay;
import com.yuseix.dragonminez.client.hud.spaceship.SaiyanSpacePodOverlay;
import com.yuseix.dragonminez.init.*;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.renderer.*;
import com.yuseix.dragonminez.init.entity.client.renderer.fpcharacters.*;
import com.yuseix.dragonminez.init.entity.client.renderer.namek.*;
import com.yuseix.dragonminez.init.entity.client.renderer.projectil.KiSmallBallRenderer;
import com.yuseix.dragonminez.init.entity.custom.*;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.*;
import com.yuseix.dragonminez.init.entity.custom.namek.*;
import com.yuseix.dragonminez.init.menus.screens.KarinMenuScreen;
import com.yuseix.dragonminez.init.menus.screens.KikonoArmorStationScreen;
import com.yuseix.dragonminez.init.particles.AjissaLeavesParticle;
import com.yuseix.dragonminez.init.particles.SacredLeavesParticle;
import com.yuseix.dragonminez.utils.Keys;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import com.yuseix.dragonminez.world.NamekDragonBallGenProvider;
import com.yuseix.dragonminez.world.StructuresCapability;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

	@SubscribeEvent
	public void entityAttributeEvent(EntityAttributeCreationEvent event) {
		event.put(MainEntity.DINO1.get(), DinoEntity.setAttributes());
		event.put(MainEntity.NUBE_VOLADORA.get(), NubeEntity.createAttributes());
		event.put(MainEntity.MASTER_KARIN.get(), KarinEntity.setAttributes());
		event.put(MainEntity.SHENLONG.get(), ShenlongEntity.setAttributes());
		event.put(MainEntity.PORUNGA.get(), PorungaEntity.setAttributes());
		event.put(MainEntity.MASTER_DENDE.get(), DendeEntity.setAttributes());

		event.put(MainEntity.NAMEK_FROG.get(), NamekFrogEntity.setAttributes());
		event.put(MainEntity.PINK_FROG.get(), PinkFrogEntity.setAttributes());
		event.put(MainEntity.YELLOW_FROG.get(), YellowFrogEntity.setAttributes());
		event.put(MainEntity.GINYU_FROG.get(), GinyuFrogEntity.setAttributes());

		event.put(MainEntity.NAMEKNPC_WARRIOR1.get(), NamekWarriorEntity.setAttributes());
		event.put(MainEntity.NAMEKNPC_WARRIOR2.get(), NamekWarrior02Entity.setAttributes());
		event.put(MainEntity.NAMEKNPC_TRADER1.get(), NamekTraderEntity.setAttributes());
		event.put(MainEntity.NAMEKNPC_TRADER2.get(), NamekTrader02Entity.setAttributes());
		event.put(MainEntity.NAMEKNPC_TRADER3.get(), NamekTrader03Entity.setAttributes());

		event.put(MainEntity.FRIEZA_SOLDIER01.get(), FriezaSoldierEntity.setAttributes());
		event.put(MainEntity.MORO_SOLDIER.get(), MoroSoldierEntity.setAttributes());
		event.put(MainEntity.NAVE_SAIYAN.get(), NaveSaiyanEntity.setAttributes());

		//FAKEPLAYERS
		event.put(MainEntity.FP_BIOANDROIDE.get(), FPBioAndroidEntity.setAttributes());
		event.put(MainEntity.FP_DEMONCOLD.get(), FPDemonColdEntity.setAttributes());
		event.put(MainEntity.FP_HUMANSAIYAN.get(), FPHumanSaiyanEntity.setAttributes());
		event.put(MainEntity.FP_SLIMSAIYANHUM.get(), FPSlimEntity.setAttributes());
		event.put(MainEntity.FP_NAMEK.get(), FPNamekianEntity.setAttributes());
		event.put(MainEntity.FP_MAJINGORDO.get(), FPMajinGordEntity.setAttributes());
		event.put(MainEntity.AURA.get(), AuraEntity.setAttributes());

	}

	@SubscribeEvent
	public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(DragonBallGenProvider.class);
		event.register(NamekDragonBallGenProvider.class);
		event.register(StructuresCapability.class);
	}

}
