package com.mauro.bestmazes.handler;

import com.mauro.bestmazes.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.awt.*;
import java.io.File;

/**
 * Created by Gabriele on 6/25/2015.
 */
public class ConfigurationHandler
{
    public static Configuration configuration;
    public static boolean testValue = false;

    public static void init(File configFile)
    {
        if(configuration == null) {
            configuration = new Configuration(configFile);
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID)){
            loadConfiguration();
        }
    }

    public void loadConfiguration()
    {
        try
        {
            testValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "asdf").getBoolean(true);
        }
        catch(Exception e)
        {
        }
        finally
        {
            if(configuration.hasChanged())
                configuration.save();
        }
    }
}
