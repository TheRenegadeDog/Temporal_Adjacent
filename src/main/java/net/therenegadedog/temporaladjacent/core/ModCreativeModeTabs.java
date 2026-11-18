package net.therenegadedog.temporaladjacent.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.energy.ArtronResonanceCollectorBlock;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TemporalAdjacent.MOD_ID);

    public static final Supplier<CreativeModeTab> SPACE_ORES_TAB = CREATIVE_MODE_TABS.register("space_ores_tabs",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ZEITON.get()))
                    .title(Component.translatable("creativetab.temporaladjacent.space_ores_tabs"))
                    .withSearchBar()
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.UNREFINED_ZEITON);
                        output.accept(ModItems.ZEITON);
                    })
                    .build());
    public static final Supplier<CreativeModeTab> SPACE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("space_blocks_tabs",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.ZEITON_CRYSTAL_BLOCK.get()))
                    .title(Component.translatable("creativetab.temporaladjacent.space_blocks_tabs"))
                    .withSearchBar()
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ZEITON_CRYSTAL_BLOCK);
                        output.accept(ModBlocks.ZEITON_CRYSTAL_ORE);
                        output.accept(ModBlocks.ZEITON_CRYSTAL_DEEPSLATE_ORE);
                    })
                    .build());
    public static final Supplier<CreativeModeTab> TARDIS = CREATIVE_MODE_TABS.register("tardis",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ZEITON_CLOCK.get()))
                    .title(Component.translatable("creativetab.temporaladjacent.tardis"))
                    .withSearchBar()
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ZEITON_CLOCK);
                        output.accept(ModBlocks.ARTRON_RESONANCE_COLLECTOR);
                        output.accept(ModBlocks.CREATIVE_ARTRON_RESONANCE_COLLECTOR);
                        output.accept(ModItems.FREQUENCY_SCANNER);
                        output.accept(ModBlocks.TARDIS);
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
