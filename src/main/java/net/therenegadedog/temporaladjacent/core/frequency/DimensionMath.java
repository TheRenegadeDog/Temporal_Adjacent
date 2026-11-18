package net.therenegadedog.temporaladjacent.core.frequency;

// The record is a class designed to act as a place to store immutable and unchangable data. The helper method is used to help build it later in other classes, because its public

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Map;

// Proximity helps determine proximity to other dimensions, the closer the number is to a dimension, the more coherant you are to another dimension
// Frequency is a persons latent connection to that dimension. The more you frequent it, the closer you get
// Gravity refers to a specific dimensions gravitational pull on the player. The lower down a dimension is, the higher the gravity

public record DimensionMath(double frequency, double gravity, double proximity) { // <-- Establishing the record

    private static final DimensionMath DEFAULTED_COHERANCE = new DimensionMath(1.0, 1.0, 1.0f);

    private static final Map<ResourceKey<Level>, DimensionMath> COHERANCE = Map.of( // <-- Map uses two variables inside the diamond operator
            Level.OVERWORLD, new DimensionMath(4.0, 9.87, 1), //<-- This gives the values to the dimensions, using the ResourceKey
            Level.NETHER, new DimensionMath(5.0, 13, 0.5),
            Level.END, new DimensionMath(6.0, 5.5, 1.5)
            /* Base Coherance Values for each dimension are:
             * END: 49.5
             * TRANSITION OVERWORLD/NETHER WORLDS: 39.5 * 49.5
             * Overworld: 39.5
            * TRANSITION OVERWORLD/NETHER WORLDS: 32.6 -> 39.4
            * Nether: 32.5
            */
    );

    public static DimensionMath getLevelCoherance(Level level) {
        return COHERANCE.getOrDefault(level.dimension(), DEFAULTED_COHERANCE); //<-- Helper method
    }
}