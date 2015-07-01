package com.mauro.bestmazes.common;

import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.PiselliteBricksSlab;
import com.mauro.bestmazes.dungeon.Dungeon;
import com.mauro.bestmazes.tree.Tree;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 6/25/2015.
 */
public class StructureGenerator implements IWorldGenerator {

    private static final double DUNGEON_PROB = 0.01;
    private static final int Y_DUNGEON = 5;

    public static void setBlock(World world, int x, int y, int z, Block b){
        world.setBlockToAir(x, y, z);

        if(b instanceof PiselliteBricksSlab){
            PiselliteBricksSlab pbs = (PiselliteBricksSlab) b;
            world.setBlock(x, y, z, Blocks.stone_slab, pbs.state ? 13 : 5, 3);
        }
        else{
            world.setBlock(x, y, z, b);
        }
    }

    public static void createModel(World world, Block[][][] blocks, int x, int y, int z){
        ArrayList<int[]> torches = new ArrayList<int[]>();
        for(int i = 0; i < blocks.length; i++){
            for(int e = 0; e < blocks[i].length; e++){
                for(int o = 0; o < blocks[i][e].length; o++){
                    if(blocks[i][e][o] != null) {
                        if (Blocks.torch.equals(blocks[i][e][o])){
                            int[] t = new int[3];
                            t[0] = i;
                            t[1] = e;
                            t[2] = o;
                            torches.add(t);
                        } else {
                            setBlock(world, x + i, y + e, z + o, blocks[i][e][o]);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < torches.size(); i++)
        {
            setBlock(world, x + torches.get(i)[0], y + torches.get(i)[1], z + torches.get(i)[2], Blocks.torch);
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if(world.provider.dimensionId == 0 && random.nextDouble() < DUNGEON_PROB){

            int x = chunkX * 16;
            int z = chunkZ * 16;

            //System.out.println("DUNGEON: x " + x + " z " + z + " ######################");

            Dungeon d = new Dungeon(world, random, PiselliteBricks.piselliteBricks, x, Y_DUNGEON, z);
            if(d.available()) {
                d.generate();
                //Tree t = new Tree(Tree.WIDTH, Tree.LENGTH, Tree.SPREAD, Tree.SIZE, new Random(), Tree.N_SONS, Tree.CHILD_PROB, Tree.K_LENGTH, Tree.K_WIDTH);
                //Block[][][] model = new Block[10][10][10];
                //Drawer.line(model, 0, 0, 0, 3, 9, 5, Blocks.log);
                //createModel(world, model, x, 100, z);
                setBlock(world, x, 100, z, Blocks.chest);


                TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(x, 100, z);

                tileentitychest.setInventorySlotContents(1, new ItemStack(Items.chainmail_chestplate, 1));
            }
        }
    }
}