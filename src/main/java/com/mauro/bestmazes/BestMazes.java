package com.mauro.bestmazes;

/**
 * Created by Gabriele on 6/24/2015.
 */

import com.google.common.collect.Sets;
import com.mauro.bestmazes.proxy.IProxy;
import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.utility.BestMazesInitializer;
import com.mauro.bestmazes.utility.Point3D;
import com.mauro.bestmazes.utility.inflatables.InflatableCylider;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Set;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class BestMazes
{
    @Mod.Instance(Reference.MOD_ID)
    public static BestMazes instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){}

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        BestMazesInitializer.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){}
}
