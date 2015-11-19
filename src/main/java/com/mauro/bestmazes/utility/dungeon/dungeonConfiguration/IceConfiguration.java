package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.Spawner;
import com.mauro.bestmazes.entities.minotaurs.IceMinotaur;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/26/2015.
 */
public class IceConfiguration extends DungeonConfiguration {
    public IceConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = Blocks.packed_ice;
        content = Blocks.air;
        name = DungeonReferences.ICE;
        prob = 0.005;

        xStart = 2;
        yStart = 3;
        zStart = 0;

        xSize = 4;
        ySize = 4;
        zSize = 4;

        xDelta = 2;
        yDelta = 2;
        zDelta = 2;

        x1Delta = 2;
        y1Delta = 2;
        z1Delta = 2;

        xLootRoom = 8;
        yLootRoom = 1;

        xMinotaurSpawn = 8.5;
        yMinotaurSpawn = 5.0;
        zMinotaurSpawn = 10.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.iceMountains);
        biomes.add(BiomeGenBase.icePlains);
        biomes.add(BiomeGenBase.frozenRiver);
        biomes.add(BiomeGenBase.frozenOcean);
        biomes.add(BiomeGenBase.coldBeach);
    }

    public ArrayList<ItemStack> getLoot(Random random){
        ArrayList<ItemStack> loot = super.getLoot(random);
        for(int i = 0; i < Spawner.spawnEggsMetaData.length; i++){
            loot.add(new ItemStack(Blocks.mob_spawner, 1, Spawner.spawnEggsMetaData[i]));
        }

        return loot;
    }

    public DungeonConfiguration clone(){
        return new IceConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[17][9][16];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 15, 7, 14, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 16, 8, 15, walls);

        Drawer.fillParallelepipedon1(model, 7, 5, 12, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 7, 5, 11, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 4, 10, 5, 1, 1, BestMazesBlocks.piselliteBricks);
        Drawer.fillParallelepipedon1(model, 6, 4, 9, 5, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 5, 3, 8, 7, 1, 1, BestMazesBlocks.piselliteBricks);
        Drawer.fillParallelepipedon1(model, 5, 3, 7, 7, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 4, 2, 6, 9, 1, 1, BestMazesBlocks.piselliteBricks);
        Drawer.fillParallelepipedon1(model, 4, 2, 5, 9, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 3, 1, 4, 11, 1, 1, BestMazesBlocks.piselliteBricks);
        Drawer.fillParallelepipedon1(model, 3, 1, 3, 11, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.column(model, 2, 1, 7, walls, roof);
        Drawer.column(model, 3, 1, 9, walls, roof);
        Drawer.column(model, 4, 1, 11, walls, roof);
        Drawer.column(model, 5, 1, 13, walls, roof);
        Drawer.column(model, 14, 1, 7, walls, roof);
        Drawer.column(model, 13, 1, 9, walls, roof);
        Drawer.column(model, 12, 1, 11, walls, roof);
        Drawer.column(model, 11, 1, 13, walls, roof);

        Drawer.fillParallelepipedon1(model, 8, 1, 13, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, xLootRoom, yLootRoom, 0, 1, 2, 1, content);

        model[8][6][13] = new Chest(getLoot(random), Chest.NORTH);

        model[7][7][14] = Blocks.torch;
        model[9][7][14] = Blocks.torch;

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new IceMinotaur(world);
    }
}
