package com.mauro.bestmazes.utility;

import com.mauro.bestmazes.BestMazes;
import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.entities.BestMazesEntities;
import com.mauro.bestmazes.items.BestMazesItems;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import com.mauro.bestmazes.tileEntities.BestMazesTileEntities;
import com.mauro.bestmazes.worldgenerators.BestMazesWorldGenerators;

/**
 * Created by Gabriele on 10/27/2015.
 */
public class BestMazesInitializer {

    public static void init(){
        BestMazesWorldGenerators.initWorldGenerators();
        BestMazesTabs.initTabs();
        BestMazesItems.initItems();
        BestMazesBlocks.initBlocks();
        BestMazesEntities.initEntities();
        BestMazesTileEntities.initTileEntities();
        BestMazes.proxy.registerRenderers();
    }
}
