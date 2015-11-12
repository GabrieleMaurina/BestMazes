package com.mauro.bestmazes.utility.dungeon;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import com.mauro.bestmazes.utility.dungeon.dungeonConfiguration.DungeonConfiguration;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import java.util.Random;

/**
 * Created by Gabriele on 6/27/2015.
 */

public class Dungeon {

    private Maze3D m;
    private Block[][][] maze;
    private Block[][][] stairs;
    private Block[][][] lootRoom;
    private Block[][][] corridor;
    private Block[][][] hut;
    private World world;
    private int xDungeon, yDungeon, zDungeon, xMaze, yMaze, zMaze, xStairs, yStairs, zStairs, xLootRoom, yLootRoom, zLootRoom, xCorridor, yCorridor, zCorridor, xHut, yHut, zHut;
    private int xEntrance;
    private Random random;
    private DungeonConfiguration dC;
    private boolean ok = false;

    private static final int Y_DUNGEON = 5;
    private static final int corridorLength = 5;

    private static int nLava = 0, nWater = 0, nPassages = 0, nSpawners = 0, nSpiderNets = 0;

    public static void generateDungeon(World world, int xChunk, int zChunk, Random random){
        int x = xChunk * 16 + random.nextInt(16);
        int z = zChunk * 16 + random.nextInt(16);
        DungeonConfiguration dC = DungeonConfigurations.getConfByBiome(world.getBiomeGenForCoords(x, z));
        if(dC != null && random.nextDouble() < dC.prob && Dungeon.available(world, x, Y_DUNGEON, z)){
            Dungeon d = new Dungeon(world, random, x, Y_DUNGEON, z, dC);
            d.generate();
            System.out.println("@@@###***   MAZE   ***###@@@");
            System.out.println("X: " + x);
            System.out.println("Z: " + z);
            System.out.println("TYPE: " + dC.name);
            System.out.println("SPAWNERS: " + nSpawners);
            System.out.println("PASSAGES: " + nPassages);
            System.out.println("LAVA: " + nLava);
            System.out.println("WATER: " + nWater);
            System.out.println("NETS: " + nSpiderNets);
        }
    }

    public Dungeon(World world, Random r, int x, int y, int z, DungeonConfiguration dC){
        this.dC = dC;

        this.xDungeon = x;
        this.yDungeon = y;
        this.zDungeon = z;

        this.random = r;
        this.world = world;

        this.xEntrance = dC.xStart * 2 + 1;

        m = new Maze3D(dC, r);
        maze = genMaze();

        xStairs = xDungeon - 2;
        yStairs = yDungeon + m.getYCoor(m.ySize - 2) - 1;
        zStairs = zDungeon - 2;

        xMaze = xStairs + 1 - m.getXCoor(xEntrance);
        yMaze = yDungeon;
        zMaze = zDungeon + 2;

        xCorridor = xMaze + m.getXCoor(xEntrance) - 1;
        yCorridor = yDungeon + m.getYCoor(1) - 1;
        zCorridor = zMaze + m.zMSize - m.deltas[m.zSize - 1][2];

        xLootRoom = xMaze + m.getXCoor(xEntrance) - dC.xLootRoom;
        yLootRoom = yDungeon + m.getYCoor(1) - dC.yLootRoom;
        zLootRoom = zCorridor + corridorLength;

        stairs = genStairs();

        if(!ok) return;

        xHut = xDungeon + dC.xHut;
        yHut = yStairs + stairs[0].length - 1;
        zHut = zDungeon + dC.zHut;

        lootRoom = dC.genLootRoom(random);

        corridor = genCorridor();

        hut = dC.genEntranceHut(random);
    }

    public static boolean available(World world, int x, int y, int z){
        for(int i = -2; i < 3; i++){
            for(int e = -1; e < 4; e++){
                if(world.getBlock(x + (i * 10), y, z + (e * 10)) == BestMazesBlocks.piselliteBricks) return false;
            }
        }

        return true;
    }

    public void generate()
    {
        if(ok)
        {
            StructureGenerator.createModel(world, hut, xHut, yHut, zHut, random);
            StructureGenerator.createModel(world, maze, xMaze, yMaze, zMaze, random);
            StructureGenerator.createModel(world, stairs, xStairs, yStairs, zStairs, random);
            StructureGenerator.createModel(world, lootRoom, xLootRoom, yLootRoom, zLootRoom, random);
            StructureGenerator.createModel(world, corridor, xCorridor, yCorridor, zCorridor, random);
            spawnMinotaur();
        }
    }

    private Block[][][] genStairs(){

        int k = 0;
        ok = false;
        int threshold = dC.name.equals(DungeonReferences.HELL) ? 120 : 220;
        for (k = yStairs + 10; k < threshold && ok == false; k++) {
            ok = true;
            for (int i = 0; i < 7; i++) {
                for (int e = 0; e < 7; e++) {
                    if (i == 0 || e == 0 || i == 6 || e == 6) {
                        Block b = world.getBlock(xStairs + i, k, zStairs + e);
                        if(b.getMaterial().isSolid() || b == Blocks.lava || (!dC.name.equals(DungeonReferences.OCEAN) && b == Blocks.water)){
                        //if (b != Blocks.air && b != Blocks.water && b != Blocks.tallgrass) {
                            ok = false;
                        }
                    }
                }
            }
        }

        if(!ok) return null;

        k -= (yStairs - 1);

        Block[][][] model = new Block[5][k][5];

        Drawer.fillParallelepipedon(model, 0, 0, 0, 4, k - 1, 4, dC.walls);
        Drawer.fillParallelepipedon(model, 1, 1, 1, 3, k - 1, 3, dC.content);
        Drawer.fillParallelepipedon(model, 2, 1, 2, 2, k - 1, 2, dC.walls);

        Drawer.fillParallelepipedon1(model, 1, 1, 4, 3, 3, 1, null);

        int e = 0;
        double dir = -Math.PI / 2.0;
        int dx = 0;
        int dz = 1;

        for (int i = 2; i < k * 2; i++) {
            e++;
            e %= 2;
            if (e == 0) {
                dir += Math.PI / 2.0;
            }

            dx += (int) Math.round(Math.cos(dir));
            dz += (int) Math.round(Math.sin(dir));

            if (i % 2 == 0) {
                model[1 + dx][i / 2][1 + dz] = BestMazesBlocks.stoneBricksSlabDown;
            } else {
                model[1 + dx][i / 2][1 + dz] = BestMazesBlocks.stoneBricksSlabUp;
            }

            if (!dC.name.equals(DungeonReferences.OCEAN) && i % 8 == 0 && i < k * 2 - 8) {
                model[2][i / 2 + 1][3] = Blocks.torch;
            }
        }
        return model;
    }

    private Block[][][] genMaze(){
        m.generate();

        m.m[xEntrance][m.ySize - 2][0] = false;

        int[][] deltas = null;

        if(!dC.crazy){
            deltas = m.initDeltas(dC.xDelta, dC.yDelta, dC.zDelta, dC.x1Delta, dC.y1Delta, dC.z1Delta);
        }

        m.getMaze(deltas);
        boolean[][][] b = m.maze;
        Block[][][] model = new Block[b.length][b[0].length][b[0][0].length];

        for(int i = 0; i < b.length; i++)
        {
            for(int e = 0; e < b[i].length; e++)
            {
                for(int o = 0; o < b[i][e].length; o++)
                {
                    if(b[i][e][o]){
                        model[i][e][o] = dC.walls;
                    }
                    else{
                        model[i][e][o] = dC.content;
                    }
                }
            }
        }

        spawners(model);
        passages(model);
        lavaWaterNets(model);

        return model;
    }

    private void lavaWaterNets(Block[][][] model){
        if(dC.name.equals(DungeonReferences.OCEAN)) return;

        for(int i = 1; i < model.length - 1; i++){
            for(int e = 1; e < model[i].length - 1; e++){
                for(int o = 1; o < model[i][e].length - 1; o++){
                    if(model[i][e][o] != dC.content){
                        boolean lava = random.nextDouble() < dC.lavaProb;
                        boolean water = random.nextDouble() < dC.waterProb;
                        if((lava || water) && (model[i + 1][e][o] == dC.content || model[i - 1][e][o] == dC.content || model[i][e][o + 1] == dC.content || model[i][e][o - 1] == dC.content || (model[i][e + 1][o] == dC.content && model[i][e - 1][o] != dC.content) || (model[i][e - 1][o] == dC.content && model[i][e + 1][o] != dC.content))){
                            if(lava){
                                model[i][e][o] = Blocks.lava;
                                nLava++;
                            }
                            else{
                                model[i][e][o] = Blocks.water;
                                nWater++;
                            }
                        }
                    }
                    else{
                        if(random.nextDouble() < dC.spiderNetProb){
                            Drawer.drawSpiderNet(model, i, e, o, random.nextBoolean() ? 3 : 2, dC.content);
                            nSpiderNets++;
                        }
                    }
                }
            }
        }
    }

    private void passages(Block[][][] model){
        for(int i = 0; i < m.m.length; i += 2){
            for(int e = 1; e < m.m[i].length; e += 2){
                for(int o = 1; o < m.m[i][e].length; o += 2){
                    if(m.m[i][e][o] && random.nextDouble() < dC.passageProb){
                        nPassages++;
                        int x = m.getXCoor(i);
                        int y = m.getYCoor(e);
                        int z = m.getZCoor(o) + random.nextInt(m.deltas[o][2]);
                        for(int d = 0; d < m.deltas[i][0]; d++){
                            model[x + d][y][z] = Blocks.stonebrick;
                            model[x + d][y + 1][z] = Blocks.stonebrick;
                        }
                    }
                }
            }
        }
        for(int i = 1; i < m.m.length; i += 2){
            for(int e = 0; e < m.m[i].length; e += 2){
                for(int o = 1; o < m.m[i][e].length; o += 2){
                    if(m.m[i][e][o] && random.nextDouble() < dC.passageProb){
                        nPassages++;
                        int x = m.getXCoor(i) + random.nextInt(m.deltas[i][0]);
                        int y = m.getYCoor(e);
                        int z = m.getZCoor(o) + random.nextInt(m.deltas[o][2]);
                        for(int d = 0; d < m.deltas[e][1]; d++){
                            model[x][y + d][z] = Blocks.stonebrick;
                        }
                    }
                }
            }
        }
        for(int i = 1; i < m.m.length; i += 2){
            for(int e = 1; e < m.m[i].length; e += 2){
                for(int o = 0; o < m.m[i][e].length; o += 2){
                    if(m.m[i][e][o] && random.nextDouble() < dC.passageProb){
                        nPassages++;
                        int x = m.getXCoor(i) + random.nextInt(m.deltas[i][0]);
                        int y = m.getYCoor(e);
                        int z = m.getZCoor(o);
                        for(int d = 0; d < m.deltas[o][2]; d++){
                            model[x][y][z + d] = Blocks.stonebrick;
                            model[x][y + 1][z + d] = Blocks.stonebrick;
                        }
                    }
                }
            }
        }
    }

    private void spawners(Block[][][] model){
        if(dC.name.equals(DungeonReferences.OCEAN)) return;
        int x = 0;
        int y = 0;
        int z = 0;
        for(int i = 0; i < m.xSize; i++){
            y = 0;
            for (int e = 0; e < m.ySize; e++) {
                z = 0;
                for (int o = 0; o < m.zSize; o++) {
                    if (random.nextDouble() < dC.mobProb && x > 0 && y > 0 && z > 0 && x < m.xMSize - 1 && y < m.yMSize - 1 && z < m.zMSize - 1 && posOK(model, x, y, z)) {
                        model[x][y][z] = Blocks.mob_spawner;
                        nSpawners++;
                    }
                    if(o % 2 == 1){
                        z++;
                    }
                    else{
                        z--;
                    }
                    z += m.deltas[o][2];
                }
                if(e % 2 == 1){
                    y++;
                }
                else{
                    y--;
                }
                y += m.deltas[e][1];
            }
            if(i % 2 == 1){
                x++;
            }
            else{
                x--;
            }
            x += m.deltas[i][0];
        }
    }

    private boolean posOK(Block[][][] model, int x, int y, int z){
        if(model[x][y][z] == Blocks.air) return false;
        if(x + 1 >= model.length) return false;
        if(y + 1 >= model[0].length) return false;
        if(z + 1 >= model[0][0].length) return false;
        if(x <= 0) return false;
        if(y <= 0) return false;
        if(z <= 0) return false;
        if(model[x + 1][y][z] == Blocks.air) return false;
        if(model[x][y + 1][z] == Blocks.air) return false;
        if(model[x][y][z + 1] == Blocks.air) return false;
        if(model[x - 1][y][z] == Blocks.air) return false;
        if(model[x][y - 1][z] == Blocks.air) return false;
        if(model[x][y][z - 1] == Blocks.air) return false;
        return true;
    }

    private void spawnMinotaur(){
        Minotaur minotaur = dC.getMinotaur(world);
        minotaur.setPosition(xLootRoom + dC.xMinotaurSpawn, yLootRoom + dC.yMinotaurSpawn, zLootRoom + dC.zMinotaurSpawn);
        world.spawnEntityInWorld(minotaur);
    }

    private Block[][][] genCorridor(){
        Block[][][] model = new Block[3][4][corridorLength];
        Drawer.fillParallelepipedon(model, 0, 0, 0, 2, 3, corridorLength - 1, dC.walls);
        Drawer.fillParallelepipedon(model, 1, 1, 0, 1, 2, corridorLength - 1, dC.content);
        return model;
    }
}
