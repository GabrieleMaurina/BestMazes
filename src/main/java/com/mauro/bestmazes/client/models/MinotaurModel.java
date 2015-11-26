package com.mauro.bestmazes.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Gabriele on 11/23/2015.
 */
public class MinotaurModel extends ModelBase {
    //fields
    ModelRenderer leftHorn;
    ModelRenderer leftHornBase;
    ModelRenderer rightHorn;
    ModelRenderer rightHornBase;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public MinotaurModel()
    {
        textureWidth = 116;
        textureHeight = 58;

        leftHorn = new ModelRenderer(this, 33, 0);
        leftHorn.addBox(5F, -9F, 3F, 2, 8, 2);
        leftHorn.setRotationPoint(0F, 0F, 0F);
        leftHorn.setTextureSize(116, 58);
        leftHorn.mirror = true;
        setRotation(leftHorn, 0.7063936F, 0F, 0F);
        leftHornBase = new ModelRenderer(this, 42, 0);
        leftHornBase.addBox(4F, -3F, 3F, 1, 2, 2);
        leftHornBase.setRotationPoint(0F, 0F, 0F);
        leftHornBase.setTextureSize(116, 58);
        leftHornBase.mirror = true;
        setRotation(leftHornBase, 0.7063936F, 0F, 0F);
        rightHorn = new ModelRenderer(this, 33, 0);
        rightHorn.addBox(-7F, -9F, 3F, 2, 8, 2);
        rightHorn.setRotationPoint(0F, 0F, 0F);
        rightHorn.setTextureSize(116, 58);
        rightHorn.mirror = true;
        setRotation(rightHorn, 0.7063936F, 0F, 0F);
        rightHornBase = new ModelRenderer(this, 42, 0);
        rightHornBase.addBox(-5F, -3F, 3F, 1, 2, 2);
        rightHornBase.setRotationPoint(0F, 0F, 0F);
        rightHornBase.setTextureSize(116, 58);
        rightHornBase.mirror = true;
        setRotation(rightHornBase, 0.7063936F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(116, 58);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(116, 58);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
        rightarm.setRotationPoint(-5F, 2F, 0F);
        rightarm.setTextureSize(116, 58);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
        leftarm.setRotationPoint(5F, 2F, 0F);
        leftarm.setTextureSize(116, 58);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-2F, 12F, 0F);
        rightleg.setTextureSize(116, 58);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 57, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(2F, 12F, 0F);
        leftleg.setTextureSize(116, 58);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        leftHorn.render(f5);
        leftHornBase.render(f5);
        rightHorn.render(f5);
        rightHornBase.render(f5);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
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

}