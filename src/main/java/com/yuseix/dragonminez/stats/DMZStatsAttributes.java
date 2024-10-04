package com.yuseix.dragonminez.stats;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class DMZStatsAttributes {

    private int races;
    private int hairID, bodytype, eyesType;
    private int strength = 5;
    private int defense = 5;
    private int constitution = 5, curStam;

    private int dmzRelease = 5;
    private int dmzState = 0;
    private int dmzSenzuDaily = 0;

    private int zpoints;
    private int KiPower = 5;

    private int energy = 5, currentEnergy;

    private String gender = "Male";

    private int bodyColor, bodyColor2, bodyColor3, eye1Color, eye2Color, hairColor = 921617, auraColor;

    private boolean AcceptCharacter = false;

    private String dmzClass = "Warrior";
    private String dmzAlignment = "Good";

    private final Player player;

    public DMZStatsAttributes(Player player) {
        this.player = player;
    }

    public int getDmzSenzuDaily() {
        return dmzSenzuDaily;
    }

    public void setDmzSenzuDaily(int dmzSenzuDaily) {
        this.dmzSenzuDaily = dmzSenzuDaily;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getDmzRelease() {
        return dmzRelease;
    }
    public void setDmzRelease(int dmzRelease) {
        this.dmzRelease = dmzRelease;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getDmzState() {
        return dmzState;
    }

    public void setDmzState(int dmzState) {
        this.dmzState = dmzState;
        DMZStatsCapabilities.syncStats(player);
    }

    public String getDmzClass() {
        return dmzClass;
    }


    public String getDmzAlignment() {
        return dmzAlignment;
    }

    public int getZpoints() {
        return zpoints;
    }

    public void setZpoints(int zpoints) {
        this.zpoints = zpoints;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(int bodyColor) {
        this.bodyColor = bodyColor;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getBodyColor2() {
        return bodyColor2;
    }

    public void setBodyColor2(int bodyColor2) {
        this.bodyColor2 = bodyColor2;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getBodyColor3() {
        return bodyColor3;
    }

    public void setBodyColor3(int bodyColor3) {
        this.bodyColor3 = bodyColor3;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getEye1Color() {
        return eye1Color;

    }

    public void setEye1Color(int eye1Color) {
        this.eye1Color = eye1Color;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getEye2Color() {
        return eye2Color;
    }

    public void setEye2Color(int eye2Color) {
        this.eye2Color = eye2Color;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getAuraColor() {
        return auraColor;
    }

    public void setAuraColor(int auraColor) {
        this.auraColor = auraColor;
        DMZStatsCapabilities.syncStats(player);
    }

    public void addStrength(int points) {

        if (strength <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            strength += points;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void addDefense(int points) {

        if (defense <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            defense += points;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void addCon(int points) {

        if (constitution <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            constitution += points;
        }
        DMZStatsCapabilities.syncStats(player);

    }


    public void addKipwr(int points) {

        if (KiPower <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            KiPower += points;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void addEnergy(int points) {

        if (energy <= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            energy += points;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public void addZpoints(int points) {

        zpoints += points;
        DMZStatsCapabilities.syncStats(player);

    }

    public void removeZpoints(int points) {

        if (this.zpoints >= points) {
            this.zpoints -= points;
        } else {
            this.zpoints = 0;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void removeStrenght(int points) {

        this.strength -= points;

        if (this.strength < 3) {
            this.strength = 3;
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public void removeDefense(int points) {

        this.defense -= points;

        if (this.defense < 3) {
            this.defense = 3;
        }
        DMZStatsCapabilities.syncStats(player);
    }

    public void removeConstitution(int points) {

        this.constitution -= points;

        if (this.constitution < 3) {
            this.constitution = 3;
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public void removeKiPower(int points) {

        this.KiPower -= points;

        if (this.KiPower < 3) {
            this.KiPower = 3;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public void removeEnergy(int points) {

        this.energy -= points;

        if (this.energy < 3) {
            this.energy = 3;
        }

        DMZStatsCapabilities.syncStats(player);

    }


    public int getRace() {
        return races;
    }

    public void setRace(int races) {
        this.races = Math.min(races, 6);
        DMZStatsCapabilities.syncStats(player);
    }

    public int getHairID() {
        return hairID;
    }

    public void setHairID(int hairID) {
        this.hairID = hairID;
        DMZStatsCapabilities.syncStats(player);

    }

    public int getBodytype() {
        return bodytype;
    }

    public void setBodytype(int bodytype) {
        this.bodytype = bodytype;
        DMZStatsCapabilities.syncStats(player);
    }

    public int getEyesType() {
        return eyesType;
    }

    public void setEyesType(int eyesType) {
        this.eyesType = eyesType;
        DMZStatsCapabilities.syncStats(player);

    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {

        this.strength = strength;

        if (this.strength >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.strength = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.strength = strength;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public int getDefense() {

        return defense;
    }

    public void setDefense(int defense) {

        this.defense = defense;

        if (this.defense >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.defense = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.defense = defense;
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {

        this.constitution = constitution;

        if (this.constitution >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.constitution = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.constitution = constitution;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public int getKiPower() {
        return KiPower;
    }

    public void setKiPower(int kiPower) {

        this.KiPower = kiPower;

        if (this.KiPower >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            KiPower = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.KiPower = kiPower;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {

        this.energy = energy;

        if (this.energy >= DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.energy = DMZGeneralConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.energy = energy;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {

        var maxEne = 0;

        maxEne = DMZDatos.calcularENE(races, energy, dmzClass);

        if(currentEnergy >= maxEne){
            this.currentEnergy = maxEne;
        } else {
            this.currentEnergy = currentEnergy;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public void removeCurEnergy(int currentEnergy) {
        this.currentEnergy -= currentEnergy;

        if (this.currentEnergy < 0) {
            this.currentEnergy = 0;
        }

        DMZStatsCapabilities.syncStats(player);

    }

    public void addCurEnergy(int currentEnergy) {

        var maxEne = 0;

        maxEne = DMZDatos.calcularENE(races, energy, dmzClass);

        if(currentEnergy >= maxEne){
            this.currentEnergy = maxEne;
        } else {
            this.currentEnergy += currentEnergy;

            if(this.currentEnergy > maxEne){
                this.currentEnergy = maxEne;
            }
        }
        DMZStatsCapabilities.syncStats(player);
    }

    public int getCurStam() {

        return curStam;
    }

    public void setCurStam(int curStam) {

        var maxStam = 0;
        var maxVIDA = 0;


        maxVIDA = DMZDatos.calcularCON(races, constitution, 20, dmzClass);
        maxStam = DMZDatos.calcularSTM(races, maxVIDA);

        if(curStam >= maxStam){
            this.curStam = maxStam;
        } else {
            this.curStam = curStam;
        }

        //this.curStam = curStam;
        DMZStatsCapabilities.syncStats(player);
    }

    public void removeCurStam(int curStam) {
        this.curStam -= curStam;

        if (this.curStam < 0) {
            this.curStam = 0;
        }
        DMZStatsCapabilities.syncStats(player);

    }

    public void addCurStam(int curStam) {
        var maxStam = 0;
        var maxVIDA = 0;

        maxVIDA = DMZDatos.calcularCON(races, constitution, 20, dmzClass);
        maxStam = DMZDatos.calcularSTM(races, maxVIDA);

        if(curStam >= maxStam){
            this.curStam = maxStam;
        } else {
            this.curStam += curStam;

            if(this.curStam > maxStam){
                this.curStam = maxStam;
            }
        }

        DMZStatsCapabilities.syncStats(player);
    }

    public boolean isAcceptCharacter() {
        return AcceptCharacter;
    }

    public void setAcceptCharacter(boolean acceptCharacter) {
        AcceptCharacter = acceptCharacter;
        DMZStatsCapabilities.syncStats(player);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        DMZStatsCapabilities.syncStats(player);
    }

    public void setDmzClass(String dmzClass) {
        this.dmzClass = dmzClass;
        DMZStatsCapabilities.syncStats(player);
    }

    public void setDmzAlignment(String dmzAlignment) {
        this.dmzAlignment = dmzAlignment;
        DMZStatsCapabilities.syncStats(player);
    }

    public CompoundTag saveNBTData() {

        CompoundTag nbt = new CompoundTag();

        nbt.putInt("race", races);

        nbt.putInt("hairID", hairID);
        nbt.putInt("bodyType", bodytype);
        nbt.putInt("eyesType", eyesType);

        nbt.putInt("strength", strength);
        nbt.putInt("defense", defense);
        nbt.putInt("constitution", constitution);
        nbt.putInt("kiPower", KiPower);
        nbt.putInt("energy", energy);

        nbt.putInt("currentEnergy", currentEnergy);
        nbt.putInt("currentStamina", curStam);
        nbt.putInt("dmzState", dmzState);
        nbt.putInt("dmzRelease", dmzRelease);

        nbt.putInt("bodyColor", bodyColor);
        nbt.putInt("bodyColor2", bodyColor2);
        nbt.putInt("bodyColor3", bodyColor3);
        nbt.putInt("hairColor", hairColor);
        nbt.putInt("eye1Color", eye1Color);
        nbt.putInt("eye2Color", eye2Color);
        nbt.putInt("auraColor", auraColor);

        nbt.putString("gender", gender);
        nbt.putString("dmzClass", dmzClass);
        nbt.putString("dmzAlignment",dmzAlignment);

        nbt.putInt("zpoints", zpoints);
        nbt.putInt("dmzSenzuDaily", dmzSenzuDaily);
        nbt.putBoolean("acceptCharacter", AcceptCharacter);

        return nbt;
    }

    public void loadNBTData(CompoundTag nbt) {

        races = nbt.getInt("race");

        hairID = nbt.getInt("hairID");
        bodytype = nbt.getInt("bodyType");
        eyesType = nbt.getInt("eyesType");

        strength = nbt.getInt("strength");
        defense = nbt.getInt("defense");
        constitution = nbt.getInt("constitution");
        KiPower = nbt.getInt("kiPower");
        energy = nbt.getInt("energy");

        zpoints = nbt.getInt("zpoints");
        dmzSenzuDaily = nbt.getInt("dmzSenzuDaily");

        currentEnergy = nbt.getInt("currentEnergy");
        curStam = nbt.getInt("currentStamina");
        dmzState = nbt.getInt("dmzState");
        dmzRelease = nbt.getInt("dmzRelease");

        bodyColor = nbt.getInt("bodyColor");
        bodyColor2 = nbt.getInt("bodyColor2");
        bodyColor3 = nbt.getInt("bodyColor3");
        hairColor = nbt.getInt("hairColor");
        eye1Color = nbt.getInt("eye1Color");
        eye2Color = nbt.getInt("eye2Color");
        auraColor = nbt.getInt("auraColor");

        gender = nbt.getString("gender");
        dmzClass = nbt.getString("dmzClass");
        dmzAlignment = nbt.getString("dmzAlignment");

        AcceptCharacter = nbt.getBoolean("acceptCharacter");

    }


}
