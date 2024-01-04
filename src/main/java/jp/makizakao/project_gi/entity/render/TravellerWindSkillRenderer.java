package jp.makizakao.project_gi.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.entity.TravellerWindSkillEntity;
import jp.makizakao.project_gi.entity.model.TravellerWindSkillEntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TravellerWindSkillRenderer extends LivingEntityRenderer<
        TravellerWindSkillEntity, TravellerWindSkillEntityModel<TravellerWindSkillEntity>> {
    public static final String TEXTURE_LOCATION = "textures/entity/traveller_wind_skill_entity.png";
    private static final float scale = 5.0f;


    public TravellerWindSkillRenderer(EntityRendererProvider.Context pContext,
                                      TravellerWindSkillEntityModel<TravellerWindSkillEntity> pModel,
                                      float pShadowRadius) {
        super(pContext, pModel, pShadowRadius);
    }

    public TravellerWindSkillRenderer(EntityRendererProvider.Context pContext) {
        this(pContext, new TravellerWindSkillEntityModel<>(
                pContext.bakeLayer(TravellerWindSkillEntityModel.LAYER_LOCATION)), 0.1f);
    }

    @Override
    protected boolean shouldShowName(@NotNull TravellerWindSkillEntity pEntity) {
        return false;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull TravellerWindSkillEntity travellerWindSkillEntity) {
        return ProjectGIMod.getLocation.apply(TEXTURE_LOCATION);
    }

    @Override
    public void render(@NotNull TravellerWindSkillEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.scale(scale, scale, scale);
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}