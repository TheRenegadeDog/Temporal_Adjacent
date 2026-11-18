package net.therenegadedog.temporaladjacent.client.renderer.block;

import com.geckolib.model.GeoModel;
import com.geckolib.renderer.GeoBlockRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.therenegadedog.temporaladjacent.client.models.TardisBlockModel;
import net.therenegadedog.temporaladjacent.core.block_entities.TardisBlockEntity;

public class TardisBlockEntityRenderer<R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<TardisBlockEntity, R>  {
    public TardisBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context, new TardisBlockModel());
    }
}
