package com.gabrielemaurina.bestmazesbygabrielemaurina.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Gabriele on 7/3/2015.
 */
public class SpecialBlock extends Block {

    public Block block;
    public int first;
    public int second;

    public SpecialBlock(Block block, int meta, int flag) {
        super(Material.anvil);
        this.block = block;
        this.first = meta;
        this.second = flag;
    }
}
