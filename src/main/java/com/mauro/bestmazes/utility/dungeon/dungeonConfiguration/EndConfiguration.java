package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

/**
 * Created by Gabriele on 10/29/2015.
 */

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.Spawner;
import com.mauro.bestmazes.entities.minotaurs.EndMinotaur;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.Dungeon;
import com.mauro.bestmazes.utility.dungeon.DungeonConfigurations;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
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
 * Created by Gabriele on 10/21/2015.
 */
public class EndConfiguration extends DungeonConfiguration{

    public Block roof1;
    public double finalRoomSpawnerProb;
    private Block b1, b2;

    public EndConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        finalRoomSpawnerProb = 0.5;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = Blocks.obsidian;
        roof1 = Blocks.end_stone;
        content = Blocks.air;
        name = DungeonReferences.END;
        prob = 1.0;

        xStart = 6;
        yStart = 0;
        zStart = 11;

        xSize = 12;
        ySize = 1;
        zSize = 12;

        xDelta = 2;
        yDelta = 3;
        zDelta = 2;

        x1Delta = 1;
        y1Delta = 1;
        z1Delta = 1;

        xLootRoom = 22;
        yLootRoom = 1;

        xMinotaurSpawn = 21.5;
        yMinotaurSpawn = 18.0;
        zMinotaurSpawn = 21.5;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.sky);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack sword = new ItemStack(Items.diamond_sword, 1);
        sword.addEnchantment(Enchantment.sharpness, 10);
        loot.add(sword);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new EndConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        return null;
    }

    public void genFinalRoom(World world, int x, int y, int z,  Random random){
        genRoomFrame(world, x, y, z);
        genTemple(world, x, y, z, random);
        genFinalMaze(world, x, y, z, random);
        genStairs(world, x, y, z);
        genLightsRoom(world, x, y, z);
        genEntrance(world, x, y, z);
        genSecretLoot(world, x, y, z, random);
        addSpawners(world, x, y, z, random);
    }

    private void genFinalMaze(World world, int x, int y, int z, Random random){
        x += 15;
        z += 15;
        FinalConfiguration fC = (FinalConfiguration)DungeonConfigurations.getConfiguration(DungeonReferences.FINAL);

        int xLoot, zLoot;

        if(random.nextBoolean()){
            fC.xStart = 0;
            xLoot = fC.xSize - 1;
        }
        else{
            fC.xStart = fC.xSize - 1;
            xLoot = 0;
        }

        if(random.nextBoolean()){
            fC.zStart = 0;
            zLoot = fC.zSize - 1;
        }
        else{
            fC.zStart = fC.zSize - 1;
            zLoot = 0;
        }

        xLoot = xLoot * 2 + 1;
        zLoot = zLoot * 2 + 1;

        Block[][][] model = Dungeon.genFinalMaze(fC, random);
        Drawer.fillParallelepipedon1(model, 0, 0, 0, model.length, 1, model[0][0].length, null);
        Drawer.fillParallelepipedon1(model, 0, 3, 0, model.length, 1, model[0][0].length, null);
        StructureGenerator.createModel(world, model, x, y, z);

        int dir = Chest.NORTH;
        if(model[xLoot + 1][1][zLoot] == Blocks.air) dir = Chest.EAST;
        else if(model[xLoot - 1][1][zLoot] == Blocks.air) dir = Chest.WEST;
        else if(model[xLoot][1][zLoot + 1] == Blocks.air) dir = Chest.SOUTH;

        StructureGenerator.setBlock(world, x + xLoot, y + 1, z + zLoot, new Chest(fC.getLoot(random), dir));

        StructureGenerator.setBlock(world, x + fC.xStart * 2 + 1, y + 3, z + fC.zStart * 2 + 1, BestMazesBlocks.stoneBricksSlabDown);
    }

    @Override
    public Block[][][] genEntranceHut(Random random){
        Block[][][] model = new Block[7][7][7];

        Drawer.fillParallelepipedon1(model, 1, 0, 1, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 1, 0, 5, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 5, 0, 1, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(model, 5, 0, 5, 1, 3, 1, walls);

        model[1][1][1] = roof;
        model[1][1][5] = roof;
        model[5][1][1] = roof;
        model[5][1][5] = roof;

        Drawer.fillParallelepipedon1(model, 1, 3, 1, 5, 1, 5, walls);
        Drawer.fillParallelepipedon1(model, 2, 3, 2, 3, 1, 3, content);
        Drawer.fillParallelepipedon1(model, 0, 4, 0, 7, 1, 7, roof);
        Drawer.fillParallelepipedon1(model, 2, 5, 2, 3, 1, 3, roof);
        model[3][6][3] = roof;

        model[3][5][3] = new Spawner(Spawner.SKELETON);

        model[2][0][3] = Blocks.torch;
        model[4][0][3] = Blocks.torch;

        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        loot.add(new ItemStack(Items.bone, 1));
        loot.add(new ItemStack(Items.baked_potato, 1));
        loot.add(new ItemStack(Items.flower_pot, 1));
        model[3][0][3] = new Chest(loot, Chest.NORTH);

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new EndMinotaur(world);
    }

    private void genRoomFrame(World world, int x, int y, int z){
        Drawer.drawParallelepipedon1(world, x, y, z, 43, 22, 43, walls);
        Drawer.fillParallelepipedon1(world, x + 1, y + 1, z + 1, 41, 20, 41, content);
        Drawer.fillParallelepipedon1(world, x + 20, y + 1, z, 3, 3, 1, content);
    }

    private void genTemple(World world, int x, int y, int z, Random random){
        Drawer.drawRectangle(world, x + 9, y + 1, z + 9, 25, 25, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 10, y + 1, z + 10, 23, 1, 23, walls);
        Drawer.drawRectangle(world, x + 11, y + 2, z + 11, 21, 21, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 12, y + 2, z + 12, 19, 1, 19, walls);

        Drawer.drawRectangle(world, x + 16, y + 3, z + 16, 11, 11, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 17, y + 3, z + 17, 9, 1, 9, walls);
        Drawer.drawRectangle(world, x + 18, y + 4, z + 18, 7, 7, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 19, y + 4, z + 19, 5, 1, 5, walls);

        Drawer.drawRectangle(world, x + 9, y + 16, z + 9, 25, 25, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 10, y + 16, z + 10, 23, 1, 23, walls);
        Drawer.drawRectangle(world, x + 11, y + 15, z + 11, 21, 21, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 12, y + 15, z + 12, 19, 1, 19, walls);


        Drawer.fillParallelepipedon1(world, x + 13, y + 3, z + 19, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 13, y + 3, z + 23, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 29, y + 3, z + 19, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 29, y + 3, z + 23, 1, 12, 1, roof1);

        Drawer.fillParallelepipedon1(world, x + 19, y + 3, z + 13, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 23, y + 3, z + 13, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 19, y + 3, z + 29, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 23, y + 3, z + 29, 1, 12, 1, roof1);

        Drawer.fillParallelepipedon1(world, x + 15, y + 3, z + 15, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 15, y + 3, z + 27, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 27, y + 3, z + 15, 1, 12, 1, roof1);
        Drawer.fillParallelepipedon1(world, x + 27, y + 3, z + 27, 1, 12, 1, roof1);


        StructureGenerator.createModel(world, genEntranceHut(random), x + 18, y + 5, z + 18);
    }

    private void genStairs(World world, int x, int y, int z){
        genStairs1(world, x, y, z);
        genStairs2(world, x, y, z);
    }

    private void genStairs1(World world, int x, int y, int z){
        Drawer.fillParallelepipedon1(world, x + 1, y + 1, z + 12, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 1, z + 13, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 1, y + 1, z + 14, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 1, y + 2, z + 14, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 2, z + 15, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 1, y + 2, z + 16, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 1, y + 3, z + 16, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 3, z + 17, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 1, y + 3, z + 18, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 1, y + 4, z + 18, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 4, z + 19, 4, 1, 1, walls);


        Drawer.fillParallelepipedon1(world, x + 1, y + 4, z + 20, 4, 1, 22, walls);
        Drawer.fillParallelepipedon1(world, x + 5, y + 4, z + 38, 8, 1, 4, walls);


        Drawer.fillParallelepipedon1(world, x + 12, y + 5, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 13, y + 5, z + 38, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 14, y + 5, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 14, y + 6, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 15, y + 6, z + 38, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 16, y + 6, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 16, y + 7, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 17, y + 7, z + 38, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 18, y + 7, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 18, y + 8, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 19, y + 8, z + 38, 1, 1, 4, walls);


        Drawer.fillParallelepipedon1(world, x + 20, y + 8, z + 38, 22, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 38, y + 8, z + 30, 4, 1, 8, walls);


        Drawer.fillParallelepipedon1(world, x + 38, y + 9, z + 30, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 38, y + 9, z + 29, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 38, y + 9, z + 28, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 38, y + 10, z + 28, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 38, y + 10, z + 27, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 38, y + 10, z + 26, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 38, y + 11, z + 26, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 38, y + 11, z + 25, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 38, y + 11, z + 24, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 38, y + 12, z + 24, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 38, y + 12, z + 23, 4, 1, 1, walls);


        Drawer.fillParallelepipedon1(world, x + 38, y + 12, z + 1, 4, 1, 22, walls);
        Drawer.fillParallelepipedon1(world, x + 30, y + 12, z + 1, 8, 1, 4, walls);


        Drawer.fillParallelepipedon1(world, x + 30, y + 13, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 29, y + 13, z + 1, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 28, y + 13, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 28, y + 14, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 27, y + 14, z + 1, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 26, y + 14, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 26, y + 15, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 25, y + 15, z + 1, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 24, y + 15, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 24, y + 16, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 23, y + 16, z + 1, 1, 1, 4, walls);


        Drawer.fillParallelepipedon1(world, x + 20, y + 16, z + 1, 3, 1, 9, walls);
    }

    private void genStairs2(World world, int x, int y, int z){
        Drawer.fillParallelepipedon1(world, x + 38, y + 1, z + 30, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 38, y + 1, z + 29, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 38, y + 1, z + 28, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 38, y + 2, z + 28, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 38, y + 2, z + 27, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 38, y + 2, z + 26, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 38, y + 3, z + 26, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 38, y + 3, z + 25, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 38, y + 3, z + 24, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 38, y + 4, z + 24, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 38, y + 4, z + 23, 4, 1, 1, walls);


        Drawer.fillParallelepipedon1(world, x + 38, y + 4, z + 1, 4, 1, 22, walls);
        Drawer.fillParallelepipedon1(world, x + 30, y + 4, z + 1, 8, 1, 4, walls);


        Drawer.fillParallelepipedon1(world, x + 30, y + 5, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 29, y + 5, z + 1, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 28, y + 5, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 28, y + 6, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 27, y + 6, z + 1, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 26, y + 6, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 26, y + 7, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 25, y + 7, z + 1, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 24, y + 7, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 24, y + 8, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 23, y + 8, z + 1, 1, 1, 4, walls);


        Drawer.fillParallelepipedon1(world, x + 1, y + 8, z + 1, 22, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 1, y + 8, z + 5, 4, 1, 8, walls);


        Drawer.fillParallelepipedon1(world, x + 1, y + 9, z + 12, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 9, z + 13, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 1, y + 9, z + 14, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 1, y + 10, z + 14, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 10, z + 15, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 1, y + 10, z + 16, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 1, y + 11, z + 16, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 11, z + 17, 4, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 1, y + 11, z + 18, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 1, y + 12, z + 18, 4, 1, 1, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 12, z + 19, 4, 1, 1, walls);


        Drawer.fillParallelepipedon1(world, x + 1, y + 12, z + 20, 4, 1, 22, walls);
        Drawer.fillParallelepipedon1(world, x + 5, y + 12, z + 38, 8, 1, 4, walls);


        Drawer.fillParallelepipedon1(world, x + 12, y + 13, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 13, y + 13, z + 38, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 14, y + 13, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 14, y + 14, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 15, y + 14, z + 38, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 16, y + 14, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 16, y + 15, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 17, y + 15, z + 38, 1, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 18, y + 15, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 18, y + 16, z + 38, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 19, y + 16, z + 38, 1, 1, 4, walls);


        Drawer.fillParallelepipedon1(world, x + 20, y + 16, z + 33, 3, 1, 9, walls);
    }

    private void genLightsRoom(World world, int x, int y, int z){
        StructureGenerator.setBlock(world, x + 4, y + 5, z + 38, walls);
        StructureGenerator.setBlock(world, x + 4, y + 6, z + 38, Blocks.torch);

        StructureGenerator.setBlock(world, x + 38, y + 9, z + 38, walls);
        StructureGenerator.setBlock(world, x + 38, y + 10, z + 38, Blocks.torch);

        StructureGenerator.setBlock(world, x + 38, y + 13, z + 4, walls);
        StructureGenerator.setBlock(world, x + 38, y + 14, z + 4, Blocks.torch);


        StructureGenerator.setBlock(world, x + 38, y + 5, z + 4, walls);
        StructureGenerator.setBlock(world, x + 38, y + 6, z + 4, Blocks.torch);

        StructureGenerator.setBlock(world, x + 4, y + 9, z + 4, walls);
        StructureGenerator.setBlock(world, x + 4, y + 10, z + 4, Blocks.torch);

        StructureGenerator.setBlock(world, x + 4, y + 13, z + 38, walls);
        StructureGenerator.setBlock(world, x + 4, y + 14, z + 38, Blocks.torch);


        StructureGenerator.setBlock(world, x + 19, y + 17, z + 9, walls);
        StructureGenerator.setBlock(world, x + 19, y + 18, z + 9, Blocks.torch);

        StructureGenerator.setBlock(world, x + 23, y + 17, z + 9, walls);
        StructureGenerator.setBlock(world, x + 23, y + 18, z + 9, Blocks.torch);

        StructureGenerator.setBlock(world, x + 19, y + 17, z + 33, walls);
        StructureGenerator.setBlock(world, x + 19, y + 18, z + 33, Blocks.torch);

        StructureGenerator.setBlock(world, x + 23, y + 17, z + 33, walls);
        StructureGenerator.setBlock(world, x + 23, y + 18, z + 33, Blocks.torch);
    }

    private void genEntrance(World world, int x, int y, int z){
        Drawer.fillParallelepipedon1(world, x + 18, y + 5, z + 1, 7, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 20, y + 6, z + 1, 3, 1, 2, walls);
        Drawer.fillParallelepipedon1(world, x + 19, y + 4, z + 1, 5, 1, 3, walls);
        Drawer.fillParallelepipedon1(world, x + 19, y + 1, z + 3, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 23, y + 1, z + 3, 1, 3, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 20, y + 4, z + 1, 3, 1, 2, content);

        StructureGenerator.setBlock(world, x + 21, y + 7, z + 1, walls);

        StructureGenerator.setBlock(world, x + 19, y + 2, z + 4, Blocks.torch);
        StructureGenerator.setBlock(world, x + 23, y + 2, z + 4, Blocks.torch);
    }

    private void genSecretLoot(World world, int x, int y, int z, Random random){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        loot.add(new ItemStack(Blocks.diamond_block, 2));
        loot.add(new ItemStack(Blocks.gold_block, 4));
        loot.add(new ItemStack(Items.golden_apple, 2, 1));
        StructureGenerator.setBlock(world, x + 21, y + 6, z + 1, new Chest(loot, Chest.SOUTH));
        StructureGenerator.setBlock(world, x + 21, y + 7, z + 1, Blocks.stonebrick);
    }

    private void addSpawners(World world, int x, int y, int z, Random random){
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 13, y + 15, z + 19, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 13, y + 15, z + 23, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 29, y + 15, z + 19, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 29, y + 15, z + 23, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 19, y + 15, z + 13, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 23, y + 15, z + 13, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 19, y + 15, z + 29, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 23, y + 15, z + 29, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 15, y + 15, z + 15, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 15, y + 15, z + 27, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 27, y + 15, z + 15, BestMazesBlocks.spiderSpawner);
        }
        if(random.nextDouble() < finalRoomSpawnerProb){
            StructureGenerator.setBlock(world, x + 27, y + 15, z + 27, BestMazesBlocks.spiderSpawner);
        }


        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 41, y + 2, z + 1, walls);
        StructureGenerator.setBlock(world, x + 41, y + 3, z + 2, b1);
        StructureGenerator.setBlock(world, x + 40, y + 3, z + 1, b2);
        StructureGenerator.setBlock(world, x + 41, y + 3, z + 1, BestMazesBlocks.skeletonSpawner);

        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 1, y + 6, z + 1, walls);
        StructureGenerator.setBlock(world, x + 1, y + 7, z + 2, b1);
        StructureGenerator.setBlock(world, x + 2, y + 7, z + 1, b2);
        StructureGenerator.setBlock(world, x + 1, y + 7, z + 1, BestMazesBlocks.skeletonSpawner);

        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 1, y + 10, z + 41, walls);
        StructureGenerator.setBlock(world, x + 1, y + 11, z + 40, b1);
        StructureGenerator.setBlock(world, x + 2, y + 11, z + 41, b2);
        StructureGenerator.setBlock(world, x + 1, y + 11, z + 41, BestMazesBlocks.skeletonSpawner);


        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 1, y + 2, z + 41, walls);
        StructureGenerator.setBlock(world, x + 1, y + 3, z + 40, b1);
        StructureGenerator.setBlock(world, x + 2, y + 3, z + 41, b2);
        StructureGenerator.setBlock(world, x + 1, y + 3, z + 41, BestMazesBlocks.skeletonSpawner);

        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 41, y + 6, z + 41, walls);
        StructureGenerator.setBlock(world, x + 41, y + 7, z + 40, b1);
        StructureGenerator.setBlock(world, x + 40, y + 7, z + 41, b2);
        StructureGenerator.setBlock(world, x + 41, y + 7, z + 41, BestMazesBlocks.skeletonSpawner);

        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 41, y + 10, z + 1, walls);
        StructureGenerator.setBlock(world, x + 41, y + 11, z + 2, b1);
        StructureGenerator.setBlock(world, x + 40, y + 11, z + 1, b2);
        StructureGenerator.setBlock(world, x + 41, y + 11, z + 1, BestMazesBlocks.skeletonSpawner);


        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 1, y + 19, z + 1, walls);
        StructureGenerator.setBlock(world, x + 1, y + 20, z + 2, b1);
        StructureGenerator.setBlock(world, x + 2, y + 20, z + 1, b2);
        StructureGenerator.setBlock(world, x + 1, y + 20, z + 1, BestMazesBlocks.skeletonSpawner);

        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 1, y + 19, z + 41, walls);
        StructureGenerator.setBlock(world, x + 1, y + 20, z + 40, b1);
        StructureGenerator.setBlock(world, x + 2, y + 20, z + 41, b2);
        StructureGenerator.setBlock(world, x + 1, y + 20, z + 41, BestMazesBlocks.skeletonSpawner);

        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 41, y + 19, z + 1, walls);
        StructureGenerator.setBlock(world, x + 41, y + 20, z + 2, b1);
        StructureGenerator.setBlock(world, x + 40, y + 20, z + 1, b2);
        StructureGenerator.setBlock(world, x + 41, y + 20, z + 1, BestMazesBlocks.skeletonSpawner);

        getBlocksSpawner(random);
        StructureGenerator.setBlock(world, x + 41, y + 19, z + 41, walls);
        StructureGenerator.setBlock(world, x + 41, y + 20, z + 40, b1);
        StructureGenerator.setBlock(world, x + 40, y + 20, z + 41, b2);
        StructureGenerator.setBlock(world, x + 41, y + 20, z + 41, BestMazesBlocks.skeletonSpawner);
    }

    private void getBlocksSpawner(Random random){
        if(random.nextBoolean()){
            b1 = walls;
            b2 = Blocks.stonebrick;
        }
        else{
            b1 = Blocks.stonebrick;
            b2 = walls;
        }
    }

    public void genFinalEntrance(World world, int x, int y, int z){
        emptyEntrance(world, x, y, z);

        Drawer.fillParallelepipedon1(world, x + 5, y, z, 10, 12, 1, walls);

        Drawer.fillParallelepipedon1(world, x + 5, y + 11, z + 1, 10, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 5, y + 5, z + 1, 10, 1, 4, walls);
        Drawer.fillParallelepipedon1(world, x + 5, y, z + 1, 10, 1, 7, walls);

        Drawer.fillParallelepipedon1(world, x + 7, y + 11, z + 1, 6, 1, 2, content);

        Drawer.fillParallelepipedon1(world, x + 6, y + 1, z + 3, 1, 10, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 13, y + 1, z + 3, 1, 10, 1, walls);

        Drawer.fillParallelepipedon1(world, x + 8, y + 1, z + 1, 1, 9, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 11, y + 1, z + 1, 1, 9, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 9, y + 9, z + 1, 2, 1, 1, walls);


        Drawer.fillParallelepipedon1(world, x + 4, y + 5, z + 2, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 3, y + 4, z + 2, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 2, y + 4, z + 2, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 1, y + 3, z + 2, 1, 1, 5, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 0, y + 3, z + 2, 1, 1, 5, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 2, y + 3, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 3, y + 2, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 4, y + 2, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 5, y + 1, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 6, y + 1, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(world, x + 15, y + 5, z + 2, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 16, y + 4, z + 2, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 17, y + 4, z + 2, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 18, y + 3, z + 2, 1, 1, 5, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 19, y + 3, z + 2, 1, 1, 5, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 17, y + 3, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 16, y + 2, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 15, y + 2, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 14, y + 1, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 13, y + 1, z + 5, 1, 1, 2, BestMazesBlocks.piselliteBricksSlabDown);


        Drawer.fillParallelepipedon1(world, x + 6, y + 12, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 7, y + 12, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 8, y + 13, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 9, y + 13, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);

        Drawer.fillParallelepipedon1(world, x + 13, y + 12, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 12, y + 12, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(world, x + 11, y + 13, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(world, x + 10, y + 13, z + 1, 1, 1, 4, BestMazesBlocks.piselliteBricksSlabUp);

        StructureGenerator.setBlock(world, x + 7, y + 12, z + 4, walls);
        StructureGenerator.setBlock(world, x + 9, y + 13, z + 4, walls);

        StructureGenerator.setBlock(world, x + 12, y + 12, z + 4, walls);
        StructureGenerator.setBlock(world, x + 10, y + 13, z + 4, walls);

        StructureGenerator.setBlock(world, x + 8, y + 12, z + 4, BestMazesBlocks.piselliteBricksSlabUp);
        StructureGenerator.setBlock(world, x + 11, y + 12, z + 4, BestMazesBlocks.piselliteBricksSlabUp);


        Drawer.fillParallelepipedon1(world, x + 6, y + 12, z, 8, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 7, y + 13, z, 6, 1, 1, walls);
        Drawer.fillParallelepipedon1(world, x + 9, y + 14, z, 2, 1, 1, walls);

        genLocksEntrance(world, x, y, z);
        genLightsEntrance(world, x, y, z);
    }

    private void emptyEntrance(World world, int x, int y, int z) {
        Drawer.fillParallelepipedon1(world, x + 4, y + 6, z + 1, 12, 7, 6, content);
        Drawer.fillParallelepipedon1(world, x + 7, y + 1, z + 1, 6, 5, 6, content);

        Drawer.fillParallelepipedon1(world, x + 6, y + 13, z + 1, 8, 1, 6, content);
        Drawer.fillParallelepipedon1(world, x + 8, y + 14, z + 1, 4, 1, 6, content);


        Drawer.fillParallelepipedon1(world, x + 2, y + 5, z + 2, 2, 7, 5, content);
        Drawer.fillParallelepipedon1(world, x + 0, y + 4, z + 2, 2, 7, 5, content);
        Drawer.fillParallelepipedon1(world, x + 2, y + 4, z + 5, 1, 1, 2, content);
        Drawer.fillParallelepipedon1(world, x + 3, y + 3, z + 5, 2, 3, 2, content);
        Drawer.fillParallelepipedon1(world, x + 5, y + 2, z + 5, 2, 4, 2, content);

        Drawer.fillParallelepipedon1(world, x + 16, y + 5, z + 2, 2, 7, 5, content);
        Drawer.fillParallelepipedon1(world, x + 18, y + 4, z + 2, 2, 7, 5, content);
        Drawer.fillParallelepipedon1(world, x + 17, y + 4, z + 5, 1, 1, 2, content);
        Drawer.fillParallelepipedon1(world, x + 15, y + 3, z + 5, 2, 3, 2, content);
        Drawer.fillParallelepipedon1(world, x + 13, y + 2, z + 5, 2, 4, 2, content);


        Drawer.fillParallelepipedon1(world, x + 6, y + 1, z + 1, 1, 4, 4, content);
        Drawer.fillParallelepipedon1(world, x + 5, y + 2, z + 1, 1, 3, 4, content);

        Drawer.fillParallelepipedon1(world, x + 13, y + 1, z + 1, 1, 4, 4, content);
        Drawer.fillParallelepipedon1(world, x + 14, y + 2, z + 1, 1, 3, 4, content);


        Drawer.fillParallelepipedon1(world, x + 2, y + 5, z + 1, 2, 6, 1, content);
        Drawer.fillParallelepipedon1(world, x + 16, y + 5, z + 1, 2, 6, 1, content);


        Drawer.fillParallelepipedon1(world, x + 2, y + 4, z + 7, 2, 7, 1, content);
        Drawer.fillParallelepipedon1(world, x + 4, y + 3, z + 7, 2, 9, 1, content);
        Drawer.fillParallelepipedon1(world, x + 6, y + 2, z + 7, 2, 11, 1, content);
        Drawer.fillParallelepipedon1(world, x + 8, y + 1, z + 7, 2, 13, 1, content);

        Drawer.fillParallelepipedon1(world, x + 16, y + 4, z + 7, 2, 7, 1, content);
        Drawer.fillParallelepipedon1(world, x + 14, y + 3, z + 7, 2, 9, 1, content);
        Drawer.fillParallelepipedon1(world, x + 12, y + 2, z + 7, 2, 11, 1, content);
        Drawer.fillParallelepipedon1(world, x + 10, y + 1, z + 7, 2, 13, 1, content);


        Drawer.fillParallelepipedon1(world, x + 4, y + 4, z + 8, 2, 7, 1, content);
        Drawer.fillParallelepipedon1(world, x + 6, y + 3, z + 8, 2, 9, 1, content);
        Drawer.fillParallelepipedon1(world, x + 8, y + 2, z + 8, 2, 11, 1, content);

        Drawer.fillParallelepipedon1(world, x + 14, y + 4, z + 8, 2, 7, 1, content);
        Drawer.fillParallelepipedon1(world, x + 12, y + 3, z + 8, 2, 9, 1, content);
        Drawer.fillParallelepipedon1(world, x + 10, y + 2, z + 8, 2, 11, 1, content);


        Drawer.fillParallelepipedon1(world, x + 6, y + 4, z + 9, 2, 7, 1, content);
        Drawer.fillParallelepipedon1(world, x + 8, y + 3, z + 9, 2, 9, 1, content);

        Drawer.fillParallelepipedon1(world, x + 12, y + 4, z + 9, 2, 7, 1, content);
        Drawer.fillParallelepipedon1(world, x + 10, y + 3, z + 9, 2, 9, 1, content);


        Drawer.fillParallelepipedon1(world, x + 8, y + 4, z + 10, 4, 7, 1, content);
    }

    private void genLightsEntrance(World world, int x, int y, int z){
        StructureGenerator.setBlock(world, x + 8, y + 3, z + 2, Blocks.torch);
        StructureGenerator.setBlock(world, x + 8, y + 8, z + 2, Blocks.torch);
        StructureGenerator.setBlock(world, x + 11, y + 3, z + 2, Blocks.torch);
        StructureGenerator.setBlock(world, x + 11, y + 8, z + 2, Blocks.torch);
    }

    private void genLocksEntrance(World world, int x, int y, int z) {
        StructureGenerator.setBlock(world, x + 8, y + 2, z + 1, BestMazesBlocks.mazeLock);
        StructureGenerator.setBlock(world, x + 8, y + 7, z + 1, BestMazesBlocks.mazeLock);
        StructureGenerator.setBlock(world, x + 11, y + 2, z + 1, BestMazesBlocks.mazeLock);
        StructureGenerator.setBlock(world, x + 11, y + 7, z + 1, BestMazesBlocks.mazeLock);
    }

    public Block[][][] genFinalConnection() {
        Block[][][] model = new Block[12][9][9];

        Drawer.fillParallelepipedon1(model, 0, 4, 0, 5, 5, 9, walls);
        Drawer.fillParallelepipedon1(model, 1, 5, 1, 3, 3, 8, content);
        Drawer.fillParallelepipedon1(model, 4, 5, 1, 1, 3, 3, content);

        Drawer.fillParallelepipedon1(model, 2, 0, 4, 3, 5, 5, walls);
        Drawer.fillParallelepipedon1(model, 3, 1, 5, 2, 3, 4, content);

        Drawer.fillParallelepipedon1(model, 8, 2, 0, 4, 5, 9, walls);
        Drawer.fillParallelepipedon1(model, 8, 3, 1, 3, 3, 7, content);


        Drawer.fillParallelepipedon1(model, 5, 1, 5, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 1, 5, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 7, 2, 5, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 7, 3, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 3, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 5, 4, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);


        Drawer.fillParallelepipedon1(model, 5, 5, 5, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 5, 5, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 7, 6, 5, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);

        Drawer.fillParallelepipedon1(model, 7, 7, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 7, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 5, 8, 1, 1, 1, 3, BestMazesBlocks.piselliteBricksSlabDown);


        Drawer.fillParallelepipedon1(model, 5, 1, 8, 2, 5, 1, walls);
        Drawer.fillParallelepipedon1(model, 7, 2, 8, 1, 5, 1, walls);

        Drawer.fillParallelepipedon1(model, 5, 1, 4, 1, 8, 1, walls);
        Drawer.fillParallelepipedon1(model, 6, 1, 4, 1, 7, 1, walls);
        Drawer.fillParallelepipedon1(model, 7, 2, 4, 1, 6, 1, walls);

        Drawer.fillParallelepipedon1(model, 6, 3, 0, 2, 5, 1, walls);
        Drawer.fillParallelepipedon1(model, 5, 4, 0, 1, 5, 1, walls);


        Drawer.fillParallelepipedon1(model, 5, 2, 5, 2, 3, 3, content);
        Drawer.fillParallelepipedon1(model, 7, 3, 5, 1, 3, 3, content);

        Drawer.fillParallelepipedon1(model, 6, 4, 1, 2, 3, 3, content);
        Drawer.fillParallelepipedon1(model, 5, 5, 1, 1, 3, 3, content);


        return model;
    }
}
