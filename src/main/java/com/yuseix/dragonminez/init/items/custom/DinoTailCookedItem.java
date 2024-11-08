package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DinoTailCookedItem extends Item {
    private static final double HP_RESTORE_PERCENTAGE = 0.18; // 18%
    private static final double KI_RESTORE_PERCENTAGE = 0.12; // 12%
    private static final int HUNGER = 4;
    private static final float SATURATION = 6F;
    public DinoTailCookedItem() {
        super(new Properties().food(
                new FoodProperties.Builder()
                        .nutrition(HUNGER)
                        .meat()
                        .saturationMod(SATURATION)
                        .build()
        ));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return Component.translatable("item.dragonminez.dinotail_cooked");
    }

    // Curación Vida/Ki
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide && pLivingEntity instanceof ServerPlayer player) {
            DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
                int maxHp = stats.getMaxHealth();
                int curarVida = (int) (maxHp * HP_RESTORE_PERCENTAGE);
                int maxKi = stats.getMaxEnergy();
                int curarKi = (int) (maxKi * KI_RESTORE_PERCENTAGE);

                player.heal(curarVida);
                stats.addCurEnergy(curarKi);
            });
            player.getFoodData().eat(HUNGER, SATURATION);
        }
        pStack.shrink(1);
        return pStack;
    }
}
