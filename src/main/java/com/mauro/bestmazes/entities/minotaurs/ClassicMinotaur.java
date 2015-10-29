package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class ClassicMinotaur extends Minotaur{

    public static String type = DungeonReferences.CLASSIC;
    public static String name = type + "Minotaur";

    public ClassicMinotaur(World world){
        super(world);
    }
}
