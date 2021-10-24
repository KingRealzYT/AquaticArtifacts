package org.waterbenders.aquaticartifacts.common.entities;

import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.waterbenders.aquaticartifacts.init.ModEntityTypes;

public class PlayerDefendWaterEntity extends Entity {

    public static final double ATTACK_RANGE = 7.5D;

    public PlayerEntity owner;
    public Entity target;
    public int tickCounter;

    public PlayerDefendWaterEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    public PlayerDefendWaterEntity(PlayerEntity owner) {
        super(ModEntityTypes.PLAYER_DEFEND_WATER_ENTITY.get(), owner.level);
        this.owner = owner;
        this.tickCounter = (int) (Math.random() * 100f);
        this.setPos(owner.getX(), owner.getEyeY(), owner.getZ());
        setFlyingPosition();
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide) {
            if (owner == null || target == owner) {
                this.remove();
                return;
            }

            AxisAlignedBB aabb = new AxisAlignedBB(this.getX() - ATTACK_RANGE, this.getY() - 1, this.getZ() - ATTACK_RANGE,
                    this.getX() + ATTACK_RANGE, this.getY() + 3, this.getZ() + ATTACK_RANGE);
            this.target = this.level.getNearestEntity(MonsterEntity.class, new EntityPredicate(), owner, this.getX(), this.getY(), this.getZ(), aabb);

            if (target == null) {
                if (++tickCounter > 1000) {
                    tickCounter = 0;
                }
                setFlyingPosition();
            } else {
                double xVel = target.getX() - this.getX();
                double yVel = target.getY() + 1 - this.getY();
                double zVel = target.getZ() - this.getZ();
                this.setDeltaMovement(new Vector3d(xVel, yVel, zVel).normalize().multiply(0.2f, 0.2f, 0.2f));

                Vector3d mov = this.getDeltaMovement();
                double d2 = this.getX() + mov.x;
                double d0 = this.getY() + mov.y;
                double d1 = this.getZ() + mov.z;
                this.setPos(d2, d0, d1);

                RayTraceResult rayTraceResult = ProjectileHelper.getHitResult(this, (entity) -> true);
                if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
                    //this.remove();
                }
                if(rayTraceResult.getType() == RayTraceResult.Type.ENTITY){
                    Entity hitEntity = ((EntityRayTraceResult) rayTraceResult).getEntity();
                    hitEntity.hurt(DamageSource.GENERIC, 1f);
                    hitEntity.invulnerableTime = 0;
                    if(hitEntity instanceof LivingEntity) {
                        ((LivingEntity) hitEntity).knockback(1, 0, 0);
                    }
                    this.remove();
                }

            }
        }

        this.level.addParticle(ParticleTypes.BUBBLE, this.getX(), this.getY() + 0.1, this.getZ(), 0, 0, 0);
    }

    private void setFlyingPosition(){
        double a = tickCounter / 10f;
        double x = owner.getX() + Math.cos(a) * 1.25f;
        double y = owner.getY() + 0.75f + Math.sin(a * 3) * 0.25f;
        double z = owner.getZ() + Math.sin(a) * 1.25f;
        this.setPos(x, y, z);
    }


    @Override
    public EntitySize getDimensions(Pose p_213305_1_) {
        return new EntitySize(0.4f, 0.4f, true);
    }
}