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
public class WideConfiguration extends DungeonConfiguration{

    public WideConfiguration(){
        passageProb = 0.005;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesItemsBlocksTabs.piselliteBricks;
        roof = SpecialBlocks.acaciaWoodPlanks;
        content = Blocks.air;
        name = DungeonReferences.WIDE;
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

        x1Delta = 1;
        y1Delta = 1;
        z1Delta = 1;

        xLootRoom = 7;
        yLootRoom = 1;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.savanna);
        biomes.add(BiomeGenBase.savannaPlateau);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack leggings = new ItemStack(Items.diamond_leggings, 1);
        leggings.addEnchantment(Enchantment.protection, 10);
        loot.add(leggings);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new WideConfiguration();
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

        Drawer.fillParallelepipedon1(model, 7, 1, 10, 1, 5, 1, walls);
        Drawer.fillParallelepipedon1(model, 6, 1, 3, 1, 2, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 3, 1, 2, 1, walls);

        model[8][1][8] = SpecialBlocks.stoneBricksSlabDown;
        model[8][1][7] = SpecialBlocks.stoneBricksSlabUp;
        model[8][2][6] = SpecialBlocks.stoneBricksSlabDown;
        model[8][2][5] = SpecialBlocks.stoneBricksSlabUp;
        model[8][3][4] = SpecialBlocks.stoneBricksSlabDown;

        model[6][1][8] = SpecialBlocks.stoneBricksSlabDown;
        model[6][1][7] = SpecialBlocks.stoneBricksSlabUp;
        model[6][2][6] = SpecialBlocks.stoneBricksSlabDown;
        model[6][2][5] = SpecialBlocks.stoneBricksSlabUp;
        model[6][3][4] = SpecialBlocks.stoneBricksSlabDown;

        model[7][6][8] = SpecialBlocks.stoneBricksSlabDown;
        model[7][5][7] = SpecialBlocks.stoneBricksSlabUp;
        model[7][5][6] = SpecialBlocks.stoneBricksSlabDown;
        model[7][4][5] = SpecialBlocks.stoneBricksSlabUp;
        model[7][4][4] = SpecialBlocks.stoneBricksSlabDown;

        Drawer.fillParallelepipedon1(model, 7, 1, 0, 1, 2, 1, content);

        model[7][7][10] = new Chest(getLoot(random), Chest.NORTH);

        model[6][8][11] = Blocks.torch;
        model[8][8][11] = Blocks.torch;

        return model;
    }
}
