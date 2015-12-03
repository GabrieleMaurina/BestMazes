package com.gabrielemaurina.bestmazesbygabrielemaurina.teleporters;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.MazePortal;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * Created by Gabriele on 12/1/2015.
 */
public class MazeTeleporter extends Teleporter{

    public MazeTeleporter(WorldServer par1WorldServer)
    {
        super(par1WorldServer);
    }

    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        par1Entity.setPosition(0.0, 150.0, 0.0);
    }
}