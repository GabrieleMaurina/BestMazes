package com.mauro.bestmazes.utility;

import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Gabriele on 7/1/2015.
 */
public class Drawer {

    public static void drawRectangle(World world, int x, int y, int z, int dx, int dz, Block b){
        fillParallelepipedon1(world, x, y, z, dx, 1, 1, b);
        fillParallelepipedon1(world, x, y, z + dz - 1, dx, 1, 1, b);
        fillParallelepipedon1(world, x, y, z + 1, 1, 1, dz - 2, b);
        fillParallelepipedon1(world, x + dx - 1, y, z + 1, 1, 1, dz - 2, b);
    }

    public static void drawParallelepipedon(Block[][][] model, int x, int y, int z, int x1, int y1, int z1, Block b) {
        if(x1 < x){
            int tmp = x1;
            x1 = x;
            x = tmp;
        }
        if(y1 < y){
            int tmp = y1;
            y1 = y;
            y = tmp;
        }
        if(z1 < z){
            int tmp = z1;
            z1 = z;
            z = tmp;
        }
        fillParallelepipedon(model, x, y, z, x1, y, z1, b);
        fillParallelepipedon(model, x, y1, z, x1, y1, z1, b);
        fillParallelepipedon(model, x, y + 1, z, x, y1 - 1, z1, b);
        fillParallelepipedon(model, x1, y + 1, z, x1, y1 - 1, z1, b);
        fillParallelepipedon(model, x + 1, y + 1, z, x1 - 1, y1 - 1, z, b);
        fillParallelepipedon(model, x + 1, y + 1, z1, x1 - 1, y1 - 1, z1, b);
    }

    public static void drawParallelepipedon(World world, int x, int y, int z, int x1, int y1, int z1, Block b) {
        if(x1 < x){
            int tmp = x1;
            x1 = x;
            x = tmp;
        }
        if(y1 < y){
            int tmp = y1;
            y1 = y;
            y = tmp;
        }
        if(z1 < z){
            int tmp = z1;
            z1 = z;
            z = tmp;
        }
        fillParallelepipedon(world, x, y, z, x1, y, z1, b);
        fillParallelepipedon(world, x, y1, z, x1, y1, z1, b);
        fillParallelepipedon(world, x, y + 1, z, x, y1 - 1, z1, b);
        fillParallelepipedon(world, x1, y + 1, z, x1, y1 - 1, z1, b);
        fillParallelepipedon(world, x + 1, y + 1, z, x1 - 1, y1 - 1, z, b);
        fillParallelepipedon(world, x + 1, y + 1, z1, x1 - 1, y1 - 1, z1, b);
    }

    public static void drawParallelepipedon1(Block[][][] model, int x, int y, int z, int dx, int dy, int dz, Block b) {
        int x1 = x + dx - 1;
        int y1 = y + dy - 1;
        int z1 = z + dz - 1;

        if(x1 < x){
            int tmp = x1;
            x1 = x;
            x = tmp;
        }
        if(y1 < y){
            int tmp = y1;
            y1 = y;
            y = tmp;
        }
        if(z1 < z){
            int tmp = z1;
            z1 = z;
            z = tmp;
        }

        fillParallelepipedon(model, x, y, z, x1, y, z1, b);
        fillParallelepipedon(model, x, y1, z, x1, y1, z1, b);
        fillParallelepipedon(model, x, y + 1, z, x, y1 - 1, z1, b);
        fillParallelepipedon(model, x1, y + 1, z, x1, y1 - 1, z1, b);
        fillParallelepipedon(model, x + 1, y + 1, z, x1 - 1, y1 - 1, z, b);
        fillParallelepipedon(model, x + 1, y + 1, z1, x1 - 1, y1 - 1, z1, b);
    }

    public static void drawParallelepipedon1(World world, int x, int y, int z, int dx, int dy, int dz, Block b) {
        int x1 = x + dx - 1;
        int y1 = y + dy - 1;
        int z1 = z + dz - 1;

        if(x1 < x){
            int tmp = x1;
            x1 = x;
            x = tmp;
        }
        if(y1 < y){
            int tmp = y1;
            y1 = y;
            y = tmp;
        }
        if(z1 < z){
            int tmp = z1;
            z1 = z;
            z = tmp;
        }

        fillParallelepipedon(world, x, y, z, x1, y, z1, b);
        fillParallelepipedon(world, x, y1, z, x1, y1, z1, b);
        fillParallelepipedon(world, x, y + 1, z, x, y1 - 1, z1, b);
        fillParallelepipedon(world, x1, y + 1, z, x1, y1 - 1, z1, b);
        fillParallelepipedon(world, x + 1, y + 1, z, x1 - 1, y1 - 1, z, b);
        fillParallelepipedon(world, x + 1, y + 1, z1, x1 - 1, y1 - 1, z1, b);
    }

    public static void fillParallelepipedon(Block[][][] model, int x, int y, int z, int x1, int y1, int z1, Block b){
        if(x1 < x){
            int tmp = x1;
            x1 = x;
            x = tmp;
        }
        if(y1 < y){
            int tmp = y1;
            y1 = y;
            y = tmp;
        }
        if(z1 < z){
            int tmp = z1;
            z1 = z;
            z = tmp;
        }

        for(int i = x; i < x1 + 1; i++)
        {
            for(int e = y; e < y1 + 1; e++)
            {
                for(int o = z; o < z1 + 1; o++)
                {
                    model[i][e][o] = b;
                }
            }
        }
    }

    public static void fillParallelepipedon(World world, int x, int y, int z, int x1, int y1, int z1, Block b){
        if(x1 < x){
            int tmp = x1;
            x1 = x;
            x = tmp;
        }
        if(y1 < y){
            int tmp = y1;
            y1 = y;
            y = tmp;
        }
        if(z1 < z){
            int tmp = z1;
            z1 = z;
            z = tmp;
        }

        for(int i = x; i < x1 + 1; i++)
        {
            for(int e = y; e < y1 + 1; e++)
            {
                for(int o = z; o < z1 + 1; o++)
                {
                    StructureGenerator.setBlock(world, i, e, o, b);
                }
            }
        }
    }

    public static void fillParallelepipedon1(Block[][][] model, int x, int y, int z, int dx, int dy, int dz, Block b){
        if(dx < 0){
            x = x + dx + 1;
            dx = dx * (-1);
        }
        if(dy < 0){
            y = y + dy + 1;
            dy = dy * (-1);
        }
        if(dz < 0){
            z = z + dz + 1;
            dz = dz * (-1);
        }

        for(int i = x; i < x + dx; i++)
        {
            for(int e = y; e < y + dy; e++)
            {
                for(int o = z; o < z + dz; o++)
                {
                    model[i][e][o] = b;
                }
            }
        }
    }

    public static void fillParallelepipedon1(World world, int x, int y, int z, int dx, int dy, int dz, Block b){
        if(dx < 0){
            x = x + dx + 1;
            dx = dx * (-1);
        }
        if(dy < 0){
            y = y + dy + 1;
            dy = dy * (-1);
        }
        if(dz < 0){
            z = z + dz + 1;
            dz = dz * (-1);
        }

        for(int i = x; i < x + dx; i++)
        {
            for(int e = y; e < y + dy; e++)
            {
                for(int o = z; o < z + dz; o++)
                {
                    StructureGenerator.setBlock(world, i, e, o, b);
                }
            }
        }
    }

    public static void line(Block[][][] model, int x1, int y1, int z1, int x2, int y2, int z2, Block b){
        //model[x1][y1][z1] = b;
        if(x1 > x2){
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if(y1 > y2){
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        if(z1 > z2){
            int tmp = z1;
            z1 = z2;
            z2 = tmp;
        }

        int dx = x2 - x1;
        int dy = y2 - y1;
        int dz = z2 - z1;

        if(dx > dy && dx > dz){
            double dyx = dy / dx;
            double dzx = dz / dx;
            for(int x = x1; x < x2 + 1; x++){
                int y = y1 + (int)((x - x1) * dyx);
                int z = z1 + (int)((x - x1) * dzx);
                model[x][y][z] = b;
            }
        }
        else if(dy > dx && dy > dz){
            double dxy = dx / dy;
            double dzy = dz / dy;
            for(int y = y1; y < y2 + 1; y++){
                int x = x1 + (int)((y - y1) * dxy);
                int z = z1 + (int)((y - y1) * dzy);
                model[x][y][z] = b;
            }
        }
        else if(dz > dx && dz > dy){
            double dxz = dx / dz;
            double dyz = dy / dz;
            for(int z = z1; z < z2 + 1; z++){
                int x = x1 + (int)((z - z1) * dxz);
                int y = y1 + (int)((z - z1) * dyz);
                model[x][y][z] = b;
            }
        }
    }

    public static void column(Block[][][] model, int x, int y, int z, Block b1, Block b2){
        fillParallelepipedon1(model, x, y, z, 1, 4, 1, b1);
        model[x][y + 4][z] = b2;
        fillParallelepipedon1(model, x, y + 5, z, 1, 2, 1, b1);
    }

    public static void drawSpiderNet(Block[][][] model, int x, int y, int z, int size, Block b){
        if(size == 1 &&  x > 0 && y > 0 && z > 0 && x < model.length && y < model[x].length && z < model[x][y].length && model[x][y][z] == b){
            model[x][y][z] = Blocks.web;
        }
        else if(size == 2) {
            drawSpiderNet(model, x, y, z, 1, b);

            drawSpiderNet(model, x + 1, y, z, 1, b);
            drawSpiderNet(model, x - 1, y, z, 1, b);
            drawSpiderNet(model, x, y + 1, z, 1, b);
            drawSpiderNet(model, x, y - 1, z, 1, b);
            drawSpiderNet(model, x, y, z + 1, 1, b);
            drawSpiderNet(model, x, y, z - 1, 1, b);
        }
        else if(size > 2){

            drawSpiderNet(model, x + 1, y, z + 1, 1, b);
            drawSpiderNet(model, x - 1, y, z + 1, 1, b);
            drawSpiderNet(model, x, y + 1, z + 1, 1, b);
            drawSpiderNet(model, x, y - 1, z + 1, 1, b);

            drawSpiderNet(model, x + 1, y, z - 1, 1, b);
            drawSpiderNet(model, x - 1, y, z - 1, 1, b);
            drawSpiderNet(model, x, y + 1, z - 1, 1, b);
            drawSpiderNet(model, x, y - 1, z - 1, 1, b);

            drawSpiderNet(model, x + 2, y, z, 1, b);
            drawSpiderNet(model, x - 2, y, z, 1, b);
            drawSpiderNet(model, x, y + 2, z, 1, b);
            drawSpiderNet(model, x, y - 2, z, 1, b);
            drawSpiderNet(model, x, y, z + 2, 1, b);
            drawSpiderNet(model, x, y, z - 2, 1, b);

            drawSpiderNet(model, x, y, z, 2, b);
        }
    }

    public static void printModelOnBase(Block[][][] base, Block[][][] model, int x, int y, int z){
        for(int i = 0; i < model.length; i++){
            for(int e = 0; e < model[i].length; e++){
                for(int o = 0; o < model[i][e].length; o++){
                    base[x + i][y + e][z + o] = model[i][e][o];
                }
            }
        }
    }
}
