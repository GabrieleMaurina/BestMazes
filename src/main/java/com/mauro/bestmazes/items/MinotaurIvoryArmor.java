package com.mauro.bestmazes.items;

import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.reference.Reference;
import com.mauro.bestmazes.tabs.BestMazesTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gabriele on 11/12/2015.
 */
public class MinotaurIvoryArmor extends ItemArmor implements ISpecialArmor {

    public static final String HELMET = "minotaurIvoryHelmet";
    public static final String CHESTPLATE = "minotaurIvoryChestplate";
    public static final String LEGGINS = "minotaurIvoryLeggins";
    public static final String BOOTS = "minotaurIvoryBoots";

    public String name;

    public static final Map<String, Integer> ids = new HashMap<String, Integer>();
    static{
        ids.put(HELMET, 0);
        ids.put(CHESTPLATE, 1);
        ids.put(LEGGINS, 2);
        ids.put(BOOTS, 3);
    }

    public static final ArmorMaterial IVORY = EnumHelper.addArmorMaterial("IVORY", 100, new int[]{4, 8, 6, 4}, 30);

    public static final String TEXTURE = "minotaurIvoryArmor";

    public MinotaurIvoryArmor(String name) {
        super(IVORY, 0, ids.get(name));
        this.setUnlocalizedName(name);
        this.name = name;
        this.setTextureName(Reference.MOD_ID + ":armors/" + name);
        setCreativeTab(BestMazesTabs.bestMazesTab);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return Reference.MOD_ID + ":textures/items/armors/" + TEXTURE + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        if(armor.getItem().getUnlocalizedName().substring(5).equals(HELMET) && hasArmorSet(player)){
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20, 4));
        }
    }

    public boolean hasArmorSet(EntityPlayer player) {
        return hasArmorItem(player, HELMET) && hasArmorItem(player, CHESTPLATE) && hasArmorItem(player, LEGGINS) && hasArmorItem(player, BOOTS);
    }

    public boolean hasArmorItem(EntityPlayer player, String item) {
        ItemStack stack = player.inventory.armorInventory[3 - ids.get(item)];

        if(stack == null)
            return false;
        switch(ids.get(item)){
            case 0: return stack.getItem() == BestMazesItems.minotaurIvoryHelmet;
            case 1: return stack.getItem() == BestMazesItems.minotaurIvoryChestplate;
            case 2: return stack.getItem() == BestMazesItems.minotaurIvoryLeggings;
            case 3: return stack.getItem() == BestMazesItems.minotaurIvoryBoots;
        }

        return false;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if(source.isUnblockable()) {
            return new ArmorProperties(0, 0, 0);
        }
        if(source.getEntity() instanceof Minotaur) {
            return new ArmorProperties(0, damageReduceAmount / 23.0, armor.getMaxDamage() + 1 - armor.getItemDamage());
        }
        return new ArmorProperties(0, damageReduceAmount / 25.5, armor.getMaxDamage() + 1 - armor.getItemDamage());
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return damageReduceAmount;
    }
}
