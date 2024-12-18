package com.yuseix.dragonminez.init.items.models;

import com.yuseix.dragonminez.init.items.custom.YajirobeKatana;
import com.yuseix.dragonminez.init.items.custom.ZSword;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ZSwordRenderer extends GeoItemRenderer<ZSword> {
	public ZSwordRenderer() {
		super (new ZSwordModel());
	}
}
