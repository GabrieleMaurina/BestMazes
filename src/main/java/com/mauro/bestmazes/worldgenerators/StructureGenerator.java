package com.mauro.bestmazes.worldgenerators;

import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.SpecialBlock;
import com.mauro.bestmazes.utility.dungeon.Dungeon;
import com.mauro.bestmazes.utility.dungeon.DungeonConfigurations;
import com.mauro.bestmazes.utility.dungeon.dungeonConfiguration.DungeonConfiguration;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DungeonHooks;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 6/25/2015.
 */
public class StructureGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);

        Dungeon.generateDungeon(world, x, z, random);
    }

    public static void setBlock(World world, int x, int y, int z, Block block, Random random){

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
            else if(Blocks.mob_spawner == block){
                world.setBlock(x, y, z, block);
                fillSpawner(world, x, y, z, random);
            }
            else{
                world.setBlock(x, y, z, block);
            };
        }
    }

    public static void createModel(World world, Block[][][] blocks, int x, int y, int z, Random random){
        ArrayList<int[]> torches = new ArrayList<int[]>();
        for(int i = 0; i < blocks.length; i++){
            for(int e = blocks[i].length - 1; e >= 0 ; e--){
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

    public static void fillChest(World world, int x, int y, int z, Chest chest){
        TileEntityChest tileEntityChest = (TileEntityChest)world.getTileEntity(x, y, z);
        ArrayList<ItemStack> items = chest.items;
        tileEntityChest.getBlockMetadata();
        for(int i = 0; i < items.size(); i++){
            tileEntityChest.setInventorySlotContents(i, items.get(i));
        }
    }

    public static void fillSpawner(World world, int x, int y, int z, Random random){
        TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
        tileEntityMobSpawner.func_145881_a().setEntityName(DungeonHooks.getRandomDungeonMob(random));
    }
}