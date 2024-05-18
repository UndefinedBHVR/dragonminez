package com.yuseix.dragonminez.stats;

import com.yuseix.dragonminez.config.DMCAttrConfig;
import net.minecraft.nbt.CompoundTag;

public class StatsAttributesV2 {

    public int races;
    public int hairID, bodytype, eyesType;
    public int strength = 5;
    public int defense = 5;
    public int constitution = 5, curBody, curStam, stamina = 15;
    public int zpoints;
    public int KiPower = 5;
    public int energy = 5, currentEnergy;
    public int bodyColor;


    public int getZpoints() {
        return zpoints;
    }

    public int addZpoints(int points) {
        zpoints += points;
        return zpoints;
    }

    public void setZpoints(int zpoints) {
        this.zpoints = zpoints;
    }

    public int getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(int bodyColor) {
        this.bodyColor = bodyColor;
    }

    public int addStrength(int points) {
        if (strength <= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            strength += points;
        }
        return strength;
    }

    public int addDefense(int points) {
        if (defense <= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            defense += points;
        }
        return defense;
    }

    public int addCon(int points) {
        if (constitution <= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            constitution += points;
        }
        return constitution;
    }

    public int addStam(int points) {
        if (stamina <= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            stamina += points;
        }
        return stamina;
    }

    public int addKipwr(int points) {
        if (KiPower <= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            KiPower += points;
        }
        return KiPower;
    }

    public int addEnergy(int points) {
        if (energy <= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            energy += points;
        }
        return energy;
    }


    public int removeZpoints(int points) {
        zpoints -= points;
        return zpoints;
    }

    public int removeStrenght(int points) {
        if (this.strength > 3) {
            this.strength -= points;
        } else {
            this.strength = 3;
        }
        return strength;
    }

    public int removeDefense(int points) {
        if (this.defense > 3) {
            this.defense -= points;
        } else {
            this.defense = 3;
        }
        return defense;
    }

    public int removeConstitution(int points) {

        if (this.constitution > 5) {
            this.constitution -= points;
        } else {
            this.constitution = 5;
        }
        return constitution;
    }

    public int removeKiPower(int points) {

        if (this.KiPower > 5) {
            this.KiPower -= points;
        } else {
            this.KiPower = 5;
        }
        return KiPower;
    }

    public int removeEnergy(int points) {

        if (this.energy > 10) {
            this.energy -= points;
        } else {
            this.energy = 10;
        }
        return energy;
    }

    public int removeStamina(int points) {

        if (this.stamina > 10) {
            this.stamina -= points;
        } else {
            this.stamina = 10;
        }
        return stamina;
    }

    public int getRace() {
        return races;
    }

    public void setRace(int races) {
        this.races = races;
        if (races > 6) {
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
        if (this.strength >= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.strength = DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.strength = strength;
        }
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;

        if (this.defense >= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.defense = DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.defense = defense;
        }
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;

        if (this.constitution >= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.constitution = DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.constitution = constitution;
        }
    }

    public int getKiPower() {
        return KiPower;
    }

    public void setKiPower(int kiPower) {
        this.KiPower = kiPower;

        if (this.KiPower >= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            KiPower = DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.KiPower = kiPower;
        }
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;

        if (this.energy >= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.energy = DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.energy = energy;
        }
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public void removeCurEnergy(int currentEnergy) {
        this.currentEnergy -= currentEnergy;

        if (this.currentEnergy < 0) {
            this.currentEnergy = 0;
        }
    }

    public int addCurEnergy(int currentEnergy) {

        if (this.currentEnergy < ((int) (energy * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get())) {
            this.currentEnergy += currentEnergy;
        } else {
            this.currentEnergy += 0;
        }

        return this.currentEnergy;
    }

    public int getCurBody() {
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

        if (this.curStam < 0) {
            this.curStam = 0;
        }
        return this.curStam;
    }

    public int addCurStam(int curStam) {
        if (this.curStam < (stamina + 3)) {
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
        if (this.stamina >= DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get()) {
            this.stamina = DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get();
        } else {
            this.stamina = stamina;
        }
    }


    @SuppressWarnings("DuplicatedCode")
    public void saveNBTData(CompoundTag nbt) {

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

        nbt.putInt("bodyColor", bodyColor);

        nbt.putInt("zpoints", zpoints);

    }

    @SuppressWarnings("DuplicatedCode")
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
        stamina = nbt.getInt("stamina");

        zpoints = nbt.getInt("zpoints");
        currentEnergy = nbt.getInt("currentEnergy");
        curBody = nbt.getInt("currentBody");
        curStam = nbt.getInt("currentStamina");

        bodyColor = nbt.getInt("bodyColor");

    }


}
