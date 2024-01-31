package jp.makizakao.project_gi.skill;

import jp.makizakao.project_gi.constant.Times;
import jp.makizakao.project_gi.registry.Skills;
import net.minecraft.server.level.ServerLevel;

import java.util.Optional;

import static jp.makizakao.project_gi.capability.PlayerElement.getElementOptional;

public class TravelerWindBurst extends BaseBurst {
    protected static TravelerWindBurst instance;

    private TravelerWindBurst() {
        this.skillId = Skills.nextId();
        this.duration = (int) (0.5 * Times.SECOND_TICK);
        this.coolDown = 15 * Times.SECOND_TICK;
        this.energyUsage = 70;
        this.elementType = Skills.ElementType.Anemo;

        this.handler = player -> getElementOptional(player)
                .filter(cap -> cap.getElementEnergy(elementType) >= energyUsage)
                .ifPresent(cap -> Optional.of(player.serverLevel()).ifPresent(l -> {
                    ServerLevel world = player.serverLevel();
                    var pos = player.getEyePosition().add(0, -0.5, 0)
                            .add(player.getLookAngle().multiply(4.0, 4.0, 4.0));
                    cap.consumeElementEnergy(elementType);
                }));
    }

    public static TravelerWindBurst getInstance() {
        instance = instance == null ? new TravelerWindBurst() : instance;
        return instance;
    }
}
