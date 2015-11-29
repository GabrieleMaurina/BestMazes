package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.dungeonConfiguration;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.Minotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.SwampMinotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.Chest;
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
public class SwampConfiguration extends DungeonConfiguration{

    public SwampConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        tallGrassProb = 0.4;
        vineProb = 0.5;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = Blocks.grass;
        content = Blocks.air;
        name = DungeonReferences.SWAMP;
        prob = 0.005;

        xStart = 0;
        yStart = 6;
        zStart = 0;

        xSize = 1;
        ySize = 7;
        zSize = 7;

        xDelta = 1;
        yDelta = 3;
        zDelta = 2;

        x1Delta = 1;
        y1Delta = 2;
        z1Delta = 1;

        xLootRoom = 1;
        yLootRoom = 1;

        xHut = -4;
        zHut = -4;

        xMinotaurSpawn = 8.5;
        yMinotaurSpawn = 9.0;
        zMinotaurSpawn = 7.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.swampland);
    }

    public ArrayList<ItemStack> getLoot(Random random){
        ArrayList<ItemStack> loot = super.getLoot(random);
        int n = random.nextInt(5) + 5;
        int metadata;
        for(int i = 0; i < n; i++){
            metadata = random.nextInt(64);
            metadata += random.nextBoolean() ? 64 : 0;
            metadata += random.nextBoolean() ? 16384 : 0;
            loot.add(new ItemStack(Items.potionitem, random.nextInt(2) + 1, metadata));
        }
        return loot;
    }

    public DungeonConfiguration clone(){
        return new SwampConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[17][12][17];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 15, 10, 15, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 16, 11, 16, walls);


        Drawer.fillParallelepipedon1(model, 7, 8, 7, 3, 1, 3, walls);

        Drawer.fillParallelepipedon1(model, 7, 6, 1, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 7, 6, 13, 3, 1, 3, walls);

        Drawer.fillParallelepipedon1(model, 13, 4, 1, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 1, 4, 13, 3, 1, 3, walls);

        Drawer.fillParallelepipedon1(model, 1, 2, 7, 3, 1, 3, walls);
        Drawer.fillParallelepipedon1(model, 13, 2, 7, 3, 1, 3, walls);


        model[14][1][8] = walls;
        model[2][1][8] = walls;

        Drawer.fillParallelepipedon1(model, 14, 1, 2, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 2, 1, 14, 1, 3, 1, walls);

        Drawer.fillParallelepipedon1(model, 8, 1, 2, 1, 5, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 14, 1, 5, 1, walls);

        Drawer.column(model, 8, 1, 8, walls, roof);


        Drawer.column(model, 2, 1, 2, walls, roof);
        Drawer.fillParallelepipedon(model, 2, 8, 2, 2, 11, 2, walls);
        Drawer.column(model, 4, 1, 2, walls, roof);
        Drawer.fillParallelepipedon(model, 4, 8, 2, 4, 11, 2, walls);
        Drawer.column(model, 2, 1, 4, walls, roof);
        Drawer.fillParallelepipedon(model, 2, 8, 4, 2, 11, 4, walls);
        Drawer.column(model, 4, 1, 4, walls, roof);
        Drawer.fillParallelepipedon(model, 4, 8, 4, 4, 11, 4, walls);

        Drawer.column(model, 11, 1, 11, walls, roof);
        Drawer.fillParallelepipedon(model, 11, 8, 11, 11, 11, 11, walls);
        Drawer.column(model, 14, 1, 11, walls, roof);
        Drawer.fillParallelepipedon(model, 14, 8, 11, 14, 11, 11, walls);
        Drawer.column(model, 11, 1, 14, walls, roof);
        Drawer.fillParallelepipedon(model, 11, 8, 14, 11, 11, 14, walls);
        Drawer.column(model, 14, 1, 14, walls, roof);
        Drawer.fillParallelepipedon(model, 14, 8, 14, 14, 11, 14, walls);


        Drawer.fillParallelepipedon1(model, 7, 7, 4, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 7, 7, 5, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 7, 8, 6, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 7, 7, 12, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 7, 7, 11, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 7, 8, 10, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 13, 3, 6, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 13, 3, 5, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 13, 4, 4, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 1, 3, 10, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 1, 3, 11, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 1, 4, 12, 3, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 10, 1, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 11, 1, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 12, 2, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 6, 1, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 5, 1, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 4, 2, 7, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 12, 5, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 11, 5, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 10, 6, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 4, 5, 13, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 5, 5, 13, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 6, 6, 13, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        model[8][9][8] = new Chest(getLoot(random), Chest.NORTH);

        model[9][9][8] = Blocks.torch;
        model[7][9][8] = Blocks.torch;

        Drawer.fillParallelepipedon1(model, xLootRoom, yLootRoom, 0, 1, 2, 1, content);

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new SwampMinotaur(world);
    }

    @Override
    public Block[][][] genEntranceHut(Random random) {
        Block[][][] model = new Block[9][9][9];
        Drawer.printModelOnBase(model, super.genEntranceHut(random), 1, 0, 1);

        int e = 6;
        for(int i = 1; i < 8; i++){
            for(int o = 1; o < 8; o++){
                if(random.nextDouble() < tallGrassProb)
                {
                    if(i == 4 && o == 4){
                        e = 8;
                    }
                    else if(i > 2 && i < 6 && o > 2 && o < 6){
                        e = 7;
                    }
                    else{
                        e = 6;
                    }
                    model[i][e][o] = BestMazesBlocks.tallGrass;
                }
            }
        }

        for(int i = 1; i < 8; i++){
            if(random.nextDouble() < 0.5){
                model[i][0][0] = BestMazesBlocks.vineSouth;
            }
            if(random.nextDouble() < 0.5){
                model[i][0][8] = BestMazesBlocks.vineNorth;
            }
            if(random.nextDouble() < 0.5){
                model[0][0][i] = BestMazesBlocks.vineEast;
            }
            if(random.nextDouble() < 0.5){
                model[8][0][i] = BestMazesBlocks.vineWest;
            }
            if(random.nextDouble() < 0.5){
                int l = random.nextInt(3) + 3;
                for(e = 0; e < l; e++) {
                    model[i][5 - e][0] = BestMazesBlocks.vineSouth;
                }
            }
            if(random.nextDouble() < 0.5){
                int l = random.nextInt(3) + 3;
                for(e = 0; e < l; e++) {
                    model[i][5 - e][8] = BestMazesBlocks.vineNorth;
                }
            }
            if(random.nextDouble() < 0.5){
                int l = random.nextInt(3) + 3;
                for(e = 0; e < l; e++) {
                    model[0][5 - e][i] = BestMazesBlocks.vineEast;
                }
            }
            if(random.nextDouble() < 0.5){
                int l = random.nextInt(3) + 3;
                for(e = 0; e < l; e++) {
                    model[8][5 - e][i] = BestMazesBlocks.vineWest;
                }
            }
        }

        return model;
    }
}
