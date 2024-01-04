package jp.makizakao.project_gi.capability;

import jp.makizakao.project_gi.capability.provider.PlayerSkillProvider;
import jp.makizakao.project_gi.networking.packet.SyncSkillUsingStateToClientPacket;
import jp.makizakao.project_gi.registries.ProjectGISkills;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;

import static jp.makizakao.project_gi.networking.PacketHandler.sendToPlayer;

public class PlayerSkill {
    private static final float COOL_DOWN_MULTIPLIER = 0.8f;
    private int coolDownTime;
    private int durationTime;
    private boolean canUseSkill = true;
    private boolean isUsingSkill = false;

    private void setCoolDownTime(int coolDown) {
        this.coolDownTime = coolDown;
    }

    private void setDuration(int duration) {
        this.durationTime = duration;
    }
    public void setUsingSkill(boolean isUsingSkill) {
        this.isUsingSkill = isUsingSkill;
    }
    public int getDurationTime() {
        return this.durationTime;
    }
    public boolean isUsing() {
        return this.isUsingSkill;
    }

    public void onCoolDownTick() {
        if(canUseSkill) return;
        if(coolDownTime > 0) {
            this.coolDownTime--;
        } else {
            this.canUseSkill = true;
        }
    }
    public void onSkillTick() {
        if(!isUsingSkill) return;
        if(this.durationTime > 0) {
            this.durationTime--;
        } else {
            this.isUsingSkill = false;
        }
    }
    public void useSkill(int skillId, ServerPlayer player) {
        if(canUseSkill)
        {
            ProjectGISkills.skillList.get(skillId).getHandler().accept(player);
            setCoolDownTime(ProjectGISkills.skillList.get(skillId).getCoolDown());
            setDuration(ProjectGISkills.skillList.get(skillId).getDuration());
            this.canUseSkill = false;
            this.isUsingSkill = true;
            sendToPlayer(new SyncSkillUsingStateToClientPacket(true), player);
        }
    }
    public void cancelSkill() {
        this.durationTime = 0;
        this.coolDownTime = (int) (coolDownTime * COOL_DOWN_MULTIPLIER);
        this.isUsingSkill = false;
    }
    public static Optional<PlayerSkill> getSkillOptional(Player player) {
        return Optional.ofNullable(player)
                .map(p -> p.getCapability(PlayerSkillProvider.PLAYER_SKILL_CAPABILITY))
                .flatMap(LazyOptional::resolve);
    }
    public void saveNBTTags(CompoundTag tag) {
        tag.putInt("coolDownTime", coolDownTime);
        tag.putInt("durationTime", durationTime);
    }
    public void loadNBTTags(CompoundTag tag) {
        this.coolDownTime = tag.getInt("coolDownTime");
        this.durationTime = tag.getInt("durationTime");
    }
    public void copyFrom(PlayerSkill skill) {
        this.coolDownTime = skill.coolDownTime;
        this.durationTime = skill.durationTime;
    }
}
