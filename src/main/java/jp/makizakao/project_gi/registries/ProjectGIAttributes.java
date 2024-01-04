package jp.makizakao.project_gi.registries;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.entity.TravellerWindSkillEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProjectGIAttributes {
    @SubscribeEvent
    public static void register(EntityAttributeCreationEvent event) {
        event.put(ProjectGIEntityTypes.TRAVELLER_WIND_SKILL_ENTITY.get(), TravellerWindSkillEntity.createAttributes().build());
    }
}
