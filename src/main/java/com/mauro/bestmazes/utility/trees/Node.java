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
    public double width = 0.0;
    public double length = 0.0;
    public ArrayList<Node> sons = new ArrayList<Node>();
    public Node father;
    public int sonsN = 0;
    public double verAng = 0.0;
    public double orAng = 0.0;


    public Node(double x, double y, double z, double width, double length, Node father, int sonsProb, double verAng, double orAng)
    {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.length = length;
        this.father = father;
        this.sonsN = sonsProb;
        this.verAng = verAng;
        this.orAng = orAng;
    }

    @Override
    public String toString()
    {
        return "Node [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}