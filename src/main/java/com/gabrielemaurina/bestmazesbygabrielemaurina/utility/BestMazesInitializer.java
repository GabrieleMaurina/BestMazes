package com.gabrielemaurina.bestmazesbygabrielemaurina.utility;

import com.gabrielemaurina.bestmazesbygabrielemaurina.BestMazes;
import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.BestMazesEntities;
import com.gabrielemaurina.bestmazesbygabrielemaurina.items.BestMazesItems;
import com.gabrielemaurina.bestmazesbygabrielemaurina.tabs.BestMazesTabs;
import com.gabrielemaurina.bestmazesbygabrielemaurina.tileEntities.BestMazesTileEntities;
import com.gabrielemaurina.bestmazesbygabrielemaurina.worldgenerators.BestMazesWorldGenerators;

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
