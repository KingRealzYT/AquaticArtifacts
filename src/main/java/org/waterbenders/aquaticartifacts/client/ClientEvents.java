package org.waterbenders.aquaticartifacts.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.client.render.entity.PlayerDefendWaterRenderer;
import org.waterbenders.aquaticartifacts.client.render.tile_entity.OrbInfuserTileEntityRenderer;
import org.waterbenders.aquaticartifacts.common.items.WaterBallWand;
import org.waterbenders.aquaticartifacts.common.items.WaterOrb;
import org.waterbenders.aquaticartifacts.init.ModEntityTypes;
import org.waterbenders.aquaticartifacts.init.ModItems;
import org.waterbenders.aquaticartifacts.init.ModTileEntityTypes;

public class ClientEvents {

    public static final ResourceLocation WATER_BAR_BACKGROUND_LOCATION = new ResourceLocation(AquaticArtifacts.MOD_ID, "textures/screens/water_bar_background.png");
    public static final ResourceLocation WATER_BAR_LOCATION = new ResourceLocation(AquaticArtifacts.MOD_ID, "textures/screens/water_bar.png");

    @Mod.EventBusSubscriber(modid = AquaticArtifacts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void renderWorldLast(RenderWorldLastEvent event) {
//            PlayerEntity player = Minecraft.getInstance().player;
//            World world = player.level;
//            ItemStack itemStack = player.getMainHandItem();
//            if (itemStack.getItem() == ModItems.WATER_BALL_WAND.get()) {
//                CompoundNBT nbt = itemStack.getOrCreateTagElement(AquaticArtifacts.MOD_ID);
//                if (nbt.getBoolean("has_water")) {
//                    Vector3d playerEyePosition = new Vector3d(player.getX(), player.getEyeY(), player.getZ());
//                    int sphereDistance = nbt.getInt("sphere_distance");
//                    int sphereRadius = nbt.getInt("sphere_radius");
//                    BlockPos hitPos = new BlockPos(playerEyePosition.add(player.getLookAngle().multiply(sphereDistance, sphereDistance, sphereDistance)));
//
//                    for (BlockPos pos : WaterBallWand.getSphere(hitPos, sphereRadius)) {
//                        //Minecraft.getInstance().getBlockRenderer().renderBlock(Blocks.WATER.defaultBlockState(), event.getMatrixStack(), );
//                    }
//                }
//            }
        }

        @SubscribeEvent
        public static void renderGameOverlay(RenderGameOverlayEvent.Post event) {
            if(event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                PlayerEntity player = Minecraft.getInstance().player;
                for (ItemStack stack : player.inventory.items) {
                    if (!stack.isEmpty() && stack.getItem() == ModItems.WATER_ORB.get()) {
                        //float wMult = event.getWindow().getWidth() / 1920f;
                        //float hMult = event.getWindow().getHeight() / 1080f;

                        WaterOrb waterOrbItem = (WaterOrb) stack.getItem();
                        CompoundNBT nbt = stack.getOrCreateTagElement(AquaticArtifacts.MOD_ID);
                        float waterAmount = nbt.getFloat("water");
                        int barSize = (int) (waterAmount / waterOrbItem.maximumWater * 132);
                        //int barWidth = (int) (132 * wMult);
                        //int barHeight = (int) (16 * hMult);
                        int barWidth = 132, barHeight = 16;

                        Minecraft.getInstance().textureManager.bind(WATER_BAR_BACKGROUND_LOCATION);
                        AbstractGui.blit(event.getMatrixStack(), 25, 325, 0, 0, barWidth, barHeight, barWidth, barHeight);

                        Minecraft.getInstance().textureManager.bind(WATER_BAR_LOCATION);
                        AbstractGui.blit(event.getMatrixStack(), 25, 325, 0, 0, barSize, barHeight, barWidth, barHeight);

                        String text = (int) waterAmount + " / " + waterOrbItem.maximumWater;
                        AbstractGui.drawCenteredString(event.getMatrixStack(), Minecraft.getInstance().font, text, 100, 329, 0xFFFFFFFF);
                        break;
                    }
                }
            }
        }
    }

    @Mod.EventBusSubscriber(modid = AquaticArtifacts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModEvents {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PLAYER_DEFEND_WATER_ENTITY.get(), PlayerDefendWaterRenderer::new);

            ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.ORB_INFUSER_TILE_ENTITY_TYPE.get(), OrbInfuserTileEntityRenderer::new);
        }
    }
}
