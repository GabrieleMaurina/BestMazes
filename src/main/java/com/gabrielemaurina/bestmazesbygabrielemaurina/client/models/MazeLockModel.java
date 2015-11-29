package com.gabrielemaurina.bestmazesbygabrielemaurina.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Gabriele on 11/17/2015.
 */

public class MazeLockModel extends ModelBase
{
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;

    public MazeLockModel()
    {
        textureWidth = 64;
        textureHeight = 64;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-8F, -8F, 0F, 16, 16, 8);
        Shape1.setRotationPoint(0F, 16F, 0F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(-8F, -8F, -8F, 16, 2, 8);
        Shape2.setRotationPoint(0F, 16F, 0F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(-8F, 6F, -8F, 16, 2, 8);
        Shape3.setRotationPoint(0F, 16F, 0F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 24);
        Shape4.addBox(6F, -6F, -8F, 2, 12, 4);
        Shape4.setRotationPoint(0F, 16F, 4F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 24);
        Shape5.addBox(-8F, -6F, -8F, 2, 12, 4);
        Shape5.setRotationPoint(0F, 16F, 4F);
        Shape5.setTextureSize(64, 64);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void renderModel(float f){
        Shape1.render(f);
        Shape2.render(f);
        Shape3.render(f);
        Shape4.render(f);
        Shape5.render(f);
    }
}