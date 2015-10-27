package com.mauro.bestmazes.utility.dungeon.dungeonConfiguration;

import com.mauro.bestmazes.blocks.Chest;
import com.mauro.bestmazes.blocks.PiselliteBricks;
import com.mauro.bestmazes.blocks.SpecialBlocks;
import com.mauro.bestmazes.utility.Drawer;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gabriele on 10/26/2015.
 */
public class IceConfiguration extends DungeonConfiguration {
    public IceConfiguration(){
        mobProb = 0.02;
        branchesProb = 0.7;
        joinProb = 0.005;
        crazy = false;
        walls = PiselliteBricks.piselliteBricks;
        roof = Blocks.packed_ice;
        content = Blocks.air;
        name = DungeonReferences.ICE;
        prob = 0.005;

        xStart = 2;
        yStart = 3;
        zStart = 0;

        xSize = 4;
        ySize = 4;
        zSize = 4;

        xDelta = 2;
        yDelta = 2;
        zDelta = 2;

        x1Delta = 2;
        y1Delta = 2;
        z1Delta = 2;

        xLootRoom = 7;
        yLootRoom = 1;

        biomes = new ArrayList<BiomeGenBase>();

        biomes.add(BiomeGenBase.iceMountains);
        biomes.add(BiomeGenBase.icePlains);
        biomes.add(BiomeGenBase.frozenRiver);
        biomes.add(BiomeGenBase.frozenOcean);
        biomes.add(BiomeGenBase.coldBeach);
    }

    public ArrayList<ItemStack> getLoot(Random r){
        ArrayList<ItemStack> loot = new ArrayList<ItemStack>();
        ItemStack boots = new ItemStack(Items.diamond_boots, 1);
        boots.addEnchantment(Enchantment.featherFalling, 10);
        loot.add(boots);
        return loot;
    }

    public DungeonConfiguration clone(){
        return new IceConfiguration();
    }

    public Block[][][] genLootRoom(Random random){
        Block[][][] model = new Block[15][9][16];

        Drawer.fillParallelepipedon(model, 1, 1, 1, 13, 7, 14, content);
        Drawer.drawParallelepipedon(model, 0, 0, 0, 14, 8, 15, walls);

        Drawer.fillParallelepipedon1(model, 3, 1, 14, 9, 1, -6, SpecialBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 4, 1, 14, 7, 1, -5, SpecialBlocks.stoneBricksSlabUp);
        Drawer.fillParallelepipedon1(model, 5, 2, 14, 5, 1, -4, SpecialBlocks.stoneBricksSlabDown);
        Drawer.fillParallelepipedon1(model, 6, 2, 14, 3, 1, -3, SpecialBlocks.stoneBricksSlabUp);

        Drawer.column(model, 3, 1, 3, walls, roof);
        Drawer.column(model, 3, 1, 6, walls, roof);
        Drawer.column(model, 3, 1, 9, walls, roof);
        Drawer.column(model, 3, 1, 12, walls, roof);
        Drawer.column(model, 11, 1, 3, walls, roof);
        Drawer.column(model, 11, 1, 6, walls, roof);
        Drawer.column(model, 11, 1, 9, walls, roof);
        Drawer.column(model, 11, 1, 12, walls, roof);

        Drawer.fillParallelepipedon1(model, 6, 3, 1, 3, 1, 2, walls);
        Drawer.fillParallelepipedon1(model, 6, 1, 2, 1, 2, 1, walls);
        Drawer.fillParallelepipedon1(model, 8, 1, 2, 1, 2, 1, walls);

        Drawer.fillParallelepipedon1(model, 7, 1, 0, 1, 2, 1, content);

        model[7][3][13] = new Chest(getLoot(random), Chest.NORTH);

        model[6][4][14] = Blocks.torch;
        model[8][4][14] = Blocks.torch;

        return model;
    }
}
