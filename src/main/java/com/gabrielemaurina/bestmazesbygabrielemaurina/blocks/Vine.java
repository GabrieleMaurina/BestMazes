package com.gabrielemaurina.bestmazesbygabrielemaurina.blocks;

import net.minecraft.init.Blocks;

/**
 * Created by Gabriele on 11/1/2015.
 */
public class Vine extends SpecialBlock {
    public static final int SOUTH = 1;
    public static final int WEST = 2;
    public static final int NORTH = 4;
    public static final int EAST = 8;

    public Vine(int meta){
        super(Blocks.vine, meta, 0);
    }
}
