package jp.makizakao.project_gi.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TestPacket implements IProjectGIPacket<TestPacket> {
    @Override
    public void encode(TestPacket msg, FriendlyByteBuf buf) {
    }

    @Override
    public TestPacket decode(FriendlyByteBuf buf) {
        return new TestPacket();
    }

    @Override
    public void handle(TestPacket message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                System.out.println("TestPacket received");
            });
        });
        context.setPacketHandled(true);
    }
}
