package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * Created by Gabriele on 11/25/2015.
 */
public class TreeConfiguration {
    public int radius = 1;
    public int dRadius = -1;
    public double length = 10.0;
    public double kLength = 1.0;
    public double spread = Math.PI / 8;
    public double bonusSpread = Math.PI / 16;
    public int size = 3;
    public int nSons = 4;
    public double fatherPull = 0.5;
    public int leavesSize = 5;
    public double leavesProb = 1.0;
    public static int dProb = 2;
    public Block leaves = Blocks.leaves;
    public Block wood = Blocks.log;
    public Block planks = BestMazesBlocks.oakWoodPlanks;
    public double lianesProb = 0.1;
}
