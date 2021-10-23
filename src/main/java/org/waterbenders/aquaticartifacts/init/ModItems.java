package org.waterbenders.aquaticartifacts.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.items.WaterBallWand;
import org.waterbenders.aquaticartifacts.common.items.WaterOrb;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AquaticArtifacts.MOD_ID);

    public static final RegistryObject<Item> WATER_BALL_WAND = ITEMS.register("water_ball_wand", WaterBallWand::new);

    public static final RegistryObject<Item> WATER_ORB = ITEMS.register("water_orb", WaterOrb::new);
}
