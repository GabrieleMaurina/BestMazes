package com.mauro.bestmazes.items;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.utility.BestMazesItemsBlocksTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Gabriele on 10/27/2015.
 */
public class Key extends Item {
    public String type;
    public String name;
    public Key(String type){
        super();
        this.type = type;
        this.name = type + "Key";
        setUnlocalizedName(name);
        //setTextureName(Reference.MOD_ID + ":keys/" + name);
        setTextureName(Reference.MOD_ID + ":keys/key");
        setCreativeTab(BestMazesItemsBlocksTabs.bestMazesTab);
    }
}
