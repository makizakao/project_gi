package jp.makizakao.project_gi.registry.event;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.capability.PlayerSkill;
import jp.makizakao.project_gi.item.EyeOfGod;
import jp.makizakao.project_gi.networking.PacketHandler;
import jp.makizakao.project_gi.networking.packet.SkillCancelPacket;
import jp.makizakao.project_gi.networking.packet.UseSkillPacket;
import jp.makizakao.project_gi.skill.TravelerWindSkill;
import jp.makizakao.project_gi.util.ProjectGIKeybinding;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

import static jp.makizakao.project_gi.capability.PlayerSkill.getSkillOptional;

public class ProjectGIKeyBindings {
    @Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class KeyRegisterEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(ProjectGIKeybinding.USING_SKILL_KEY);
        }
    }

    @Mod.EventBusSubscriber(modid = ProjectGIMod.MOD_ID, value = Dist.CLIENT)
    public static class KeyInputEvents {
        @SubscribeEvent
        public static void onKeyBindingTick(ClientTickEvent event) {
            if(event.phase != ClientTickEvent.Phase.END) return;
            onUsingSkillTick();
        }

        private static void onUsingSkillTick() {
            int id = Optional.ofNullable(Minecraft.getInstance().player)
                    .map(player -> player.getItemBySlot(EquipmentSlot.OFFHAND).getItem())
                    .filter(item -> item instanceof EyeOfGod)
                    .map(item -> ((EyeOfGod) item).getSkillId())
                    .orElse(TravelerWindSkill.getInstance().getSkillId());
            getSkillOptional(Minecraft.getInstance().player)
                    .filter(p -> !ProjectGIKeybinding.USING_SKILL_KEY.consumeClick())
                    .filter(PlayerSkill::isUsing)
                    .ifPresent(p -> PacketHandler.sendToServer(new SkillCancelPacket()));
            while(ProjectGIKeybinding.USING_SKILL_KEY.consumeClick()) {
                PacketHandler.sendToServer(new UseSkillPacket(id));
            }
        }
    }
}
