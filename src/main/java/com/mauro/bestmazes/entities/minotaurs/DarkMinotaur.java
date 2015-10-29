package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class DarkMinotaur extends Minotaur{

    public static String type = DungeonReferences.DARK;
    public static String name = type + "Minotaur";

    public DarkMinotaur(World world){
        super(world);
    }
}
