package org.waterbenders.aquaticartifacts.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SunkenMetalBlock extends Block {

    public SunkenMetalBlock() {
        super(Properties.of(Material.HEAVY_METAL)
                .strength(40.0F, 8000.0F)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2));
    }
}
