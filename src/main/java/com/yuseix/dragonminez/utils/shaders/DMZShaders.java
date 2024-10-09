package com.yuseix.dragonminez.utils.shaders;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterShadersEvent;

import java.io.IOException;

public class DMZShaders {

    private static ShaderInstance AURA_SHADER;

    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        new ResourceLocation(DragonMineZ.MOD_ID, "aura_glow"), DefaultVertexFormat.NEW_ENTITY),
                shader -> AURA_SHADER = shader);
    }

    public static ShaderInstance getAuraShader() {
        if (AURA_SHADER == null) {
            System.out.println("Shader is not yet loaded!");
        }
        return AURA_SHADER;
    }
}
