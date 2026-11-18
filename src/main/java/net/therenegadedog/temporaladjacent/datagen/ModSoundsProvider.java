package net.therenegadedog.temporaladjacent.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.ModSounds;

public class ModSoundsProvider extends SoundDefinitionsProvider {

    public ModSoundsProvider(PackOutput output) {
        super(output, TemporalAdjacent.MOD_ID);
    }

    @Override
    public void registerSounds() {
        add(ModSounds.ZEITON_CLOCK_USE.get(), definition().subtitle("sounds.temporal_adjacent.zeiton_clock_use")
                .with(sound(Identifier.fromNamespaceAndPath(TemporalAdjacent.MOD_ID, "zeiton_clock_use"))));
    }
}
