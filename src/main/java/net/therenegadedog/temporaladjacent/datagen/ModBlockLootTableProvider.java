package net.therenegadedog.temporaladjacent.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.therenegadedog.temporaladjacent.core.ModBlocks;
import net.therenegadedog.temporaladjacent.core.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ZEITON_CRYSTAL_BLOCK.get());
        dropSelf(ModBlocks.TARDIS.get());
        add(ModBlocks.ZEITON_CRYSTAL_ORE.get(), block -> createMultipleOreDrops(block, ModItems.UNREFINED_ZEITON.get(),1 ,2));
        add(ModBlocks.ZEITON_CRYSTAL_DEEPSLATE_ORE.get(), block -> createMultipleOreDrops(block, ModItems.UNREFINED_ZEITON.get(), 3, 5));
        dropSelf(ModBlocks.ARTRON_RESONANCE_COLLECTOR.get());
        dropSelf(ModBlocks.CREATIVE_ARTRON_RESONANCE_COLLECTOR.get());
    }
    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    // This finds the blocks we make. If we do not make this, this will try and force a loot for all vanilla blocks
    // Converts blocks into a list and makes it an iterable
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
