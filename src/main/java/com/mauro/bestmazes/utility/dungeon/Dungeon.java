package com.mauro.bestmazes.utility.dungeon;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.entities.minotaurs.ClassicMinotaur;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import com.mauro.bestmazes.utility.dungeon.dungeonConfiguration.DungeonConfiguration;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

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

    private static final int Y_DUNGEON = 5;

    public static void generateDungeon(World world, int x, int z, Random random){
        DungeonConfiguration dC = DungeonConfigurations.getConfByBiome(world.getBiomeGenForCoords(x, z));
        if(dC != null && random.nextDouble() < dC.prob && Dungeon.available(world, x, Y_DUNGEON, z, dC)){
            Dungeon d = new Dungeon(world, random, x, Y_DUNGEON, z, dC);
            d.generate();
            Minotaur minotaur = new ClassicMinotaur(world);
            minotaur.setPosition(d.xLootRoom + 5.0, Y_DUNGEON + 2.0, d.zLootRoom + 5.0);
            world.spawnEntityInWorld(minotaur);
            System.out.println("########  " + x + " " + z + "  " + dC.name + "  ########");
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

        xStairs = xDungeon - 3;
        yStairs = yDungeon + m.getYCoor(m.ySize - 2) - 1;
        zStairs = zDungeon - 3;

        xMaze = xStairs + 2 - m.getXCoor(xEntrance);
        yMaze = yDungeon;
        zMaze = zDungeon + 2;

        xLootRoom = xMaze + m.getXCoor(xEntrance) - dC.xLootRoom;
        yLootRoom = yDungeon + m.getYCoor(1) - dC.yLootRoom;
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
                if(world.getBlock(x + (i * 10), y, z + (e * 10)) == BestMazesBlocks.piselliteBricks) return false;
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
                model[2 + dx][i / 2][2 + dz] = BestMazesBlocks.stoneBricksSlabDown;
            } else {
                model[2 + dx][i / 2][2 + dz] = BestMazesBlocks.stoneBricksSlabUp;
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
        passages(model);

        return model;
    }

    private void passages(Block[][][] model){
        for(int i = 0; i < m.m.length; i += 2){
            for(int e = 1; e < m.m[i].length; e += 2){
                for(int o = 1; o < m.m[i][e].length; o += 2){
                    if(random.nextDouble() < dC.passageProb && m.m[i][e][o]){
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
                    if(random.nextDouble() < dC.passageProb && m.m[i][e][o]){
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
                    if(random.nextDouble() < dC.passageProb && m.m[i][e][o]){
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
}
