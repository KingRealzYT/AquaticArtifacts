package org.waterbenders.aquaticartifacts.common.tileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.waterbenders.aquaticartifacts.common.items.WaterOrb;
import org.waterbenders.aquaticartifacts.init.ModTileEntityTypes;

import javax.annotation.Nonnull;

public class OrbInfuserTileEntity extends TileEntity implements ITickableTileEntity {

    public ItemStackHandler itemHandler = createHandler();
    public LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public OrbInfuserTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public OrbInfuserTileEntity() {
        this(ModTileEntityTypes.ORB_INFUSER_TILE_ENTITY_TYPE.get());
    }


    @Override
    public CompoundNBT save(CompoundNBT tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        return super.save(tag);
    }

    @Override
    public void load(BlockState blockState, CompoundNBT tag) {
        tag.put("inv", itemHandler.serializeNBT());
        super.load(blockState, tag);
    }


    @Override
    public SUpdateTileEntityPacket getUpdatePacket(){
        CompoundNBT nbtTag = new CompoundNBT();
        //Write your data into the nbtTag
        nbtTag.put("inv", itemHandler.serializeNBT());
        return new SUpdateTileEntityPacket(this.getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        CompoundNBT tag = pkt.getTag();
        //Handle your Data
        itemHandler.deserializeNBT(tag.getCompound("inv"));
    }


    @Override
    public void tick() {
        System.out.println(this.itemHandler.getStackInSlot(0));
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
