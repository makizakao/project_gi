package jp.makizakao.project_gi.networking.packet;

import jp.makizakao.project_gi.capability.provider.PlayerSkillProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class UseSkillPacket implements IProjectGIPacket<UseSkillPacket> {
    private final int skillId;

    public UseSkillPacket() {
        this.skillId = 0;
    }

    public UseSkillPacket(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public void encode(UseSkillPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.skillId);
    }

    @Override
    public UseSkillPacket decode(FriendlyByteBuf buf) {
        return new UseSkillPacket(buf.readInt());
    }

    @Override
    public void handle(UseSkillPacket message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> Optional.ofNullable(context.getSender())
                .ifPresent(p -> p.getCapability(PlayerSkillProvider.PLAYER_SKILL_CAPABILITY)
                        .ifPresent(cap -> cap.useSkill(message.skillId, p))));
        context.setPacketHandled(true);
    }
}
