package jp.makizakao.project_gi.registry;

import jp.makizakao.project_gi.ProjectGIMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Particles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ProjectGIMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> TARGET_SEEKING_PARTICLE =
            PARTICLE_TYPES.register("traveler_wind_skill_entity_particles",
                    () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> ORBITING_PARTICLE =
            PARTICLE_TYPES.register("traveler_wind_skill_orbiting_particles",
                    () -> new SimpleParticleType(true));

    public static void register(IEventBus bus) {
        PARTICLE_TYPES.register(bus);
    }
}
