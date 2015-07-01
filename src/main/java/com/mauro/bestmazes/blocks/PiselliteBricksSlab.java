package com.mauro.bestmazes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Gabriele on 6/27/2015.
 */
public class PiselliteBricksSlab extends Block {

    public boolean state;
    public static PiselliteBricksSlab pbs = new PiselliteBricksSlab(false);
    public static PiselliteBricksSlab upbs = new PiselliteBricksSlab(true);

    public PiselliteBricksSlab(boolean state){

        super(Material.anvil);
        this.state = state;
    }
}
