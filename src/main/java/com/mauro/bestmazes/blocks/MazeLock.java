package com.mauro.bestmazes.blocks;

import com.mauro.bestmazes.BestMazes;
import com.mauro.bestmazes.items.Key;
import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import com.mauro.bestmazes.tileEntities.MazeLockTileEntity;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/16/2015.
 */
public class MazeLock extends BlockContainer{

    public static final String name = "mazeLock";
    private IIcon icon;

    private static final int D_X = 3;
    private static final int D_Y = 5;

    public MazeLock(){
        super(Material.anvil);

        setHardness(-1F);
        setResistance(18000000);

        setBlockName(name);
        setStepSound(soundTypeAnvil);
        setCreativeTab(BestMazesTabs.bestMazesTab);
        setBlockTextureName(Reference.MOD_ID + ":" + name);
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icon = p_149651_1_.registerIcon(Reference.MOD_ID + ":" + name);
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return icon;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new MazeLockTileEntity();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        MazeLockTileEntity tileEntity = ((MazeLockTileEntity) world.getTileEntity(x, y, z));
        if (player.isSneaking() && tileEntity.shouldDrop()) {
            world.playSound(x, y, z, Reference.MOD_ID + ":lock", 1, 1, true);
            dropBlockAsItem(world, x, y, z + 1, tileEntity.getStackInSlot(0));
            tileEntity.resetInventory();
            lockMaze(world, x, y, z);
        }
        else if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof Key && !tileEntity.shouldDrop()) {
            world.playSound(x, y, z, Reference.MOD_ID + ":unlock", 1, 1, true);
            tileEntity.setInventorySlotContents(0, new ItemStack(player.getCurrentEquippedItem().getItem(), 1));
            player.inventory.consumeInventoryItem(player.getHeldItem().getItem());
            unlockMaze(world, x, y, z);
        }
        return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    private void lockMaze(World world, int x, int y, int z) {
        int realX = (world.getBlock(x - D_X, y, z) == BestMazesBlocks.mazeLock) ? x - D_X : x;
        int realY = (world.getBlock(x, y - D_Y, z) == BestMazesBlocks.mazeLock) ? y - D_Y : y;
        if(!checkKeys(world, realX, realY, z)){
            closeGates(world, realX + 1, realY + D_Y - 1, z - 1);
        }
    }

    private void unlockMaze(World world, int x, int y, int z){

        int realX = (world.getBlock(x - D_X, y, z) == BestMazesBlocks.mazeLock) ? x - D_X : x;
        int realY = (world.getBlock(x, y - D_Y, z) == BestMazesBlocks.mazeLock) ? y - D_Y : y;
        if(checkKeys(world, realX, realY, z)){
            openGates(world, realX + 1, realY + D_Y - 1, z - 1);
        }

    }

    private boolean checkKeys(World world, int x, int y, int z){
        Item item;
        Key[] keys = new Key[4];
        MazeLockTileEntity mLTE;

        mLTE = (MazeLockTileEntity)world.getTileEntity(x, y, z);
        if(mLTE == null) return false;
        item = mLTE.getStackInSlot(0).getItem();
        if(item instanceof Key) keys[0] = (Key)item;
        else return false;

        mLTE = (MazeLockTileEntity)world.getTileEntity(x + D_X, y, z);
        if(mLTE == null) return false;
        item = mLTE.getStackInSlot(0).getItem();
        if(item instanceof Key) keys[1] = (Key)item;
        else return false;

        mLTE = (MazeLockTileEntity)world.getTileEntity(x, y + D_Y, z);
        if(mLTE == null) return false;
        item = mLTE.getStackInSlot(0).getItem();
        if(item instanceof Key) keys[2] = (Key)item;
        else return false;

        mLTE = (MazeLockTileEntity)world.getTileEntity(x + D_X, y + D_Y, z);
        if(mLTE == null) return false;
        item = mLTE.getStackInSlot(0).getItem();
        if(item instanceof Key) keys[3] = (Key)item;
        else return false;

        if(
                keys[0].name.equals(keys[1].name) ||
                keys[0].name.equals(keys[2].name) ||
                keys[0].name.equals(keys[3].name) ||
                keys[1].name.equals(keys[2].name) ||
                keys[1].name.equals(keys[3].name) ||
                keys[2].name.equals(keys[3].name)
            )
            return false;

        return true;
    }

    private void openGates(World world, int x, int y, int z){
        boolean play = false;

        for(int i = 0; i < 2; i++){
            for(int e = 0; e < 3; e++){
                if(world.getBlock(x + i, y + e, z) == BestMazesBlocks.piselliteBricks){
                    play = true;
                    world.setBlockToAir(x + i, y + e, z);
                }
            }
        }

        if(play) {
            world.playSound(x, y + 1, z, Reference.MOD_ID + ":gate", 1, 1, true);
        }
    }

    private void closeGates(World world, int x, int y, int z){
        boolean play = false;

        for(int i = 0; i < 2; i++){
            for(int e = 0; e < 3; e++){
                if(world.getBlock(x + i, y + e, z) != BestMazesBlocks.piselliteBricks){
                    play = true;
                    StructureGenerator.setBlock(world, x + i, y + e, z, BestMazesBlocks.piselliteBricks);
                }
            }
        }

        if(play) {
            world.playSound(x, y + 1, z, Reference.MOD_ID + ":gate", 1, 1, true);
        }
    }

    @Override
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        return super.addHitEffects(worldObj, target, effectRenderer);
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        MazeLockTileEntity tileEntity = ((MazeLockTileEntity)world.getTileEntity(x,y,z));
        if(tileEntity.shouldDrop()){
            dropBlockAsItem(world, x, y, z, tileEntity.getStackInSlot(0));
        }
        super.onBlockPreDestroy(world, x, y, z, meta);
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
    {
        return false;
    }
}
