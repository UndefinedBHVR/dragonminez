package com.yuseix.dragonminez.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMCAttrConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_ATTRIBUTE_VALUE;


    //0 = RAZA HUMANO
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_STR;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_DEF;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_CON;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_KIPOWER;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_ENERGY;

    //1 = RAZA SAIYAJIN
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_STR_SAIYAN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_DEF_SAIYAN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_CON_SAIYAN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_KIPOWER_SAIYAN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_ENERGY_SAIYAN;

    //2 = RAZA NAMEKIANO

    //3 = RAZA BIOANDROIDE

    //4 = RAZA COLDDEMON

    //5 = RAZA MAJIN


    //CONFIGS GENERALES
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FALLDMG;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_ZPOINTS_COST;


    static {
        BUILDER.push("Configs for Attributes of DragonMineZ");

        MAX_ATTRIBUTE_VALUE = BUILDER.comment("Max Attributes! (Min: 100 / Max: 1000)")
                .defineInRange("Attributes: ", 500, 100, 1000);


        BUILDER.push("<- RACES ATTRIBUTES ->");

        BUILDER.push("Human Race -> ");

        MULTIPLIER_STR = BUILDER.comment("Multiplier for Strength Human Attribute! (Min: 3 / Max: 200)")
                .defineInRange("Strength: ", 5, 3, 200);

        MULTIPLIER_DEF = BUILDER.comment("Multiplier for Defense Human Attribute! (Min: 3 / Max: 200)")
                .defineInRange("Defense: ", 5, 3, 200);

        MULTIPLIER_CON = BUILDER.comment("Multiplier for Constitution Human Attribute! (Min: 3 / Max: 200)")
                .defineInRange("Constitution: ", 5, 3, 200);

        MULTIPLIER_KIPOWER = BUILDER.comment("Multiplier for KiPower Human Attribute! (Min: 3 / Max: 200)")
                .defineInRange("KiPower: ", 5, 3, 200);

        MULTIPLIER_ENERGY = BUILDER.comment("Multiplier for Max KI Human Attribute! (Min: 3 / Max: 200)")
                .defineInRange("Energy: ", 5, 3, 200);

        BUILDER.push("Saiyan Race -> ");

        MULTIPLIER_STR_SAIYAN = BUILDER.comment("Multiplier for Strength Saiyan Attribute! (Min: 3 / Max: 200)")
                .defineInRange("Strength: ", 5, 3, 200);

        MULTIPLIER_DEF_SAIYAN = BUILDER.comment("Multiplier for Defense Saiyan Attribute! (Min: 3 / Max: 200)")
                .defineInRange("Defense: ", 5, 3, 200);

        MULTIPLIER_CON_SAIYAN = BUILDER.comment("Multiplier for Constitution Saiyan Attribute! (Min: 3 / Max: 200)")
                .defineInRange("Constitution: ", 5, 3, 200);

        MULTIPLIER_KIPOWER_SAIYAN = BUILDER.comment("Multiplier for KiPower Saiyan Attribute! (Min: 3 / Max: 200)")
                .defineInRange("KiPower: ", 5, 3, 200);

        MULTIPLIER_ENERGY_SAIYAN = BUILDER.comment("Multiplier for Max KI Saiyan Attribute! (Min: 3 / Max: 200)")
                .defineInRange("Energy: ", 5, 3, 200);


        MULTIPLIER_ZPOINTS_COST = BUILDER.comment("Multiplier for ZPoints Cost (Min: 3 / Max: 200)")
                .defineInRange("ZPoints: ", 5, 3, 200);

        MULTIPLIER_FALLDMG = BUILDER.comment("Fall Damage Multiplier Percentage (Min: 0.01 / Max: 1.00)")
                .defineInRange("FallDmg: ", 0.03, 0.01, 1.00);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
