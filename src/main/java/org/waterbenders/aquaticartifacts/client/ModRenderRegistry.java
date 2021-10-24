package org.waterbenders.aquaticartifacts.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.waterbenders.aquaticartifacts.init.ModEntities;

@OnlyIn(Dist.CLIENT)
public class ModRenderRegistry
{
    public static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HEAVY_BOAT.get(), HeavyBoatRender::new);
    }
}
