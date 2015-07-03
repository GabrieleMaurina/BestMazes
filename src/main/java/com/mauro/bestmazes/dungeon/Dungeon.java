package com.mauro.bestmazes.dungeon;

import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.SpecialBlocks;
import com.mauro.bestmazes.common.Drawer;
import com.mauro.bestmazes.common.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Gabriele on 6/27/2015.
 */

public class Dungeon {

    private static final double MOB_PROB = 0.02;

    private Maze3D m;
    private Block[][][] maze;
    private Block[][][] stairs;
    private World world;
    private int x, y, z, x1, y1, z1, xDelta, yDelta, zDelta, x1Delta, y1Delta, z1Delta;
    private Random random;
    private Block block;
    private Block block1;
    private boolean crazy;

    public Dungeon(World world, Random r, int x, int y, int z, DungeonConfiguration dC){
        this.crazy = dC.crazy;

        this.x = x;
        this.y = y;
        this.z = z;

        this.xDelta = dC.xDelta;
        this.yDelta = dC.yDelta;
        this.zDelta = dC.zDelta;

        this.x1Delta = dC.x1Delta;
        this.y1Delta = dC.y1Delta;
        this.z1Delta = dC.z1Delta;

        this.random = r;
        this.world = world;
        this.block = dC.block;
        this.block1 = dC.block1;

        dC.world = world;
        dC.random = random;

        m = new Maze3D(dC);
        maze = genMaze();

        x1 = x + m.deltas[0][0] - 2;
        y1 = y + m.yMSize - m.deltas[m.ySize - 1][1] - m.deltas[m.ySize - 2][1];
        z1 = z + m.deltas[0][2] - 2;

        stairs = genStairs();
    }

    public boolean available(){
        boolean toRtn = true;
        if(world.getBlock(x, y, z) == PiselliteBricks.piselliteBricks ||
                world.getBlock(x + maze.length, y, z) == PiselliteBricks.piselliteBricks ||
                world.getBlock(x, y, z + maze[0][0].length) == PiselliteBricks.piselliteBricks ||
                world.getBlock(x + maze.length, y, z + maze[0][0].length) == PiselliteBricks.piselliteBricks){
            toRtn = false;
        }
        return toRtn;
    }

    public void generate(){
        StructureGenerator.createModel(world, maze, x, y, z, random);
        StructureGenerator.createModel(world, stairs, x1, y1, z1, random);
    }

    private Block[][][] genStairs(){
        int k = 0;
        for(int i = 0; i < 7; i++){
            for(int e = 0; e < 7; e++){
                for(int o = 255; o > k; o--){
                    if(world.getBlock(x + i, o, z + e) != Blocks.air)
                    {
                        k = o;
                    }
                }
            }
        }
        k -= 5;

        int k1 = 0;
        for(int i = -1; i < 8; i++){
            for(int e = -1; e < 8; e++){
                if(i == -1 || e == -1 || i == 7 || e == 7)
                for(int o = 255; o > k1; o--){
                    if(world.getBlock(x + i, o, z + e) != Blocks.air)
                    {
                        k1 = o;
                    }
                }
            }
        }
        k1 += 2;

        if(k1 > k){
            k = k1;
        }

        k -= y1;

        Block[][][] model = new Block[7][k + 8][7];

        Drawer.fillParallelepipedon(model, 0, k - 1, 0, 6, k + 6, 6, Blocks.air);

        Drawer.fillParallelepipedon(model, 1, 0, 1, 5, k, 5, block);
        Drawer.fillParallelepipedon(model, 0, k, 0, 6, k, 6, block);
        Drawer.fillParallelepipedon(model, 2, 0, 2, 4, k, 4, Blocks.air);
        Drawer.fillParallelepipedon(model, 3, 0, 3, 3, k + 1, 3, block);

        Drawer.fillParallelepipedon(model, 3, 0, 5, 3, 1, 5, null);
        Drawer.fillParallelepipedon(model, 5, 0, 3, 5, 1, 3, null);

        Drawer.fillParallelepipedon(model, 1, k + 1, 1, 1, k + 3, 1, block);
        Drawer.fillParallelepipedon(model, 1, k + 1, 5, 1, k + 3, 5, block);
        Drawer.fillParallelepipedon(model, 5, k + 1, 1, 5, k + 3, 1, block);
        Drawer.fillParallelepipedon(model, 5, k + 1, 5, 5, k + 3, 5, block);

        model[1][k + 2][1] = block1;
        model[1][k + 2][5] = block1;
        model[5][k + 2][1] = block1;
        model[5][k + 2][5] = block1;

        Drawer.fillParallelepipedon(model, 1, k + 4, 1, 5, k + 4, 5, block);
        Drawer.fillParallelepipedon(model, 2, k + 4, 2, 4, k + 4, 4, Blocks.air);
        Drawer.fillParallelepipedon(model, 0, k + 5, 0, 6, k + 5, 6, block1);
        model[3][k+5][3] = Blocks.air;
        Drawer.fillParallelepipedon(model, 2, k + 6, 2, 4, k + 6, 4, block1);

        model[3][k + 7][3] = block1;
        model[3][k + 2][3] = Blocks.torch;

        int e = 0;
        double dir = - Math.PI;
        int dx = 1;
        int dy = 2;

        for(int i = 0; i < (k + 1) * 2; i++)
        {
            e++;
            e %= 2;
            if(e == 0){
                dir += Math.PI / 2.0;
            }

            dx += (int)Math.round(Math.cos(dir));
            dy += (int)Math.round(Math.sin(dir));

            if(i % 2 == 0){
                model[2 + dx][i / 2][2 + dy] = SpecialBlocks.stoneBricksSlabDown;
            }
            else{
                model[2 + dx][i / 2][2 + dy] = SpecialBlocks.stoneBricksSlabUp;
            }

            if(i % 8 == 0 && i < k * 2){
                model[3][i / 2 + 1][4] = Blocks.torch;
            }
        }

        return model;
    }

    private Block[][][] genMaze(){
        //room
        m.open(m.xVSize - 2, 0, m.zVSize - 3, m.xVSize - 1, 1, m.zVSize - 1);

        //generate genMaze
        m.generate();

        //open room
        m.m[m.xSize - 5][1][m.zSize - 6] = false;

        //genMaze entrance
        m.m[1][m.ySize - 1][1] = false;

        int[][] deltas = null;

        if(!crazy){
            deltas = m.initDeltas(xDelta, yDelta, zDelta, x1Delta, y1Delta, z1Delta);
            deltas[1][0] = 3;
            deltas[1][2] = 3;
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
                        model[i][e][o] = block;
                    }
                    else{
                        model[i][e][o] = Blocks.air;
                    }
                }
            }
        }

        int x = b.length - m.deltas[m.xSize - 1][0] - m.deltas[m.xSize - 2][0] - 1;
        int y = m.deltas[0][1];
        int z =  b[0][0].length - m.deltas[m.zSize - 1][2] - m.deltas[m.zSize - 2][2];

        altar(model, block, x, y, z);
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
                            if(o % 2 == 0 && random.nextDouble() < MOB_PROB && posOK(model, x, y, z)){
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
        if(x + 1 < model.length && model[x + 1][y][z] == Blocks.air) return false;
        if(y + 1 < model[0].length && model[x][y + 1][z] == Blocks.air) return false;
        if(z + 1 < model[0][0].length && model[x][y][z + 1] == Blocks.air) return false;
        if(x - 1 > 0 && model[x - 1][y][z] == Blocks.air) return false;
        if(y - 1 > 0 && model[x][y - 1][z] == Blocks.air) return false;
        if(z - 1 > 0 && model[x][y][z - 1] == Blocks.air) return false;
        return true;
    }

    private void altar(Block[][][] model, Block block, int x, int y, int z){

        Drawer.fillParallelepipedon1(model, x - 1, y, z - 1, 3, 1, 3, block);

        model[x][y + 1][z] = Blocks.chest;
        model[x + 1][y + 1][z] = Blocks.torch;
        model[x - 1][y + 1][z] = Blocks.torch;
    }
}
