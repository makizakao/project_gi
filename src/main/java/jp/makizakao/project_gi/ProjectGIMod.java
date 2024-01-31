package jp.makizakao.project_gi;

import com.mojang.logging.LogUtils;
import jp.makizakao.project_gi.networking.PacketHandler;
import jp.makizakao.project_gi.registry.*;
import jp.makizakao.project_gi.registry.event.CreativeTabs;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.function.Function;

@Mod(ProjectGIMod.MOD_ID)
public class ProjectGIMod {
    public static final String MOD_ID = "project_gi";
    public static Function<String ,ResourceLocation> getLocation =
            path -> new ResourceLocation(MOD_ID, path);
    private static final Logger LOGGER = LogUtils.getLogger();
    public ProjectGIMod() {
        onSetUp();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Items.register(bus);
        EntityTypes.register(bus);
        Particles.register(bus);
        PacketHandler.registerMessages();
        bus.addListener(this::commonSetup);
        bus.addListener(CreativeTabs::addCreativeModTab);
        bus.addListener(Renderers::onRegister);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
    private void onSetUp() {
        Skills.register();
    }
}
