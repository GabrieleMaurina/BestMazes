package com.mauro.bestmazes.items;

import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by Gabriele on 11/16/2015.
 */
public class MinotaurIvorySword extends ItemSword{
    public static final String name = "minotaurIvorySword";
    public static Item.ToolMaterial ivorySwordMaterial = EnumHelper.addToolMaterial(name, 3, 2000, 15.0F, 5.0F, 30);

    public MinotaurIvorySword() {
        super(ivorySwordMaterial);
        this.setUnlocalizedName(name);
        this.setTextureName(Reference.MOD_ID + ":" + name);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }
}
