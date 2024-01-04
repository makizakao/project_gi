package jp.makizakao.project_gi.capability.provider;

import jp.makizakao.project_gi.capability.PlayerSkill;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PlayerSkillProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerSkill> PLAYER_SKILL_CAPABILITY =
            CapabilityManager.get(new CapabilityToken<>(){});
    private PlayerSkill instance = null;
    private final LazyOptional<PlayerSkill> holder = LazyOptional.of(this::getInstance);
    private PlayerSkill getInstance() {
        return Optional.ofNullable(instance).orElse(new PlayerSkill());
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability,
                                                      @Nullable Direction direction) {
        return capability == PLAYER_SKILL_CAPABILITY ? holder.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        getInstance().saveNBTTags(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        getInstance().loadNBTTags(compoundTag);
    }
}
