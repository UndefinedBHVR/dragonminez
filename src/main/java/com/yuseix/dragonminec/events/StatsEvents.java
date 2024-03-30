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
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineC.MODID)
public class StatsEvents {

    private static int tickcounter = 0;
    private static int energiacounter = 0;

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event){


        //Regenerar stamina
        if(event.side == LogicalSide.SERVER){

            energiacounter++;
            tickcounter++;

            PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.player).ifPresent(playerstats -> {

                int maxcon = (int) (playerstats.getConstitution() * 0.5) * DMCAttrConfig.MULTIPLIER_CON.get();
                int maxstamina = (playerstats.getStamina() + 3);
                int maxenergia = (int) (playerstats.getEnergy() * 0.5)*DMCAttrConfig.MULTIPLIER_ENERGY.get();


                if(playerstats.getCurStam() >= 0 && playerstats.getCurStam() <= maxstamina ){

                    if(tickcounter >= 60 * 3){

                        int regenStamina = ((maxstamina) / 4);

                        playerstats.addCurStam(regenStamina);

                        tickcounter = 0;

                    }

                }
                if(playerstats.getCurrentEnergy() >= 0 && playerstats.getCurrentEnergy() <= maxenergia){
                    if(energiacounter >= 60 * 5){

                        int regenki = ((maxenergia) / 10);

                        playerstats.addCurEnergy(regenki);

                        energiacounter = 0;
                    }
                }


                event.player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxcon);

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

    @SubscribeEvent
    public static void FallEvent(LivingFallEvent event){

        if(event.getEntity() instanceof ServerPlayer player){
                if(event.getDistance() > 4.5f){
                    PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, player).ifPresent(stats -> {

                        if(stats.getCurrentEnergy() > 5){
                            stats.removeCurEnergy(5);
                            event.setCanceled(true);
                        }
                    });
            }
        }
    }


    @SuppressWarnings({"deprecation", "removal"})
    @SubscribeEvent
    public static void cambiarTamano(EntityEvent.Size event){

        EntityDimensions newSize = new EntityDimensions(2.0f, 2.0f, event.getNewSize().fixed);
        //event.setNewSize(newSize);
        //event.setNewEyeHeight(3.5f);

    }
    @SubscribeEvent
    public static void changeSizePRE(RenderPlayerEvent.Pre event){

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getEntity()).ifPresent(cap -> {

            //Tamaño del jugador (ALPHA)
            int atributosMAX = DMCAttrConfig.MAX_ATTRIBUTE_VALUE.get();
            

            event.getPoseStack().pushPose();

            event.getPoseStack().scale(2.0f,2.0f,2.0f);



        });



    }
    @SubscribeEvent
    public static void changeSizePOST(RenderPlayerEvent.Post event){
       
        event.getPoseStack().popPose();
    }
    private static void sonidosGolpes(Player player, int id) {
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



