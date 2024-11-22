package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.AuraEntity;
import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.C2S.InvocarAuraC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import com.yuseix.dragonminez.utils.DMZDatos2;
import com.yuseix.dragonminez.utils.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StatsEvents {

    private static int tickcounter = 0;
    private static int energyRegen = 0;
    private static int energiaConsumecounter = 0;
    private static int Senzu_countdown = 0;

    private static int chargeTimer = 0; // Aca calculamos el tiempo de espera
    private static final int CHARGE_INTERVAL = 1 * (20); // No borrar el 20, eso es el tiempo en ticks lo que si puedes configurar es lo que esta la lado

    //Teclas
    private static boolean isActionKeyPressed = false;
    private static boolean previousKiChargeState = false;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // Verificar que estamos en el servidor y en la fase final
        if (event.side != LogicalSide.SERVER || event.phase != TickEvent.Phase.END) {
            return;
        }

        Player player = event.player;
        DMZDatos2 dmzdatos = new DMZDatos2();

        // Verificar que el jugador es un ServerPlayer
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        // Acceder a la capability
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, serverPlayer).ifPresent(playerStats -> {
            // Modificar los datos de la capacidad
            int maximaEnergy = dmzdatos.calcularENE(playerStats.getRace(), playerStats.getEnergy(),playerStats.getDmzClass());

            //playerStats.addCurEnergy(5);

            // Mensaje de depuración para confirmar
            System.out.println("Tu maximo de energia es: " + maximaEnergy);
            System.out.println("Tu energia actual es: " + playerStats.getCurrentEnergy());

        });
    }

//    @SubscribeEvent
//    public static void tick(TickEvent.PlayerTickEvent event) {
//        // Regenerar stamina
//        if (event.side == LogicalSide.SERVER && event.player instanceof ServerPlayer serverPlayer) {
//
//            energyRegen++;
//            tickcounter++;
//            energiaConsumecounter++;
//
//            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, serverPlayer).ifPresent(playerstats -> {
//                var vidaMC = 20;
//                var con = playerstats.getConstitution();
//                var raza = playerstats.getRace();
//                var energia = playerstats.getEnergy();
//
//                int maxenergia = DMZDatos.calcularENE(raza, energia, playerstats.getDmzClass());
//                int maxstamina = DMZDatos.calcularSTM(raza, DMZDatos.calcularCON(raza, con, vidaMC, playerstats.getDmzClass()));
//
//                // Ajustar la salud máxima del jugador
//                serverPlayer.getAttribute(Attributes.MAX_HEALTH).setBaseValue(DMZDatos.calcularCON(raza, con, vidaMC, playerstats.getDmzClass()));
//
//                // Regeneración de stamina
//                if (playerstats.getCurStam() >= 0 && playerstats.getCurStam() <= maxstamina) {
//                    if (tickcounter >= 60 * 3) { // Cada 3 segundos
//                        int regenStamina = (maxstamina / 4);
//                        playerstats.addCurStam(regenStamina);
//                        tickcounter = 0;
//                    }
//                }
//
//                // Regeneración de energía Warrior
//                if (playerstats.getCurrentEnergy() >= 0 && playerstats.getCurrentEnergy() <= maxenergia) {
//                    if (energyRegen >= 60 * 5) { // Cada 5 segundos
//                        int regenki = DMZDatos.calcularKiRegen(raza, maxenergia, playerstats.getDmzClass()); // Regenerar 10% de la energía máxima
//                        playerstats.addCurEnergy(regenki);
//                        energyRegen = 0;
//                    }
//                }
//
//
//                //Consumo de energia
//                if (playerstats.getCurrentEnergy() >= 0 && playerstats.getCurrentEnergy() <= maxenergia) {
//                    if (energiaConsumecounter >= 60 * 3) { // Cada 3 segundos
//                        int consumeki = DMZDatos.calcularKiConsume(raza, playerstats.getEnergy(), playerstats.getDmzState());
//                        playerstats.removeCurEnergy(consumeki);
//                        energiaConsumecounter = 0;
//                    }
//                }
//
//                //Tiempo para reclamar una senzu
//                if (Senzu_countdown > 0) {
//                    playerstats.setDmzSenzuDaily(Senzu_countdown / 20);
//                    Senzu_countdown--;
//                }
//                if (Senzu_countdown == 0) {
//                    playerstats.setDmzSenzuDaily(0);
//                }
//
//                //Aca manejamos la carga de aura
//                //manejarCargaDeAura(playerstats, isActionKeyPressed, maxenergia);
//
//
//                //Restar el tiempo que se pone en el comando dmztempeffect
//                //updateTemporaryEffects(event.player);
//
//
//            });
//
//
//        }
//
//    }
    private static void manejarCargaDeAura(DMZStatsAttributes playerstats, boolean isActionKeyPressed, int maxenergia) {
        // Incrementa el temporizador en cada tick
        chargeTimer++;

        if (chargeTimer >= CHARGE_INTERVAL) {
            if (playerstats.isAuraOn() && isActionKeyPressed) {
                if (playerstats.getDmzRelease() > 0) {
                    playerstats.setDmzRelease(playerstats.getDmzRelease() - 5);
                    if (playerstats.getDmzRelease() < 0) {
                        playerstats.setDmzRelease(0);
                    }
                }
            } else if (playerstats.isAuraOn()) {
                if (playerstats.getDmzRelease() < 50) {
                    playerstats.setDmzRelease(playerstats.getDmzRelease() + 5);
                    if (playerstats.getDmzRelease() > 50) {
                        playerstats.setDmzRelease(50);
                    }
                }

                playerstats.addCurEnergy(DMZDatos.calcularCargaKi(maxenergia, playerstats.getDmzClass()));
            }

            chargeTimer = 0;
        }
    }
    private static void updateTemporaryEffects(Player player) {
        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(playerstats -> {
            Iterator<Map.Entry<String, Integer>> iterator = playerstats.getDMZTemporalEffects().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                int timeLeft = entry.getValue() - 1;  // Reducir en 1 tick cada vez
                if (timeLeft <= 0) {
                    playerstats.removeTemporalEffect(entry.getKey());  // Usa el método para eliminar el efecto
                } else {
                    entry.setValue(timeLeft);  // Actualiza el tiempo restante
                }
            }
        });
    }

    @SubscribeEvent
    public static void Recibirdano(LivingHurtEvent event) {
        // Si el que hace el daño es un jugador
        if (event.getSource().getEntity() instanceof Player atacante) {
            // Obtener las estadísticas del atacante
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, atacante).ifPresent(cap -> {
                int raza = cap.getRace();
                int curStamina = cap.getCurStam();
                var majinOn = cap.hasDMZPermaEffect("majin");
                var mightfruitOn = cap.hasDMZTemporalEffect("mightfruit");

                float danoDefault = event.getAmount(); // Capturamos el daño original

                // Calcular el daño basado en la fuerza del atacante
                int maxStr = DMZDatos.calcularSTR(raza, cap.getStrength(), danoDefault, cap.getDmzState(),
                        cap.getDmzRelease(), cap.getDmzClass(), majinOn, mightfruitOn);

                int staminacost = maxStr / 12;

                if (curStamina >= staminacost) {
                    // Si el atacante tiene suficiente stamina, aplicar el daño basado en la fuerza
                    event.setAmount(maxStr);
                    // Descontar stamina del atacante
                    cap.removeCurStam(staminacost);
                    sonidosGolpes(atacante);
                } else {
                    // Daño por defecto si al atacante le falta stamina
                    event.setAmount(danoDefault);
                }
            });

            // Si la entidad que recibe el daño es un jugador
            if (event.getEntity() instanceof Player objetivo) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, objetivo).ifPresent(statsObjetivo -> {
                    var majinOn = statsObjetivo.hasDMZPermaEffect("majin");
                    var fruta = statsObjetivo.hasDMZTemporalEffect("mightfruit");

                    int defObjetivo = DMZDatos.calcularDEF(statsObjetivo.getRace(), statsObjetivo.getDefense(), statsObjetivo.getDmzState(), statsObjetivo.getDmzRelease(), statsObjetivo.getDmzClass(), majinOn, fruta);
                    // Restar la defensa del objetivo al daño
                    float danoFinal = event.getAmount() - defObjetivo;
                    event.setAmount(Math.max(danoFinal, 1)); // Asegurarse de que al menos se haga 1 de daño
                });
            } else {
                // Si golpeas a otra entidad (no jugador), aplica el daño máximo basado en la fuerza
                event.setAmount(event.getAmount()); // Aplica tu máximo daño
            }
        } else {
            // Aquí manejamos el caso donde el atacante no es un jugador
            if (event.getEntity() instanceof Player objetivo) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, objetivo).ifPresent(statsObjetivo -> {
                    var majinOn = statsObjetivo.hasDMZPermaEffect("majin");
                    var fruta = statsObjetivo.hasDMZTemporalEffect("mightfruit");

                    int defObjetivo = DMZDatos.calcularDEF(statsObjetivo.getRace(), statsObjetivo.getDefense(),
                            statsObjetivo.getDmzState(), statsObjetivo.getDmzRelease(),
                            statsObjetivo.getDmzClass(), majinOn, fruta);

                    // Restar la defensa del objetivo al daño
                    float danoFinal = event.getAmount() - defObjetivo;
                    event.setAmount(Math.max(danoFinal, 1)); // Asegurarse de que al menos se haga 1 de daño
                });
            }
        }
    }


    @SubscribeEvent
    public static void livingFallEvent(LivingFallEvent event) {
        float fallDistance = event.getDistance();

        if (event.getEntity() instanceof ServerPlayer player) {
            if (fallDistance > 3.0f) {

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {

                    int maxEnergy = DMZDatos.calcularENE(stats.getRace(), stats.getEnergy(), stats.getDmzClass());

                    // drenaje de config
                    int baseEnergyDrain = (int) Math.ceil(maxEnergy * DMZGeneralConfig.MULTIPLIER_FALLDMG.get());

                    // Incrementar el drenaje por altura
                    int extraEnergyDrain = (int) ((fallDistance - 4.5f) * baseEnergyDrain / 4.5f);

                    int totalEnergyDrain = baseEnergyDrain + extraEnergyDrain;

                    System.out.println("Se va a drenar al jugador la siguiente cantidad de energia: " + totalEnergyDrain);

                    // Solo drenar energía si el jugador tiene suficiente y cancelar el daño
                    if (stats.getCurrentEnergy() >= totalEnergyDrain) {
                        stats.removeCurEnergy(totalEnergyDrain);
                        event.setCanceled(true);
                    }
                });
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onKeyInputEvent(InputEvent.Key event) {
            // Variable para almacenar el estado previo de la tecla KI_CHARGE
            boolean isKiChargeKeyPressed = Keys.KI_CHARGE.isDown();
            // Detecta si la tecla KI_CHARGE está presionada o liberada y solo envía el paquete si cambia el estado
            if (isKiChargeKeyPressed && !previousKiChargeState) {
                ModMessages.sendToServer(new CharacterC2S("isAuraOn", 1));
                //ModMessages.sendToServer(new InvocarAuraC2S());
                previousKiChargeState = true; // Actualiza el estado previo
            } else if (!isKiChargeKeyPressed && previousKiChargeState) {
                ModMessages.sendToServer(new CharacterC2S("isAuraOn", 0));
                //ModMessages.sendToServer(new InvocarAuraC2S());
                previousKiChargeState = false; // Actualiza el estado previo
            }


            // Detecta si la tecla DESCEND_KEY está presionada o liberada
            isActionKeyPressed = Keys.DESCEND_KEY.isDown();

    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            UUID playerId = player.getUUID();
            AuraEntity aura = InvocarAuraC2S.playerAuraMap.remove(playerId); // Elimina el aura del mapa

            if (aura != null) {
                aura.remove(Entity.RemovalReason.DISCARDED); // Remueve el aura del mundo si aún existe
            }
        }
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

    // Método para iniciar la cuenta regresiva
    public static void startSenzuCountdown(Player player, int seconds) {
        player.getCapability(DMZStatsCapabilities.INSTANCE).ifPresent(playerstats -> {
            // Convertir segundos a ticks y asignar a Senzu_countdown
            Senzu_countdown = seconds * 20;
            playerstats.setDmzSenzuDaily(seconds); // Inicializa el valor en segundos
        });
    }


}



