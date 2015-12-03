package com.gabrielemaurina.bestmazesbygabrielemaurina.dimensions;

import com.gabrielemaurina.bestmazesbygabrielemaurina.worldproviders.MazeWorldProvider;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by Gabriele on 12/3/2015.
 */
public class BestMazesDimensions {
    public static void initDimesions(){
        int dimensionId = DimensionManager.getNextFreeDimId();
        DimensionManager.registerProviderType(dimensionId, MazeWorldProvider.class, true);
        DimensionManager.registerDimension(dimensionId, dimensionId);
        MazeWorldProvider.dimensionId = dimensionId;
    }
}
