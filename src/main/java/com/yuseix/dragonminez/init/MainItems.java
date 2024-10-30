package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.*;
import com.yuseix.dragonminez.init.items.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public final class MainItems {

    public static final Item.Properties properties = new Item.Properties();
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, DragonMineZ.MOD_ID);

    //CAPSULAS
    public static final RegistryObject<Item> CAPSULA_ROJA = ITEM_REGISTER.register("capsula_roja", CapsulaRojaItem::new);
    public static final RegistryObject<Item> CAPSULA_VERDE = ITEM_REGISTER.register("capsula_verde", CapsulaVerdeItem::new);
    public static final RegistryObject<Item> CAPSULA_ANARANJADA = ITEM_REGISTER.register("capsula_anaranjada", CapsulaNaranjaItem::new);
    public static final RegistryObject<Item> CAPSULA_AZUL = ITEM_REGISTER.register("capsula_azul", CapsulaAzulItem::new);
    public static final RegistryObject<Item> CAPSULA_MORADA = ITEM_REGISTER.register("capsula_morada", CapsulaMoradaItem::new);
    public static final RegistryObject<Item> SENZU_BEAN = ITEM_REGISTER.register("senzu_bean", SenzuBeanItem::new);

    //COMIDA
    public static final RegistryObject<Item> MIGHT_TREE_FRUIT = ITEM_REGISTER.register("might_tree_fruit", MightTreeFruitItem::new);
    public static final RegistryObject<Item> COMIDA_DINO_RAW = ITEM_REGISTER.register("comida_dino_raw", ComidaDinoRawItem::new);
    public static final RegistryObject<Item> COMIDA_DINO_COOKED = ITEM_REGISTER.register("comida_dino_cooked", ComidaDinoCookedItem::new);
    public static final RegistryObject<Item> MEDICINA_CORAZON = ITEM_REGISTER.register("medicina_corazon", MedicinaCorazonItem::new);
    public static final RegistryObject<Item> DINO_TAIL_RAW = ITEM_REGISTER.register("dino_tail_raw", DinoTailRawItem::new);
    public static final RegistryObject<Item> DINO_TAIL_COOKED = ITEM_REGISTER.register("dino_tail_cooked", DinoTailCookedItem::new);
    public static final RegistryObject<Item> FROG_LEGS_RAW = ITEM_REGISTER.register("frog_legs_raw", FrogLegsRawItem::new);
    public static final RegistryObject<Item> FROG_LEGS_COOKED = ITEM_REGISTER.register("frog_legs_cooked", FrogLegsCookedItem::new);

    //POTHALAS
    public static final RegistryObject<Item> POTHALA_RIGHT =
            ITEM_REGISTER.register("pothala_right", () -> new Item(properties
                    .stacksTo(1)
                    .fireResistant()));
    public static final RegistryObject<Item> POTHALA_LEFT =
            ITEM_REGISTER.register("pothala_left", () -> new Item(properties
                    .stacksTo(1)
                    .fireResistant()));
    public static final RegistryObject<Item> GREEN_POTHALA_RIGHT =
            ITEM_REGISTER.register("green_pothala_right", () -> new Item(properties
                    .stacksTo(1)
                    .fireResistant()));
    public static final RegistryObject<Item> GREEN_POTHALA_LEFT =
            ITEM_REGISTER.register("green_pothala_left", () -> new Item(properties
                    .stacksTo(1)
                    .fireResistant()));

    //ARMADURAS
    //VEGETA SAGA SAIYAJIN ARMADURA
    public static final RegistryObject<Item> VEGETA_SAIYAN_ARMOR_CHESTPLATE =
            ITEM_REGISTER.register("vegeta_saiyan_armor_chestplate", () -> new VegetaSaiyanArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> VEGETA_SAIYAN_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("vegeta_saiyan_armor_leggings", () -> new VegetaSaiyanArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> VEGETA_SAIYAN_ARMOR_BOOTS =
            ITEM_REGISTER.register("vegeta_saiyan_armor_boots", () -> new VegetaSaiyanArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    //VEGETA SAGA NAMEK ARMOR
    public static final RegistryObject<Item> VEGETA_NAMEK_ARMOR_CHESTPLATE =
            ITEM_REGISTER.register("vegeta_namek_armor_chestplate", () -> new VegetaNamekArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> VEGETA_NAMEK_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("vegeta_namek_armor_leggings", () -> new VegetaNamekArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> VEGETA_NAMEK_ARMOR_BOOTS =
            ITEM_REGISTER.register("vegeta_namek_armor_boots", () -> new VegetaNamekArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    //BARDOCK SUPER
    public static final RegistryObject<Item> BARDOCK_SUPER_ARMOR_CHESTPLATE =
            ITEM_REGISTER.register("bardock_super_armor_chestplate", () -> new BardockSuperArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> BARDOCK_SUPER_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("bardock_super_armor_leggings", () -> new BardockSuperArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> BARDOCK_SUPER_ARMOR_BOOTS =
            ITEM_REGISTER.register("bardock_super_armor_boots", () -> new BardockSuperArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    //BARDOCK DBZ
    public static final RegistryObject<Item> BARDOCK_DBZ_ARMOR_CHESTPLATE =
            ITEM_REGISTER.register("bardock_dbz_armor_chestplate", () -> new BardockDBZArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> BARDOCK_DBZ_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("bardock_dbz_armor_leggings", () -> new BardockDBZArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> BARDOCK_DBZ_ARMOR_BOOTS =
            ITEM_REGISTER.register("bardock_dbz_armor_boots", () -> new BardockDBZArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    //GOKU GI
    public static final RegistryObject<Item> GOKU_ARMOR_CHESTPLATE =
            ITEM_REGISTER.register("goku_armor_chestplate", () -> new GokuArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> GOKU_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("goku_armor_leggings", () -> new GokuArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> GOKU_ARMOR_BOOTS =
            ITEM_REGISTER.register("goku_armor_boots", () -> new GokuArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    //PICCOLO
    public static final RegistryObject<Item> PICCOLO_ARMOR_HELMET =
            ITEM_REGISTER.register("piccolo_armor_helmet", () -> new PiccoloArmorItem //TURBANTE
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.HELMET, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> PICCOLO_ARMOR_CHESTPLATE =
            ITEM_REGISTER.register("piccolo_armor_cape", () -> new PiccoloArmorItem //CON CAPA
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> PICCOLO_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("piccolo_armor_leggings", () -> new PiccoloArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> PICCOLO_ARMOR_BOOTS =
            ITEM_REGISTER.register("piccolo_armor_boots", () -> new PiccoloArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    //DEMON GI (AZUL)
    public static final RegistryObject<Item> DEMON_GI_BLUE_ARMOR_CHESTPLATE =
            ITEM_REGISTER.register("demon_gi_blue_armor_chestplate", () -> new DemonGiBlueArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> DEMON_GI_BLUE_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("demon_gi_blue_armor_leggings", () -> new DemonGiBlueArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS, new Item.Properties()
                            .fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> DEMON_GI_BLUE_ARMOR_BOOTS =
            ITEM_REGISTER.register("demon_gi_blue_armor_boots", () -> new DemonGiBlueArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS, new Item.Properties()
                            .fireResistant().stacksTo(1)));

    //LÍQUIDOS

    public static final RegistryObject<Item> HEALING_BUCKET = ITEM_REGISTER.register("healing_liquid_bucket",
            () -> new BucketItem(MainFluids.SOURCE_HEALING, properties
                    .craftRemainder(Items.BUCKET)
                    .stacksTo(1)
            ));

    public static final RegistryObject<Item> NAMEK_WATER_BUCKET = ITEM_REGISTER.register("namek_water_bucket",
            () -> new BucketItem(MainFluids.SOURCE_NAMEK, properties
                    .craftRemainder(Items.BUCKET)
                    .stacksTo(1)
            ));

    //MINERALES
    public static final RegistryObject<Item> GETE_SCRAP = ITEM_REGISTER.register("gete_scrap", GeteScrapItem::new);
    public static final RegistryObject<Item> GETE_INGOT = ITEM_REGISTER.register("gete_ingot", GeteIngotItem::new);
    public static final RegistryObject<Item> KIKONO_SHARD = ITEM_REGISTER.register("kikono_shard", KikonoShardItem::new);

    //ARMOR STATION/ARMOR CRAFTING
    public static final RegistryObject<Item> ARMOR_CRAFTING_KIT = ITEM_REGISTER.register("armor_crafting_kit",
            () -> new ArmorCraftingKitItem(properties.stacksTo(1)));
    public static final RegistryObject<Item> KIKONO_STRING = ITEM_REGISTER.register("kikono_string", KikonoStringItem::new);
    public static final RegistryObject<Item> KIKONO_CLOTH = ITEM_REGISTER.register("kikono_cloth", KikonoClothItem::new);
    public static final RegistryObject<Item> PATTERN_GOKU1 = ITEM_REGISTER.register("pattern_goku1",
            () -> new Item(properties.stacksTo(64)));
   public static final RegistryObject<Item> PATTERN_GOTEN = ITEM_REGISTER.register("pattern_goten",
            () -> new Item(properties.stacksTo(64)));
    public static final RegistryObject<Item> PATTERN_VEGETA1 = ITEM_REGISTER.register("pattern_vegeta1",
            () -> new Item(properties.stacksTo(64)));
    public static final RegistryObject<Item> PATTERN_VEGETA2 = ITEM_REGISTER.register("pattern_vegeta2",
            () -> new Item(properties.stacksTo(64)));
    public static final RegistryObject<Item> PATTERN_PICCOLO = ITEM_REGISTER.register("pattern_piccolo",
            () -> new Item(properties.stacksTo(64)));
    public static final RegistryObject<Item> PATTERN_GOHAN1 = ITEM_REGISTER.register("pattern_gohan1",
            () -> new Item(properties.stacksTo(64)));
    public static final RegistryObject<Item> PATTERN_BARDOCK1 = ITEM_REGISTER.register("pattern_bardock1",
            () -> new Item(properties.stacksTo(64)));
    public static final RegistryObject<Item> PATTERN_BARDOCK2 = ITEM_REGISTER.register("pattern_bardock2",
            () -> new Item(properties.stacksTo(64)));


    //DRAGON BALL RADAR
    public static final RegistryObject<Item> DBALL_RADAR_ITEM = ITEM_REGISTER.register("dball_radar", DragonBallRadarItem::new);
    public static final RegistryObject<Item> NAMEKDBALL_RADAR_ITEM = ITEM_REGISTER.register("namekdball_radar", NamekDragonBallRadarItem::new);

    //NUBE VOLADORA
    public static final RegistryObject<Item> NUBE_ITEM = ITEM_REGISTER.register("flying_nimbus", FlyingNimbusItem::new);

    //DRAGON BALLS
    public static final RegistryObject<Item> DBALL1_BLOCK_ITEM = ITEM_REGISTER.register("dball1",
            () -> new BlockItem(MainBlocks.DBALL1_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL2_BLOCK_ITEM = ITEM_REGISTER.register("dball2",
            () -> new BlockItem(MainBlocks.DBALL2_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL3_BLOCK_ITEM = ITEM_REGISTER.register("dball3",
            () -> new BlockItem(MainBlocks.DBALL3_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL4_BLOCK_ITEM = ITEM_REGISTER.register("dball4",
            () -> new BlockItem(MainBlocks.DBALL4_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL5_BLOCK_ITEM = ITEM_REGISTER.register("dball5",
            () -> new BlockItem(MainBlocks.DBALL5_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL6_BLOCK_ITEM = ITEM_REGISTER.register("dball6",
            () -> new BlockItem(MainBlocks.DBALL6_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL7_BLOCK_ITEM = ITEM_REGISTER.register("dball7",
            () -> new BlockItem(MainBlocks.DBALL7_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL1_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball1_namek",
            () -> new BlockItem(MainBlocks.DBALL1_NAMEK_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL2_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball2_namek",
            () -> new BlockItem(MainBlocks.DBALL2_NAMEK_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL3_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball3_namek",
            () -> new BlockItem(MainBlocks.DBALL3_NAMEK_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL4_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball4_namek",
            () -> new BlockItem(MainBlocks.DBALL4_NAMEK_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL5_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball5_namek",
            () -> new BlockItem(MainBlocks.DBALL5_NAMEK_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL6_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball6_namek",
            () -> new BlockItem(MainBlocks.DBALL6_NAMEK_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL7_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball7_namek",
            () -> new BlockItem(MainBlocks.DBALL7_NAMEK_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));


    public static void register(IEventBus bus) {
        ITEM_REGISTER.register(bus);
    }
}
