package jp.makizakao.project_gi.networking.packet;

import jp.makizakao.project_gi.capability.PlayerSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

import static jp.makizakao.project_gi.capability.PlayerSkill.getSkillOptional;

public class SkillCancelPacket implements IProjectGIPacket<SkillCancelPacket> {
    public SkillCancelPacket() {
    }


    @Override
    public void encode(SkillCancelPacket msg, FriendlyByteBuf buf) {

    }

    @Override
    public SkillCancelPacket decode(FriendlyByteBuf buf) {
        return new SkillCancelPacket();
    }

    @Override
    public void handle(SkillCancelPacket message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> getSkillOptional(context.getSender())
                .ifPresent(PlayerSkill::cancelSkill));
        context.setPacketHandled(true);
    }
}
