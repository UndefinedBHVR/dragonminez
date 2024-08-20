package com.yuseix.dragonminez.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMCAttrConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_ATTRIBUTE_VALUE;


    //0 = RAZA HUMANO
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_STR;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_DEF;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_CON;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_KIPOWER;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ENERGY;

    //1 = RAZA SAIYAJIN
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_STR_SAIYAN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_DEF_SAIYAN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_CON_SAIYAN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_KIPOWER_SAIYAN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ENERGY_SAIYAN;

    //2 = RAZA NAMEKIANO
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_STR_NAMEK;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_DEF_NAMEK;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_CON_NAMEK;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_KIPOWER_NAMEK;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ENERGY_NAMEK;

    //3 = RAZA BIOANDROIDE
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_STR_BIO;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_DEF_BIO;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_CON_BIO;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_KIPOWER_BIO;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ENERGY_BIO;
    //4 = RAZA COLDDEMON
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_STR_COLD;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_DEF_COLD;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_CON_COLD;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_KIPOWER_COLD;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ENERGY_COLD;
    //5 = RAZA MAJIN
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_STR_MAJIN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_DEF_MAJIN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_CON_MAJIN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_KIPOWER_MAJIN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ENERGY_MAJIN;

    //CONFIGS GENERALES
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FALLDMG;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ZPOINTS_COST;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_SPIRITUALIST;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_WARRIOR;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_COOLDOWN;

    static {
        BUILDER.push("Configs for Attributes of DragonMineZ");

        MAX_ATTRIBUTE_VALUE = BUILDER.comment("Max Attributes! (Min: 100 / Max: 1000)")
                .defineInRange("Attributes: ", 500, 100, 1000);

        //ATRIBUTOS DE RAZA

        BUILDER.push("<- RACES ATTRIBUTES ->");

        BUILDER.push("Human Race -> ");

        MULTIPLIER_STR = BUILDER.comment("Multiplier for Strenght Human Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength: ", 1.2, 1.0, 200.0);

        MULTIPLIER_DEF = BUILDER.comment("Multiplier for Defense Human Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense: ", 1.2, 1.0, 200.0);

        MULTIPLIER_CON = BUILDER.comment("Multiplier for Constitution Human Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Constitution: ", 1.2, 1.0, 200.0);

        MULTIPLIER_KIPOWER = BUILDER.comment("Multiplier for KiPower Human Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower: ", 1.2, 1.0, 200.0);

        MULTIPLIER_ENERGY = BUILDER.comment("Multiplier for Max KI Human Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Energy: ", 1.2, 1.0, 200.0);

        BUILDER.push("Saiyan Race -> ");

        MULTIPLIER_STR_SAIYAN = BUILDER.comment("Multiplier for Strenght Saiyan Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength: ", 1.2, 1.0, 200.0);

        MULTIPLIER_DEF_SAIYAN = BUILDER.comment("Multiplier for Defense Saiyan Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense: ", 1.2, 1.0, 200.0);

        MULTIPLIER_CON_SAIYAN = BUILDER.comment("Multiplier for Constitution Saiyan Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Constitution: ", 1.2, 1.0, 200.0);

        MULTIPLIER_KIPOWER_SAIYAN = BUILDER.comment("Multiplier for KiPower Saiyan Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower: ", 1.2, 1.0, 200.0);

        MULTIPLIER_ENERGY_SAIYAN = BUILDER.comment("Multiplier for Max KI Saiyan Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Energy: ", 1.2, 1.0, 200.0);

        BUILDER.push("Namek Race -> ");

        MULTIPLIER_STR_NAMEK = BUILDER.comment("Multiplier for Strenght Namek Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength: ", 1.2, 1.0, 200.0);

        MULTIPLIER_DEF_NAMEK = BUILDER.comment("Multiplier for Defense Namek Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense: ", 1.2, 1.0, 200.0);

        MULTIPLIER_CON_NAMEK = BUILDER.comment("Multiplier for Constitution Namek Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Constitution: ", 1.2, 1.0, 200.0);

        MULTIPLIER_KIPOWER_NAMEK = BUILDER.comment("Multiplier for KiPower Namek Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower: ", 1.2, 1.0, 200.0);

        MULTIPLIER_ENERGY_NAMEK = BUILDER.comment("Multiplier for Max KI Namek Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Energy: ", 1.2, 1.0, 200.0);

        BUILDER.push("BioAndroid Race -> ");

        MULTIPLIER_STR_BIO = BUILDER.comment("Multiplier for Strenght BioAndroid Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength: ", 1.2, 1.0, 200.0);

        MULTIPLIER_DEF_BIO = BUILDER.comment("Multiplier for Defense BioAndroid Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense: ", 1.2, 1.0, 200.0);

        MULTIPLIER_CON_BIO = BUILDER.comment("Multiplier for Constitution BioAndroid Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Constitution: ", 1.2, 1.0, 200.0);

        MULTIPLIER_KIPOWER_BIO = BUILDER.comment("Multiplier for KiPower BioAndroid Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower: ", 1.2, 1.0, 200.0);

        MULTIPLIER_ENERGY_BIO = BUILDER.comment("Multiplier for Max KI BioAndroid Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Energy: ", 1.2, 1.0, 200.0);

        BUILDER.push("ColdDemon Race -> ");

        MULTIPLIER_STR_COLD = BUILDER.comment("Multiplier for Strenght ColdDemon Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength: ", 1.2, 1.0, 200.0);

        MULTIPLIER_DEF_COLD = BUILDER.comment("Multiplier for Defense ColdDemon Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense: ", 1.2, 1.0, 200.0);

        MULTIPLIER_CON_COLD = BUILDER.comment("Multiplier for Constitution ColdDemon Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Constitution: ", 1.2, 1.0, 200.0);

        MULTIPLIER_KIPOWER_COLD = BUILDER.comment("Multiplier for KiPower ColdDemon Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower: ", 1.2, 1.0, 200.0);

        MULTIPLIER_ENERGY_COLD = BUILDER.comment("Multiplier for Max KI ColdDemon Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Energy: ", 1.2, 1.0, 200.0);

        BUILDER.push("Majin Race -> ");

        MULTIPLIER_STR_MAJIN = BUILDER.comment("Multiplier for Strenght Majin Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength: ", 1.2, 1.0, 200.0);

        MULTIPLIER_DEF_MAJIN = BUILDER.comment("Multiplier for Defense Majin Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense: ", 1.2, 1.0, 200.0);

        MULTIPLIER_CON_MAJIN = BUILDER.comment("Multiplier for Constitution Majin Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Constitution: ", 1.2, 1.0, 200.0);

        MULTIPLIER_KIPOWER_MAJIN = BUILDER.comment("Multiplier for KiPower Majin Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower: ", 1.2, 1.0, 200.0);

        MULTIPLIER_ENERGY_MAJIN = BUILDER.comment("Multiplier for Max KI Majin Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Energy: ", 1.2, 1.0, 200.0);

        //CLASES DEL PERSONAJE

        MULTIPLIER_SPIRITUALIST = BUILDER.comment("Multiplier for Spiritualist Class! (Min: 0.5 / Max: 200)")
                .defineInRange("MULTIPLIER: ", 1.1, 0.5, 200.0);

        MULTIPLIER_WARRIOR = BUILDER.comment("Multiplier for Warrior Class! (Min: 0.5 / Max: 200)")
                .defineInRange("MULTIPLIER: ", 1.1, 0.5, 200.0);

        //MULTIPLICADORES GENERALES

        MULTIPLIER_ZPOINTS_COST = BUILDER.comment("Multiplier for ZPoints Cost (Min: 3 / Max: 200)")
                .defineInRange("ZPoints: ", 1.2, 1.0, 20.0);

        MULTIPLIER_FALLDMG = BUILDER.comment("Fall Damage Multiplier Percentage (Min: 0.01 / Max: 1.00)")
                .defineInRange("FallDmg: ", 0.03, 0.01, 1.00);

        SENZU_COOLDOWN = BUILDER.comment("Cooldown for Senzu Beans")
                        .define("Seconds: ", 10);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
