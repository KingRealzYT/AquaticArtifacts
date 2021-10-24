package org.waterbenders.aquaticartifacts.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;

public class WaterOrb extends Item {

    public final int maximumWater;
    public final int waterIncrease;

    public WaterOrb(int maximumWater, int waterIncrease) {
        super(new Item.Properties().tab(AquaticArtifacts.TAB).stacksTo(1));
        this.maximumWater = maximumWater;
        this.waterIncrease = waterIncrease;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockRayTraceResult rayTraceResult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

        if (rayTraceResult.getType() != RayTraceResult.Type.MISS) {
            if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = rayTraceResult.getBlockPos();
                if (!world.mayInteract(player, blockpos)) {
                    return ActionResult.pass(itemStack);
                }
                if (world.getFluidState(blockpos).is(FluidTags.WATER)) {
                    CompoundNBT nbt = itemStack.getOrCreateTagElement(AquaticArtifacts.MOD_ID);
                    float waterAmount = nbt.getFloat("water");
                    if(waterAmount < maximumWater) {
                        float nextWater = Math.max(0, Math.min(waterAmount + waterIncrease, maximumWater));
                        nbt.putFloat("water", nextWater);
                    }
                    return ActionResult.sidedSuccess(itemStack, world.isClientSide);
                }
            }

        }
        return ActionResult.pass(itemStack);
    }
}
