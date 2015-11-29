package com.gabrielemaurina.bestmazesbygabrielemaurina.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ChestGenHooks;

import java.util.ArrayList;
import java.util.Random;

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

    public static ArrayList<ItemStack> genDungeonLootItems(Random random){
        int n = random.nextInt(3) + 1;
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        for(int i = 0; i < n; i++) {
            loot.add(ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).getOneItem(random));
        }
        return loot;
    }
}
