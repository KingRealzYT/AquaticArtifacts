package org.waterbenders.aquaticartifacts;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waterbenders.aquaticartifacts.init.ModBlocks;
import org.waterbenders.aquaticartifacts.init.ModEntityTypes;
import org.waterbenders.aquaticartifacts.init.ModItems;
import org.waterbenders.aquaticartifacts.init.ModTileEntityTypes;
import org.waterbenders.aquaticartifacts.world.gen.ModFeatures;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AquaticArtifacts.MOD_ID)
public class AquaticArtifacts {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "aquatic_artifacts";

    public static final ItemGroup TAB = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return Items.WATER_BUCKET.getDefaultInstance();
        }
    };

    public AquaticArtifacts() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModEntityTypes.ENTITY_TYPES.register(bus);

        ModTileEntityTypes.TILE_ENTITY_TYPES.register(bus);
        ModFeatures.FEATURES.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) { }

}
