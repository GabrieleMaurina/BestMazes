package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class WideMinotaur extends Minotaur{

    public static String type = DungeonReferences.WIDE;
    public static String name = type + "Minotaur";

    public WideMinotaur(World world){
        super(world);
    }
}