package com.gabrielemaurina.bestmazesbygabrielemaurina;

/**
 * Created by Gabriele on 6/24/2015.
 */

import com.gabrielemaurina.bestmazesbygabrielemaurina.proxy.IProxy;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.BestMazesInitializer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
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
