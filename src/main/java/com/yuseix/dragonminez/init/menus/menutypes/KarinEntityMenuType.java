package com.yuseix.dragonminez.init.menus.menutypes;

import com.yuseix.dragonminez.init.MainMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class KarinEntityMenuType extends AbstractContainerMenu {

	public KarinEntityMenuType(int pContainerId, Inventory inventory) {
		super(MainMenus.KARIN_ENTITY_MENU.get(), pContainerId);
	}


	@Override
	public ItemStack quickMoveStack(Player player, int i) {
		return null;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

}