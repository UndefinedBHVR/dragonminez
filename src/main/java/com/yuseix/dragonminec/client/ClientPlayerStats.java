package com.yuseix.dragonminec.client;

import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.network.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class ClientPlayerStats {

    private static int strenght = 1;
    private static int defense = 1;
    private static int constitution = 1;
    private static int kipower = 1;
    private static int energy = 1;
    private static int stamina = 1;

    private static int level = 0;

    private static int zpoints;


    private static int MaxSTR;
    private static int MaxDEF;
    private static int MaxCON;
    private static int curBody, curEnergy, curStam;
    private static int MaxKIPWR;
    private static int MaxENERGY;
    private static int MaxSTAMINA;


    public static int getZpoints() {
        return zpoints;
    }

    public static void setZpoints(int zpoints) {
        ClientPlayerStats.zpoints = zpoints;
    }

    public static void addZpoints(int zpoints) {
        ClientPlayerStats.zpoints += zpoints;
    }


    public static int getZcost() {

        int costo = (int) ((strenght + defense + constitution + kipower + energy +4) * DMCAttrConfig.MULTIPLIER_ZPOINTS_COST.get());

        return costo;
    }

    public static int getLevel(){
        level = (strenght+defense+constitution+kipower+energy)/5;

        return level;
    }

    public static int getStrenght() {
        return strenght;
    }

    public static void setStrenght(int strenght) {
        ClientPlayerStats.strenght = strenght;
    }

    public static int getDefense() {
        return defense;
    }

    public static void setDefense(int defense) {
        ClientPlayerStats.defense = defense;
    }

    public static int getConstitution() {
        return constitution;
    }

    public static void setConstitution(int constitution) {
        ClientPlayerStats.constitution = constitution;
    }

    public static int getKipower() {
        return kipower;
    }

    public static void setKipower(int kipower) {
        ClientPlayerStats.kipower = kipower;
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        ClientPlayerStats.energy = energy;
    }

    public static int getMaxSTR() {

        MaxSTR = (int)(strenght -2)*DMCAttrConfig.MULTIPLIER_STR.get();

        return MaxSTR;
    }
    public static int getMaxDEF() {

        MaxDEF = (defense - 2)*DMCAttrConfig.MULTIPLIER_DEF.get();

        return MaxDEF;
    }


    public static int getMaxCON() {

        MaxCON = (constitution - 2)*DMCAttrConfig.MULTIPLIER_CON.get();
        //ModMessages.sendToServer(new HealthC2S(MaxCON));

        return MaxCON;
    }

    public static int getMaxKIPWR() {

        MaxKIPWR = (kipower - 2)*DMCAttrConfig.MULTIPLIER_KIPOWER.get();

        return MaxKIPWR;
    }

    public static int getMaxENERGY() {

        MaxENERGY = (energy - 2)*DMCAttrConfig.MULTIPLIER_ENERGY.get();

        return MaxENERGY;
    }

    public static void setCurBody(int curBody) {
        ClientPlayerStats.curBody = curBody;
    }

    public static int getCurEnergy() {
        return curEnergy;
    }

    public static void setCurEnergy(int curEnergy) {
        ClientPlayerStats.curEnergy = curEnergy;
    }

    public static int getCurStam() {
        return curStam;
    }

    public static void setCurStam(int curStam) {
        ClientPlayerStats.curStam = curStam;
    }

    public static int getStamina() {
        return stamina;
    }

    public static void setStamina(int stamina) {
        ClientPlayerStats.stamina = stamina;
    }

    public static int getMaxSTAMINA() {

        MaxSTAMINA = ((getStamina() + 3) / 2);

        return MaxSTAMINA;
    }

}
