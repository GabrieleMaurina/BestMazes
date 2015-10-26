package com.mauro.bestmazes.utility;

import net.minecraft.block.Block;

/**
 * Created by Gabriele on 7/1/2015.
 */
public class Drawer {

    public static void drawParallelepipedon(Block[][][] model, int x, int y, int z, int x1, int y1, int z1, Block b) {
        fillParallelepipedon(model, x, y, z, x1, y, z1, b);
        fillParallelepipedon(model, x, y1, z, x1, y1, z1, b);
        fillParallelepipedon(model, x, y + 1, z, x, y1 - 1, z1, b);
        fillParallelepipedon(model, x1, y + 1, z, x1, y1 - 1, z1, b);
        fillParallelepipedon(model, x + 1, y + 1, z, x1 - 1, y1 - 1, z, b);
        fillParallelepipedon(model, x + 1, y + 1, z1, x1 - 1, y1 - 1, z1, b);
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

    public static void fillParallelepipedon1(Block[][][] model, int x, int y, int z, int x1, int y1, int z1, Block b){
        if(x1 < 0){
            x = x + x1 + 1;
            x1 = x1 * (-1);
        }
        if(y1 < 0){
            y = y + y1 + 1;
            y1 = y1 * (-1);
        }
        if(z1 < 0){
            z = z + z1 + 1;
            z1 = z1 * (-1);
        }

        for(int i = x; i < x + x1; i++)
        {
            for(int e = y; e < y + y1; e++)
            {
                for(int o = z; o < z + z1; o++)
                {
                    model[i][e][o] = b;
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
}
