package net.therenegadedog.temporaladjacent.client.models;

import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.minecraft.resources.Identifier;
import net.therenegadedog.temporaladjacent.TemporalAdjacent;
import net.therenegadedog.temporaladjacent.core.block_entities.TardisBlockEntity;

public class TardisBlockModel extends GeoModel<TardisBlockEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(TemporalAdjacent.MOD_ID, "tardis_exterior");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.fromNamespaceAndPath(TemporalAdjacent.MOD_ID, "textures/block_entity/tardis_exterior.png");
    }

    @Override
    public Identifier getAnimationResource(TardisBlockEntity animatable) {
        return Identifier.fromNamespaceAndPath(TemporalAdjacent.MOD_ID, "tardis_exterior");
    }
}
