package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.Dungeon;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 7/3/2015.
 */
public abstract class DungeonConfiguration {

    public double mobProb;
    public double passageProb;
    public double lavaProb;
    public double waterProb;
    public double spiderNetProb;
    public double branchesProb;
    public double joinProb;
    public double tallGrassProb;
    public double vineProb;
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

    public int xHut = -3;
    public int zHut = -3;

    public double xMinotaurSpawn;
    public double yMinotaurSpawn;
    public double zMinotaurSpawn;

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

    public abstract Minotaur getMinotaur(World world);

    public Block[][][] genEntranceHut(Random random){
        Block[][][] model = new Block[7][9][7];

        Drawer.fillParallelepipedon1(model, 0, 0, 0, 7, 1, 7, walls);
        Drawer.fillParallelepipedon1(model, 2, 0, 2, 3, 1, 3, content);
        Drawer.fillParallelepipedon1(model, 3, 0, 3, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 1, 1, 1, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 1, 1, 5, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 5, 1, 1, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 5, 1, 5, 1, 3, 1, walls);

        model[1][2][1] = roof;
        model[1][2][5] = roof;
        model[5][2][1] = roof;
        model[5][2][5] = roof;

        Drawer.fillParallelepipedon1(model, 1, 4, 1, 5, 1, 5, walls);
        Drawer.fillParallelepipedon1(model, 2, 4, 2, 3, 1, 3, content);
        Drawer.fillParallelepipedon1(model, 0, 5, 0, 7, 1, 7, roof);
        model[3][5][3] = content;
        Drawer.fillParallelepipedon1(model, 2, 6, 2, 3, 1, 3, roof);

        model[3][7][3] = roof;

        if(!name.equals("ocean")) model[3][2][3] = Blocks.torch;

        return model;
    }
}
