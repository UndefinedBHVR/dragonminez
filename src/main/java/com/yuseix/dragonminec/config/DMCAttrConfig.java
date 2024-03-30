package com.yuseix.dragonminec.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMCAttrConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_ATTRIBUTE_VALUE;

    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_STR;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_DEF;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_CON;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_KIPOWER;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_ENERGY;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FALLDMG;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_ZPOINTS_COST;


    static {
        BUILDER.push("Configs for Attributes of DragonMineZ");

        MAX_ATTRIBUTE_VALUE = BUILDER.comment("Max Attributes! (Min: 10 / Max: 1000")
                .defineInRange("Attributes: ", 500, 10, 1000);


        MULTIPLIER_STR = BUILDER.comment("Multiplier for Strength Attribute! (Min: 3 / Max: 200")
                .defineInRange("Strength: ", 5, 3, 200);

        MULTIPLIER_DEF = BUILDER.comment("Multiplier for Defense Attribute! (Min: 3 / Max: 200")
                .defineInRange("Defense: ", 5, 3, 200);

        MULTIPLIER_CON = BUILDER.comment("Multiplier for Constitution Attribute! (Min: 3 / Max: 200")
                .defineInRange("Constitution: ", 5, 3, 200);

        MULTIPLIER_KIPOWER = BUILDER.comment("Multiplier for KiPower Attribute! (Min: 3 / Max: 200")
                .defineInRange("KiPower: ", 5, 3, 200);

        MULTIPLIER_ENERGY = BUILDER.comment("Multiplier for Max KI Attribute! (Min: 3 / Max: 200")
                .defineInRange("Energy: ", 5, 3, 200);

        MULTIPLIER_ZPOINTS_COST = BUILDER.comment("Multiplier for ZPoints Cost (Min: 3 / Max: 200")
                .defineInRange("ZPoints: ", 5, 3, 200);

        MULTIPLIER_FALLDMG = BUILDER.comment("Fall Damage Multiplier Percentage (Min: 0.01 / Max: 1.00)")
                .defineInRange("FallDmg: ", 0.03, 0.01, 1.00);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
