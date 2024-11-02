package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.SoldierEntity;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class EntityEvents {

    @SubscribeEvent
    public static void onNamekWarriorDeath(LivingDeathEvent event) {
        // Verificar si la entidad muerta es un aldeano, namekianentity,etc
        if (event.getEntity() instanceof NamekianEntity ||
                event.getEntity() instanceof Villager ||
                event.getEntity() instanceof Player) {

            // Verificar si el atacante es un jugador
            if (event.getSource().getEntity() instanceof Player) {
                Player player = (Player) event.getSource().getEntity();


                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                    cap.removeDmzAlignment(5); //Remover puntos te hace maligno
                });

                player.displayClientMessage(Component.translatable("lines.alignment.evil"), true);

            }
        }
        //Aca es para ganar puntos de bondad xxx claro pe tilin
        if(event.getEntity() instanceof SoldierEntity || event.getEntity() instanceof Monster){

            if(event.getSource().getEntity() instanceof Player){
                Player player = (Player) event.getSource().getEntity();

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                    cap.addDmzAlignment(5); //Remover puntos te hace maligno
                });

            }
        }

    }

}



