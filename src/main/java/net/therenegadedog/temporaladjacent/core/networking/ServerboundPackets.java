package net.therenegadedog.temporaladjacent.core.networking;

// These will be on the server

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.therenegadedog.temporaladjacent.core.networking.packet.TestPacketC2S;

public class ServerboundPackets {

    // When a packet arrives on the server the handleTestPacket gets called.
    public static void handleTestPacket(TestPacketC2S testPacket, IPayloadContext context) {
        Player player = context.player();
        ServerLevel level = ((ServerLevel) player.level());
        EntityType.SHEEP.spawn(level, player.getOnPos(), EntitySpawnReason.TRIGGERED);
        player.sendOverlayMessage(Component.literal(testPacket.name() + "has just said " + testPacket.value()));
    }
}
