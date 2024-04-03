package com.yuseix.dragonminec.events;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.character.LayerDMZPost;
import com.yuseix.dragonminec.commands.StatsCommand;
import com.yuseix.dragonminec.commands.ZPointsCommand;
import com.yuseix.dragonminec.config.DMCAttrConfig;
import com.yuseix.dragonminec.init.MainEntity;
import com.yuseix.dragonminec.init.entity.custom.DinoEntity;
import com.yuseix.dragonminec.network.ModMessages;
import com.yuseix.dragonminec.network.S2C.StatsSyncS2C;
import com.yuseix.dragonminec.stats.PlayerStatsAttrProvider;
import com.yuseix.dragonminec.stats.PlayerStatsAttributes;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = DragonMineC.MODID)
public class ModEvents {

    public static final Capability<PlayerStatsAttributes> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public static void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        sync(event.getEntity());
        event.getEntity().refreshDimensions();

        PlayerStatsAttrProvider.getCap(INSTANCE, event.getEntity()).ifPresent(cap ->
                event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue((cap.getConstitution() * 0.5) * DMCAttrConfig.MULTIPLIER_CON.get()));
    }

    @SubscribeEvent
    public static void playerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        sync(event.getEntity());
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        sync(event.getEntity());

        PlayerStatsAttrProvider.getCap(INSTANCE, event.getEntity()).ifPresent(cap -> {

            event.getEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue((cap.getConstitution() * 0.5) * DMCAttrConfig.MULTIPLIER_CON.get());
            event.getEntity().heal((float) (cap.getConstitution() * 0.5) * DMCAttrConfig.MULTIPLIER_CON.get());

            int maxEnergia = (int) (cap.getEnergy() * 0.5) * DMCAttrConfig.MULTIPLIER_ENERGY.get();
            int maxStamina = cap.getStamina() + 3;

            cap.setCurrentEnergy(maxEnergia);
            cap.setStamina(maxStamina);

        });

    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerStatsAttributes.class);
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {

        event.getOriginal().reviveCaps();

        PlayerStatsAttrProvider.getCap(ModEvents.INSTANCE, event.getEntity()).ifPresent(
                cap -> PlayerStatsAttrProvider.getCap(INSTANCE, event.getOriginal()).ifPresent(originalcap ->
                        cap.loadNBTData(originalcap.saveNBTData())));


        event.getOriginal().invalidateCaps();

    }

    @SubscribeEvent
    public static void onTrack(PlayerEvent.StartTracking event) {
        var trackingplayer = event.getEntity();
        if (!(trackingplayer instanceof ServerPlayer player)) {
            return;
        }
        if (event.getTarget() instanceof ServerPlayer trackedplayer) {
            PlayerStatsAttrProvider.getCap(INSTANCE, event.getTarget()).ifPresent(cap -> ModMessages.sendToPlayer(
                    new StatsSyncS2C(trackedplayer), player
            ));
        }
    }


    public static void sync(Player player) {
        ModMessages.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new StatsSyncS2C(player));
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (event.getObject().getCapability(INSTANCE).isPresent()) {
                return;
            }
            //System.out.println("Añadiendo capability");
            final PlayerStatsAttrProvider provider = new PlayerStatsAttrProvider(player);

            event.addCapability(PlayerStatsAttrProvider.ID, provider);

        }
    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new ZPointsCommand(event.getDispatcher());
        new StatsCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

    @Mod.EventBusSubscriber(modid = DragonMineC.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(MainEntity.DINO1.get(), DinoEntity.setAttributes());
        }


    }
}
