package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.items.BestMazesItems;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class OceanMinotaur extends Minotaur{

    public static String type = DungeonReferences.OCEAN;
    public static String name = type + "Minotaur";

    public OceanMinotaur(World world){
        super(world);
        drop = new ItemStack(BestMazesItems.oceanKey, 1);
    }
}