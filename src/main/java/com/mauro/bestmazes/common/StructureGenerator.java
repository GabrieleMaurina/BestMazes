package com.mauro.bestmazes.common;

import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.PiselliteBricksSlab;
import com.mauro.bestmazes.dungeon.Dungeon;
import com.mauro.bestmazes.dungeon.Maze3D;
import com.mauro.bestmazes.tree.Tree;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 6/25/2015.
 */
public class StructureGenerator implements IWorldGenerator {

    private static final double DUNGEON_PROB = 0.05;
    private static final int Y_DUNGEON = 5;

    public static void setBlock(World world, int x, int y, int z, Block b, Random random){
        world.setBlockToAir(x, y, z);

        if(b instanceof PiselliteBricksSlab){
            PiselliteBricksSlab pbs = (PiselliteBricksSlab) b;
            world.setBlock(x, y, z, Blocks.stone_slab, pbs.state ? 13 : 5, 3);
        }
        else{
            world.setBlock(x, y, z, b);
            if(Blocks.chest == b){
                fillChest(world, x, y, z);
            }
            else if(Blocks.mob_spawner == b){
                fillSpawner(world, x, y, z, random);
            }
        }
    }

    public static void createModel(World world, Block[][][] blocks, int x, int y, int z, Random random){
        ArrayList<int[]> torches = new ArrayList<int[]>();
        for(int i = 0; i < blocks.length; i++){
            for(int e = 0; e < blocks[i].length; e++){
                for(int o = 0; o < blocks[i][e].length; o++){
                    if(blocks[i][e][o] != null) {
                        if (Blocks.torch == blocks[i][e][o]){
                            int[] t = new int[3];
                            t[0] = i;
                            t[1] = e;
                            t[2] = o;
                            torches.add(t);
                        }
                        else{
                                setBlock(world, x + i, y + e, z + o, blocks[i][e][o], random);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < torches.size(); i++)
        {
            setBlock(world, x + torches.get(i)[0], y + torches.get(i)[1], z + torches.get(i)[2], Blocks.torch, random);
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if(world.provider.dimensionId == 0 && random.nextDouble() < DUNGEON_PROB){

            int x = chunkX * 16;
            int z = chunkZ * 16;

            Dungeon d = new Dungeon(world, random, PiselliteBricks.piselliteBricks, x, Y_DUNGEON, z, Maze3D.X_SIZE, Maze3D.Y_SIZE, Maze3D.Z_SIZE);
            if(d.available()) {
                d.generate();
                setBlock(world, x, 100, z, Blocks.mob_spawner, random);
            }
        }
    }

    public static void fillChest(World world, int x, int y, int z){
        TileEntityChest tileEntityChest = (TileEntityChest)world.getTileEntity(x, y, z);
        tileEntityChest.setInventorySlotContents(0, new ItemStack(Items.diamond_boots, 1));
        tileEntityChest.setInventorySlotContents(1, new ItemStack(Items.diamond_leggings, 1));
        tileEntityChest.setInventorySlotContents(2, new ItemStack(Items.diamond_chestplate, 1));
        tileEntityChest.setInventorySlotContents(3, new ItemStack(Items.diamond_helmet, 1));
        tileEntityChest.setInventorySlotContents(4, new ItemStack(Items.diamond_sword, 1));
    }

    public static void fillSpawner(World world, int x, int y, int z, Random random){
        TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
        tileEntityMobSpawner.func_145881_a().setEntityName(DungeonHooks.getRandomDungeonMob(random));
    }
}