// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package jp.makizakao.project_gi.entity.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import jp.makizakao.project_gi.ProjectGIMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class TravelerWindSkillEntityModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ProjectGIMod.getLocation.apply("traveller_wind_skill_entity_layer"), "main");
	private final ModelPart core;

	public TravelerWindSkillEntityModel(ModelPart root) {
		this.core = root.getChild("core");
	}
	public ModelPart getCore() {
		return core;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition one = core.addOrReplaceChild("one", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1989F, -0.5F, -1.0F, 0.3978F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -0.5F, -0.1989F, 2.0F, 1.0F, 0.3978F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r1 = one.addOrReplaceChild("hexadecagon_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, -0.1989F, 2.0F, 1.0F, 0.3978F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1989F, -0.5F, -1.0F, 0.3978F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r2 = one.addOrReplaceChild("hexadecagon_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, -0.1989F, 2.0F, 1.0F, 0.3978F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1989F, -0.5F, -1.0F, 0.3978F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r3 = one.addOrReplaceChild("hexadecagon_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1989F, -0.5F, -1.0F, 0.3978F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r4 = one.addOrReplaceChild("hexadecagon_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1989F, -0.5F, -1.0F, 0.3978F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition two = core.addOrReplaceChild("two", CubeListBuilder.create().texOffs(0, 0).addBox(-0.179F, -0.7F, -0.9F, 0.358F, 1.4F, 1.8F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.9F, -0.7F, -0.179F, 1.8F, 1.4F, 0.358F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r5 = two.addOrReplaceChild("hexadecagon_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9F, -0.7F, -0.179F, 1.8F, 1.4F, 0.358F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.179F, -0.7F, -0.9F, 0.358F, 1.4F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r6 = two.addOrReplaceChild("hexadecagon_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9F, -0.7F, -0.179F, 1.8F, 1.4F, 0.358F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.179F, -0.7F, -0.9F, 0.358F, 1.4F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r7 = two.addOrReplaceChild("hexadecagon_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-0.179F, -0.7F, -0.9F, 0.358F, 1.4F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r8 = two.addOrReplaceChild("hexadecagon_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-0.179F, -0.7F, -0.9F, 0.358F, 1.4F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition three = core.addOrReplaceChild("three", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1591F, -0.8F, -0.8F, 0.3183F, 1.6F, 1.6F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.8F, -0.8F, -0.1591F, 1.6F, 1.6F, 0.3183F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r9 = three.addOrReplaceChild("hexadecagon_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-0.8F, -0.8F, -0.1591F, 1.6F, 1.6F, 0.3183F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1591F, -0.8F, -0.8F, 0.3183F, 1.6F, 1.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r10 = three.addOrReplaceChild("hexadecagon_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-0.8F, -0.8F, -0.1591F, 1.6F, 1.6F, 0.3183F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1591F, -0.8F, -0.8F, 0.3183F, 1.6F, 1.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r11 = three.addOrReplaceChild("hexadecagon_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1591F, -0.8F, -0.8F, 0.3183F, 1.6F, 1.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r12 = three.addOrReplaceChild("hexadecagon_r12", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1591F, -0.8F, -0.8F, 0.3183F, 1.6F, 1.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition four = core.addOrReplaceChild("four", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1392F, -0.85F, -0.7F, 0.2785F, 1.7F, 1.4F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.7F, -0.85F, -0.1392F, 1.4F, 1.7F, 0.2785F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r13 = four.addOrReplaceChild("hexadecagon_r13", CubeListBuilder.create().texOffs(0, 0).addBox(-0.7F, -0.85F, -0.1392F, 1.4F, 1.7F, 0.2785F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1392F, -0.85F, -0.7F, 0.2785F, 1.7F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r14 = four.addOrReplaceChild("hexadecagon_r14", CubeListBuilder.create().texOffs(0, 0).addBox(-0.7F, -0.85F, -0.1392F, 1.4F, 1.7F, 0.2785F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1392F, -0.85F, -0.7F, 0.2785F, 1.7F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r15 = four.addOrReplaceChild("hexadecagon_r15", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1392F, -0.85F, -0.7F, 0.2785F, 1.7F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r16 = four.addOrReplaceChild("hexadecagon_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1392F, -0.85F, -0.7F, 0.2785F, 1.7F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition five = core.addOrReplaceChild("five", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1193F, -0.9F, -0.6F, 0.2387F, 1.8F, 1.2F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.6F, -0.9F, -0.1193F, 1.2F, 1.8F, 0.2387F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hexadecagon_r17 = five.addOrReplaceChild("hexadecagon_r17", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, -0.9F, -0.1193F, 1.2F, 1.8F, 0.2387F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1193F, -0.9F, -0.6F, 0.2387F, 1.8F, 1.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition hexadecagon_r18 = five.addOrReplaceChild("hexadecagon_r18", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, -0.9F, -0.1193F, 1.2F, 1.8F, 0.2387F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.1193F, -0.9F, -0.6F, 0.2387F, 1.8F, 1.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition hexadecagon_r19 = five.addOrReplaceChild("hexadecagon_r19", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1193F, -0.9F, -0.6F, 0.2387F, 1.8F, 1.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition hexadecagon_r20 = five.addOrReplaceChild("hexadecagon_r20", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1193F, -0.9F, -0.6F, 0.2387F, 1.8F, 1.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}