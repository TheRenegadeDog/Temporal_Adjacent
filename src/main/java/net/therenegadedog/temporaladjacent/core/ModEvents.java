package net.therenegadedog.temporaladjacent.core;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.networking.ServerboundPackets;
import net.therenegadedog.temporaladjacent.core.networking.packet.TestPacketC2S;

@EventBusSubscriber(modid = TemporalAdjacent.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(TestPacketC2S.TYPE, TestPacketC2S.STREAM_CODEC, ServerboundPackets::handleTestPacket);



    }
}
