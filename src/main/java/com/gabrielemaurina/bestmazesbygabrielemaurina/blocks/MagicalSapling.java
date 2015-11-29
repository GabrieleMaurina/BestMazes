package com.gabrielemaurina.bestmazesbygabrielemaurina.blocks;

import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import com.gabrielemaurina.bestmazesbygabrielemaurina.tabs.BestMazesTabs;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Gabriele on 11/24/2015.
 */
public class MagicalSapling extends BlockBush{

    public static final String name = "magicalSapling";

    protected MagicalSapling()
    {
        super(Material.plants);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        setCreativeTab(BestMazesTabs.bestMazesTab);
        setBlockName(name);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
    }

    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, x, y, z, random);

            if (world.getBlockLightValue(x, y + 1, z) >= 10/* && random.nextInt(10) == 0*/)
            {
                grow(world, x, y, z);
            }
        }
    }

    public void grow(World world, int x, int y, int z)
    {
        world.setBlock(x, y - 10, z, BestMazesBlocks.magicalTreeCore);
        world.setBlockToAir(x, y, z);
    }
}
