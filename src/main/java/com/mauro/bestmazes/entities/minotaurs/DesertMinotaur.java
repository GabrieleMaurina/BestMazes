package com.mauro.bestmazes.entities.minotaurs;

import com.mauro.bestmazes.items.BestMazesItems;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class DesertMinotaur extends Minotaur{

    public static String type = DungeonReferences.DESERT;
    public static String name = type + "Minotaur";

    public DesertMinotaur(World world){
        super(world);
        key = BestMazesItems.desertKey;
    }
}
