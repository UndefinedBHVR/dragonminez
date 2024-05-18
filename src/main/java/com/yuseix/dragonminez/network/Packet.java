package com.yuseix.dragonminez.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public interface Packet {

    void read(FriendlyByteBuf buf);

    void write(FriendlyByteBuf buf);

    void handle(ServerPlayer player);
}
