package jp.makizakao.project_gi.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public interface IProjectGIPacket<T> {

    void encode(T msg, FriendlyByteBuf buf);
    T decode(FriendlyByteBuf buf);
    void handle(T message, Supplier<NetworkEvent.Context> ctx);

}
