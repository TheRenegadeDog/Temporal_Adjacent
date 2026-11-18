package net.therenegadedog.temporaladjacent.core;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.frequency.FrequencyScannerItem;
import net.therenegadedog.temporaladjacent.core.item.ZeitonWatchItem;

import java.util.concurrent.CompletableFuture;

public class ModItems {
    // This is the list of items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TemporalAdjacent.MOD_ID);

    public static final DeferredItem<Item> UNREFINED_ZEITON = ITEMS.registerSimpleItem("unrefined_zeiton",
            properties -> properties);
    public static final DeferredItem<Item> ZEITON = ITEMS.registerSimpleItem("zeiton",
            properties -> properties);
    public static final DeferredItem<Item> ZEITON_CLOCK = ITEMS.registerItem("zeiton_clock",
            properties -> new ZeitonWatchItem(properties.durability(35)));
    public static final DeferredItem<Item> FREQUENCY_SCANNER = ITEMS.registerItem("frequency_scanner",
            properties -> new FrequencyScannerItem(properties.stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static class ModItemTagProvider extends ItemTagsProvider {
        public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider, TemporalAdjacent.MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider registries) {
            tag(ModTags.Items.ZEITON_WATCH_BLOCKS);


        }
    }
}
