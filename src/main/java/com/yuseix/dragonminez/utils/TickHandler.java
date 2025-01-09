package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.network.C2S.CharacterC2S;
import com.yuseix.dragonminez.network.C2S.PermaEffC2S;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.stats.DMZStatsAttributes;

public class TickHandler {
    private int energyRegenCounter = 0;
    private int staminaRegenCounter = 0;
    private int energyConsumeCounter = 0;
    private  int chargeTimer = 0; // Aca calculamos el tiempo de espera

    private final int CHARGE_INTERVAL = 1 * (20); // No borrar el 20, eso es el tiempo en ticks lo que si puedes configurar es lo que esta la lado

    public void tickRegenConsume(DMZStatsAttributes playerStats, DMZDatos dmzDatos) {
        // Regeneración de stamina cada 3 segundos
        staminaRegenCounter++;
        if (staminaRegenCounter >= 20 * 3) {
            int maxStamina = dmzDatos.calcularSTM(playerStats.getRace(), playerStats.getConstitution());
            int regenStamina = (int) Math.ceil(maxStamina / 3.0);
            playerStats.addCurStam(regenStamina);
            staminaRegenCounter = 0;
        }

        // Regeneración de energía cada 5 segundos, si tenes turbo activo, no se regenera y consumis energia cada 3s
        energyRegenCounter++;
        if (playerStats.isTurbonOn()) {
            if (energyRegenCounter >= 20 * 3) {
                int maxEnergy = dmzDatos.calcularENE(playerStats.getRace(), playerStats.getEnergy(), playerStats.getDmzClass());
                int consumeEnergy = (dmzDatos.calcularKiRegen(playerStats.getRace(), maxEnergy, playerStats.getDmzClass()));
                playerStats.removeCurEnergy(consumeEnergy);
                energyRegenCounter = 0;
            }
        } else {
            if (energyRegenCounter >= 20 * 5) {
                int maxEnergy = dmzDatos.calcularENE(playerStats.getRace(), playerStats.getEnergy(), playerStats.getDmzClass());
                int regenEnergy = dmzDatos.calcularKiRegen(playerStats.getRace(), maxEnergy, playerStats.getDmzClass());
                playerStats.addCurEnergy(regenEnergy);
                energyRegenCounter = 0;
            }
        }

        // Consumo de energía cada 3 segundos
        energyConsumeCounter++;
        if (energyConsumeCounter >= 20 * 3) {
            int consumeEnergy = dmzDatos.calcularKiConsume(playerStats.getRace(), playerStats.getEnergy(), playerStats.getDmzState());
            playerStats.removeCurEnergy(consumeEnergy);
            energyConsumeCounter = 0;
        }
    }

    public void manejarCargaDeAura(DMZStatsAttributes playerstats, int maxenergia) {
        // Incrementa el temporizador en cada tick
        chargeTimer++;

        DMZDatos dmzdatos = new DMZDatos();

        if (chargeTimer >= CHARGE_INTERVAL) {
            if (playerstats.isAuraOn() && playerstats.isDescendKeyOn()) {
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
                if (!playerstats.isTurbonOn()) {
                    playerstats.addCurEnergy(dmzdatos.calcularCargaKi(maxenergia, playerstats.getDmzClass()));
                }
            }

            if (playerstats.isTurbonOn() && playerstats.getCurrentEnergy() <= 1) {
                playerstats.setDmzRelease(0);
            }

            chargeTimer = 0;
        }
    }

}
