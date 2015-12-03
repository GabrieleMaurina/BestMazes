package com.gabrielemaurina.bestmazesbygabrielemaurina.chunkproviders;

import com.gabrielemaurina.bestmazesbygabrielemaurina.biomegenbases.MazeBiome;
import com.gabrielemaurina.bestmazesbygabrielemaurina.blocks.BestMazesBlocks;
import com.gabrielemaurina.bestmazesbygabrielemaurina.utility.Drawer;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;
import java.util.Random;

/**
 * Created by Gabriele on 11/30/2015.
 */
public class MazeChunkProvider implements IChunkProvider
{
    private Random rand;
    private World worldObj;
    public static final String name = "mazeChunkProvider";
    private BiomeGenBase mazeBiome = new MazeBiome();

    private static final long INIT_RAND_1 = 341873128712L;
    private static final long INIT_RAND_2 = 132897987541L;
    private static final int CHUNK_SIZE = 16 * 16 * 128;

    public MazeChunkProvider(World world, long seed) {
        this.worldObj = world;
        this.rand = new Random(seed);
    }

    @Override
    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }
    @Override
    public Chunk provideChunk(int x, int z)
    {
        Block[] blocks = new Block[CHUNK_SIZE];
        /*Block[][][] model = new Block[16][1][16];
        Drawer.fillParallelepipedon1(model, 0, 0, 0, 16, 1, 16, BestMazesBlocks.piselliteBricks);
        createModel(blocks, model, 0, 100, 0);*/

        Chunk chunk = new Chunk(this.worldObj, blocks, x, z);
        chunk.generateSkylightMap();


        chunk.setBiomeArray(getBiomes());
        return chunk;
    }

    private byte[] getBiomes(){
        byte[] biomes = new byte[256];
        for(int i = 0; i < 256; i++){
            biomes[i] = (byte)mazeBiome.biomeID;
        }
        return biomes;
    }

    private static void setBlock(Block[] blocks, int x, int y, int z, Block block){
        int index = 0;
        index += x * 16 * 128;
        index += z * 128;
        index += y;
        blocks[index] = block;
    }

    private static void createModel(Block[] blocks, Block[][][] model, int x, int y, int z){
        for(int i = 0; i < model.length; i++){
            for(int e = 0; e < model[i].length; e++){
                for(int o = 0; o < model[i][e].length; o++){
                    setBlock(blocks, x + i, y + e, z + o, model[i][e][o]);
                }
            }
        }
    }

    private void initRandom(int x, int z){
        rand.setSeed(x * INIT_RAND_1 + z * INIT_RAND_2);
    }

    @Override
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }
    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
    return true;
    }
    @Override
    public boolean canSave()
    {
        return true;
    }
    @Override
    public String makeString()
    {
        return name;
    }
    @Override
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) { return null;}
    @Override
    public int getLoadedChunkCount() {
        return 0;
    }
    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }
    @Override
    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_){return null;}
    @Override
    public void recreateStructures(int i, int j){}
    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3){}
    @Override
    public void saveExtraData(){}
}