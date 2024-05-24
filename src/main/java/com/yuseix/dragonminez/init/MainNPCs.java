package com.yuseix.dragonminez.init;

import com.google.common.collect.ImmutableSet;
import com.yuseix.dragonminez.DragonMineZ;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;

@SuppressWarnings("unused")
public final class MainNPCs {
    //NOTA: LOS ALDEANOS FUNCIONAN COMO NPCS, ASÍ QUE USAREMOS TÉCNICAMENTE ALDEANOS CON SKIN DE NAMEKIANOS/NPCS, ETC.

    public static final DeferredRegister<PoiType> INTEREST_REGISTER =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, DragonMineZ.MOD_ID);

    public static final DeferredRegister<VillagerProfession> PROFESSION_REGISTER =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, DragonMineZ.MOD_ID);

    /*TODO:
     * Cambiar el Bloque de Interés a uno propio, este puede ser uno de namek?
     * De manera adicional configurar modelos para la profesión del aldeano (en este caso el NPC)
     * labels: Estado: Disponible, Prioridad: Media, Tipo: Modelos, Tipo: Nueva Idea
     */
    public static final RegistryObject<PoiType> INTEREST_TEST = INTEREST_REGISTER.register("test",
            //maxTickets es la cantidad de aldeanos que pueden obtener el "trabajo" al mismo tiempo (como usarlo al mismo tiempo, no como limite de profesiones)
            //validRange es la distancia máxima a la que el aldeano puede estar del bloque para que cuente como "trabajo"
            () -> new PoiType(new HashSet<>(Blocks.TORCH.getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<VillagerProfession> PROFESSION_TEST = PROFESSION_REGISTER.register("test",
            () -> new VillagerProfession("test",
                    //Estos 2 parámetros son un predicate para determinar el Punto de Interés para la profesión de Aldeano
                    holder -> holder.get() == INTEREST_TEST.get(),
                    holder -> holder.get() == INTEREST_TEST.get(),
                    //Este parámetro es una lista de items necesarios para conseguir la profesión
                    ImmutableSet.of(),
                    //Este parámetro es una lista de puntos de interés que el aldeano puede usar de manera adicional
                    ImmutableSet.of(),
                    null));

    public static void register(IEventBus bus) {
        INTEREST_REGISTER.register(bus);
        PROFESSION_REGISTER.register(bus);
    }
}
