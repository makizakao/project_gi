package jp.makizakao.project_gi.registry;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.entity.TravelerWindSkillEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ProjectGIMod.MOD_ID);
    public static final RegistryObject<EntityType<TravelerWindSkillEntity>> TRAVELLER_WIND_SKILL_ENTITY =
            createEntity("traveler_wind_skill_entity", TravelerWindSkillEntity::new,
                    MobCategory.MISC, 1f, 1f);


    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> createEntity(
            String name, EntityFactory<T> supplier, MobCategory category, float width, float height) {
        return ENTITY_TYPES.register(name,
                () -> EntityType.Builder.of(supplier, category)
                        .sized(width, height)
                        .build(name));
    }
}
