package org.waterbenders.aquaticartifacts.common.tileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.waterbenders.aquaticartifacts.common.items.WaterOrb;
import org.waterbenders.aquaticartifacts.init.ModItems;
import org.waterbenders.aquaticartifacts.init.ModTileEntityTypes;

import javax.annotation.Nonnull;

public class OrbInfuserTileEntity extends TileEntity implements ITickableTileEntity {

    public ItemStackHandler itemHandler = createHandler();
    public LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public boolean ritualRunning;
    public int ritualTickCounter;

    public OrbInfuserTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public OrbInfuserTileEntity() {
        this(ModTileEntityTypes.ORB_INFUSER_TILE_ENTITY_TYPE.get());
    }


    @Override
    public CompoundNBT save(CompoundNBT tag) {
        ritualRunning = tag.getBoolean("ritual_running");
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        return super.save(tag);
    }

    @Override
    public void load(BlockState blockState, CompoundNBT tag) {
        tag.putBoolean("ritual_running", ritualRunning);
        tag.put("inv", itemHandler.serializeNBT());
        super.load(blockState, tag);
    }


    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTag = new CompoundNBT();
        //Write your data into the nbtTag
        nbtTag.putBoolean("ritual_running", ritualRunning);
        nbtTag.put("inv", itemHandler.serializeNBT());
        return new SUpdateTileEntityPacket(this.getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT tag = pkt.getTag();
        //Handle your Data
        ritualRunning = tag.getBoolean("ritual_running");
        itemHandler.deserializeNBT(tag.getCompound("inv"));
    }

    public boolean testRitual() {
        BlockPos pos = this.getBlockPos();
        if (this.level.getBlockState(pos.offset(2, 0, 2)).getBlock() != Blocks.LAPIS_BLOCK) return false;
        if (this.level.getBlockState(pos.offset(2, 0, -2)).getBlock() != Blocks.LAPIS_BLOCK) return false;
        if (this.level.getBlockState(pos.offset(-2, 0, 2)).getBlock() != Blocks.LAPIS_BLOCK) return false;
        if (this.level.getBlockState(pos.offset(-2, 0, -2)).getBlock() != Blocks.LAPIS_BLOCK) return false;
        if (this.level.getBlockState(pos.offset(2, 1, 2)).getBlock() != Blocks.LAPIS_BLOCK) return false;
        if (this.level.getBlockState(pos.offset(2, 1, -2)).getBlock() != Blocks.LAPIS_BLOCK) return false;
        if (this.level.getBlockState(pos.offset(-2, 1, 2)).getBlock() != Blocks.LAPIS_BLOCK) return false;
        if (this.level.getBlockState(pos.offset(-2, 1, -2)).getBlock() != Blocks.LAPIS_BLOCK) return false;

        if (this.level.getBlockState(pos.offset(-1, -1, 1)).getBlock() != Blocks.WATER) return false;
        if (this.level.getBlockState(pos.offset(-1, -1, 0)).getBlock() != Blocks.WATER) return false;
        if (this.level.getBlockState(pos.offset(-1, -1, -1)).getBlock() != Blocks.WATER) return false;

        if (this.level.getBlockState(pos.offset(0, -1, 1)).getBlock() != Blocks.WATER) return false;
        if (this.level.getBlockState(pos.offset(0, -1, -1)).getBlock() != Blocks.WATER) return false;

        if (this.level.getBlockState(pos.offset(1, -1, 1)).getBlock() != Blocks.WATER) return false;
        if (this.level.getBlockState(pos.offset(1, -1, 0)).getBlock() != Blocks.WATER) return false;
        if (this.level.getBlockState(pos.offset(1, -1, -1)).getBlock() != Blocks.WATER) return false;

        Item item = this.itemHandler.getStackInSlot(0).getItem();
        return item instanceof WaterOrb && item != ModItems.WATER_ORB_4.get();
    }


    public void activateRitual() {
        if(testRitual()){
            ritualRunning = true;
            ritualTickCounter = 0;
        }
    }

    @Override
    public void tick() {
        if (ritualRunning) {
            ritualTickCounter++;
            if (!testRitual()) {
                ritualRunning = false;
                return;
            }

            BlockPos pos = this.getBlockPos();

            for(int i = 0; i < 10; i++) {
                double d0 = Math.random() - 0.5f;
                double d1 = Math.random() - 0.5f;
                double d2 = Math.random() - 0.5f;

                this.level.addParticle(ParticleTypes.BUBBLE, pos.getX() + 0.5f, pos.getY() + 1.8, pos.getZ() + 0.5f, d0, d1, d2);
            }


            if(ritualTickCounter == 60){
                spawnLightningAndRemove(pos.offset(2, 1, 2));
                spawnLightningAndRemove(pos.offset(2, 1, -2));
                spawnLightningAndRemove(pos.offset(-2, 1, 2));
                spawnLightningAndRemove(pos.offset(-2, 1, -2));

                Item item = this.itemHandler.getStackInSlot(0).getItem();
                if(item == ModItems.WATER_ORB_1.get()) spawnOrb(ModItems.WATER_ORB_2.get());
                if(item == ModItems.WATER_ORB_2.get()) spawnOrb(ModItems.WATER_ORB_3.get());
                if(item == ModItems.WATER_ORB_3.get()) spawnOrb(ModItems.WATER_ORB_4.get());

                this.itemHandler.setStackInSlot(0, ItemStack.EMPTY);
                this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                ritualRunning = false;
            }
        }
    }

    private void spawnLightningAndRemove(BlockPos pos){
        this.level.removeBlock(pos, true);
        this.level.removeBlock(pos.offset(0, -1, 0), true);
        LightningBoltEntity entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, this.level);
        entity.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
        entity.setVisualOnly(true);
        this.level.addFreshEntity(entity);
    }

    private void spawnOrb(Item item){
        BlockPos pos = this.getBlockPos();
        ItemEntity itemEntity = new ItemEntity(this.level, pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, new ItemStack(item));
        itemEntity.setDeltaMovement(0, 0, 0);
        this.level.addFreshEntity(itemEntity);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() instanceof WaterOrb;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap);
    }
}
