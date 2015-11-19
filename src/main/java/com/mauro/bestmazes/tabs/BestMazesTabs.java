package com.mauro.bestmazes.tabs;

import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class BestMazesTabs {
    public static BestMazesTab bestMazesTab;
    public static void initTabs(){
        bestMazesTab = new BestMazesTab(CreativeTabs.getNextID(), BestMazesTab.name);
    }
}
