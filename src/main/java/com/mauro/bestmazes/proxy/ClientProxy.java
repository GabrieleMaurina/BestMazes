package com.mauro.bestmazes.proxy;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.client.renders.InventoryMazeLockRender;
import com.mauro.bestmazes.client.renders.MazeLockRender;
import com.mauro.bestmazes.client.renders.MinotaurRender;
import com.mauro.bestmazes.client.models.MinotaurModel;
import com.mauro.bestmazes.entities.minotaurs.*;
import com.mauro.bestmazes.tileEntities.MazeLockTileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by Gabriele on 6/25/2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(ForestMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(BirchMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(RoofedMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(DesertMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(JungleMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(ExtremeMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(NetherMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(IceMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(TaigaMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(OceanMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(PlainMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(SwampMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(SavannaMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));

        MazeLockRender render = new MazeLockRender();
        ClientRegistry.bindTileEntitySpecialRenderer(MazeLockTileEntity.class, render);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BestMazesBlocks.mazeLock), new InventoryMazeLockRender(render, new MazeLockTileEntity()));
    }
}