package com.yuseix.dragonminez.network.S2C;

import com.yuseix.dragonminez.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MenuS2C {

	private final boolean openCharacterMenu;

	public MenuS2C(boolean isConfirmCharacter) {
		this.openCharacterMenu = isConfirmCharacter;
	}

	public MenuS2C(FriendlyByteBuf buf) {
		this.openCharacterMenu = buf.readBoolean();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeBoolean(openCharacterMenu);
	}

	public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
		ctxSupplier.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(
					Dist.CLIENT, () -> () -> ClientPacketHandler.handleMenuPacket(openCharacterMenu)
			);
		});
		ctxSupplier.get().setPacketHandled(true);
	}


}
