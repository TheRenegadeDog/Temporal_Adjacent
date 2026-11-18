package net.therenegadedog.temporaladjacent.client.event_subscribers;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.client.renderer.block.TardisBlockEntityRenderer;
import net.therenegadedog.temporaladjacent.core.block_entities.TardisBlockEntity;

@EventBusSubscriber(modid = TemporalAdjacent.MOD_ID, value = Dist.CLIENT)
public class ModClientRenderers {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TardisBlockEntity.TYPE.get(), TardisBlockEntityRenderer::new);
    }
}
