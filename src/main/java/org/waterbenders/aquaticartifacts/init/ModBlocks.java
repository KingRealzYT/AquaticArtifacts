package org.waterbenders.aquaticartifacts.init;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.blocks.SunkenMetalBlock;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AquaticArtifacts.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> SUNKEN_METAL_BLOCK = BLOCKS.register("sunken_metal_block", SunkenMetalBlock::new);

}
