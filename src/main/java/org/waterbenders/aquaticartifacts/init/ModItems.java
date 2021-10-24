package org.waterbenders.aquaticartifacts.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.armor.AquaticBoots;
import org.waterbenders.aquaticartifacts.common.armor.AquaticChestplate;
import org.waterbenders.aquaticartifacts.common.armor.AquaticHelmet;
import org.waterbenders.aquaticartifacts.common.armor.AquaticLeggings;
import org.waterbenders.aquaticartifacts.common.blocks.BlockItemBase;
import org.waterbenders.aquaticartifacts.common.items.*;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AquaticArtifacts.MOD_ID);

    // Wands
    public static final RegistryObject<Item> WATER_BALL_WAND = ITEMS.register("water_ball_wand", WaterBallWand::new);

    // Water Orbs
    public static final RegistryObject<Item> INFUSION_KEY = ITEMS.register("infusion_key", () -> new Item(new Item.Properties().stacksTo(1).tab(AquaticArtifacts.TAB)));
    public static final RegistryObject<Item> WATER_ORB_1 = ITEMS.register("water_orb_1", () -> new WaterOrb(250, 1));
    public static final RegistryObject<Item> WATER_ORB_2 = ITEMS.register("water_orb_2", () -> new WaterOrb(500, 3));
    public static final RegistryObject<Item> WATER_ORB_3 = ITEMS.register("water_orb_3", () -> new WaterOrb(1000, 6));
    public static final RegistryObject<Item> WATER_ORB_4 = ITEMS.register("water_orb_4", () -> new WaterOrb(2000, 10));

    // Ingots
    public static final RegistryObject<Item> SUNKEN_METAL_INGOT = ITEMS.register("sunken_metal_ingot", SunkenMetalIngot::new);

    // Block Items
    public static final RegistryObject<Item> SUNKEN_METAL_BLOCK_ITEM = ITEMS.register("sunken_metal_block",
            () -> new BlockItemBase(ModBlocks.SUNKEN_METAL_BLOCK.get()));

    public static final RegistryObject<Item> ORB_INFUSER = ITEMS.register("orb_infuser",
            () -> new BlockItemBase(ModBlocks.ORB_INFUSER.get()));


    // Water Shard it is shard made from water related things:
    public static final RegistryObject<Item> WATER_SHARD = ITEMS.register("water_shard", WaterShard::new);

    //Armor
    public static final RegistryObject<Item> AQUATIC_HELMET = ITEMS.register("aquatic_helmet", AquaticHelmet::new);
    public static final RegistryObject<Item> AQUATIC_CHESTPLATE = ITEMS.register("aquatic_chestplate", AquaticChestplate::new);
    public static final RegistryObject<Item> AQUATIC_LEGGINGS = ITEMS.register("aquatic_leggings", AquaticLeggings::new);
    public static final RegistryObject<Item> AQUATIC_BOOTS = ITEMS.register("aquatic_boots", AquaticBoots::new);

    // Boat Item this is an item that can summon boat when you right click
    public static final RegistryObject<Item> HEAVY_BOAT = ITEMS.register("heavy_boat", HeavyBoatItem::new);
}
