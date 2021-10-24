package org.waterbenders.aquaticartifacts.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.entities.HeavyBoat;
import org.waterbenders.aquaticartifacts.common.entities.PlayerDefendWaterEntity;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES  = DeferredRegister.create(ForgeRegistries.ENTITIES, AquaticArtifacts.MOD_ID);

    public static final RegistryObject<EntityType<PlayerDefendWaterEntity>> PLAYER_DEFEND_WATER_ENTITY = ENTITY_TYPES.register("player_defend_water",
            () -> EntityType.Builder.<PlayerDefendWaterEntity>of(PlayerDefendWaterEntity::new, EntityClassification.AMBIENT)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(AquaticArtifacts.MOD_ID, "player_defend_water").toString()));

    public static final EntityType<HeavyBoat> TYPE_HEAVY_BOAT = EntityType.Builder.of((EntityType.IFactory<HeavyBoat>) HeavyBoat::new, EntityClassification.MISC).sized(1.375F, 0.5625F).build("aquatic_artifacts:heavy_boat");
    public static final RegistryObject<EntityType<HeavyBoat>> HEAVY_BOAT = ENTITY_TYPES.register("heavy_boat", () -> TYPE_HEAVY_BOAT);

}
