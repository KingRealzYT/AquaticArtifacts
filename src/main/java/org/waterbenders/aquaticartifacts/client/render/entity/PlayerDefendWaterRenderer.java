package org.waterbenders.aquaticartifacts.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.util.ResourceLocation;
import org.waterbenders.aquaticartifacts.common.entities.PlayerDefendWaterEntity;

public class PlayerDefendWaterRenderer extends EntityRenderer<PlayerDefendWaterEntity> {

    public PlayerDefendWaterRenderer(EntityRendererManager p_i46554_1_) {
        super(p_i46554_1_);
    }

    @Override
    public ResourceLocation getTextureLocation(PlayerDefendWaterEntity p_110775_1_) {
        return AtlasTexture.LOCATION_BLOCKS;
    }
}
