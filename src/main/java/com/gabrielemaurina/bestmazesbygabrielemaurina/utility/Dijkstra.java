package com.gabrielemaurina.bestmazesbygabrielemaurina.utility;

import java.util.ArrayList;

/**
 * Created by Gabriele on 11/19/2015.
 */
public class Dijkstra {

    public static int[][][] getDistances(boolean[][][] m, int x, int y, int z){
        int[][][] distances = new int[m.length][m[0].length][m[0][0].length];

        for(int i = 0; i < distances.length; i++){
            for(int e = 0; e < distances[0].length; e++){
                for(int o = 0; o < distances[0][0].length; o++){
                    distances[i][e][o] = -1;
                }
            }
        }

        ArrayList<Point3D> points = new ArrayList<Point3D>();
        points.add(new Point3D(x, y, z));
        distances[x][y][z] = 0;
        while(points.size() > 0){
            Point3D p = points.get(0);
            points.remove(0);
            int dis = distances[p.x][p.y][p.z] + 1;
            System.out.println("@@@@@@  x: " + p.x + "  y: " + p.y + "  z: " + p.z);
            if (!m[p.x + 1][p.y][p.z] && distances[p.x + 2][p.y][p.z] == -1) addPoint(points, distances, p.x + 2, p.y, p.z, dis);
            if (!m[p.x - 1][p.y][p.z] && distances[p.x - 2][p.y][p.z] == -1) addPoint(points, distances, p.x - 2, p.y, p.z, dis);
            if (!m[p.x][p.y + 1][p.z] && distances[p.x][p.y + 2][p.z] == -1) addPoint(points, distances, p.x, p.y + 2, p.z, dis);
            if (!m[p.x][p.y - 1][p.z] && distances[p.x][p.y - 2][p.z] == -1) addPoint(points, distances, p.x, p.y - 2, p.z, dis);
            if (!m[p.x][p.y][p.z + 1] && distances[p.x][p.y][p.z + 2] == -1) addPoint(points, distances, p.x, p.y, p.z + 2, dis);
            if (!m[p.x][p.y][p.z - 1] && distances[p.x][p.y][p.z - 2] == -1)  addPoint(points, distances, p.x, p.y, p.z - 2, dis);
        }

        return distances;
    }

    private static void addPoint(ArrayList<Point3D> points, int[][][] distances, int x, int y, int z, int dis){
        points.add(new Point3D(x, y, z));
        distances[x][y][z] = dis;

    }

    public static Point3D getfarthestPoint(boolean[][][] m, int x, int y, int z){
        int[][][] distances = getDistances(m, x, y, z);
        int max = 0;
        Point3D maxPoint = new Point3D(0, 0, 0);
        for(int i = 0; i < distances.length; i++){
            for(int e = 0; e < distances[0].length; e++){
                for(int o = 0; o < distances[0][0].length; o++){
                    if(distances[i][e][o] > max){
                        max = distances[i][e][o];
                        maxPoint.x = i;
                        maxPoint.y = e;
                        maxPoint.z = o;
                    }
                }
            }
        }
        return maxPoint;
    }
}
