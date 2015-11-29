package com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs;

import com.gabrielemaurina.bestmazesbygabrielemaurina.items.BestMazesItems;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class TaigaMinotaur extends Minotaur{

    public static String type = DungeonReferences.TAIGA;
    public static String name = type + "Minotaur";

    public TaigaMinotaur(World world){
        super(world);
        drop.add(new ItemStack(BestMazesItems.taigaKey, 1));
    }
}
