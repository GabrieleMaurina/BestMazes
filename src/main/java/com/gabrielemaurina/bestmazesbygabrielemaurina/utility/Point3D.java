package com.gabrielemaurina.bestmazesbygabrielemaurina.utility;

/**
 * Created by Gabriele on 11/19/2015.
 */

public class Point3D
{
    public int x = 0;
    public int y = 0;
    public int z = 0;

    public Point3D(int x, int y, int z)
    {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int hashCode() {
        return (x + "#" + y + "#" + z).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Point3D)) {
            return false;
        }
        Point3D p = (Point3D)other;
        return x == p.x && y == p.y && z == p.z;
    }

    @Override
    public String toString(){
        return "X: " + x + "  Y: " + y + "   Z: " + z;
    }
}