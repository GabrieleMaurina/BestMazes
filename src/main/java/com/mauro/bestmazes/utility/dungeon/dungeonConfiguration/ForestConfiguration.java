package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.entities.minotaurs.ForestMinotaur;
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
public class ForestConfiguration extends DungeonConfiguration{

    public ForestConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = BestMazesBlocks.oakWoodPlanks;
        content = Blocks.air;
        name = DungeonReferences.FOREST;
        prob = 0.004;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xDelta = 2;
        yDelta = 3;
        zDelta = 2;

        x1Delta = 1;
        y1Delta = 1;
        z1Delta = 1;

        xLootRoom = 7;
        yLootRoom = 1;

        xMinotaurSpawn = 6.5;
        yMinotaurSpawn = 1.5;
        zMinotaurSpawn = 5.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.forest);
        biomes.add(BiomeGenBase.forestHills);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack boots = new ItemStack(BestMazesItems.minotaurIvoryBoots, 1);
        boots.addEnchantment(Enchantment.featherFalling, 10);
        loot.add(boots);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new ForestConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][16];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 7, 14, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 15, walls);

        Drawer.fillParallelepipedon1(model, 3, 1, 14, 9, 1, -6, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 4, 1, 14, 7, 1, -5, walls);
        Drawer.fillParallelepipedon1(model, 5, 2, 14, 5, 1, -4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 2, 14, 3, 1, -3, walls);

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

    public Minotaur getMinotaur(World world){
        return new ForestMinotaur(world);
    }
}