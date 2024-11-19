package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.*;
import com.yuseix.dragonminez.init.items.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("ALL")
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
    public static final RegistryObject<Item> POTHALA_LEFT =
            ITEM_REGISTER.register("pothala_left", () -> new Item(properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> POTHALA_RIGHT =
            ITEM_REGISTER.register("pothala_right", () -> new Item(properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> GREEN_POTHALA_LEFT =
            ITEM_REGISTER.register("green_pothala_left", () -> new Item(properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> GREEN_POTHALA_RIGHT =
            ITEM_REGISTER.register("green_pothala_right", () -> new Item(properties.stacksTo(1).fireResistant()));

    //ARMADURAS
    // GOKU NIÑO
    public static final RegistryObject<Item> GOKU_KID_ARMOR_CHESTPLATE = armorItem("goku_kid_armor_chestplate", ArmorItem.Type.CHESTPLATE, "goku_kid", false);
    public static final RegistryObject<Item> GOKU_KID_ARMOR_LEGGINGS = armorItem("goku_kid_armor_leggings", ArmorItem.Type.LEGGINGS, "goku_kid", false);
    public static final RegistryObject<Item> GOKU_KID_ARMOR_BOOTS = armorItem("goku_kid_armor_boots", ArmorItem.Type.BOOTS, "goku_kid", false);
    //GOKU GI
    public static final RegistryObject<Item> GOKU_ARMOR_CHESTPLATE = armorItem("goku_armor_chestplate", ArmorItem.Type.CHESTPLATE, "goku_gi", true);
    public static final RegistryObject<Item> GOKU_ARMOR_LEGGINGS = armorItem("goku_armor_leggings", ArmorItem.Type.LEGGINGS, "goku_gi", true);
    public static final RegistryObject<Item> GOKU_ARMOR_BOOTS = armorItem("goku_armor_boots", ArmorItem.Type.BOOTS, "goku_gi", true);
    //Goku Boku no hero (osea mid xdxdxddx)
    public static final RegistryObject<Item> GOKU_KAITO_ARMOR_CHESTPLATE = armorItem("goku_kaito_armor_chestplate", ArmorItem.Type.CHESTPLATE, "goku_gi_kaito", true);
    public static final RegistryObject<Item> GOKU_KAITO_ARMOR_LEGGINGS = armorItem("goku_kaito_armor_leggings", ArmorItem.Type.LEGGINGS, "goku_gi_kaito", true);
    public static final RegistryObject<Item> GOKU_KAITO_ARMOR_BOOTS = armorItem("goku_kaito_armor_boots", ArmorItem.Type.BOOTS, "goku_gi_kaito", true);
    //Goku Super
    public static final RegistryObject<Item> GOKU_SUPER_ARMOR_CHESTPLATE = armorItem("goku_super_armor_chestplate", ArmorItem.Type.CHESTPLATE, "goku_super", false);
    public static final RegistryObject<Item> GOKU_SUPER_ARMOR_LEGGINGS = armorItem("goku_super_armor_leggings", ArmorItem.Type.LEGGINGS, "goku_super", false);
    public static final RegistryObject<Item> GOKU_SUPER_ARMOR_BOOTS = armorItem("goku_super_armor_boots", ArmorItem.Type.BOOTS, "goku_super", false);
    // GOKU GT
    public static final RegistryObject<Item> GOKU_GT_ARMOR_CHESTPLATE = armorItem("goku_gt_armor_chestplate", ArmorItem.Type.CHESTPLATE, "goku_gt", false);
    public static final RegistryObject<Item> GOKU_GT_ARMOR_LEGGINGS = armorItem("goku_gt_armor_leggings", ArmorItem.Type.LEGGINGS, "goku_gt", false);
    public static final RegistryObject<Item> GOKU_GT_ARMOR_BOOTS = armorItem("goku_gt_armor_boots", ArmorItem.Type.BOOTS, "goku_gt", false);
    // GOTEN Z
    public static final RegistryObject<Item> GOTEN_ARMOR_CHESTPLATE = armorItem("goten_armor_chestplate", ArmorItem.Type.CHESTPLATE, "goten_gi", false);
    public static final RegistryObject<Item> GOTEN_ARMOR_LEGGINGS = armorItem("goten_armor_leggings", ArmorItem.Type.LEGGINGS, "goten_gi", false);
    public static final RegistryObject<Item> GOTEN_ARMOR_BOOTS = armorItem("goten_armor_boots", ArmorItem.Type.BOOTS, "goten_gi", false);
    // GOTEN TEEN (SUPER)
    public static final RegistryObject<Item> GOTEN_SUPER_ARMOR_CHESTPLATE = armorItem("goten_super_armor_chestplate", ArmorItem.Type.CHESTPLATE, "goten_dbs", false);
    public static final RegistryObject<Item> GOTEN_SUPER_ARMOR_LEGGINGS = armorItem("goten_super_armor_leggings", ArmorItem.Type.LEGGINGS, "goten_dbs", false);
    public static final RegistryObject<Item> GOTEN_SUPER_ARMOR_BOOTS = armorItem("goten_super_armor_boots", ArmorItem.Type.BOOTS, "goten_dbs", false);
    // GOHAN GI (SUPER)
    public static final RegistryObject<Item> GOHAN_SUPER_ARMOR_CHESTPLATE = armorItem("gohan_super_armor_chestplate", ArmorItem.Type.CHESTPLATE, "gohan_dbs", false);
    public static final RegistryObject<Item> GOHAN_SUPER_ARMOR_LEGGINGS = armorItem("gohan_super_armor_leggings", ArmorItem.Type.LEGGINGS, "gohan_dbs", false);
    public static final RegistryObject<Item> GOHAN_SUPER_ARMOR_BOOTS = armorItem("gohan_super_armor_boots", ArmorItem.Type.BOOTS, "gohan_dbs", false);
    //VEGETA SAGA SAIYAJIN ARMADURA
    public static final RegistryObject<Item> VEGETA_SAIYAN_ARMOR_CHESTPLATE = saiyArItem("vegeta_saiyan_armor_chestplate", ArmorItem.Type.CHESTPLATE, "vegeta_saiyan_armor", true);
    public static final RegistryObject<Item> VEGETA_SAIYAN_ARMOR_LEGGINGS = saiyArItem("vegeta_saiyan_armor_leggings", ArmorItem.Type.LEGGINGS, "vegeta_saiyan_armor", true);
    public static final RegistryObject<Item> VEGETA_SAIYAN_ARMOR_BOOTS = saiyArItem("vegeta_saiyan_armor_boots", ArmorItem.Type.BOOTS, "vegeta_saiyan_armor", true);
    //VEGETA SAGA NAMEK ARMOR
    public static final RegistryObject<Item> VEGETA_NAMEK_ARMOR_CHESTPLATE = armorItem("vegeta_namek_armor_chestplate", ArmorItem.Type.CHESTPLATE, "vegetanamek_armor", false);
    public static final RegistryObject<Item> VEGETA_NAMEK_ARMOR_LEGGINGS = armorItem("vegeta_namek_armor_leggings", ArmorItem.Type.LEGGINGS, "vegetanamek_armor", false);
    public static final RegistryObject<Item> VEGETA_NAMEK_ARMOR_BOOTS = armorItem("vegeta_namek_armor_boots", ArmorItem.Type.BOOTS, "vegetanamek_armor", false);
    //VEGETA ARMADURA DE Z
    public static final RegistryObject<Item> VEGETA_Z_ARMOR_CHESTPLATE = armorItem("vegeta_z_armor_chestplate", ArmorItem.Type.CHESTPLATE, "vegetaz_armor", false);
    public static final RegistryObject<Item> VEGETA_Z_ARMOR_LEGGINGS = armorItem("vegeta_z_armor_leggings", ArmorItem.Type.LEGGINGS, "vegetaz_armor", false);
    public static final RegistryObject<Item> VEGETA_Z_ARMOR_BOOTS = armorItem("vegeta_z_armor_boots", ArmorItem.Type.BOOTS, "vegetaz_armor", false);
    //VEGETA ROPA ENTRENAMIENTO - SAGA BUU
    public static final RegistryObject<Item> VEGETA_BUU_ARMOR_CHESTPLATE = armorItem("vegeta_buu_armor_chestplate", ArmorItem.Type.CHESTPLATE, "vegetabuu_armor", false);
    public static final RegistryObject<Item> VEGETA_BUU_ARMOR_LEGGINGS = armorItem("vegeta_buu_armor_leggings", ArmorItem.Type.LEGGINGS, "vegetabuu_armor", false);
    public static final RegistryObject<Item> VEGETA_BUU_ARMOR_BOOTS = armorItem("vegeta_buu_armor_boots", ArmorItem.Type.BOOTS, "vegetabuu_armor", false);
    //VEGETA ARMADURA DE SUPER
    public static final RegistryObject<Item> VEGETA_SUPER_ARMOR_CHESTPLATE = armorItem("vegeta_super_armor_chestplate", ArmorItem.Type.CHESTPLATE, "vegetasuper", false);
    public static final RegistryObject<Item> VEGETA_SUPER_ARMOR_LEGGINGS = armorItem("vegeta_super_armor_leggings", ArmorItem.Type.LEGGINGS, "vegetasuper", false);
    public static final RegistryObject<Item> VEGETA_SUPER_ARMOR_BOOTS = armorItem("vegeta_super_armor_boots", ArmorItem.Type.BOOTS, "vegetasuper", false);
    //PICCOLO
    public static final RegistryObject<Item> PICCOLO_ARMOR_HELMET = //TURBANTE
            ITEM_REGISTER.register("piccolo_armor_helmet", () -> new PiccoloArmorItem(ModArmorMaterials.KIKONO, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> PICCOLO_ARMOR_CHESTPLATE_CAPE = //CON CAPA
            ITEM_REGISTER.register("piccolo_armor_cape", () -> new PiccoloArmorItem(ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> PICCOLO_ARMOR_CHESTPLATE = armorItem("piccolo_armor_chestplate", ArmorItem.Type.CHESTPLATE, "piccolo_gi", false);
    public static final RegistryObject<Item> PICCOLO_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("piccolo_armor_leggings", () -> new PiccoloArmorItem(ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> PICCOLO_ARMOR_BOOTS =
            ITEM_REGISTER.register("piccolo_armor_boots", () -> new PiccoloArmorItem(ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1)));
    //DEMON GI (AZUL)
    public static final RegistryObject<Item> DEMON_GI_BLUE_ARMOR_CHESTPLATE = armorItem("demon_gi_blue_armor_chestplate", ArmorItem.Type.CHESTPLATE, "demon_gi_blue", false);
    public static final RegistryObject<Item> DEMON_GI_BLUE_ARMOR_LEGGINGS = armorItem("demon_gi_blue_armor_leggings", ArmorItem.Type.LEGGINGS, "demon_gi_blue", false);
    public static final RegistryObject<Item> DEMON_GI_BLUE_ARMOR_BOOTS = armorItem("demon_gi_blue_armor_boots", ArmorItem.Type.BOOTS, "demon_gi_blue", false);
    //BARDOCK DBZ
    public static final RegistryObject<Item> BARDOCK_DBZ_ARMOR_CHESTPLATE = saiyArItem("bardock_dbz_armor_chestplate", ArmorItem.Type.CHESTPLATE, "bardock_armor", false);
    public static final RegistryObject<Item> BARDOCK_DBZ_ARMOR_LEGGINGS = saiyArItem("bardock_dbz_armor_leggings", ArmorItem.Type.LEGGINGS, "bardock_armor", false);
    public static final RegistryObject<Item> BARDOCK_DBZ_ARMOR_BOOTS = saiyArItem("bardock_dbz_armor_boots", ArmorItem.Type.BOOTS, "bardock_armor", false);
    //BARDOCK SUPER
    public static final RegistryObject<Item> BARDOCK_SUPER_ARMOR_CHESTPLATE = saiyArItem("bardock_super_armor_chestplate", ArmorItem.Type.CHESTPLATE, "bardockdbs_armor", false);
    public static final RegistryObject<Item> BARDOCK_SUPER_ARMOR_LEGGINGS = saiyArItem("bardock_super_armor_leggings", ArmorItem.Type.LEGGINGS, "bardockdbs_armor", false);
    public static final RegistryObject<Item> BARDOCK_SUPER_ARMOR_BOOTS = saiyArItem("bardock_super_armor_boots", ArmorItem.Type.BOOTS, "bardockdbs_armor", false);
    //TURLES
    public static final RegistryObject<Item> TURLES_ARMOR_CHESTPLATE = saiyArItem("turles_armor_chestplate", ArmorItem.Type.CHESTPLATE, "turles_armor", false);
    public static final RegistryObject<Item> TURLES_ARMOR_LEGGINGS = saiyArItem("turles_armor_leggings", ArmorItem.Type.LEGGINGS, "turles_armor", false);
    public static final RegistryObject<Item> TURLES_ARMOR_BOOTS = saiyArItem("turles_armor_boots", ArmorItem.Type.BOOTS, "turles_armor", false);
    //TIEN
    public static final RegistryObject<Item> TIEN_ARMOR_CHESTPLATE = armorItem("tien_armor_chestplate", ArmorItem.Type.CHESTPLATE, "tien_armor", false);
    public static final RegistryObject<Item> TIEN_ARMOR_LEGGINGS = armorItem("tien_armor_leggings", ArmorItem.Type.LEGGINGS, "tien_armor", false);
    public static final RegistryObject<Item> TIEN_ARMOR_BOOTS = armorItem("tien_armor_boots", ArmorItem.Type.BOOTS, "tien_armor", false);
    //TRUNKS Z
    public static final RegistryObject<Item> TRUNKS_Z_ARMOR_CHESTPLATE = armorItem("trunks_z_armor_chestplate", ArmorItem.Type.CHESTPLATE, "trunks_z_armor", false);
    public static final RegistryObject<Item> TRUNKS_Z_ARMOR_LEGGINGS = armorItem("trunks_z_armor_leggings", ArmorItem.Type.LEGGINGS, "trunks_z_armor", false);
    public static final RegistryObject<Item> TRUNKS_Z_ARMOR_BOOTS = armorItem("trunks_z_armor_boots", ArmorItem.Type.BOOTS, "trunks_z_armor", false);
    //TRUNKS SUPER
    public static final RegistryObject<Item> TRUNKS_SUPER_ARMOR_CHESTPLATE = armorItem("trunks_super_armor_chestplate", ArmorItem.Type.CHESTPLATE, "trunks_dbs", false);
    public static final RegistryObject<Item> TRUNKS_SUPER_ARMOR_LEGGINGS = armorItem("trunks_super_armor_leggings", ArmorItem.Type.LEGGINGS, "trunks_dbs", false);
    public static final RegistryObject<Item> TRUNKS_SUPER_ARMOR_BOOTS = armorItem("trunks_super_armor_boots", ArmorItem.Type.BOOTS, "trunks_dbs", false);
    //TRUNKS KID DBZ
    public static final RegistryObject<Item> TRUNKS_KID_ARMOR_CHESTPLATE = armorItem("trunks_kid_armor_chestplate", ArmorItem.Type.CHESTPLATE, "trunks_gi", false);
    public static final RegistryObject<Item> TRUNKS_KID_ARMOR_LEGGINGS = armorItem("trunks_kid_armor_leggings", ArmorItem.Type.LEGGINGS, "trunks_gi", false);
    public static final RegistryObject<Item> TRUNKS_KID_ARMOR_BOOTS = armorItem("trunks_kid_armor_boots", ArmorItem.Type.BOOTS, "trunks_gi", false);
    // BROLY Z
    public static final RegistryObject<Item> BROLY_Z_ARMOR_CHESTPLATE = armorItem("broly_z_armor_chestplate", ArmorItem.Type.CHESTPLATE, "broly_dbz", false);
    public static final RegistryObject<Item> BROLY_Z_ARMOR_LEGGINGS = armorItem("broly_z_armor_leggings", ArmorItem.Type.LEGGINGS, "broly_dbz", false);
    public static final RegistryObject<Item> BROLY_Z_ARMOR_BOOTS = armorItem("broly_z_armor_boots", ArmorItem.Type.BOOTS, "broly_dbz", false);
    // BROLY SUPER
    public static final RegistryObject<Item> BROLY_SUPER_ARMOR_CHESTPLATE = saiyArItem("broly_super_armor_chestplate", ArmorItem.Type.CHESTPLATE, "broly_dbs", false);
    public static final RegistryObject<Item> BROLY_SUPER_ARMOR_LEGGINGS = saiyArItem("broly_super_armor_leggings", ArmorItem.Type.LEGGINGS, "broly_dbs", false);
    public static final RegistryObject<Item> BROLY_SUPER_ARMOR_BOOTS = saiyArItem("broly_super_armor_boots", ArmorItem.Type.BOOTS, "broly_dbs", false);
    // SHIN
    public static final RegistryObject<Item> SHIN_ARMOR_CHESTPLATE = armorItem("shin_armor_chestplate", ArmorItem.Type.CHESTPLATE, "kaioshin", false);
    public static final RegistryObject<Item> SHIN_ARMOR_LEGGINGS = armorItem("shin_armor_leggings", ArmorItem.Type.LEGGINGS, "kaioshin", false);
    public static final RegistryObject<Item> SHIN_ARMOR_BOOTS = armorItem("shin_armor_boots", ArmorItem.Type.BOOTS, "kaioshin", false);
    // TROPAS DEL ORGULLO
    public static final RegistryObject<Item> PRIDE_TROOPS_ARMOR_CHESTPLATE = armorItem("pride_troops_armor_chestplate", ArmorItem.Type.CHESTPLATE, "pride_troper", false);
    public static final RegistryObject<Item> PRIDE_TROOPS_ARMOR_LEGGINGS = armorItem("pride_troops_armor_leggings", ArmorItem.Type.LEGGINGS, "pride_troper", false);
    public static final RegistryObject<Item> PRIDE_TROOPS_ARMOR_BOOTS = armorItem("pride_troops_armor_boots", ArmorItem.Type.BOOTS, "pride_troper", false);
    // HIT
    public static final RegistryObject<Item> HIT_ARMOR_CHESTPLATE = armorItem("hit_armor_chestplate", ArmorItem.Type.CHESTPLATE, "hit", false);
    public static final RegistryObject<Item> HIT_ARMOR_LEGGINGS = armorItem("hit_armor_leggings", ArmorItem.Type.LEGGINGS, "hit", false);
    public static final RegistryObject<Item> HIT_ARMOR_BOOTS = armorItem("hit_armor_boots", ArmorItem.Type.BOOTS, "hit", false);

    //LÍQUIDOS
    public static final RegistryObject<Item> HEALING_BUCKET = ITEM_REGISTER.register("healing_liquid_bucket",
            () -> new BucketItem(MainFluids.SOURCE_HEALING, properties.craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> NAMEK_WATER_BUCKET = ITEM_REGISTER.register("namek_water_bucket",
            () -> new BucketItem(MainFluids.SOURCE_NAMEK, properties.craftRemainder(Items.BUCKET).stacksTo(1)));

    //MINERALES
    public static final RegistryObject<Item> GETE_SCRAP = regItem("gete_scrap");
    public static final RegistryObject<Item> GETE_INGOT = regItem("gete_ingot");
    public static final RegistryObject<Item> KIKONO_SHARD = regItem("kikono_shard");

    //DRAGON BALL RADAR
    public static final RegistryObject<Item> DBALL_RADAR_ITEM = ITEM_REGISTER.register("dball_radar", DragonBallRadarItem::new);
    public static final RegistryObject<Item> NAMEKDBALL_RADAR_ITEM = ITEM_REGISTER.register("namekdball_radar", NamekDragonBallRadarItem::new);
    public static final RegistryObject<Item> RADAR_PIECE = ITEM_REGISTER.register("radar_piece",
            () -> new Item(properties.stacksTo(16)));
    public static final RegistryObject<Item> T1_RADAR_CHIP = ITEM_REGISTER.register("t1_radar_chip",
            () -> new Item(properties.stacksTo(16)));
    public static final RegistryObject<Item> T1_RADAR_CPU = ITEM_REGISTER.register("t1_radar_cpu",
            () -> new Item(properties.stacksTo(16)));
    public static final RegistryObject<Item> T2_RADAR_CHIP = ITEM_REGISTER.register("t2_radar_chip",
            () -> new Item(properties.stacksTo(16)));
    public static final RegistryObject<Item> T2_RADAR_CPU = ITEM_REGISTER.register("t2_radar_cpu",
            () -> new Item(properties.stacksTo(16)));

    //ENTIDADES (VEHÍCULOS)
    public static final RegistryObject<Item> NUBE_ITEM = ITEM_REGISTER.register("flying_nimbus", FlyingNimbusItem::new);
    public static final RegistryObject<Item> NAVE_SAIYAN_ITEM = ITEM_REGISTER.register("saiyan_ship", SaiyanShipItem::new);

    //ARMOR STATION/ARMOR CRAFTING PATTERNS
    public static final RegistryObject<Item> ARMOR_CRAFTING_KIT = ITEM_REGISTER.register("armor_crafting_kit",
            () -> new ArmorCraftingKitItem(properties.stacksTo(1)));
    public static final RegistryObject<Item> KIKONO_STRING = regItem("kikono_string");
    public static final RegistryObject<Item> KIKONO_CLOTH = regItem("kikono_cloth");
    public static final RegistryObject<Item> BLANK_PATTERN_Z = regItem("blank_pattern_z");
    public static final RegistryObject<Item> BLANK_PATTERN_SUPER = regItem("blank_pattern_super");
    public static final RegistryObject<Item> PATTERN_GOKU_KID = regItem("pattern_goku_kid");
    public static final RegistryObject<Item> PATTERN_GOKU1 = regItem("pattern_goku1");
    public static final RegistryObject<Item> PATTERN_GOKU2 = regItem("pattern_goku2");
    public static final RegistryObject<Item> PATTERN_GOKU_SUPER = regItem("pattern_goku_super");
    public static final RegistryObject<Item> PATTERN_GOKU_GT = regItem("pattern_goku_gt");
    public static final RegistryObject<Item> PATTERN_GOTEN = regItem("pattern_goten");
    public static final RegistryObject<Item> PATTERN_GOTEN_SUPER = regItem("pattern_goten_super");
    public static final RegistryObject<Item> PATTERN_VEGETA1 = regItem("pattern_vegeta1");
    public static final RegistryObject<Item> PATTERN_VEGETA2 = regItem("pattern_vegeta2");
    public static final RegistryObject<Item> PATTERN_VEGETA_Z = regItem("pattern_vegeta_z");
    public static final RegistryObject<Item> PATTERN_VEGETA_BUU = regItem("pattern_vegeta_buu");
    public static final RegistryObject<Item> PATTERN_VEGETA_SUPER = regItem("pattern_vegeta_super");
    public static final RegistryObject<Item> PATTERN_PICCOLO = regItem("pattern_piccolo");
    public static final RegistryObject<Item> PATTERN_GOHAN1 = regItem("pattern_gohan1");
    public static final RegistryObject<Item> PATTERN_BARDOCK1 = regItem("pattern_bardock1");
    public static final RegistryObject<Item> PATTERN_BARDOCK2 = regItem("pattern_bardock2");
    public static final RegistryObject<Item> PATTERN_TURLES = regItem("pattern_turles");
    public static final RegistryObject<Item> PATTERN_TIEN = regItem("pattern_tien");
    public static final RegistryObject<Item> PATTERN_TRUNKS_Z = regItem("pattern_trunks_z");
    public static final RegistryObject<Item> PATTERN_TRUNKS_SUPER = regItem("pattern_trunks_super");
    public static final RegistryObject<Item> PATTERN_BROLY_Z = regItem("pattern_broly_z");
    public static final RegistryObject<Item> PATTERN_BROLY_SUPER = regItem("pattern_broly_super");
    public static final RegistryObject<Item> PATTERN_SHIN = regItem("pattern_shin");
    public static final RegistryObject<Item> PATTERN_PRIDE_TROOPS = regItem("pattern_pride_troops");
    public static final RegistryObject<Item> PATTERN_HIT = regItem("pattern_hit");

    //DRAGON BALLS
    public static final RegistryObject<Item> DBALL1_BLOCK_ITEM = ITEM_REGISTER.register("dball1",
            () -> new BlockItem(MainBlocks.DBALL1_BLOCK.get(), properties
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> DBALL2_BLOCK_ITEM = ITEM_REGISTER.register("dball2",
            () -> new BlockItem(MainBlocks.DBALL2_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL3_BLOCK_ITEM = ITEM_REGISTER.register("dball3",
            () -> new BlockItem(MainBlocks.DBALL3_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL4_BLOCK_ITEM = ITEM_REGISTER.register("dball4",
            () -> new BlockItem(MainBlocks.DBALL4_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL5_BLOCK_ITEM = ITEM_REGISTER.register("dball5",
            () -> new BlockItem(MainBlocks.DBALL5_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL6_BLOCK_ITEM = ITEM_REGISTER.register("dball6",
            () -> new BlockItem(MainBlocks.DBALL6_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL7_BLOCK_ITEM = ITEM_REGISTER.register("dball7",
            () -> new BlockItem(MainBlocks.DBALL7_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL1_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball1_namek",
            () -> new BlockItem(MainBlocks.DBALL1_NAMEK_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL2_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball2_namek",
            () -> new BlockItem(MainBlocks.DBALL2_NAMEK_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL3_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball3_namek",
            () -> new BlockItem(MainBlocks.DBALL3_NAMEK_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL4_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball4_namek",
            () -> new BlockItem(MainBlocks.DBALL4_NAMEK_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL5_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball5_namek",
            () -> new BlockItem(MainBlocks.DBALL5_NAMEK_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL6_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball6_namek",
            () -> new BlockItem(MainBlocks.DBALL6_NAMEK_BLOCK.get(), properties.stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DBALL7_NAMEK_BLOCK_ITEM = ITEM_REGISTER.register("dball7_namek",
            () -> new BlockItem(MainBlocks.DBALL7_NAMEK_BLOCK.get(), properties.stacksTo(1).fireResistant()));

    // I AM THE STORM THAT IS APPROACHING, PROVOKING DARK CLOUDS IN ISOLATION
    public static RegistryObject<Item> regItem(String name) {
        return ITEM_REGISTER.register(name, () -> new Item(properties.stacksTo(64)));
    }

    public static RegistryObject<Item> armorItem(String name, ArmorItem.Type armorType, String itemId, boolean isDamageOn) {
        return ITEM_REGISTER.register(name, () ->
                new DbzArmorItem(ModArmorMaterials.KIKONO, armorType,
                        new Item.Properties().fireResistant().stacksTo(1), itemId, isDamageOn));
    }

    public static RegistryObject<Item> saiyArItem(String name, ArmorItem.Type armorType, String itemId, boolean isDamageOn) {
        return ITEM_REGISTER.register(name, () ->
                new SaiyanArmorItem(ModArmorMaterials.KIKONO, armorType,
                        new Item.Properties().fireResistant().stacksTo(1), itemId, isDamageOn));
    }

    public static void register(IEventBus bus) {
        ITEM_REGISTER.register(bus);
    }
}