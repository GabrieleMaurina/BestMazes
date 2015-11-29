package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables;

import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Point3D;
import net.minecraft.block.Block;

/**
 * Created by Gabriele on 11/29/2015.
 */
public class InflatableRoundedCylinder extends InflatableCylinder {
    public InflatableRoundedCylinder(int dX, int dY, int dZ, int radius, int internalRadius, Block block){
        super(dX, dY, dZ, radius, internalRadius, block);
    }

    @Override
    protected boolean elegible(Point3D point) {
        if(distPointPlane(point, FIRST) > maxDist || distPointPlane(point, SECOND) > maxDist){
            int dist1 = (int)Math.round(distPointPoint(0, 0, 0, point.x, point.y, point.z));
            int dist2 = (int)Math.round(distPointPoint(dX, dY, dZ, point.x, point.y, point.z));
            return (dist1 <= radius && dist1 >= internalRadius) || (dist2 <= radius && dist2 >= internalRadius);
        }
        else {
            int dist = (int) Math.round(distPointLine(point));
            return dist <= radius && dist >= internalRadius;
        }
    }
}
