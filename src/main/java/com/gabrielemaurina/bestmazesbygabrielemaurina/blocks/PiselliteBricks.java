package com.gabrielemaurina.bestmazesbygabrielemaurina.blocks;

import com.gabrielemaurina.bestmazesbygabrielemaurina.tabs.BestMazesTabs;
import com.gabrielemaurina.bestmazesbygabrielemaurina.items.MinotaurIvoryPickaxe;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 6/26/2015.
 */
public class PiselliteBricks extends Block {
    
    public static final String name = "piselliteBricks";

    public PiselliteBricks(){

        super(Material.anvil);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
        setResistance(18000000);

        setBlockName(name);
        setStepSound(soundTypeStone);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
        Item item = player.inventory.getCurrentItem().getItem();
        return (item != null && item instanceof MinotaurIvoryPickaxe) ? 0.05F : -1F;
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        Item item = player.inventory.getCurrentItem().getItem();
        return (item != null && item instanceof MinotaurIvoryPickaxe);
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

}
