package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.entities.minotaurs.PlainMinotaur;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
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
public class PlainConfiguration extends DungeonConfiguration {
    public PlainConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        tallGrassProb = 0.4;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = Blocks.grass;
        content = Blocks.air;
        name = DungeonReferences.PLAIN;
        prob = 0.004;

        xStart = 2;
        yStart = 1;
        zStart = 0;

        xSize = 6;
        ySize = 2;
        zSize = 6;

        xDelta = 2;
        yDelta = 4;
        zDelta = 2;

        x1Delta = 3;
        y1Delta = 3;
        z1Delta = 3;

        xLootRoom = 8;
        yLootRoom = 1;

        xMinotaurSpawn = 7.5;
        yMinotaurSpawn = 3.0;
        zMinotaurSpawn = 12.5;

        xHut = -4;
        zHut = -4;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.plains);
    }

    public ArrayList<ItemStack> getLoot(Random random){
        ArrayList<ItemStack> loot = super.getLoot(random);
        ItemStack saddles = new ItemStack(Items.saddle, random.nextInt(1) + 1);
        ItemStack armors = new ItemStack(Items.diamond_horse_armor, random.nextInt(1) + 1);
        loot.add(saddles);
        loot.add(armors);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new PlainConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[17][11][11];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 15, 9, 9, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 16, 10, 10, walls);


        Drawer.fillParallelepipedon1(model, 1, 2, 1, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 13, 2, 1, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 1, 4, 7, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 13, 4, 7, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 7, 6, 7, 3, 1, 3, walls);

        model[2][1][2] = walls;
        model[14][1][2] = walls;
        Drawer.fillParallelepipedon1(model, 2, 1, 8, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 14, 1, 8, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 8, 1, 5, 1, walls);

        Drawer.column(model, 5, 1, 5, walls, roof);
        Drawer.column(model, 11, 1, 5, walls, roof);
        Drawer.fillParallelepipedon1(model, 5, 8, 5, 1, 2, 1, walls);
        Drawer.fillParallelepipedon1(model, 11, 8, 5, 1, 2, 1, walls);


        Drawer.fillParallelepipedon1(model, 6, 1, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 5, 1, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 4, 2, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 10, 1, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 11, 1, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 12, 2, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 1, 3, 4, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 1, 3, 5, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 1, 4, 6, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 13, 3, 4, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 13, 3, 5, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 13, 4, 6, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 4, 5, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 5, 5, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 6, 6, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 12, 5, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 11, 5, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 10, 6, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);


        Drawer.fillParallelepipedon1(model, 7, 4, 1, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 7, 1, 3, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 9, 1, 3, 1, 3, 1, walls);

        Drawer.fillParallelepipedon1(model, 8, 1, 0, 1, 2, 1, content);

        model[8][7][8] = new Chest(getLoot(random), Chest.NORTH);

        model[7][8][9] = Blocks.torch;
        model[9][8][9] = Blocks.torch;

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new PlainMinotaur(world);
    }

    @Override
    public Block[][][] genEntranceHut(Random random) {
        Block[][][] model = new Block[9][9][9];
        Drawer.printModelOnBase(model, super.genEntranceHut(random), 1, 0, 1);

        int e = 6;
        for (int i = 1; i < 8; i++) {
            for (int o = 1; o < 8; o++) {
                if (random.nextDouble() < tallGrassProb) {
                    if (i == 4 && o == 4) {
                        e = 8;
                    } else if (i > 2 && i < 6 && o > 2 && o < 6) {
                        e = 7;
                    } else {
                        e = 6;
                    }
                    model[i][e][o] = BestMazesBlocks.tallGrass;
                }
            }
        }
        return model;
    }
}
