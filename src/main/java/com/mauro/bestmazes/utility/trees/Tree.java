package com.mauro.bestmazes.utility.trees;

/**
 * Created by Gabriele on 11/20/2015.
 */

import com.mauro.bestmazes.utility.Drawer;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.Random;

public class Tree
{
    private Random random;
    private int size;
    private int nSons;
    private double spread;
    private double bonusSpread;
    private double kLength;
    private Node base;
    private double fatherPull;

    public Tree(TreeConfiguration tC, Random random)
    {
        this.base = new Node(0, 0, 0, tC.radius, tC.length, null, 0, 0);
        this.random = random;
        this.size = tC.size;
        this.nSons = tC.nSons;
        this.spread = tC.spread;
        this.bonusSpread = tC.bonusSpread;
        this.kLength = tC.kLength;
        this.fatherPull = tC.fatherPull;
        addLayer(0, base);
    }

    private int sons(int index, Node father)
    {
        if(index == 1)
        {
            return nSons;
        }
        else if(index > 1)
        {
            return random.nextInt(nSons - 2) + 3;
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
                bonus = bonusSpread;
            }

            double verAng = (index == 0) ? 0 : (random.nextDouble() * spread + bonus);

            double[] orAng = new double[sons];
            orAng[0] = norm(random.nextDouble() * Math.PI * 2.0);
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
                double fatherVerAng = father.verAng * fatherPull;

                double orLen1 = Math.sin(fatherVerAng) * dY;
                double dX1 = Math.cos(father.orAng) * orLen1;
                double dZ1 = Math.sin(father.orAng) * orLen1;
                double dY1 = Math.cos(fatherVerAng) * dY;

                orLen1 = Math.cos(fatherVerAng) * dX;
                dX1 += Math.cos(father.orAng) * orLen1;
                dZ1 += Math.sin(father.orAng) * orLen1;
                dY1 += -Math.sin(fatherVerAng) * dX;

                orLen1 = dZ;
                dX1 += -Math.sin(father.orAng) * orLen1;
                dZ1 += Math.cos(father.orAng) * orLen1;
                dY1 += 0.0;

                double verAng1 = norm(Math.asin(Math.sqrt(dX1 * dX1 + dZ1 * dZ1) / father.length));
                double orAng1 = norm((dX1 == 0.0 && dZ1 == 0.0) ? 0.0 : Math.atan2(dZ1, dX1));

                double newX = father.x + dX1;
                double newY = father.y + dY1;
                double newZ = father.z + dZ1;

                int radius = father.radius - 1;
                radius = radius < 1 ? 1 : radius;

                Node son = new Node(newX, newY, newZ, radius, father.length * kLength, father, verAng1, orAng1);
                father.sons.add(son);

                addLayer(index + 1, son);
            }
        }
    }

    public static void genTree(World world, int x, int y, int z, TreeConfiguration tC, Random random)
    {
        Tree t = new Tree(tC, random);
        genBranch(world, null, t.base, x, y, z, tC.wood, tC.leaves, tC.leavesSize);

    }

    private static void genBranch(World world, Node father, Node son, int x, int y, int z, Block wood, Block leaves, int leavesSize){
        for(Node grandSon : son.sons){
            genBranch(world, son, grandSon, x, y, z, wood, leaves, leavesSize);
        }

        if(son.sons.size() == 0){
            //Drawer.fillSphere(world, (int) (x + son.x), (int) (y + son.y), (int) (z + son.z), leavesSize, leaves);
        }

        if(father != null){
            //Drawer.fillCylinder(world, (int) (x + father.x), (int) (y + father.y), (int) (z + father.z), (int) (x + son.x), (int) (y + son.y), (int) (z + son.z), father.radius, wood);
        }
    }
}