package com.yuseix.dragonminec.events;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.init.MainSounds;
import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineC.MODID)
public class StatsEvents {

    private static int tickcounter = 0;

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event){


        //Regenerar stamina
        if(event.side == LogicalSide.SERVER){

            tickcounter++;

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.player).ifPresent(playerstats -> {

                int maxcon = (int) (playerstats.getConstitution() * 0.5) * DMCAttrConfig.MULTIPLIER_CON.get();
                int maxstamina = (playerstats.getStamina() + 3);


                if(playerstats.getCurStam() >= 0 && playerstats.getCurStam() <= maxstamina ){

                    if(tickcounter >= 60 * 3){

                        int regenStamina = ((maxstamina) / 4);

                        playerstats.addCurStam(regenStamina);

                        tickcounter = 0;
                    }

                }

                event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue((playerstats.getConstitution() *0.5)* DMCAttrConfig.MULTIPLIER_CON.get());

            });

        }


    }

    @SubscribeEvent
    public static void Recibirdano(LivingHurtEvent event){
        if(!(event.getEntity() instanceof Player)){  //LA ENTIDAD QUE RECIBE EL GOLPE NO ES UN JUGADOR
            if(event.getSource().getEntity() instanceof Player){ //SI EL QUE HACE DANO ES UN JUGADOR

                Player jugadorpemrd = (Player) event.getSource().getEntity();

                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getSource().getEntity()).ifPresent(playerstats -> {

                    int maxstr = (int) (playerstats.getStrength()*0.5)*DMCAttrConfig.MULTIPLIER_STR.get();

                    int staminacost = (maxstr / 4);

                    int curstamina = playerstats.getCurStam();

                    if(curstamina >= staminacost){
                        event.setAmount(maxstr);
                        playerstats.removeCurStam(staminacost);
                       }
                    if(staminacost >= curstamina){
                        event.setAmount(1);
                    }
                });

                    int id = (int) (Math.random()*6+1);
                    sonidosGolpes(jugadorpemrd,id);
            }
        }

        if(event.getEntity() instanceof Player){ //SI LA ENTIDAD QUE RECIBE UN GOLPE ES UN JUGADOR

            if(!(event.getSource().getEntity() instanceof Player)){ //SI LA ENTIDAD QUE HACE DANO NO ES UN JUGADOR

                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getEntity()).ifPresent(playerstats -> {

                    event.setAmount(event.getAmount() - (int) (((playerstats.getDefense() * 0.5)*DMCAttrConfig.MULTIPLIER_DEF.get())/2));

                });

            }

            if((event.getSource().getEntity() instanceof Player)){ //SI LA ENTIDAD QUE HACE DANO ES UN JUGADOR

                Player jugadorpemrd = (Player) event.getSource().getEntity();

                PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getEntity()).ifPresent(playerstats -> {

                    int maxstr = (int) (playerstats.getStrength()*0.5)*DMCAttrConfig.MULTIPLIER_STR.get();
                    int maxdef = (int) (playerstats.getDefense()*0.5)*DMCAttrConfig.MULTIPLIER_DEF.get();

                    int staminacost = (maxstr / 4);
                    int curstamina = playerstats.getCurStam();

                    if(curstamina >= staminacost){

                        event.setAmount(event.getAmount() - (int) (maxdef/3));
                        playerstats.removeCurStam(staminacost);

                    }
                    if(staminacost >= curstamina){
                        event.setAmount(1);
                    }

                });

                int id = (int) (Math.random()*6+1);
                    sonidosGolpes(jugadorpemrd,id);
                }
            }
        }




    public static void sonidosGolpes(Player player, int id) {
        switch (id) {
            case 1:
                player.level().playSound(null, player.getOnPos(), MainSounds.GOLPE1.get(), SoundSource.PLAYERS, 2.2F, 0.9F);
                break;
            case 2:
                player.level().playSound(null, player.getOnPos(), MainSounds.GOLPE2.get(), SoundSource.PLAYERS, 2.2F, 0.9F);
                break;
            case 3:
                player.level().playSound(null, player.getOnPos(), MainSounds.GOLPE3.get(), SoundSource.PLAYERS, 2.2F, 0.9F);
                break;
            case 4:
                player.level().playSound(null, player.getOnPos(), MainSounds.GOLPE4.get(), SoundSource.PLAYERS, 2.2F, 0.9F);
                break;
            case 5:
                player.level().playSound(null, player.getOnPos(), MainSounds.GOLPE5.get(), SoundSource.PLAYERS, 2.2F, 0.9F);
                break;
            case 6:
                player.level().playSound(null, player.getOnPos(), MainSounds.GOLPE6.get(), SoundSource.PLAYERS, 2.2F, 0.9F);
                break;
        }
    }

}



