package org.waterbenders.aquaticartifacts.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.armor.AquaticBoots;
import org.waterbenders.aquaticartifacts.common.armor.AquaticChestplate;
import org.waterbenders.aquaticartifacts.common.armor.AquaticHelmet;
import org.waterbenders.aquaticartifacts.common.armor.AquaticLeggings;
import org.waterbenders.aquaticartifacts.common.items.WaterBallWand;
import org.waterbenders.aquaticartifacts.common.items.WaterDefenderWand;
import org.waterbenders.aquaticartifacts.common.items.WaterOrb;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AquaticArtifacts.MOD_ID);

    public static final RegistryObject<Item> WATER_BALL_WAND = ITEMS.register("water_ball_wand", WaterBallWand::new);
    public static final RegistryObject<Item> WATER_ORB = ITEMS.register("water_orb", () -> new WaterOrb(250));
    public static final RegistryObject<Item> WATER_DEFENDER_WAND = ITEMS.register("water_defender_wand", WaterDefenderWand::new);
    public static final RegistryObject<Item> AQUATIC_CHESTPLATE = ITEMS.register("aquatic_chestplate", AquaticChestplate::new);
    public static final RegistryObject<Item> AQUATIC_HELMET = ITEMS.register("aquatic_helmet", AquaticHelmet::new);
    public static final RegistryObject<Item> AQUATIC_LEGGINGS = ITEMS.register("aquatic_leggings", AquaticLeggings::new);
    public static final RegistryObject<Item> AQUATIC_BOOTS = ITEMS.register("aquatic_boots", AquaticBoots::new);
}
