package jp.makizakao.project_gi.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SeekParticle extends TextureSheetParticle {
    private final Vec3 aimPos;
    private final float SPEED = 0.05F;
    protected SeekParticle(ClientLevel pLevel, SpriteSet pSprites,
                           double pX, double pY, double pZ,
                           double aimPosX, double aimPosY, double aimPosZ) {
        super(pLevel, pX, pY, pZ, aimPosX, aimPosY, aimPosZ);
        this.friction = 0.0f;
        this.gravity = 0.0f;
        this.hasPhysics = false;
        this.quadSize *= 0.5f * Mth.randomBetween(this.random, 0.2f, 1.0f);
        this.lifetime = 10;
        this.aimPos = new Vec3(aimPosX, aimPosY, aimPosZ);
        this.setSpriteFromAge(pSprites);
    }

    @Override
    public void tick() {
        if(this.alpha <= 1) fadeOut();
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        xd = aimPos.x - x;
        yd = aimPos.y - y;
        zd = aimPos.z - z;
        var vec3 = new Vec3(xd, yd, zd).normalize().scale(SPEED);
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
           this.move(vec3.x(), vec3.y(), vec3.z());
        }
    }

    @Override
    public void move(double pX, double pY, double pZ) {
        this.setBoundingBox(this.getBoundingBox().move(pX, pY, pZ));
        this.setLocationFromBoundingbox();
    }

    private void fadeOut() {
        this.alpha = -(1/(float)lifetime) * age + 1;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return 0xF000F0;
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
            return new SeekParticle(clientLevel, sprites, x, y, z, aimPosX, aimPosY, aimPosZ);
        }
    }
}
