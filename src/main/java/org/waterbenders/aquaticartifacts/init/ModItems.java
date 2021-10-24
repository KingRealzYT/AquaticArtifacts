package org.waterbenders.aquaticartifacts.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.blocks.BlockItemBase;
import org.waterbenders.aquaticartifacts.common.items.SunkenMetalIngot;
import org.waterbenders.aquaticartifacts.common.items.WaterBallWand;
import org.waterbenders.aquaticartifacts.common.items.WaterOrb;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AquaticArtifacts.MOD_ID);

    // Wands
    public static final RegistryObject<Item> WATER_BALL_WAND = ITEMS.register("water_ball_wand", WaterBallWand::new);

    // Water Orbs
    public static final RegistryObject<Item> WATER_ORB = ITEMS.register("water_orb", WaterOrb::new);

    // Ingots
    public static final RegistryObject<Item> SUNKEN_METAL_INGOT = ITEMS.register("sunken_metal_ingot", SunkenMetalIngot::new);

    // Block Items
    public static final RegistryObject<Item> SUNKEN_METAL_BLOCK_ITEM = ITEMS.register("sunken_metal_block",
            () -> new BlockItemBase(ModBlocks.SUNKEN_METAL_BLOCK.get()));

}
