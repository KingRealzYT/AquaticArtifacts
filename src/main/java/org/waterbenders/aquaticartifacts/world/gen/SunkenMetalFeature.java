package org.waterbenders.aquaticartifacts.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;
import org.waterbenders.aquaticartifacts.init.ModBlocks;

import java.util.List;
import java.util.Random;

public class SunkenMetalFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState SUNKEN_METAL = ModBlocks.SUNKEN_METAL_BLOCK.get().defaultBlockState();
    private static final List<BlockState> placeOn = NonNullList.of(Blocks.GRAVEL.defaultBlockState(), Blocks.SAND.defaultBlockState(), Blocks.STONE.defaultBlockState());

    public SunkenMetalFeature(Codec<NoFeatureConfig> p_i231953_1_) {
        super(p_i231953_1_);
    }

    @Override
    public boolean place(ISeedReader seedReader, ChunkGenerator chunkGenerator, Random rand, BlockPos startPosition, NoFeatureConfig config)
    {
        startPosition = new BlockPos(startPosition.getX(), seedReader.getSeaLevel(), startPosition.getZ());
        int depth = 0;

        // Moving down until it is on the ground
        while (startPosition.getY() > 1 && (isAir(seedReader, startPosition) || seedReader.getFluidState(startPosition).is(FluidTags.WATER)))
        {
            startPosition = startPosition.below();
            if (seedReader.getFluidState(startPosition).is(FluidTags.WATER))
                depth++;
        }

        startPosition = startPosition.above();

        if (depth < 10)
        {
            return false; // it should generate in 10 blocks deep or more water.
        }

        if (!placeOn.contains(seedReader.getBlockState(startPosition.below())))
        {
            return false; // to detect for a good ground to generate it on
        }

        setBlock(seedReader, startPosition, SUNKEN_METAL);
        return true;
    }
}
