package com.yuseix.dragonminez.stats;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.StatsSyncS2C;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID)
public class DMZStatsCapabilities {

    private DMZDatos dmzdatos = new DMZDatos();

    public static final Capability<DMZStatsAttributes> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        syncStats(event.getEntity());

        event.getEntity().refreshDimensions();

        DMZStatsProvider.getCap(INSTANCE, event.getEntity()).ifPresent(cap -> {

            var vidaMC = event.getEntity().getAttribute(Attributes.MAX_HEALTH).getBaseValue();
            var con = cap.getConstitution();
            var raza = cap.getRace();

            event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(dmzdatos.calcularCON(raza, con, 20, cap.getDmzClass()));
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
            maxVIDA = dmzdatos.calcularCON(raza, con, vidaMC,cap.getDmzClass());
            event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxVIDA);
            event.getEntity().heal((float) maxVIDA);
            cap.setCurStam(dmzdatos.calcularSTM(raza, (int) maxVIDA));

            //ENERGIAAA
            cap.setCurrentEnergy(dmzdatos.calcularENE(raza, energia, cap.getDmzClass()));
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
