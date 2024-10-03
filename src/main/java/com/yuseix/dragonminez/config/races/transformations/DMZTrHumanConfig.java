package com.yuseix.dragonminez.config.races.transformations;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMZTrHumanConfig {

    //FORMA BASE
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_BASE;
    //FORMA FULL POWER
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FP_FORM_STR;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FP_FORM_DEF;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_FP_FORM_KIPOWER;


    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {

        //Transformaciones

        BUILDER.comment(" HUMAN FORMS ");

        BUILDER.push(" Base Form");

        MULTIPLIER_BASE = BUILDER.comment("Multiplier for Base Form! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Multiplier: ", 1.0, 1.0, 200.0);

        BUILDER.pop();

        BUILDER.push(" Full Power Form");

        MULTIPLIER_FP_FORM_STR = BUILDER.comment("Multiplier for FullPower Form! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength Multiplier: ", 2.0, 1.0, 200.0);

        MULTIPLIER_FP_FORM_DEF = BUILDER.comment("Multiplier for FullPower Form! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense Multiplier: ", 2.0, 1.0, 200.0);

        MULTIPLIER_FP_FORM_KIPOWER = BUILDER.comment("Multiplier for FullPower Form! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower Multiplier: ", 2.0, 1.0, 200.0);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
