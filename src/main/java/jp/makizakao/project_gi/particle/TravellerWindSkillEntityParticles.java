package jp.makizakao.project_gi.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TravellerWindSkillEntityParticles extends TextureSheetParticle {
    protected TravellerWindSkillEntityParticles(ClientLevel pLevel, SpriteSet pSprites,
                                                double pX, double pY, double pZ,
                                                double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.friction = 0.8f;
        this.quadSize *= 1.5f;
        this.lifetime = 10;
        this.setSpriteFromAge(pSprites);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.alpha <= 1) fadeOut();
    }

    private void fadeOut() {
        this.alpha = -(1/(float)lifetime) * age + 1;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
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
                                       double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new TravellerWindSkillEntityParticles(clientLevel, sprites, x, y, z, xSpeed, ySpeed, zSpeed);

        }
    }
}
