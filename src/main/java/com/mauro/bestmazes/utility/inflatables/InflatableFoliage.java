package com.mauro.bestmazes.utility.inflatables;

import com.mauro.bestmazes.utility.Point3D;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Gabriele on 11/26/2015.
 */
public class InflatableFoliage extends InflatableSphere {
    private Random random;
    private double prob;

    public InflatableFoliage(int radius, Block block, double prob, Random random){
        super(radius, 0, block);
        this.random = random;
        this.prob = prob;
    }

    @Override
    protected void inflate(World world, int x, int y, int z, Point3D point) {
        if(random.nextDouble() < prob){
            super.inflate(world, x, y, z, point);
        }
    }
}
