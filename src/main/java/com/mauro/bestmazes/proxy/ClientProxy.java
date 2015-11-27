package com.mauro.bestmazes.proxy;

import com.mauro.bestmazes.blocks.BestMazesBlocks;
import com.mauro.bestmazes.client.models.MinotaurModel;
import com.mauro.bestmazes.client.renders.InventoryMazeLockRender;
import com.mauro.bestmazes.client.renders.MazeLockRender;
import com.mauro.bestmazes.client.renders.MinotaurRender;
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
        RenderingRegistry.registerEntityRenderingHandler(ForestMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, ForestMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(BirchMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, BirchMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(RoofedMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, RoofedMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(DesertMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, DesertMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(JungleMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, JungleMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(ExtremeMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, ExtremeMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(NetherMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, NetherMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(IceMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, IceMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(TaigaMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, TaigaMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(OceanMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, OceanMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(PlainMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, PlainMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(SwampMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, SwampMinotaur.type));
        RenderingRegistry.registerEntityRenderingHandler(SavannaMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F, SavannaMinotaur.type));

        MazeLockRender render = new MazeLockRender();
        ClientRegistry.bindTileEntitySpecialRenderer(MazeLockTileEntity.class, render);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BestMazesBlocks.mazeLock), new InventoryMazeLockRender(render, new MazeLockTileEntity()));
    }
}