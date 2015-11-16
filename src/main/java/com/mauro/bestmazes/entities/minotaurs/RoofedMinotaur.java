package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.items.BestMazesItems;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class RoofedMinotaur extends Minotaur{

    public static String type = DungeonReferences.ROOFED;
    public static String name = type + "Minotaur";

    public RoofedMinotaur(World world){
        super(world);
        drop = new ItemStack(BestMazesItems.roofedKey, 1);
    }
}
