package org.waterbenders.aquaticartifacts.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.tileEntities.OrbInfuserTileEntity;

public class ModTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, AquaticArtifacts.MOD_ID);

    public static final RegistryObject<TileEntityType<OrbInfuserTileEntity>> ORB_INFUSER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("orb_infuser_tile_entity",
            () -> TileEntityType.Builder.of(OrbInfuserTileEntity::new, ModBlocks.ORB_INFUSER.get()).build(null));

}
