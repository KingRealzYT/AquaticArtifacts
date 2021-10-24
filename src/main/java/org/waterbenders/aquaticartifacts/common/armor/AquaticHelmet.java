package org.waterbenders.aquaticartifacts.common.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.items.WaterBendingItem;

public class AquaticHelmet extends ArmorItem implements WaterBendingItem {

    public AquaticHelmet() {
        super(ModArmorMaterial.AQUATIC, EquipmentSlotType.HEAD, new Item.Properties().tab(AquaticArtifacts.TAB));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        super.onArmorTick(stack, world, player);
        if (!world.isClientSide) {
            if (player.isUnderWater()) {
                castSpell(player, () -> player.setAirSupply(player.getMaxAirSupply()));
            }
        }
    }

    @Override
    public float manaCost() {
        return 0.01f;
    }

}
