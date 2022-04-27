package net.msrandom.featuresandcreatures.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class Tbh extends PathfinderMob implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    private static final EntityDataAccessor<Integer> tradeTimer = SynchedEntityData.defineId(BlackForestSpirit.class, EntityDataSerializers.INT);


    public Tbh(EntityType<? extends Tbh> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    public static boolean checkSpawnRules(EntityType<Tbh> type, LevelAccessor world, MobSpawnType spawnType, BlockPos pos, Random random) {
        if (world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) {
            Holder<Biome> biome = world.getBiome(pos);
            if (biome.is(Biomes.PLAINS)) {
                return true;
            }
            if (biome.is(Biomes.SUNFLOWER_PLAINS)) {
                return true;
            }
            if (biome.is(Biomes.MEADOW)) {
                return true;
            }
            if (biome.is(Biomes.MEADOW)) {
                return true;
            }
            if (biome.is(Biomes.FLOWER_FOREST)) {
                return true;
            }
            return checkMobSpawnRules(type, world, spawnType, pos, random);
        }
        return false;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 12.0).add(Attributes.MOVEMENT_SPEED, 0.2F).add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController<?> controller = event.getController();
        controller.transitionLengthTicks = 0;
        if (this.isOnGround() && !event.isMoving()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.tbh.idle", true));
            return PlayState.CONTINUE;
        }
        if (this.isOnGround() && event.isMoving()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.tbh.walk", true));
            if (this.getSpeed() > 0.2F) {
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.tbh.run", true));
                return PlayState.CONTINUE;
            }
            return PlayState.CONTINUE;
        }
        else{
            return PlayState.STOP;
        }
    }
}
