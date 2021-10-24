package org.waterbenders.aquaticartifacts.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.init.ModBlocks;

import java.util.List;

public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, AquaticArtifacts.MOD_ID);

    public static final RegistryObject<SunkenMetalFeature> SUNKEN_METAL = FEATURES.register("sunken_metal", () -> new SunkenMetalFeature(NoFeatureConfig.CODEC));
    public static final Lazy<ConfiguredFeature<?, ?>> CONFIGURED_SUNKEN_METAL= Lazy.of(() -> Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(AquaticArtifacts.MOD_ID, "sunken_metal"), SUNKEN_METAL.get().configured(IFeatureConfig.NONE).squared().decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.4F, 3)))));

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class FeatureEvents
    {
        @SubscribeEvent
        public static void generateOres(BiomeLoadingEvent event) {
            if ((event.getCategory().equals(Biome.Category.OCEAN))) {
                event.getGeneration().addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, CONFIGURED_SUNKEN_METAL.get());
                System.out.println("Added sunken metal to " + event.getName());
            }
        }
    }
}
