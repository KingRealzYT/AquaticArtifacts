package org.waterbenders.aquaticartifacts.common.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.network.IPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.waterbenders.aquaticartifacts.init.ModEntityTypes;
import org.waterbenders.aquaticartifacts.init.ModItems;


public class HeavyBoat extends BoatEntity
{
    public HeavyBoat(World world) {
        super(ModEntityTypes.HEAVY_BOAT.get(), world);
        this.blocksBuilding = true;
    }

    public HeavyBoat(EntityType<? extends BoatEntity> entityEntityType, World world)
    {
        super(entityEntityType, world);
        this.blocksBuilding = true;
    }

    public HeavyBoat(World world, double x, double y, double z)
    {
        super(ModEntityTypes.HEAVY_BOAT.get(), world);
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos)
    {
        this.lastYd = this.getDeltaMovement().y;
        if (!this.isPassenger()) {
            if (onGroundIn) {
                if (this.fallDistance > 3.0F) {
                    if (this.status != HeavyBoat.Status.ON_LAND) {
                        this.fallDistance = 0.0F;
                        return;
                    }

                    this.causeFallDamage(this.fallDistance, 1.0F);
                    if (!this.level.isClientSide && this.isAlive()) {
                        this.kill();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            for (int i = 0; i < 4; ++i) {
                                this.spawnAtLocation(ModItems.SUNKEN_METAL_INGOT.get());
                            }
                        }
                    }
                }

                this.fallDistance = 0.0F;
            } else if (!this.level.getFluidState((new BlockPos(this.position())).below()).is(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance = (float) ((double) this.fallDistance - y);
            }
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick()
    {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().add(0d, -0.1d, 0d));
    }
}