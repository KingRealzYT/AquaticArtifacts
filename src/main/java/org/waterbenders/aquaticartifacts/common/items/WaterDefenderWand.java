package org.waterbenders.aquaticartifacts.common.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.waterbenders.aquaticartifacts.AquaticArtifacts;
import org.waterbenders.aquaticartifacts.common.entities.PlayerDefendWaterEntity;

public class WaterDefenderWand extends Item {

    public WaterDefenderWand() {
        super(new Item.Properties().tab(AquaticArtifacts.TAB));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        PlayerDefendWaterEntity entity = new PlayerDefendWaterEntity(player);
        world.addFreshEntity(entity);


        return ActionResult.success(player.getItemInHand(hand));
    }
}
