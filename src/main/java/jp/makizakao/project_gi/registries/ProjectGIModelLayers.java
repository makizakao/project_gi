package jp.makizakao.project_gi.registries;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.entity.model.TravellerWindSkillEntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ProjectGIModelLayers {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TravellerWindSkillEntityModel.LAYER_LOCATION,
                TravellerWindSkillEntityModel::createBodyLayer);
    }
}
