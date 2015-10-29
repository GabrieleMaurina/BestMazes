package com.mauro.bestmazes.client.Renders;

import com.mauro.bestmazes.reference.Reference;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gabriele on 10/28/2015.
 */
public class MinotaurRender extends RenderBiped {

    private static final ResourceLocation textureLocation = new ResourceLocation(Reference.MOD_ID + ":" + "textures/models/minotaur.png");

    public MinotaurRender(ModelBiped model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return textureLocation;
    }
}