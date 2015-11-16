package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import com.mauro.bestmazes.utility.BestMazesInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Gabriele on 6/26/2015.
 */
public class PiselliteBricks extends Block {
    
    public static final String name = "piselliteBricks";

    public PiselliteBricks(){

        super(Material.anvil);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
        setHardness(-1F);
        setResistance(18000000);

        setBlockName(name);
        setStepSound(soundTypeStone);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
    {
        return false;
    }
}
