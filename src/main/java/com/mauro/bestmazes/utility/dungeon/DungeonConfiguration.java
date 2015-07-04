package com.mauro.bestmazes.utility.dungeon;

import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.SpecialBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Gabriele on 7/3/2015.
 */
public class DungeonConfiguration {

    public World world;
    public Random random;
    public double branchesProb;
    public double joinProb;
    public boolean crazy;
    public Block block;
    public Block block1;
    public String name;
    public Item item;
    public double prob;

    public int xStart;
    public int yStart;
    public int zStart;

    public int xSize;
    public int ySize;
    public int zSize;

    public int xDelta;
    public int yDelta;
    public int zDelta;

    public int x1Delta;
    public int y1Delta;
    public int z1Delta;

    private static Map<String, DungeonConfiguration> configurations = new HashMap<String, DungeonConfiguration>();
    private static Map<BiomeGenBase, DungeonConfiguration> biomConf = new HashMap<BiomeGenBase, DungeonConfiguration>();

    public DungeonConfiguration(){}

    public static void initConfigurations(){

        // ****CLASSIC****

            DungeonConfiguration classic = new DungeonConfiguration();

            classic.branchesProb = 0.7;
            classic.joinProb = 0.005;
            classic.crazy = false;
            classic.block = PiselliteBricks.piselliteBricks;
            classic.block1 = SpecialBlocks.oakWoodPlanks;
            classic.name = "classic";
            classic.item = Items.diamond_boots;
            classic.prob = 0.005;

            classic.xStart = 0;
            classic.yStart = 4;
            classic.zStart = 0;

            classic.xSize = 8;
            classic.ySize = 5;
            classic.zSize = 8;

            classic.xDelta = 2;
            classic.yDelta = 3;
            classic.zDelta = 2;

            classic.x1Delta = 1;
            classic.y1Delta = 1;
            classic.z1Delta = 1;

            biomConf.put(BiomeGenBase.forest, classic);
            biomConf.put(BiomeGenBase.forestHills, classic);

            configurations.put("classic", classic);

        // ****NARROW****

            DungeonConfiguration narrow = new DungeonConfiguration();

            narrow.branchesProb = 0.7;
            narrow.joinProb = 0.005;
            narrow.crazy = false;
            narrow.block = PiselliteBricks.piselliteBricks;
            narrow.block1 = SpecialBlocks.spruceWoodPlanks;
            narrow.name = "narrow";
            narrow.item = Items.diamond_chestplate;
            narrow.prob = 0.01;

            narrow.xStart = 0;
            narrow.yStart = 4;
            narrow.zStart = 0;

            narrow.xSize = 8;
            narrow.ySize = 5;
            narrow.zSize = 8;

            narrow.xDelta = 1;
            narrow.yDelta = 2;
            narrow.zDelta = 1;

            narrow.x1Delta = 1;
            narrow.y1Delta = 1;
            narrow.z1Delta = 1;

            biomConf.put(BiomeGenBase.taiga, narrow);
            biomConf.put(BiomeGenBase.taigaHills, narrow);
            biomConf.put(BiomeGenBase.coldTaiga, narrow);
            biomConf.put(BiomeGenBase.megaTaiga, narrow);
            biomConf.put(BiomeGenBase.coldTaigaHills, narrow);
            biomConf.put(BiomeGenBase.megaTaigaHills, narrow);

            configurations.put("narrow", narrow);

        // ****WIDE****

            DungeonConfiguration wide = new DungeonConfiguration();

            wide.branchesProb = 0.7;
            wide.joinProb = 0.005;
            wide.crazy = false;
            wide.block = PiselliteBricks.piselliteBricks;
            wide.block1 = SpecialBlocks.acaciaWoodPlanks;
            wide.name = "wide";
            wide.item = Items.diamond_leggings;
            wide.prob = 0.008;

            wide.xStart = 0;
            wide.yStart = 4;
            wide.zStart = 0;

            wide.xSize = 8;
            wide.ySize = 5;
            wide.zSize = 8;

            wide.xDelta = 3;
            wide.yDelta = 3;
            wide.zDelta = 3;

            wide.x1Delta = 1;
            wide.y1Delta = 1;
            wide.z1Delta = 1;

            biomConf.put(BiomeGenBase.savanna, wide);
            biomConf.put(BiomeGenBase.savannaPlateau, wide);

            configurations.put("wide", wide);

        // ****EXPANDED****

            DungeonConfiguration expanded = new DungeonConfiguration();

            expanded.branchesProb = 0.7;
            expanded.joinProb = 0.005;
            expanded.crazy = false;
            expanded.block = PiselliteBricks.piselliteBricks;
            expanded.block1 = SpecialBlocks.jungleWoodPlanks;
            expanded.name = "expanded";
            expanded.item = Items.diamond_helmet;
            expanded.prob = 0.01;

            expanded.xStart = 0;
            expanded.yStart = 4;
            expanded.zStart = 0;

            expanded.xSize = 8;
            expanded.ySize = 5;
            expanded.zSize = 8;

            expanded.xDelta = 4;
            expanded.yDelta = 4;
            expanded.zDelta = 4;

            expanded.x1Delta = 2;
            expanded.y1Delta = 1;
            expanded.z1Delta = 2;

            biomConf.put(BiomeGenBase.jungle, narrow);
            biomConf.put(BiomeGenBase.jungleEdge, narrow);
            biomConf.put(BiomeGenBase.jungleHills, narrow);
            configurations.put("expanded", expanded);

        // ****CRAZY****

            DungeonConfiguration crazy = new DungeonConfiguration();

            crazy.branchesProb = 0.7;
            crazy.joinProb = 0.01;
            crazy.crazy = true;
            crazy.block = PiselliteBricks.piselliteBricks;
            crazy.block1 = SpecialBlocks.britchWoodPlanks;
            crazy.name = "crazy";
            crazy.item = Items.diamond_sword;
            crazy.prob = 0.008;

            crazy.xStart = 0;
            crazy.yStart = 4;
            crazy.zStart = 0;

            crazy.xSize = 8;
            crazy.ySize = 5;
            crazy.zSize = 8;

            biomConf.put(BiomeGenBase.birchForest, crazy);
            biomConf.put(BiomeGenBase.birchForestHills, crazy);

            configurations.put("crazy", crazy);
    }

    public DungeonConfiguration clone(){

        DungeonConfiguration dC = new DungeonConfiguration();

        dC.branchesProb = branchesProb;
        dC.joinProb = joinProb;
        dC.crazy = crazy;
        dC.block = block;
        dC.block1 = block1;
        dC.name = name;
        dC.item = item;
        dC.prob = prob;

        dC.xStart = xStart;
        dC.yStart = yStart;
        dC.zStart = zStart;

        dC.xSize = xSize;
        dC.ySize = ySize;
        dC.zSize = zSize;

        dC.xDelta = xDelta;
        dC.yDelta = yDelta;
        dC.zDelta = zDelta;

        dC.x1Delta = x1Delta;
        dC.y1Delta = y1Delta;
        dC.z1Delta = z1Delta;

        return dC;
    }

    public static DungeonConfiguration getConfiguration(String name){
        return configurations.get(name).clone();
    }

    public static DungeonConfiguration getRandomDungeonConfiguration(Random random){
        Object[] keys = configurations.keySet().toArray();
        return getConfiguration((String) keys[random.nextInt(keys.length)]);
    }

    public static DungeonConfiguration getConfByBiome(BiomeGenBase biome){
        DungeonConfiguration dC = biomConf.get(biome);
        if(dC != null){
            dC = dC.clone();
        }
        return dC;
    }
}
