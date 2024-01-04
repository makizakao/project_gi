package jp.makizakao.project_gi.registries;

import jp.makizakao.project_gi.entity.render.TravellerWindSkillRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ProjectGIRenderers {
    public static void onRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ProjectGIEntityTypes.TRAVELLER_WIND_SKILL_ENTITY.get(),
                TravellerWindSkillRenderer::new);
    }
}
