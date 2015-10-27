package com.mauro.bestmazes.utility.dungeon;

import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.SpecialBlocks;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.common.StructureGenerator;
import com.mauro.bestmazes.utility.dungeon.dungeonConfiguration.DungeonConfiguration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Gabriele on 6/27/2015.
 */

public class Dungeon {

    private Maze3D m;
    private Block[][][] maze;
    private Block[][][] stairs;
    private Block[][][] lootRoom;
    private World world;
    private int xDungeon, yDungeon, zDungeon, xMaze, yMaze, zMaze, xStairs, yStairs, zStairs, xLootRoom, yLootRoom, zLootRoom;
    private int xEntrance;
    private Random random;
    private DungeonConfiguration dC;

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

        xStairs = xDungeon - 3;
        yStairs = yDungeon + m.getYCoor(m.ySize - 2);;
        zStairs = zDungeon - 3;

        xMaze = xStairs - m.getXCoor(xEntrance) + 1;
        yMaze = yDungeon;
        zMaze = zDungeon + 2;

        xLootRoom = xMaze + m.getXCoor(xEntrance) - dC.xLootRoom + 1;
        yLootRoom = yDungeon + m.getYCoor(1) - dC.yLootRoom + 1;
        zLootRoom = zMaze + m.zMSize - 1;

        stairs = genStairs();

        lootRoom = dC.genLootRoom(random);
    }

    public static boolean available(World world, int x, int y, int z, DungeonConfiguration dC){
        if(!dC.isBiome(world.getBiomeGenForCoords(x + 20, z + 20))) return false;
        if(!dC.isBiome(world.getBiomeGenForCoords(x - 20, z + 20))) return false;
        if(!dC.isBiome(world.getBiomeGenForCoords(x + 20, z - 20))) return false;
        if(!dC.isBiome(world.getBiomeGenForCoords(x - 20, z - 20))) return false;

        for(int i = -10; i < 11; i++){
            for(int e = -10; e < 11; e++){
                if(world.getBlock(x + (i * 10), y, z + (e * 10)) == PiselliteBricks.piselliteBricks) return false;
            }
        }

        return true;
    }

    public void generate()
    {
        if(!dC.name.equals("hell") || stairs[0].length < 120)
        {
            StructureGenerator.createModel(world, maze, xMaze, yMaze, zMaze, random);
            StructureGenerator.createModel(world, stairs, xStairs, yStairs, zStairs, random);
            StructureGenerator.createModel(world, lootRoom, xLootRoom, yLootRoom, zLootRoom, random);
        }
    }

    private Block[][][] genStairs(){

        int k = 0;
        boolean ok = false;
        for (k = 30; k < 120 && ok == false; k++) {
            ok = true;
            for (int i = 0; i < 7; i++) {
                for (int e = 0; e < 7; e++) {
                    if (i == 0 || e == 0 || i == 6 || e == 6) {
                        Block b = world.getBlock(xStairs + i, k, zStairs + e);
                        if(b.getMaterial().isSolid() || b == Blocks.lava){
                        //if (b != Blocks.air && b != Blocks.water && b != Blocks.tallgrass) {
                            ok = false;
                        }
                    }
                }
            }
        }

        k -= yStairs;

        Block[][][] model = new Block[7][k + 9][7];

        Drawer.fillParallelepipedon(model, 1, 0, 1, 5, k, 5, dC.walls);
        Drawer.fillParallelepipedon(model, 0, k, 0, 6, k, 6, dC.walls);
        Drawer.fillParallelepipedon(model, 2, 1, 2, 4, k, 4, dC.content);
        Drawer.fillParallelepipedon(model, 3, 1, 3, 3, k + 1, 3, dC.walls);

        Drawer.fillParallelepipedon1(model, 2, 1, 5, 3, 3, 1, null);

        Drawer.fillParallelepipedon(model, 1, k + 1, 1, 1, k + 3, 1, dC.walls);
        Drawer.fillParallelepipedon(model, 1, k + 1, 5, 1, k + 3, 5, dC.walls);
        Drawer.fillParallelepipedon(model, 5, k + 1, 1, 5, k + 3, 1, dC.walls);
        Drawer.fillParallelepipedon(model, 5, k + 1, 5, 5, k + 3, 5, dC.walls);

        model[1][k + 2][1] = dC.roof;
        model[1][k + 2][5] = dC.roof;
        model[5][k + 2][1] = dC.roof;
        model[5][k + 2][5] = dC.roof;

        Drawer.fillParallelepipedon(model, 1, k + 4, 1, 5, k + 4, 5, dC.walls);
        Drawer.fillParallelepipedon(model, 2, k + 4, 2, 4, k + 4, 4, dC.content);
        Drawer.fillParallelepipedon(model, 0, k + 5, 0, 6, k + 5, 6, dC.roof);
        model[3][k + 5][3] = dC.content;
        Drawer.fillParallelepipedon(model, 2, k + 6, 2, 4, k + 6, 4, dC.roof);

        model[3][k + 7][3] = dC.roof;
        if(!dC.name.equals("ocean")) model[3][k + 2][3] = Blocks.torch;

        int e = 0;
        double dir = -Math.PI / 2.0;
        int dx = 0;
        int dz = 1;

        for (int i = 2; i < (k + 1) * 2; i++) {
            e++;
            e %= 2;
            if (e == 0) {
                dir += Math.PI / 2.0;
            }

            dx += (int) Math.round(Math.cos(dir));
            dz += (int) Math.round(Math.sin(dir));

            if (i % 2 == 0) {
                model[2 + dx][i / 2][2 + dz] = SpecialBlocks.stoneBricksSlabDown;
            } else {
                model[2 + dx][i / 2][2 + dz] = SpecialBlocks.stoneBricksSlabUp;
            }

            if (!dC.name.equals("ocean") && i % 8 == 0 && i < k * 2) {
                model[3][i / 2 + 1][4] = Blocks.torch;
            }
        }
        return model;
    }

    private Block[][][] genMaze(){
        m.generate();

        m.m[xEntrance][m.ySize - 2][0] = false;
        m.m[xEntrance][1][m.zSize - 1] = false;

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
        return model;
    }

    private void spawners(Block[][][] model){
        int x = 0;
        int y = 0;
        int z = 0;
        for(int i = 0; i < m.xSize; i++){
            y = 0;
            if(i % 2 == 0){
                for (int e = 0; e < m.ySize; e++){
                    z = 0;
                    if(e % 2 == 0){
                        for (int o = 0; o < m.zSize; o++){
                            if(o % 2 == 0 && random.nextDouble() < dC.mobProb && posOK(model, x, y, z)){
                                model[x][y][z] = Blocks.mob_spawner;
                            }
                            z += m.deltas[o][2];
                        }
                    }
                    y += m.deltas[e][1];
                }
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
}
