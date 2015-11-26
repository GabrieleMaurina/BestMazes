package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.trees.MagicalTreeConfiguration;
import com.mauro.bestmazes.utility.trees.Tree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Gabriele on 11/24/2015.
 */
public class MagicalSapling extends BlockBush implements IGrowable {

    public static final String name = "magicalSapling";

    protected MagicalSapling()
    {
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

            if (world.getBlockLightValue(x, y + 1, z) >= 10 && random.nextInt(10) == 0)
            {
                grow(world, x, y, z, random);
            }
        }
    }

    public void grow(World world, int x, int y, int z, Random random)
    {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
        Tree.genTree(world, x, y, z, new MagicalTreeConfiguration(), random);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World world, Random random, int x, int y, int z)
    {
        return random.nextDouble() < 0.1;
    }

    public void func_149853_b(World world, Random random, int x, int y, int z)
    {
        grow(world, x, y, z, random);
    }
}
