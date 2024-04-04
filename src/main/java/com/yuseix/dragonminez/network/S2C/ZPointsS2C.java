package com.yuseix.dragonminez.network.S2C;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ZPointsS2C {

    private int zpoints;

    public ZPointsS2C(int zpoints) {
        this.zpoints = zpoints;

    }

    public ZPointsS2C(FriendlyByteBuf buf) {
        zpoints = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(zpoints);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {


        });
        return true;
    }
}
