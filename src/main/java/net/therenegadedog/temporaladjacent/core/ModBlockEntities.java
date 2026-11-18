package net.therenegadedog.temporaladjacent.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.block_entities.TardisBlockEntity;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TemporalAdjacent.MOD_ID);

    public static final Supplier<BlockEntityType<TardisBlockEntity>> TARDIS_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(
            "tardis_block_entity",
            // The block entity type.
            () -> new BlockEntityType<>(
                    // The supplier to use for constructing the block entity instances.
                    TardisBlockEntity::new,
                    // An optional value that, when true, only allows players with OP permissions
                    // to load NBT data (e.g. placing a block item)
                    false,
                    // A vararg of blocks that can have this block entity.
                    // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                    ModBlocks.TARDIS.get()
            ));
    public static void register(IEventBus eventBus) {BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
