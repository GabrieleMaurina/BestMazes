package com.mauro.bestmazes.tabs;

import com.mauro.bestmazes.items.BestMazesItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Gabriele on 10/27/2015.
 */
public class BestMazesTab extends CreativeTabs {
    public static final String name = "bestMazesTab";

    public BestMazesTab(int nextID, String instTab) {
        super(nextID,instTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem(){
        return BestMazesItems.forestKey;
    }
}
