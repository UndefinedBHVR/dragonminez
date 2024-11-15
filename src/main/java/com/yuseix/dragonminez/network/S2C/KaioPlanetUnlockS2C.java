package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.client.hud.spaceship.SaiyanSpacePodOverlay;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KaioPlanetUnlockS2C {
    private final boolean isUnlocked;

    public KaioPlanetUnlockS2C(boolean isUnlocked) {
        this.isUnlocked = isUnlocked;
    }

    public static void encode(KaioPlanetUnlockS2C msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.isUnlocked);
    }

    public static KaioPlanetUnlockS2C decode(FriendlyByteBuf buf) {
        return new KaioPlanetUnlockS2C(buf.readBoolean());
    }

    public static void handle(KaioPlanetUnlockS2C msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            SaiyanSpacePodOverlay.setKaioAvailable(msg.isUnlocked);
        });
        ctx.get().setPacketHandled(true);
    }
}
