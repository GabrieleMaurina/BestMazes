package com.gabrielemaurina.bestmazesbygabrielemaurina.blocks;

import com.gabrielemaurina.bestmazesbygabrielemaurina.proxy.ClientProxy;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import com.gabrielemaurina.bestmazesbygabrielemaurina.tabs.BestMazesTabs;
import com.gabrielemaurina.bestmazesbygabrielemaurina.teleporters.MazeTeleporter;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 12/3/2015.
 */
public class MazePortal extends Block {
    public static final String name = "mazePortal";
    public static final float xDim = 1.0F;
    public static final float yDim = 0.8F;
    public static final float zDim = 1.0F;

    public MazePortal(){
        super(Material.portal);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
        setBlockUnbreakable();
        setBlockName(name);
        setResistance(18000000);
        setBlockBounds(0.0F, 0.0F, 0.0F, xDim, yDim, zDim);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @Override
    public int getRenderType() {
        return ClientProxy.mazePortalRender.getRenderId();
    }

    @Override
    public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_) {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if(!world.isRemote && entity instanceof EntityPlayerMP) {
            EntityPlayerMP var4 = (EntityPlayerMP) entity;
            var4.mcServer.getConfigurationManager().transferPlayerToDimension(var4, 2, new MazeTeleporter(var4.mcServer.worldServerForDimension(2)));
            System.out.println(entity.worldObj.getChunkProvider().getClass());
        }
    }

    public static void genPortal(World world, int x, int y, int z){
        Drawer.fillParallelepipedon1(world, x, y, z, 5, 2, 5, BestMazesBlocks.piselliteBricks);
        Drawer.fillParallelepipedon1(world, x + 1, y + 1, z + 1, 3, 1, 3, BestMazesBlocks.mazePortal);
        Drawer.fillParallelepipedon1(world, x + 2, y + 1, z + 2, 1, 2, 1, BestMazesBlocks.piselliteBricks);
        world.setBlock(x + 2, y + 3, z + 2, BestMazesBlocks.mazeLock);
    }
}