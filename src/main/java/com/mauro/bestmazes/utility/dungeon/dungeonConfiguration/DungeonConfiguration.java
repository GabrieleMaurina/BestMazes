package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.PiselliteBricks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 7/3/2015.
 */
public abstract class DungeonConfiguration {

    public double mobProb;
    public double passageProb;
    public double branchesProb;
    public double joinProb;
    public boolean crazy;
    public Block walls;
    public Block roof;
    public Block content;
    public String name;
    public double prob;

    public int xStart;
    public int yStart;
    public int zStart;

    public int xSize;
    public int ySize;
    public int zSize;

    public int xDelta;
    public int yDelta;
    public int zDelta;

    public int x1Delta;
    public int y1Delta;
    public int z1Delta;

    public int xLootRoom;
    public int yLootRoom;

    public ArrayList<BiomeGenBase> biomes;

    public abstract ArrayList<ItemStack> getLoot(Random r);

    public abstract DungeonConfiguration clone();

    public abstract Block[][][] genLootRoom(Random random);

    public boolean isBiome(BiomeGenBase b){
        for(int i = 0; i < biomes.size(); i++){
            if(biomes.get(i) == b) return true;
        }
        return false;
    }
}
