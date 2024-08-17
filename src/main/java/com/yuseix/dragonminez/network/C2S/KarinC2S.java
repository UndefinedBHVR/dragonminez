package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.S2C.MenuS2C;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KarinC2S {

    private int option;

    public KarinC2S(int option) {
        this.option = option;
    }
    public KarinC2S(FriendlyByteBuf buf) {
        option = buf.readInt();

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(option);

    }
    public static void handle(KarinC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player != null) {

                if(packet.option == 1){
                    player.getInventory().add(new ItemStack(MainItems.NUBE_ITEM.get()));
                }

            }

        });
        context.setPacketHandled(true);
    }
}
