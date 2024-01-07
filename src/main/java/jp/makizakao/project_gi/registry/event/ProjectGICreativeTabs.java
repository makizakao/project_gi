package jp.makizakao.project_gi.registry.event;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.registry.ProjectGIItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.function.Supplier;

@EventBusSubscriber(modid = ProjectGIMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ProjectGICreativeTabs {
    public static CreativeModeTab PROJECT_GI_MAIN_TAB;

    @SubscribeEvent
    public static void register(CreativeModeTabEvent.Register event) {
        PROJECT_GI_MAIN_TAB = createTab(event, "project_gi_main_tab",
                () -> new ItemStack(ProjectGIItems.TEST_ITEM.get()));
    }

    private static CreativeModeTab createTab(
            CreativeModeTabEvent.Register event,
            String name,
            Supplier<ItemStack> icon) {
        ResourceLocation location = ProjectGIMod.getLocation.apply(name);
        return event.registerCreativeModeTab(
                location,
                builder -> builder
                        .icon(icon)
                        .title(Component.translatable(location.toLanguageKey())));
    }

    public static void addCreativeModTab(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == PROJECT_GI_MAIN_TAB) {
            event.accept(ProjectGIItems.TEST_ITEM.get());
            event.accept(ProjectGIItems.TEST_EYE_OF_GOD.get());
        }
    }
}
