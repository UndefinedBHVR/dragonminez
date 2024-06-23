package com.yuseix.dragonminez.listener;

import com.yuseix.dragonminez.character.FaceModel;
import com.yuseix.dragonminez.character.models.ModeloPrueba;
import com.yuseix.dragonminez.init.MainBlockEntities;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.renderer.DinoRenderer;
import com.yuseix.dragonminez.network.ModMessages;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public final class ModListener {

    @SubscribeEvent
    public void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            SpawnPlacements.register(MainEntity.DINO1.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

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

    }

    @SubscribeEvent
    public void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(FaceModel.LAYER_LOCATION, FaceModel::createBodyLayer);

        e.registerLayerDefinition(ModeloPrueba.LAYER_LOCATION, ModeloPrueba::createBodyLayer);
    }
}
