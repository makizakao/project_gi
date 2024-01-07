package jp.makizakao.project_gi.registry.event;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.particle.TravelerWindSkillEntityParticles;
import jp.makizakao.project_gi.registry.ProjectGIParticles;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProjectGIParticleSpriteSet {
    @SubscribeEvent
    public static void onRegisterParticleSpriteSet(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ProjectGIParticles.TRAVELLER_WIND_SKILL_ENTITY_PARTICLES.get(),
                TravelerWindSkillEntityParticles.Provider::new);
    }
}
