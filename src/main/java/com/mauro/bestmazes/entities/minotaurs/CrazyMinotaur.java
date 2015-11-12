package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.items.BestMazesItems;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class CrazyMinotaur extends Minotaur{

    public static String type = DungeonReferences.CRAZY;
    public static String name = type + "Minotaur";

    public CrazyMinotaur(World world){
        super(world);
        key = BestMazesItems.crazyKey;
    }
}
