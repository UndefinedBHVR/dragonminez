package com.yuseix.dragonminez.listener;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.FaceModel;
import com.yuseix.dragonminez.character.models.ModeloPrueba;
import com.yuseix.dragonminez.init.MainBlockEntities;
import com.yuseix.dragonminez.init.MainEntity;
import com.yuseix.dragonminez.init.blocks.entity.client.*;
import com.yuseix.dragonminez.init.entity.client.renderer.DinoRenderer;
import com.yuseix.dragonminez.network.ModMessages;
import com.yuseix.dragonminez.worldgen.biome.ModBiomes;
import com.yuseix.dragonminez.worldgen.biome.surface.ModSurfaceRules;
import com.yuseix.dragonminez.worldgen.dimension.ModDimensions;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import terrablender.api.SurfaceRuleManager;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public final class ModListener {

    @SubscribeEvent
    public void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            SpawnPlacements.register(MainEntity.DINO1.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

            ModMessages.register();

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, DragonMineZ.MOD_ID, ModSurfaceRules.compileRules());
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

    @SubscribeEvent
    public void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();

        DatapackBuiltinEntriesProvider provider = new DatapackBuiltinEntriesProvider(output, lookup,
                new RegistrySetBuilder()
                        .add(Registries.BIOME, ModBiomes::generateData)
                        .add(Registries.LEVEL_STEM, ModDimensions::generateStem)
                        .add(Registries.DIMENSION_TYPE, ModDimensions::generateDimension),
                Set.of(DragonMineZ.MOD_ID));

        generator.addProvider(event.includeServer(), provider);
    }
}
