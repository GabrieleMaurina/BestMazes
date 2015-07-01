package com.mauro.bestmazes.dungeon;

/**
 * Created by Gabriele on 6/25/2015.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Maze3D
{
    public static final double PROB = 0.005;
    public static final int X_SIZE = 10;
    public static final int Y_SIZE = 5;
    public static final int Z_SIZE = 10;
    public static final int X_DELTA = 2;
    public static final int Y_DELTA = 3;
    public static final int Z_DELTA = 2;
    public static final int X1_DELTA = 1;
    public static final int Y1_DELTA = 1;
    public static final int Z1_DELTA = 1;
    public static final int X = 0;
    public static final int Y = 4;
    public static final int Z = 0;
    private static final double K_PROB = 0.7;

    public boolean[][][] m;
    private ArrayList<Point3D> w = new ArrayList<Point3D>();
    private Random r;
    public int[][] deltas;

    public int xSize;
    public int ySize;
    public int zSize;
    public int xVSize;
    public int yVSize;
    public int zVSize;
    public int xMSize;
    public int yMSize;
    public int zMSize;

    private class Point3D
    {
        public int x, y, z;

        public Point3D(int x, int y, int z)
        {
            super();
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public Maze3D(int xMax, int yMax, int zMax, int x, int y, int z, Random r)
    {
        this.r = r;

        xVSize = xMax;
        yVSize = yMax;
        zVSize = zMax;

        xSize = xMax * 2 + 1;
        ySize = yMax * 2 + 1;
        zSize = zMax * 2 + 1;

        m = new boolean[xSize][ySize][zSize];

        for(int i = 0; i < xSize; i++)
        {
            for(int e = 0; e < ySize; e++)
            {
                for(int o = 0; o < zSize; o++)
                {
                    m[i][e][o] = true;
                }
            }
        }

        addCell(x * 2 + 1, y * 2 + 1, z * 2 + 1);
        removeCell(x * 2 + 1, y * 2, z * 2 + 1);
    }

    private void removeCell(int x, int y, int z){
        for(int i = 0; i < w.size(); i++)
        {
            if(w.get(i).x == x && w.get(i).y == y && w.get(i).z == z)
            {
                w.remove(i);
                return;
            }
        }
    }

    public int[][] initDeltas(int xDelta, int yDelta, int zDelta, int x1Delta, int y1Delta, int z1Delta)
    {
        int[][] deltas = new int[Math.max(xSize, Math.max(ySize, zSize))][3];

        for(int i = 0; i < deltas.length; i++)
        {
            if(i % 2 == 0)
            {
                deltas[i][0] = x1Delta;
                deltas[i][1] = y1Delta;
                deltas[i][2] = z1Delta;
            }
            else
            {
                deltas[i][0] = xDelta;
                deltas[i][1] = yDelta;
                deltas[i][2] = zDelta;
            }
        }

        return deltas;
    }

    private boolean getBlockCrazyMaze(int x, int y, int z, int[][] deltas)
    {
        int x1 = 0;
        int y1 = 0;
        int z1 = 0;

        int i = 0;

        i = -1;
        while(x > -1)
        {
            i++;
            x -= deltas[i][0];
        }
        x1 = i;

        i = -1;
        while(y > -1)
        {
            i++;
            y -= deltas[i][1];
        }
        y1 = i;

        i = -1;
        while(z > -1)
        {
            i++;
            z -= deltas[i][2];
        }
        z1 = i;

        return m[x1][y1][z1];
    }

    private int[][] initDeltasCrazy()
    {
        int[][] deltas = new int[Math.max(xSize, Math.max(ySize, zSize))][3];
        for(int i = 0; i < deltas.length; i++)
        {
            if(i % 2 == 0)
            {
                deltas[i][0] = r.nextInt(3) + 3;
                deltas[i][1] = r.nextInt(2) + 2;
                deltas[i][2] = r.nextInt(3) + 3;
            }
            else
            {
                deltas[i][0] = r.nextInt(3) + 1;
                deltas[i][1] = r.nextInt(2) + 2;
                deltas[i][2] = r.nextInt(3) + 1;
            }
        }
        return deltas;
    }

    public boolean[][][] getMaze(int[][] deltas)
    {
        if(deltas == null)
        {
            deltas = initDeltasCrazy();
        }

        this.deltas = deltas;

        xMSize = 0;
        yMSize = 0;
        zMSize = 0;

        for(int i = 0; i < deltas.length; i++)
        {
            if(i < xSize) xMSize += deltas[i][0];
            if(i < ySize) yMSize += deltas[i][1];
            if(i < zSize) zMSize += deltas[i][2];
        }

        boolean[][][] maze = new boolean[xMSize][yMSize][zMSize];

        for(int x = 0; x < xMSize; x++)
        {
            for(int y = 0; y < yMSize; y++)
            {
                for(int z = 0; z < zMSize; z++)
                {
                    maze[x][y][z] = getBlockCrazyMaze(x, y, z, deltas);
                }
            }
        }

        return maze;
    }

    public void open(int x1, int y1, int z1, int x2, int y2, int z2)
    {
        x1 = x1 * 2 + 1;
        y1 = y1 * 2 + 1;
        z1 = z1 * 2 + 1;
        x2 = x2 * 2 + 1;
        y2 = y2 * 2 + 1;
        z2 = z2 * 2 + 1;

        if(x1 > x2)
        {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if(y1 > y2)
        {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        if(z1 > z2)
        {
            int tmp = z1;
            z1 = z2;
            z2 = tmp;
        }

        for(int i = x1; i < x2 + 1; i++)
        {
            for(int e = y1; e < y2 + 1; e++)
            {
                for(int o = z1; o < z2 + 1; o++)
                {
                    m[i][e][o] = false;
                }
            }
        }
    }

    private int getSurrounds(int x, int y, int z)
    {
        int surrounds = 0;

        if(!m[x + 1][y][z]) surrounds++;
        if(!m[x - 1][y][z]) surrounds++;
        if(!m[x][y + 1][z]) surrounds++;
        if(!m[x][y - 1][z]) surrounds++;
        if(!m[x][y][z + 1]) surrounds++;
        if(!m[x][y][z - 1]) surrounds++;

        return surrounds;
    }

    private Point3D getNextWall()
    {
        ArrayList<Point3D> w1 = new ArrayList<Point3D>();
        ArrayList<Point3D> w2 = new ArrayList<Point3D>();

        for (Point3D p : w)
        {
            int x = p.x;
            int y = p.y;
            int z = p.z;

            if(x % 2 == 0)
            {
                if(m[x + 1][y][z])
                {
                    if(getSurrounds(x - 1, y, z) < 2)
                    {
                        w1.add(p);
                    }
                    else
                    {
                        w2.add(p);
                    }
                }
                else if(m[x - 1][y][z])
                {
                    if(getSurrounds(x + 1, y, z) < 2)
                    {
                        w1.add(p);
                    }
                    else
                    {
                        w2.add(p);
                    }
                }
                else
                {
                    w2.add(p);
                }
            }
            else if(y % 2 == 0)
            {
                if(m[x][y + 1][z])
                {
                    if(getSurrounds(x, y - 1, z) < 2)
                    {
                        w1.add(p);
                    }
                    else
                    {
                        w2.add(p);
                    }
                }
                else if(m[x][y - 1][z])
                {
                    if(getSurrounds(x, y + 1, z) < 2)
                    {
                        w1.add(p);
                    }
                    else
                    {
                        w2.add(p);
                    }
                }
                else
                {
                    w2.add(p);
                }
            }
            else
            {
                if(m[x][y][z + 1])
                {
                    if(getSurrounds(x, y, z - 1) < 2)
                    {
                        w1.add(p);
                    }
                    else
                    {
                        w2.add(p);
                    }
                }
                else if(m[x][y][z - 1])
                {
                    if(getSurrounds(x, y, z + 1) < 2)
                    {
                        w1.add(p);
                    }
                    else
                    {
                        w2.add(p);
                    }
                }
                else
                {
                    w2.add(p);
                }
            }
        }

        Point3D p = null;

        if(w1.size() < 1 && w2.size() > 0)
        {
            p = getPoint(w2);
        }
        else if(w1.size() > 0 && w2.size() < 1)
        {
            p = getPoint(w1);
        }
        else
        {
            if(r.nextDouble() < K_PROB)
            {
                p = getPoint(w1);
            }
            else
            {
                p = getPoint(w2);
            }
        }

        w.clear();
        w.addAll(w1);
        w.addAll(w2);

        return p;
    }

    private Point3D getPoint(ArrayList<Point3D> w)
    {
        int index = r.nextInt(w.size());
        Collections.shuffle(w);
        Point3D p = w.get(index);
        w.remove(index);
        return p;
    }

    public void generate()
    {
        while(w.size() > 0)
        {
            Point3D p = getNextWall();

            int x = (int)p.x;
            int y = (int)p.y;
            int z = (int)p.z;

            if(x % 2 == 0)
            {
                if(m[x + 1][y][z])
                {
                    m[x][y][z] = false;
                    addCell(x + 1, y, z);
                }
                else if(m[x - 1][y][z])
                {
                    m[x][y][z] = false;
                    addCell(x - 1, y, z);
                }
                else if(r.nextDouble() < PROB)
                {
                    m[x][y][z] = false;
                }
            }
            else if(y % 2 == 0)
            {
                if(m[x][y + 1][z])
                {
                    m[x][y][z] = false;
                    addCell(x, y + 1, z);
                }
                else if(m[x][y - 1][z])
                {
                    m[x][y][z] = false;
                    addCell(x, y - 1, z);
                }
                else if(r.nextDouble() < PROB)
                {
                    m[x][y][z] = false;
                }
            }
            else
            {
                if(m[x][y][z + 1])
                {
                    m[x][y][z] = false;
                    addCell(x, y, z + 1);
                }
                else if(m[x][y][z - 1])
                {
                    m[x][y][z] = false;
                    addCell(x, y, z - 1);
                }
                else if(r.nextDouble() < PROB)
                {
                    m[x][y][z] = false;
                }
            }
        }
    }

    private void addCell(int x, int y, int z)
    {
        m[x][y][z] = false;
        if(x + 1 < xSize - 1 && m[x + 1][y][z])
        {
            w.add(new Point3D(x + 1, y, z));
        }
        if(y + 1 < ySize - 1 && m[x][y + 1][z])
        {
            w.add(new Point3D(x, y + 1, z));
        }
        if(z + 1 < zSize - 1 && m[x][y][z + 1])
        {
            w.add(new Point3D(x, y, z + 1));
        }
        if(x - 1 > 0 && m[x - 1][y][z])
        {
            w.add(new Point3D(x - 1, y, z));
        }
        if(y - 1 > 0 && m[x][y - 1][z])
        {
            w.add(new Point3D(x, y - 1, z));
        }
        if(z - 1 > 0 && m[x][y][z - 1])
        {
            w.add(new Point3D(x, y, z - 1));
        }
    }
}

