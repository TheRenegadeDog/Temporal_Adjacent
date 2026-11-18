package net.therenegadedog.temporaladjacent.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.ModBlocks;
import net.therenegadedog.temporaladjacent.core.ModItems;
import net.therenegadedog.temporaladjacent.core.energy.ArtronResonanceCollectorBlock;
import net.therenegadedog.temporaladjacent.core.energy.CreativeArtronResonanceCollectorBlock;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, TemporalAdjacent.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.UNREFINED_ZEITON.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ZEITON.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ZEITON_CLOCK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.declareCustomModelItem(ModItems.FREQUENCY_SCANNER.get());

        //BLOCKS
        blockModels.createTrivialCube(ModBlocks.ZEITON_CRYSTAL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ZEITON_CRYSTAL_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZEITON_CRYSTAL_DEEPSLATE_ORE.get());

        // BLOCK ENTITIES
        blockModels.createNonTemplateModelBlock(ModBlocks.TARDIS.get());

        blockModels.blockStateOutput.accept( // <-- This tells the generator to generate two different json files for on and off, one has the suffix with on and the other has it off.
                MultiVariantGenerator.dispatch(ModBlocks.ARTRON_RESONANCE_COLLECTOR.get())
                        .with(BlockModelGenerators.createBooleanModelDispatch(ArtronResonanceCollectorBlock.CLICKED,
                                BlockModelGenerators.plainVariant(blockModels.createSuffixedVariant(ModBlocks.ARTRON_RESONANCE_COLLECTOR.get(), "on", ModelTemplates.CUBE_ALL, TextureMapping::cube)),
                                BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(ModBlocks.ARTRON_RESONANCE_COLLECTOR.get(), blockModels.modelOutput)))));

        blockModels.blockStateOutput.accept( // <-- This tells the generator to generate two different json files for on and off, one has the suffix with on and the other has it off.
                MultiVariantGenerator.dispatch(ModBlocks.CREATIVE_ARTRON_RESONANCE_COLLECTOR.get())
                        .with(BlockModelGenerators.createBooleanModelDispatch(CreativeArtronResonanceCollectorBlock.CLICKED,
                                BlockModelGenerators.plainVariant(blockModels.createSuffixedVariant(ModBlocks.CREATIVE_ARTRON_RESONANCE_COLLECTOR.get(), "on", ModelTemplates.CUBE_ALL, TextureMapping::cube)),
                                BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(ModBlocks.CREATIVE_ARTRON_RESONANCE_COLLECTOR.get(), blockModels.modelOutput)))));
    }
}
