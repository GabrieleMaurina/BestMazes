package com.mauro.bestmazes.tileEntities;

import com.google.gson.Gson;
import com.mauro.bestmazes.utility.trees.MagicalTreeConfiguration;
import com.mauro.bestmazes.utility.trees.Node;
import com.mauro.bestmazes.utility.trees.Tree;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import java.util.Random;

/**
 * Created by Gabriele on 11/27/2015.
 */
public class MagicalTreeCoreTileEntity extends TileEntity{
    public static final String name = "magicalTreeCoreTileEntity";
    public Node base;

    public MagicalTreeCoreTileEntity(){
        base = new Tree(new MagicalTreeConfiguration(), new Random()).base;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        String json = tagCompound.getString(name);
        Gson gson = new Gson();
        base = gson.fromJson(json, Node.class);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        Gson gson = new Gson();
        String json = gson.toJson(base, Node.class);
        tagCompound.setString(name, json);
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
