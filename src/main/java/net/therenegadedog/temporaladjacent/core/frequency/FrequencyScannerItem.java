
package net.therenegadedog.temporaladjacent.core.frequency;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.therenegadedog.temporaladjacent.core.ModSounds;
// PROXIMITY CALCULATIONS
public class FrequencyScannerItem extends Item {
// PROXIMITY CALCULATIONS
            public static double overworldProximity(double y) {
                if (y < -63) return 0.5;
                if (y < -49) return 0.6;
                if (y < -38) return 0.7;
                if (y < -27) return 0.8;
                if (y < 0) return 0.9;
                if (y < 101) return 1.0;
                return 1.5;
            }

            public static double netherProximity(double y) {
                if (y < -63) return -0.5;
                if (y < -49) return -0.4;
                if (y < -38) return -0.3;
                if (y < -27) return -0.2;
                if (y < 0) return -0.1;
                if (y < 101) return 0.5;
                return 1.0;
            }

            public static double endProximity(double y) {
                if (y < -63) return 1.5;
                if (y < -49) return 1.6;
                if (y < -38) return 1.7;
                if (y < -27) return 1.8;
                if (y < 0) return 1.9;
                if (y < 101) return 2.0;
                return 2.5;
            }
            public static double getProximity(Player player) {
                ResourceKey<Level> dimension = player.level().dimension();
                double y = player.getY();

                if (dimension == Level.OVERWORLD) return overworldProximity(y);
                if (dimension == Level.NETHER) return netherProximity(y);
                if (dimension == Level.END) return endProximity(y);

                return 1.0;

            }
            public FrequencyScannerItem(Properties properties) {

                super(properties);
            }
            @Override
            public InteractionResult useOn(UseOnContext context) {
                Level level = context.getLevel();
                BlockPos pos = context.getClickedPos();
                Player player = context.getPlayer();
                double currentProximity = 0;
                DimensionMath coherance = DimensionMath.getLevelCoherance(level);
                double y = player.getY();
                double currentFrequency;
                double currentGravity;
                double currentCoherance = 0;
                if (!(level instanceof ServerLevel serverLevel)) {
                    return InteractionResult.PASS;
                }
                if (player == null) {
                    return InteractionResult.PASS;
                }
                if (player.level().dimension() == Level.OVERWORLD){
                   currentProximity = overworldProximity(y);
                } else if(player.level().dimension() == Level.NETHER) {
                    currentProximity = netherProximity(y);
                } else if(player.level().dimension() == Level.END) {
                    currentProximity = endProximity(y);
                }
                currentGravity = coherance.gravity();
                currentFrequency = coherance.frequency();
                // Calculate Current Coherance
                currentCoherance = currentFrequency * currentGravity * currentProximity;
                // Display Data
                player.sendOverlayMessage(
                        Component.literal("Current Coherence is " + currentCoherance)
                                .withStyle(style ->
                                        style.withColor(TextColor.fromRgb(0xffff37))
                                )

                );
                serverLevel.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.ZEITON_CLOCK_USE.get(), SoundSource.MASTER);
        return InteractionResult.SUCCESS;
    }
}