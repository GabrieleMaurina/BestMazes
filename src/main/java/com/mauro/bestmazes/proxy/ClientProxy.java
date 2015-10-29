package com.mauro.bestmazes.proxy;

import com.mauro.bestmazes.client.Renders.MinotaurRender;
import com.mauro.bestmazes.entities.minotaurs.ClassicMinotaur;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBiped;

/**
 * Created by Gabriele on 6/25/2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(ClassicMinotaur.class, new MinotaurRender(new ModelBiped(), 0.5F));
    }
}