package com.gabrielemaurina.bestmazesbygabrielemaurina.biomegenbases;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by Gabriele on 12/1/2015.
 */
public class MazeBiome extends BiomeGenBase{
    public static final String name = "TheMaze";
    public MazeBiome(){
        super(getNextFreeId());
        setBiomeName(name);
        setEnableSnow();
    }

    public static int getNextFreeId(){
        BiomeGenBase[] biomes = BiomeGenBase.getBiomeGenArray();
        for(int i = 0; i < biomes.length; i++){
            if(biomes[i] == null){
                return i;
            }
        }
        return 255;
    }
}