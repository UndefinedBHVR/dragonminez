package com.yuseix.dragonminez.listener;

import com.yuseix.dragonminez.character.FaceModel;
import com.yuseix.dragonminez.character.models.ModeloPrueba;
import com.yuseix.dragonminez.init.MainBlockEntities;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.MainFluids;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.renderer.DinoRenderer;
import com.yuseix.dragonminez.model.Keys;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.network.PacketHandler;
import com.yuseix.dragonminez.stats.StatsAttrProviderV2;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class ModListener {

    @SubscribeEvent
    public void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            SpawnPlacements.register(MainEntity.DINO1.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

            //Registramos los packets
            PacketHandler.register();
            ModMessages.register();
        });
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        //ENTIDADES
        EntityRenderers.register(MainEntity.DINO1.get(), DinoRenderer::new);

        //BLOQUES
        BlockEntityRenderers.register(MainBlockEntities.DBALL1_NAMEK_BLOCK_ENTITY.get(), Dball1NamekBlockRenderer::new);
        BlockEntityRenderers.register(MainBlockEntities.DBALL1_BLOCK_ENTITY.get(), Dball1BlockRenderer::new);
        BlockEntityRenderers.register(MainBlockEntities.DBALL2_BLOCK_ENTITY.get(), Dball2BlockRenderer::new);
        BlockEntityRenderers.register(MainBlockEntities.DBALL3_BLOCK_ENTITY.get(), Dball3BlockRenderer::new);
        BlockEntityRenderers.register(MainBlockEntities.DBALL4_BLOCK_ENTITY.get(), Dball4BlockRenderer::new);
        BlockEntityRenderers.register(MainBlockEntities.DBALL5_BLOCK_ENTITY.get(), Dball5BlockRenderer::new);
        BlockEntityRenderers.register(MainBlockEntities.DBALL6_BLOCK_ENTITY.get(), Dball6BlockRenderer::new);
        BlockEntityRenderers.register(MainBlockEntities.DBALL7_BLOCK_ENTITY.get(), Dball7BlockRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(MainFluids.SOURCE_HEALING.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MainFluids.FLOWING_HEALING.get(), RenderType.translucent());

        MinecraftForge.EVENT_BUS.addListener(DballOutlineRenderer::renderOutlineDball);
    }

    @SubscribeEvent
    public void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(FaceModel.LAYER_LOCATION, FaceModel::createBodyLayer);

        e.registerLayerDefinition(ModeloPrueba.LAYER_LOCATION, ModeloPrueba::createBodyLayer);
    }

    @SubscribeEvent
    public void onKeyRegister(RegisterKeyMappingsEvent event) {
        /*
        Usa reflection para registrar todas las teclas de la clase Keys, utilicé esto para no tener que registrar cada tecla manualmente
        También porque los fields son static
         */
        try {
            Field[] fields = Keys.class.getDeclaredFields();

            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType() == KeyMapping.class) {
                    KeyMapping keyMapping = (KeyMapping) field.get(null);
                    event.register(keyMapping);
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println("Error al intentar registrar una tecla! " + e.getMessage());
        }
    }

    @SubscribeEvent
    public void onCapabilitiesRegister(RegisterCapabilitiesEvent event) {
        event.register(StatsAttrProviderV2.class);
    }
}
