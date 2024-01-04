package jp.makizakao.project_gi.registries;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.particle.TravellerWindSkillEntityParticles;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProjectGIParticleSpriteSet {
    @SubscribeEvent
    public static void onRegisterParticleSpriteSet(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ProjectGIParticles.TRAVELLER_WIND_SKILL_ENTITY_PARTICLES.get(),
                TravellerWindSkillEntityParticles.Provider::new);
    }
}
