package com.gabrielemaurina.bestmazesbygabrielemaurina.items;

import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.tabs.BestMazesTabs;
import com.gabrielemaurina.bestmazesbygabrielemaurina.teleporters.MazeTeleporter;
import com.gabrielemaurina.bestmazesbygabrielemaurina.worldproviders.MazeWorldProvider;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.gabrielemaurina.bestmazesbygabrielemaurina.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
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
}
