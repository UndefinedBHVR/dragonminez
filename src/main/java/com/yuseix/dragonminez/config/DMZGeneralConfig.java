package com.yuseix.dragonminez.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMZGeneralConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_ATTRIBUTE_VALUE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> OVERRIDE_MAX_ATTRIBUTES;

    //CONFIGS GENERALES
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FALLDMG;
    public static final ForgeConfigSpec.ConfigValue<Integer> PERHIT_ZPOINTS_GAIN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ZPOINTS_COST;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ZPOINTS_GAIN;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ZPOINTS_HTC;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_TREE_MIGHT;

    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_GIVE;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_DAILY_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_SHENRON_WISH;
    public static final ForgeConfigSpec.ConfigValue<Integer> CAPSULE_SHENRON_WISH;



    static {
        BUILDER.push("Configs for Attributes of DragonMineZ");

        MAX_ATTRIBUTE_VALUE = BUILDER.comment("Max Attributes! (Min: 100 / Max: 10000 / Default: 1000)")
                .defineInRange("Attributes: ", 1000, 100, 10000);

        OVERRIDE_MAX_ATTRIBUTES = BUILDER.comment("Allow Override Max Attributes! (Default: true)")
                .comment("If set to false, the max attributes will always be the same.")
                .comment("If set to true, the max attributes can be overriden with a Multiplier. (Ex: Transformations)")
                .define("Override: ", true);

        BUILDER.pop();

        //ATRIBUTOS DE RAZA

        BUILDER.push("ZPoints Configs");

        PERHIT_ZPOINTS_GAIN = BUILDER.comment("ZPoints Obtained per Hit (Min: 1 / Max: 100 / Default: 2)")
                .defineInRange("ZPoints per Hit: ", 2, 1, 100);

        MULTIPLIER_ZPOINTS_COST = BUILDER.comment("Multiplier for ZPoints Cost (Min: 1.0 / Max: 20.0 / Default: 1.2)")
                .defineInRange("ZPoints Cost: ", 1.2, 1.0, 20.0);

        MULTIPLIER_ZPOINTS_GAIN = BUILDER.comment("Multiplier for ZPoints Gain (Min: 1.0 / Max: 20.0 / Default: 1.2)")
                .defineInRange("ZPoints Gain: ", 1.2, 1.0, 20.0);

        MULTIPLIER_ZPOINTS_HTC = BUILDER.comment("Multiplier for ZPoints in the Hyperbolic Time Chamber (Min: 1.0 / Max: 20.0 / Default: 8.0)")
                .defineInRange("ZPoints (HTC): ", 8.0, 1.0, 60.0);


        BUILDER.pop();


        BUILDER.push("General Configs");

        MULTIPLIER_FALLDMG = BUILDER.comment("Fall Damage Multiplier Percentage (Min: 0.01 / Max: 1.00 / Default: 0.03)")
                .defineInRange("FallDmg: ", 0.03, 0.01, 1.00);

        SENZU_COOLDOWN = BUILDER.comment("Cooldown for Senzu Beans (Min: 1 / Max: 64 / Default: 10)")
                .defineInRange("Seconds: ", 10, 1, 64);

        SENZU_GIVE = BUILDER.comment("Number of Senzu Beans the Master Korin will give (Min: 1 / Max: 10 / Default: 5)")
                .defineInRange("Number: ", 5, 1, 10);

        SENZU_DAILY_COOLDOWN = BUILDER.comment("Wait time to claim the next Senzu Bean (seconds) (Min: 1 / Max: 36000 / Default: 300)")
                .defineInRange("Time: ", 300, 1, 36000);
        MULTIPLIER_TREE_MIGHT = BUILDER.comment("Multiplier for the Fruit of the Tree of Might (Min: 1.0 / Max: 20.0 / Default: 1.3)")
                .defineInRange("Value: ", 1.3, 1.0, 20.0);


        BUILDER.pop();

        BUILDER.push("Shenron / Porunga Wishes");

        SENZU_SHENRON_WISH = BUILDER.comment("Number of Senzu Beans Shenron will give (Min: 1 / Max: 64 / Default: 5)")
                .defineInRange("Number of Senzus: ", 5, 1, 64);

        CAPSULE_SHENRON_WISH = BUILDER.comment("Number of Capsules Shenron will give (Min: 1 / Max: 64 / Default: 3)")
                .defineInRange("Number of Capsules: ", 3, 1, 64);

        SPEC = BUILDER.build();
    }
}
