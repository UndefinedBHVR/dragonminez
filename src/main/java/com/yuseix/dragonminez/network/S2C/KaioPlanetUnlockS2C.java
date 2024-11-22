package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
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

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleKaioPlanetUnlockPacket(isUnlocked, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}
