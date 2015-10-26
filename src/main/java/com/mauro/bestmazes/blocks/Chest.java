package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.utility.dungeon.dungeonConfiguration.DungeonConfiguration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gabriele on 7/3/2015.
 */
public class Chest extends Block {

    public ArrayList<ItemStack> items;
    public int dir;

    public static final int NORTH = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;
    public static final int EAST = 5;

    public Chest(ArrayList<ItemStack> items, int dir){
        super(Material.anvil);
        this.items = items;
        this.dir = dir;
    }
}
