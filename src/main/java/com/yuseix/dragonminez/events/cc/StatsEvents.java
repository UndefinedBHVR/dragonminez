package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.init.MainDimensions;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class StatsEvents {

    private static int tickcounter = 0;
    private static int energiacounter = 0;

    private static int getEnergyRemovalThreshold(int playerLevel) {
        // Asumiendo que cada nivel te da 0.1 bloques de altura de soporte
        return (int) (0.1 * (playerLevel - 100) + 10);
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        // Regenerar stamina
        if (event.side == LogicalSide.SERVER) {
            energiacounter++;
            tickcounter++;

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, event.player).ifPresent(playerstats -> {
                var vidaMC = 20;
                var con = playerstats.getConstitution();
                var raza = playerstats.getRace();
                var energia = playerstats.getEnergy();
                var statStr = playerstats.getStrength(); // Obtener fuerza del jugador

                int maxenergia = DMZDatos.calcularENE(raza, energia);
                int maxstamina = DMZDatos.calcularSTM(raza, DMZDatos.calcularCON(raza, con, vidaMC));

                // Ajustar la salud máxima del jugador
                event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON.get()));

                // Regeneración de stamina
                if (playerstats.getCurStam() >= 0 && playerstats.getCurStam() <= maxstamina) {
                    if (tickcounter >= 60 * 3) { // Cada 3 segundos
                        int regenStamina = (maxstamina / 4);
                        playerstats.addCurStam(regenStamina);
                        tickcounter = 0;
                    }
                }

                // Regeneración de energía
                if (playerstats.getCurrentEnergy() >= 0 && playerstats.getCurrentEnergy() <= maxenergia) {
                    if (energiacounter >= 60 * 5) { // Cada 5 segundos
                        int regenki = (maxenergia / 10); // Regenerar 10% de la energía máxima
                        playerstats.addCurEnergy(regenki);
                        energiacounter = 0;
                    }
                }
            });
        }
    }



    @SubscribeEvent
    public static void Recibirdano(LivingHurtEvent event) {
        // Si el que hace el daño es un jugador
        if (event.getSource().getEntity() instanceof Player atacante) {
            // Obtener las estadísticas del atacante
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, atacante).ifPresent(estatsAtacante -> {
                int raza = estatsAtacante.getRace();
                int curStamina = estatsAtacante.getCurStam();
                float danoDefault = event.getAmount(); // Capturamos el daño original

                // Calcular el daño basado en la fuerza del atacante
                int maxStr = DMZDatos.calcularSTR(raza, estatsAtacante.getStrength(), danoDefault);
                int staminacost = maxStr / 4;

                if (curStamina >= staminacost) {
                    // Si el atacante tiene suficiente stamina, aplicar el daño basado en la fuerza
                    event.setAmount(maxStr);
                    // Descontar stamina del atacante
                    estatsAtacante.removeCurStam(staminacost);
                } else {
                    // Daño por defecto si al atacante le falta stamina
                    event.setAmount(danoDefault);
                }
            });

            // Si la entidad que recibe el daño es un jugador
            if (event.getEntity() instanceof Player objetivo) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, objetivo).ifPresent(estatsObjetivo -> {
                    int defObjetivo = DMZDatos.calcularDEF(estatsObjetivo.getRace(), estatsObjetivo.getDefense());
                    // Restar la defensa del objetivo al daño
                    float danoFinal = event.getAmount() - defObjetivo;
                    event.setAmount(Math.max(danoFinal, 1)); // Asegurarse de que al menos se haga 1 de daño
                });
            } else {
                // Si golpeas a otra entidad (no jugador), aplica el daño máximo basado en la fuerza
                    event.setAmount(event.getAmount()); // Aplica tu máximo daño
                    System.out.println("Dano amount (entidad): " + event.getAmount());
            }
        } else {
            // Aquí manejamos el caso donde el atacante no es un jugador
            if (event.getEntity() instanceof Player objetivo) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, objetivo).ifPresent(estatsObjetivo -> {
                    int defObjetivo = DMZDatos.calcularDEF(estatsObjetivo.getRace(), estatsObjetivo.getDefense());
                    // Restar la defensa del objetivo al daño
                    float danoFinal = event.getAmount() - defObjetivo;
                    event.setAmount(Math.max(danoFinal, 1)); // Asegurarse de que al menos se haga 1 de daño
                });
            }
        }
    }


    @SubscribeEvent
    public static void livingFallEvent(LivingFallEvent event) {
        float realDistance = event.getDistance();

        if (event.getEntity() instanceof ServerPlayer player) {
            if (realDistance > 4.5f) {

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
                    int level = (stats.getStrength() +
                            stats.getDefense() +
                            stats.getConstitution() +
                            stats.getKiPower() +
                            stats.getEnergy()) / 5;

                    double energyToRemove = getEnergyToRemove(level);

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
        double energyRemovalValue;

        double baseReduction = DMCAttrConfig.MULTIPLIER_FALLDMG.get();

        if (level >= 100) {
            // Porcentaje calculado en base a la config (Default 0.03)
            energyRemovalValue = level * baseReduction;

        } else {
            energyRemovalValue = 0; // Niveles menores a 100 no reciben daño de ki
        }

        // Devuelve el valor actual de energía sacada
        return energyRemovalValue;
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



