package com.yuseix.dragonminez.events.cc;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.RenderManos;
import com.yuseix.dragonminez.init.armor.client.model.ArmorBaseModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CharacterEvents {

    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {


    }


}
