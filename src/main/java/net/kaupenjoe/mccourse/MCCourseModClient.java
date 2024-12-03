package net.kaupenjoe.mccourse;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.entity.client.*;
import net.minecraft.client.render.RenderLayer;

public class MCCourseModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_TRAPDOOR, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.DODO, DodoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DODO, DodoRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.GIRAFFE, GiraffeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.GIRAFFE, GiraffeRenderer::new);
    }
}
