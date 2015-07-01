package com.mauro.bestmazes.tree;

import java.util.ArrayList;

/**
 * Created by Gabriele on 7/1/2015.
 */

public class Node
{
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;
    private double width = 0.0;
    private double length = 0.0;
    private ArrayList<Node> sons;
    private Node father;
    int index;

    public Node(double x, double y, double z, double width, double length, Node father, int index)
    {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.length = length;
        this.sons = new ArrayList<Node>();
        this.father = father;
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public void addSon(Node son){
        sons.add(son);
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    public double getWidth()
    {
        return width;
    }

    public double getLength()
    {
        return length;
    }

    public ArrayList<Node> getSons()
    {
        return sons;
    }

    public Node getFather(){
        return father;
    }

    @Override
    public String toString()
    {
        return "Node [x=" + x + ", y=" + y + ", width=" + width + ", length=" + length + ", sons=" + sons + "]";
    }
}