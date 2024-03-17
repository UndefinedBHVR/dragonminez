package com.yuseix.dragonminec.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_DMC = "key.category.dragonminec.category";

    public static final String KEY_STATS_MENU = "key.dragonminec.menu_stats";


    public static final KeyMapping STATS_MENU_KEY = new KeyMapping(KEY_STATS_MENU, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_DMC);


}
