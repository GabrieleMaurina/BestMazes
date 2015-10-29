package com.mauro.bestmazes.tabs;

import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class BestMazesTabs {
    public static BestMazesKeysTab bestMazesTab;
    public static void initTabs(){
        bestMazesTab = new BestMazesKeysTab(CreativeTabs.getNextID(), BestMazesKeysTab.name);
    }
}
