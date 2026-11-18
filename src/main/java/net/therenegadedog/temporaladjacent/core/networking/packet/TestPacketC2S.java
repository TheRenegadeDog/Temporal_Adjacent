package net.therenegadedog.temporaladjacent.core.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;

// Custom packet payload, aka data
public record TestPacketC2S(String name, int value) implements CustomPacketPayload {
    public static final Type<TestPacketC2S> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TemporalAdjacent.MOD_ID, "test_packet"));
    //STREAM CODECS. NEEDS TWO GENERICS to be used inside the network.
    // giving it a template to convert from one to another
    public static final StreamCodec<RegistryFriendlyByteBuf, TestPacketC2S> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            TestPacketC2S::name,

            // These always come in pairs
            ByteBufCodecs.VAR_INT,
            TestPacketC2S::value,

            TestPacketC2S::new // <-- this makes the new packet constructor
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
