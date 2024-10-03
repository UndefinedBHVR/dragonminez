package com.yuseix.dragonminez.config.races.transformations;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMZTrMajinConfig {

    //FORMA BASE
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_BASE;

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {

        //Transformaciones

        BUILDER.comment(" MAJIN FORMS ");

        BUILDER.push(" Base Form");

        MULTIPLIER_BASE = BUILDER.comment("Multiplier for Base Form! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Multiplier: ", 1.0, 1.0, 200.0);

        BUILDER.pop();


        SPEC = BUILDER.build();
    }
}
