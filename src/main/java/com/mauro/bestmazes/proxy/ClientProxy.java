package com.mauro.bestmazes.proxy;

import com.mauro.bestmazes.client.Renders.MinotaurRender;
import com.mauro.bestmazes.client.models.MinotaurModel;
import com.mauro.bestmazes.entities.minotaurs.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBiped;

/**
 * Created by Gabriele on 6/25/2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(ClassicMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(CrazyMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(DarkMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(DesertMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(ExpandedMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(ExtremeMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(HellMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(IceMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(NarrowMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(OceanMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(PlainMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(SwampMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(WideMinotaur.class, new MinotaurRender(new MinotaurModel(), 0.5F));
    }
}