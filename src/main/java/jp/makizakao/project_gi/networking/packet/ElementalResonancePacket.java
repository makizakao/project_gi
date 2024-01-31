package jp.makizakao.project_gi.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class ElementalResonancePacket implements IProjectGIPacket<ElementalResonancePacket> {

    public ElementalResonancePacket() {
    }


    @Override
    public void encode(ElementalResonancePacket msg, FriendlyByteBuf buf) {

    }

    @Override
    public ElementalResonancePacket decode(FriendlyByteBuf buf) {
        return new ElementalResonancePacket();
    }

    @Override
    public void handle(ElementalResonancePacket message, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            Optional.ofNullable(context.getSender())
                    .ifPresent(p -> {
                        ServerLevel world = p.serverLevel();
                        EntityType.COW.spawn(
                                world, (ItemStack) null, null, p.blockPosition(),
                                MobSpawnType.COMMAND, true, false);
                    });
        });
    }
}
