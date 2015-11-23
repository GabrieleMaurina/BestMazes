package com.mauro.bestmazes.utility.inflatables;

import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.Point3D;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/21/2015.
 */
public class InflatableCylider extends Inflatable{
    public World world;
    public int x;
    public int y;
    public int z;
    public int x1;
    public int y1;
    public int z1;
    public int internalRadius;
    public int radius;
    public Block block;
    public boolean fill;

    public int[] v;

    public InflatableCylider(World world, int x, int y, int z, int x1, int y1, int z1, int radius, int internalRadius, Block block, boolean fill){
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.radius = radius;
        this.internalRadius = internalRadius;
        this.block = block;
        this.fill = fill;
        v = new int[]{x1 - x, y1 - y, z1 - z};
    }

    @Override
    public boolean elegible(Point3D point) {
        if(fill) return dist(point) <= radius;
        else{
            int dist = dist(point);
            return dist <= radius && dist >= internalRadius;
        }
    }

    @Override
    public void inflate(Point3D point) {
        StructureGenerator.setBlock(world, x + point.x, y + point.y, z + point.z, block);
    }

    @Override
    public Point3D startPoint(){
        return new Point3D(x, y, z);
    }

    private int dist(Point3D point){


        return 0;
    }
}