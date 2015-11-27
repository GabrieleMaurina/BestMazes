package com.mauro.bestmazes.utility.inflatables;

import com.mauro.bestmazes.utility.Point3D;
import com.mauro.bestmazes.worldgenerators.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/21/2015.
 */
public class InflatableCylinder extends Inflatable{
    public int dX;
    public int dY;
    public int dZ;
    public int internalRadius;
    public int radius;
    public Block block;

    private double part1;
    private double maxDist;

    private static final boolean FIRST = false;
    private static final boolean SECOND = true;

    public InflatableCylinder(int dX, int dY, int dZ, int radius, int internalRadius, Block block){
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
        this.radius = radius;
        this.internalRadius = internalRadius;
        this.block = block;
        part1  = this.dX * this.dX + this.dY * this.dY + this.dZ * this.dZ;
        maxDist = Math.sqrt(part1);
        init();
    }

    @Override
    protected boolean elegible(Point3D point) {
        if(distPointPlane(point, FIRST) > maxDist || distPointPlane(point, SECOND) > maxDist) return false;
        int dist = (int)Math.round(distPointLine(point));
        return dist <= radius && dist >= internalRadius;
    }

    @Override
    protected void inflate(World world, int x, int y, int z, Point3D point) {
        StructureGenerator.setBlock(world, x + point.x, y + point.y, z + point.z, block);
    }

    @Override
    public Point3D startPoint(){
        double verAng = Math.atan2(dY, Math.sqrt(dX * dX + dZ * dZ));
        double y = radius * Math.cos(verAng);
        double orLen = radius * Math.sin(verAng);
        double orAng = Math.atan2(dZ, dX);
        double x = -orLen * Math.cos(orAng);
        double z = -orLen * Math.sin(orAng);
        return new Point3D((int)x, (int)y, (int)z);
    }

    private double distPointLine(Point3D point){
        double t = (dX * point.x + dY * point.y + dZ * point.z) / part1;

        double xI = t * dX;
        double yI = t * dY;
        double zI = t * dZ;

        return distPointPoint(point.x, point.y, point.z, xI, yI, zI);
    }

    private double distPointPoint(double x1, double y1, double z1, double x2, double y2, double z2){
        x1 -= x2;
        y1 -= y2;
        z1 -= z2;
        return Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
    }

    private double distPointPlane(Point3D point, boolean plane){
        double t = ((plane == FIRST ? 0.0 : part1) - (dX * point.x + dY * point.y + dZ * point.z)) / part1;

        double xI = point.x + t * dX;
        double yI = point.y + t * dY;
        double zI = point.z + t * dZ;

        return distPointPoint(point.x, point.y, point.z, xI, yI, zI);
    }
}