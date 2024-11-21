package com.yuseix.dragonminez.events;

import com.yuseix.dragonminez.DragonMineZ;
import com.yuseix.dragonminez.character.models.AuraModel;
import com.yuseix.dragonminez.character.models.HumanSaiyanModel;
import com.yuseix.dragonminez.character.models.NamekianModel;
import com.yuseix.dragonminez.character.models.SlimHumanSaiyanModel;
import com.yuseix.dragonminez.character.models.bioandroid.BioAndroideModelo;
import com.yuseix.dragonminez.character.models.demoncold.DemonColdModel;
import com.yuseix.dragonminez.character.models.hair.FemHairModel;
import com.yuseix.dragonminez.character.models.hair.GohanDBSHairModel;
import com.yuseix.dragonminez.character.models.hair.GokuHairModel;
import com.yuseix.dragonminez.character.models.hair.VegetaHairModel;
import com.yuseix.dragonminez.character.models.majin.MajinFemaleModel;
import com.yuseix.dragonminez.character.models.majin.MajinGordoModel;
import com.yuseix.dragonminez.client.hud.PlayerHudOverlay;
import com.yuseix.dragonminez.client.hud.spaceship.SaiyanSpacePodOverlay;
import com.yuseix.dragonminez.init.MainParticles;
import com.yuseix.dragonminez.init.armor.client.model.ArmorBaseModel;
import com.yuseix.dragonminez.init.armor.client.model.ArmorPiccoloModel;
import com.yuseix.dragonminez.init.armor.client.model.ArmorSaiyanModel;
import com.yuseix.dragonminez.init.entity.client.model.namek.NamekNPCModel;
import com.yuseix.dragonminez.init.entity.client.model.projectil.KiBallModel;
import com.yuseix.dragonminez.init.items.models.BaculoEmptyModel;
import com.yuseix.dragonminez.init.particles.AjissaLeavesParticle;
import com.yuseix.dragonminez.init.particles.SacredLeavesParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//Anteriormente llamado ModListener o ClientEvents
//ACTUALMENTE LOS ModBusEvents son eventos que se ejecutan en el bus IModBusEvent
//Si un evento tiene "class x implements IMobBusEvent" TIENE que estar acá.
//Adicionalmente, estos eventos son aquellos que son lanzados al inicio del juego.

@Mod.EventBusSubscriber(modid = DragonMineZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBusEvents {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent e) {
        e.registerAboveAll("playerhud", PlayerHudOverlay.HUD_PLAYER);
        e.registerAboveAll("spaceshiphud", SaiyanSpacePodOverlay.HUD_SAIYAN);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        //RAZAS
        e.registerLayerDefinition(HumanSaiyanModel.LAYER_LOCATION, HumanSaiyanModel::createBodyLayer);
        e.registerLayerDefinition(SlimHumanSaiyanModel.LAYER_LOCATION, SlimHumanSaiyanModel::createBodyLayer);
        e.registerLayerDefinition(NamekianModel.LAYER_LOCATION, NamekianModel::createBodyLayer);
        e.registerLayerDefinition(BioAndroideModelo.LAYER_LOCATION, BioAndroideModelo::createBodyLayer);
        e.registerLayerDefinition(MajinGordoModel.LAYER_LOCATION, MajinGordoModel::createBodyLayer);
        e.registerLayerDefinition(MajinFemaleModel.LAYER_LOCATION, MajinFemaleModel::createBodyLayer);
        e.registerLayerDefinition(DemonColdModel.LAYER_LOCATION, DemonColdModel::createBodyLayer);

        e.registerLayerDefinition(AuraModel.LAYER_LOCATION, AuraModel::createBodyLayer);
        e.registerLayerDefinition(KiBallModel.LAYER_LOCATION, KiBallModel::createBodyLayer);

        //CABELLOS
        e.registerLayerDefinition(GokuHairModel.LAYER_LOCATION, GokuHairModel::createBodyLayer);
        e.registerLayerDefinition(FemHairModel.LAYER_LOCATION, FemHairModel::createBodyLayer);
        e.registerLayerDefinition(VegetaHairModel.LAYER_LOCATION, VegetaHairModel::createBodyLayer);
        e.registerLayerDefinition(GohanDBSHairModel.LAYER_LOCATION, GohanDBSHairModel::createBodyLayer);

        //ARMADURAS
        e.registerLayerDefinition(ArmorBaseModel.LAYER_LOCATION, ArmorBaseModel::createBodyLayer);
        e.registerLayerDefinition(ArmorSaiyanModel.LAYER_LOCATION, ArmorSaiyanModel::createBodyLayer);
        e.registerLayerDefinition(ArmorPiccoloModel.LAYER_LOCATION, ArmorPiccoloModel::createBodyLayer);

        //ENTIDADES CUSTOM EN BASE A MODELOS DE JAVA
        e.registerLayerDefinition(NamekNPCModel.LAYER_LOCATION, NamekNPCModel::createBodyLayer);

        //Armas en espalda
        e.registerLayerDefinition(BaculoEmptyModel.LAYER_LOCATION, BaculoEmptyModel::createBodyLayer);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MainParticles.AJISSA_LEAVES_PARTICLE.get(), AjissaLeavesParticle.Provider::new);
        event.registerSpriteSet(MainParticles.SACRED_LEAVES_PARTICLE.get(), SacredLeavesParticle.Provider::new);
    }

}
