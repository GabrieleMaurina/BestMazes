package com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs;

import com.gabrielemaurina.bestmazesbygabrielemaurina.items.BestMazesItems;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class IceMinotaur extends Minotaur{

    public static String type = DungeonReferences.ICE;
    public static String name = type + "Minotaur";

    public IceMinotaur(World world){
        super(world);
        drop.add(new ItemStack(BestMazesItems.iceKey, 1));
    }
}