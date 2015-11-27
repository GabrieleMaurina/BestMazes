package com.mauro.bestmazes.utility.trees;

import java.util.ArrayList;

/**
 * Created by Gabriele on 11/20/2015.
 */

public class Node
{
    public double x = 0.0;
    public double y = 0.0;
    public double z = 0.0;
    public int radius = 0;
    public double length = 0.0;
    public ArrayList<Node> sons = new ArrayList<Node>();
    public double verAng = 0.0;
    public double orAng = 0.0;
    public boolean built = false;


    public Node(double x, double y, double z, int radius, double length, double verAng, double orAng)
    {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.length = length;
        this.verAng = verAng;
        this.orAng = orAng;
    }

    @Override
    public String toString()
    {
        return "Node [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}