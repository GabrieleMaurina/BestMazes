package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.SpecialBlocks;
import com.mauro.bestmazes.utility.Drawer;
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
public class NarrowConfiguration extends DungeonConfiguration {

    public NarrowConfiguration(){
        mobProb = 0.02;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = PiselliteBricks.piselliteBricks;
        roof = SpecialBlocks.spruceWoodPlanks;
        content = Blocks.air;
        name = "narrow";
        prob = 0.005;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xDelta = 1;
        yDelta = 2;
        zDelta = 1;

        x1Delta = 1;
        y1Delta = 1;
        z1Delta = 1;

        xLootRoom = 6;
        yLootRoom = 1;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.taiga);
        biomes.add(BiomeGenBase.taigaHills);
        biomes.add(BiomeGenBase.coldTaiga);
        biomes.add(BiomeGenBase.megaTaiga);
        biomes.add(BiomeGenBase.coldTaigaHills);
        biomes.add(BiomeGenBase.megaTaigaHills);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack chestplate = new ItemStack(Items.diamond_chestplate, 1);
        chestplate.addEnchantment(Enchantment.protection, 10);
        loot.add(chestplate);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new NarrowConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[13][9][13];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 11, 7, 11, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 12, 8, 12, walls);

        Drawer.fillParallelepipedon1(model, 2, 1, 2, 9, 1, 9, SpecialBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 3, 1, 3, 7, 1, 7, SpecialBlocks.stoneBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 4, 2, 4, 5, 1, 5, SpecialBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 5, 2, 5, 3, 1, 3, SpecialBlocks.stoneBricksSlabUp);

        Drawer.column(model, 5, 1, 2, walls, roof);
        Drawer.column(model, 7, 1, 2, walls, roof);
        Drawer.column(model, 5, 1, 10, walls, roof);
        Drawer.column(model, 7, 1, 10, walls, roof);
        Drawer.column(model, 2, 1, 5, walls, roof);
        Drawer.column(model, 2, 1, 7, walls, roof);
        Drawer.column(model, 10, 1, 5, walls, roof);
        Drawer.column(model, 10, 1, 7, walls, roof);
        Drawer.column(model, 3, 1, 3, walls, roof);
        Drawer.column(model, 3, 1, 9, walls, roof);
        Drawer.column(model, 9, 1, 3, walls, roof);
        Drawer.column(model, 9, 1, 9, walls, roof);

        Drawer.fillParallelepipedon1(model, 6, 1, 0, 1, 2, 1, content);

        model[6][3][6] = new Chest(getLoot(random), Chest.NORTH);

        model[5][3][6] = Blocks.torch;
        model[7][3][6] = Blocks.torch;

        return model;
    }
}

