package com.mauro.bestmazes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by Gabriele on 7/3/2015.
 */
public class Chest extends Block {
    public ArrayList<ItemStack> items;
    public Chest(ArrayList<ItemStack> items){
        super(Material.anvil);
        this.items = items;
    }
}
