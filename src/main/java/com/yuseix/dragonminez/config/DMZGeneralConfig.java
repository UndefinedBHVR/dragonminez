package com.yuseix.dragonminez.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMZGeneralConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_ATTRIBUTE_VALUE;

    //CONFIGS GENERALES
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FALLDMG;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ZPOINTS_COST;

    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_GIVE;
    public static final ForgeConfigSpec.ConfigValue<Integer> SENZU_DAILY_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Double> KINTON_SPEED;



    static {
        BUILDER.push("Configs for Attributes of DragonMineZ");

        MAX_ATTRIBUTE_VALUE = BUILDER.comment("Max Attributes! (Min: 100 / Max: 1000)")
                .defineInRange("Attributes: ", 500, 100, 1000);

        BUILDER.pop();

        //ATRIBUTOS DE RAZA

        BUILDER.push("General Multipliers");

        MULTIPLIER_ZPOINTS_COST = BUILDER.comment("Multiplier for ZPoints Cost (Min: 1 / Max: 200)")
                .defineInRange("ZPoints: ", 1.2, 1.0, 20.0);

        MULTIPLIER_FALLDMG = BUILDER.comment("Fall Damage Multiplier Percentage (Min: 0.01 / Max: 1.00)")
                .defineInRange("FallDmg: ", 0.03, 0.01, 1.00);


        BUILDER.pop();


        BUILDER.push("General Configs");

        SENZU_COOLDOWN = BUILDER.comment("Cooldown for Senzu Beans")
                .defineInRange("Seconds: ", 10, 1, 200);

        SENZU_GIVE = BUILDER.comment("Number of Senzu Beans the Master Korin will give")
                .defineInRange("Number: ", 5, 1, 10);

        SENZU_DAILY_COOLDOWN = BUILDER.comment("Wait time to claim the next Senzu Bean (seconds)")
                .defineInRange("time: ", 600, 1, 36000);

        KINTON_SPEED = BUILDER.comment("Flying Nimbus speed (Min: 1.0 / Max: 30.0)")
                .defineInRange("Speed: ", 3.0, 1.0, 30.0);


        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
