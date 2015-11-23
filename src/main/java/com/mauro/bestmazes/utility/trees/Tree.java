package com.mauro.bestmazes.utility.trees;

/**
 * Created by Gabriele on 11/20/2015.
 */

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.utility.Drawer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class Tree
{
    private static final double WIDTH = 8;
    private static final double LENGTH = 10;
    private static final double SPREAD = Math.PI / 6.0;
    private static final int SIZE = 5;
    private static final double CHILD_PROB = 1.0;
    private static final int N_SONS = 5;
    private static final double K_WIDTH = 0.85;
    private static final double K_LENGTH = 0.9;

    private Random rand;
    private int size;
    private int nSons;
    private double spread;
    private double childProb;
    private double kWidth;
    private double kLength;
    private Node base;

    public Tree(double width, double length, double spread, int size, int nSons, double childProb, double kLength, double kWidth, Random rand)
    {
        this.base = new Node(0, 0, 0, width, length, null, 0, 0, 0);
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
        if(index > 0 && rand.nextDouble() < childProb * father.sonsN)
        {
            return rand.nextInt(nSons - 2) + 3;
            //return 4;
        }
        return 1;
    }

    private static double norm(double angle)
    {
        double toRtn = 0.0;

        if(angle > 0.0){
            while(angle >= 0.0)
            {
                toRtn = angle;
                angle -= Math.PI * 2.0;
            }
        }
        else if(angle < 0.0){
            while(angle <= 0.0)
            {
                toRtn = angle;
                angle += Math.PI * 2.0;
            }
            toRtn += Math.PI * 2.0;
        }

        return toRtn;
    }

    private void addLayer(int index, Node father)
    {
        if(index < size)
        {
            int sons = sons(index, father);

            double bonus = 0.0;
            if(sons > 1)
            {
                bonus = Math.PI / 8.0;
            }

            double verAng = (index == 0) ? 0 : (rand.nextDouble() * spread + bonus);

            double[] orAng = new double[sons];
            orAng[0] = norm(rand.nextDouble() * Math.PI * 2.0);
            double delta = Math.PI * 2.0 / sons;
            for(int i = 1; i < orAng.length; i++){
                orAng[i] = norm(orAng[i - 1] + delta);
            }

            for(int i = 0; i < sons; i++)
            {
                //relative vector
                double orLen = Math.sin(verAng) * father.length;
                double dX = Math.cos(orAng[i]) * orLen;
                double dZ = Math.sin(orAng[i]) * orLen;
                double dY = Math.cos(verAng) * father.length;

                //real vector
                double orLen1 = Math.sin(father.verAng) * dY;
                double dX1 = Math.cos(father.orAng) * orLen1;
                double dZ1 = Math.sin(father.orAng) * orLen1;
                double dY1 = Math.cos(father.verAng) * dY;

                orLen1 = Math.cos(father.verAng) * dX;
                dX1 += Math.cos(father.orAng) * orLen1;
                dZ1 += Math.sin(father.orAng) * orLen1;
                dY1 += -Math.sin(father.verAng) * dX;

                orLen1 = dZ;
                dX1 += -Math.sin(father.orAng) * orLen1;
                dZ1 += Math.cos(father.orAng) * orLen1;
                dY1 += 0.0;

                double verAng1 = norm(Math.asin(Math.sqrt(dX1 * dX1 + dZ1 * dZ1) / father.length)) * (0.7 + rand.nextDouble() * 0.3);
                double orAng1 = norm((dX1 == 0.0 && dZ1 == 0.0) ? 0.0 : Math.atan2(dZ1, dX1));

                orLen1 = Math.sin(verAng1) * father.length;
                dX1 = Math.cos(orAng1) * orLen;
                dZ1 = Math.sin(orAng1) * orLen;
                dY1 = Math.cos(verAng1) * father.length;

                double newX = father.x + dX1;
                double newY = father.y + dY1;
                double newZ = father.z + dZ1;

                Node son = new Node(newX, newY, newZ, father.width * kWidth, father.length * kLength, father, (sons > 1 ? 0 : father.sonsN + 1), verAng1, orAng1);

                father.sons.add(son);
                addLayer(index + 1, son);
            }
        }
    }

    public static void genTree(World world, int x, int y, int z, Random random)
    {
        Tree t = new Tree(WIDTH, LENGTH, SPREAD, SIZE, N_SONS, CHILD_PROB, K_LENGTH, K_WIDTH, random);
        genBranch(world, null, t.base, x, y, z, Blocks.log);

    }

    private static void genBranch(World world, Node father, Node son, int x, int y, int z, Block b){
        if(son.sons.size() == 0){
            //Drawer.fillSphere(world, (int) (x + son.x), (int) (y + son.y), (int) (z + son.z), 4, Blocks.leaves);
        }

        for(Node grandSon : son.sons){
            genBranch(world, son, grandSon, x, y, z, b);
        }

        if(father != null){
            Drawer.line(world, (int) (x + father.x), (int) (y + father.y), (int) (z + father.z), (int) (x + son.x), (int) (y + son.y), (int) (z + son.z), b);
        }
    }
}