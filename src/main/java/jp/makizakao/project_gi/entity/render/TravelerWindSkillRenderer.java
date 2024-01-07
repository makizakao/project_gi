package jp.makizakao.project_gi.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import jp.makizakao.project_gi.ProjectGIMod;
import jp.makizakao.project_gi.entity.TravelerWindSkillEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class TravelerWindSkillRenderer extends EntityRenderer<TravelerWindSkillEntity> {
    public static final String TEXTURE_LOCATION = "textures/entity/traveler_wind_skill_entity.png";
    private static final float SCALE = 0.5f;
    private static final RenderType RENDER_TYPE = RenderType
            .itemEntityTranslucentCull(ProjectGIMod.getLocation.apply(TEXTURE_LOCATION));

    public TravelerWindSkillRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.0f;
        this.shadowStrength = 0.0f;
    }

    @Override
    protected boolean shouldShowName(@NotNull TravelerWindSkillEntity pEntity) {
        return false;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull TravelerWindSkillEntity travellerWindSkillEntity) {
        return ProjectGIMod.getLocation.apply(TEXTURE_LOCATION);
    }

    @Override
    protected int getBlockLightLevel(TravelerWindSkillEntity pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    public void render(@NotNull TravelerWindSkillEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        float f = 0.0F;
        float f1 = 1.0F;
        float f2 = 0.0F;
        float f3 = 1.0F;
        float f8 = ((float)pEntity.tickCount + pPartialTicks) / 2.0F;
        int colorChangeValue = (int)(Mth.clamp((Mth.sin(f8) + 1.0F) * 63.0F, 0.0F, 63.0F));
        int red = 192 + colorChangeValue;
        int green = 255;
        int blue = 192 + colorChangeValue;
        pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        pMatrixStack.scale(SCALE, SCALE, SCALE);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RENDER_TYPE);
        PoseStack.Pose posestack$pose = pMatrixStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        vertex(vertexconsumer, matrix4f, matrix3f, -0.5F, -0.25F, red, green, blue, f, f3, pPackedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, 0.5F, -0.25F, red, green, blue, f1, f3, pPackedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, 0.5F, 0.75F, red, green, blue, f1, f2, pPackedLight);
        vertex(vertexconsumer, matrix4f, matrix3f, -0.5F, 0.75F, red, green, blue, f, f2, pPackedLight);
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    private static void vertex(VertexConsumer pConsumer, Matrix4f pMatrix, Matrix3f pMatrixNormal, float pX, float pY, int pRed, int pGreen, int pBlue, float pTexU, float pTexV, int pPackedLight) {
        pConsumer.vertex(pMatrix, pX, pY, 0.0F).color(pRed, pGreen, pBlue, 192).uv(pTexU, pTexV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(pMatrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }
}
