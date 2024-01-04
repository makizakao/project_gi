package jp.makizakao.project_gi.skill;


import jp.makizakao.project_gi.registries.ProjectGISkills;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public abstract class BaseSkill {
    protected static BaseSkill instance;
    protected int skillId;
    protected int duration;
    protected int coolDown;
    protected ProjectGISkills.ElementType elementType;
    protected Consumer<ServerPlayer> handler;
    protected BaseSkill() {
    }
    public int getSkillId() {
        return skillId;
    }
    public int getDuration() {
        return duration;
    }
    public int getCoolDown() {
        return coolDown;
    }
    public Consumer<ServerPlayer> getHandler() {
        return handler;
    }
    public static BaseSkill getInstance() {
        return instance;
    }
}