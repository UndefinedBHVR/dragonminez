package com.yuseix.dragonminec.stats;

import com.yuseix.dragonminec.client.ClientPlayerStats;
import net.minecraft.nbt.CompoundTag;

public class PlayerStatsAttributes {

    private int races;
    private int hairID, bodytype, eyesType;
    private int strength = 5;
    private int defense = 5;
    private int constitution = 5, curBody, curStam, stamina = 15;

    private int zpoints;
    private int KiPower = 5;


    private int energy = 5,currentEnergy;



    public int getZpoints() {
        return zpoints;
    }

    public void setZpoints(int zpoints) {
        this.zpoints = zpoints;
    }

    public int addStrength(int points){

        strength += points;

        return strength;
    }
    public int addDefense(int points){

        defense += points;

        return defense;
    }
    public int addCon(int points){

        constitution += points;

        return constitution;
    }
    public int addStam(int points){

        stamina += points;

        return stamina;
    }
    public int addKipwr(int points){

        KiPower += points;

        return KiPower;
    }
    public int addEnergy(int points){

        energy += points;

        return energy;
    }

    public int addZpoints(int points){

        zpoints += points;

        return zpoints;
    }
    public int removeZpoints(int points){

        zpoints -= points;

        return zpoints;
    }
    public int removeEnergy(int points){
        currentEnergy -= points;
        if(currentEnergy < 0){
            currentEnergy = 0;
        }
        return currentEnergy;
    }

    public int getRace() {
        return races;
    }

    public void setRace(int races) {
        this.races = races;
        if(races > 6){
            this.races = 6;
        }
    }

    public int getHairID() {
        return hairID;
    }

    public void setHairID(int hairID) {
        this.hairID = hairID;
    }

    public int getBodytype() {
        return bodytype;
    }

    public void setBodytype(int bodytype) {
        this.bodytype = bodytype;
    }

    public int getEyesType() {
        return eyesType;
    }

    public void setEyesType(int eyesType) {
        this.eyesType = eyesType;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {

        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getKiPower() {
        return KiPower;
    }

    public void setKiPower(int kiPower) {
        KiPower = kiPower;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getCurrentEnergy() {

        currentEnergy = ClientPlayerStats.getMaxENERGY();

        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getCurBody() {

        curBody = ClientPlayerStats.getMaxCON();

        return curBody;
    }

    public void setCurBody(int curBody) {
        this.curBody = curBody;
    }

    public int getCurStam() {

        return curStam;
    }

    public void setCurStam(int curStam) {
        this.curStam = curStam;
    }
    public int removeCurStam(int curStam) {
        this.curStam -= curStam;

        if(this.curStam < 0){
            this.curStam = 0;
        }
        return this.curStam;
    }
    public int addCurStam(int curStam) {

        if(this.curStam < (stamina + 3)/3){
            this.curStam += curStam;
        } else {
            this.curStam += 0;
        }

        return this.curStam;
    }
    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void copyFrom(PlayerStatsAttributes source){

        this.races = source.races;

        this.hairID = source.hairID;
        this.bodytype = source.bodytype;
        this.eyesType = source.eyesType;

        this.strength = source.strength;
        this.defense = source.defense;
        this.constitution = source.constitution;
        this.KiPower = source.KiPower;
        this.energy = source.energy;
        this.stamina = source.stamina;

        this.currentEnergy = source.currentEnergy;
        this.curBody = source.curBody;
        this.curStam = source.curStam;

        this.zpoints = source.zpoints;


    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("race", races);

        nbt.putInt("hairID", hairID);
        nbt.putInt("bodyType", bodytype);
        nbt.putInt("eyesType", eyesType);

        nbt.putInt("strength", strength);
        nbt.putInt("defense", defense);
        nbt.putInt("constitution", constitution);
        nbt.putInt("kiPower", KiPower);
        nbt.putInt("energy", energy);
        nbt.putInt("stamina", stamina);

        nbt.putInt("currentEnergy", currentEnergy);
        nbt.putInt("currentBody", curBody);
        nbt.putInt("currentStamina", curStam);

        nbt.putInt("zpoints",zpoints);

    }

    public void loadNBTData(CompoundTag nbt){

        races = nbt.getInt("race");

        hairID = nbt.getInt("hairID");
        bodytype = nbt.getInt("bodyType");
        eyesType = nbt.getInt("eyesType");

        strength = nbt.getInt("strength");
        defense = nbt.getInt("defense");
        constitution = nbt.getInt("constitution");
        KiPower = nbt.getInt("kiPower");
        energy = nbt.getInt("energy");
        stamina = nbt.getInt("stamina");

        zpoints = nbt.getInt("zpoints");
        currentEnergy = nbt.getInt("currentEnergy");
        curBody = nbt.getInt("currentBody");
        curStam = nbt.getInt("currentStamina");

    }



}
