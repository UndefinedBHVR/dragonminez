package com.yuseix.dragonminez.network.C2S;

import com.google.common.collect.Maps;
import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.events.cc.StatsEvents;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.init.entity.custom.fpcharacters.AuraEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekWarriorEntity;
import com.yuseix.dragonminez.init.entity.custom.namek.NamekianEntity;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.InvocarAuraS2C;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class InvocarAuraC2S {

    public static final Map<UUID, AuraEntity> playerAuraMap = Maps.newConcurrentMap();

    public InvocarAuraC2S() {
    }

    public InvocarAuraC2S(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public static void handle(InvocarAuraC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(cap -> {
                    UUID playerId = player.getUUID();

                    if (cap.isAuraOn()) { // Si el botón está presionado
                        AuraEntity aura = playerAuraMap.get(playerId);
                        if (aura == null) { // Si no existe una aura, crearla

                            aura = new AuraEntity(MainEntity.AURA.get(), player.level());

                            aura.setOwnerUUID(player.getUUID());
                            aura.setRaza(cap.getRace());
                            aura.setTransformation(cap.getDmzState());

                            var auraBase = cap.getAuraColor();

                            switch (cap.getRace()){
                                case 0: //Humano
                                    if(cap.getDmzState() == 0){
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    } else {
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    }
                                    break;
                                case 1: //Saiyan
                                    if(cap.getDmzState() == 0){
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    } else if(cap.getDmzState() == 1){
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    } else {
                                        aura.setTipoAura(0);
                                        aura.setColorAura(16777045);
                                    }
                                    break;
                                case 2: //Namek
                                    if(cap.getDmzState() == 0){
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    } else {
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    }
                                    break;
                                case 3: //BioAndroide
                                    if(cap.getDmzState() == 0){
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    } else if(cap.getDmzState() == 1){
                                        aura.setTipoAura(0);
                                        aura.setColorAura(16777045);
                                    } else {
                                        aura.setTipoAura(0);
                                        aura.setColorAura(16777045);
                                    }
                                    break;
                                case 4: //Demonio del frio causa
                                    if(cap.getDmzState() == 0){
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    } else {
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    }
                                    break;
                                case 5: //Majin buu osea uminoshita gaa
                                    if(cap.getDmzState() == 0){
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    } else {
                                        aura.setTipoAura(0);
                                        aura.setColorAura(auraBase);
                                    }
                                    break;
                                default:
                                    break;
                            }
                            player.level().addFreshEntity(aura);
                            aura.setPos(player.getX(), player.getY(), player.getZ());
                            playerAuraMap.put(playerId, aura);
                        }
                        //aura.setPos(player.getX(), player.getY(), player.getZ());

                        float transparency = playerId.equals(player.getUUID()) && isInFirstPersonView(player) ? 0.05F : 0.15F;
                        ModMessages.sendToPlayer(new InvocarAuraS2C(playerId, transparency), player);

                    } else { // Si el botón no está presionado
                        AuraEntity aura = playerAuraMap.remove(playerId);
                        if (aura != null) {  // Si la aura existe, eliminarla
                            aura.remove(Entity.RemovalReason.DISCARDED);
                        }
                    }
                });
            }
        });
        context.setPacketHandled(true);
    }

    public static boolean isInFirstPersonView(Player player) {
        return Minecraft.getInstance().options.getCameraType().isFirstPerson();
    }
}
