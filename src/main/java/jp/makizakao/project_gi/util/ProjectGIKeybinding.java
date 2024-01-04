package jp.makizakao.project_gi.util;

import com.mojang.blaze3d.platform.InputConstants;
import jp.makizakao.project_gi.ProjectGIMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import java.util.function.Function;

public class ProjectGIKeybinding {
    private static final String KEY_CATEGORY = "key.category." + ProjectGIMod.MOD_ID + ".general";
    private static final Function<String,String> getKeyName =
            name -> "key." + ProjectGIMod.MOD_ID + "." + name;
    public static final KeyMapping USING_SKILL_KEY = new KeyMapping(getKeyName.apply("use_skill"),
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_E, KEY_CATEGORY);

}
