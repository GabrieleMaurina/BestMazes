package com.mauro.bestmazes.utility.inflatables;

import com.google.common.collect.Sets;
import com.mauro.bestmazes.utility.Point3D;

import java.util.Set;

/**
 * Created by Gabriele on 11/21/2015.
 */
public abstract class Inflatable {
    public abstract boolean elegible(Point3D point);
    public abstract void inflate(Point3D point);
    public abstract Point3D startPoint();

    public static void inflateShape(Inflatable inflatable){
        Set<Point3D> points = Sets.newHashSet();
        Set<Point3D> toBeChecked = Sets.newHashSet();
        Set<Point3D> checked = Sets.newHashSet();

        inflatable.inflate(inflatable.startPoint());

        addPoint(points, inflatable.startPoint(), toBeChecked, checked);

        while(toBeChecked.size() > 0){
            Point3D p = toBeChecked.iterator().next();
            toBeChecked.remove(p);
            if(inflatable.elegible(p)){
                inflatable.inflate(p);
                addPoint(points, p, toBeChecked, checked);
            }
            else{
                checked.add(p);
            }
        }
    }

    private static void addPoint(Set<Point3D> points, Point3D point, Set<Point3D> toBeChecked, Set<Point3D> checked){
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
}
