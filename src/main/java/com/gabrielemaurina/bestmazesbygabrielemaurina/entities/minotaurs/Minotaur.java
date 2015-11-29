package com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by Gabriele on 10/28/2015.
 */
public abstract class Minotaur extends EntityMob {

    public ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

    public Minotaur(World world){
        super(world);
        setSize(0.5F, 1.7F);
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false));
        tasks.addTask(3, new EntityAIWander(this, 1.0D));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(5, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void despawnEntity(){}

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
        getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(36.0);
    }

    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public float getAbsorptionAmount() {
        return 0.5F;
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        if (!worldObj.isRemote && drop != null) {
            for(ItemStack itemStack : drop){
                entityDropItem(itemStack, 0.0F);
            }
        }
        super.onDeath(damageSource);
    }
}
