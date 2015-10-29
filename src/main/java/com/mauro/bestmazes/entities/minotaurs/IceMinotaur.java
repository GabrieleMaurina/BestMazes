package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class IceMinotaur extends Minotaur{

    public static String type = DungeonReferences.ICE;
    public static String name = type + "Minotaur";

    public IceMinotaur(World world){
        super(world);
    }
}