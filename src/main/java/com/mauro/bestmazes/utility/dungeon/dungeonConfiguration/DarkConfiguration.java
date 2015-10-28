package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.SpecialBlocks;
import com.mauro.bestmazes.utility.BestMazesItemsBlocksTabs;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/25/2015.
 */
public class DarkConfiguration extends DungeonConfiguration {
    public DarkConfiguration(){
        passageProb = 0.005;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesItemsBlocksTabs.piselliteBricks;
        roof = SpecialBlocks.darkWoodPlanks;
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
        Block[][][] model = new Block[17][12][17];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 15, 10, 15, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 16, 11, 16, walls);

        model[4][1][4] = SpecialBlocks.stoneBricksSlabDown;
        model[4][1][5] = SpecialBlocks.stoneBricksSlabUp;
        model[4][2][6] = SpecialBlocks.stoneBricksSlabDown;
        model[4][2][7] = SpecialBlocks.stoneBricksSlabUp;
        model[4][3][8] = SpecialBlocks.stoneBricksSlabDown;
        model[4][3][9] = SpecialBlocks.stoneBricksSlabUp;
        model[4][4][10] = SpecialBlocks.stoneBricksSlabDown;
        model[4][4][11] = SpecialBlocks.stoneBricksSlabUp;
        model[4][5][12] = SpecialBlocks.stoneBricksSlabDown;

        model[5][1][4] = SpecialBlocks.stoneBricksSlabDown;
        model[5][1][5] = SpecialBlocks.stoneBricksSlabUp;
        model[5][2][6] = SpecialBlocks.stoneBricksSlabDown;
        model[5][2][7] = SpecialBlocks.stoneBricksSlabUp;
        model[5][3][8] = SpecialBlocks.stoneBricksSlabDown;
        model[5][3][9] = SpecialBlocks.stoneBricksSlabUp;
        model[5][4][10] = SpecialBlocks.stoneBricksSlabDown;
        model[5][4][11] = SpecialBlocks.stoneBricksSlabUp;
        model[5][5][12] = SpecialBlocks.stoneBricksSlabDown;

        model[11][1][4] = SpecialBlocks.stoneBricksSlabDown;
        model[11][1][5] = SpecialBlocks.stoneBricksSlabUp;
        model[11][2][6] = SpecialBlocks.stoneBricksSlabDown;
        model[11][2][7] = SpecialBlocks.stoneBricksSlabUp;
        model[11][3][8] = SpecialBlocks.stoneBricksSlabDown;
        model[11][3][9] = SpecialBlocks.stoneBricksSlabUp;
        model[11][4][10] = SpecialBlocks.stoneBricksSlabDown;
        model[11][4][11] = SpecialBlocks.stoneBricksSlabUp;
        model[11][5][12] = SpecialBlocks.stoneBricksSlabDown;

        model[12][1][4] = SpecialBlocks.stoneBricksSlabDown;
        model[12][1][5] = SpecialBlocks.stoneBricksSlabUp;
        model[12][2][6] = SpecialBlocks.stoneBricksSlabDown;
        model[12][2][7] = SpecialBlocks.stoneBricksSlabUp;
        model[12][3][8] = SpecialBlocks.stoneBricksSlabDown;
        model[12][3][9] = SpecialBlocks.stoneBricksSlabUp;
        model[12][4][10] = SpecialBlocks.stoneBricksSlabDown;
        model[12][4][11] = SpecialBlocks.stoneBricksSlabUp;
        model[12][5][12] = SpecialBlocks.stoneBricksSlabDown;

        Drawer.column(model, 2, 1, 4, walls, roof);
        Drawer.column(model, 2, 1, 8, walls, roof);
        Drawer.column(model, 2, 1, 12, walls, roof);

        Drawer.column(model, 14, 1, 4, walls, roof);
        Drawer.column(model, 14, 1, 8, walls, roof);
        Drawer.column(model, 14, 1, 12, walls, roof);

        Drawer.fillParallelepipedon1(model, 2, 8, 4, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 2, 8, 8, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 2, 8, 12, 1, 3, 1, walls);

        Drawer.fillParallelepipedon1(model, 14, 8, 4, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 14, 8, 8, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 14, 8, 12, 1, 3, 1, walls);

        Drawer.fillParallelepipedon1(model, 4, 5, 13, 9, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 7, 7, 7, 3, 1, 3, walls);

        Drawer.fillParallelepipedon1(model, 7, 6, 12, 3, 1, 1, SpecialBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 7, 6, 11, 3, 1, 1, SpecialBlocks.stoneBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 7, 7, 10, 3, 1, 1, SpecialBlocks.stoneBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 7, 3, 1, 3, 1, 2, walls);
        Drawer.fillParallelepipedon1(model, 7, 1, 2, 1, 2, 1, walls);
        Drawer.fillParallelepipedon1(model, 9, 1, 2, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 8, 1, 0, 1, 2, 1, content);

        model[8][8][8] = new Chest(getLoot(random), Chest.NORTH);

        model[7][8][8] = Blocks.torch;
        model[9][8][8] = Blocks.torch;

        return model;
    }
}
