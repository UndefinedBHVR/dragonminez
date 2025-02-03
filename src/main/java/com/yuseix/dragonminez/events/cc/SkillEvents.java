package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.C2S.FlyToggleC2S;
import com.yuseix.dragonminez.network.ModMessages;
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
}
