package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.entity.client.animation.GiraffeAnimations;
import net.kaupenjoe.mccourse.entity.custom.GiraffeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class GiraffeModel<T extends GiraffeEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public GiraffeModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("torso").getChild("neck").getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-9.0F, -8.0F, -17.0F, 18.0F, 18.0F, 34.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -43.0F, 0.0F));

        ModelPartData tail = torso.addChild("tail", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-5.5F, 0.0F, 0.0F, 11.0F, 33.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -3.0F, 17.0F, 0.0436F, 0.0F, 0.0F));

        ModelPartData tip_r1 = tail.addChild("tip_r1", ModelPartBuilder.create().uv(0, 24).mirrored().cuboid(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 28.5F, 0.0F, 0.0F, 2.2689F, 0.0F));

        ModelPartData tip_r2 = tail.addChild("tip_r2", ModelPartBuilder.create().uv(0, 24).mirrored().cuboid(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 28.5F, 0.0F, 0.0F, -2.2689F, 0.0F));

        ModelPartData neck = torso.addChild("neck", ModelPartBuilder.create().uv(99, 93).cuboid(-3.5F, -23.0F, -7.0F, 7.0F, 28.0F, 7.0F, new Dilation(0.0F))
                .uv(100, 72).cuboid(-3.5F, -38.0F, -7.0F, 7.0F, 15.0F, 6.0F, new Dilation(0.0F))
                .uv(100, 53).cuboid(-3.5F, -51.0F, -7.0F, 7.0F, 13.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 52).cuboid(-6.5F, -7.0F, -6.0F, 13.0F, 16.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.0F, -13.0F));

        ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(71, 18).cuboid(-2.5F, -4.0F, -11.5F, 5.0F, 4.0F, 6.0F, new Dilation(0.0F))
                .uv(70, 0).cuboid(-4.5F, -8.0F, -5.5F, 9.0F, 8.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -51.0F, -3.5F));

        ModelPartData horns = head.addChild("horns", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

        ModelPartData hornL = horns.addChild("hornL", ModelPartBuilder.create().uv(22, 9).cuboid(-0.5F, -4.0F, 4.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F))
                .uv(22, 5).cuboid(-0.5F, -3.0F, 4.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 0.0F, -5.0F));

        ModelPartData hornR = horns.addChild("hornR", ModelPartBuilder.create().uv(22, 5).cuboid(-0.5F, -3.0F, 4.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(22, 9).cuboid(-0.5F, -4.0F, 4.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)), ModelTransform.pivot(-3.0F, 0.0F, -5.0F));

        ModelPartData earL = head.addChild("earL", ModelPartBuilder.create(), ModelTransform.pivot(4.456F, -5.7557F, -2.0F));

        ModelPartData cube_r1 = earL.addChild("cube_r1", ModelPartBuilder.create().uv(22, 0).mirrored().cuboid(-0.75F, -3.0F, 0.0F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, -0.75F, 0.5F, -0.2618F, 0.0F, 1.0036F));

        ModelPartData earR = head.addChild("earR", ModelPartBuilder.create(), ModelTransform.pivot(-4.456F, -5.7557F, -2.0F));

        ModelPartData cube_r2 = earR.addChild("cube_r2", ModelPartBuilder.create().uv(22, 0).cuboid(-2.25F, -3.0F, 0.0F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -0.75F, 0.5F, -0.2618F, 0.0F, -1.0036F));

        ModelPartData legs = body.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData legFR = legs.addChild("legFR", ModelPartBuilder.create().uv(76, 52).cuboid(-2.5F, -2.0F, -2.5F, 5.0F, 40.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(8.5F, -38.0F, -11.5F));

        ModelPartData legFL = legs.addChild("legFL", ModelPartBuilder.create().uv(76, 52).cuboid(-11.0F, -2.0F, -2.5F, 5.0F, 40.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -38.0F, -11.5F));

        ModelPartData legBR = legs.addChild("legBR", ModelPartBuilder.create().uv(56, 52).cuboid(6.0F, 10.0F, 1.0F, 5.0F, 27.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 79).cuboid(6.0F, -3.0F, -5.0F, 5.0F, 13.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -37.0F, 10.0F));

        ModelPartData legBL = legs.addChild("legBL", ModelPartBuilder.create().uv(0, 79).cuboid(-11.0F, -3.0F, -5.0F, 5.0F, 13.0F, 11.0F, new Dilation(0.0F))
                .uv(56, 52).cuboid(-11.0F, 10.0F, 1.0F, 5.0F, 27.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -37.0F, 10.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(GiraffeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(GiraffeAnimations.ANIM_GIRAFFE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, GiraffeAnimations.ANIM_GIRAFFE_IDLE, ageInTicks, 1f);
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
