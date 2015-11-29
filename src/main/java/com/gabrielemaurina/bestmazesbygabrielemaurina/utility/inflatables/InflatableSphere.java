package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables;

import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Point3D;
import com.gabrielemaurina.bestmazesbygabrielemaurina.worldgenerators.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/21/2015.
 */
public class InflatableSphere extends Inflatable{

    public int radius;
    public int internalRadius;
    public Block block;

    public InflatableSphere(int radius, int internalRadius, Block block){
        this.radius = radius;
        this.internalRadius = internalRadius;
        this.block = block;
        init();
    }

    @Override
    protected boolean elegible(Point3D point) {
        int dist = (int)Math.round(Math.sqrt(point.x * point.x + point.y * point.y + point.z * point.z));
        return  dist <= radius && dist >= internalRadius;
    }

    @Override
    protected void inflate(World world, int x, int y, int z, Point3D point) {
        StructureGenerator.setBlock(world, x + point.x, y + point.y, z + point.z, block);
    }

    @Override
    protected Point3D startPoint(){
        return new Point3D(0, radius, 0);
    }
}
