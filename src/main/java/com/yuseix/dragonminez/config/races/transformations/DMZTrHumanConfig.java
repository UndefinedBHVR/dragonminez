package com.yuseix.dragonminez.config.races.transformations;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMZTrHumanConfig {

    //FORMA BASE
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_BASE;
    public static final ForgeConfigSpec.ConfigValue<Integer> BASE_FORM_KI_COST;
    //FORMA FULL POWER
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FP_FORM_STR;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FP_FORM_DEF;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FP_FORM_KIPOWER;
    public static final ForgeConfigSpec.ConfigValue<Integer> FP_FORM_KI_COST;


    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {

        //Transformaciones

        BUILDER.comment(" HUMAN FORMS ");

        BUILDER.push(" Base Form");

        MULTIPLIER_BASE = BUILDER.comment("Multiplier for Base Form! (Min: 1.0 / Max: 200.0 / Default: 1.0)")
                .defineInRange("Multiplier: ", 1.0, 1.0, 200.0);

        BASE_FORM_KI_COST = BUILDER.comment("Ki Cost for Base Form! (Min: 0 / Max: 20000 / Default: 0)")
                .defineInRange("Ki Cost: ", 0, 0, 20000);

        BUILDER.pop();

        BUILDER.push(" Full Power Form");

        MULTIPLIER_FP_FORM_STR = BUILDER.comment("Multiplier for FullPower Form! (Min: 1.0 / Max: 200.0 / Default: 1.0)")
                .defineInRange("Strength Multiplier: ", 2.0, 1.0, 200.0);

        MULTIPLIER_FP_FORM_DEF = BUILDER.comment("Multiplier for FullPower Form! (Min: 1.0 / Max: 200.0 / Default: 1.0)")
                .defineInRange("Defense Multiplier: ", 2.0, 1.0, 200.0);

        MULTIPLIER_FP_FORM_KIPOWER = BUILDER.comment("Multiplier for FullPower Form! (Min: 1.0 / Max: 200.0 / Default: 1.0)")
                .defineInRange("KiPower Multiplier: ", 2.0, 1.0, 200.0);

        FP_FORM_KI_COST = BUILDER.comment("Ki Cost for FullPower Form! (Min: 0 / Max: 20000 / Default: 200)")
                .defineInRange("Ki Cost: ", 200, 0, 20000);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
