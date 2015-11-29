package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees;

/**
 * Created by Gabriele on 11/20/2015.
 */

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableFoliage;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableCylinder;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.inflatables.InflatableRoundedCylinder;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class Tree
{
    public Random random;
    public TreeConfiguration tC;
    public Node base;

    public Tree(TreeConfiguration tC, Random random)
    {
        base = new Node(0, 0, 0, tC.radius, tC.length, 0, 0);
        this.random = random;
        this.tC = tC;

        addLayer(0, base);
    }

    private int sons(int index)
    {
        if(index == 1)
        {
            return tC.nSons;
        }
        else if(index > 1)
        {
            return random.nextInt(tC.nSons - 2) + 3;
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
        if(index < tC.size)
        {
            int sons = sons(index);
            boolean bonusSon = index == tC.size - 1;

            double verAng = random.nextDouble() * tC.spread + tC.bonusSpread * (bonusSon ? tC.nSons - 1 : (sons - 1));

            double[] orAng = new double[sons];
            orAng[0] = norm(random.nextDouble() * Math.PI * 2.0);

            double delta = Math.PI * 2.0 / sons;
            for(int i = 1; i < orAng.length; i++){
                orAng[i] = norm(orAng[i - 1] + delta);
            }

            for(int i = 0; i < sons; i++)
            {
                addSon(index, father, verAng, orAng[i]);
            }

            if(bonusSon){
                addSon(index, father, 0.0, 0.0);
            }
        }
    }

    private void addSon(int index, Node father, double verAng, double orAng){
        double fatherLength = index == 0 ? father.length * 4.0 : father.length;

        //relative vector
        double orLen = Math.sin(verAng) * fatherLength;
        double dX = Math.cos(orAng) * orLen;
        double dZ = Math.sin(orAng) * orLen;
        double dY = Math.cos(verAng) * fatherLength;

        //real vector
        double fatherVerAng = father.verAng * tC.fatherPull;

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

        //new node

        double verAng1 = Math.asin(Math.sqrt(dX1 * dX1 + dZ1 * dZ1) / fatherLength);
        verAng1 = dY1 < 0.0 ? Math.PI - verAng1 : verAng1;
        double orAng1 = norm((dX1 == 0.0 && dZ1 == 0.0) ? 0.0 : Math.atan2(dZ1, dX1));

        double newX = father.x + dX1;
        double newY = father.y + dY1;
        double newZ = father.z + dZ1;

        int radius = father.radius + tC.dRadius;
        radius = radius < 1 ? 1 : radius;

        Node son = new Node(newX, newY, newZ, radius, father.length * tC.kLength, verAng1, orAng1);
        father.sons.add(son);

        addLayer(index + 1, son);
    }

    public static void genTree(World world, int x, int y, int z, TreeConfiguration tC, Random random)
    {
        Tree t = new Tree(tC, random);
        genBranch(world, null, t.base, x, y - (int)tC.length, z, tC.wood, new InflatableFoliage(tC.leavesSize, tC.dProb, tC.leaves, tC.leavesProb, random));
        genLianes(world, x, y - (int)tC.length, z, t.base, tC, random);
    }

    private static void genBranch(World world, Node father, Node son, int x, int y, int z, Block wood, InflatableFoliage iF){
        for(Node grandSon : son.sons){
            genBranch(world, son, grandSon, x, y, z, wood, iF);
        }

        if(son.sons.size() == 0){
            iF.inflateShape(world, (int) (x + son.x), (int) (y + son.y), (int) (z + son.z));
        }

        if(father != null){
            new InflatableRoundedCylinder((int)(son.x - father.x), (int)(son.y - father.y), (int)(son.z - father.z), father.radius, 0, wood).inflateShape(world, (int) (x + father.x), (int) (y + father.y), (int) (z + father.z));
        }
    }

    public static void genLianes(World world, int x, int y, int z, Node father, TreeConfiguration tC, Random random){
        for(Node son : father.sons){
            genLianes(world, x, y, z, son, tC, random);
        }
        if(father.sons.size() == 0){
            x += father.x;
            y += father.y;
            z += father.z;

            Tree.gen4Lianes(world, x, y, z, tC, random);
        }
    }

    public static void gen4Lianes(World world, int x, int y, int z, TreeConfiguration tC, Random random){
        if(random.nextDouble() < tC.lianesProb){
            Tree.genLiane(world, x + tC.leavesSize, y, z, tC.leaves, random);
        }
        if(random.nextDouble() < tC.lianesProb){
            Tree.genLiane(world, x - tC.leavesSize, y, z, tC.leaves, random);
        }
        if(random.nextDouble() < tC.lianesProb){
            Tree.genLiane(world, x, y, z + tC.leavesSize, tC.leaves, random);
        }
        if(random.nextDouble() < tC.lianesProb){
            Tree.genLiane(world, x, y, z - tC.leavesSize, tC.leaves, random);
        }
    }

    public static void genLiane(World world, int x, int y, int z, Block leaves, Random random){
        boolean ok = true;
        int i = y;
        while(ok){
            Block b = world.getBlock(x, i--, z);
            ok = (b == Blocks.air || b == leaves) && i > 30;
        }
        i += 1;

        if(y - i > 10){
            i += random.nextInt((y - i) / 5);

            Drawer.line(world, x, y, z, x, i, z, leaves);

            i++;
            world.setBlock(x + 1, i, z, leaves);
            world.setBlock(x - 1, i, z, leaves);
            world.setBlock(x, i, z + 1, leaves);
            world.setBlock(x, i, z - 1, leaves);

            i++;
            Drawer.fillParallelepipedon1(world, x - 1, i, z - 1, 3, 1, 3, leaves);


            int dX = -1;
            int dZ = -1;
            int index = 0;

            int[] d1 = new int[]{1, 1, 0, 0, -1, -1, 0, 0};
            int[] d2 = new int[]{0, 0, 1, 1, 0, 0, -1, -1};

            boolean dir = random.nextBoolean();
            int pos = 0;
            while(y > i){
                world.setBlock(x + dX, y, z + dZ, leaves);

                pos = index % 8;

                dX += (dir ? d1 : d2)[pos];
                dZ += (dir ? d2 : d1)[pos];

                index++;
                y--;
            }
        }
    }

    public static int counter(Node father){
        int toRtn = father.sons.size();
        for(Node son : father.sons){
            toRtn += counter(son);
        }
        return toRtn;
    }
}