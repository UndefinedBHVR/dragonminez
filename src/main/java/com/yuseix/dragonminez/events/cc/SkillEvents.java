package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.thread.EffectiveSide;

import java.awt.event.KeyEvent;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SkillEvents {
    private static boolean isFlying = false;

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) { //Metodo que sirve solo para la habilidad jump
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
            DMZSkill jumpSkill = cap.getDMZSkills().get("jump");

            if (jumpSkill != null && jumpSkill.isActive()) {
                int jumpLevel = jumpSkill.getLevel(); // Nivel actual de la habilidad
                if (jumpLevel > 0) {
                    float jumpBoost = 0.1f * jumpLevel; // Cada nivel aumenta el salto en 0.5 bloques
                    player.setDeltaMovement(player.getDeltaMovement().add(0, jumpBoost, 0));
                }
            }
        });
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) { // Detectar la tecla de vuelo
        if (EffectiveSide.get().isClient()) {
            Minecraft mc = Minecraft.getInstance();
            if (Keys.FLY_KEY.consumeClick()) {
                Player player = mc.player;
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                    DMZSkill flySkill = cap.getDMZSkills().get("fly");

                    if (flySkill != null && flySkill.isActive()) {
                        int flyLevel = flySkill.getLevel();
                        if (flyLevel > 0) {
                            isFlying = !isFlying; // Alternar el estado de vuelo
                            if (isFlying) {
                                if (player.onGround()) {
                                    player.jumpFromGround(); // Salto inicial
                                }
                                player.setNoGravity(true);
                            } else player.setNoGravity(false);
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) { // Lógica de vuelo durante cada tick
        Player player = event.player;

        if (player.level().isClientSide) return; // Solo ejecutar en el servidor

        if (isFlying) {
            if (player.onGround() || !player.getFeetBlockState().isAir()) { // Desactivar vuelo si el jugador toca el suelo
                isFlying = false;
                player.setNoGravity(false);
                return;
            }

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                DMZSkill flySkill = cap.getDMZSkills().get("fly");

                if (flySkill != null && flySkill.isActive()) {
                    int flyLevel = flySkill.getLevel();

                    // Velocidad base del jugador aumentada un 10% por nivel de vuelo
                    double baseSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
                    double speedMultiplier = baseSpeed * (1.0 + (0.1 * flyLevel));

                    // Movimiento en X y Z
                    double forward = player.zza; // Movimiento hacia adelante/atrás
                    double strafe = player.xxa;  // Movimiento lateral
                    double motionX = strafe * speedMultiplier;
                    double motionZ = forward * speedMultiplier;

                    // Movimiento en Y controlado
                    double motionY = -0.03f; // No ascender ni descender por defecto

                    if (Minecraft.getInstance().options.keyJump.isDown()) {
                        motionY = 0.3f;
                    } else if (player.isCrouching()) {
                        motionY = -0.3f;
                    }

                    player.setDeltaMovement(motionX, motionY, motionZ);


                    // Aplicar movimiento
                    player.setDeltaMovement(motionX, motionY, motionZ);
                }
            });
        }
    }
}
