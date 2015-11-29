package com.gabrielemaurina.bestmazesbygabrielemaurina.tileEntities;

import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableCylinder;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableFoliage;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableMagicalBranch;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableRoundedCylinder;
import com.gabrielemaurina.bestmazesbygabrielemaurina.worldgenerators.StructureGenerator;
import com.google.gson.Gson;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees.MagicalTreeConfiguration;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees.Node;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees.Tree;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Random;

/**
 * Created by Gabriele on 11/27/2015.
 */
public class MagicalTreeCoreTileEntity extends TileEntity{
    private static final int UPDATE_RATE = 1000;
    public static final String name = "magicalTreeCoreTileEntity";
    public static final MagicalTreeConfiguration mTC = new MagicalTreeConfiguration();
    public Node base;
    private InflatableFoliage iF;
    public int size;
    public int i;

    public MagicalTreeCoreTileEntity() {
        base = new Tree(new MagicalTreeConfiguration(), new Random()).base;
        size = Tree.counter(base);
    }

    @Override
    public void updateEntity(){
        if (!worldObj.isRemote)
        {
            if(worldObj.getTotalWorldTime() % UPDATE_RATE == 0) {
                if(iF == null){
                    iF = new InflatableFoliage(mTC.leavesSize, mTC.dProb, mTC.leaves, mTC.leavesProb, worldObj.rand);
                }
                System.out.println("SIZE: " + size + "   NODE: " + (i++));
                if (!grow(0, worldObj, xCoord, yCoord, zCoord, base, iF, worldObj.rand)) {
                    genHut();
                    Tree.genLianes(worldObj, xCoord, yCoord, zCoord, base, mTC, worldObj.rand);
                    worldObj.setBlock(xCoord, yCoord, zCoord, mTC.wood);
                }
            }
        }
    }

    private void genHut(){
        Node hutNode = base.sons.get(0);
        int xHut = (int)hutNode.x - 3;
        int yHut = (int)hutNode.y + base.radius;
        int zHut = (int)hutNode.z - 3;

        StructureGenerator.createModel(worldObj, mTC.getHut(worldObj.rand), xCoord + xHut, yCoord + yHut, zCoord + zHut);
    }

    private static boolean grow(int index, World world, int x, int y, int z, Node father, InflatableFoliage iF, Random random){

        Collections.shuffle(father.sons, random);
        for(Node son : father.sons) {
            if (!son.built){
                son.built = true;
                if(son.sons.size() == 0){
                    iF.inflateShape(world, (int) (x + son.x), (int) (y + son.y), (int) (z + son.z));
                }

                InflatableCylinder iC;
                int dX = (int) (son.x - father.x);
                int dY = (int) (son.y - father.y);
                int dZ = (int) (son.z - father.z);
                int radius = father.radius;
                int internalRadius = 0;
                Block wood = Blocks.log;

                if(index > 0){
                    iC = new InflatableRoundedCylinder(dX, dY, dZ, radius, internalRadius, wood);
                }
                else{
                    iC = new InflatableMagicalBranch(dX, dY, dZ, radius, internalRadius, wood);
                }

                iC.inflateShape(world, (int)(x + father.x), (int)(y + father.y), (int)(z + father.z));

                return true;
            }
            else if(grow(index + 1, world, x, y, z, son, iF, random)){
                return true;
            }
        }

        return false;
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
