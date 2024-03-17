package com.yuseix.dragonminec.events;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.client.ClientPlayerStats;
import com.yuseix.dragonminec.commands.ZPointsCommand;
import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.network.S2C.StatsS2C;
import com.yuseix.dragonminec.network.S2C.ZPointsS2C;
import com.yuseix.dragonminec.network.S2C.curStatsS2C;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import com.yuseix.dragonminec.stats.PlayerStatsAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = DragonMineC.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event){
        if(!event.getLevel().isClientSide()){
            if(event.getEntity() instanceof ServerPlayer player){
                player.getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerstats -> {

                    playerstats.setCurStam((playerstats.getStamina() + 3) / 3);

                    //Aca enviar los datos al jugador
                    ModMessages.sendToPlayer(new StatsS2C(playerstats.getStrength(),
                            playerstats.getDefense(),
                            playerstats.getConstitution(),
                            playerstats.getKiPower(),
                            playerstats.getEnergy()), player);

                    ModMessages.sendToPlayer(new ZPointsS2C(playerstats.getZpoints()), player);

                    ModMessages.sendToPlayer(new curStatsS2C(playerstats.getCurrentEnergy(),
                            playerstats.getCurBody(),
                            playerstats.getCurStam(), playerstats.getStamina()), player);

                });
            }
        }
    }

    @SubscribeEvent
    public static void playerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        event.getEntity().reviveCaps();
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event)
    {
        if(event.getEntity() instanceof ServerPlayer player){
            event.getEntity().getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(playerstats -> {

                playerstats.setCurrentEnergy( (playerstats.getEnergy() - 2) * DMCAttrConfig.MULTIPLIER_ENERGY.get());

                playerstats.setCurStam((playerstats.getStamina() + 3) / 2);

                ModMessages.sendToPlayer(new curStatsS2C(playerstats.getCurrentEnergy(),
                        playerstats.getCurBody(),
                        playerstats.getCurStam(), playerstats.getStamina()), player);

                player.setHealth(  (playerstats.getConstitution() - 2)*DMCAttrConfig.MULTIPLIER_CON.get());

            });

    }


    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(PlayerStatsAttributes.class);
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event)
    {

        event.getOriginal().reviveCaps();

        if(event.isWasDeath())
        {
            event.getOriginal().getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(oldStore ->
            {
                event.getEntity().getCapability(PlayerStatsAttrProvider.PLAYER_STATS).ifPresent(newStore ->
                {
                    newStore.copyFrom(oldStore);
                });
            });
        }


    }
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof Player)
        {
            if(!event.getObject().getCapability(PlayerStatsAttrProvider.PLAYER_STATS).isPresent())
            {
                event.addCapability(new ResourceLocation(DragonMineC.MODID, "properties"), new PlayerStatsAttrProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event){
        new ZPointsCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @Mod.EventBusSubscriber(modid = DragonMineC.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            //event.put(ModEntities.GOKU.get(), GokuEntity.setAttributes());
        }
    }
}
