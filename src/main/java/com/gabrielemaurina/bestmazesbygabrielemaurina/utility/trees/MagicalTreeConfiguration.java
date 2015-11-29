package com.gabrielemaurina.bestmazesbygabrielemaurina.utility.trees;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonConfigurations;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.dungeonConfiguration.DungeonConfiguration;
import net.minecraft.block.Block;

import java.util.Random;

/**
 * Created by Gabriele on 11/25/2015.
 */
public class MagicalTreeConfiguration extends TreeConfiguration {
    public MagicalTreeConfiguration()
    {
        radius = 8;
        dRadius = -2;
        length = 20.0;
        kLength = 1.4;
        spread = Math.PI / 9;
        bonusSpread = Math.PI / 15;
        size = 4;
        nSons = 5;
        fatherPull = 0.9;
        leavesSize = 20;
        leavesProb = 0.5;
        leaves = BestMazesBlocks.magicalLeaves;
    }

    public Block[][][] getHut(Random random){
        DungeonConfiguration dC = DungeonConfigurations.getConfiguration(DungeonReferences.FOREST);
        dC.walls = wood;
        dC.roof = planks;

        Block[][][] model = new Block[7][13][7];

        Drawer.printModelOnBase(model, dC.genEntranceHut(random), 0, 5, 0);

        Drawer.fillParallelepipedon1(model, 0, 5, 0, 7, 1, 7, planks);
        Drawer.fillParallelepipedon1(model, 1, 4, 1, 5, 1, 5, wood);

        Drawer.fillParallelepipedon1(model, 1, 0, 1, 1, 4, 1, wood);
        Drawer.fillParallelepipedon1(model, 5, 0, 1, 1, 4, 1, wood);
        Drawer.fillParallelepipedon1(model, 1, 0, 5, 1, 4, 1, wood);
        Drawer.fillParallelepipedon1(model, 5, 0, 5, 1, 4, 1, wood);

        return model;
    }
}
