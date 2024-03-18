package com.yuseix.dragonminec.stats;

import com.yuseix.dragonminec.events.ModEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class PlayerStatsAttributes {

    private int races;
    private int hairID, bodytype, eyesType;
    private int strength = 5;
    private int defense = 5;
    private int constitution = 5, curBody, curStam, stamina = 15;

    private int zpoints;
    private int KiPower = 5;


    private int energy = 5,currentEnergy;

    private final Player player;

    public PlayerStatsAttributes(Player player) {
        this.player = player;
    }

    public int getZpoints() {
        return zpoints;
    }

    public void setZpoints(int zpoints) {
        this.zpoints = zpoints;
        ModEvents.sync(player);
    }

    public int addStrength(int points){

        strength += points;
        ModEvents.sync(player);

        return strength;
    }
    public int addDefense(int points){

        defense += points;
        ModEvents.sync(player);

        return defense;
    }
    public int addCon(int points){

        constitution += points;
        ModEvents.sync(player);

        return constitution;
    }
    public int addStam(int points){

        stamina += points;
        ModEvents.sync(player);

        return stamina;
    }
    public int addKipwr(int points){

        KiPower += points;
        ModEvents.sync(player);

        return KiPower;
    }
    public int addEnergy(int points){

        energy += points;
        ModEvents.sync(player);

        return energy;
    }

    public int addZpoints(int points){

        zpoints += points;
        ModEvents.sync(player);

        return zpoints;
    }
    public int removeZpoints(int points){

        zpoints -= points;
        ModEvents.sync(player);

        return zpoints;
    }
    public int removeEnergy(int points){
        currentEnergy -= points;
        if(currentEnergy < 0){
            currentEnergy = 0;
        }
        ModEvents.sync(player);

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
        ModEvents.sync(player);

    }

    public int getHairID() {
        return hairID;
    }

    public void setHairID(int hairID) {
        this.hairID = hairID;
        ModEvents.sync(player);

    }

    public int getBodytype() {
        return bodytype;
    }

    public void setBodytype(int bodytype) {
        this.bodytype = bodytype;
        ModEvents.sync(player);
    }

    public int getEyesType() {
        return eyesType;
    }

    public void setEyesType(int eyesType) {
        this.eyesType = eyesType;
        ModEvents.sync(player);

    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
        ModEvents.sync(player);

    }

    public int getDefense() {

        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
        ModEvents.sync(player);
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
        ModEvents.sync(player);

    }

    public int getKiPower() {
        return KiPower;
    }

    public void setKiPower(int kiPower) {
        KiPower = kiPower;
        ModEvents.sync(player);

    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        ModEvents.sync(player);

    }

    public int getCurrentEnergy() {


        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
        ModEvents.sync(player);

    }

    public int getCurBody() {


        return curBody;
    }

    public void setCurBody(int curBody) {
        this.curBody = curBody;
        ModEvents.sync(player);
    }

    public int getCurStam() {

        return curStam;
    }

    public void setCurStam(int curStam) {
        this.curStam = curStam;
        ModEvents.sync(player);
    }
    public int removeCurStam(int curStam) {
        this.curStam -= curStam;

        if(this.curStam < 0){
            this.curStam = 0;
        }
        ModEvents.sync(player);

        return this.curStam;
    }
    public int addCurStam(int curStam) {

        if(this.curStam < (stamina + 3)){
            this.curStam += curStam;
        } else {
            this.curStam += 0;
        }

        ModEvents.sync(player);
        return this.curStam;
    }
    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
        ModEvents.sync(player);
    }
    public CompoundTag saveNBTData(){

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
        nbt.putInt("stamina", stamina);

        nbt.putInt("currentEnergy", currentEnergy);
        nbt.putInt("currentBody", curBody);
        nbt.putInt("currentStamina", curStam);

        nbt.putInt("zpoints",zpoints);

        return nbt;
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
