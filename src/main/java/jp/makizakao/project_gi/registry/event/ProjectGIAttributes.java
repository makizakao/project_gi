package jp.makizakao.project_gi.registry.event;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.entity.TravelerWindSkillEntity;
import jp.makizakao.project_gi.registry.ProjectGIEntityTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProjectGIAttributes {
    @SubscribeEvent
    public static void onEntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
    }
}
