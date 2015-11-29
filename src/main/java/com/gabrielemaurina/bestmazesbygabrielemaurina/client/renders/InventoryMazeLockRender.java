package com.gabrielemaurina.bestmazesbygabrielemaurina.client.renders;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Gabriele on 11/17/2015.
 */
public class InventoryMazeLockRender implements IItemRenderer {

    private MazeLockRender render;
    private TileEntity entity;

    public InventoryMazeLockRender(MazeLockRender render, TileEntity entity){
        this.render = render;
        this.entity = entity;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glTranslatef(0F, -0.1F, 0F);
        this.render.renderTileEntityAt(this.entity,0.0D,0.0D,0.0D,0.0F);
    }
}
