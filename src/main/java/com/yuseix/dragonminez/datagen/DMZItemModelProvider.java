package com.yuseix.dragonminez.datagen;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.MainBlocks;
import com.yuseix.dragonminez.init.MainItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMZItemModelProvider extends ItemModelProvider {
    public DMZItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DragonMineZ.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Items (MainItems)
        simpleItem(MainItems.DBALL_RADAR_ITEM);
        simpleItem(MainItems.MIGHT_TREE_FRUIT);
        simpleItem(MainItems.NUBE_ITEM);
        simpleItem(MainItems.SENZU_BEAN);
        simpleItem(MainItems.CAPSULA_ROJA);
        simpleItem(MainItems.CAPSULA_VERDE);
        simpleItem(MainItems.CAPSULA_ANARANJADA);
        simpleItem(MainItems.CAPSULA_AZUL);
        simpleItem(MainItems.CAPSULA_MORADA);
        simpleItem(MainItems.POTHALA_LEFT);
        simpleItem(MainItems.POTHALA_RIGHT);
        simpleItem(MainItems.GREEN_POTHALA_LEFT);
        simpleItem(MainItems.GREEN_POTHALA_RIGHT);
        simpleItem(MainItems.MEDICINA_CORAZON);
        simpleItem(MainItems.HEALING_BUCKET);

        //Comidas
        simpleItem(MainItems.COMIDA_DINO_RAW);
        simpleItem(MainItems.COMIDA_DINO_COOKED);
        simpleItem(MainItems.DINO_TAIL_RAW);
        simpleItem(MainItems.DINO_TAIL_COOKED);

        //Armaduras
        armorItem(MainItems.VEGETA_SAIYAN_ARMOR_CHESTPLATE);
        armorItem(MainItems.VEGETA_SAIYAN_ARMOR_LEGGINGS);
        armorItem(MainItems.VEGETA_SAIYAN_ARMOR_BOOTS);
        armorItem(MainItems.VEGETA_NAMEK_ARMOR_CHESTPLATE);
        armorItem(MainItems.VEGETA_NAMEK_ARMOR_LEGGINGS);
        armorItem(MainItems.VEGETA_NAMEK_ARMOR_BOOTS);
        armorItem(MainItems.GOKU_ARMOR_CHESTPLATE);
        armorItem(MainItems.GOKU_ARMOR_LEGGINGS);
        armorItem(MainItems.GOKU_ARMOR_BOOTS);
        armorItem(MainItems.PICCOLO_ARMOR_HELMET);
        armorItem(MainItems.PICCOLO_ARMOR_CHESTPLATE);
        armorItem(MainItems.PICCOLO_ARMOR_LEGGINGS);
        armorItem(MainItems.PICCOLO_ARMOR_BOOTS);
        armorItem(MainItems.DEMON_GI_BLUE_ARMOR_CHESTPLATE);
        armorItem(MainItems.DEMON_GI_BLUE_ARMOR_LEGGINGS);
        armorItem(MainItems.DEMON_GI_BLUE_ARMOR_BOOTS);

        //Crafting Armaduras
        simpleItem(MainItems.KIKONO_STRING);
        simpleItem(MainItems.KIKONO_CLOTH);
        simpleItem(MainItems.ARMOR_CRAFTING_KIT);
        patternItem(MainItems.PATTERN_GOTEN);
        patternItem(MainItems.PATTERN_GOKU1);
        patternItem(MainItems.PATTERN_GOHAN1);
        patternItem(MainItems.PATTERN_VEGETA1);
        patternItem(MainItems.PATTERN_PICCOLO);

        //Minerales
        simpleItem(MainItems.GETE_SCRAP);
        simpleItem(MainItems.GETE_INGOT);
        simpleItem(MainItems.KIKONO_SHARD);

        //Bloques (MainBlocks)
        blockItem(MainBlocks.NAMEK_BLOCK);
        blockItem(MainBlocks.NAMEK_DIRT);
        blockItem(MainBlocks.NAMEK_STONE);
        blockItem(MainBlocks.NAMEK_COBBLESTONE);
        blockItem(MainBlocks.NAMEK_AJISSA_PLANKS);
        blockItem(MainBlocks.NAMEK_AJISSA_LEAVES);
        blockItem(MainBlocks.NAMEK_SACRED_PLANKS);
        blockItem(MainBlocks.NAMEK_SACRED_LEAVES);
        blockItem(MainBlocks.GETE_BLOCK);
        blockItem(MainBlocks.NAMEK_KIKONO_ORE);
        blockItem(MainBlocks.KIKONO_BLOCK);
        blockItem(MainBlocks.NAMEK_DIAMOND_ORE);
        blockItem(MainBlocks.NAMEK_GOLD_ORE);
        blockItem(MainBlocks.NAMEK_IRON_ORE);
        blockItem(MainBlocks.NAMEK_LAPIS_ORE);
        blockItem(MainBlocks.NAMEK_REDSTONE_ORE);
        blockItem(MainBlocks.NAMEK_COAL_ORE);
        blockItem(MainBlocks.NAMEK_EMERALD_ORE);
        blockItem(MainBlocks.NAMEK_COPPER_ORE);
        blockItem(MainBlocks.NAMEK_DEEPSLATE_DIAMOND);
        blockItem(MainBlocks.NAMEK_DEEPSLATE_GOLD);
        blockItem(MainBlocks.NAMEK_DEEPSLATE_IRON);
        blockItem(MainBlocks.NAMEK_DEEPSLATE_LAPIS);
        blockItem(MainBlocks.NAMEK_DEEPSLATE_REDSTONE);
        blockItem(MainBlocks.NAMEK_DEEPSLATE_COAL);
        blockItem(MainBlocks.NAMEK_DEEPSLATE_EMERALD);
        blockItem(MainBlocks.NAMEK_DEEPSLATE_COPPER);
        blockItem(MainBlocks.GETE_FURNACE);
        blockItem(MainBlocks.TIME_CHAMBER_PORTAL);

        //Vegetacion
        blockItem(MainBlocks.CHRYSANTHEMUM_FLOWER);
        blockItem(MainBlocks.AMARYLLIS_FLOWER);
        blockItem(MainBlocks.MARIGOLD_FLOWER);
        blockItem(MainBlocks.CATHARANTHUS_ROSEUS_FLOWER);
        blockItem(MainBlocks.TRILLIUM_FLOWER);
        blockItem(MainBlocks.NAMEK_FERN);
        blockItem(MainBlocks.SACRED_CHRYSANTHEMUM_FLOWER);
        blockItem(MainBlocks.SACRED_AMARYLLIS_FLOWER);
        blockItem(MainBlocks.SACRED_MARIGOLD_FLOWER);
        blockItem(MainBlocks.SACRED_CATHARANTHUS_ROSEUS_FLOWER);
        blockItem(MainBlocks.SACRED_TRILLIUM_FLOWER);
        blockItem(MainBlocks.SACRED_FERN);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DragonMineZ.MOD_ID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder armorItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DragonMineZ.MOD_ID, "item/armors/" + item.getId().getPath()));
    }
    private ItemModelBuilder patternItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DragonMineZ.MOD_ID, "item/patterns/" + item.getId().getPath()));
    }
    private ItemModelBuilder blockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DragonMineZ.MOD_ID, "block/" + item.getId().getPath()));
    }
    public void simpleBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(DragonMineZ.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
}
