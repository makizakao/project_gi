package jp.makizakao.project_gi.networking;

import jp.makizakao.project_gi.networking.packet.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import javax.annotation.Nullable;
import java.util.Optional;

import static jp.makizakao.project_gi.ProjectGIMod.getLocation;

public class PacketHandler {
    public static final String PROTOCOL_VERSION = "1";
    private static final String LOCATION_PATH = "messages";
    private static int packetId = 0;
    private static SimpleChannel instance;

    public static void registerMessages() {
        instance = NetworkRegistry.ChannelBuilder
                .named(getLocation.apply(LOCATION_PATH))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();
        register(TestPacket.class, new TestPacket(), NetworkDirection.PLAY_TO_CLIENT);
        register(ElementalResonancePacket.class, new ElementalResonancePacket(), NetworkDirection.PLAY_TO_SERVER);
        register(UseSkillPacket.class, new UseSkillPacket(), NetworkDirection.PLAY_TO_SERVER);
        register(SkillCancelPacket.class, new SkillCancelPacket(), NetworkDirection.PLAY_TO_SERVER);
        register(SyncSkillUsingStateToClientPacket.class, new SyncSkillUsingStateToClientPacket(), NetworkDirection.PLAY_TO_CLIENT);
    }

    private static <T> void register(Class<T> msgClass, IProjectGIPacket<T> msg, @Nullable NetworkDirection direction)
    {
        instance.registerMessage(packetId++, msgClass, msg::encode, msg::decode, msg::handle, Optional.ofNullable(direction));
    }
    private static <T> void register(Class<T> msgClass, IProjectGIPacket<T> msg)
    {
        instance.registerMessage(packetId++, msgClass, msg::encode, msg::decode, msg::handle);
    }
    public static <T> void sendToServer(T msg) {
        instance.sendToServer(msg);
    }

    public static <T> void sendToPlayer(T msg, ServerPlayer player) {
        instance.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }
}
