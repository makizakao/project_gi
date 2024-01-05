package jp.makizakao.project_gi.skill;

import jp.makizakao.project_gi.constant.ProjectGITimes;
import jp.makizakao.project_gi.registry.ProjectGISkills;
import net.minecraft.server.level.ServerLevel;

import static jp.makizakao.project_gi.capability.PlayerElement.getElementOptional;

public class TravelerWindBurst extends BaseBurst {
    protected static TravelerWindBurst instance;

    private TravelerWindBurst() {
        this.skillId = ProjectGISkills.nextId();
        this.duration = (int) (0.5 * ProjectGITimes.SECOND_TICK);
        this.coolDown = 15 * ProjectGITimes.SECOND_TICK;
        this.energyUsage = 70;
        this.elementType = ProjectGISkills.ElementType.Anemo;

        this.handler = player -> getElementOptional(player)
                .filter(cap -> cap.getElementEnergy(elementType) >= energyUsage)
                .ifPresent(cap -> {
                    ServerLevel world = player.getLevel();
                    var pos = player.getEyePosition().add(0, -0.5, 0)
                            .add(player.getLookAngle().multiply(4.0, 4.0, 4.0));
                    //var skill = new TravellerWindBurstEntity(player.getStringUUID(), world, pos);
                    //world.addFreshEntity(skill);
                    cap.consumeElementEnergy(elementType);
                });
    }

    public static TravelerWindBurst getInstance() {
        instance = instance == null ? new TravelerWindBurst() : instance;
        return instance;
    }
}
