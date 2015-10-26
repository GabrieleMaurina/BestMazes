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
        configurations.put("classic", new ClassicConfiguration());
        configurations.put("narrow", new NarrowConfiguration());
        configurations.put("wide", new WideConfiguration());
        configurations.put("expanded", new ExpandedConfiguration());
        configurations.put("crazy", new CrazyConfiguration());
        configurations.put("hell", new HellConfiguration());
        configurations.put("desert", new DesertConfiguration());
        configurations.put("ocean", new OceanConfiguration());
        configurations.put("dark", new DarkConfiguration());

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
