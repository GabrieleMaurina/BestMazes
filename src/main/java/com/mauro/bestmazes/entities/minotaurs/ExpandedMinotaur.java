package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class ExpandedMinotaur extends Minotaur{

    public static String type = DungeonReferences.EXPANDED;
    public static String name = type + "Minotaur";

    public ExpandedMinotaur(World world){
        super(world);
    }
}
