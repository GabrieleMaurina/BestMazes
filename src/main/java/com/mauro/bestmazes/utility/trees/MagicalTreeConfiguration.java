package com.mauro.bestmazes.utility.trees;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import net.minecraft.init.Blocks;

/**
 * Created by Gabriele on 11/25/2015.
 */
public class MagicalTreeConfiguration extends TreeConfiguration {
    public MagicalTreeConfiguration()
    {
        radius = 4;
        length = 20.0;
        kLength = 1.0;
        spread = Math.PI / 6;
        bonusSpread = Math.PI / 6;
        size = 4;
        nSons = 5;
        fatherPull = 0.95;
        wood = Blocks.log;
        leaves = BestMazesBlocks.magicalLeaves;
        leavesSize = 10;
    }
}
