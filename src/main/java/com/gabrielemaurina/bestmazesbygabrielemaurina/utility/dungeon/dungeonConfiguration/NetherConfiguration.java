package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.dungeonConfiguration;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.Chest;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.NetherMinotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.Minotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/21/2015.
 */
public class NetherConfiguration extends DungeonConfiguration{

    public NetherConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.01;
        crazy = true;
        walls = BestMazesBlocks.piselliteBricks;
        roof = Blocks.nether_brick;
        content = Blocks.air;
        name = DungeonReferences.NETHER;
        prob = 0.004;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xLootRoom = 6;
        yLootRoom = 3;

        xMinotaurSpawn = 6.5;
        yMinotaurSpawn = 1.0;
        zMinotaurSpawn = 5.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.hell);
    }

    public ArrayList<ItemStack> getLoot(Random random){
        ArrayList<ItemStack> loot = super.getLoot(random);
        loot.add(new ItemStack(Items.nether_star, random.nextInt(2) + 1));
        loot.add(new ItemStack(Items.ghast_tear, random.nextInt(4) + 1));
        return loot;
    }

    public DungeonConfiguration clone(){
        return new NetherConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[13][11][13];

        Drawer.fillParallelepipedon(model, 1, 3, 1, 11, 9, 11, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 12, 10, 12, walls);

        Drawer.fillParallelepipedon(model, 1, 1, 1, 11, 2, 11, walls);

        Drawer.fillParallelepipedon1(model, 2, 2, 2, 9, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 2, 2, 10, 9, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 2, 2, 3, 1, 1, 7, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 10, 2, 3, 1, 1, 7, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 3, 2, 3, 7, 1, 7, Blocks.air);

        Drawer.fillParallelepipedon1(model, 4, 1, 4, 5, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 4, 1, 8, 5, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 4, 1, 5, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 8, 1, 5, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 5, 1, 5, 3, 1, 3, Blocks.air);


        Drawer.fillParallelepipedon1(model, 5, 2, 2, 1, 4, 1, roof);
        Drawer.fillParallelepipedon1(model, 5, 6, 2, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 7, 2, 2, 1, 4, 1, roof);
        Drawer.fillParallelepipedon1(model, 7, 6, 2, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 5, 2, 10, 1, 4, 1, roof);
        Drawer.fillParallelepipedon1(model, 5, 6, 10, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 7, 2, 10, 1, 4, 1, roof);
        Drawer.fillParallelepipedon1(model, 7, 6, 10, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 2, 2, 5, 1, 4, 1, roof);
        Drawer.fillParallelepipedon1(model, 2, 6, 5, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 2, 2, 7, 1, 4, 1, roof);
        Drawer.fillParallelepipedon1(model, 2, 6, 7, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 10, 2, 5, 1, 4, 1, roof);
        Drawer.fillParallelepipedon1(model, 10, 6, 5, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 10, 2, 7, 1, 4, 1, roof);
        Drawer.fillParallelepipedon1(model, 10, 6, 7, 1, 4, 1, walls);

        Drawer.fillParallelepipedon1(model, 3, 2, 3, 1, 5, 1, roof);
        Drawer.fillParallelepipedon1(model, 3, 7, 3, 1, 3, 1, walls);

        Drawer.fillParallelepipedon1(model, 3, 2, 9, 1, 5, 1, roof);
        Drawer.fillParallelepipedon1(model, 3, 7, 9, 1, 3, 1, walls);

        Drawer.fillParallelepipedon1(model, 9, 2, 3, 1, 5, 1, roof);
        Drawer.fillParallelepipedon1(model, 9, 7, 3, 1, 3, 1, walls);

        Drawer.fillParallelepipedon1(model, 9, 2, 9, 1, 5, 1, roof);
        Drawer.fillParallelepipedon1(model, 9, 7, 9, 1, 3, 1, walls);


        Drawer.fillParallelepipedon1(model, 5, 6, 3, 1, 2, 1, roof);
        Drawer.fillParallelepipedon1(model, 5, 8, 3, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 7, 6, 3, 1, 2, 1, roof);
        Drawer.fillParallelepipedon1(model, 7, 8, 3, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 5, 6, 9, 1, 2, 1, roof);
        Drawer.fillParallelepipedon1(model, 5, 8, 9, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 7, 6, 9, 1, 2, 1, roof);
        Drawer.fillParallelepipedon1(model, 7, 8, 9, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 3, 6, 5, 1, 2, 1, roof);
        Drawer.fillParallelepipedon1(model, 3, 8, 5, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 3, 6, 7, 1, 2, 1, roof);
        Drawer.fillParallelepipedon1(model, 3, 8, 7, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 9, 6, 5, 1, 2, 1, roof);
        Drawer.fillParallelepipedon1(model, 9, 8, 5, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 9, 6, 7, 1, 2, 1, roof);
        Drawer.fillParallelepipedon1(model, 9, 8, 7, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 4, 7, 4, 1, 1, 1, roof);
        Drawer.fillParallelepipedon1(model, 4, 8, 4, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 4, 7, 8, 1, 1, 1, roof);
        Drawer.fillParallelepipedon1(model, 4, 8, 8, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 8, 7, 4, 1, 1, 1, roof);
        Drawer.fillParallelepipedon1(model, 8, 8, 4, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 8, 7, 8, 1, 1, 1, roof);
        Drawer.fillParallelepipedon1(model, 8, 8, 8, 1, 2, 1, walls);


        model[5][9][5] = roof;

        model[5][9][7] = roof;

        model[7][9][5] = roof;

        model[7][9][7] = roof;

        model[5][8][4] = roof;
        model[5][9][4] = walls;

        model[7][8][4] = roof;
        model[7][9][4] = walls;

        model[5][8][8] = roof;
        model[5][9][8] = walls;

        model[7][8][8] = roof;
        model[7][9][8] = walls;

        model[4][8][5] = roof;
        model[4][9][5] = walls;

        model[4][8][7] = roof;
        model[4][9][7] = walls;

        model[8][8][5] = roof;
        model[8][9][5] = walls;

        model[8][8][7] = roof;
        model[8][9][7] = walls;

        model[6][9][6] = roof;

        Drawer.fillParallelepipedon1(model, 5, 3, 1, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 7, 3, 1, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 5, 3, 11, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 7, 3, 11, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 1, 3, 5, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 1, 3, 7, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 11, 3, 5, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 11, 3, 7, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 2, 2, 2, 1, 8, 1, walls);
        Drawer.fillParallelepipedon1(model, 2, 2, 10, 1, 8, 1, walls);
        Drawer.fillParallelepipedon1(model, 10, 2, 2, 1, 8, 1, walls);
        Drawer.fillParallelepipedon1(model, 10, 2, 10, 1, 8, 1, walls);
        Drawer.fillParallelepipedon1(model, 1, 3, 1, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 1, 3, 11, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 11, 3, 1, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 11, 3, 11, 1, 7, 1, walls);

        Drawer.fillParallelepipedon1(model, 6, 3, 0, 1, 2, 1, content);

        model[6][1][6] = new Chest(getLoot(random), Chest.NORTH);

        model[5][1][6] = Blocks.torch;
        model[7][1][6] = Blocks.torch;

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new NetherMinotaur(world);
    }
}