package com.yuseix.dragonminez.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keys {

    public static final String CATEGORY = "key.categories.dragonminez.main";

    public static final KeyMapping KI_CHARGE = new KeyMapping("key.dragonminez.ki_charge",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, CATEGORY);
    public static final KeyMapping DESCEND_KEY = new KeyMapping("key.dragonminez.descend_key",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_ALT, CATEGORY);
    //public static final KeyMapping PANEL_GUI = new KeyMapping("key.dragonminez.panel_gui",
    //        KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_L, CATEGORY);
    public static final KeyMapping STATS_MENU = new KeyMapping("key.dragonminez.menu_stats",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, CATEGORY);

}
