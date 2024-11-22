package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class InvocarAuraS2C {
	private final UUID playerId;
	private final float transparency;

	public InvocarAuraS2C(UUID playerId, float transparency) {
		this.playerId = playerId;
		this.transparency = transparency;
	}

	public InvocarAuraS2C(FriendlyByteBuf buf) {
		this.playerId = buf.readUUID();
		this.transparency = buf.readFloat();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeUUID(playerId);
		buf.writeFloat(transparency);
	}

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleInvocarAuraPacket(playerId, transparency, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}