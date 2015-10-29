package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class NarrowMinotaur extends Minotaur{

    public static String type = DungeonReferences.NARROW;
    public static String name = type + "Minotaur";

    public NarrowMinotaur(World world){
        super(world);
    }
}
