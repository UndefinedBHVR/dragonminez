package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.stats.DMZStatsAttributes;

public class TickHandler {
    private int energyRegenCounter = 0;
    private int staminaRegenCounter = 0;
    private int energyConsumeCounter = 0;

    public void tickRegenConsume(DMZStatsAttributes playerStats, DMZDatos dmzDatos) {
        // Regeneración de stamina cada 3 segundos
        staminaRegenCounter++;
        if (staminaRegenCounter >= 60 * 3) {
            int maxStamina = dmzDatos.calcularSTM(playerStats.getRace(), playerStats.getConstitution());
            int regenStamina = (int) Math.ceil(maxStamina / 4.0);
            playerStats.addCurStam(regenStamina);
            staminaRegenCounter = 0;
        }

        // Regeneración de energía cada 5 segundos
        energyRegenCounter++;
        if (energyRegenCounter >= 60 * 5) {
            int maxEnergy = dmzDatos.calcularENE(playerStats.getRace(), playerStats.getEnergy(), playerStats.getDmzClass());
            int regenEnergy = dmzDatos.calcularKiRegen(playerStats.getRace(), maxEnergy, playerStats.getDmzClass());
            playerStats.addCurEnergy(regenEnergy);
            energyRegenCounter = 0;
        }

        // Consumo de energía cada 3 segundos
        energyConsumeCounter++;
        if (energyConsumeCounter >= 60 * 3) {
            int consumeEnergy = dmzDatos.calcularKiConsume(playerStats.getRace(), playerStats.getEnergy(), playerStats.getDmzState());
            playerStats.removeCurEnergy(consumeEnergy);
            energyConsumeCounter = 0;
        }
    }

}
