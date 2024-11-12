package com.yuseix.dragonminez.network.C2S;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.MainItems;
import com.yuseix.dragonminez.init.entity.custom.PorungaEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PorungaC2S {
    private final int option;
    private final int wishCount;

    public PorungaC2S(int option, int wishCount) {
        this.option = option;
        this.wishCount = wishCount;
    }

    public PorungaC2S(FriendlyByteBuf buf) {
        option = buf.readInt();
        wishCount = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(option);
        buf.writeInt(wishCount);
    }

    public static void handle(PorungaC2S packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            if (player != null) {
                switch (packet.option) {
                    case 1 -> player.getInventory().add(new ItemStack(MainItems.CAPSULA_ROJA.get(), DMZGeneralConfig.CAPSULE_PORUNGA_WISH.get()));
                    case 2 -> player.getInventory().add(new ItemStack(MainItems.CAPSULA_MORADA.get(), DMZGeneralConfig.CAPSULE_PORUNGA_WISH.get()));
                    case 3 -> player.getInventory().add(new ItemStack(MainItems.CAPSULA_VERDE.get(), DMZGeneralConfig.CAPSULE_PORUNGA_WISH.get()));
                    case 4 -> player.getInventory().add(new ItemStack(MainItems.CAPSULA_AZUL.get(), DMZGeneralConfig.CAPSULE_PORUNGA_WISH.get()));
                    case 5 -> player.getInventory().add(new ItemStack(MainItems.CAPSULA_ANARANJADA.get(), DMZGeneralConfig.CAPSULE_PORUNGA_WISH.get()));
                    case 6 -> player.getInventory().add(new ItemStack(MainItems.SENZU_BEAN.get(), DMZGeneralConfig.SENZU_PORUNGA_WISH.get()));
                    case 7 -> player.getInventory().add(new ItemStack(MainItems.GETE_SCRAP.get(), DMZGeneralConfig.GETE_PORUNGA_WISH.get()));
                }

                // Despawnear la entidad Porunga en el mundo del jugador
                if (packet.wishCount == 3) {
                    player.level().getEntities(player, player.getBoundingBox().inflate(50), entity ->
                            entity.getType() == MainEntity.PORUNGA.get()).forEach(entity -> {
                        if (entity instanceof PorungaEntity porunga) {
                            ServerLevel serverLevel = (ServerLevel) player.level();
                            serverLevel.setDayTime(porunga.getInvokingTime()); // Restaura el tiempo original
                        }
                        entity.discard();
                    });
                }
            }
        });
        context.setPacketHandled(true);
    }
}
