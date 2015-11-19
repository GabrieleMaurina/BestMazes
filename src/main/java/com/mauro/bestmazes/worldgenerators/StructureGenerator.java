package com.mauro.bestmazes.worldgenerators;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mauro.bestmazes.blocks.*;
import com.mauro.bestmazes.utility.dungeon.Dungeon;
import com.mauro.bestmazes.utility.dungeon.DungeonConfigurations;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import com.mauro.bestmazes.utility.dungeon.dungeonConfiguration.EndConfiguration;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 6/25/2015.
 */
public class StructureGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if(chunkX == 0 && chunkZ == 0){
            setBlock(world, 0 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.BIRCH).getLoot(random), Chest.SOUTH));
            setBlock(world, 2 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.DESERT).getLoot(random), Chest.SOUTH));
            setBlock(world, 4 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.END).getLoot(random), Chest.SOUTH));
            setBlock(world, 6 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.EXTREME).getLoot(random), Chest.SOUTH));
            setBlock(world, 8 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.FINAL).getLoot(random), Chest.SOUTH));
            setBlock(world, 10 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.FOREST).getLoot(random), Chest.SOUTH));
            setBlock(world, 12 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.ICE).getLoot(random), Chest.SOUTH));
            setBlock(world, 14 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.JUNGLE).getLoot(random), Chest.SOUTH));
            setBlock(world, 16 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.NETHER).getLoot(random), Chest.SOUTH));
            setBlock(world, 18 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.OCEAN).getLoot(random), Chest.SOUTH));
            setBlock(world, 20 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.PLAIN).getLoot(random), Chest.SOUTH));
            setBlock(world, 22 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.ROOFED).getLoot(random), Chest.SOUTH));
            setBlock(world, 24 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.SAVANNA).getLoot(random), Chest.SOUTH));
            setBlock(world, 26 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.SWAMP).getLoot(random), Chest.SOUTH));
            setBlock(world, 28 , 100, 0, new Chest(DungeonConfigurations.getConfiguration(DungeonReferences.TAIGA).getLoot(random), Chest.SOUTH));
        }
        Dungeon.generateDungeon(world, chunkX, chunkZ, random);
    }

    public static void setBlock(World world, int x, int y, int z, Block block){

        world.setBlockToAir(x, y, z);

        if(block instanceof SpecialBlock){
            SpecialBlock sB = (SpecialBlock)block;
            world.setBlock(x, y, z, sB.block, sB.first, sB.second);
        }
        else{
            if(block instanceof Chest){
                world.setBlock(x, y, z, Blocks.chest);
                fillChest(world, x, y, z, (Chest) block);
                world.setBlockMetadataWithNotify(x, y, z, ((Chest) block).dir, 0);
            }
            else if(block instanceof Spawner){
                world.setBlock(x, y, z, Blocks.mob_spawner);
                fillSpawner(world, x, y, z, (Spawner)block);
            }
            else{
                world.setBlock(x, y, z, block);
            };
        }
    }

    public static void createModel(World world, Block[][][] model, int x, int y, int z){
        ArrayList<int[]> coor = new ArrayList<int[]>();
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(int i = 0; i < model.length; i++){
            for(int e = model[i].length - 1; e >= 0 ; e--){
                for(int o = 0; o < model[i][e].length; o++){
                    if(model[i][e][o] != null) {
                        if (model[i][e][o] == Blocks.torch || model[i][e][o] instanceof TallGrass || model[i][e][o] instanceof Vine){
                            int[] t = new int[3];
                            t[0] = i;
                            t[1] = e;
                            t[2] = o;
                            coor.add(t);
                            blocks.add(model[i][e][o]);
                        }
                        else{
                                setBlock(world, x + i, y + e, z + o, model[i][e][o]);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < coor.size(); i++)
        {
            setBlock(world, x + coor.get(i)[0], y + coor.get(i)[1], z + coor.get(i)[2], blocks.get(i));
        }
    }

    public static void fillChest(World world, int x, int y, int z, Chest chest){
        TileEntityChest tileEntityChest = (TileEntityChest)world.getTileEntity(x, y, z);
        ArrayList<ItemStack> items = chest.items;
        tileEntityChest.getBlockMetadata();
        for(int i = 0; i < items.size(); i++){
            tileEntityChest.setInventorySlotContents(i, items.get(i));
        }
    }

    public static void fillSpawner(World world, int x, int y, int z, Spawner spawner){
        TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
        tileEntityMobSpawner.func_145881_a().setEntityName(spawner.type);
    }
}