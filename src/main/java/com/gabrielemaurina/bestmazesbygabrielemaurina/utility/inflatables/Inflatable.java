package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables;

import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Point3D;
import com.google.common.collect.Sets;
import net.minecraft.world.World;

import java.util.Set;

/**
 * Created by Gabriele on 11/21/2015.
 */
public abstract class Inflatable {

    protected abstract boolean elegible(Point3D point);
    protected abstract Point3D startPoint();
    protected abstract void inflate(World world, int x, int y, int z, Point3D point);

    public Set<Point3D> points = Sets.newHashSet();
    private Set<Point3D> toBeChecked = Sets.newHashSet();
    private Set<Point3D> checked = Sets.newHashSet();

    protected void init(){
        addPoint(startPoint());
        while(toBeChecked.size() > 0){
            Point3D p = toBeChecked.iterator().next();
            toBeChecked.remove(p);
            if(elegible(p)){
                addPoint(p);
            }
            else{
                checked.add(p);
            }
        }
    }

    private void addPoint(Point3D point){
        points.add(point);
        for(int i = -1; i < 2; i++){
            for(int e = -1; e < 2; e++){
                for(int o = -1; o < 2; o++){
                    Point3D p = new Point3D(point.x + i, point.y + e, point.z + o);
                    if(!points.contains(p) && !toBeChecked.contains(p) && !checked.contains(p)){
                        toBeChecked.add(p);
                    }
                }
            }
        }
    }

    public void inflateShape(World world, int x, int y, int z){
        for(Point3D p : points){
            inflate(world, x, y, z, p);
        }
    }
}
