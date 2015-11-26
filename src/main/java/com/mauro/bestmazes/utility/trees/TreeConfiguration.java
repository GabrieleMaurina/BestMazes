package com.mauro.bestmazes.utility.trees;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/25/2015.
 */
public abstract class TreeConfiguration {
    public int radius = 1;
    public double length = 10.0;
    public double kLength = 1.0;
    public double spread = Math.PI / 6;
    public double bonusSpread = Math.PI / 6;
    public int size = 3;
    public int nSons = 4;
    public double fatherPull = 0.5;
    public int leavesSize = 5;
    public Block wood = Blocks.log;
    public Block leaves = Blocks.leaves;
}
