package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class StatsEvents {

    private static int tickcounter = 0;
    private static int energiacounter = 0;

    private static int getEnergyRemovalThreshold(int playerLevel) {
        // Asumiendo que cada nivel te da 0.1 bloques de altura de soporte
        return (int) (0.1 * (playerLevel - 100) + 10);
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {

        //Regenerar stamina
        if (event.side == LogicalSide.SERVER) {

            energiacounter++;
            tickcounter++;

            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, event.player).ifPresent(playerstats -> {

                var vidaMC = 20;
                var con = playerstats.getConstitution();
                var raza = playerstats.getRace();
                var energia = playerstats.getEnergy();

                int maxstamina = 0;

                int maxenergia = 0;

                //ENERGIAAA
                if(raza == 0){
                    maxenergia = ( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY.get() + 40));
                    event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON.get()));
                    maxstamina = (int) Math.round( (vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON.get())) * 0.5);

                } else if(raza == 1){
                    maxenergia = ( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
                    event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double)con * DMCAttrConfig.MULTIPLIER_CON_SAIYAN.get()));
                    maxstamina = (int) Math.round( (vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get())) * 0.5);

                } else if(raza == 2){
                    maxenergia = ( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
                    event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_NAMEK.get()));
                    maxstamina = (int) Math.round( (vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_ENERGY_NAMEK.get())) * 0.5);

                } else if(raza == 3){
                    maxenergia = ( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
                    event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_BIO.get()));
                    maxstamina = (int) Math.round( (vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_ENERGY_BIO.get())) * 0.5);

                } else if(raza == 4){
                    maxenergia = ( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
                    event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_COLD.get()));
                    maxstamina = (int) Math.round( (vidaMC + ((double) con* DMCAttrConfig.MULTIPLIER_ENERGY_COLD.get())) * 0.5);

                } else if(raza == 5){
                    maxenergia = ( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
                    event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
                    maxstamina = (int) Math.round( (vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_ENERGY_MAJIN.get())) * 0.5);

                }

                if (playerstats.getCurStam() >= 0 && playerstats.getCurStam() <= maxstamina) {

                    if (tickcounter >= 60 * 3) {

                        int regenStamina = ((maxstamina) / 4);

                        playerstats.addCurStam(regenStamina);

                        tickcounter = 0;

                    }

                }
                if (playerstats.getCurrentEnergy() >= 0 && playerstats.getCurrentEnergy() <= maxenergia) {
                    if (energiacounter >= 60 * 5) {

                        int regenki = (Math.round(maxenergia / 10));

                        playerstats.addCurEnergy(regenki);
                        System.out.println("energia restaurada: " + regenki);

                        energiacounter = 0;
                    }
                }

            });

        }

}

    @SubscribeEvent
    public static void Recibirdano(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player)) {  //LA ENTIDAD QUE RECIBE EL GOLPE NO ES UN JUGADOR
            if (event.getSource().getEntity() instanceof Player jugadorpemrd) { //SI EL QUE HACE DANO ES UN JUGADOR

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, event.getSource().getEntity()).ifPresent(playerstats -> {

                    int raza = playerstats.getRace();

                    int staminacost, curstamina;

                    var danoDefault = event.getAmount();

                    switch (raza) {
                        case 0: //TODO: HUMANO

                            int maxstrHUMANO = calcularSTR("humano", playerstats.getStrength(),danoDefault);

                            staminacost = (maxstrHUMANO / 4);

                            curstamina = playerstats.getCurStam();

                            if (curstamina >= staminacost) {
                                //Aca es el dano total
                                event.setAmount(maxstrHUMANO);
                                //System.out.println("Dano total del Humano: " + maxstrHUMANO);
                                playerstats.removeCurStam(staminacost);
                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(danoDefault);
                                //System.out.println("Dano default del jugador(deberia ser 1 o 2 si es critico): " + danoDefault);
                            }

                            break;
                        case 1: //TODO: SAIYAN
                            int maxstrSaiyan = calcularSTR("saiyan", playerstats.getStrength(),danoDefault);

                            staminacost = (maxstrSaiyan / 4);

                            curstamina = playerstats.getCurStam();

                            if (curstamina >= staminacost) {
                                event.setAmount(maxstrSaiyan);
                                playerstats.removeCurStam(staminacost);
                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(danoDefault);
                            }

                            break;
                        case 2: //TODO: NAMEKIANO
                            int maxstrNamek = calcularSTR("namek", playerstats.getStrength(),danoDefault);
                            staminacost = (maxstrNamek / 4);

                            curstamina = playerstats.getCurStam();

                            if (curstamina >= staminacost) {
                                event.setAmount(maxstrNamek);
                                playerstats.removeCurStam(staminacost);
                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(1);
                            }

                            break;
                        case 3: //TODO: BIOANDROIDE
                            int maxstrBioAndroide = calcularSTR("bio", playerstats.getStrength(), danoDefault);

                            staminacost = (maxstrBioAndroide / 4);

                            curstamina = playerstats.getCurStam();

                            if (curstamina >= staminacost) {
                                event.setAmount(maxstrBioAndroide);
                                playerstats.removeCurStam(staminacost);
                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(1);
                            }

                            break;
                        case 4: //TODO: COLDDEMON
                            int maxstrColdDemon = calcularSTR("colddemon", playerstats.getStrength(), danoDefault);
                            staminacost = (maxstrColdDemon / 4);

                            curstamina = playerstats.getCurStam();

                            if (curstamina >= staminacost) {
                                event.setAmount(maxstrColdDemon);
                                playerstats.removeCurStam(staminacost);
                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(1);
                            }

                            break;
                        case 5: //TODO: MAJIN
                            int maxstrMajin = calcularSTR("majin", playerstats.getStrength(),danoDefault);
                            staminacost = (maxstrMajin / 4);

                            curstamina = playerstats.getCurStam();

                            if (curstamina >= staminacost) {
                                event.setAmount(maxstrMajin);
                                playerstats.removeCurStam(staminacost);
                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(1);
                            }
                            break;

                        default: //TODO: FUTURAS RAZAS

                            break;

                    }

                });

                sonidosGolpes(jugadorpemrd);
            }
        }

        if (event.getEntity() instanceof Player) { //SI LA ENTIDAD QUE RECIBE UN GOLPE ES UN JUGADOR

            if (!(event.getSource().getEntity() instanceof Player)) { //SI LA ENTIDAD QUE HACE DANO NO ES UN JUGADOR
                var danoDefault = event.getAmount();

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, event.getEntity()).ifPresent(playerstats -> {
                    int raza = playerstats.getRace();

                    switch (raza) {
                        case 0: //RAZA HUMANO

                            int defensaHumano = calcularDEF("humano", playerstats.getDefense());
                            event.setAmount(event.getAmount() - defensaHumano);

                            break;
                        case 1: //RAZA SAIYAN

                            int defensaSaiyan = calcularDEF("saiyan", playerstats.getDefense());
                            event.setAmount(event.getAmount() - defensaSaiyan);

                            break;
                        case 2: //RAZA NAMEK

                            int defensaNamek = calcularDEF("humano", playerstats.getDefense());
                            event.setAmount(event.getAmount() - defensaNamek);

                            break;
                        case 3: //RAZA BIOANDROIDE

                            int defensaBioAndroide = calcularDEF("humano", playerstats.getDefense());
                            event.setAmount(event.getAmount() - defensaBioAndroide);

                            break;
                        case 4: //RAZA COLD DEMON

                            int defensaColdDemon = calcularDEF("humano", playerstats.getDefense());
                            event.setAmount(event.getAmount() - defensaColdDemon);

                            break;
                        case 5: //RAZA MAJIN

                            int defensaMajin = calcularDEF("humano", playerstats.getDefense());
                            event.setAmount(event.getAmount() - defensaMajin);

                            break;
                        default:

                            break;
                    }


                });
            }

            if ((event.getSource().getEntity() instanceof Player jugadorpemrd)) { //SI LA ENTIDAD QUE HACE DANO ES UN JUGADOR
                var danoDefault = event.getAmount();

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, event.getEntity()).ifPresent(playerstats -> {

                    int razas = playerstats.getRace();

                    int maxstr, maxdef;
                    int staminacost;
                    int curstamina = playerstats.getCurStam();
                    switch (razas) {
                        case 0: //RAZA HUMANO

                            maxstr = calcularSTR("humano", playerstats.getStrength(), danoDefault);
                            maxdef = calcularDEF("humano", playerstats.getDefense());

                            staminacost = (maxstr / 4);

                            if (curstamina >= staminacost) {

                                event.setAmount(event.getAmount() - maxdef);
                                playerstats.removeCurStam(staminacost);

                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(danoDefault);
                            }

                            break;
                        case 1: //RAZA SAIYAN

                            maxstr = calcularSTR("saiyan", playerstats.getStrength(), danoDefault);
                            maxdef = calcularDEF("saiyan", playerstats.getDefense());
                            staminacost = (maxstr / 4);

                            if (curstamina >= staminacost) {

                                event.setAmount(event.getAmount() - maxdef);
                                playerstats.removeCurStam(staminacost);

                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(danoDefault);
                            }

                            break;
                        case 2: //RAZA NAMEK

                            maxstr = calcularSTR("humano", playerstats.getStrength(), danoDefault);
                            maxdef = calcularDEF("humano", playerstats.getDefense());

                            staminacost = (maxstr / 4);

                            if (curstamina >= staminacost) {

                                event.setAmount(event.getAmount() - maxdef);
                                playerstats.removeCurStam(staminacost);

                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(danoDefault);
                            }

                            break;
                        case 3: //RAZA BIOANDROIDE

                            maxstr = calcularSTR("humano", playerstats.getStrength(), danoDefault);
                            maxdef = calcularDEF("humano", playerstats.getDefense());

                            staminacost = (maxstr / 4);

                            if (curstamina >= staminacost) {

                                event.setAmount(event.getAmount() - maxdef);
                                playerstats.removeCurStam(staminacost);

                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(danoDefault);
                            }

                            break;
                        case 4: //RAZA COLD DEMON

                            maxstr = calcularSTR("humano", playerstats.getStrength(), danoDefault);
                            maxdef = calcularDEF("humano", playerstats.getDefense());

                            staminacost = (maxstr / 4);

                            if (curstamina >= staminacost) {

                                event.setAmount(event.getAmount() - maxdef);
                                playerstats.removeCurStam(staminacost);

                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(danoDefault);
                            }

                            break;
                        case 5: //RAZA MAJIN

                            maxstr = calcularSTR("humano", playerstats.getStrength(), danoDefault);
                            maxdef = calcularDEF("humano", playerstats.getDefense());

                            staminacost = (maxstr / 4);

                            if (curstamina >= staminacost) {

                                event.setAmount(event.getAmount() - maxdef);
                                playerstats.removeCurStam(staminacost);

                            }
                            if (staminacost >= curstamina) {
                                event.setAmount(danoDefault);
                            }

                            break;

                        default: //futuras razas

                            break;
                    }


                });

                sonidosGolpes(jugadorpemrd);
            }
        }
    }


    private static int calcularSTR(String raza, int StatSTR, float danoJugador) {

            double maxStr = 0;

            switch (raza) {
                case "humano", "Humano", "h":
                     maxStr = ((danoJugador + ((double) StatSTR /10)) * DMCAttrConfig.MULTIPLIER_STR.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();

                    break;

                case "saiyan", "Saiyan", "s":
                        maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR_SAIYAN.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();

                    break;
                case "namek", "Namek", "n":
                        maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR_NAMEK.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();

                    break;
                case "bioandroide", "bio", "b":
                        maxStr = ((danoJugador + ((double) StatSTR / 10)) * DMCAttrConfig.MULTIPLIER_STR_BIO.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();

                    break;
                case "colddemon", "cold", "c":

                    maxStr = ((danoJugador + ((double) StatSTR/10)) * DMCAttrConfig.MULTIPLIER_STR_COLD.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();

                    break;
                case "majin", "Majin", "m":

                    maxStr = ((danoJugador + ((double) StatSTR/10)) * DMCAttrConfig.MULTIPLIER_STR_MAJIN.get()) * DMCAttrConfig.MULTIPLIER_WARRIOR.get();

                    break;
            }

        //Fórmula = ((StatSTR * ConfigRaza) * Transf) * Porcentaje


        return (int) maxStr;
    }

    private static int calcularDEF(String raza, int StatDEF) {

        Player player = Minecraft.getInstance().player;

        double maxDef = 0;

        int DefensaArmor = player.getArmorValue();
        int DurezaArmor = Mth.floor(player.getAttributeValue(Attributes.ARMOR_TOUGHNESS));

        //Defensa = (((((StatDEF * ConfigRaza) * Transf) * Porcentaje) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4))) / 2.25)
        switch (raza) {
            case "humano", "Humano", "h":

                maxDef = ((StatDEF/4) * DMCAttrConfig.MULTIPLIER_DEF.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;

                break;
            case "saiyan", "Saiyan", "s":

                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_SAIYAN.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;

                break;
            case "namek", "Namek", "n":

                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_NAMEK.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;

                break;
            case "BioAndroide", "Bio", "b":

                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_BIO.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;

                break;
            case "ColdDemon", "Cold", "c":

                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_BIO.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;

                break;
            case "Majin", "majin", "M":

                maxDef = (StatDEF * DMCAttrConfig.MULTIPLIER_DEF_MAJIN.get()) + ((DefensaArmor / 5) + (DefensaArmor - DurezaArmor / 4)) / 2.25;

                break;
        }

        return (int) maxDef;
    }


    @SubscribeEvent
    public static void livingFallEvent(LivingFallEvent event) {
        float realDistance = event.getDistance();

        if (event.getEntity() instanceof ServerPlayer player) {
            if (realDistance > 4.5f) {

                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
                    int level = (stats.getStrength() +
                            stats.getDefense() +
                            stats.getConstitution() +
                            stats.getKiPower() +
                            stats.getEnergy()) / 5;

                    double energyToRemove = getEnergyToRemove(level);

                    // Checar si la distancia de caída es menor al soporte que puedes tener por x nivel
                    if ((int) realDistance <= getEnergyRemovalThreshold(level) /* && stats.getCurrentEnergy() >= energyToRemove */) {
                        stats.removeCurEnergy((int) Math.round(energyToRemove));
                        event.setCanceled(true);
                    } //else hacer algo si te haces más daño de ki del que puedes soportar
                });
            }
        }

    }

    private static double getEnergyToRemove(int level) {
        double energyRemovalValue;

        double baseReduction = DMCAttrConfig.MULTIPLIER_FALLDMG.get();

        if (level >= 100) {
            // Porcentaje calculado en base a la config (Default 0.03)
            energyRemovalValue = level * baseReduction;

        } else {
            energyRemovalValue = 0; // Niveles menores a 100 no reciben daño de ki
        }

        // Devuelve el valor actual de energía sacada
        return energyRemovalValue;
    }


    private static void sonidosGolpes(Player player) {

        SoundEvent[] golpeSounds = {
                MainSounds.GOLPE1.get(),
                MainSounds.GOLPE2.get(),
                MainSounds.GOLPE3.get(),
                MainSounds.GOLPE4.get(),
                MainSounds.GOLPE5.get(),
                MainSounds.GOLPE6.get()
        };

        Random rand = new Random();
        int randomIndex = rand.nextInt(golpeSounds.length);
        SoundEvent randomGolpeSound = golpeSounds[randomIndex];

        player.level().playSound(null, player.getOnPos(), randomGolpeSound, SoundSource.PLAYERS, 2.2F, 0.9F);

    }

}



