package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.custom.WarturtleEntity;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.item.custom.WarturtleArmorItem;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Map;

public class WarturtleArmorFeatureRenderer extends FeatureRenderer<WarturtleEntity, WarturtleModel<WarturtleEntity>> {
    private final WarturtleModel<WarturtleEntity> model;
    private Map<Item, Identifier> ARMOR_MAP = Map.of(
            ModItems.IRON_WARTURTLE_ARMOR, Identifier.of(MCCourseMod.MOD_ID, "textures/entity/warturtle/armor/iron_warturtle.png"),
            ModItems.GOLD_WARTURTLE_ARMOR, Identifier.of(MCCourseMod.MOD_ID, "textures/entity/warturtle/armor/gold_warturtle.png"),
            ModItems.DIAMOND_WARTURTLE_ARMOR, Identifier.of(MCCourseMod.MOD_ID, "textures/entity/warturtle/armor/diamond_warturtle.png"),
            ModItems.NETHERITE_WARTURTLE_ARMOR, Identifier.of(MCCourseMod.MOD_ID, "textures/entity/warturtle/armor/netherite_warturtle.png"),
            ModItems.FLUORITE_WARTURTLE_ARMOR, Identifier.of(MCCourseMod.MOD_ID, "textures/entity/warturtle/armor/fluorite_warturtle.png")
    );

    public WarturtleArmorFeatureRenderer(FeatureRendererContext<WarturtleEntity, WarturtleModel<WarturtleEntity>> context, EntityModelLoader loader) {
        super(context);
        model = new WarturtleModel<>(loader.getModelPart(ModEntityModelLayers.WARTURTLE_ARMOR));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, WarturtleEntity entity, float limbSwing, float limbSwingAmount,
                       float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.hasArmorOn()) {
            ItemStack itemStack = entity.getBodyArmor();
            if (itemStack.getItem() instanceof WarturtleArmorItem armorItem) {
                (this.getContextModel()).copyStateTo(this.model);
                this.model.animateModel(entity, limbSwing, limbSwingAmount, partialTick);
                this.model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(ARMOR_MAP.get(armorItem)));
                this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            }
        }
    }
}
