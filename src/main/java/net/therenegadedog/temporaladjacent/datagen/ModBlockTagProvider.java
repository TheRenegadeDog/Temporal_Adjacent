package net.therenegadedog.temporaladjacent.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.ModBlocks;
import net.therenegadedog.temporaladjacent.core.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TemporalAdjacent.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ZEITON_CRYSTAL_BLOCK.get())
                .add(ModBlocks.ZEITON_CRYSTAL_ORE.get())
                .add(ModBlocks.ZEITON_CRYSTAL_DEEPSLATE_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ZEITON_CRYSTAL_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ZEITON_CRYSTAL_DEEPSLATE_ORE.get());

        tag(ModTags.Blocks.ZEITON_WATCH_BLOCKS);
    }
}
