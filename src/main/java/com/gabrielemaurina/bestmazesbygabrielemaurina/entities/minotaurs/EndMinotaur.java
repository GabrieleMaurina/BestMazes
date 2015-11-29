package com.gabrielemaurina.bestmazesbygabrielemaurina.entities.minotaurs;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.items.BestMazesItems;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.dungeon.DungeonReferences;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Gabriele on 11/16/2015.
 */
public class EndMinotaur extends Minotaur {

    public static String type = DungeonReferences.END;
    public static String name = type + "Minotaur";

    public EndMinotaur(World world) {
        super(world);
        drop.add(new ItemStack(BestMazesItems.minotaurIvoryPickaxe, 1));
        drop.add(new ItemStack(BestMazesBlocks.magicalSapling, 1));
        drop.add(new ItemStack(BestMazesItems.endKey, 1));
        setSize(1.5F, 3.7F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0);
        getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0);
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(48.0);
    }
}