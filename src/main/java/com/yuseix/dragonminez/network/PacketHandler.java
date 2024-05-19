package com.yuseix.dragonminez.network;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.network.packets.PacketStats;
import com.yuseix.dragonminez.network.packets.PacketStatsSync;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PacketHandler {

    private static SimpleChannel INSTANCE;
    private static int packetID = 0;

    public static void register() {
        INSTANCE = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(DragonMineZ.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        addPacket(NetworkDirection.PLAY_TO_SERVER, PacketStats.class);
        addPacket(NetworkDirection.PLAY_TO_CLIENT, PacketStatsSync.class);

    }

    private static <T extends Packet> void addPacket(NetworkDirection direction, Class<T> packet) {
        INSTANCE.messageBuilder(packet, packetID++, direction)
                .decoder((FriendlyByteBuf.Reader<T>) buf -> {
                    try {
                        T newInstance = packet.getDeclaredConstructor().newInstance();
                        newInstance.read(buf);

                        return newInstance;

                    } catch (ReflectiveOperationException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .encoder(Packet::write)
                .consumerMainThread((packet1, supplier) -> {
                    NetworkEvent.Context context = supplier.get();

                    context.enqueueWork(() -> packet1.handle(context.getSender()));
                })
                .add();
    }

    public static void sendToServer(Packet packet) {
        INSTANCE.sendToServer(packet);
    }

    public static void sendToPlayer(Packet packet, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static void sendToClients(Packet packet) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
    }

    public static void sendToAll(ServerPlayer player, Packet packet) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), packet);
    }
}
