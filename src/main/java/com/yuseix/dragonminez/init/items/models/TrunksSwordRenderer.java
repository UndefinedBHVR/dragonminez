package com.yuseix.dragonminez.init.items.models;

import com.yuseix.dragonminez.init.items.custom.TrunksSword;
import com.yuseix.dragonminez.init.items.custom.ZSword;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class TrunksSwordRenderer extends GeoItemRenderer<TrunksSword> {
	public TrunksSwordRenderer() {
		super (new TrunksSwordModel());
	}
}
