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

    public static final Map<String, DungeonConfiguration> configurations = new HashMap<String, DungeonConfiguration>();
    public static final Map<BiomeGenBase, DungeonConfiguration> biomConf = new HashMap<BiomeGenBase, DungeonConfiguration>();

    static{
        initConfigurations();
    }

    private static void initConfigurations()
    {
        DungeonConfiguration dC;

        dC = new ForestConfiguration();
        configurations.put(dC.name, dC);
        dC = new TaigaConfiguration();
        configurations.put(dC.name, dC);
        dC = new SavannaConfiguration();
        configurations.put(dC.name, dC);
        dC = new JungleConfiguration();
        configurations.put(dC.name, dC);
        dC = new BirchConfiguration();
        configurations.put(dC.name, dC);
        dC = new NetherConfiguration();
        configurations.put(dC.name, dC);
        dC = new DesertConfiguration();
        configurations.put(dC.name, dC);
        dC = new OceanConfiguration();
        configurations.put(dC.name, dC);
        dC = new RoofedConfiguration();
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
        dC = new FinalConfiguration();
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
