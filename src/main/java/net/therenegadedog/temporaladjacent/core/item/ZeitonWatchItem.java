package net.therenegadedog.temporaladjacent.core.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.therenegadedog.temporaladjacent.core.ModDataComponentTypes;
import net.therenegadedog.temporaladjacent.core.ModSounds;

import java.util.Map;
import java.util.function.Consumer;

public class ZeitonWatchItem extends Item {
    private static final Map<Block, Block> ZEITONCLOCK_MAP =
            Map.of(
                    // Cobblestone
                    Blocks.MOSSY_COBBLESTONE, Blocks.COBBLESTONE,
                    Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.COBBLESTONE_SLAB,
                    Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.COBBLESTONE_STAIRS,
                    Blocks.MOSSY_COBBLESTONE_WALL, Blocks.COBBLESTONE_WALL,
                    // Stone
                    Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.STONE_BRICK_SLAB,
                    Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.STONE_BRICK_STAIRS,
                    Blocks.MOSSY_STONE_BRICK_WALL, Blocks.STONE_BRICK_WALL,
                    Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.STONE_BRICKS,
                    // ANVIL
                    Blocks.DAMAGED_ANVIL, Blocks.CHIPPED_ANVIL,
                    Blocks.CHIPPED_ANVIL, Blocks.ANVIL

            );
    public ZeitonWatchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel(); // <-- this is a generic level class
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        Block clickedBlock = level.getBlockState(pos).getBlock(); // Make this only once, making more is redundant
        Direction yaw = context.getClickedFace();

        if(!ZEITONCLOCK_MAP.containsKey(clickedBlock)) return InteractionResult.PASS;
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.setBlockAndUpdate(pos, ZEITONCLOCK_MAP.get(clickedBlock).defaultBlockState());
            context.getItemInHand().hurtAndBreak(1, serverLevel, player,
                    item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            context.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(), context.getClickedPos()); // <-- This saves coordinates of clicked block onto item
                    serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,pos.getX(),pos.getY(),pos.getZ(),25,.1,.2,.3,0.01);
                    serverLevel.playSound(null, pos.getX(),pos.getY(),pos.getZ(), ModSounds.ZEITON_CLOCK_USE.get(), SoundSource.MASTER);
        }
        return InteractionResult.SUCCESS; // Adds right click hand animation
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.nullToEmpty("This item can revert blocks back to their prime state!"));
        } else {
            builder.accept(Component.nullToEmpty("Press for more information!"));
        }
        if(itemStack.get(ModDataComponentTypes.COORDINATES) != null) {
            builder.accept(Component.literal("Watch used at: " + itemStack.get((ModDataComponentTypes.COORDINATES))).withStyle(style -> style.withColor(TextColor.fromRgb(0xffff37))));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}

