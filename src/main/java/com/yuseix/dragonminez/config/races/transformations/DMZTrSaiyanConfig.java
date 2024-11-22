package com.yuseix.dragonminez.config.races.transformations;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMZTrSaiyanConfig {

    //FORMA BASE
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_BASE;
    public static final ForgeConfigSpec.ConfigValue<Integer> BASE_FORM_KI_COST;

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {

        //Transformaciones

        BUILDER.comment(" SAIYAN FORMS ");

        BUILDER.push(" Base Form");

        MULTIPLIER_BASE = BUILDER.comment("Multiplier for Base Form! (Min: 1.0 / Max: 200.0 / Default: 1.0)")
                .defineInRange("Multiplier: ", 1.0, 1.0, 200.0);

        BASE_FORM_KI_COST = BUILDER.comment("Ki Cost for Base Form! (Min: 0 / Max: 20000 / Default: 0)")
                .defineInRange("Ki Cost: ", 0, 0, 20000);

        BUILDER.pop();


        SPEC = BUILDER.build();
    }
}
