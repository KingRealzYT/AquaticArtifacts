package org.waterbenders.aquaticartifacts.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Constants;
import org.waterbenders.aquaticartifacts.common.tileEntities.OrbInfuserTileEntity;

import javax.annotation.Nullable;

public class OrbInfuser extends Block {

    public OrbInfuser() {
        super(AbstractBlock.Properties.of(Material.STONE)
        .strength(30f)
        .sound(SoundType.STONE)
        .harvestTool(ToolType.PICKAXE)
        .requiresCorrectToolForDrops());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new OrbInfuserTileEntity();
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
        if(!world.isClientSide){
            TileEntity te = world.getBlockEntity(pos);
            if(te instanceof OrbInfuserTileEntity){
                ((OrbInfuserTileEntity) te).itemHandler.insertItem(0, player.getItemInHand(hand), false);
                world.sendBlockUpdated(pos, state, state, Constants.BlockFlags.BLOCK_UPDATE);
            }
        }

        return super.use(state, world, pos, player, hand, ray);
    }
}
