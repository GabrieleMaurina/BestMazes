package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 11/25/2015.
 */
public class MagicalLeaves extends BlockLeaves{
    public static final String name = "magicalLeaves";
    private IIcon icon;

    public MagicalLeaves(){
        super();
        setBlockTextureName(Reference.MOD_ID + ":" + name);
        setBlockName(name);
        setStepSound(soundTypeGrass);
        setCreativeTab(BestMazesTabs.bestMazesTab);
        setHardness(0.2F);
        setLightOpacity(1);
        setTickRandomly(false);
    }

    public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_){
        return icon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegister)
    {
        icon = iIconRegister.registerIcon(this.getTextureName());
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
        drop.add(new ItemStack(BestMazesBlocks.magicalLeaves, 1));
        return drop;
    }

    @Override
    public String[] func_150125_e(){
        return new String[]{name};
    }
}
