package net.kaupenjoe.mccourse.entity.client;

import com.google.common.collect.Maps;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.custom.DodoEntity;
import net.kaupenjoe.mccourse.entity.variant.DodoVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class DodoRenderer extends MobEntityRenderer<DodoEntity, DodoModel> {
    private static final Map<DodoVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DodoVariant.class), map -> {
                map.put(DodoVariant.BLUE, Identifier.of(MCCourseMod.MOD_ID, "textures/entity/dodo/dodo_blue.png"));
                map.put(DodoVariant.BLACK, Identifier.of(MCCourseMod.MOD_ID, "textures/entity/dodo/dodo_black.png"));
            });

    public DodoRenderer(EntityRendererFactory.Context context) {
        super(context, new DodoModel(context.getPart(ModEntityModelLayers.DODO)), 0.5f);
    }

    @Override
    public Identifier getTexture(DodoEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant()); // Identifier.of(MCCourseMod.MOD_ID, "textures/entity/dodo/dodo_blue.png");
    }

    @Override
    public void render(DodoEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
