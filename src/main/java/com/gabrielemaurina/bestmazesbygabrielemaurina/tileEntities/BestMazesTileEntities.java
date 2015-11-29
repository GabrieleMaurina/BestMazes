package com.gabrielemaurina.bestmazesbygabrielemaurina.tileEntities;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Gabriele on 11/16/2015.
 */
public class BestMazesTileEntities {
    public static void initTileEntities(){
        GameRegistry.registerTileEntity(MazeLockTileEntity.class, MazeLockTileEntity.name);
        GameRegistry.registerTileEntity(MagicalTreeCoreTileEntity.class, MagicalTreeCoreTileEntity.name);
    }
}
