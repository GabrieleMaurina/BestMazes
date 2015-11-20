package com.mauro.bestmazes.items;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import com.mauro.bestmazes.utility.BestMazesInitializer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
        setTextureName(Reference.MOD_ID + ":keys/key");
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return true;
    }
}
