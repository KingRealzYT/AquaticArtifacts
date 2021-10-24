package org.waterbenders.aquaticartifacts.common.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.items.WaterBendingItem;
import org.waterbenders.aquaticartifacts.init.ModItems;

import java.util.UUID;

public class AquaticBoots extends ArmorItem implements WaterBendingItem {

    public static final UUID uuid = UUID.fromString("fe2de708-34cb-11ec-8d3d-0242ac130003");
    private final Lazy<Multimap<Attribute, AttributeModifier>> defaultModifiers;

    public AquaticBoots() {
        super(ModArmorMaterial.AQUATIC, EquipmentSlotType.FEET, new Item.Properties().tab(AquaticArtifacts.TAB));

        defaultModifiers = Lazy.of(() -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "Swim modifier", 2.0D, AttributeModifier.Operation.MULTIPLY_TOTAL));
            return builder.build();
        });
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        super.onArmorTick(stack, world, player);
        if (player.isUnderWater()) {
          castSpell(player, () -> {});
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        return slot == EquipmentSlotType.FEET ? defaultModifiers.get() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public float manaCost() {
        return 0.01f;
    }
}
