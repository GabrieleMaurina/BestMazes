package com.mauro.bestmazes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Gabriele on 11/12/2015.
 */
public class Spawner extends Block {

    public static final String SKELETON = "Skeleton";
    public static final String ZOMBIE = "Zombie";
    public static final String SPIDER = "Spider";

    public String type;

    public Spawner(String type){
        super(Material.anvil);
        this.type = type;
    }
}
