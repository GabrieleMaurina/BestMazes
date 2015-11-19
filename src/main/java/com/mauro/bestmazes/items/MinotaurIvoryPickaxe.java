package com.mauro.bestmazes.items;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import java.util.Set;

/**
 * Created by Gabriele on 11/18/2015.
 */
public class MinotaurIvoryPickaxe extends ItemPickaxe {

    public static final String name = "minotaurIvoryPickaxe";
    public static final int level = 3;
    public static final ToolMaterial ivoryPickaxeMaterial = EnumHelper.addToolMaterial(name, level, 2000, 5.0F, 1.0F, 30);

    private static final Set effectiveAgainst = Sets.newHashSet(new Block[]{
            BestMazesBlocks.piselliteBricks,
            BestMazesBlocks.piselliteBricksSlabDown,
            BestMazesBlocks.piselliteBricksSlabUp});

    protected MinotaurIvoryPickaxe() {
        super(ivoryPickaxeMaterial);
        this.setUnlocalizedName(name);
        this.setTextureName(Reference.MOD_ID + ":" + name);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of(name);
    }

    @Override
    public boolean func_150897_b(Block block) {
        return effectiveAgainst.contains(block) ? true : false;
    }

    @Override
    public float func_150893_a(ItemStack stack, Block block) {
        return effectiveAgainst.contains(block) ? this.efficiencyOnProperMaterial : 0F;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
        return true;
    }
}
