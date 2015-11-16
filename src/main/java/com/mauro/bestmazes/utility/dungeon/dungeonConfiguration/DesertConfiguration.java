package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.entities.minotaurs.DesertMinotaur;
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
 * Created by Gabriele on 10/21/2015.
 */
public class DesertConfiguration extends DungeonConfiguration{

    public DesertConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.01;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = Blocks.sandstone;
        content = Blocks.air;
        name = DungeonReferences.DESERT;
        prob = 0.005;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xDelta = 2;
        yDelta = 2;
        zDelta = 2;

        x1Delta = 3;
        y1Delta = 1;
        z1Delta = 3;

        xLootRoom = 7;
        yLootRoom = 1;

        xMinotaurSpawn = 7.5;
        yMinotaurSpawn = 5.0;
        zMinotaurSpawn = 9.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.desert);
        biomes.add(BiomeGenBase.desertHills);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack apples = new ItemStack(Items.golden_apple, 10, 1);
        loot.add(apples);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new DesertConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][15];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 7, 13, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 14, walls);

        Drawer.fillParallelepipedon1(model, 6, 2, 3, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 6, 4, 9, 3, 1, 3, walls);

        Drawer.fillParallelepipedon1(model, 7, 1, 10, 1, 3, 1, walls);
        model[7][1][4] = walls;

        model[7][3][6] = BestMazesBlocks.piselliteBricksSlabDown;
        model[7][3][7] = BestMazesBlocks.piselliteBricksSlabUp;
        model[7][4][8] = BestMazesBlocks.piselliteBricksSlabDown;

        model[3][1][4] = BestMazesBlocks.piselliteBricksSlabDown;
        model[4][1][4] = BestMazesBlocks.piselliteBricksSlabUp;
        model[5][2][4] = BestMazesBlocks.piselliteBricksSlabDown;

        model[11][1][4] = BestMazesBlocks.piselliteBricksSlabDown;
        model[10][1][4] = BestMazesBlocks.piselliteBricksSlabUp;
        model[9][2][4] = BestMazesBlocks.piselliteBricksSlabDown;

        Drawer.column(model, 4, 1, 7, walls, roof);
        Drawer.column(model, 10, 1, 7, walls, roof);

        Drawer.fillParallelepipedon1(model, 7, 1, 0, 1, 2, 1, content);

        model[7][5][10] = new Chest(getLoot(random), Chest.NORTH);

        model[6][5][10] = Blocks.torch;
        model[8][5][10] = Blocks.torch;

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new DesertMinotaur(world);
    }
}