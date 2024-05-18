package model;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keys {

    public static final String CATEGORY = "key.categories.dragonminez.main";

    public static final KeyMapping KI_CHARGE = new KeyMapping("key.dragonminez.ki_charge",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, CATEGORY);
    public static final KeyMapping ACTION_MENU = new KeyMapping("key.dragonminez.action_menu",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, CATEGORY);
    public static final KeyMapping PANEL_GUI = new KeyMapping("key.dragonminez.panel_gui",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_L, CATEGORY);

}
