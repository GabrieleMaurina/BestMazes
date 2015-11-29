package com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs;

import com.gabrielemaurina.bestmazesbygabrielemaurina.items.BestMazesItems;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
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
        drop.add(new ItemStack(BestMazesItems.roofedKey, 1));
    }
}
