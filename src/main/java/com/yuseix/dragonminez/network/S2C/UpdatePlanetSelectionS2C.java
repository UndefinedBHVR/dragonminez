package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.client.hud.spaceship.SaiyanSpacePodOverlay;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdatePlanetSelectionS2C {
    private final int selectedPlanet;

    public UpdatePlanetSelectionS2C(int selectedPlanet) {
        this.selectedPlanet = selectedPlanet;
    }

    public static void encode(UpdatePlanetSelectionS2C msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.selectedPlanet);
    }

    public static UpdatePlanetSelectionS2C decode(FriendlyByteBuf buf) {
        return new UpdatePlanetSelectionS2C(buf.readInt());
    }

    public static void handle(UpdatePlanetSelectionS2C msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> SaiyanSpacePodOverlay.updatePlanetTarget(msg.selectedPlanet));
        ctx.get().setPacketHandled(true);
    }
}
