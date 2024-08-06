package com.yuseix.dragonminez.init;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.init.armor.GokuArmorItem;
import com.yuseix.dragonminez.init.armor.ModArmorMaterials;
import com.yuseix.dragonminez.init.armor.VegetaSaiyanArmorItem;
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

    //COMIDA
    public static final RegistryObject<Item> MIGHT_TREE_FRUIT = ITEM_REGISTER.register("might_tree_fruit", MightTreeFruitItem::new);
    public static final RegistryObject<Item> SENZU_BEAN = ITEM_REGISTER.register("senzu_bean", SenzuBeanItem::new);
    public static final RegistryObject<Item> COMIDA_DINO_RAW = ITEM_REGISTER.register("comida_dino_raw", ComidaDinoRawItem::new);
    public static final RegistryObject<Item> COMIDA_DINO_COOKED = ITEM_REGISTER.register("comida_dino_cooked", ComidaDinoCookedItem::new);
    public static final RegistryObject<Item> MEDICINA_CORAZON = ITEM_REGISTER.register("medicina_corazon", MedicinaCorazonItem::new);
    public static final RegistryObject<Item> DINO_TAIL_RAW = ITEM_REGISTER.register("dino_tail_raw", DinoTailRawItem::new);
    public static final RegistryObject<Item> DINO_TAIL_COOKED = ITEM_REGISTER.register("dino_tail_cooked", DinoTailCookedItem::new);

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
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE , new Item.Properties()
                            .fireResistant()
                    ));
    public static final RegistryObject<Item> VEGETA_SAIYAN_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("vegeta_saiyan_armor_leggings", () -> new VegetaSaiyanArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS , new Item.Properties()
                            .fireResistant()
                    ));
    public static final RegistryObject<Item> VEGETA_SAIYAN_ARMOR_BOOTS =
            ITEM_REGISTER.register("vegeta_saiyan_armor_boots", () -> new VegetaSaiyanArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS , new Item.Properties()
                            .fireResistant()
                    ));
    //GOKU GI
    public static final RegistryObject<Item> GOKU_ARMOR_CHESTPLATE =
            ITEM_REGISTER.register("goku_armor_chestplate", () -> new GokuArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.CHESTPLATE , new Item.Properties()
                            .fireResistant()
                    ));
    public static final RegistryObject<Item> GOKU_ARMOR_LEGGINGS =
            ITEM_REGISTER.register("goku_armor_leggings", () -> new GokuArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.LEGGINGS , new Item.Properties()
                            .fireResistant()
                    ));
    public static final RegistryObject<Item> GOKU_ARMOR_BOOTS =
            ITEM_REGISTER.register("goku_armor_boots", () -> new GokuArmorItem
                    (ModArmorMaterials.KIKONO, ArmorItem.Type.BOOTS , new Item.Properties()
                            .fireResistant()
                    ));

    //LÍQUIDOS
    /*TODO: Texturas Líquido de Curación
     *    Falta la textura del item de: Líquido de Curación (en un bucket)
     * labels: Estado: Disponible, Prioridad: Media, Tipo: Modelos
     */

    public static final RegistryObject<Item> HEALING_BUCKET = ITEM_REGISTER.register("healing_liquid_bucket",
            () -> new BucketItem(MainFluids.SOURCE_HEALING, properties
                    .craftRemainder(Items.BUCKET)
                    .stacksTo(1)
            ));

    //MINERALES
    public static final RegistryObject<Item> GETE = ITEM_REGISTER.register("gete_scrap", GeteScrapItem::new);
    public static final RegistryObject<Item> GETE_INGOT = ITEM_REGISTER.register("gete_ingot", GeteIngotItem::new);

    public static final RegistryObject<Item> KIKONO_SHARD = ITEM_REGISTER.register("kikono_shard",
            () -> new Item(properties
                    .stacksTo(64)
            ));


    //DRAGON BALL RADAR (TIERRA)
    public static final RegistryObject<Item> DBALL_RADAR_ITEM = ITEM_REGISTER.register("dball_radar", DragonBallRadarItem::new);

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

    public static void register(IEventBus bus) {
        ITEM_REGISTER.register(bus);
    }
}
