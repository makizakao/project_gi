package jp.makizakao.project_gi.registry;

import jp.makizakao.project_gi.entity.render.TravelerWindSkillRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ProjectGIRenderers {
    public static void onRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ProjectGIEntityTypes.TRAVELLER_WIND_SKILL_ENTITY.get(),
                TravelerWindSkillRenderer::new);
    }
}
