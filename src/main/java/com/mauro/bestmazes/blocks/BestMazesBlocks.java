package com.mauro.bestmazes.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * Created by Gabriele on 10/29/2015.
 */
public class BestMazesBlocks {

    static public Block piselliteBricks = new PiselliteBricks();
    static private Block piselliteBricksSlab = new PiselliteBricksSlab();
    public static final Block mazeLock = new MazeLock();

    //public static final SpecialBlock stoneBricksSlabDown = new SpecialBlock(Blocks.stone_slab, 5, 3);
    //public static final SpecialBlock stoneBricksSlabUp = new SpecialBlock(Blocks.stone_slab, 13, 3);
    public static final SpecialBlock piselliteBricksSlabDown = new SpecialBlock(BestMazesBlocks.piselliteBricksSlab, 5, 3);
    public static final SpecialBlock piselliteBricksSlabUp = new SpecialBlock(BestMazesBlocks.piselliteBricksSlab, 13, 3);
    public static final SpecialBlock oakWoodPlanks = new SpecialBlock(Blocks.planks, 0, 3);
    public static final SpecialBlock spruceWoodPlanks = new SpecialBlock(Blocks.planks, 1, 3);
    public static final SpecialBlock britchWoodPlanks = new SpecialBlock(Blocks.planks, 2, 3);
    public static final SpecialBlock jungleWoodPlanks = new SpecialBlock(Blocks.planks, 3, 3);
    public static final SpecialBlock acaciaWoodPlanks = new SpecialBlock(Blocks.planks, 4, 3);
    public static final SpecialBlock darkWoodPlanks = new SpecialBlock(Blocks.planks, 5, 3);
    public static final SpecialBlock tallGrass = new TallGrass(TallGrass.TALLGRASS);
    public static final SpecialBlock shrub = new TallGrass(TallGrass.SHRUB);
    public static final SpecialBlock fern = new TallGrass(TallGrass.FERN);
    public static final SpecialBlock vineNorth = new Vine(Vine.NORTH);
    public static final SpecialBlock vineSouth = new Vine(Vine.SOUTH);
    public static final SpecialBlock vineEast = new Vine(Vine.EAST);
    public static final SpecialBlock vineWest = new Vine(Vine.WEST);

    public static void initBlocks(){
        GameRegistry.registerBlock(BestMazesBlocks.piselliteBricks, PiselliteBricks.name);
        GameRegistry.registerBlock(BestMazesBlocks.piselliteBricksSlab, PiselliteBricksSlab.name);
        GameRegistry.registerBlock(BestMazesBlocks.mazeLock, MazeLock.name);
    }
}
