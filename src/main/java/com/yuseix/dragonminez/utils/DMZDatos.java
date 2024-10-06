package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.config.races.*;
import com.yuseix.dragonminez.config.races.transformations.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class DMZDatos {
    public static int calcularSTR(int raza, int StatSTR, float danoJugador, int transformation, int porcentaje, String clase) {
        double maxStr = 0;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        if(transformation == 0){ //FORMA BASE
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrHumanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) { //FULL POWER
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get()) * (porcentaje/2);
                        } else { //CUALQUIERWA
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrHumanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 1: // Saiyan
                        if(transformation == 0){ //FORMA BASE
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) { //SUPER SAIYAJIN 1
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else { //OTROS
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 2: // Namek
                        if(transformation == 0){ //FORMA BASE
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) { // FULL POWER
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 3: // Bioandroide
                        if(transformation == 0){ //FORMA IMPERFECTA
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) { //SEMIPERFECTO
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else { //OTROS
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 4: // Cold Demon
                        if(transformation == 0){ //FORMA MINIMA
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) { //SEGUNDA FORMA
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else { //OTROS
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 5: // Majin
                        if(transformation == 0){ //FORMA BASE
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) { //FORMA KID
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else { //OTROS OSEA FORMA SUPER
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_WARRIOR.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        if(transformation == 0){
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrHumanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrHumanConfig.MULTIPLIER_FP_FORM_STR.get()) * (porcentaje/2);
                        } else {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZHumanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrHumanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 1: // Saiyan
                        if(transformation == 0){
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZSaiyanConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 2: // Namek
                        if(transformation == 0){
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZNamekConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 3: // Bioandroide
                        if(transformation == 0){
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZBioAndroidConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 4: // Cold Demon
                        if(transformation == 0){
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZColdDemonConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    case 5: // Majin
                        if(transformation == 0){
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else if (transformation == 1) {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        } else {
                            maxStr = (((danoJugador + ((double) StatSTR / 10)) * DMZMajinConfig.MULTIPLIER_STR_SPIRITUALIST.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (porcentaje/2);
                        }
                        break;

                    default:
                        // Manejar el caso en que la raza no sea valida
                        break;
                }
                break;
        }


        // Fórmula = ((StatSTR * ConfigRaza) * Transf) * Porcentaje
        return (int) maxStr;
    }

    public static int calcularDEF(int raza, int StatDEF, int Transformation, int powerRelease, String clase) {
        Player player = Minecraft.getInstance().player;

        double maxDef = 0;

        int DefensaArmor = player.getArmorValue();
        int DurezaArmor = Mth.floor(player.getAttributeValue(Attributes.ARMOR_TOUGHNESS));

        // Defensa = (((((StatDEF * ConfigRaza) * Transf) * Porcentaje) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4))) / 2.25)
        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrHumanConfig.MULTIPLIER_BASE.get() * (powerRelease/5))) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 1: // Saiyan
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 2: // Namek
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 3: // BioAndroide
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 4: // ColdDemon
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 5: // Majin
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_WARRIOR.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrHumanConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZHumanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrHumanConfig.MULTIPLIER_FP_FORM_DEF.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 1: // Saiyan
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZSaiyanConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 2: // Namek
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZNamekConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 3: // BioAndroide
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZBioAndroidConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 4: // ColdDemon
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZColdDemonConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    case 5: // Majin
                        if(Transformation == 0){ //Forma base
                            maxDef = ((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        } else {
                            maxDef = ((((StatDEF / 5) * DMZMajinConfig.MULTIPLIER_DEF_SPIRITUALIST.get()) * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (powerRelease/5)) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;
                        }
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            default:
                break;
        }



        return (int) maxDef;
    }

    public static int calcularCON(int raza, int StatCON, float vidaMC, String clase) {
        double maxCon = 0;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZHumanConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;
                    case 1: // Saiyan
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZSaiyanConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    case 2: // Namek
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZNamekConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    case 3: // Bioandroide
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZBioAndroidConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    case 4: // Cold Demon
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZColdDemonConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    case 5: // Majin
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZMajinConfig.MULTIPLIER_CON_WARRIOR.get()));
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZHumanConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;
                    case 1: // Saiyan
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZSaiyanConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    case 2: // Namek
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZNamekConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    case 3: // Bioandroide
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZBioAndroidConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    case 4: // Cold Demon
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZColdDemonConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    case 5: // Majin
                        maxCon = Math.round(vidaMC + ((double) StatCON * DMZMajinConfig.MULTIPLIER_CON_SPIRITUALIST.get()));
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            default:
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

    public static int calcularKiPower(int raza, int StatPWR, int Transformation, int PowerRelease, String clase){
        double maxPWR = 0;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrHumanConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 1: // Saiyan
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 2: // Namek
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 3: // Bioandroide
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 4: // Cold Demon
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 5: // Majin
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_WARRIOR.get() * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrHumanConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZHumanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrHumanConfig.MULTIPLIER_FP_FORM_KIPOWER.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 1: // Saiyan
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZSaiyanConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrSaiyanConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 2: // Namek
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZNamekConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrNamekConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 3: // Bioandroide
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZBioAndroidConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrBioAndroidConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 4: // Cold Demon
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZColdDemonConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrColdDemonConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    case 5: // Majin
                        if(Transformation == 0){
                            maxPWR = Math.round((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        } else {
                            maxPWR = Math.round((StatPWR * DMZMajinConfig.MULTIPLIER_KIPOWER_SPIRITUALIST.get() * DMZTrMajinConfig.MULTIPLIER_BASE.get()) * (PowerRelease/2) );
                        }
                        break;
                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
        }

        return (int) maxPWR;
    }

    public static int calcularENE(int raza, int StatENE, String clase) {
        double maxENE = 0;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        maxENE = Math.round(StatENE * DMZHumanConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;
                    case 1: // Saiyan
                        maxENE = Math.round(StatENE * DMZSaiyanConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 2: // Namek
                        maxENE = Math.round(StatENE * DMZNamekConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 3: // Bioandroide
                        maxENE = Math.round(StatENE * DMZBioAndroidConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 4: // Cold Demon
                        maxENE = Math.round(StatENE * DMZColdDemonConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 5: // Majin
                        maxENE = Math.round(StatENE * DMZMajinConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        maxENE = Math.round(StatENE * DMZHumanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;
                    case 1: // Saiyan
                        maxENE = Math.round(StatENE * DMZSaiyanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 2: // Namek
                        maxENE = Math.round(StatENE * DMZNamekConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 3: // Bioandroide
                        maxENE = Math.round(StatENE * DMZBioAndroidConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 4: // Cold Demon
                        maxENE = Math.round(StatENE * DMZColdDemonConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 5: // Majin
                        maxENE = Math.round(StatENE * DMZMajinConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            default:
                break;
        }


        return (int) maxENE;
    }

    public static int calcularKiConsume(int raza, int StatENE, int form){
        int costo = 0;

        switch (raza){
            case 0:
                if(form == 0){
                    costo = DMZTrHumanConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrHumanConfig.FP_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL HUMANO
                } else {
                    costo = DMZTrHumanConfig.BASE_FORM_KI_COST.get();
                }
                break;
            case 1:
                if(form == 0){
                    costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL SAIYAN
                } else {
                    costo = DMZTrSaiyanConfig.BASE_FORM_KI_COST.get();
                }

                break;
            case 2:
                if(form == 0){
                    costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL NAMEK
                } else {
                    costo = DMZTrNamekConfig.BASE_FORM_KI_COST.get();
                }
                break;
            case 3:
                if(form == 0){
                    costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL BIO ANDROIDE
                } else {
                    costo = DMZTrBioAndroidConfig.BASE_FORM_KI_COST.get();
                }
                break;
            case 4:
                if(form == 0){
                    costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL COLD DEMON
                } else {
                    costo = DMZTrColdDemonConfig.BASE_FORM_KI_COST.get();
                }
                break;
            case 5:
                if(form == 0){
                    costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get();
                } else if(form == 1){
                    costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get() + (2 * StatENE); //ESTO VA A CONSUMIR LA FORMA 1 DEL MAJIN
                } else {
                    costo = DMZTrMajinConfig.BASE_FORM_KI_COST.get();
                }
                break;
            default:
                break;
        }

        return costo;
    }

    public static int calcularKiRegen(int raza, int EnergiaTotal, String clase){
        int regenki = 0;

        switch (clase){
            case "Warrior":
                switch (raza){
                    case 0:
                        regenki = EnergiaTotal * (DMZHumanConfig.KI_REGEN_WARRIOR.get()/100);
                        break;
                    case 1:
                        regenki = EnergiaTotal * (DMZSaiyanConfig.KI_REGEN_WARRIOR.get()/100);
                        break;
                    case 2:
                        regenki = EnergiaTotal * (DMZNamekConfig.KI_REGEN_WARRIOR.get()/100);
                        break;
                    case 3:
                        regenki = EnergiaTotal * (DMZBioAndroidConfig.KI_REGEN_WARRIOR.get()/100);
                        break;
                    case 4:
                        regenki = EnergiaTotal * (DMZColdDemonConfig.KI_REGEN_WARRIOR.get()/100);
                        break;
                    case 5:
                        regenki = EnergiaTotal * (DMZMajinConfig.KI_REGEN_WARRIOR.get()/100);
                        break;
                    default:
                        break;
                }

                break;
            case "Spiritualist":
                switch (raza){
                    case 0:
                        regenki = EnergiaTotal * (DMZHumanConfig.KI_REGEN_SPIRITUALIST.get()/100);
                        break;
                    case 1:
                        regenki = EnergiaTotal * (DMZSaiyanConfig.KI_REGEN_SPIRITUALIST.get()/100);
                        break;
                    case 2:
                        regenki = EnergiaTotal * (DMZNamekConfig.KI_REGEN_SPIRITUALIST.get()/100);
                        break;
                    case 3:
                        regenki = EnergiaTotal * (DMZBioAndroidConfig.KI_REGEN_SPIRITUALIST.get()/100);
                        break;
                    case 4:
                        regenki = EnergiaTotal * (DMZColdDemonConfig.KI_REGEN_SPIRITUALIST.get()/100);
                        break;
                    case 5:
                        regenki = EnergiaTotal * (DMZMajinConfig.KI_REGEN_SPIRITUALIST.get()/100);
                        break;
                    default:
                        break;
                }
                    break;
        }

        return regenki;
    }

    public static int calcularCargaKi(int EnergiaTotal, String clase){
        var porcentaje = 0;

        switch (clase){
            case "Warrior":
                porcentaje = (int) (EnergiaTotal * 0.02);
                break;
            case "Spiritualist":
                porcentaje = (int) (EnergiaTotal * 0.04);
                break;
        }

        return porcentaje;
    }

}
