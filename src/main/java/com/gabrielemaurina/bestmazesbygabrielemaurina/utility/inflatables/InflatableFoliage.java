package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables;

import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Point3D;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Gabriele on 11/26/2015.
 */
public class InflatableFoliage extends InflatableSphere{
    private Random random;
    private double prob;
    private InflatableSphere iS;

    public InflatableFoliage(int radius, int dProb, Block block, double prob, Random random){
        super(radius, radius - dProb + 1, block);
        iS = new InflatableSphere(radius - dProb, 0, block);
        this.random = random;
        this.prob = prob;
    }

    @Override
    protected void inflate(World world, int x, int y, int z, Point3D point) {
        if(random.nextDouble() < prob){
            super.inflate(world, x, y, z, point);
        }
    }

    @Override
    public void inflateShape(World world, int x, int y, int z){
        iS.inflateShape(world, x, y, z);
        super.inflateShape(world, x, y, z);
    }
}
