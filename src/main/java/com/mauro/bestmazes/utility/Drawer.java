package com.mauro.bestmazes.utility;

import com.google.common.collect.Sets;
import com.mauro.bestmazes.utility.inflatables.Inflatable;
import com.mauro.bestmazes.utility.inflatables.InflatableCylider;
import com.mauro.bestmazes.utility.inflatables.InflatableSphere;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Set;

/**
 * Created by Gabriele on 7/1/2015.
 */
public class Drawer {

    public static final int XY_PLANE = 0;
    public static final int YZ_PLANE = 1;
    public static final int ZX_PLANE = 2;

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

    private static boolean condition(int n1, int n2, int dir){
        if(dir > 0) return n1 <= n2;
        else return n1 >= n2;
    }

    public static void line(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block b){
        double dX = x2 - x1;
        double dY = y2 - y1;
        double dZ = z2 - z1;

        int xDir = dX > 0 ? 1 : -1;
        int yDir = dY > 0 ? 1 : -1;
        int zDir = dZ > 0 ? 1 : -1;

        if(Math.abs(dX) >= Math.abs(dY) && Math.abs(dX) >= Math.abs(dZ)){
            double dyx = dY / dX;
            double dzx = dZ / dX;
            for(int x = x1; condition(x, x2, xDir); x += xDir){
                int y = y1 + (int)((x - x1) * dyx);
                int z = z1 + (int)((x - x1) * dzx);
                StructureGenerator.setBlock(world, x, y, z, b);
            }
        }
        else if(Math.abs(dY) >= Math.abs(dX) && Math.abs(dY) >= Math.abs(dZ)){
            double dxy = dX / dY;
            double dzy = dZ / dY;
            for(int y = y1; condition(y, y2, yDir); y += yDir){
                int x = x1 + (int)((y - y1) * dxy);
                int z = z1 + (int)((y - y1) * dzy);
                StructureGenerator.setBlock(world, x, y, z, b);
            }
        }
        else if(Math.abs(dZ) >= Math.abs(dX) && Math.abs(dZ) >= Math.abs(dY)){
            double dxz = dX / dZ;
            double dyz = dY / dZ;
            for(int z = z1; condition(z, z2, zDir); z += zDir){
                int x = x1 + (int)((z - z1) * dxz);
                int y = y1 + (int)((z - z1) * dyz);
                StructureGenerator.setBlock(world, x, y, z, b);
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

    public static void drawCircle(World world, int x, int y, int z, int radius, int plane, Block b)
    {
        int d1 = radius;
        int d2 = 0;
        int d = 1 - d1;

        do {
            if(plane == XY_PLANE){
                StructureGenerator.setBlock(world, x + d1, y + d2, z, b);
                StructureGenerator.setBlock(world, x + d1, y - d2, z, b);
                StructureGenerator.setBlock(world, x - d1, y + d2, z, b);
                StructureGenerator.setBlock(world, x - d1, y - d2, z, b);
                StructureGenerator.setBlock(world, x + d2, y + d1, z, b);
                StructureGenerator.setBlock(world, x + d2, y - d1, z, b);
                StructureGenerator.setBlock(world, x - d2, y + d1, z, b);
                StructureGenerator.setBlock(world, x - d2, y - d1, z, b);
            }
            else if(plane == YZ_PLANE){
                StructureGenerator.setBlock(world, x, y + d1, z + d2, b);
                StructureGenerator.setBlock(world, x, y + d1, z - d2, b);
                StructureGenerator.setBlock(world, x, y - d1, z + d2, b);
                StructureGenerator.setBlock(world, x, y - d1, z - d2, b);
                StructureGenerator.setBlock(world, x, y + d2, z + d1, b);
                StructureGenerator.setBlock(world, x, y + d2, z - d1, b);
                StructureGenerator.setBlock(world, x, y - d2, z + d1, b);
                StructureGenerator.setBlock(world, x, y - d2, z - d1, b);
            }
            else if(plane == ZX_PLANE){
                StructureGenerator.setBlock(world, x + d2, y, z + d1, b);
                StructureGenerator.setBlock(world, x - d2, y, z + d1, b);
                StructureGenerator.setBlock(world, x + d2, y, z - d1, b);
                StructureGenerator.setBlock(world, x - d2, y, z - d1, b);
                StructureGenerator.setBlock(world, x + d1, y, z + d2, b);
                StructureGenerator.setBlock(world, x - d1, y, z + d2, b);
                StructureGenerator.setBlock(world, x + d1, y, z - d2, b);
                StructureGenerator.setBlock(world, x - d1, y, z - d2, b);
            }

            d2++;
            if (d <= 0) {
                d += 2 * d2 + 1;
            }
            else{
                d1--;
                d += 2 * (d2 - d1) + 1;
            }
        } while (d2 <= d1);
    }

    public static void fillCircle(World world, int x, int y, int z, int radius, int plane, Block b)
    {
        int d1 = radius;
        int d2 = 0;
        int d = 1 - d1;

        do {
            if(plane == XY_PLANE){
                line(world, x + d1, y + d2, z, x + d1, y - d2, z, b);
                line(world, x - d1, y + d2, z, x - d1, y - d2, z, b);
                line(world, x + d2, y + d1, z, x + d2, y - d1, z, b);
                line(world, x - d2, y + d1, z, x - d2, y - d1, z, b);
            }
            else if(plane == YZ_PLANE){
                line(world, x, y + d1, z + d2, x, y + d1, z - d2, b);
                line(world, x, y - d1, z + d2, x, y - d1, z - d2, b);
                line(world, x, y + d2, z + d1, x, y + d2, z - d1, b);
                line(world, x, y - d2, z + d1, x, y - d2, z - d1, b);
            }
            else if(plane == ZX_PLANE){
                line(world, x + d2, y, z + d1, x - d2, y, z + d1, b);
                line(world, x + d2, y, z - d1, x - d2, y, z - d1, b);
                line(world, x + d1, y, z + d2, x - d1, y, z + d2, b);
                line(world, x + d1, y, z - d2, x - d1, y, z - d2, b);
            }

            d2++;
            if (d <= 0) {
                d += 2 * d2 + 1;
            }
            else{
                d1--;
                d += 2 * (d2 - d1) + 1;
            }
        } while (d2 <= d1);
    }

    public static void drawSphere(World world, int x, int y, int z, int radius, int internalRadius, Block b) {
        Inflatable.inflateShape(new InflatableSphere(world, x, y, z, radius, internalRadius, b, false));
    }

    public static void fillSphere(World world, int x, int y, int z, int radius, Block b) {
        Inflatable.inflateShape(new InflatableSphere(world, x, y, z, radius, 0, b, true));
    }

    public static void drawCylinder(World world, int x, int y, int z, int x1, int y1, int z1, int radius, int internalRadius, Block b) {
        Inflatable.inflateShape(new InflatableCylider(world, x, y, z, x1, y1, z1, radius, internalRadius, b, false));
    }

    public static void fillCylinder(World world, int x, int y, int z, int x1, int y1, int z1, int radius, Block b) {
        Inflatable.inflateShape(new InflatableCylider(world, x, y, z, x1, y1, z1, radius, 0, b, true));
}
}
