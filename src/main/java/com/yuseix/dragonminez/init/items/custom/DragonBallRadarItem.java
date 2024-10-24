package com.yuseix.dragonminez.init.items.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.world.DragonBallGenProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
public class DragonBallRadarItem extends Item {

    private static final int[] RANGES = {100, 200}; // Diferentes rangos
    public static final String NBT_RANGE = "RadarRange"; // Clave NBT para almacenar el rango

    public DragonBallRadarItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Alternar rango al hacer clic derecho
        if (!world.isClientSide()) {
            int currentRange = stack.getOrCreateTag().getInt(NBT_RANGE);
            int newRange = RANGES[(indexOf(currentRange) + 1) % RANGES.length]; // Alternar entre los rangos

            stack.getOrCreateTag().putInt(NBT_RANGE, newRange);
            player.displayClientMessage(Component.literal("Rango del radar: " + newRange + " bloques"), true);
        }

        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }

    private int indexOf(int range) {
        for (int i = 0; i < RANGES.length; i++) {
            if (RANGES[i] == range) return i;
        }
        return 0;
    }


    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.dragonminez.dball_radar.tooltip").withStyle(ChatFormatting.GRAY));
    }

}