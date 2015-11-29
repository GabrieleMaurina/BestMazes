package com.gabrielemaurina.bestmazesbygabrielemaurina.items;

import com.gabrielemaurina.bestmazesbygabrielemaurina.tabs.BestMazesTabs;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
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
        setTextureName(Reference.MOD_ID + ":keys/" + type + "Key");
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }
}
