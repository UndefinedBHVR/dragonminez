package com.yuseix.dragonminez.init.menus.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.client.gui.buttons.DMZButton;
import com.yuseix.dragonminez.client.gui.buttons.DMZRightButton;
import com.yuseix.dragonminez.client.gui.buttons.GlowButton;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.entity.custom.KarinEntity;
import com.yuseix.dragonminez.init.menus.menutypes.KarinEntityMenuType;
import com.yuseix.dragonminez.network.C2S.KarinC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KarinMenuScreen extends AbstractContainerScreen<KarinEntityMenuType> {

	private static final ResourceLocation textoCuadro = new ResourceLocation(DragonMineZ.MOD_ID,
			"textures/gui/texto.png");

	private GlowButton kinton, senzu;
	private DMZButton AcceptButton, DeclineButton;
	private DMZRightButton rightButton, leftButton;

	private String PageOption = "";
	private int PageButtons;

	public KarinMenuScreen(KarinEntityMenuType pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	public void containerTick() {
		super.containerTick();

		paginaBotones();
		PaginaOpciones();

	}

	@Override
	protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {

	}

	@Override
	public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float delta) {
		int centerX = (this.width / 2);
		int centerY = (this.height);

		LivingEntity karinEntity = new KarinEntity(MainEntity.MASTER_KARIN.get(), this.minecraft.level);


		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, textoCuadro);


		BufferBuilder buffer = Tesselator.getInstance().getBuilder();
		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

		buffer.vertex(centerX - 140, centerY + 250, 0.0D).uv(0.0F, 1.0F).endVertex();
		buffer.vertex(centerX + 140, centerY + 250, 0.0D).uv(1.0F, 1.0F).endVertex();
		buffer.vertex(centerX + 140, centerY - 90, 0.0D).uv(1.0F, 0.0F).endVertex();
		buffer.vertex(centerX - 140, centerY - 90, 0.0D).uv(0.0F, 0.0F).endVertex();
		Tesselator.getInstance().end();

		RenderSystem.disableBlend();


		//NOMBRE DE LA ENTIDAD
		pGuiGraphics.drawString(font, Component.literal(karinEntity.getName().getString()).withStyle(ChatFormatting.BOLD), centerX - 120, centerY - 88, 0xFFFFFF);

		//TEXTO QUE DIRA LA ENTIDAD
		switch (PageOption) {
			case "" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.master_korin.menu"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "kinton" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.master_korin.nube"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
			case "senzu" -> {
				List<FormattedCharSequence> lines = font.split(Component.translatable("lines.master_korin.senzubeans"), 250);
				for (int i = 0; i < lines.size(); i++) {
					pGuiGraphics.drawString(font, lines.get(i), (centerX - 120), (centerY - 73) + i * font.lineHeight, 0xFFFFFF);
				}
			}
		}
		super.render(pGuiGraphics, pMouseX, pMouseY, delta);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private void removerBotones() {
		removeWidget(this.kinton);
		removeWidget(this.senzu);
		removeWidget(this.AcceptButton);
		removeWidget(this.DeclineButton);
		removeWidget(this.rightButton);
		removeWidget(this.leftButton);
	}

	private void paginaBotones() {
		if (PageButtons == 0) {
			removerBotones();
			DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, Minecraft.getInstance().player).ifPresent(cap -> {

				if (cap.getDmzSenzuDaily() == 0) { //Si el Tiempo de espera de la nube voladora es 0
					//Dar Senzu
					this.senzu = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23), Component.translatable("lines.master_korin.senzu"), wa -> {
						//ModMessages.sendToServer(new KarinC2S(2));
						//this.minecraft.setScreen(null);
						PageOption = "senzu";
					}));
				}
			});

			//Dar nube voladora
			this.kinton = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) + 5, (this.height - 23), Component.translatable("lines.master_korin.kinton"), wa -> {
				//ModMessages.sendToServer(new KarinC2S(1));
				//this.minecraft.setScreen(null);
				PageOption = "kinton";
			}));

            /*
            this.rightButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("right",(this.width / 2) + 120, (this.height - 22), Component.empty(), wa -> {
                PageButtons = 1;

            }));

             */

		} else if (PageButtons == 1) {
			removerBotones();
			//Dar Senzu
			this.senzu = (GlowButton) this.addRenderableWidget(new GlowButton((this.width / 2) - 105, (this.height - 23), Component.translatable("lines.master_korin.senzu"), wa -> {
				//ModMessages.sendToServer(new KarinC2S(2));
				//this.minecraft.setScreen(null);
				PageOption = "no";

			}));
			//Aca deberian estar los otros botones :v
			this.leftButton = (DMZRightButton) this.addRenderableWidget(new DMZRightButton("left", (this.width / 2) - 120, (this.height - 22), Component.empty(), wa -> {
				PageButtons = 0;
			}));
		}
	}

	public void PaginaOpciones() {
		switch (PageOption) {
			case "kinton":
				//Aceptar
				this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47), Component.translatable("lines.menu.accept"), wa -> {
					ModMessages.sendToServer(new KarinC2S(1));

					this.minecraft.setScreen(null);

				}));
				//Rechazar
				this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47), Component.translatable("lines.menu.decline"), wa -> {
					this.minecraft.setScreen(null);
				}));
				break;

			case "senzu":
				//Aceptar
				this.AcceptButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) - 5, (this.height - 47), Component.translatable("lines.menu.accept"), wa -> {

					ModMessages.sendToServer(new KarinC2S(2)); //Recibir senzus
					ModMessages.sendToServer(new KarinC2S(3)); //Poner tiempo de espera en los datos de jugador

					this.minecraft.setScreen(null);

				}));
				//Rechazar
				this.DeclineButton = (DMZButton) this.addRenderableWidget(new DMZButton((this.width / 2) + 60, (this.height - 47), Component.translatable("lines.menu.decline"), wa -> {
					this.minecraft.setScreen(null);
				}));
				break;

			default:
				break;
		}
	}
}
