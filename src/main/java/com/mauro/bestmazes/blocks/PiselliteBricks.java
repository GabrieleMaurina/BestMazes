package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Gabriele on 6/26/2015.
 */
public class PiselliteBricks extends Block {

    static public Block piselliteBricks = new PiselliteBricks();

    public PiselliteBricks(){

        super(Material.anvil);
        setBlockTextureName(Reference.MOD_ID + ":pisellite1");
        setHardness(1000F);
        setResistance(18000000);

        setBlockName("PiselliteBricks");
        setStepSound(soundTypeStone);
        setCreativeTab(CreativeTabs.tabBlock);
    }
}
