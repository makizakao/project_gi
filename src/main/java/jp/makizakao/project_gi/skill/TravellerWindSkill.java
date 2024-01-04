package jp.makizakao.project_gi.skill;

import jp.makizakao.project_gi.constant.ProjectGITimes;
import jp.makizakao.project_gi.entity.TravellerWindSkillEntity;
import net.minecraft.server.level.ServerLevel;

public class TravellerWindSkill extends BaseSkill {
    protected static TravellerWindSkill instance;
    private TravellerWindSkill(int id) {
        this.skillId = id;
        this.duration = 3 * ProjectGITimes.SECOND_TICK;
        this.coolDown = 5 * ProjectGITimes.SECOND_TICK + duration;
        this.handler = player -> {
            ServerLevel world = player.getLevel();
            var pos = player.getEyePosition().add(0, -0.5, 0)
                    .add(player.getLookAngle().multiply(4.0, 4.0, 4.0));
            var skill = new TravellerWindSkillEntity(player.getStringUUID(), world, pos);
            world.addFreshEntity(skill);
        };
    }
    public static TravellerWindSkill getInstance(int id) {
        instance = instance == null ? new TravellerWindSkill(id) : instance;
        return instance;
    }
    public static TravellerWindSkill getInstance() {
        return instance;
    }
}
