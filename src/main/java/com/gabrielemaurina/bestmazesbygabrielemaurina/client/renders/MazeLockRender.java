package com.gabrielemaurina.bestmazesbygabrielemaurina.client.renders;

import com.gabrielemaurina.bestmazesbygabrielemaurina.client.models.MazeLockModel;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import com.gabrielemaurina.bestmazesbygabrielemaurina.tileEntities.MazeLockTileEntity;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Gabriele on 11/17/2015.
 */

public class MazeLockRender extends TileEntitySpecialRenderer {

    private static final ResourceLocation texturenormal = new ResourceLocation(Reference.MOD_ID,"textures/models/MazeLock.png");

    private MazeLockModel model;

    EntityItem entityItem = null;

    public MazeLockRender(){
        this.model = new MazeLockModel();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y - 0.5F,(float)z + 0.5F);
        GL11.glRotatef(180,0F,1F,0F);

        this.bindTexture(texturenormal);
        GL11.glPushMatrix();
        this.model.renderModel(0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        //render hovering item
        int slot = 0;
        MazeLockTileEntity tileEntity = (MazeLockTileEntity)tile;
        if((entityItem == null) || entityItem.getEntityItem().getItem() != tileEntity.getStackInSlot(slot).getItem())
            entityItem = new EntityItem(tileEntity.getWorldObj(), x, y, z, tileEntity.getStackInSlot(slot));
        if(entityItem != null){
            GL11.glPushMatrix();
            this.entityItem.hoverStart = 0.0F;
            RenderItem.renderInFrame = true;
            GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.9F);
            GL11.glRotatef(-90, 1, 0, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glScaled(1.5, 1.5, 1.5);
            RenderManager.instance.renderEntityWithPosYaw(this.entityItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            RenderItem.renderInFrame = false;
            GL11.glPopMatrix();
        }
    }

}