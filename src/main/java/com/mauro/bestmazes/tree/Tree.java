package com.mauro.bestmazes.tree;

/**
 * Created by Gabriele on 7/1/2015.
 */

import com.mauro.bestmazes.common.Drawer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.*;

public class Tree
{
    public static final double WIDTH = 8;
    public static final double LENGTH = 20;
    public static final double SPREAD = Math.PI / 4.0;
    public static final int SIZE = 10;
    public static final double CHILD_PROB = 0.6;
    public static final int N_SONS = 5;
    public static final double K_WIDTH = 0.85;
    public static final double K_LENGTH = 0.9;

    private double up = Math.PI / 2.0;
    private Random rand;
    private int size;
    private int nSons;
    private double spread;
    private double childProb;
    private double kWidth;
    private double kLength;
    private Node base;

    public Tree(double width, double length, double spread, int size, Random rand, int nSons, double childProb, double kLength, double kWidth)
    {
        base = new Node(0, 0, 0, width, length, null, 0);
        this.rand = rand;
        this.size = size;
        this.nSons = nSons;
        this.spread = spread;
        this.childProb = childProb;
        this.kLength = kLength;
        this.kWidth = kWidth;
        addLayer(0, base);
    }

    private int sons(int index, Node father)
    {
        if(rand.nextDouble() < childProb * father.getIndex() && index > 0)
        {
            return rand.nextInt(nSons - 1) + 2;
        }
        return 1;
    }

    private double normalize(double angle)
    {
        double dec = angle - (int)angle;
        return (((int)angle) % 360) + dec;
    }

    private double getXAngle(Node father)
    {
        double angle = 0.0;
        Node grandFather = father.getFather();
        if(grandFather == null)
        {
            angle = up;
        }
        else
        {
            angle = Math.atan((father.getY() - grandFather.getY()) / (father.getX() - grandFather.getX())) + (father.getX() - grandFather.getX() < 0 ? Math.PI : 0.0);
            angle = normalize(angle);
        }
        return angle;
    }

    private double getZAngle(Node father)
    {
        double angle = 0.0;
        Node grandFather = father.getFather();
        if(grandFather == null)
        {
            angle = up;
        }
        else
        {
            angle = Math.atan((father.getY() - grandFather.getY()) / (father.getZ() - grandFather.getZ())) + (father.getZ() - grandFather.getZ() < 0 ? Math.PI : 0.0);
            angle = normalize(angle);
        }
        return angle;
    }

    private void addLayer(int index, Node father)
    {
        if(index < size)
        {
            double xAngle = getXAngle(father);
            double zAngle = getZAngle(father);
            int sons = sons(index, father);

            double bonus = 1.0;
            if(sons > 1)
            {
                bonus = 3.0;
            }

            int left = 0, right = 0;
            int up = 0, down = 0;

            for(int e = 0; e < sons; e++)
            {
                double tmpX = 0.0;
                double tmpZ = 0.0;

                if(left > right)
                {
                    tmpX = xAngle + (-rand.nextDouble() / 2.0) * spread * bonus;
                    right++;
                }
                else if(right > left)
                {
                    tmpX = xAngle + (rand.nextDouble() / 2.0) * spread * bonus;
                    left++;
                }
                else
                {
                    if(rand.nextDouble() < .5)
                    {
                        tmpX = xAngle + (-rand.nextDouble() / 2.0) * spread * bonus;
                        right++;
                    }
                    else
                    {
                        tmpX = xAngle + (rand.nextDouble() / 2.0) * spread * bonus;
                        left++;
                    }
                }

                if(up > down)
                {
                    tmpZ = zAngle + (-rand.nextDouble() / 2.0) * spread * bonus;
                    right++;
                }
                else if(down > up)
                {
                    tmpZ = zAngle + (rand.nextDouble() / 2.0) * spread * bonus;
                    left++;
                }
                else
                {
                    if(rand.nextDouble() < .5)
                    {
                        tmpZ = zAngle + (-rand.nextDouble() / 2.0) * spread * bonus;
                        right++;
                    }
                    else
                    {
                        tmpZ = zAngle + (rand.nextDouble() / 2.0) * spread * bonus;
                        left++;
                    }
                }

                tmpX = normalize(tmpX);
                tmpZ = normalize(tmpZ);

                double dX = father.getLength() * Math.cos(tmpX);
                double dZ = father.getLength() * Math.cos(tmpZ);
                double dY = Math.sqrt(Math.pow(father.getLength(), 2.0) - Math.pow(dX, 2.0) - Math.pow(dZ, 2.0));

                double newX = father.getX() + dX;
                double newY = father.getY() + dY;
                double newZ = father.getZ() + dZ;

                Node son = new Node(newX, newY, newZ, father.getWidth() * kWidth, father.getLength() * kLength, father, (sons > 1 ? 0 : father.getIndex() + 1));
                father.getSons().add(son);
                addLayer(index + 1, son);
            }
        }
    }

    private void draw(Block[][][] model, Node n){
        if(n != null){
            for(int i = 0; i < n.getSons().size(); i++){
                int x1 = (int)Math.round(n.getX());
                int y1 = (int)Math.round(n.getY());
                int z1 = (int)Math.round(n.getZ());
                Node n1 = n.getSons().get(i);
                int x2 = (int)Math.round(n1.getX());
                int y2 = (int)Math.round(n1.getY());
                int z2 = (int)Math.round(n1.getZ());

                if(x1 > x2){
                    int tmp = x1;
                    x1 = x2;
                    x2 = tmp;
                }
                if(y1 > y2){
                    int tmp = y1;
                    y1 = y2;
                    y2 = tmp;
                }
                if(z1 > z2){
                    int tmp = z1;
                    z1 = z2;
                    z2 = tmp;
                }

                x2 = x1 < 0 ? x2 - x1 : x2;
                x1 = x1 < 0 ? 0: x1;
                y2 = y1 < 0 ? y2 - y1 : y2;
                y1 = y1 < 0 ? 0: y1;
                z2 = z1 < 0 ? z2 - z1 : z2;
                z1 = z1 < 0 ? 0: z1;

                Drawer.line(model, x1, y1, z1, x2, y2, z2, Blocks.log);
            }
        }
    }

    public Block[][][] getTree()
    {
        Block[][][] model = new Block[100][100][100];

        draw(model, base);

        return model;
    }
}