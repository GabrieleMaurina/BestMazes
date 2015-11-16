package com.mauro.bestmazes.items;

import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

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

    public static MinotaurIvoryArmor minotaurIvoryHelmet = new MinotaurIvoryArmor(MinotaurIvoryArmor.HELMET);
    public static MinotaurIvoryArmor minotaurIvoryChestplate = new MinotaurIvoryArmor(MinotaurIvoryArmor.CHESTPLATE);
    public static MinotaurIvoryArmor minotaurIvoryLeggings = new MinotaurIvoryArmor(MinotaurIvoryArmor.LEGGINS);
    public static MinotaurIvoryArmor minotaurIvoryBoots = new MinotaurIvoryArmor(MinotaurIvoryArmor.BOOTS);

    public static void initItems(){
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

        GameRegistry.registerItem(minotaurIvoryHelmet, minotaurIvoryHelmet.name);
        GameRegistry.registerItem(minotaurIvoryChestplate, minotaurIvoryChestplate.name);
        GameRegistry.registerItem(minotaurIvoryLeggings, minotaurIvoryLeggings.name);
        GameRegistry.registerItem(minotaurIvoryBoots, minotaurIvoryBoots.name);
    }
}
