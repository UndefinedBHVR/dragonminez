package com.yuseix.dragonminec.events;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.client.ClientPlayerStats;
import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.init.MainSounds;
import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.network.S2C.curStatsS2C;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
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

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event){

        //Regenerar stamina
        if(event.side == LogicalSide.SERVER){
            event.player.getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerstats -> {

                int maxcon = (playerstats.getConstitution() - 2) * DMCAttrConfig.MULTIPLIER_CON.get();
                int maxstamina = (playerstats.getStamina() + 3) / 2;


                if(playerstats.getCurStam() >= 0 && playerstats.getCurStam() <= maxstamina
                        && event.player.getRandom().nextFloat() < 0.001f){ // 0.005 = 10s   && 0.0025 = 5s

                    int regenStamina = ((maxstamina) / 4);

                    playerstats.addCurStam(regenStamina);

                    ModMessages.sendToPlayer(new curStatsS2C(playerstats.getCurrentEnergy(),playerstats.getCurBody(),playerstats.getCurStam(), playerstats.getStamina()), (ServerPlayer) event.player);

                }
            });
        }

        event.player.getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerstats -> {

            event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(
                    (playerstats.getConstitution() - 2)*DMCAttrConfig.MULTIPLIER_CON.get());

        });

    }

    @SubscribeEvent
    public static void Recibirdano(LivingHurtEvent event){
        if(!(event.getEntity() instanceof Player)){  //LA ENTIDAD QUE RECIBE EL GOLPE NO ES UN JUGADOR
            if(event.getSource().getEntity() instanceof Player){ //SI EL QUE HACE DANO ES UN JUGADOR

                Player jugadorpemrd = (Player) event.getSource().getEntity();

                event.getSource().getEntity().getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerstats -> {

                    int maxstr = (playerstats.getStrength()-2)*DMCAttrConfig.MULTIPLIER_STR.get();

                    int staminacost = (maxstr / 6);

                    int curstamina = playerstats.getCurStam();

                    if(curstamina >= staminacost){
                        event.setAmount(maxstr);
                        playerstats.removeCurStam(staminacost);

                        ModMessages.sendToPlayer(new curStatsS2C(playerstats.getCurrentEnergy(),
                                playerstats.getCurBody(),
                                playerstats.getCurStam(), playerstats.getStamina()), (ServerPlayer) event.getSource().getEntity());
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

                event.getEntity().getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerstats ->{

                    event.setAmount(event.getAmount() - (int) (((playerstats.getDefense()-2)*DMCAttrConfig.MULTIPLIER_DEF.get())/2));

                });

            }

            if((event.getSource().getEntity() instanceof Player)){ //SI LA ENTIDAD QUE HACE DANO ES UN JUGADOR

                Player jugadorpemrd = (Player) event.getSource().getEntity();
                
                event.getEntity().getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerstats ->{

                    int maxstr = (playerstats.getStrength()-2)*DMCAttrConfig.MULTIPLIER_STR.get();
                    int maxdef = (playerstats.getDefense()-2)*DMCAttrConfig.MULTIPLIER_DEF.get();

                    int staminacost = (maxstr / 6);
                    int curstamina = playerstats.getCurStam();

                    if(curstamina >= staminacost){

                        event.setAmount(event.getAmount() - (int) (maxdef/3));
                        playerstats.removeCurStam(staminacost);

                        ModMessages.sendToPlayer(new curStatsS2C(playerstats.getCurrentEnergy(),
                                playerstats.getCurBody(),
                                playerstats.getCurStam(),
                                playerstats.getStamina()), (ServerPlayer) event.getSource().getEntity());

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



