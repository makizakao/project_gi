package jp.makizakao.project_gi.skill;

import jp.makizakao.project_gi.constant.ProjectGITimes;
import jp.makizakao.project_gi.registries.ProjectGISkills;
import net.minecraft.server.level.ServerLevel;

import java.util.Optional;

import static jp.makizakao.project_gi.capability.PlayerElement.getElementOptional;

public class TravellerWindBurst extends BaseBurst {
    protected static TravellerWindBurst instance;

    private TravellerWindBurst(int id) {
        this.skillId = id;
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

    public static TravellerWindBurst getInstance(int id) {
        instance = instance == null ? new TravellerWindBurst(id) : instance;
        return instance;
    }

    public static TravellerWindBurst getInstance() {
        return instance;
    }
}
