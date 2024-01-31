package jp.makizakao.project_gi.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OrbitParticle extends TextureSheetParticle {
    private final float orbitSpeed;
    private final Vec3 targetPos;
    protected OrbitParticle(ClientLevel pLevel, SpriteSet pSprites,
                            double pX, double pY, double pZ,
                            double targetPosX, double targetPosY, double targetPosZ) {
        super(pLevel, pX, pY, pZ, targetPosX, targetPosY, targetPosZ);
        this.friction = 0.0f;
        this.gravity = 0.0f;
        this.hasPhysics = false;
        this.quadSize *= 1f * Mth.randomBetween(this.random, 0.2f, 1.0f);
        this.lifetime = 3;

        this.setSpriteFromAge(pSprites);
        this.targetPos = new Vec3(targetPosX, targetPosY, targetPosZ);
        this.orbitSpeed = 1.5f;

    }
    @Override
    protected int getLightColor(float pPartialTick) {
        return 0xF000F0;
    }
    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) this.remove();
        else {
            double dx = this.x - this.targetPos.x;
            double dy = this.y - this.targetPos.y;
            double dz = this.z - this.targetPos.z;

            double angle = Math.atan2(dz, dx) - this.orbitSpeed;
            double radius = Math.sqrt(dx * dx + dz * dz);

            double newX = this.targetPos.x + radius * Math.cos(angle);
            double newY = this.targetPos.y + dy;
            double newZ = this.targetPos.z + radius * Math.sin(angle);
            this.move(newX - this.x, newY - this.y, newZ - this.z);
        }
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        double dx = this.x - this.targetPos.x;
        double dy = this.y - this.targetPos.y;
        double dz = this.z - this.targetPos.z;
        float yaw = (float) Math.atan2(dz, dx);
        float pitch = (float) Math.atan2(Math.sqrt(dx * dx + dz * dz), dy);
        pRenderInfo.setAnglesInternal(yaw, pitch);
        super.render(pBuffer, pRenderInfo, pPartialTicks);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(@NotNull SimpleParticleType simpleParticleType,
                                       @NotNull ClientLevel clientLevel,
                                       double x, double y, double z, double aimPosX, double aimPosY, double aimPosZ) {
            return new OrbitParticle(clientLevel, sprites, x, y, z, aimPosX, aimPosY, aimPosZ);
        }
    }
}
