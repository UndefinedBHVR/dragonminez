package com.yuseix.dragonminez.utils;

import com.yuseix.dragonminez.config.races.*;

public class DMZDatos2 implements IDMZDatos{
    @Override
    public int calcularENE(int raza, int StatENE, String clase) {
        double maxENE = 0;

        switch (clase){
            case "Warrior":
                switch (raza) {
                    case 0: // Humano
                        maxENE = Math.round(StatENE * DMZHumanConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;
                    case 1: // Saiyan
                        maxENE = Math.round(StatENE * DMZSaiyanConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 2: // Namek
                        maxENE = Math.round(StatENE * DMZNamekConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 3: // Bioandroide
                        maxENE = Math.round(StatENE * DMZBioAndroidConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 4: // Cold Demon
                        maxENE = Math.round(StatENE * DMZColdDemonConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    case 5: // Majin
                        maxENE = Math.round(StatENE * DMZMajinConfig.MULTIPLIER_ENERGY_WARRIOR.get() + 40);
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            case "Spiritualist":
                switch (raza) {
                    case 0: // Humano
                        maxENE = Math.round(StatENE * DMZHumanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;
                    case 1: // Saiyan
                        maxENE = Math.round(StatENE * DMZSaiyanConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 2: // Namek
                        maxENE = Math.round(StatENE * DMZNamekConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 3: // Bioandroide
                        maxENE = Math.round(StatENE * DMZBioAndroidConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 4: // Cold Demon
                        maxENE = Math.round(StatENE * DMZColdDemonConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    case 5: // Majin
                        maxENE = Math.round(StatENE * DMZMajinConfig.MULTIPLIER_ENERGY_SPIRITUALIST.get() + 40);
                        break;

                    default:
                        // Manejar el caso en que la raza no sea válida
                        break;
                }
                break;
            default:
                break;
        }


        return (int) maxENE;
    }
}
