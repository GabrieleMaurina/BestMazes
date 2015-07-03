package com.mauro.bestmazes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Gabriele on 7/3/2015.
 */
public class SpecialBlock extends Block {

    public Block block;
    public int first;
    public int second;

    public SpecialBlock(Block block, int first, int second) {
        super(Material.anvil);
        this.block = block;
        this.first = first;
        this.second = second;
    }
}
