package com.fusionflux.thinkingwithportatos.entity;

import dev.lazurite.rayon.core.impl.physics.space.MinecraftSpace;
import dev.lazurite.rayon.core.impl.physics.space.body.ElementRigidBody;
import dev.lazurite.rayon.entity.api.EntityPhysicsElement;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

import java.util.Optional;

public class PhysicsFallingBlockEntity extends Entity implements EntityPhysicsElement {
    private final ElementRigidBody rigidBody = new ElementRigidBody(this);
    private static final TrackedData<Optional<BlockState>> BLOCK_STATE = DataTracker.registerData(PhysicsFallingBlockEntity.class, TrackedDataHandlerRegistry.OPTIONAL_BLOCK_STATE);

    public PhysicsFallingBlockEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public PhysicsFallingBlockEntity(World world, double x, double y, double z, BlockState blockState) {
        this(ThinkingWithPortatosEntities.PHYSICS_FALLING_BLOCK, world);
        this.updatePosition(x, y, z);
        this.setBlockState(blockState);
    }

    @Override
    protected void initDataTracker() {
        this.getDataTracker().startTracking(BLOCK_STATE, Optional.ofNullable(Blocks.AIR.getDefaultState()));
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {

    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return getSpawnPacket();
    }

    @Override
    public void step(MinecraftSpace space) {

    }

    @Override
    public ElementRigidBody getRigidBody() {
        return this.rigidBody;
    }

    public void setBlockState(BlockState blockState) {
        getDataTracker().set(BLOCK_STATE, Optional.ofNullable(blockState));
    }

    public BlockState getBlockState() {
        return getDataTracker().get(BLOCK_STATE).orElse(null);
    }
}
