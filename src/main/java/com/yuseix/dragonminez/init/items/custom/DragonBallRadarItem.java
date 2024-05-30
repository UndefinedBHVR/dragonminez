package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ComplexItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class DragonBallRadarItem extends ComplexItem {

    public DragonBallRadarItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);

        //Toggle radar soun
        if (level.isClientSide())
            player.playSound(MainSounds.RADAR_SCAN.get(), 0.5F, 1.0F);
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

            /*TODO: Renderizado Radar
             * Abajo se puede ver cómo se envian al chat las posiciones de las DBalls al hacer click derecho con el radar.
             * Pero aún no existe ni la textura ni el renderizado del radar en sí.
             * Parece una tarea complicada, hay que revisar items como el MapItem
             * labels: Estado: Disponible, Prioridad: Media, Tipo: Modelos
             */

            level.getCapability(DragonBallGenProvider.CAPABILITY).ifPresent(dragonBallsCapability -> {
                if (dragonBallsCapability.hasDragonBalls()) {
                    List<BlockPos> dragonBallPositions = dragonBallsCapability.DragonBallPositions();
                    for (BlockPos pos : dragonBallPositions) {
                        player.sendSystemMessage(Component.literal("Dragon Ball found! At " + pos));
                    }
                }
            });
        }

        return InteractionResultHolder.pass(item);
    }

}