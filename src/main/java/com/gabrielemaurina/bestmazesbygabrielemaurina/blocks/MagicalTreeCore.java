package com.gabrielemaurina.bestmazesbygabrielemaurina.blocks;

import com.gabrielemaurina.bestmazesbygabrielemaurina.tabs.BestMazesTabs;
import com.gabrielemaurina.bestmazesbygabrielemaurina.tileEntities.MagicalTreeCoreTileEntity;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableFoliage;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableMagicalBranch;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees.MagicalTreeConfiguration;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableCylinder;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees.Node;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees.Tree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Random;

/**
 * Created by Gabriele on 11/27/2015.
 */
public class MagicalTreeCore extends BlockContainer {
    public static final String name = "magicalTreeCore";

    public MagicalTreeCore(){
        super(Material.anvil);
        setBlockName(name);
        setStepSound(soundTypeWood);
        setHardness(-1F);
        setResistance(18000000F);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new MagicalTreeCoreTileEntity();
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return false;
    }
}
