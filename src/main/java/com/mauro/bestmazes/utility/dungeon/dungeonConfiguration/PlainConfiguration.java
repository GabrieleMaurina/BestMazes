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
 * Created by Gabriele on 10/26/2015.
 */
public class PlainConfiguration extends DungeonConfiguration {
    public PlainConfiguration(){
        passageProb = 0.005;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesItemsBlocksTabs.piselliteBricks;
        roof = Blocks.grass;
        content = Blocks.air;
        name = DungeonReferences.PLAIN;
        prob = 0.004;

        xStart = 2;
        yStart = 1;
        zStart = 0;

        xSize = 6;
        ySize = 2;
        zSize = 6;

        xDelta = 2;
        yDelta = 4;
        zDelta = 2;

        x1Delta = 3;
        y1Delta = 3;
        z1Delta = 3;

        xLootRoom = 7;
        yLootRoom = 1;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.plains);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack saddles = new ItemStack(Items.saddle, 2);
        ItemStack armors = new ItemStack(Items.diamond_horse_armor, 2);
        loot.add(saddles);
        loot.add(armors);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new PlainConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][16];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 7, 14, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 15, walls);

        Drawer.fillParallelepipedon1(model, 3, 1, 14, 9, 1, -6, SpecialBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 4, 1, 14, 7, 1, -5, SpecialBlocks.stoneBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 5, 2, 14, 5, 1, -4, SpecialBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 2, 14, 3, 1, -3, SpecialBlocks.stoneBricksSlabUp);

        Drawer.column(model, 3, 1, 3, walls, roof);
        Drawer.column(model, 3, 1, 6, walls, roof);
        Drawer.column(model, 3, 1, 9, walls, roof);
        Drawer.column(model, 3, 1, 12, walls, roof);
        Drawer.column(model, 11, 1, 3, walls, roof);
        Drawer.column(model, 11, 1, 6, walls, roof);
        Drawer.column(model, 11, 1, 9, walls, roof);
        Drawer.column(model, 11, 1, 12, walls, roof);

        Drawer.fillParallelepipedon1(model, 6, 3, 1, 3, 1, 2, walls);
        Drawer.fillParallelepipedon1(model, 6, 1, 2, 1, 2, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 2, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 7, 1, 0, 1, 2, 1, content);

        model[7][3][13] = new Chest(getLoot(random), Chest.NORTH);

        model[6][4][14] = Blocks.torch;
        model[8][4][14] = Blocks.torch;

        return model;
    }
}
