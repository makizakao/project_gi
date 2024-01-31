package jp.makizakao.project_gi.registry.event;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.particle.OrbitParticle;
import jp.makizakao.project_gi.particle.SeekParticle;
import jp.makizakao.project_gi.registry.Particles;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleSpriteSets {
    @SubscribeEvent
    public static void onRegisterParticleSpriteSet(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(Particles.TARGET_SEEKING_PARTICLE.get(),
                SeekParticle.Provider::new);
        event.registerSpriteSet(Particles.ORBITING_PARTICLE.get(),
                OrbitParticle.Provider::new);
    }
}
