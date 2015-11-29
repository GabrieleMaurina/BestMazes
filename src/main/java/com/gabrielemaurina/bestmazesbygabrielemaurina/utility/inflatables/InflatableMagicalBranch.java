package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables;

import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Point3D;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/27/2015.
 */
public class InflatableMagicalBranch extends InflatableRoundedCylinder {

    public InflatableMagicalBranch(int dX, int dY, int dZ, int radius, int internalRadius, Block block){
        super(dX, dY, dZ, radius, internalRadius, block);
    }

    @Override
    protected void inflate(World world, int x, int y, int z, Point3D point){
        if(point.x != 0 || point.y != 0 || point.z != 0) {
            super.inflate(world, x, y, z, point);
        }
    }
}
