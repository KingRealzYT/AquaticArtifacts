package org.waterbenders.aquaticartifacts.common.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.entities.PlayerDefendWaterEntity;
import org.waterbenders.aquaticartifacts.common.items.WaterBendingItem;
import org.waterbenders.aquaticartifacts.init.ModEntityTypes;

public class AquaticChestplate extends ArmorItem implements WaterBendingItem {

    public static final float SCAN_RANGE = 2;
    public static final int MAXIMUM_DEFENDERS = 25;

    private int tickCount;

    public AquaticChestplate() {
        super(ModArmorMaterial.AQUATIC, EquipmentSlotType.CHEST, new Item.Properties().tab(AquaticArtifacts.TAB));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        super.onArmorTick(stack, world, player);

        tickCount++;
        if(tickCount >= 2) {

            AxisAlignedBB aabb = new AxisAlignedBB(player.getX() - SCAN_RANGE, player.getY() - SCAN_RANGE, player.getZ() - SCAN_RANGE,
                    player.getX() + SCAN_RANGE, player.getY() + SCAN_RANGE, player.getZ() + SCAN_RANGE);

            if (world.getEntities(ModEntityTypes.PLAYER_DEFEND_WATER_ENTITY.get(), aabb, (entity -> true)).size() < MAXIMUM_DEFENDERS) {
                castSpell(player, () -> {
                    PlayerDefendWaterEntity entity = new PlayerDefendWaterEntity(player);
                    world.addFreshEntity(entity);
                });
            }

            tickCount = 0;
        }
    }

    @Override
    public float manaCost() {
        return 0.05f;
    }
}
