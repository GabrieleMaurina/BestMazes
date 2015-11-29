package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.dungeonConfiguration;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.Minotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.items.BestMazesItems;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 11/17/2015.
 */
public class FinalConfiguration extends DungeonConfiguration{
    public FinalConfiguration(){
        passageProb = 0.000;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.00;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = BestMazesBlocks.oakWoodPlanks;
        content = Blocks.air;
        name = DungeonReferences.FINAL;
        prob = 0.000;

        xStart = 0;
        yStart = 0;
        zStart = 0;

        xSize = 6;
        ySize = 1;
        zSize = 6;

        xDelta = 1;
        yDelta = 2;
        zDelta = 1;

        x1Delta = 1;
        y1Delta = 1;
        z1Delta = 1;

        biomes = new ArrayList<BiomeGenBase>();
    }

    public ArrayList<ItemStack> getLoot(Random random){
        ArrayList<ItemStack> loot = super.getLoot(random);
        ItemStack sword = new ItemStack(BestMazesItems.minotaurIvorySword, 1);
        sword.addEnchantment(Enchantment.sharpness, 10);
        loot.add(sword);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new FinalConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        return null;
    }

    public Minotaur getMinotaur(World world){
        return null;
    }
}
