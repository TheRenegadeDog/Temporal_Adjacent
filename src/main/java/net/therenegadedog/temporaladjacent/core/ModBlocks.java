package net.therenegadedog.temporaladjacent.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.blocks.TardisBlock;
import net.therenegadedog.temporaladjacent.core.energy.ArtronResonanceCollectorBlock;
import net.therenegadedog.temporaladjacent.core.energy.CreativeArtronResonanceCollectorBlock;

import java.util.function.Function;

/* Important Notes */
// Private means it can only be called in this class and no other
// static means we don't have to initialize it
// T will have to extend from the block class.

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(TemporalAdjacent.MOD_ID);

    public static final DeferredBlock<Block> ZEITON_CRYSTAL_BLOCK = registerBlock("zeiton_crystal_block",
            properties -> new Block(properties
                    .destroyTime(1f)
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .lightLevel(value -> 4)));
    public static final DeferredBlock<Block> ZEITON_CRYSTAL_ORE = registerBlock("zeiton_crystal_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2,4), properties
                    .destroyTime(2f)
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .sound(SoundType.STONE)
                    .lightLevel(value -> 2)));
    public static final DeferredBlock<Block> ZEITON_CRYSTAL_DEEPSLATE_ORE = registerBlock("zeiton_crystal_deepslate_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3,5), properties
                    .destroyTime(3f)
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .sound(SoundType.DEEPSLATE)
                    .lightLevel(value -> 4)));
    public static final DeferredBlock<Block> ARTRON_RESONANCE_COLLECTOR = registerBlock("artron_resonance_collector",
            properties -> new ArtronResonanceCollectorBlock(properties
                    .destroyTime(3f)
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .sound(SoundType.METAL)
                    .lightLevel(state -> state.getValue(ArtronResonanceCollectorBlock.CLICKED) ? 15 : 0))); // <-- This indicates that when CLICKED is true, it will produce a light level of 15, and 0 if false
    public static final DeferredBlock<Block> CREATIVE_ARTRON_RESONANCE_COLLECTOR = registerBlock("creative_artron_resonance_collector",
            properties -> new CreativeArtronResonanceCollectorBlock(properties
                    .destroyTime(3f)
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .sound(SoundType.METAL)
                    .lightLevel(state -> state.getValue(CreativeArtronResonanceCollectorBlock.CLICKED) ? 15 : 0)));
    public static final DeferredBlock<Block> TARDIS = registerBlock("tardis", TardisBlock::new);
    // <-- This indicates that when CLICKED is true, it will produce a light level of 15, and 0 if false

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static<T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
