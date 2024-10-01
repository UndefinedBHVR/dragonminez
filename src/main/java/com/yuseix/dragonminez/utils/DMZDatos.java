package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.config.DMCAttrConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class DMZDatos {
    public static int calcularSTR(int raza, int StatSTR, float danoJugador) {
        double maxStr = 0;

        switch (raza) {
            case 0: // Humano
                maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();
                break;

            case 1: // Saiyan
                maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR_SAIYAN.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();
                break;

            case 2: // Namek
                maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR_NAMEK.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();
                break;

            case 3: // Bioandroide
                maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR_BIO.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();
                break;

            case 4: // Cold Demon
                maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR_COLD.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();
                break;

            case 5: // Majin
                maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR_MAJIN.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();
                break;

            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        // Fórmula = ((StatSTR * ConfigRaza) * Transf) * Porcentaje
        return (int) maxStr;
    }

    public static int calcularDEF(int raza, int StatDEF) {
        Player player = Minecraft.getInstance().player;

        double maxDef = 0;

        int DefensaArmor = player.getArmorValue();
        int DurezaArmor = Mth.floor(player.getAttributeValue(Attributes.ARMOR_TOUGHNESS));

        // Defensa = (((((StatDEF * ConfigRaza) * Transf) * Porcentaje) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4))) / 2.25)
        switch (raza) {
            case 0: // Humano
                maxDef = ((StatDEF / 4) * DMCAttrConfig.MULTIPLIER_DEF.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                break;

            case 1: // Saiyan
                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_SAIYAN.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                break;

            case 2: // Namek
                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_NAMEK.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                break;

            case 3: // BioAndroide
                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_BIO.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                break;

            case 4: // ColdDemon
                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_COLD.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                break;

            case 5: // Majin
                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_MAJIN.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                break;

            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) maxDef;
    }

    public static int calcularCON(int raza, int StatCON, float vidaMC) {
        double maxCon = 0;

        switch (raza) {
            case 0: // Humano
                maxCon = Math.round(vidaMC + ((double) StatCON * DMCAttrConfig.MULTIPLIER_CON.get()));
                break;
            case 1: // Saiyan
                maxCon = Math.round(vidaMC + ((double) StatCON * DMCAttrConfig.MULTIPLIER_CON_SAIYAN.get()));
                break;

            case 2: // Namek
                maxCon = Math.round(vidaMC + ((double) StatCON * DMCAttrConfig.MULTIPLIER_CON_NAMEK.get()));
                break;

            case 3: // Bioandroide
                maxCon = Math.round(vidaMC + ((double) StatCON * DMCAttrConfig.MULTIPLIER_CON_BIO.get()));
                break;

            case 4: // Cold Demon
                maxCon = Math.round(vidaMC + ((double) StatCON * DMCAttrConfig.MULTIPLIER_CON_COLD.get()));
                break;

            case 5: // Majin
                maxCon = Math.round(vidaMC + ((double) StatCON * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
                break;

            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) maxCon;
    }
    public static int calcularSTM(int raza, int maxCON) {
        //Aca lo configuraremos segun raza y si es spiritualista o guerrero
        double maxSTM = 0;

        switch (raza) {
            case 0: // Humano
                maxSTM = Math.round(maxCON * 0.5);
                break;
            case 1: // Saiyan
                maxSTM = Math.round(maxCON * 0.5);
                break;

            case 2: // Namek
                maxSTM = Math.round(maxCON * 0.5);
                break;

            case 3: // Bioandroide
                maxSTM = Math.round(maxCON * 0.5);
                break;

            case 4: // Cold Demon
                maxSTM = Math.round(maxCON * 0.5);
                break;

            case 5: // Majin
                maxSTM = Math.round(maxCON * 0.5);
                break;

            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) maxSTM;
    }
    public static int calcularENE(int raza, int StatENE) {
        double maxENE = 0;

        switch (raza) {
            case 0: // Humano
                maxENE = Math.round(StatENE * DMCAttrConfig.MULTIPLIER_ENERGY.get() + 40);
                break;
            case 1: // Saiyan
                maxENE = Math.round(StatENE * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40);
                break;

            case 2: // Namek
                maxENE = Math.round(StatENE * DMCAttrConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40);
                break;

            case 3: // Bioandroide
                maxENE = Math.round(StatENE * DMCAttrConfig.MULTIPLIER_ENERGY_BIO.get() + 40);
                break;

            case 4: // Cold Demon
                maxENE = Math.round(StatENE * DMCAttrConfig.MULTIPLIER_ENERGY_COLD.get() + 40);
                break;

            case 5: // Majin
                maxENE = Math.round(StatENE * DMCAttrConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40);
                break;

            default:
                // Manejar el caso en que la raza no sea válida
                break;
        }

        return (int) maxENE;
    }

}
