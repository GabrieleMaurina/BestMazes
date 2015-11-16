package com.mauro.bestmazes.tileEntities;

import com.mauro.bestmazes.blocks.MazeLock;
import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.PiselliteBricksSlab;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Gabriele on 11/16/2015.
 */
public class BestMazesTileEntities {
    public static void initTileEntities(){
        GameRegistry.registerTileEntity(MazeLockTileEntity.class, MazeLockTileEntity.name);
    }
}
