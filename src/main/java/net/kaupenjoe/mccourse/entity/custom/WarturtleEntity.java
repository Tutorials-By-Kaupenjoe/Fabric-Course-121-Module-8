package net.kaupenjoe.mccourse.entity.custom;

import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class WarturtleEntity extends TameableEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState sittingTransitionAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState standingTransitionAnimationState = new AnimationState();

    public static final TrackedData<Long> LAST_POSE_TICK =
            DataTracker.registerData(WarturtleEntity.class, TrackedDataHandlerRegistry.LONG);

    public WarturtleEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new SitGoal(this));

        this.goalSelector.add(2, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(ModItems.STRAWBERRY), false));

        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.1D, 10f, 3f));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));

        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createWarturtleAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 45)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1);
    }

    /* BREEDABLE */
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.STRAWBERRY);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.WARTURTLE.create(world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.shouldUpdateSittingAnimations()) {
            this.standingTransitionAnimationState.stop();
            if (this.shouldPlaySittingTransitionAnimation()) {
                this.sittingTransitionAnimationState.startIfNotRunning(this.age);
                this.sittingAnimationState.stop();
            } else {
                this.sittingTransitionAnimationState.stop();
                this.sittingAnimationState.startIfNotRunning(this.age);
            }
        } else {
            this.sittingTransitionAnimationState.stop();
            this.sittingAnimationState.stop();
            this.standingTransitionAnimationState.setRunning(this.isChangingPose() && this.getLastPoseTickDelta() >= 0L, this.age);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    private boolean shouldPlaySittingTransitionAnimation() {
        return this.isSitting() && this.getLastPoseTickDelta() < 40L && this.getLastPoseTickDelta() >= 0L;
    }

    public boolean shouldUpdateSittingAnimations() {
        return this.getLastPoseTickDelta() < 0L != this.isSitting();
    }

    public long getLastPoseTickDelta() {
        return this.getWorld().getTime() - Math.abs(this.dataTracker.get(LAST_POSE_TICK));
    }

    public boolean isChangingPose() {
        long l = this.getLastPoseTickDelta();
        return l < (long)(this.isSitting() ? 40 : 52);
    }

    public void setLastPoseTick(long lastPoseTick) {
        this.dataTracker.set(LAST_POSE_TICK, lastPoseTick);
    }

    private void initLastPoseTick(long time) {
        this.setLastPoseTick(Math.max(0L, time - 52L - 1L));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(LAST_POSE_TICK, 0L);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putLong("LastPoseTick", this.dataTracker.get(LAST_POSE_TICK));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        long l = nbt.getLong("LastPoseTick");
        if (l < 0L) {
            this.setPose(EntityPose.SITTING);
        }
        this.setLastPoseTick(l);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        this.initLastPoseTick(world.toServerWorld().getTime());
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public void toggleSitting() {
        if (this.isSitting()) {
            startStanding();
        } else {
            startSitting();
        }
    }

    public void startSitting() {
        if (this.isSitting()) {
            return;
        }
        this.playSound(SoundEvents.ENTITY_CAMEL_SIT);
        this.setPose(EntityPose.SITTING);
        this.emitGameEvent(GameEvent.ENTITY_ACTION);
        this.setLastPoseTick(-this.getWorld().getTime());

        setInSittingPose(true);
        setSitting(true);
    }

    public void startStanding() {
        if (!this.isSitting()) {
            return;
        }
        this.playSound(SoundEvents.ENTITY_CAMEL_STAND);
        this.setPose(EntityPose.STANDING);
        this.emitGameEvent(GameEvent.ENTITY_ACTION);
        this.setLastPoseTick(this.getWorld().getTime());

        setInSittingPose(false);
        setSitting(false);
    }

    public boolean isSitting() {
        return this.dataTracker.get(LAST_POSE_TICK) < 0L;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = Items.APPLE;

        if(item == itemForTaming && !isTamed()) {
            if(this.getWorld().isClient()) {
                return ActionResult.CONSUME;
            } else {
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                super.setOwner(player);
                this.navigation.recalculatePath();
                this.setTarget(null);
                this.getWorld().sendEntityStatus(this, (byte)7);
                toggleSitting();

                return ActionResult.SUCCESS;
            }
        }

        if(isTamed() && hand == Hand.MAIN_HAND && item != itemForTaming && !isBreedingItem(itemstack)) {
            toggleSitting();
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }
}
