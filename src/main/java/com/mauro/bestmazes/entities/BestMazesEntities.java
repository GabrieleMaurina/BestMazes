package com.mauro.bestmazes.entities;

import com.mauro.bestmazes.BestMazes;
import com.mauro.bestmazes.entities.minotaurs.ClassicMinotaur;
import com.mauro.bestmazes.entities.minotaurs.Minotaur;
import com.mauro.bestmazes.utility.dungeon.DungeonReferences;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;

import java.util.Random;

/**
 * Created by Gabriele on 10/28/2015.
 */
public class BestMazesEntities {

    public static void initEntities(){
        registerEntity(ClassicMinotaur.class, ClassicMinotaur.name);
    }

    public static void registerEntity(Class entityClass, String name)
    {
        int entityID = EntityRegistry.findGlobalUniqueEntityId();
        long seed = name.hashCode();
        Random rand = new Random(seed);
        int primaryColor = rand.nextInt() * 16777215;
        int secondaryColor = rand.nextInt() * 16777215;

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
        EntityRegistry.registerModEntity(entityClass, name, entityID, BestMazes.instance, 64, 1, true);
        EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
    }
}
