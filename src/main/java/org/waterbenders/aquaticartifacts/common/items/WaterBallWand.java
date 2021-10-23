package org.waterbenders.aquaticartifacts.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WaterBallWand extends Item implements WaterBendingItem {

    public static final int TRACE_RANGE = 50;
    public static final int SUCK_RADIUS = 2;

    public WaterBallWand() {
        super(new Item.Properties().tab(AquaticArtifacts.TAB));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.startUsingItem(hand);

        Vector3d playerEyePosition = new Vector3d(player.getX(), player.getEyeY(), player.getZ());
        ItemStack stack = player.getItemInHand(hand);
        CompoundNBT tag = stack.getOrCreateTagElement(AquaticArtifacts.MOD_ID);

        if (tag.getBoolean("has_water")) {
//            int sphereRadius = tag.getInt("sphere_radius");
//            int sphereDistance = tag.getInt("sphere_distance");


//            //REMOVE OLD WATER
//            BlockPos oldHitPos = BlockPos.of(tag.getLong("last_hit_pos"));
//            for (BlockPos pos : getSphere(oldHitPos, sphereRadius)) {
//                if (world.getBlockState(pos).getBlock() == Blocks.WATER) {
//                    world.removeBlock(pos, true);
//                }
//            }
//
//            //SET NEW WATER
//            BlockPos hitPos = new BlockPos(playerEyePosition.add(player.getLookAngle().multiply(sphereDistance, sphereDistance, sphereDistance)));
//            tag.putLong("last_hit_pos", hitPos.asLong());
//
//            for (BlockPos pos : getSphere(hitPos, sphereRadius)) {
//                if (world.getBlockState(pos).isAir()) {
//                    world.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
//                }
//            }
        } else
            for (int i = 0; i < TRACE_RANGE; i++) {
                BlockPos hitPos = new BlockPos(playerEyePosition.add(player.getLookAngle().multiply(i, i, i)));
                if (world.getBlockState(hitPos).getBlock() == Blocks.WATER) {
                    int waterAmount = 0;

                    for (BlockPos pos : getSphere(hitPos, SUCK_RADIUS)) {
                        if (world.getBlockState(pos).getBlock() == Blocks.WATER) {
                            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                            waterAmount++;
                        }
                    }

                    int sphereRadius = (int) Math.pow(waterAmount * 0.75f / Math.PI, 1f / 3f) + 1;
                    tag.putInt("sphere_distance", i);
                    tag.putInt("sphere_radius", sphereRadius);
                    tag.putBoolean("has_water", true);
                }
            }

        return super.use(world, player, hand);
    }


    public static List<BlockPos> getSphere(BlockPos start, int radius) {
        List<BlockPos> blocks = new ArrayList<>();
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    if (x * x + y * y + z * z < radius * radius) {
                        BlockPos pos = start.offset(x, y, z);
                        blocks.add(pos);
                    }
                }
            }
        }
        return blocks;
    }

    @Override
    public void releaseUsing(ItemStack itemStack, World world, LivingEntity livingEntity, int p_77615_4_) {
        itemStack.removeTagKey(AquaticArtifacts.MOD_ID);
        super.releaseUsing(itemStack, world, livingEntity, p_77615_4_);
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }
}
