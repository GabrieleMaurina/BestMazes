package com.gabrielemaurina.bestmazesbygabrielemaurina.client.renders;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.MazePortal;
import com.gabrielemaurina.bestmazesbygabrielemaurina.client.models.MazeLockModel;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * Created by Gabriele on 12/3/2015.
 */
public class MazePortalRender implements ISimpleBlockRenderingHandler
{
    private int renderId;

    public MazePortalRender(int renderId){
        this.renderId = renderId;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        renderer.setRenderBounds(0.0, 0.0, 0.0, MazePortal.xDim, MazePortal.yDim, MazePortal.zDim);
        renderer.renderStandardBlock(block,x,y,z);
        return true;
    }

    @Override
    public int getRenderId()
    {
        return renderId;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer){}
}