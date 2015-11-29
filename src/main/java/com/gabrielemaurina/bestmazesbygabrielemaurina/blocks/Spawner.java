package com.gabrielemaurina.bestmazesbygabrielemaurina.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Gabriele on 11/12/2015.
 */
public class Spawner extends Block {

    public static final String SKELETON = "Skeleton";
    public static final String ZOMBIE = "Zombie";
    public static final String SPIDER = "Spider";

    public static final Integer[] spawnEggsMetaData = new Integer[]{50, 51, 52, 54, 55, 56, 57, 58, 59, 60,
            61, 62, 65, 66, 90, 91, 92, 93, 94, 95, 96, 98, 100, 120};

    public String type;

    public Spawner(String type){
        super(Material.anvil);
        this.type = type;
    }
}
