package jp.makizakao.project_gi.skill;

import jp.makizakao.project_gi.constant.ProjectGITimes;
import jp.makizakao.project_gi.entity.TravelerWindSkillEntity;
import jp.makizakao.project_gi.registry.ProjectGISkills;
import net.minecraft.server.level.ServerLevel;

public class TravelerWindSkill extends BaseSkill {
    protected static TravelerWindSkill instance;
    private TravelerWindSkill() {
        this.skillId = ProjectGISkills.nextId();
        this.duration = 3 * ProjectGITimes.SECOND_TICK;
        this.coolDown = 5 * ProjectGITimes.SECOND_TICK + duration;
        this.handler = player -> {
            ServerLevel world = player.getLevel();
            var pos = player.getEyePosition().add(0, -0.5, 0)
                    .add(player.getLookAngle().multiply(4.0, 4.0, 4.0));
            var skill = new TravelerWindSkillEntity(player.getStringUUID(), world, pos);
            world.addFreshEntity(skill);
        };
    }
    public static TravelerWindSkill getInstance() {
        instance = instance == null ? new TravelerWindSkill() : instance;
        return instance;
    }
}
