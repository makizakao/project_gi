package jp.makizakao.project_gi.registry.event;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.registry.Items;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


import static jp.makizakao.project_gi.registry.Items.TEST_EYE_OF_GOD;

@EventBusSubscriber(modid = ProjectGIMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabs {
    private static final String MAIN_TAB_NAME = "project_gi_main_tab";
    private static final String MAIN_TAB_TITLE = "item_group." + ProjectGIMod.MOD_ID + "." + MAIN_TAB_NAME;

    public static DeferredRegister<CreativeModeTab> REGISTRIES =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProjectGIMod.MOD_ID);
    public static final RegistryObject<CreativeModeTab> PROJECT_GI_MAIN_TAB = REGISTRIES
            .register(MAIN_TAB_NAME, () -> CreativeModeTab.builder()
                    .title(Component.translatable(MAIN_TAB_TITLE))
                    .icon(() -> new ItemStack(TEST_EYE_OF_GOD.get()))
                    .build());

    @SubscribeEvent
    public static void register(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(Items.TEST_ITEM.get());
            event.accept(TEST_EYE_OF_GOD.get());
        }
    }
}
