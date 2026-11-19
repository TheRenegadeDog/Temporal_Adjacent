package net.therenegadedog.temporaladjacent.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.therenegadedog.temporaladjacent.core.ModBlockEntities;
import org.jspecify.annotations.Nullable;

public class TardisBlock extends Block implements EntityBlock {
    public TardisBlock(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return ModBlockEntities.TARDIS_BLOCK_ENTITY_TYPE.get().create(worldPosition, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }
}