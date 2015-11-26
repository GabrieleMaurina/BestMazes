package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.items.MinotaurIvoryPickaxe;
import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/12/2015.
 */
public class PiselliteBricksSlab extends BlockSlab {

    public static final String name = "piselliteBricksSlab";

    public PiselliteBricksSlab(){

        super(false, Material.anvil);
        setBlockTextureName(Reference.MOD_ID + ":" + PiselliteBricks.name);
        setHardness(-1F);
        setResistance(18000000);
        setBlockName(name);
        setStepSound(soundTypeStone);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
        return player.inventory.getCurrentItem().getItem() instanceof MinotaurIvoryPickaxe ? 0.05F : -1F;
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return player.inventory.getCurrentItem().getItem() instanceof MinotaurIvoryPickaxe;
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
    {
        return false;
    }

    public String func_150002_b(int p_150002_1_)
    {
        return getUnlocalizedName();
    }
}
