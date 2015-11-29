package com.gabrielemaurina.bestmazesbygabrielemaurina.tileEntities;

import com.gabrielemaurina.bestmazesbygabrielemaurina.items.Key;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;

/**
 * Created by Gabriele on 11/16/2015.
 */

public class MazeLockTileEntity extends TileEntity implements IInventory {

    public static final String name = "mazeLockTileEntity";
    ArrayList<ItemStack> items = new ArrayList<ItemStack>(1);

    public boolean shouldDrop(){
        return items.get(0).getItem() instanceof Key;
    }

    public MazeLockTileEntity(){
        items.add(new ItemStack(Blocks.air));
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return items.get(p_70301_1_);
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return items.get(p_70304_1_);
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        items.set(p_70299_1_,p_70299_2_);
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    public void resetInventory(){
        items.set(0,new ItemStack(Blocks.air));
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack itemStack) {
        return itemStack.getItem() instanceof Key;
    }


    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        NBTTagList tagList = tagCompound.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < items.size()) {
                items.set(slot, (ItemStack.loadItemStackFromNBT(tag)!=null?ItemStack.loadItemStackFromNBT(tag):new ItemStack(Blocks.air)));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        //save all(1) inventory slots
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < items.size(); i++) {
            ItemStack stack = items.get(i);
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Inventory", itemList);
    }


    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tileTag = new NBTTagCompound();
        this.writeToNBT(tileTag);
        return new S35PacketUpdateTileEntity(this.xCoord,this.yCoord,this.zCoord,0,tileTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }
}