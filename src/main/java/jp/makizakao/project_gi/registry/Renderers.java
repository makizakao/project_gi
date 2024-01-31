package jp.makizakao.project_gi.registry;

import jp.makizakao.project_gi.entity.render.TravelerWindSkillRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class Renderers {
    public static void onRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypes.TRAVELLER_WIND_SKILL_ENTITY.get(),
                TravelerWindSkillRenderer::new);
    }
}
