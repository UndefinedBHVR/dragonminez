package com.yuseix.dragonminez.config.races;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMZBioAndroidConfig {

    //WARRIOR
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_STR_WARRIOR;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_DEF_WARRIOR;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_CON_WARRIOR;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_KIPOWER_WARRIOR;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ENERGY_WARRIOR;

    //SPIRITUALIST
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_STR_SPIRITUALIST;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_DEF_SPIRITUALIST;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_CON_SPIRITUALIST;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_KIPOWER_SPIRITUALIST;
    public static final ForgeConfigSpec.ConfigValue<Double> MULTIPLIER_ENERGY_SPIRITUALIST;



    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {

        BUILDER.comment(" DRAGONMINEZ - BIOANDROID CLASS CONFIG ");

        //GUERRERO
        BUILDER.push(" WARRIOR CLASS ");

        MULTIPLIER_STR_WARRIOR = BUILDER.comment("Multiplier for Strenght Warrior Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength: ", 1.2, 1.0, 200.0);

        MULTIPLIER_DEF_WARRIOR = BUILDER.comment("Multiplier for Defense Warrior Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense: ", 1.2, 1.0, 200.0);

        MULTIPLIER_CON_WARRIOR = BUILDER.comment("Multiplier for Constitution Warrior Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Constitution: ", 1.2, 1.0, 200.0);

        MULTIPLIER_KIPOWER_WARRIOR = BUILDER.comment("Multiplier for KiPower Warrior Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower: ", 1.2, 1.0, 200.0);

        MULTIPLIER_ENERGY_WARRIOR = BUILDER.comment("Multiplier for Max KI Warrior Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Energy: ", 1.2, 1.0, 200.0);

        BUILDER.pop();

        //ESPIRITUALISTA
        BUILDER.push(" SPIRITUALIST CLASS ");

        MULTIPLIER_STR_SPIRITUALIST = BUILDER.comment("Multiplier for Strenght Spiritualist Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Strength: ", 1.2, 1.0, 200.0);

        MULTIPLIER_DEF_SPIRITUALIST = BUILDER.comment("Multiplier for Defense Spiritualist Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Defense: ", 1.2, 1.0, 200.0);

        MULTIPLIER_CON_SPIRITUALIST = BUILDER.comment("Multiplier for Constitution Spiritualist Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Constitution: ", 1.2, 1.0, 200.0);

        MULTIPLIER_KIPOWER_SPIRITUALIST = BUILDER.comment("Multiplier for KiPower Spiritualist Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("KiPower: ", 1.2, 1.0, 200.0);

        MULTIPLIER_ENERGY_SPIRITUALIST = BUILDER.comment("Multiplier for Max KI Spiritualist Attribute! (Min: 1.0 / Max: 200.0)")
                .defineInRange("Energy: ", 1.2, 1.0, 200.0);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

}
