package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.dungeonConfiguration;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.ExtremeMinotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.Chest;
import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.Spawner;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.Minotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import net.minecraft.block.Block;
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
public class ExtremeConfiguration extends DungeonConfiguration{
    public ExtremeConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = Blocks.stone;
        content = Blocks.air;
        name = DungeonReferences.EXTREME;
        prob = 0.005;

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

        xLootRoom = 8;
        yLootRoom = 6;

        xMinotaurSpawn = 8.5;
        yMinotaurSpawn = 6.0;
        zMinotaurSpawn = 7.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.extremeHills);
        biomes.add(BiomeGenBase.extremeHillsEdge);
        biomes.add(BiomeGenBase.extremeHillsPlus);
    }

    public ArrayList<ItemStack> getLoot(Random random){
        ArrayList<ItemStack> loot = super.getLoot(random);
        loot.add(new ItemStack(Blocks.dragon_egg, 1));
        int n = random.nextInt(5) + 5;
        for(int i = 0; i < n; i++){
            ItemStack itemStack = new ItemStack(Items.spawn_egg, random.nextInt(2) + 1, Spawner.spawnEggsMetaData[random.nextInt(Spawner.spawnEggsMetaData.length)]);
            loot.add(itemStack);
        }
        return loot;
    }

    public DungeonConfiguration clone(){
        return new ExtremeConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[17][12][17];

        Drawer.drawParallelepipedon(model, 0, 0, 0, 16, 11, 16, walls);
        Drawer.fillParallelepipedon(model, 1, 0, 1, 15, 10, 15, content);

        Drawer.fillParallelepipedon1(model, 6, 5, 6, 5, 1, 5, walls);

        Drawer.fillParallelepipedon1(model, 2, 5, 8, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(model, 11, 5, 8, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 5, 2, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(model, 8, 5, 11, 1, 1, 4, walls);

        Drawer.fillParallelepipedon1(model, 1, 5, 15, 15, 1, 1, walls);
        Drawer.fillParallelepipedon1(model, 1, 5, 1, 15, 1, 1, walls);
        Drawer.fillParallelepipedon1(model, 1, 5, 2, 1, 1, 13, walls);
        Drawer.fillParallelepipedon1(model, 15, 5, 2, 1, 1, 13, walls);

        Drawer.column(model, 2, 5, 2, walls, roof);
        Drawer.column(model, 2, 5, 14, walls, roof);
        Drawer.column(model, 14, 5, 2, walls, roof);
        Drawer.column(model, 14, 5, 14, walls, roof);

        Drawer.fillParallelepipedon1(model, 2, 5, 2, 2, 1, 2, walls);
        Drawer.fillParallelepipedon1(model, 2, 5, 13, 2, 1, 2, walls);
        Drawer.fillParallelepipedon1(model, 13, 5, 2, 2, 1, 2, walls);
        Drawer.fillParallelepipedon1(model, 13, 5, 13, 2, 1, 2, walls);

        Drawer.fillParallelepipedon1(model, xLootRoom, yLootRoom, 0, 1, 2, 1, content);

        model[8][6][8] = new Chest(getLoot(random), Chest.NORTH);

        model[7][6][8] = Blocks.torch;
        model[9][6][8] = Blocks.torch;

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new ExtremeMinotaur(world);
    }
}