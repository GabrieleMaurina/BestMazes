package com.mauro.bestmazes.items;

import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.entities.BestMazesEntities;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class BestMazesItems {
    static public final Key darkKey = new Key(DungeonReferences.DARK);
    static public final Key classicKey = new Key(DungeonReferences.CLASSIC);
    static public final Key crazyKey = new Key(DungeonReferences.CRAZY);
    static public final Key desertKey = new Key(DungeonReferences.DESERT);
    static public final Key expandedKey = new Key(DungeonReferences.EXPANDED);
    static public final Key extremeKey = new Key(DungeonReferences.EXTREME);
    static public final Key hellKey = new Key(DungeonReferences.HELL);
    static public final Key iceKey = new Key(DungeonReferences.ICE);
    static public final Key narrowKey = new Key(DungeonReferences.NARROW);
    static public final Key oceanKey = new Key(DungeonReferences.OCEAN);
    static public final Key plainKey = new Key(DungeonReferences.PLAIN);
    static public final Key swampKey = new Key(DungeonReferences.SWAMP);
    static public final Key wideKey = new Key(DungeonReferences.WIDE);

    public static void initItems(){
        GameRegistry.registerItem(BestMazesItems.classicKey, BestMazesItems.classicKey.name);
        GameRegistry.registerItem(BestMazesItems.crazyKey, BestMazesItems.crazyKey.name);
        GameRegistry.registerItem(BestMazesItems.darkKey, BestMazesItems.darkKey.name);
        GameRegistry.registerItem(BestMazesItems.desertKey, BestMazesItems.desertKey.name);
        GameRegistry.registerItem(BestMazesItems.expandedKey, BestMazesItems.expandedKey.name);
        GameRegistry.registerItem(BestMazesItems.extremeKey, BestMazesItems.extremeKey.name);
        GameRegistry.registerItem(BestMazesItems.hellKey, BestMazesItems.hellKey.name);
        GameRegistry.registerItem(BestMazesItems.iceKey, BestMazesItems.iceKey.name);
        GameRegistry.registerItem(BestMazesItems.narrowKey, BestMazesItems.narrowKey.name);
        GameRegistry.registerItem(BestMazesItems.oceanKey, BestMazesItems.oceanKey.name);
        GameRegistry.registerItem(BestMazesItems.plainKey, BestMazesItems.plainKey.name);
        GameRegistry.registerItem(BestMazesItems.swampKey, BestMazesItems.swampKey.name);
        GameRegistry.registerItem(BestMazesItems.wideKey, BestMazesItems.wideKey.name);
    }
}
