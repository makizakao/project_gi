package jp.makizakao.project_gi.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

import static jp.makizakao.project_gi.capability.PlayerSkill.getSkillOptional;

public class SyncSkillUsingStateToClientPacket implements IProjectGIPacket<SyncSkillUsingStateToClientPacket> {
    private final boolean isUsingSkill;
    public SyncSkillUsingStateToClientPacket() {
        this.isUsingSkill = false;
    }
    public SyncSkillUsingStateToClientPacket(boolean isUsingSkill) {
        this.isUsingSkill = isUsingSkill;
    }
    @Override
    public void encode(SyncSkillUsingStateToClientPacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.isUsingSkill);
    }

    @Override
    public SyncSkillUsingStateToClientPacket decode(FriendlyByteBuf buf) {
        return new SyncSkillUsingStateToClientPacket(buf.readBoolean());
    }

    @Override
    public void handle(SyncSkillUsingStateToClientPacket message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> getSkillOptional(Minecraft.getInstance().player)
                        .ifPresent(p -> p.setUsingSkill(message.isUsingSkill))));
        context.setPacketHandled(true);
    }
}
