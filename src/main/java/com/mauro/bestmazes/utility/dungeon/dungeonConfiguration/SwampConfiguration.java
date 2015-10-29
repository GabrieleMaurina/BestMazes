package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.entities.minotaurs.SwampMinotaur;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/26/2015.
 */
public class SwampConfiguration extends DungeonConfiguration{
    public SwampConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.005;
        waterProb = 0.005;
        spiderNetProb = 0.005;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = BestMazesBlocks.oakWoodPlanks;
        content = Blocks.air;
        name = DungeonReferences.SWAMP;
        prob = 0.005;

        xStart = 0;
        yStart = 6;
        zStart = 0;

        xSize = 1;
        ySize = 7;
        zSize = 7;

        xDelta = 1;
        yDelta = 3;
        zDelta = 2;

        x1Delta = 1;
        y1Delta = 2;
        z1Delta = 1;

        xLootRoom = 7;
        yLootRoom = 1;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.plains);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack boots = new ItemStack(Items.diamond_boots, 1);
        boots.addEnchantment(Enchantment.featherFalling, 10);
        loot.add(boots);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new PlainConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][16];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 7, 14, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 15, walls);

        Drawer.fillParallelepipedon1(model, 3, 1, 14, 9, 1, -6, BestMazesBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 4, 1, 14, 7, 1, -5, BestMazesBlocks.stoneBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 5, 2, 14, 5, 1, -4, BestMazesBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 2, 14, 3, 1, -3, BestMazesBlocks.stoneBricksSlabUp);

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
        return new SwampMinotaur(world);
    }
}
