package com.mauro.bestmazes.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class MinotaurModel extends ModelBase
{
    //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightArm;
    ModelRenderer leftArm;
    ModelRenderer rightLeg;
    ModelRenderer leftLeg;
    ModelRenderer leftHorn;
    ModelRenderer leftHornBase;
    ModelRenderer rightHorn;
    ModelRenderer rightHornBase;

    private float HornsXRotation = 0.5F;

    public MinotaurModel()
    {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, -6F, 0F);
        head.setTextureSize(128, 128);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 0, 38);
        body.addBox(-7F, -6F, -3F, 14, 14, 8);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(128, 128);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightArm = new ModelRenderer(this, 24, 16);
        rightArm.addBox(-6F, -2.266667F, -2F, 6, 14, 6);
        rightArm.setRotationPoint(-7F, -2F, 0F);
        rightArm.setTextureSize(128, 128);
        rightArm.mirror = true;
        setRotation(rightArm, 0F, 0F, 0F);
        leftArm = new ModelRenderer(this, 24, 16);
        leftArm.addBox(0F, -2F, -2F, 6, 14, 6);
        leftArm.setRotationPoint(7F, -2F, 0F);
        leftArm.setTextureSize(128, 128);
        leftArm.mirror = true;
        setRotation(leftArm, 0F, 0F, 0F);
        rightLeg = new ModelRenderer(this, 0, 16);
        rightLeg.addBox(-3F, 0F, -4F, 6, 16, 6);
        rightLeg.setRotationPoint(-3F, 8F, 0F);
        rightLeg.setTextureSize(128, 128);
        rightLeg.mirror = true;
        setRotation(rightLeg, 0F, 0F, 0F);
        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.addBox(-3F, 0F, -4F, 6, 16, 6);
        leftLeg.setRotationPoint(3F, 8F, 0F);
        leftLeg.setTextureSize(128, 128);
        leftLeg.mirror = true;
        setRotation(leftLeg, 0F, 0F, 0F);
        leftHorn = new ModelRenderer(this, 0, 0);
        leftHorn.addBox(5F, -12F, 1F, 1, 6, 1);
        leftHorn.setRotationPoint(0F, -6F, 0F);
        leftHorn.setTextureSize(128, 128);
        leftHorn.mirror = true;
        setRotation(leftHorn, HornsXRotation, 0F, 0F);
        leftHornBase = new ModelRenderer(this, 0, 0);
        leftHornBase.addBox(4F, -7F, 1F, 1, 1, 1);
        leftHornBase.setRotationPoint(0F, -6F, 0F);
        leftHornBase.setTextureSize(128, 128);
        leftHornBase.mirror = true;
        setRotation(leftHornBase, HornsXRotation, 0F, 0F);
        rightHorn = new ModelRenderer(this, 0, 0);
        rightHorn.addBox(-6F, -12F, 1F, 1, 6, 1);
        rightHorn.setRotationPoint(0F, -6F, 0F);
        rightHorn.setTextureSize(128, 128);
        rightHorn.mirror = true;
        setRotation(rightHorn, HornsXRotation, 0F, 0F);
        rightHornBase = new ModelRenderer(this, 0, 0);
        rightHornBase.addBox(-5F, -7F, 1F, 1, 1, 1);
        rightHornBase.setRotationPoint(0F, -6F, 0F);
        rightHornBase.setTextureSize(128, 128);
        rightHornBase.mirror = true;
        setRotation(rightHornBase, HornsXRotation, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        leftHorn.render(f5);
        leftHornBase.render(f5);
        rightHorn.render(f5);
        rightHornBase.render(f5);
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

        this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
        this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
        this.rightHorn.rotateAngleY = this.head.rotateAngleY;
        this.rightHorn.rotateAngleX = this.head.rotateAngleX + HornsXRotation;
        this.rightHornBase.rotateAngleY = this.head.rotateAngleY;
        this.rightHornBase.rotateAngleX = this.head.rotateAngleX + HornsXRotation;
        this.leftHorn.rotateAngleY = this.head.rotateAngleY;
        this.leftHorn.rotateAngleX = this.head.rotateAngleX + HornsXRotation;
        this.leftHornBase.rotateAngleY = this.head.rotateAngleY;
        this.leftHornBase.rotateAngleX = this.head.rotateAngleX + HornsXRotation;

        this.rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.rightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;


        this.leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        this.leftArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;

        this.rightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.rightLeg.rotateAngleY = (float)Math.PI;

        this.leftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        this.leftLeg.rotateAngleY = (float)Math.PI;
    }
}