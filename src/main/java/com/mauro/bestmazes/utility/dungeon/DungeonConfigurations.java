package com.mauro.bestmazes.utility.dungeon;

import com.mauro.bestmazes.utility.dungeon.dungeonConfiguration.*;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Gabriele on 10/23/2015.
 */
public class DungeonConfigurations {

    private static final Map<String, DungeonConfiguration> configurations = new HashMap<String, DungeonConfiguration>();
    private static final Map<BiomeGenBase, DungeonConfiguration> biomConf = new HashMap<BiomeGenBase, DungeonConfiguration>();

    static{
        initConfigurations();
    }

    private static void initConfigurations()
    {
        DungeonConfiguration dC;

        dC = new ClassicConfiguration();
        configurations.put(dC.name, dC);
        dC = new NarrowConfiguration();
        configurations.put(dC.name, dC);
        dC = new WideConfiguration();
        configurations.put(dC.name, dC);
        dC = new ExpandedConfiguration();
        configurations.put(dC.name, dC);
        dC = new CrazyConfiguration();
        configurations.put(dC.name, dC);
        dC = new HellConfiguration();
        configurations.put(dC.name, dC);
        dC = new DesertConfiguration();
        configurations.put(dC.name, dC);
        dC = new OceanConfiguration();
        configurations.put(dC.name, dC);
        dC = new DarkConfiguration();
        configurations.put(dC.name, dC);
        dC = new IceConfiguration();
        configurations.put(dC.name, dC);
        dC = new PlainConfiguration();
        configurations.put(dC.name, dC);
        dC = new ExtremeConfiguration();
        configurations.put(dC.name, dC);
        dC = new SwampConfiguration();
        configurations.put(dC.name, dC);
        dC = new EndConfiguration();
        configurations.put(dC.name, dC);

        for (Map.Entry<String, DungeonConfiguration> entry : configurations.entrySet())
        {
            for(BiomeGenBase biome : entry.getValue().biomes){
                biomConf.put(biome, entry.getValue());
            }
        }
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
