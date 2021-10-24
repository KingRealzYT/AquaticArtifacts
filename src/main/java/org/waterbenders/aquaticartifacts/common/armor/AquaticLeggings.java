package org.waterbenders.aquaticartifacts.common.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;

public class AquaticLeggings extends ArmorItem {

    public AquaticLeggings() {
        super(ModArmorMaterial.AQUATIC, EquipmentSlotType.LEGS, new Item.Properties().tab(AquaticArtifacts.TAB));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        super.onArmorTick(stack, world, player);
        if (player.isUnderWater()) {
            player.setSwimming(false);
            player.setDeltaMovement(player.getDeltaMovement().add(0, -0.1, 0));
        }
    }
}