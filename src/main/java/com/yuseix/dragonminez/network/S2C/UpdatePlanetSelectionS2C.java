package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
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

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleUpdatePlanetSelectionPacket(selectedPlanet, ctxSupplier)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}
}
