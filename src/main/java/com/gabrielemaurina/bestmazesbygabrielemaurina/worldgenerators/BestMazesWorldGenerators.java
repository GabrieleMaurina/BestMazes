package com.gabrielemaurina.bestmazesbygabrielemaurina.worldgenerators;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class BestMazesWorldGenerators {
    public static StructureGenerator structureGenerator = new StructureGenerator();
    public static void initWorldGenerators(){
        GameRegistry.registerWorldGenerator(BestMazesWorldGenerators.structureGenerator, 0);
    }
}
