package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.SoldierEntity;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class EntityEvents {

    @SubscribeEvent
    public static void mobDeath(LivingDeathEvent event) {
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
                    cap.addDmzAlignment(2); //Agregar puntos te hace bueno
                });

            }
        }

        //Pa ganar tps cuando mates algo claro pes papeto
        if(event.getEntity() instanceof Monster || event.getEntity() instanceof Animal || event.getEntity() instanceof Player
                || event.getEntity() instanceof NamekianEntity){
            if (event.getSource().getEntity() instanceof Player) {
                Player player = (Player) event.getSource().getEntity();
                var vidaTps = (int) (event.getEntity().getMaxHealth() * 0.5); // 50% hp enemigo
                var tps = (int) Math.round((10 + vidaTps) * DMZGeneralConfig.MULTIPLIER_ZPOINTS_GAIN.get()); // (10 + 50% hp) * config

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                    cap.addZpoints(tps);
                    //player.displayClientMessage(Component.literal("TPS: " + tps), true); // testing
                });
            }
        }
    }

    @SubscribeEvent
    public static void onEntityHit(LivingHurtEvent event) {

        if (event.getSource().getEntity() instanceof Player) {
            Player player = (Player) event.getSource().getEntity();

            double baseTps = DMZGeneralConfig.PERHIT_ZPOINTS_GAIN.get() * DMZGeneralConfig.MULTIPLIER_ZPOINTS_GAIN.get();

            // multiplicar si está en la hab del tiempo pes
            if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
                baseTps *= DMZGeneralConfig.MULTIPLIER_ZPOINTS_HTC.get();
            }

            int finalTps = (int) Math.round(baseTps);

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                cap.addZpoints(finalTps);

               /* Testing
               if (player.level().dimension().equals(ModDimensions.TIME_CHAMBER_DIM_LEVEL_KEY)) {
                    player.sendSystemMessage(Component.literal("TPS: " + finalTps + " (HTC)")); }
                    else {player.sendSystemMessage(Component.literal("TPS: " + finalTps)); } */
            });
        }
    }
}



