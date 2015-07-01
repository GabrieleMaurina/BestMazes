package com.mauro.bestmazes;

/**
 * Created by Gabriele on 6/24/2015.
 */

import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.PiselliteBricksSlab;
import com.mauro.bestmazes.common.StructureGenerator;
import com.mauro.bestmazes.handler.ConfigurationHandler;
import com.mauro.bestmazes.proxy.IProxy;
import com.mauro.bestmazes.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class BestMazes
{
    public static StructureGenerator structureGenerator;

    @Mod.Instance(Reference.MOD_ID)
    public static BestMazes instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        structureGenerator = new StructureGenerator();
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        GameRegistry.registerBlock(PiselliteBricks.piselliteBricks, "PiselliteBricks");
        GameRegistry.registerBlock(PiselliteBricksSlab.pbs, "PiselliteBricksSlab");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(structureGenerator, 50);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
