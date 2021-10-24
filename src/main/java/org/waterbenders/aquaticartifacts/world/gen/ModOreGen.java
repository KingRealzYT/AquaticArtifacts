package org.waterbenders.aquaticartifacts.world.gen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.waterbenders.aquaticartifacts.init.ModBlocks;

public class ModOreGen {
    private static final int veinSize = 2;
    private static final int maxHeight = 30;
    private static final int minHeight = 23;
    private static final int veinsPerChunk = 10;

    public static void generateOres(BiomeLoadingEvent event) {
        if (!(event.getCategory().equals(Biome.Category.NETHER) && event.getCategory().equals(Biome.Category.THEEND))) {
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.configured(
                                    new OreFeatureConfig(
                                            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                            ModBlocks.SUNKEN_METAL_BLOCK.get().defaultBlockState(),
                                            veinSize))
                            .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight))).squared().count(veinsPerChunk)
            );
        }
    }
}
