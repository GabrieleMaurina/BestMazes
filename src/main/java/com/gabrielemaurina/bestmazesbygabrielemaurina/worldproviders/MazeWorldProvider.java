package com.gabrielemaurina.bestmazesbygabrielemaurina.worldproviders;

import com.gabrielemaurina.bestmazesbygabrielemaurina.chunkManagers.MazeChunkManager;
import com.gabrielemaurina.bestmazesbygabrielemaurina.chunkproviders.MazeChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Created by Gabriele on 11/30/2015.
 */
public class MazeWorldProvider extends WorldProvider {
    public static final String name = "mazeDimesion";
    public static int dimensionId;
    public MazeWorldProvider()
    {
        worldChunkMgr = new MazeChunkManager();
    }

    public IChunkProvider createChunkGenerator()
    {
        return new MazeChunkProvider(this.worldObj, this.worldObj.getSeed());
    }

    public String getDimensionName()
    {
        return name;
    }

    @SideOnly(Side.CLIENT)
    public String getWelcomeMessage()
    {
        return "Welcome to The Maze";
    }
}