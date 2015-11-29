package com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs;

import com.gabrielemaurina.bestmazesbygabrielemaurina.items.BestMazesItems;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class PlainMinotaur extends Minotaur{

    public static String type = DungeonReferences.PLAIN;
    public static String name = type + "Minotaur";

    public PlainMinotaur(World world){
        super(world);
        drop.add(new ItemStack(BestMazesItems.plainKey, 1));
    }
}
