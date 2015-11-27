package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import com.mauro.bestmazes.tileEntities.MazeLockTileEntity;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.trees.MagicalTreeConfiguration;
import com.mauro.bestmazes.utility.trees.Tree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

import static net.minecraftforge.common.EnumPlantType.*;
import static net.minecraftforge.common.EnumPlantType.Plains;

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

            if (world.getBlockLightValue(x, y + 1, z) >= 10 && random.nextInt(10) == 0)
            {
                grow(world, x, y, z);
            }
        }
    }

    public void grow(World world, int x, int y, int z)
    {
        world.setBlock(x, y - 10, z, BestMazesBlocks.magicalTreeCore);
    }
}
