package com.mauro.bestmazes.proxy;

import com.mauro.bestmazes.client.Renders.MinotaurRender;
import com.mauro.bestmazes.client.models.MinotaurModel;
import com.mauro.bestmazes.entities.minotaurs.*;
import cpw.mods.fml.client.registry.RenderingRegistry;

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
    }
}