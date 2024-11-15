package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.UpdatePlanetSelectionS2C;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlanetSelectionC2S {
    private final int selectedPlanet;

    public PlanetSelectionC2S(int selectedPlanet) {
        this.selectedPlanet = selectedPlanet;
    }

    public static void encode(PlanetSelectionC2S msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.selectedPlanet);
    }

    public static PlanetSelectionC2S decode(FriendlyByteBuf buf) {
        return new PlanetSelectionC2S(buf.readInt());
    }

    public static void handle(PlanetSelectionC2S msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = ctx.get().getSender();
            if (player != null) {
                ModMessages.sendToPlayer(new UpdatePlanetSelectionS2C(msg.selectedPlanet), player);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
