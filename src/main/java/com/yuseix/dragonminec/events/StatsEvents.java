package com.yuseix.dragonminec.events;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.init.MainSounds;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = DragonMineC.MODID)
public class StatsEvents {

    private static int tickcounter = 0;
    private static int energiacounter = 0;

    private static int getEnergyRemovalThreshold(int playerLevel) {
        // Asumiendo que cada nivel te da 0.1 bloques de altura de soporte
        return (int) (0.1 * (playerLevel - 100) + 10);
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {


        //Regenerar stamina
        if (event.side == LogicalSide.SERVER) {

            energiacounter++;
            tickcounter++;

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.player).ifPresent(playerstats -> {

                int maxcon = (int) (playerstats.getConstitution() * 0.5) * DMCAttrConfig.MULTIPLIER_CON.get();
                int maxstamina = (playerstats.getStamina() + 3);
                int maxenergia = (int) (playerstats.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get();


                if (playerstats.getCurStam() >= 0 && playerstats.getCurStam() <= maxstamina) {

                    if (tickcounter >= 60 * 3) {

                        int regenStamina = ((maxstamina) / 4);

                        playerstats.addCurStam(regenStamina);

                        tickcounter = 0;

                    }

                }
                if (playerstats.getCurrentEnergy() >= 0 && playerstats.getCurrentEnergy() <= maxenergia) {
                    if (energiacounter >= 60 * 5) {

                        int regenki = ((maxenergia) / 10);

                        playerstats.addCurEnergy(regenki);

                        energiacounter = 0;
                    }
                }

                event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxcon);

            });

        }


    }

    @SubscribeEvent
    public static void Recibirdano(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player)) {  //LA ENTIDAD QUE RECIBE EL GOLPE NO ES UN JUGADOR
            if (event.getSource().getEntity() instanceof Player jugadorpemrd) { //SI EL QUE HACE DANO ES UN JUGADOR

                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getSource().getEntity()).ifPresent(playerstats -> {

                    int maxstr = (int) (playerstats.getStrength() * 0.5) * DMCAttrConfig.MULTIPLIER_STR.get();

                    int staminacost = (maxstr / 4);

                    int curstamina = playerstats.getCurStam();

                    if (curstamina >= staminacost) {
                        event.setAmount(maxstr);
                        playerstats.removeCurStam(staminacost);
                    }
                    if (staminacost >= curstamina) {
                        event.setAmount(1);
                    }
                });

                sonidosGolpes(jugadorpemrd);
            }
        }

        if (event.getEntity() instanceof Player) { //SI LA ENTIDAD QUE RECIBE UN GOLPE ES UN JUGADOR

            if (!(event.getSource().getEntity() instanceof Player)) { //SI LA ENTIDAD QUE HACE DANO NO ES UN JUGADOR

                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getEntity()).ifPresent(playerstats ->
                        event.setAmount(event.getAmount() - (int) (((playerstats.getDefense() * 0.5) * DMCAttrConfig.MULTIPLIER_DEF.get()) / 2)));

            }

            if ((event.getSource().getEntity() instanceof Player jugadorpemrd)) { //SI LA ENTIDAD QUE HACE DANO ES UN JUGADOR

                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getEntity()).ifPresent(playerstats -> {

                    int maxstr = (int) (playerstats.getStrength() * 0.5) * DMCAttrConfig.MULTIPLIER_STR.get();
                    int maxdef = (int) (playerstats.getDefense() * 0.5) * DMCAttrConfig.MULTIPLIER_DEF.get();

                    int staminacost = (maxstr / 4);
                    int curstamina = playerstats.getCurStam();

                    if (curstamina >= staminacost) {

                        event.setAmount(event.getAmount() - ((float) maxdef / 3));
                        playerstats.removeCurStam(staminacost);

                    }
                    if (staminacost >= curstamina) {
                        event.setAmount(1);
                    }

                });

                sonidosGolpes(jugadorpemrd);
            }
        }
    }

    @SubscribeEvent
    public static void livingFallEvent(LivingFallEvent event) {
        float realDistance = event.getDistance();

        if (event.getEntity() instanceof ServerPlayer player) {
            if (realDistance > 4.5f) {
                System.out.println(realDistance);
                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(stats -> {
                    int level = (stats.getStrength() +
                            stats.getDefense() +
                            stats.getConstitution() +
                            stats.getKiPower() +
                            stats.getEnergy()) / 5;

                    double energyToRemove = getEnergyToRemove(level);

                    System.out.println(level);
                    System.out.println(energyToRemove);
                    System.out.println(getEnergyRemovalThreshold(level));

                    // Checar si la distancia de caída es menor al soporte que puedes tener por x nivel
                    if ((int) realDistance <= getEnergyRemovalThreshold(level) /* && stats.getCurrentEnergy() >= energyToRemove */) {
                        stats.removeCurEnergy((int) Math.round(energyToRemove));
                        event.setCanceled(true);
                    } //else hacer algo si te haces más daño de ki del que puedes soportar
                });
            }
        }

    }

    private static double getEnergyToRemove(int level) {
        double energyRemovalPercentage;

        double baseReduction = 0.0003;

        if (level >= 100) {
            // Porcentaje calculado normalmente mayor al 3% base del nivel 100
            energyRemovalPercentage = level * baseReduction;

        } else {
            energyRemovalPercentage = 0; // Niveles menores a 100 no reciben daño de ki
        }

        // Devuelve el valor actual de energía sacada
        return level * energyRemovalPercentage;
    }


    private static void sonidosGolpes(Player player) {

        SoundEvent[] golpeSounds = {
                MainSounds.GOLPE1.get(),
                MainSounds.GOLPE2.get(),
                MainSounds.GOLPE3.get(),
                MainSounds.GOLPE4.get(),
                MainSounds.GOLPE5.get(),
                MainSounds.GOLPE6.get()
        };

        Random rand = new Random();
        int randomIndex = rand.nextInt(golpeSounds.length);
        SoundEvent randomGolpeSound = golpeSounds[randomIndex];

        player.level().playSound(null, player.getOnPos(), randomGolpeSound, SoundSource.PLAYERS, 2.2F, 0.9F);

    }

}



