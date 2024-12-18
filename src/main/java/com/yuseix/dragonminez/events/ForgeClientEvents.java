package com.yuseix.dragonminez.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.AuraModel;
import com.yuseix.dragonminez.network.C2S.MenuC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.Keys;
import com.yuseix.dragonminez.utils.TextureManager;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {


	@SubscribeEvent
	public static void onKeyInput(InputEvent.Key event) {
		if (Keys.STATS_MENU.consumeClick()) {
			ModMessages.sendToServer(new MenuC2S());
		}
	}

	@SubscribeEvent
	public static void RenderHealthBar(RenderGuiOverlayEvent.Pre event) {
		if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
			event.setCanceled(true);
		}
	}




}
