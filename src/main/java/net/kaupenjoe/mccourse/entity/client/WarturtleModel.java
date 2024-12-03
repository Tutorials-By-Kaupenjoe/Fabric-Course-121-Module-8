package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.entity.client.animation.WarturtleAnimations;
import net.kaupenjoe.mccourse.entity.custom.WarturtleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class WarturtleModel<T extends WarturtleEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart tier1;
    private final ModelPart tier2;
    private final ModelPart tier3;
    private final ModelPart head;

    public WarturtleModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("torso").getChild("head");

        this.tier1 = body.getChild("torso").getChild("chests").getChild("tier1");
        this.tier2 = body.getChild("torso").getChild("chests").getChild("tier2");
        this.tier3 = body.getChild("torso").getChild("chests").getChild("tier3");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 31).cuboid(-11.0F, -23.0F, -12.0F, 22.0F, 17.0F, 28.0F, new Dilation(0.0F))
                .uv(3, 99).cuboid(-9.0F, -26.0F, -8.0F, 18.0F, 3.0F, 21.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData blanky = torso.addChild("blanky", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -23.0F, 13.0F));

        ModelPartData blanky_r1 = blanky.addChild("blanky_r1", ModelPartBuilder.create().uv(43, 97).cuboid(-10.0F, -3.0F, -3.0F, 20.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData chests = torso.addChild("chests", ModelPartBuilder.create(), ModelTransform.pivot(12.0F, -25.0F, 2.0F));

        ModelPartData tier1 = chests.addChild("tier1", ModelPartBuilder.create(), ModelTransform.pivot(-12.0F, 13.0F, -1.0F));

        ModelPartData chest_r1 = tier1.addChild("chest_r1", ModelPartBuilder.create().uv(2, 27).mirrored().cuboid(-4.0F, -3.0F, 0.0F, 6.0F, 6.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-13.0F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData chest_r2 = tier1.addChild("chest_r2", ModelPartBuilder.create().uv(2, 27).cuboid(-2.0F, -3.0F, 0.0F, 6.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData tier2 = chests.addChild("tier2", ModelPartBuilder.create(), ModelTransform.pivot(-12.0F, 7.0F, 7.0F));

        ModelPartData chest_r3 = tier2.addChild("chest_r3", ModelPartBuilder.create().uv(3, 47).mirrored().cuboid(-5.0F, -4.0F, 1.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(10.0F, 1.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData chest_r4 = tier2.addChild("chest_r4", ModelPartBuilder.create().uv(3, 47).mirrored().cuboid(-5.0F, -4.0F, 1.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-15.0F, 1.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData tier3 = chests.addChild("tier3", ModelPartBuilder.create(), ModelTransform.pivot(-12.0F, 6.0F, -7.5F));

        ModelPartData chest_r5 = tier3.addChild("chest_r5", ModelPartBuilder.create().uv(3, 37).mirrored().cuboid(-4.0F, -4.0F, 1.0F, 7.0F, 6.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-15.0F, 1.0F, -0.5F, 0.0F, 1.5708F, 0.0F));

        ModelPartData chest_r6 = tier3.addChild("chest_r6", ModelPartBuilder.create().uv(3, 37).mirrored().cuboid(-4.0F, -4.0F, 1.0F, 7.0F, 6.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(10.0F, 1.0F, -0.5F, 0.0F, 1.5708F, 0.0F));

        ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(22, 8).cuboid(-4.5F, -6.5F, -12.0F, 9.0F, 10.0F, 13.0F, new Dilation(0.0F))
                .uv(72, 31).cuboid(-4.5F, -0.5F, -12.0F, 9.0F, 4.0F, 9.0F, new Dilation(-0.001F)), ModelTransform.pivot(0.0F, -12.5F, -12.0F));

        ModelPartData helmet = head.addChild("helmet", ModelPartBuilder.create().uv(91, 67).cuboid(-4.5F, 1.0F, -8.5F, 9.0F, 4.0F, 9.0F, new Dilation(0.3F))
                .uv(72, 35).cuboid(-0.5F, -1.6F, -5.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.3F))
                .uv(78, 79).cuboid(4.8F, 5.3F, -9.2F, 0.0F, 7.0F, 10.0F, new Dilation(0.0F))
                .uv(78, 79).mirrored().cuboid(-4.8F, 5.3F, -9.2F, 0.0F, 7.0F, 10.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -9.5F, -1.5F));

        ModelPartData frill_r1 = helmet.addChild("frill_r1", ModelPartBuilder.create().uv(62, -13).mirrored().cuboid(0.0F, -11.5F, -12.0F, 0.0F, 12.0F, 21.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.05F, -2.45F, -3.8F, -0.1745F, 0.0F, 0.0F));

        ModelPartData frillsprike_r1 = helmet.addChild("frillsprike_r1", ModelPartBuilder.create().uv(42, 116).cuboid(-10.5F, -0.5F, -7.0F, 12.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.55F, -2.9F, -9.8F, -1.5708F, 1.3963F, -1.5708F));

        ModelPartData nosecap_r1 = helmet.addChild("nosecap_r1", ModelPartBuilder.create().uv(104, 71).cuboid(-4.5F, 0.0F, -3.75F, 9.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.751F, -8.75F, 0.0873F, 0.0F, 0.0F));

        ModelPartData mouth = head.addChild("mouth", ModelPartBuilder.create().uv(67, 20).cuboid(-4.5F, -3.0F, -7.0F, 9.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.5F, -5.0F));

        ModelPartData chests2 = torso.addChild("chests2", ModelPartBuilder.create(), ModelTransform.pivot(-12.0F, -25.0F, 2.0F));

        ModelPartData legs = body.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData legFL = legs.addChild("legFL", ModelPartBuilder.create().uv(46, 76).mirrored().cuboid(-4.0F, -2.0F, -5.0F, 8.0F, 12.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(10.0F, -10.0F, -10.0F));

        ModelPartData legFR = legs.addChild("legFR", ModelPartBuilder.create().uv(46, 76).cuboid(-4.0F, -2.0F, -5.0F, 8.0F, 12.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-10.0F, -10.0F, -10.0F));

        ModelPartData legBL = legs.addChild("legBL", ModelPartBuilder.create().uv(0, 76).cuboid(-5.0F, -2.0F, -2.0F, 8.0F, 12.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(11.0F, -10.0F, 13.0F));

        ModelPartData legBR = legs.addChild("legBR", ModelPartBuilder.create().uv(0, 76).mirrored().cuboid(-3.0F, -2.0F, -2.0F, 8.0F, 12.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-11.0F, -10.0F, 13.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(WarturtleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(WarturtleAnimations.ANIM_WARTURTLE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, WarturtleAnimations.ANIM_WARTURTLE_IDLE, ageInTicks, 1f);

        this.updateAnimation(entity.sittingTransitionAnimationState, WarturtleAnimations.ANIM_WARTURTLE_HIDE, ageInTicks, 1.0f);
        this.updateAnimation(entity.sittingAnimationState, WarturtleAnimations.ANIM_WARTURTLE_SITTING, ageInTicks, 1.0f);
        this.updateAnimation(entity.standingTransitionAnimationState, WarturtleAnimations.ANIM_WARTURTLE_EMERGE, ageInTicks, 1.0f);

        tier1.visible = entity.hasTier1Chest();
        tier2.visible = entity.hasTier2Chest();
        tier3.visible = entity.hasTier3Chest();
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        body.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }
}
