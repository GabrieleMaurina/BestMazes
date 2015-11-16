package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.entities.minotaurs.BirchMinotaur;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.items.BestMazesItems;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/21/2015.
 */
public class BirchConfiguration extends DungeonConfiguration{

    public BirchConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.01;
        crazy = true;
        walls = BestMazesBlocks.piselliteBricks;
        roof = BestMazesBlocks.britchWoodPlanks;
        content = Blocks.air;
        name = DungeonReferences.BIRCH;
        prob = 0.005;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xLootRoom = 7;
        yLootRoom = 1;

        xMinotaurSpawn = 7.5;
        yMinotaurSpawn = 1.0;
        zMinotaurSpawn = 2.0;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.birchForest);
        biomes.add(BiomeGenBase.birchForestHills);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack sword = new ItemStack(BestMazesItems.minotaurIvorySword, 1);
        sword.addEnchantment(Enchantment.sharpness, 10);
        loot.add(sword);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new BirchConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][10];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 7, 8, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 9, walls);

        Drawer.fillParallelepipedon1(model, 6, 5, 6, 3, 1, 3, walls);

        model[9][5][8] = BestMazesBlocks.piselliteBricksSlabDown;
        model[10][4][8] = BestMazesBlocks.piselliteBricksSlabUp;
        model[11][4][8] = BestMazesBlocks.piselliteBricksSlabDown;
        model[12][3][8] = BestMazesBlocks.piselliteBricksSlabUp;
        model[13][3][8] = BestMazesBlocks.piselliteBricksSlabDown;
        model[13][2][7] = BestMazesBlocks.piselliteBricksSlabUp;
        model[13][2][6] = BestMazesBlocks.piselliteBricksSlabDown;
        model[13][1][5] = BestMazesBlocks.piselliteBricksSlabUp;
        model[13][1][4] = BestMazesBlocks.piselliteBricksSlabDown;

        model[5][5][8] = BestMazesBlocks.piselliteBricksSlabDown;
        model[4][4][8] = BestMazesBlocks.piselliteBricksSlabUp;
        model[3][4][8] = BestMazesBlocks.piselliteBricksSlabDown;
        model[2][3][8] = BestMazesBlocks.piselliteBricksSlabUp;
        model[1][3][8] = BestMazesBlocks.piselliteBricksSlabDown;
        model[1][2][7] = BestMazesBlocks.piselliteBricksSlabUp;
        model[1][2][6] = BestMazesBlocks.piselliteBricksSlabDown;
        model[1][1][5] = BestMazesBlocks.piselliteBricksSlabUp;
        model[1][1][4] = BestMazesBlocks.piselliteBricksSlabDown;

        Drawer.column(model, 4, 1, 4, walls, roof);
        Drawer.column(model, 10, 1, 4, walls, roof);

        Drawer.fillParallelepipedon1(model, 6, 1, 6, 1, 4, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 6, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 6, 3, 1, 3, 1, 2, walls);
        Drawer.fillParallelepipedon1(model, 6, 1, 2, 1, 2, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 2, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 7, 1, 0, 1, 2, 1, content);

        model[7][6][7] = new Chest(getLoot(random), Chest.NORTH);

        model[6][3][5] = Blocks.torch;
        model[8][3][5] = Blocks.torch;

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new BirchMinotaur(world);
    }
}