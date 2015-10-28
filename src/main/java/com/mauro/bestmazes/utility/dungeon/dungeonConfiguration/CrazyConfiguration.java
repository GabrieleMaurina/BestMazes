package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.SpecialBlocks;
import com.mauro.bestmazes.utility.BestMazesItemsBlocksTabs;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/21/2015.
 */
public class CrazyConfiguration extends DungeonConfiguration{

    public CrazyConfiguration(){
        passageProb = 0.005;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.01;
        crazy = true;
        walls = BestMazesItemsBlocksTabs.piselliteBricks;
        roof = SpecialBlocks.britchWoodPlanks;
        content = Blocks.air;
        name = DungeonReferences.CRAZY;
        prob = 0.005;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xLootRoom = 7;
        yLootRoom = 1;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.birchForest);
        biomes.add(BiomeGenBase.birchForestHills);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack sword = new ItemStack(Items.diamond_sword, 1);
        sword.addEnchantment(Enchantment.sharpness, 10);
        loot.add(sword);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new CrazyConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][10];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 7, 8, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 9, walls);

        Drawer.fillParallelepipedon1(model, 6, 5, 6, 3, 1, 3, walls);

        model[9][5][8] = SpecialBlocks.stoneBricksSlabDown;
        model[10][4][8] = SpecialBlocks.stoneBricksSlabUp;
        model[11][4][8] = SpecialBlocks.stoneBricksSlabDown;
        model[12][3][8] = SpecialBlocks.stoneBricksSlabUp;
        model[13][3][8] = SpecialBlocks.stoneBricksSlabDown;
        model[13][2][7] = SpecialBlocks.stoneBricksSlabUp;
        model[13][2][6] = SpecialBlocks.stoneBricksSlabDown;
        model[13][1][5] = SpecialBlocks.stoneBricksSlabUp;
        model[13][1][4] = SpecialBlocks.stoneBricksSlabDown;

        model[5][5][8] = SpecialBlocks.stoneBricksSlabDown;
        model[4][4][8] = SpecialBlocks.stoneBricksSlabUp;
        model[3][4][8] = SpecialBlocks.stoneBricksSlabDown;
        model[2][3][8] = SpecialBlocks.stoneBricksSlabUp;
        model[1][3][8] = SpecialBlocks.stoneBricksSlabDown;
        model[1][2][7] = SpecialBlocks.stoneBricksSlabUp;
        model[1][2][6] = SpecialBlocks.stoneBricksSlabDown;
        model[1][1][5] = SpecialBlocks.stoneBricksSlabUp;
        model[1][1][4] = SpecialBlocks.stoneBricksSlabDown;

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
}