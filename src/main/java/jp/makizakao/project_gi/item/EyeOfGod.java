package jp.makizakao.project_gi.item;

import jp.makizakao.project_gi.skill.BaseSkill;
import net.minecraft.world.item.Item;

public class EyeOfGod extends Item {
    protected final BaseSkill skill;
    public EyeOfGod(Properties pProperties, BaseSkill skill) {
        super(pProperties);
        this.skill = skill;
    }
    public int getSkillId() {
        return skill.getSkillId();
    }
}
