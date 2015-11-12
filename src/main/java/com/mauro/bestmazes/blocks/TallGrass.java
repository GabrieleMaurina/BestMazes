package com.mauro.bestmazes.blocks;

import net.minecraft.init.Blocks;

/**
 * Created by Gabriele on 11/1/2015.
 */
public class TallGrass extends SpecialBlock {
    public static final int SHRUB = 0;
    public static final int TALLGRASS = 1;
    public static final int FERN = 2;

    public TallGrass(int meta){
        super(Blocks.tallgrass, meta, 0);
    }
}
