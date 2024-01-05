package jp.makizakao.project_gi.registry;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.capability.PlayerElement;
import jp.makizakao.project_gi.capability.PlayerSkill;
import jp.makizakao.project_gi.capability.provider.PlayerElementProvider;
import jp.makizakao.project_gi.capability.provider.PlayerSkillProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID)
public class ProjectGICapabilities {
    @SubscribeEvent
    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject()
                    .getCapability(PlayerSkillProvider.PLAYER_SKILL_CAPABILITY)
                    .isPresent()) {
                event.addCapability(ProjectGIMod.getLocation.apply("skill_properties"),
                        new PlayerSkillProvider());
                event.addCapability(ProjectGIMod.getLocation.apply("element_properties"),
                        new PlayerElementProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal()
                    .getCapability(PlayerSkillProvider.PLAYER_SKILL_CAPABILITY)
                    .ifPresent(oldCap -> event.getOriginal()
                            .getCapability(PlayerSkillProvider.PLAYER_SKILL_CAPABILITY)
                            .ifPresent(newCap -> newCap.copyFrom(oldCap)));
        }
    }
    @SubscribeEvent
    public static void onRegister(RegisterCapabilitiesEvent event) {
        event.register(PlayerSkill.class);
        event.register(PlayerElement.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side.isServer()) {
            event.player.getCapability(PlayerSkillProvider.PLAYER_SKILL_CAPABILITY)
                    .ifPresent(PlayerSkill::onCoolDownTick);
            event.player.getCapability(PlayerSkillProvider.PLAYER_SKILL_CAPABILITY)
                    .ifPresent(PlayerSkill::onSkillTick);
            event.player.getCapability(PlayerElementProvider.PLAYER_ELEMENT_CAPABILITY)
                    .ifPresent(PlayerElement::printEnergy);
        }
    }
}
