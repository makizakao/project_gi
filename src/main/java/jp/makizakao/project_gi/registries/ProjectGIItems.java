package jp.makizakao.project_gi.registries;

import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.item.EyeOfGod;
import jp.makizakao.project_gi.item.TestItem;
import jp.makizakao.project_gi.skill.TravellerWindSkill;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ProjectGIItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ProjectGIMod.MOD_ID);
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new TestItem(new Item.Properties()));
    public static final RegistryObject<Item> TEST_EYE_OF_GOD = ITEMS.register("eye_of_god_test",
            () -> new EyeOfGod(new Item.Properties(), TravellerWindSkill.getInstance()));
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
