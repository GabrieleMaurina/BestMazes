package com.mauro.bestmazes.utility.inflatables;

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

    private double part1;
    public double maxDist;

    private static final boolean FIRST = false;
    private static final boolean SECOND = true;

    public InflatableCylider(World world, int x, int y, int z, int x1, int y1, int z1, int radius, int internalRadius, Block block, boolean fill){
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.x1 = x1 - x;
        this.y1 = y1 - y;
        this.z1 = z1 - z;
        this.radius = radius;
        this.internalRadius = internalRadius;
        this.block = block;
        this.fill = fill;
        part1  = this.x1 * this.x1 + this.y1 * this.y1 + this.z1 * this.z1;
        maxDist = Math.sqrt(part1);
    }

    @Override
    public boolean elegible(Point3D point) {
        if(distPointPlane(point, FIRST) > maxDist || distPointPlane(point, SECOND) > maxDist) return false;
        int dist = (int)Math.round(distPointLine(point));
        if(fill) return dist <= radius;
        else return dist <= radius && dist >= internalRadius;
    }

    @Override
    public void inflate(Point3D point) {
        StructureGenerator.setBlock(world, x + point.x, y + point.y, z + point.z, block);
    }

    @Override
    public Point3D startPoint(){
        double verAng = Math.atan2(y1, Math.sqrt(x1 * x1 + z1 * z1));
        double dY = radius * Math.cos(verAng);
        double orLen = radius * Math.sin(verAng);
        double orAng = Math.atan2(z1, x1);
        double dX = orLen * Math.cos(orAng);
        double dZ = orLen * Math.sin(orAng);
        return new Point3D((int)dX, (int)dY, (int)dZ);
    }

    public double distPointLine(Point3D point){
        double t = (x1 * point.x + y1 * point.y + z1 * point.z) / part1;

        double xI = t * x1;
        double yI = t * y1;
        double zI = t * z1;

        return distPointPoint(point.x, point.y, point.z, xI, yI, zI);
    }

    private double distPointPoint(double x, double y, double z, double x1, double y1, double z1){
        x1 -= x;
        y1 -= y;
        z1 -= z;
        return Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
    }

    private double distPointPlane(Point3D point, boolean plane){
        double t = ((plane == FIRST ? 0.0 : part1) - (x1 * point.x + y1 * point.y + z1 * point.z)) / part1;

        double xI = point.x + t * x1;
        double yI = point.y + t * y1;
        double zI = point.z + t * z1;

        return distPointPoint(point.x, point.y, point.z, xI, yI, zI);
    }
}