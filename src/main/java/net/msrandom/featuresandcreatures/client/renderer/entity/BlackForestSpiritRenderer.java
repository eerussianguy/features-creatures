package net.msrandom.featuresandcreatures.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.msrandom.featuresandcreatures.client.model.BlackForestSpiritModel;
import net.msrandom.featuresandcreatures.entity.BlackForestSpirit;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BlackForestSpiritRenderer extends GeoEntityRenderer<BlackForestSpirit> {

    public BlackForestSpiritRenderer(EntityRendererProvider.Context dispatcher) {
        super(dispatcher, new BlackForestSpiritModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(BlackForestSpirit p_110775_1_) {
        return BlackForestSpiritModel.TEXTURE;
    }

}