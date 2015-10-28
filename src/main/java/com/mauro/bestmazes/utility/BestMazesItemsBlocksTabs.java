package com.mauro.bestmazes.utility;

import com.mauro.bestmazes.BestMazes;
import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.items.Key;
import com.mauro.bestmazes.tabs.BestMazesTab;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * Created by Gabriele on 10/27/2015.
 */
public class BestMazesItemsBlocksTabs {

    static public final BestMazesTab bestMazesTab = BestMazes.bestMazesTab;

    static public Block piselliteBricks = new PiselliteBricks();

    static public final Key classicKey = new Key(DungeonReferences.CLASSIC);
    static public final Key crazyKey = new Key(DungeonReferences.CRAZY);
    static public final Key darkKey = new Key(DungeonReferences.DARK);
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

    public static void init(){
        GameRegistry.registerBlock(piselliteBricks, PiselliteBricks.name);

        GameRegistry.registerItem(classicKey, classicKey.name);
        GameRegistry.registerItem(crazyKey, crazyKey.name);
        GameRegistry.registerItem(darkKey, darkKey.name);
        GameRegistry.registerItem(desertKey, desertKey.name);
        GameRegistry.registerItem(expandedKey, expandedKey.name);
        GameRegistry.registerItem(extremeKey, extremeKey.name);
        GameRegistry.registerItem(hellKey, hellKey.name);
        GameRegistry.registerItem(iceKey, iceKey.name);
        GameRegistry.registerItem(narrowKey, narrowKey.name);
        GameRegistry.registerItem(oceanKey, oceanKey.name);
        GameRegistry.registerItem(plainKey, plainKey.name);
        GameRegistry.registerItem(swampKey, swampKey.name);
        GameRegistry.registerItem(wideKey, wideKey.name);
    }
}
