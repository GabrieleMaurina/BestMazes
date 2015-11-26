package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 11/25/2015.
 */
public class MagicalLeaves extends Block implements IShearable {

    public static final String name = "magicalLeaves";

    public MagicalLeaves(){
        super(Material.leaves);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
        setBlockName(name);
        setStepSound(soundTypeGrass);
        setHardness(0.2F);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
        drop.add(new ItemStack(this, 1));
        return drop;
    }
}
