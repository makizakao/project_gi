package jp.makizakao.project_gi.entity;

import jp.makizakao.project_gi.networking.packet.SyncSkillUsingStateToClientPacket;
import jp.makizakao.project_gi.registry.ProjectGIEntityTypes;
import jp.makizakao.project_gi.registry.ProjectGIParticles;
import jp.makizakao.project_gi.registry.ProjectGISkills;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static jp.makizakao.project_gi.capability.PlayerElement.getElementOptional;
import static jp.makizakao.project_gi.capability.PlayerSkill.getSkillOptional;
import static jp.makizakao.project_gi.networking.PacketHandler.sendToPlayer;

public class TravelerWindSkillEntity extends Entity {
    private static final double VACUUM_RANGE = 3.0;
    private static final double DAMAGE_RANGE = 4.0;
    private static final double FORCE = 0.1;
    private static final float BASE_DAMAGE = 3f;
    private static final float MAX_DAMAGE = 3f;
    private static final float MAX_BLAST_DAMAGE = 6f;
    private static final int DAMAGE_COOL_DOWN = 15;
    private static final double MAIN_PARTICLE_RADIUS = 2.0;
    private static final double VACUUM_PARTICLE_RADIUS = 3.0;;
    private static final double MAIN_PARTICLE_SPEED = 0.25;
    private static final double VACUUM_PARTICLE_SPEED = -1;
    private static final double BLAST_PARTICLE_SPEED = 100;
    private static final ProjectGISkills.ElementType ELEMENT_TYPE = ProjectGISkills.ElementType.Anemo;
    private static final EntityDataAccessor<String> OWNER_UUID;
    private static final EntityDataAccessor<Integer> TICK_COUNT;
    public TravelerWindSkillEntity(EntityType<? extends TravelerWindSkillEntity> entityType, Level pLevel) {
        super(entityType, pLevel);
        this.entityData.set(OWNER_UUID, "");
        this.entityData.set(TICK_COUNT, 0);
        this.setNoGravity(true);
        this.setInvulnerable(true);
    }

    public TravelerWindSkillEntity(String uuid, Level pLevel, Vec3 pPos) {
        this(ProjectGIEntityTypes.TRAVELLER_WIND_SKILL_ENTITY.get(), pLevel);
        this.setPos(pPos);
        this.entityData.set(OWNER_UUID, uuid);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TICK_COUNT, 0);
        this.entityData.define(OWNER_UUID, "");
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots() {
        return List.of(Items.AIR.getDefaultInstance());
    }

    @Override
    public void setItemSlot(@NotNull EquipmentSlot equipmentSlot, @NotNull ItemStack itemStack) {
    }

    @Override
    public void tick() {
        super.tick();
        this.entityData.set(TICK_COUNT, this.entityData.get(TICK_COUNT) + 1);
        this.vacuumEntity();
        this.damageEntity();
        this.spawnMainParticle();
        getSkillOptional(getOwner())
                .filter(s -> !s.isUsing())
                .ifPresent(i -> onRemove());
    }

    private void onRemove() {
        Optional.ofNullable(getOwner())
                .filter(player -> player instanceof ServerPlayer)
                .map(player -> (ServerPlayer) player)
                .ifPresent(player -> {
                    //spawnBlastParticle(player);
                    sendToPlayer(new SyncSkillUsingStateToClientPacket(false), player);
                });
        this.discard();
        blast();
    }

    private void vacuumEntity() {
        var vacuumAabb = new AABB(this.getX() - VACUUM_RANGE, this.getY() - VACUUM_RANGE,
                this.getZ() - VACUUM_RANGE, this.getX() + VACUUM_RANGE, this.getY() + VACUUM_RANGE,
                this.getZ() + VACUUM_RANGE);
        List<Entity> entities = this.level.getEntities(this, vacuumAabb,
                entity -> entity instanceof ItemEntity || entity instanceof Mob);
        for (var entity : entities) {
            double dx = this.getX() - entity.getX();
            double dy = this.getY() - entity.getY();
            double dz = this.getZ() - entity.getZ();
            var pos = new Vec3(dx, dy, dz).normalize().scale(FORCE);
            entity.setDeltaMovement(pos);
        }
    }

    private void damageEntity() {
        var damageAabb = new AABB(this.getX() - DAMAGE_RANGE, this.getY() - DAMAGE_RANGE,
                this.getZ() - DAMAGE_RANGE, this.getX() + DAMAGE_RANGE, this.getY() + DAMAGE_RANGE,
                this.getZ() + DAMAGE_RANGE);
        List<Entity> mobs = this.level.getEntities(this, damageAabb,
                entity -> entity instanceof Mob);
        for (var mob : mobs) {
            float distance = this.distanceTo(mob);
            if(this.entityData.get(TICK_COUNT) % DAMAGE_COOL_DOWN == 0) {
                Optional.ofNullable(getOwner())
                        .ifPresent(player -> {
                            mob.hurt(damageSources().playerAttack(player),
                                    Math.max(BASE_DAMAGE / distance, MAX_DAMAGE));
                            getElementOptional(player)
                                    .ifPresent(cap -> cap.addElementEnergy(1, ELEMENT_TYPE));
                        });
            }
        }
    }

    private void blast() {
        var damageAabb = new AABB(this.getX() - DAMAGE_RANGE, this.getY() - DAMAGE_RANGE,
                this.getZ() - DAMAGE_RANGE, this.getX() + DAMAGE_RANGE, this.getY() + DAMAGE_RANGE,
                this.getZ() + DAMAGE_RANGE);
        List<Entity> mobs = this.level.getEntities(this, damageAabb,
                entity -> entity instanceof Mob);
        for (var mob : mobs) {
            double dx = mob.getX() - this.getX();
            double dy = mob.getY() - this.getY();
            double dz = mob.getZ() - this.getZ();
            var pos = new Vec3(dx, dy, dz).normalize().multiply(1, - FORCE, 1);
            mob.setDeltaMovement(pos);
            float distance = this.distanceTo(mob);
            Optional.ofNullable(getOwner())
                    .ifPresent(player -> {
                        mob.hurt(damageSources().playerAttack(player),
                                Math.max(MAX_BLAST_DAMAGE / distance, MAX_BLAST_DAMAGE));
                        getElementOptional(player)
                                .ifPresent(cap -> cap.addElementEnergy(1, ELEMENT_TYPE));
                    });
        }
    }



    private void spawnMainParticle() {
        if(this.entityData.get(TICK_COUNT) % 5 != 0) return;
        for(int i = 0; i < 2; i ++) {
            float phi = (float) (this.random.nextDouble() * Mth.PI * 2);
            float theta = (float) Math.acos(this.random.nextDouble() * 2 -1);

            double posX = this.getX() + MAIN_PARTICLE_RADIUS * Mth.sin(theta) * Math.cos(phi);
            double posY = this.getY() + MAIN_PARTICLE_RADIUS * Mth.sin(theta) * Math.sin(phi);
            double posZ = this.getZ() + MAIN_PARTICLE_RADIUS * Mth.cos(theta);

            double aimPosX = this.getX();
            double aimPosY = this.getY() + 0.1;
            double aimPosZ = this.getZ();

            this.level.addParticle(ProjectGIParticles.TRAVELLER_WIND_SKILL_ENTITY_PARTICLES.get(),
                    posX, posY, posZ, aimPosX, aimPosY, aimPosZ);
        }
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean canCollideWith(@NotNull Entity pEntity) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }



    private Player getOwner() {
        return Optional.of(this.level)
                .filter(l -> !l.isClientSide() || !this.entityData.get(OWNER_UUID).isEmpty())
                .filter(l -> !this.entityData.get(OWNER_UUID).isEmpty())
                .map(l -> l.getPlayerByUUID(UUID.fromString(this.entityData.get(OWNER_UUID))))
                .orElse(null);
    }

    static {
        OWNER_UUID = SynchedEntityData.defineId(TravelerWindSkillEntity.class, EntityDataSerializers.STRING);
        TICK_COUNT = SynchedEntityData.defineId(TravelerWindSkillEntity.class, EntityDataSerializers.INT);
    }
}
