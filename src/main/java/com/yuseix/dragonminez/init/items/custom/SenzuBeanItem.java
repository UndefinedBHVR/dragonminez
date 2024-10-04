package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SenzuBeanItem extends Item {
    public SenzuBeanItem() {
        super(new Properties()
                .stacksTo(10)
        );
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.senzu_bean");
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.dragonminez.senzu_bean.tooltip").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        // Verifica si el jugador tiene un cooldown activo en el item
        if (pPlayer.getCooldowns().isOnCooldown(this)) {
            pPlayer.displayClientMessage(Component.literal("Senzu Bean is on Cooldown!").withStyle(ChatFormatting.RED), true);
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        }

        ItemStack senzu = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), MainSounds.SENZU_BEAN.get(), SoundSource.NEUTRAL, 1.5F, 1.0F);

        if (!pLevel.isClientSide) {
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, pPlayer).ifPresent(stats -> {

                var vidaMC = 20;
                var con = stats.getConstitution();
                var energia = stats.getEnergy();
                var raza = stats.getRace();

                // Calcular vida total, energía máxima y stamina
                double VidaTotal = DMZDatos.calcularCON(raza, con, vidaMC, stats.getDmzClass());
                int energiaMax = DMZDatos.calcularENE(raza, energia, stats.getDmzClass());
                int staminaMax = DMZDatos.calcularSTM(raza, (int) VidaTotal);

                // Regenerar vida, stamina y energía
                pPlayer.heal((float) VidaTotal);
                stats.setCurStam(staminaMax);
                stats.setCurrentEnergy(energiaMax);
            });

            pPlayer.getFoodData().setFoodLevel(20);
            pPlayer.getFoodData().setSaturation(15.0F);
        }

        var segundos = DMZGeneralConfig.SENZU_COOLDOWN.get();
        var tiempo = segundos * 20;

        pPlayer.getCooldowns().addCooldown(this, tiempo); // 200 ticks = 10 segundos

        senzu.shrink(1);
        return InteractionResultHolder.sidedSuccess(senzu, pLevel.isClientSide());
    }
}
