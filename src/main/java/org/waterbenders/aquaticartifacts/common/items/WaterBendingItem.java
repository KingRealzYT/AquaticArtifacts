package org.waterbenders.aquaticartifacts.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidAttributes;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.init.ModItems;

public interface WaterBendingItem {

    float manaCost();

    default float manaAmount(PlayerEntity player) {
        for (ItemStack stack : player.inventory.items) {
            if (!stack.isEmpty() && stack.getItem() instanceof WaterOrb) {
                CompoundNBT nbt = stack.getOrCreateTagElement(AquaticArtifacts.MOD_ID);
                return nbt.getFloat("water");
            }
        }
        return 0;
    }

    default ItemStack getManaOrb(PlayerEntity player){
        for (ItemStack stack : player.inventory.items) {
            if (!stack.isEmpty() && stack.getItem() instanceof WaterOrb) {
                return stack;
            }
        }
        return null;
    }

    default boolean castSpell(PlayerEntity player, Runnable runnable){
        if(manaAmount(player) < manaCost()){
            return false;
        } else {
            CompoundNBT nbt = getManaOrb(player).getOrCreateTagElement(AquaticArtifacts.MOD_ID);
            nbt.putFloat("water", nbt.getFloat("water") - manaCost());
            runnable.run();
            return true;
        }
    }

}
