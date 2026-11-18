package net.therenegadedog.temporaladjacent.core.block_entities;

import com.geckolib.animatable.GeoBlockEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.therenegadedog.temporaladjacent.core.ModBlockEntities;

import java.util.function.Supplier;

public class TardisBlockEntity extends BlockEntity implements GeoBlockEntity{
    public static Supplier<BlockEntityType<TardisBlockEntity>> TYPE;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public TardisBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(TYPE.get(), worldPosition, blockState);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}