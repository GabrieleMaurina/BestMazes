package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.SpecialBlocks;
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
 * Created by Gabriele on 10/21/2015.
 */
public class OceanConfiguration extends DungeonConfiguration{

    public OceanConfiguration(){
        mobProb = 0.02;
        branchesProb = 0.7;
        joinProb = 0.01;
        crazy = false;
        walls = PiselliteBricks.piselliteBricks;
        roof = Blocks.glowstone;
        content = Blocks.water;
        name = DungeonReferences.OCEAN;
        prob = 0.004;

        xStart = 1;
        yStart = 3;
        zStart = 0;

        xSize = 4;
        ySize = 4;
        zSize = 4;

        xDelta = 2;
        yDelta = 2;
        zDelta = 2;

        x1Delta = 1;
        y1Delta = 1;
        z1Delta = 1;

        xLootRoom = 7;
        yLootRoom = 1;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.ocean);
        biomes.add(BiomeGenBase.deepOcean);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        loot.add(new ItemStack(Items.ender_pearl, 10));
        return loot;
    }

    public DungeonConfiguration clone(){
        return new OceanConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][15];

        Drawer.fillParallelepipedon(model, 1, 5, 1, 13, 8, 13, Blocks.air);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 14, walls);
        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 4, 13, Blocks.water);

        Drawer.column(model, 5, 1, 2, walls, roof);
        Drawer.column(model, 9, 1, 2, walls, roof);
        Drawer.column(model, 5, 1, 12, walls, roof);
        Drawer.column(model, 9, 1, 12, walls, roof);
        Drawer.column(model, 2, 1, 5, walls, roof);
        Drawer.column(model, 2, 1, 9, walls, roof);
        Drawer.column(model, 12, 1, 5, walls, roof);
        Drawer.column(model, 12, 1, 9, walls, roof);

        Drawer.fillParallelepipedon1(model, 6, 4, 5, 3, 1, 5, walls);
        Drawer.fillParallelepipedon1(model, 5, 4, 6, 1, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 9, 4, 6, 1, 1, 3, walls);

        Drawer.fillParallelepipedon1(model, 6, 1, 5, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 5, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 6, 1, 9, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 9, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 5, 1, 7, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 9, 1, 7, 1, 3, 1, walls);

        Drawer.fillParallelepipedon1(model, 7, 1, 0, 1, 2, 1, content);

        model[7][5][7] = new Chest(getLoot(random), Chest.NORTH);

        return model;
    }
}