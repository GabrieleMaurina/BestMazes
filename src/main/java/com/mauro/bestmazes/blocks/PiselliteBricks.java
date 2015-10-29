package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import com.mauro.bestmazes.utility.BestMazesInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Gabriele on 6/26/2015.
 */
public class PiselliteBricks extends Block {
    
    public static final String name = "piselliteBricks";

    public PiselliteBricks(){

        super(Material.anvil);
        setBlockTextureName(Reference.MOD_ID + ":pisellite1");
        setHardness(-1F);
        setResistance(18000000);

        setBlockName(name);
        setStepSound(soundTypeStone);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }
}
