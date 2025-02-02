package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.stats.skills.DMZSkill;
import com.yuseix.dragonminez.utils.DMZDatos;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.thread.EffectiveSide;

import java.awt.event.KeyEvent;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SkillEvents {
    private static boolean isFlying = false; private static boolean isDescending = false;

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
    public static void onKeyPress(InputEvent.Key event) { // Detectar vuelo
        if (EffectiveSide.get().isClient()) {
            Minecraft mc = Minecraft.getInstance();
            if (Keys.FLY_KEY.consumeClick()) {
                LocalPlayer player = mc.player;
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                    DMZSkill flySkill = cap.getDMZSkills().get("fly");
                    DMZSkill jumpSkill = cap.getDMZSkills().get("jump");

                    if (flySkill != null && flySkill.isActive()) {
                        int flyLevel = flySkill.getLevel();
                        if (flyLevel > 0) {
                            if (!isFlying) {
                                player.getAbilities().mayfly = true;
                                if (player.onGround()) {

                                    player.jumpFromGround();
                                    if (jumpSkill != null && jumpSkill.isActive()) { // Si tiene Jump, hace el salto potenciado
                                        int jumpLevel = jumpSkill.getLevel();
                                        if (jumpLevel > 0) {
                                            float jumpBoost = 0.1f * jumpLevel;
                                            player.setDeltaMovement(player.getDeltaMovement().add(0, jumpBoost, 0));
                                        }
                                    } else player.setDeltaMovement(player.getDeltaMovement().x, 0.42D, player.getDeltaMovement().z); // Salto normal si no tiene Jump
                                    isDescending = true; // Esperar a que empiece a caer
                                }
                            } else {
                                isFlying = false;
                                player.getAbilities().mayfly = false;
                                player.getAbilities().flying = false;
                                player.fallDistance = 0; // Anular daño de caída al desactivar el vuelo
                            }
                            player.onUpdateAbilities();
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;

        if (event.player.level().isClientSide) return;

        if (isDescending && player.getDeltaMovement().y < 0) { // Si está cayendo después del salto
            isFlying = true;
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = true;
            player.onUpdateAbilities();
            isDescending = false;
        }

        if (isFlying) {
            if (player.onGround() || !player.getFeetBlockState().isAir()) { // Desactivar vuelo si toca el suelo
                isFlying = false;
                player.getAbilities().flying = false;
                player.fallDistance = 0; // Resetear daño de caída
                player.onUpdateAbilities();
                return;
            }

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                DMZSkill flySkill = cap.getDMZSkills().get("fly");

                if (flySkill != null && flySkill.isActive()) {
                    int flyLevel = flySkill.getLevel();

                    // La vel de vuelo aumenta un 20% por nivel
                    float baseSpeed = 0.05F;
                    float flySpeed = baseSpeed * (1.0F + (0.20F * flyLevel));
                    player.getAbilities().setFlyingSpeed(flySpeed);

                    Vec3 motion = player.getDeltaMovement();
                    double yVelocity = motion.y;

                    // Si mantiene espacio, ascender
                    if (player.input.jumping) {
                        yVelocity = 0.2;
                    }
                    // Si mantiene shift, descender más rápido
                    else if (player.input.shiftKeyDown) {
                        yVelocity = -0.2;
                    }
                    // Si no presiona nada, descenso lento
                    else {
                        yVelocity = -0.02;
                    }

                    player.setDeltaMovement(motion.x, yVelocity, motion.z);
                    player.onUpdateAbilities();
                }
            });
        }
    }
}
