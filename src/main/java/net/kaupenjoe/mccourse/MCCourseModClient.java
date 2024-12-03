package net.kaupenjoe.mccourse;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.entity.client.*;
import net.kaupenjoe.mccourse.screen.ModScreenHandlers;
import net.kaupenjoe.mccourse.screen.custom.WarturtleScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
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

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.WARTURTLE, WarturtleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WARTURTLE, WarturtleRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.TOMAHAWK, TomahawkProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.TOMAHAWK, TomahawkProjectileRenderer::new);

        HandledScreens.register(ModScreenHandlers.WARTURTLE_SCREEN_HANDLER, WarturtleScreen::new);
    }
}
