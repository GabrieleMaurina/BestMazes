package com.mauro.bestmazes.items;

import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class BestMazesItems {
    static public final Key roofedKey = new Key(DungeonReferences.ROOFED);
    static public final Key forestKey = new Key(DungeonReferences.FOREST);
    static public final Key birchKey = new Key(DungeonReferences.BIRCH);
    static public final Key desertKey = new Key(DungeonReferences.DESERT);
    static public final Key jungleKey = new Key(DungeonReferences.JUNGLE);
    static public final Key extremeKey = new Key(DungeonReferences.EXTREME);
    static public final Key netherKey = new Key(DungeonReferences.NETHER);
    static public final Key iceKey = new Key(DungeonReferences.ICE);
    static public final Key taigaKey = new Key(DungeonReferences.TAIGA);
    static public final Key oceanKey = new Key(DungeonReferences.OCEAN);
    static public final Key plainKey = new Key(DungeonReferences.PLAIN);
    static public final Key swampKey = new Key(DungeonReferences.SWAMP);
    static public final Key savannaKey = new Key(DungeonReferences.SAVANNA);

    static public final MinotaurIvorySword minotaurIvorySword = new MinotaurIvorySword();

    public static MinotaurIvoryArmor minotaurIvoryHelmet = new MinotaurIvoryArmor(MinotaurIvoryArmor.HELMET);
    public static MinotaurIvoryArmor minotaurIvoryChestplate = new MinotaurIvoryArmor(MinotaurIvoryArmor.CHESTPLATE);
    public static MinotaurIvoryArmor minotaurIvoryLeggings = new MinotaurIvoryArmor(MinotaurIvoryArmor.LEGGINS);
    public static MinotaurIvoryArmor minotaurIvoryBoots = new MinotaurIvoryArmor(MinotaurIvoryArmor.BOOTS);

    public static void initItems(){
        GameRegistry.registerItem(forestKey, forestKey.name);
        GameRegistry.registerItem(birchKey, birchKey.name);
        GameRegistry.registerItem(roofedKey, roofedKey.name);
        GameRegistry.registerItem(desertKey, desertKey.name);
        GameRegistry.registerItem(jungleKey, jungleKey.name);
        GameRegistry.registerItem(extremeKey, extremeKey.name);
        GameRegistry.registerItem(netherKey, netherKey.name);
        GameRegistry.registerItem(iceKey, iceKey.name);
        GameRegistry.registerItem(taigaKey, taigaKey.name);
        GameRegistry.registerItem(oceanKey, oceanKey.name);
        GameRegistry.registerItem(plainKey, plainKey.name);
        GameRegistry.registerItem(swampKey, swampKey.name);
        GameRegistry.registerItem(savannaKey, savannaKey.name);

        GameRegistry.registerItem(minotaurIvorySword, MinotaurIvorySword.name);

        GameRegistry.registerItem(minotaurIvoryHelmet, minotaurIvoryHelmet.name);
        GameRegistry.registerItem(minotaurIvoryChestplate, minotaurIvoryChestplate.name);
        GameRegistry.registerItem(minotaurIvoryLeggings, minotaurIvoryLeggings.name);
        GameRegistry.registerItem(minotaurIvoryBoots, minotaurIvoryBoots.name);
    }
}
