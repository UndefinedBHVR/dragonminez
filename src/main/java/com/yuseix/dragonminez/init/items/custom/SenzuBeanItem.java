package com.yuseix.dragonminez.init.items.custom;

import com.yuseix.dragonminez.config.DMZGeneralConfig;
import com.yuseix.dragonminez.init.MainSounds;
import com.yuseix.dragonminez.stats.DMZStatsCapabilities;
import com.yuseix.dragonminez.stats.DMZStatsProvider;
import com.yuseix.dragonminez.utils.DMZDatos2;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SenzuBeanItem extends Item {
	public SenzuBeanItem() {
		super(new Properties()
				.stacksTo(8)
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
	public UseAnim getUseAnimation(ItemStack pStack) {
		return UseAnim.BLOCK;
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 20 * 15; // 15s y se reinicia la animacion xd
	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
		if (pPlayer.getCooldowns().isOnCooldown(this)) {
			return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
		}
		pPlayer.startUsingItem(pUsedHand);
		return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
	}

	@Override
	public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int timeLeft) {
		if (!(pLivingEntity instanceof Player player) || pLevel.isClientSide) return;

		DMZDatos2 dmzdatos = new DMZDatos2();

		pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), MainSounds.SENZU_BEAN.get(), SoundSource.NEUTRAL, 1.5F, 1.0F);

		DMZStatsProvider.getCap(DMZStatsCapabilities.INSTANCE, player).ifPresent(stats -> {
			var vidaMC = 20;
			var con = stats.getConstitution();
			var energia = stats.getEnergy();
			var raza = stats.getRace();

			double VidaTotal = dmzdatos.calcularCON(raza, con, vidaMC, stats.getDmzClass());
			int energiaMax = dmzdatos.calcularENE(raza, energia, stats.getDmzClass());
			int staminaMax = dmzdatos.calcularSTM(raza, (int) VidaTotal);

			player.heal((float) VidaTotal);
			stats.setCurStam(staminaMax);
			stats.setCurrentEnergy(energiaMax);
		});

		player.getFoodData().setFoodLevel(20);
		player.getFoodData().setSaturation(15.0F);

		var segundos = DMZGeneralConfig.SENZU_COOLDOWN.get();
		var tiempo = segundos * 20;

		player.getCooldowns().addCooldown(this, tiempo);

		pStack.shrink(1);
	}
}
