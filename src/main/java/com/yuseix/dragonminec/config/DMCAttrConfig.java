package com.yuseix.dragonminec.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMCAttrConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_STR;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_DEF;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_CON;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_KIPOWER;
    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_ENERGY;


    public static final ForgeConfigSpec.ConfigValue<Integer> MULTIPLIER_ZPOINTS_COST;


    static {
        BUILDER.push("Configs for Attributes of DragonMineZ");

        MULTIPLIER_STR = BUILDER.comment("Multiplier for Strenght Attribute! (Min: 10 / Max: 200")
                .defineInRange("Strength: ", 5, 1, 200);

        MULTIPLIER_DEF = BUILDER.comment("Multiplier for Defense Attribute! (Min: 10 / Max: 200")
                .defineInRange("Defense: ", 5, 1, 200);

        MULTIPLIER_CON = BUILDER.comment("Multiplier for Constitution Attribute! (Min: 10 / Max: 200")
                .defineInRange("Constitution: ", 5, 1, 200);

        MULTIPLIER_KIPOWER = BUILDER.comment("Multiplier for KiPower Attribute! (Min: 10 / Max: 200")
                .defineInRange("KiPower: ", 5, 1, 200);

        MULTIPLIER_ENERGY = BUILDER.comment("Multiplier for Max KI Attribute! (Min: 10 / Max: 200")
                .defineInRange("Energy: ", 5, 1, 200);

        MULTIPLIER_ZPOINTS_COST = BUILDER.comment("Multiplier for ZPoints Cost (Min: 10 / Max: 200")
                .defineInRange("ZPoints: ", 5, 1, 200);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
