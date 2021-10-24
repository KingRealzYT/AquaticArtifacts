package org.waterbenders.aquaticartifacts.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.entities.HeavyBoat;
import net.minecraft.entity.Entity;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, AquaticArtifacts.MOD_ID);

    public static final EntityType<HeavyBoat> TYPE_HEAVY_BOAT = EntityType.Builder.of((EntityType.IFactory<HeavyBoat>) HeavyBoat::new, EntityClassification.MISC).sized(1.0F, 0.5F).build("aquatic_artifacts:heavy_boat");
    public static final RegistryObject<EntityType<HeavyBoat>> HEAVY_BOAT = ENTITIES.register("heavy_boat", () -> TYPE_HEAVY_BOAT);
}
