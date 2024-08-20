package com.yuseix.dragonminez.stats;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.config.DMCAttrConfig;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.StatsSyncS2C;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class DMZStatsCapabilities {

    public static final Capability<DMZStatsAttributes> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        syncStats(event.getEntity());
        event.getEntity().refreshDimensions();

        DMZStatsProvider.getCap(INSTANCE, event.getEntity()).ifPresent(cap -> {

            var vidaMC = event.getEntity().getAttribute(Attributes.MAX_HEALTH).getBaseValue();
            var con = cap.getConstitution();
            var raza = cap.getRace();

            if(raza == 0){
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON.get()));
            } else if(raza == 1){
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_SAIYAN.get()));
            } else if(raza == 2){
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_NAMEK.get()));
            } else if(raza == 3){
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_BIO.get()));
            } else if(raza == 4){
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_COLD.get()));
            } else if(raza == 5){
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get()));
            }

        });


        }

    @SubscribeEvent
    public void playerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        syncStats(event.getEntity());
    }

    @SubscribeEvent
    public void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        syncStats(event.getEntity());

        DMZStatsProvider.getCap(INSTANCE, event.getEntity()).ifPresent(cap -> {

            var vidaMC = 20;
            var con = cap.getConstitution();
            var raza = cap.getRace();
            var energia = cap.getEnergy();
            var maxVIDA = 0.0;

            //VIDAAAAAAA
            if(raza == 0){
                maxVIDA = vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON.get());
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxVIDA);
                event.getEntity().heal((float) maxVIDA);
                cap.setCurStam((int) Math.round(maxVIDA * 0.5));

            } else if(raza == 1){
                maxVIDA = vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_SAIYAN.get());
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxVIDA);
                event.getEntity().heal((float) maxVIDA);
                cap.setCurStam((int) Math.round(maxVIDA * 0.5));

            } else if(raza == 2){
                maxVIDA = vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_NAMEK.get());
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxVIDA);
                event.getEntity().heal((float) maxVIDA);
                cap.setCurStam((int) Math.round(maxVIDA * 0.5));
            } else if(raza == 3){
                maxVIDA = vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_BIO.get());
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxVIDA);
                event.getEntity().heal((float) maxVIDA);
                cap.setCurStam((int) Math.round(maxVIDA * 0.5));
            } else if(raza == 4){
                maxVIDA = vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_COLD.get());
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxVIDA);
                event.getEntity().heal((float) maxVIDA);
                cap.setCurStam((int) Math.round(maxVIDA * 0.5));
            } else if(raza == 5){
                maxVIDA = vidaMC + ((double) con * DMCAttrConfig.MULTIPLIER_CON_MAJIN.get());
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxVIDA);
                event.getEntity().heal((float) maxVIDA);
                cap.setCurStam((int) Math.round(maxVIDA * 0.5));
            }
            //ENERGIAAA
            if(raza == 0){
                cap.setCurrentEnergy( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY.get() + 40));
            } else if(raza == 1){
                cap.setCurrentEnergy( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_SAIYAN.get() + 40));
            } else if(raza == 2){
                cap.setCurrentEnergy( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_NAMEK.get() + 40));
            } else if(raza == 3){
                cap.setCurrentEnergy( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_BIO.get() + 40));
            } else if(raza == 4){
                cap.setCurrentEnergy( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_COLD.get() + 40));
            } else if(raza == 5){
                cap.setCurrentEnergy( (int) Math.round(energia * DMCAttrConfig.MULTIPLIER_ENERGY_MAJIN.get() + 40));
            }
        });

    }

    @SubscribeEvent
    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(DMZStatsAttributes.class);
    }


    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {

        event.getOriginal().reviveCaps();

        DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, event.getEntity()).ifPresent(
                cap -> DMZStatsProvider.getCap(INSTANCE, event.getOriginal()).ifPresent(originalcap ->
                        cap.loadNBTData(originalcap.saveNBTData())));


        event.getOriginal().invalidateCaps();

    }

    @SubscribeEvent
    public static void onTrack(PlayerEvent.StartTracking event) {
        var trackingplayer = event.getEntity();
        if (!(trackingplayer instanceof ServerPlayer serverplayer)) return;

        var tracked = event.getTarget();
        if (tracked instanceof ServerPlayer trackedplayer) {
            DMZStatsProvider.getCap(INSTANCE, tracked).ifPresent(cap -> ModMessages.sendToPlayer(
                    new StatsSyncS2C(trackedplayer), serverplayer));
        }
    }

    public static void syncStats(Player player) {
        ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new StatsSyncS2C(player));
    }

}
