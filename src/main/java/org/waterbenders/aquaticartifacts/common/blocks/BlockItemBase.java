package org.waterbenders.aquaticartifacts.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;

public class BlockItemBase extends BlockItem {
    // Feel free to edit this, just here so it will be put into tab beans

    public BlockItemBase(Block block) {
        super(block, new Item.Properties().tab(AquaticArtifacts.TAB));
    }
}
