package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.entities.minotaurs.DarkMinotaur;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/25/2015.
 */
public class DarkConfiguration extends DungeonConfiguration {
    public DarkConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = BestMazesBlocks.darkWoodPlanks;
        content = Blocks.air;
        name = DungeonReferences.DARK;
        prob = 0.005;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xDelta = 3;
        yDelta = 3;
        zDelta = 3;

        x1Delta = 2;
        y1Delta = 2;
        z1Delta = 2;

        xLootRoom = 7;
        yLootRoom = 1;

        xMinotaurSpawn = 6.5;
        yMinotaurSpawn = 7.0;
        zMinotaurSpawn = 9.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.roofedForest);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        loot.add(new ItemStack(Items.diamond, 15));
        loot.add(new ItemStack(Items.gold_ingot, 35));
        return loot;
    }

    public DungeonConfiguration clone(){
        return new DarkConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][10][13];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 8, 11, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 9, 12, walls);

        Drawer.column(model, 3, 1, 4, walls, roof);
        Drawer.column(model, 3, 1, 8, walls, roof);
        Drawer.column(model, 11, 1, 4, walls, roof);
        Drawer.column(model, 11, 1, 8, walls, roof);

        model[3][8][4] = walls;
        model[3][8][8] = walls;
        model[11][8][4] = walls;
        model[11][8][8] = walls;

        Drawer.fillParallelepipedon1(model, 6, 3, 1, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 6, 6, 9, 3, 1, 3, walls);

        Drawer.column(model, 7, 1, 10, walls, roof);
        Drawer.fillParallelepipedon1(model, 6, 1, 3, 1, 2, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 3, 1, 2, 1, walls);

        model[8][1][8] = BestMazesBlocks.stoneBricksSlabDown;
        model[8][1][7] = BestMazesBlocks.stoneBricksSlabUp;
        model[8][2][6] = BestMazesBlocks.stoneBricksSlabDown;
        model[8][2][5] = BestMazesBlocks.stoneBricksSlabUp;
        model[8][3][4] = BestMazesBlocks.stoneBricksSlabDown;

        model[6][1][8] = BestMazesBlocks.stoneBricksSlabDown;
        model[6][1][7] = BestMazesBlocks.stoneBricksSlabUp;
        model[6][2][6] = BestMazesBlocks.stoneBricksSlabDown;
        model[6][2][5] = BestMazesBlocks.stoneBricksSlabUp;
        model[6][3][4] = BestMazesBlocks.stoneBricksSlabDown;

        model[7][6][8] = BestMazesBlocks.stoneBricksSlabDown;
        model[7][5][7] = BestMazesBlocks.stoneBricksSlabUp;
        model[7][5][6] = BestMazesBlocks.stoneBricksSlabDown;
        model[7][4][5] = BestMazesBlocks.stoneBricksSlabUp;
        model[7][4][4] = BestMazesBlocks.stoneBricksSlabDown;

        Drawer.fillParallelepipedon1(model, xLootRoom, yLootRoom, 0, 1, 2, 1, content);

        model[7][7][10] = new Chest(getLoot(random), Chest.NORTH);

        model[6][8][11] = Blocks.torch;
        model[8][8][11] = Blocks.torch;

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new DarkMinotaur(world);
    }
}
