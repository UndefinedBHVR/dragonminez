package com.yuseix.dragonminec.network;

import com.yuseix.dragonminec.DragonMineC;
import com.yuseix.dragonminec.network.C2S.StatsC2S;
import com.yuseix.dragonminec.network.C2S.ZPointsC2S;
import com.yuseix.dragonminec.network.S2C.StatsS2C;
import com.yuseix.dragonminec.network.S2C.ZPointsS2C;
import com.yuseix.dragonminec.network.S2C.curStatsS2C;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static SimpleChannel INSTANCE;
    private static int packetID = 0;
    private static int id(){
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(DragonMineC.MODID, "messages"))
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
        //ENVIAR DATOS AL CLIENTE
        net.messageBuilder(StatsS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(StatsS2C::new)
                .encoder(StatsS2C::toBytes)
                .consumerMainThread(StatsS2C::handle)
                .add();
        net.messageBuilder(ZPointsS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ZPointsS2C::new)
                .encoder(ZPointsS2C::toBytes)
                .consumerMainThread(ZPointsS2C::handle)
                .add();
        net.messageBuilder(curStatsS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(curStatsS2C::new)
                .encoder(curStatsS2C::toBytes)
                .consumerMainThread(curStatsS2C::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
