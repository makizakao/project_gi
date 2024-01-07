package jp.makizakao.project_gi.item;

import jp.makizakao.project_gi.entity.TravelerWindSkillEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TestItem extends Item {
    public TestItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide()) {
            var pos = pPlayer.getEyePosition().add(0, -0.5, 0)
                    .add(pPlayer.getLookAngle().multiply(4.0, 4.0, 4.0));
            var skill = new TravelerWindSkillEntity("", (ServerLevel) pLevel, pos);
            pLevel.addFreshEntity(skill);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
