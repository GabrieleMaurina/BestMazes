package com.mauro.bestmazes.dungeon;

import com.mauro.bestmazes.blocks.PiselliteBricks;
import net.minecraft.block.Block;
import net.minecraft.world.World;

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

    public DungeonConfiguration(){}

    public static void initConfigurations(){
        DungeonConfiguration classic = new DungeonConfiguration();

        classic.branchesProb = 0.7;
        classic.joinProb = 0.005;
        classic.crazy = false;
        classic.block = PiselliteBricks.piselliteBricks;

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

        configurations.put("classic", classic);

        DungeonConfiguration narrow = new DungeonConfiguration();

        narrow.branchesProb = 0.7;
        narrow.joinProb = 0.005;
        narrow.crazy = false;
        narrow.block = PiselliteBricks.piselliteBricks;

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

        configurations.put("narrow", narrow);

        DungeonConfiguration wide = new DungeonConfiguration();

        wide.branchesProb = 0.7;
        wide.joinProb = 0.005;
        wide.crazy = false;
        wide.block = PiselliteBricks.piselliteBricks;

        wide.xStart = 0;
        wide.yStart = 4;
        wide.zStart = 0;

        wide.xSize = 8;
        wide.ySize = 5;
        wide.zSize = 8;

        wide.xDelta = 4;
        wide.yDelta = 4;
        wide.zDelta = 4;

        wide.x1Delta = 1;
        wide.y1Delta = 1;
        wide.z1Delta = 1;

        configurations.put("wide", wide);
    }

    public DungeonConfiguration clone(){
        DungeonConfiguration dC = new DungeonConfiguration();

        dC.branchesProb = branchesProb;
        dC.joinProb = joinProb;
        dC.crazy = crazy;
        dC.block = block;

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
        Object[] values = configurations.values().toArray();
        return (DungeonConfiguration) values[random.nextInt(values.length)];
    }
}
