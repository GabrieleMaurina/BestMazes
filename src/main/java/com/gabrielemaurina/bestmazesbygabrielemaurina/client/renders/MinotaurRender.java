package com.gabrielemaurina.bestmazesbygabrielemaurina.client.renders;

import com.gabrielemaurina.bestmazesbygabrielemaurina.client.models.MinotaurModel;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Gabriele on 10/28/2015.
 */
public class MinotaurRender extends RenderLiving {

    private ResourceLocation textureLocation;

    private static final float X_SCALE = 0.9F;
    private static final float Y_SCALE = 0.9F;
    private static final float Z_SCALE = 1.0F;

    private static final float X_END_SCALE = 1.7F;
    private static final float Y_END_SCALE = 1.7F;
    private static final float Z_END_SCALE = 1.7F;

    private float xScale = X_SCALE;
    private float yScale = Y_SCALE;
    private float zScale = Z_SCALE;

    public MinotaurRender(MinotaurModel model, float shadowSize, String type) {
        super(model, shadowSize);
        textureLocation = new ResourceLocation(Reference.MOD_ID + ":" + "textures/models/" + type + "Minotaur.png");
        if(type.equals(DungeonReferences.END)){
            xScale = X_END_SCALE;
            yScale = Y_END_SCALE;
            zScale = Z_END_SCALE;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return textureLocation;
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        GL11.glScalef(xScale, yScale, zScale);
    }
}