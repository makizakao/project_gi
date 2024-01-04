package jp.makizakao.project_gi.capability.provider;

import jp.makizakao.project_gi.capability.PlayerElement;
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

public class PlayerElementProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerElement> PLAYER_ELEMENT_CAPABILITY =
            CapabilityManager.get(new CapabilityToken<>(){});
    private PlayerElement instance = null;
    private final LazyOptional<PlayerElement> holder = LazyOptional.of(this::getInstance);
    private PlayerElement getInstance() {
        return Optional.ofNullable(instance).orElse(new PlayerElement());
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability,
                                                      @Nullable Direction direction) {
        return capability == PLAYER_ELEMENT_CAPABILITY ? holder.cast() : LazyOptional.empty();
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
