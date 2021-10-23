package org.waterbenders.aquaticartifacts.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraftforge.fml.common.Mod;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.items.WaterBallWand;
import org.waterbenders.aquaticartifacts.init.ModItems;

@Mod.EventBusSubscriber(modid = AquaticArtifacts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

    public static final ResourceLocation WATER_BAR_BACKGROUND_LOCATION = new ResourceLocation(AquaticArtifacts.MOD_ID, "textures/screens/water_bar_background.png");
    public static final ResourceLocation WATER_BAR_LOCATION = new ResourceLocation(AquaticArtifacts.MOD_ID, "textures/screens/water_bar.png");


    @SubscribeEvent
    public static void renderWorldLast(RenderWorldLastEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        World world = player.level;
        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() == ModItems.WATER_BALL_WAND.get()) {
            CompoundNBT nbt = itemStack.getOrCreateTagElement(AquaticArtifacts.MOD_ID);
            if (nbt.getBoolean("has_water")) {
                Vector3d playerEyePosition = new Vector3d(player.getX(), player.getEyeY(), player.getZ());
                int sphereDistance = nbt.getInt("sphere_distance");
                int sphereRadius = nbt.getInt("sphere_radius");
                BlockPos hitPos = new BlockPos(playerEyePosition.add(player.getLookAngle().multiply(sphereDistance, sphereDistance, sphereDistance)));

                for (BlockPos pos : WaterBallWand.getSphere(hitPos, sphereRadius)) {
                    //Minecraft.getInstance().getBlockRenderer().renderBlock(Blocks.WATER.defaultBlockState(), event.getMatrixStack(), );
                }
            }
        }
    }

    @SubscribeEvent
    public static void renderGameOverlay(RenderGameOverlayEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        for(ItemStack stack : player.inventory.items) {
            if(!stack.isEmpty() && stack.getItem() == ModItems.WATER_ORB.get()){
                CompoundNBT nbt = stack.getOrCreateTagElement(AquaticArtifacts.MOD_ID);
                int waterAmount = nbt.getInt("water");
                int barSize = (int) (waterAmount / 100f * 132);

                Minecraft.getInstance().textureManager.bind(WATER_BAR_BACKGROUND_LOCATION);
                AbstractGui.blit(event.getMatrixStack(), 25, 300, 0, 0, 132, 16, 132, 16);

                Minecraft.getInstance().textureManager.bind(WATER_BAR_LOCATION);
                AbstractGui.blit(event.getMatrixStack(), 25, 300, 0, 0, barSize, 16, 132, 16);
                break;
            }
        }
    }
}
