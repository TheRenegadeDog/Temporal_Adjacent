package net.therenegadedog.temporaladjacent.core;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> ZEITON_WATCH_BLOCKS = createTag("zeiton_watch_blocks");


        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(TemporalAdjacent.MOD_ID, name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> ZEITON_WATCH_BLOCKS = createTag("zeiton_watch_blocks");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(TemporalAdjacent.MOD_ID, name));
        }
    }
}
