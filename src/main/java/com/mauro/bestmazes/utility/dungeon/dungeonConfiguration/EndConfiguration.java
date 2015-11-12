package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

/**
 * Created by Gabriele on 10/29/2015.
 */

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.entities.minotaurs.ClassicMinotaur;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.utility.Drawer;
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

    public EndConfiguration(){
        passageProb = 0.005;
        lavaProb = 0.001;
        waterProb = 0.001;
        spiderNetProb = 0.001;
        mobProb = 0.01;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = BestMazesBlocks.piselliteBricks;
        roof = Blocks.obsidian;
        roof1 = Blocks.end_stone;
        content = Blocks.air;
        name = DungeonReferences.END;
        prob = 0.004;

        xStart = 2;
        yStart = 4;
        zStart = 0;

        xSize = 5;
        ySize = 5;
        zSize = 5;

        xDelta = 2;
        yDelta = 3;
        zDelta = 2;

        x1Delta = 1;
        y1Delta = 1;
        z1Delta = 1;

        xLootRoom = 7;
        yLootRoom = 1;

        xMinotaurSpawn = 6.5;
        yMinotaurSpawn = 7.1;
        zMinotaurSpawn = 9.5;

        biomes = new ArrayList<BiomeGenBase>();

        //biomes.add(BiomeGenBase.forest);
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

        genRoomFrame(world, x, y, z, random);
        genTemple(world, x, y, z, random);
        genStairs(world, x, y, z, random);
        genLights(world, x, y, z, random);
        genEntrance(world, x, y, z, random);
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
        model[3][4][3] = content;
        Drawer.fillParallelepipedon1(model, 2, 5, 2, 3, 1, 3, roof);
        model[3][6][3] = roof;

        model[2][0][3] = Blocks.torch;
        model[4][0][3] = Blocks.torch;
        model[3][0][3] = new Chest(getLoot(random), Chest.NORTH);

        return model;
    }

    public Minotaur getMinotaur(World world){
        return new ClassicMinotaur(world);
    }

    private void genRoomFrame(World world, int x, int y, int z,  Random random){
        Drawer.drawParallelepipedon1(world, x, y, z, 45, 24, 45, Blocks.end_stone, random);
        Drawer.drawParallelepipedon1(world, x + 1, y + 1, z + 1, 43, 22, 43, walls, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 2, z + 2, 41, 20, 41, content, random);
    }

    private void genTemple(World world, int x, int y, int z, Random random){
        Drawer.drawRectangle(world, x + 10, y + 2, z + 10, 25, 25, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 11, y + 2, z + 11, 23, 1, 23, Blocks.stonebrick, random);
        Drawer.drawRectangle(world, x + 12, y + 3, z + 12, 21, 21, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 13, y + 3, z + 13, 19, 1, 19, Blocks.stonebrick, random);

        Drawer.drawRectangle(world, x + 17, y + 4, z + 17, 11, 11, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 18, y + 4, z + 18, 9, 1, 9, Blocks.stonebrick, random);
        Drawer.drawRectangle(world, x + 19, y + 5, z + 19, 7, 7, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 20, y + 5, z + 20, 5, 1, 5, Blocks.stonebrick, random);

        Drawer.drawRectangle(world, x + 10, y + 17, z + 10, 25, 25, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 11, y + 17, z + 11, 23, 1, 23, Blocks.stonebrick, random);
        Drawer.drawRectangle(world, x + 12, y + 16, z + 12, 21, 21, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 13, y + 16, z + 13, 19, 1, 19, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 14, y + 4, z + 20, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 14, y + 4, z + 24, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 30, y + 4, z + 20, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 30, y + 4, z + 24, 1, 12, 1, roof1, random);

        Drawer.fillParallelepipedon1(world, x + 20, y + 4, z + 14, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 24, y + 4, z + 14, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 20, y + 4, z + 30, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 24, y + 4, z + 30, 1, 12, 1, roof1, random);

        Drawer.fillParallelepipedon1(world, x + 16, y + 4, z + 16, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 16, y + 4, z + 28, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 28, y + 4, z + 16, 1, 12, 1, roof1, random);
        Drawer.fillParallelepipedon1(world, x + 28, y + 4, z + 28, 1, 12, 1, roof1, random);


        StructureGenerator.createModel(world, genEntranceHut(random), x + 19, y + 6, z + 19, random);
    }

    private void genStairs(World world, int x, int y, int z, Random random){
        genStairs1(world, x, y, z, random);
        genStairs2(world, x, y, z, random);
    }

    private void genStairs1(World world, int x, int y, int z, Random random){
        Drawer.fillParallelepipedon1(world, x + 2, y + 2, z + 13, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 2, z + 14, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 2, z + 15, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 3, z + 15, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 3, z + 16, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 3, z + 17, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 4, z + 17, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 4, z + 18, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 4, z + 19, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 5, z + 19, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 5, z + 20, 4, 1, 1, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 2, y + 5, z + 21, 4, 1, 22, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 6, y + 5, z + 39, 8, 1, 4, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 13, y + 6, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 14, y + 6, z + 39, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 15, y + 6, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 15, y + 7, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 16, y + 7, z + 39, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 17, y + 7, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 17, y + 8, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 18, y + 8, z + 39, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 19, y + 8, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 19, y + 9, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 20, y + 9, z + 39, 1, 1, 4, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 21, y + 9, z + 39, 22, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 9, z + 31, 4, 1, 8, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 39, y + 10, z + 31, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 10, z + 30, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 10, z + 29, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 11, z + 29, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 11, z + 28, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 11, z + 27, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 12, z + 27, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 12, z + 26, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 12, z + 25, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 13, z + 25, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 13, z + 24, 4, 1, 1, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 39, y + 13, z + 2, 4, 1, 22, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 31, y + 13, z + 2, 8, 1, 4, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 31, y + 14, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 30, y + 14, z + 2, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 29, y + 14, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 29, y + 15, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 28, y + 15, z + 2, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 27, y + 15, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 27, y + 16, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 26, y + 16, z + 2, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 25, y + 16, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 25, y + 17, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 24, y + 17, z + 2, 1, 1, 4, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 21, y + 17, z + 2, 3, 1, 9, Blocks.stonebrick, random);
    }

    private void genStairs2(World world, int x, int y, int z, Random random){
        Drawer.fillParallelepipedon1(world, x + 39, y + 2, z + 31, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 2, z + 30, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 2, z + 29, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 3, z + 29, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 3, z + 28, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 3, z + 27, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 4, z + 27, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 4, z + 26, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 4, z + 25, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 5, z + 25, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 39, y + 5, z + 24, 4, 1, 1, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 39, y + 5, z + 2, 4, 1, 22, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 31, y + 5, z + 2, 8, 1, 4, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 31, y + 6, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 30, y + 6, z + 2, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 29, y + 6, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 29, y + 7, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 28, y + 7, z + 2, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 27, y + 7, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 27, y + 8, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 26, y + 8, z + 2, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 25, y + 8, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 25, y + 9, z + 2, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 24, y + 9, z + 2, 1, 1, 4, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 2, y + 9, z + 2, 22, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 9, z + 6, 4, 1, 8, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 2, y + 10, z + 13, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 10, z + 14, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 10, z + 15, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 11, z + 15, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 11, z + 16, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 11, z + 17, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 12, z + 17, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 12, z + 18, 4, 1, 1, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 12, z + 19, 4, 1, 1, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 13, z + 19, 4, 1, 1, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 2, y + 13, z + 20, 4, 1, 1, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 2, y + 13, z + 21, 4, 1, 22, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 6, y + 13, z + 39, 8, 1, 4, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 13, y + 14, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 14, y + 14, z + 39, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 15, y + 14, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 15, y + 15, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 16, y + 15, z + 39, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 17, y + 15, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 17, y + 16, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 18, y + 16, z + 39, 1, 1, 4, Blocks.stonebrick, random);
        Drawer.fillParallelepipedon1(world, x + 19, y + 16, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabUp, random);
        Drawer.fillParallelepipedon1(world, x + 19, y + 17, z + 39, 1, 1, 4, BestMazesBlocks.stoneBricksSlabDown, random);
        Drawer.fillParallelepipedon1(world, x + 20, y + 17, z + 39, 1, 1, 4, Blocks.stonebrick, random);


        Drawer.fillParallelepipedon1(world, x + 21, y + 17, z + 34, 3, 1, 9, Blocks.stonebrick, random);
    }

    private void genLights(World world, int x, int y, int z, Random random){
        StructureGenerator.setBlock(world, x + 5, y + 6, z + 39, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 5, y + 7, z + 39, Blocks.torch, random);

        StructureGenerator.setBlock(world, x + 39, y + 10, z + 39, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 39, y + 11, z + 39, Blocks.torch, random);

        StructureGenerator.setBlock(world, x + 39, y + 14, z + 5, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 39, y + 15, z + 5, Blocks.torch, random);


        StructureGenerator.setBlock(world, x + 39, y + 6, z + 5, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 39, y + 7, z + 5, Blocks.torch, random);

        StructureGenerator.setBlock(world, x + 5, y + 10, z + 5, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 5, y + 11, z + 5, Blocks.torch, random);

        StructureGenerator.setBlock(world, x + 5, y + 14, z + 39, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 5, y + 15, z + 39, Blocks.torch, random);


        StructureGenerator.setBlock(world, x + 20, y + 18, z + 10, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 20, y + 19, z + 10, Blocks.torch, random);

        StructureGenerator.setBlock(world, x + 24, y + 18, z + 10, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 24, y + 19, z + 10, Blocks.torch, random);

        StructureGenerator.setBlock(world, x + 20, y + 18, z + 34, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 20, y + 19, z + 34, Blocks.torch, random);

        StructureGenerator.setBlock(world, x + 24, y + 18, z + 34, Blocks.stonebrick, random);
        StructureGenerator.setBlock(world, x + 24, y + 19, z + 34, Blocks.torch, random);
    }

    private void genEntrance(World world, int x, int y, int z, Random random){
        Drawer.fillParallelepipedon1(world, x + 19, y + 6, z + 2, 7, 1, 4, walls, random);
        Drawer.fillParallelepipedon1(world, x + 21, y + 7, z + 2, 3, 1, 2, walls, random);
        Drawer.fillParallelepipedon1(world, x + 20, y + 5, z + 2, 5, 1, 3, walls, random);
        Drawer.fillParallelepipedon1(world, x + 20, y + 2, z + 4, 1, 3, 1, walls, random);
        Drawer.fillParallelepipedon1(world, x + 24, y + 2, z + 4, 1, 3, 1, walls, random);
        Drawer.fillParallelepipedon1(world, x + 21, y + 5, z + 2, 3, 1, 2, content, random);

        StructureGenerator.setBlock(world, x + 22, y + 8, z + 2, Blocks.stonebrick, random);

        StructureGenerator.setBlock(world, x + 20, y + 3, z + 5, Blocks.torch, random);
        StructureGenerator.setBlock(world, x + 24, y + 3, z + 5, Blocks.torch, random);
    }
}
