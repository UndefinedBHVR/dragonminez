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
        armorItem(MainItems.GOKU_ARMOR_CHESTPLATE);
        armorItem(MainItems.GOKU_ARMOR_LEGGINGS);
        armorItem(MainItems.GOKU_ARMOR_BOOTS);
        armorItem(MainItems.PICCOLO_ARMOR_CHESTPLATE);
        armorItem(MainItems.PICCOLO_ARMOR_LEGGINGS);
        armorItem(MainItems.PICCOLO_ARMOR_BOOTS);

        //Minerales
        simpleItem(MainItems.GETE_SCRAP);
        simpleItem(MainItems.GETE_INGOT);
        simpleItem(MainItems.KIKONO_SHARD);

        //Bloques (MainBlocks)
        blockItem(MainBlocks.NAMEK_BLOCK);
        simpleBlockItem(MainBlocks.NAMEK_GRASS_BLOCK);
        simpleBlockItem(MainBlocks.NAMEK_SACRED_GRASS_BLOCK);
        simpleBlockItemBlockTexture(MainBlocks.NAMEK_GRASS);
        simpleBlockItemBlockTexture(MainBlocks.NAMEK_SACRED_GRASS);
        blockItem(MainBlocks.NAMEK_DIRT);
        blockItem(MainBlocks.NAMEK_STONE);
        blockItem(MainBlocks.NAMEK_COBBLESTONE);
        blockItem(MainBlocks.NAMEK_DEEPSLATE);
        blockItem(MainBlocks.NAMEK_AJISSA_LOG);
        blockItem(MainBlocks.NAMEK_STRIPPED_AJISSA_LOG);;
        saplingItem(MainBlocks.NAMEK_AJISSA_SAPLING);
        blockItem(MainBlocks.NAMEK_AJISSA_WOOD);
        blockItem(MainBlocks.NAMEK_STRIPPED_AJISSA_WOOD);
        blockItem(MainBlocks.NAMEK_AJISSA_PLANKS);
        blockItem(MainBlocks.NAMEK_AJISSA_LEAVES);
        trapdoorItem(MainBlocks.NAMEK_AJISSA_TRAPDOOR);
        blockItem(MainBlocks.NAMEK_AJISSA_DOOR);
        simpleBlockItem(MainBlocks.NAMEK_AJISSA_SLAB);
        simpleBlockItem(MainBlocks.NAMEK_AJISSA_STAIRS);
        fenceItem(MainBlocks.NAMEK_AJISSA_FENCE);
        simpleBlockItem(MainBlocks.NAMEK_AJISSA_FENCE_GATE);
        buttonItem(MainBlocks.NAMEK_AJISSA_BUTTON);
        blockItem(MainBlocks.NAMEK_SACRED_LOG);
        blockItem(MainBlocks.NAMEK_STRIPPED_SACRED_LOG);
        saplingItem(MainBlocks.NAMEK_SACRED_SAPLING);
        blockItem(MainBlocks.NAMEK_SACRED_WOOD);
        blockItem(MainBlocks.NAMEK_STRIPPED_SACRED_WOOD);
        blockItem(MainBlocks.NAMEK_SACRED_PLANKS);
        blockItem(MainBlocks.NAMEK_SACRED_LEAVES);
        trapdoorItem(MainBlocks.NAMEK_SACRED_TRAPDOOR);
        blockItem(MainBlocks.NAMEK_SACRED_DOOR);
        simpleBlockItem(MainBlocks.NAMEK_SACRED_SLAB);
        simpleBlockItem(MainBlocks.NAMEK_SACRED_STAIRS);
        fenceItem(MainBlocks.NAMEK_SACRED_FENCE);
        simpleBlockItem(MainBlocks.NAMEK_SACRED_FENCE_GATE);
        buttonItem(MainBlocks.NAMEK_SACRED_BUTTON);
        blockItem(MainBlocks.GETE_BLOCK);
        blockItem(MainBlocks.GETE_ORE);
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
        /*blockItem(MainBlocks.KIKONO_ARMOR_STATION);*/
        blockItem(MainBlocks.NAMEK_GRASS);
        blockItem(MainBlocks.NAMEK_SACRED_GRASS);
        blockItem(MainBlocks.DBALL1_BLOCK);
        blockItem(MainBlocks.DBALL2_BLOCK);
        blockItem(MainBlocks.DBALL3_BLOCK);
        blockItem(MainBlocks.DBALL4_BLOCK);
        blockItem(MainBlocks.DBALL5_BLOCK);
        blockItem(MainBlocks.DBALL6_BLOCK);
        blockItem(MainBlocks.DBALL7_BLOCK);
        blockItem(MainBlocks.DBALL1_NAMEK_BLOCK);
        /*blockItem(MainBlocks.DBALL2_NAMEK_BLOCK);
        blockItem(MainBlocks.DBALL3_NAMEK_BLOCK);
        blockItem(MainBlocks.DBALL4_NAMEK_BLOCK);
        blockItem(MainBlocks.DBALL5_NAMEK_BLOCK);
        blockItem(MainBlocks.DBALL6_NAMEK_BLOCK);
        blockItem(MainBlocks.DBALL7_NAMEK_BLOCK);*/
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
    private ItemModelBuilder blockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DragonMineZ.MOD_ID, "block/" + item.getId().getPath()));
    }
    public void simpleBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(DragonMineZ.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DragonMineZ.MOD_ID, "block/" + item.getId().getPath()));
    }
    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }
    public void fenceItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(DragonMineZ.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    public void buttonItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(DragonMineZ.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DragonMineZ.MOD_ID, "block/" + item.getId().getPath()));
    }
}
