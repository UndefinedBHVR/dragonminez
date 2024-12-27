package com.yuseix.dragonminez.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.AuraModel;
import com.yuseix.dragonminez.init.MainParticles;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.TextureManager;
import com.yuseix.dragonminez.utils.shaders.CustomRenderTypes;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
	private static final String MOD_VERSION = System.getProperty("mod_version", "unknown");

	private static final Random RANDOM = new Random();
	private static final String title = "DragonMine Z - Release v" + MOD_VERSION;

	private static final AuraModel AURA_MODEL = new AuraModel(AuraModel.createBodyLayer().bakeRoot());

	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {
		if (event.phase == TickEvent.RenderTickEvent.Phase.END) {
			Minecraft minecraft = Minecraft.getInstance();
			if (minecraft.getWindow() != null) {
				// Cambia el título de la ventana
				minecraft.getWindow().setTitle(title);
			}
		}
	}

	@SubscribeEvent
	public static void onRenderLevelLast(RenderLevelStageEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		if (!event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) return;

		for (Player player : minecraft.level.players()) {
			if (player != null) {
				DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
					if (cap.isAuraOn() || cap.isTurbonOn()) {
						boolean isLocalPlayer = player == minecraft.player;
						float transparency = isLocalPlayer && minecraft.options.getCameraType().isFirstPerson() ? 0.075f : 0.325f;

						RenderSystem.disableDepthTest();
						renderAuraBase(
								(AbstractClientPlayer) player,
								event.getPoseStack(),
								minecraft.renderBuffers().bufferSource(),
								15728880,
								event.getPartialTick(),
								transparency,
								cap.getAuraColor()
						);
						RenderSystem.enableDepthTest();
					}
				});
			}
		}
	}

	private static void renderAuraBase(AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource buffer, int packedLight, float partialTicks, float transparencia, int colorAura) {
		Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();

		// Obtener posición de la cámara
		double camX = camera.getPosition().x;
		double camY = camera.getPosition().y;
		double camZ = camera.getPosition().z;

		// Descomponer el color en sus componentes RGBA
		float red = (colorAura >> 16 & 255) / 255.0f;
		float green = (colorAura >> 8 & 255) / 255.0f;
		float blue = (colorAura & 255) / 255.0f;

		// Obtener posición interpolada del jugador
		double interpX = Mth.lerp(partialTicks, player.xOld, player.getX());
		double interpY = Mth.lerp(partialTicks, player.yOld, player.getY());
		double interpZ = Mth.lerp(partialTicks, player.zOld, player.getZ());

		//ACA YA FUNCIONA
		poseStack.pushPose();

		//Ajustar posición del aura en el jugador
		poseStack.translate(interpX - camX, interpY - camY + player.getEyeHeight(), interpZ - camZ);

		poseStack.mulPose(Axis.XP.rotationDegrees(180f));

		float rotationAngle = 0.0F;
		rotationAngle = (player.tickCount + partialTicks) * 5.0F; // Ajusta la velocidad aquí

		float rotationAngle2 = 0.0F;
		rotationAngle2 = (player.tickCount + partialTicks) * -7.0F; // Ajusta la velocidad aquí

		VertexConsumer vertexConsumer = buffer.getBuffer(CustomRenderTypes.energy(TextureManager.AURA_BASE));


		// PARTE BAJA 1
		for (int i = 0; i < 8; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.7F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(40));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.0D, -0.7D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}

		//PARTE BAJA 2
		for (int i = 0; i < 8; i++) {
			poseStack.pushPose();
			poseStack.scale(1.4F, 1.9F, 1.4F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(40));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.0D, -0.5D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//PARTE MEDIO 1 interior
		for (int i = 0; i < 10; i++) {
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.7F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(0));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -0.6D, -0.2D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//parte medio 2 exterior
		for (int i = 0; i < 10; i++) {
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.7F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(15f));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.0D, -0.4D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//parte medio 3 exterior
		for (int i = 0; i < 10; i++) {
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.9F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(15f));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.0D, -0.6D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//PARTE ARRIBA 1 interior
		for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.6F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(-35F));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.1D, -0.38D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//Parte 2 arriba exterior
		for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.6F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(25F));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -0.8D, -0.4D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//Parte 3 arriba exterior
		for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.6F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle2 + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(-15F));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.2D, -0.4D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}
		//PARTE ARRIBA 4 interior
		for (int i = 0; i < 10; i++) {  // Ajusta el número de planos
			poseStack.pushPose();
			poseStack.scale(1.2F, 1.6F, 1.2F);

			// Rotar cada plano un poco más en Y y X
			poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle + i * 45F));  // Cambia 30F por el ángulo que desees
			poseStack.mulPose(Axis.XP.rotationDegrees(5F));

			// Posicionar el aura un poco más arriba o abajo
			poseStack.translate(0.0D, -1.5D, -0.38D);

			// Renderizar cada plano
			AURA_MODEL.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, transparencia);

			poseStack.popPose();
		}

		poseStack.popPose();
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		Minecraft mc = Minecraft.getInstance();
		Level level = mc.level;

		if (level == null || mc.player == null || mc.isPaused()) {
			return;
		}

		// Frecuencia de generación (una vez cada 10 ticks)
		if (level.getGameTime() % 10 != 0) {
			return;
		}

		// Obtener posición y bioma
		BlockPos playerPos = mc.player.blockPosition();
		ResourceKey<Biome> currentBiomeKey = level.registryAccess()
				.registryOrThrow(Registries.BIOME)
				.getResourceKey(level.getBiome(playerPos).value())
				.orElse(null);

		if (currentBiomeKey == null) return;

		// Genera partículas dependiendo del bioma
		if (currentBiomeKey.equals(ModBiomes.AJISSA_PLAINS)) {
			spawnParticles(level, MainParticles.AJISSA_LEAVES_PARTICLE.get(), playerPos);
		} else if (currentBiomeKey.equals(ModBiomes.SACRED_LAND)) {
			spawnParticles(level, MainParticles.SACRED_LEAVES_PARTICLE.get(), playerPos);
		}
	}

	private static void spawnParticles(Level level, SimpleParticleType particleType, BlockPos playerPos) {
		// Generar partículas alrededor del jugador
		int cantParticulas = 6;
		for (int i = 0; i < cantParticulas; i++) { // Cantidad
			double x = playerPos.getX() + RANDOM.nextDouble() * 16 - 8; // Rango: -8 a +8 bloques
			double y = playerPos.getY() + RANDOM.nextDouble() * 6;      // Rango: hasta 6 bloques arriba
			double z = playerPos.getZ() + RANDOM.nextDouble() * 16 - 8; // Rango: -8 a +8 bloques

			double xSpeed = (RANDOM.nextDouble() - 0.5) * 0.02; // Velocidad lateral mínima
			double ySpeed = RANDOM.nextDouble() * 0.01;         // Velocidad vertical lenta
			double zSpeed = (RANDOM.nextDouble() - 0.5) * 0.02; // Velocidad lateral mínima

			level.addParticle(particleType, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}
