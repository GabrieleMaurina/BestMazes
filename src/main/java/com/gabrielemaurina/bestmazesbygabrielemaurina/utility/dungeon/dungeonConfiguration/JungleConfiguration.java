package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.dungeonConfiguration;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.JungleMinotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.items.BestMazesItems;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.Chest;
import com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs.Minotaur;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/21/2015.
 */
public class JungleConfiguration extends DungeonConfiguration{

    public JungleConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.0005;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = BestMazesBlocks.jungleWoodPlanks;
        content = Blocks.air;
        name = DungeonReferences.JUNGLE;
        prob = 0.005;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xDelta = 4;
        yDelta = 4;
        zDelta = 4;

        x1Delta = 2;
        y1Delta = 1;
        z1Delta = 2;

        xLootRoom = 7;
        yLootRoom = 1;

        xHut = -4;
        zHut = -4;

        xMinotaurSpawn = 7.5;
        yMinotaurSpawn = 3.0;
        zMinotaurSpawn = 6.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.jungle);
        biomes.add(BiomeGenBase.jungleEdge);
        biomes.add(BiomeGenBase.jungleHills);
    }

    public ArrayList<ItemStack> getLoot(Random random){
        ArrayList<ItemStack> loot = super.getLoot(random);
        loot.add(new ItemStack(BestMazesItems.minotaurIvoryHelmet, 1));
        return loot;
    }

    public DungeonConfiguration clone(){
        return new JungleConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][15];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 7, 13, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 14, walls);

        Drawer.fillParallelepipedon1(model, 3, 1, 3, 9, 1, 9, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 4, 1, 4, 7, 1, 7, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 5, 2, 5, 5, 1, 5, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 2, 6, 3, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);

        Drawer.column(model, 5, 1, 2, walls, roof);
        Drawer.column(model, 9, 1, 2, walls, roof);
        Drawer.column(model, 5, 1, 12, walls, roof);
        Drawer.column(model, 9, 1, 12, walls, roof);
        Drawer.column(model, 2, 1, 5, walls, roof);
        Drawer.column(model, 2, 1, 9, walls, roof);
        Drawer.column(model, 12, 1, 5, walls, roof);
        Drawer.column(model, 12, 1, 9, walls, roof);

        Drawer.fillParallelepipedon1(model, 7, 1, 0, 1, 2, 1, content);

        model[7][3][7] = new Chest(getLoot(random), Chest.NORTH);

        model[6][3][7] = Blocks.torch;
        model[8][3][7] = Blocks.torch;

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new JungleMinotaur(world);
    }

    @Override
    public Block[][][] genEntranceHut(Random random) {
        Block[][][] model = new Block[9][9][9];
        Drawer.printModelOnBase(model, super.genEntranceHut(random), 1, 0, 1);

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
                for(int e = 0; e < l; e++) {
                    model[i][5 - e][0] = BestMazesBlocks.vineSouth;
                }
            }
            if(random.nextDouble() < 0.5){
                int l = random.nextInt(3) + 3;
                for(int e = 0; e < l; e++) {
                    model[i][5 - e][8] = BestMazesBlocks.vineNorth;
                }
            }
            if(random.nextDouble() < 0.5){
                int l = random.nextInt(3) + 3;
                for(int e = 0; e < l; e++) {
                    model[0][5 - e][i] = BestMazesBlocks.vineEast;
                }
            }
            if(random.nextDouble() < 0.5){
                int l = random.nextInt(3) + 3;
                for(int e = 0; e < l; e++) {
                    model[8][5 - e][i] = BestMazesBlocks.vineWest;
                }
            }
        }

        return model;
    }
}