package net.therenegadedog.temporaladjacent.core.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.GameProtocols;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.therenegadedog.temporaladjacent.core.ModSounds;

import javax.naming.Context;

public class ArtronResonanceCollectorBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

    public ArtronResonanceCollectorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, false)); //<-- This sets the default state when placed down
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (level instanceof ServerLevel serverLevel) {
            level.setBlock(pos, state.cycle(CLICKED), Block.UPDATE_ALL); //<-- This will cycle through true or false. The Update all will indicate that it updates its neighbor and client

            serverLevel.playSound(null, pos.getX(),pos.getY(),pos.getZ(), ModSounds.ZEITON_CLOCK_USE.get(), SoundSource.MASTER);
        }
        return InteractionResult.SUCCESS;
    }
    // Lets you add the properties you specified
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLICKED);
    }
}
