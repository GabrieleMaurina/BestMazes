package com.mauro.bestmazes.utility.inflatables;

import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.Point3D;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/21/2015.
 */
public class InflatableSphere extends Inflatable{

    public World world;
    public int x;
    public int y;
    public int z;
    public int radius;
    public int internalRadius;
    public Block block;
    public boolean fill;

    public InflatableSphere(World world, int x, int y, int z, int radius, int internalRadius, Block block, boolean fill){
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.internalRadius = internalRadius;
        this.block = block;
        this.fill = fill;
    }

    @Override
    public boolean elegible(Point3D point) {
        int dist = (int)Math.round(Math.sqrt(point.x * point.x + point.y * point.y + point.z * point.z));
        if(fill){
            return dist == radius;
        }
        else{
            return  dist <= radius && dist >= internalRadius;
        }
    }

    @Override
    public void inflate(Point3D point) {
        if(fill){
            int k = y < point.y ? 1 : 0;
            Drawer.line(world, x + point.x, y + point.y, z + point.z, x + point.x, y + k, z + point.z, block);
        }
        else{
            StructureGenerator.setBlock(world, x + point.x, y + point.y, z + point.z, block);
        }
    }

    @Override
    public Point3D startPoint(){
        return new Point3D(0, radius, 0);
    }
}
