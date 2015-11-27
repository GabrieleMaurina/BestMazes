package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import com.mauro.bestmazes.tileEntities.MagicalTreeCoreTileEntity;
import com.mauro.bestmazes.utility.inflatables.InflatableCylinder;
import com.mauro.bestmazes.utility.inflatables.InflatableMagicalBranch;
import com.mauro.bestmazes.utility.inflatables.InflatableFoliage;
import com.mauro.bestmazes.utility.trees.MagicalTreeConfiguration;
import com.mauro.bestmazes.utility.trees.Node;
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
    public static final MagicalTreeConfiguration mTC = new MagicalTreeConfiguration();

    public MagicalTreeCore(){
        super(Material.anvil);
        setBlockName(name);
        setStepSound(soundTypeWood);
        setHardness(-1F);
        setResistance(18000000F);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
        setTickRandomly(true);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, x, y, z, random);
            System.out.println("@@@@@@@@@@@");
            MagicalTreeCoreTileEntity mTTE = (MagicalTreeCoreTileEntity)world.getTileEntity(x, y, z);
            if(!grow(0, world, x, y, z, mTTE.base, random)){
                world.setBlock(x, y, z, mTC.wood);
            }
        }
    }

    private boolean grow(int index, World world, int x, int y, int z, Node father, Random random){

        Collections.shuffle(father.sons, random);
        for(Node son : father.sons) {
            if (!son.built){
                son.built = true;
                if(son.sons.size() == 0){
                    new InflatableFoliage(mTC.leavesSize, BestMazesBlocks.magicalLeaves, mTC.prob, random).inflateShape(world, (int) (x + son.x), (int) (y + son.y), (int) (z + son.z));
                }

                InflatableCylinder iC;
                int dX = (int) (son.x - father.x);
                int dY = (int) (son.y - father.y);
                int dZ = (int) (son.z - father.z);
                int radius = father.radius;
                int internalRadius = 0;
                Block wood = Blocks.log;

                if(index > 0){
                    iC = new InflatableCylinder(dX, dY, dZ, radius, internalRadius, wood);
                }
                else{
                    iC = new InflatableMagicalBranch(dX, dY, dZ, radius, internalRadius, wood);
                }

                iC.inflateShape(world, (int)(x + father.x), (int)(y + father.y), (int)(z + father.z));

                return true;
            }
            else if(grow(index + 1, world, x, y, z, son, random)){
                return true;
            }
        }

        return false;
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
