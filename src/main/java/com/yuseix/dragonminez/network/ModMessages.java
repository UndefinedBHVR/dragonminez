package com.yuseix.dragonminez.network;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.C2S.*;
import com.yuseix.dragonminez.network.S2C.InvocarAuraS2C;
import com.yuseix.dragonminez.network.S2C.MenuS2C;
import com.yuseix.dragonminez.network.S2C.StatsSyncS2C;
import com.yuseix.dragonminez.network.S2C.ZPointsS2C;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    public static SimpleChannel INSTANCE;
    private static int packetID = 0;

    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(DragonMineZ.MOD_ID, "messagesold"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;


        //ENVIAR DATOS AL SERVIDOR
        net.messageBuilder(StatsC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StatsC2S::new)
                .encoder(StatsC2S::toBytes)
                .consumerMainThread(StatsC2S::handle)
                .add();
        net.messageBuilder(ZPointsC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ZPointsC2S::new)
                .encoder(ZPointsC2S::toBytes)
                .consumerMainThread(ZPointsC2S::handle)
                .add();
        net.messageBuilder(CharacterC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CharacterC2S::new)
                .encoder(CharacterC2S::toBytes)
                .consumerMainThread(CharacterC2S::handle)
                .add();
        net.messageBuilder(MenuC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(MenuC2S::new)
                .encoder(MenuC2S::toBytes)
                .consumerMainThread(MenuC2S::handle)
                .add();
        net.messageBuilder(KarinC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(KarinC2S::new)
                .encoder(KarinC2S::toBytes)
                .consumerMainThread(KarinC2S::handle)
                .add();
        net.messageBuilder(InvocarAuraC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(InvocarAuraC2S::new)
                .encoder(InvocarAuraC2S::toBytes)
                .consumerMainThread(InvocarAuraC2S::handle)
                .add();
        //ENVIAR DATOS AL CLIENTE
        net.messageBuilder(ZPointsS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ZPointsS2C::new)
                .encoder(ZPointsS2C::toBytes)
                .consumerMainThread(ZPointsS2C::handle)
                .add();
        net.messageBuilder(StatsSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(StatsSyncS2C::new)
                .encoder(StatsSyncS2C::toBytes)
                .consumerMainThread(StatsSyncS2C::handle)
                .add();
        net.messageBuilder(MenuS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(MenuS2C::new)
                .encoder(MenuS2C::toBytes)
                .consumerMainThread(MenuS2C::handle)
                .add();
        net.messageBuilder(InvocarAuraS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(InvocarAuraS2C::new)
                .encoder(InvocarAuraS2C::toBytes)
                .consumerMainThread(InvocarAuraS2C::handle)
                .add();
    }

    //Manda un paquete al Servidor (message no es un mensaje, es un paquete)
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    //Manda un paquete a un jugador (message no es un mensaje, es un paquete)
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    public static <MSG> void sendToAll(Player player, MSG message) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), message);
    }
}
