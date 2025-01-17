package teletubbies.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import teletubbies.entity.passive.NooNooEntity;

public class NooNooModel extends EntityModel<NooNooEntity> {
	public ModelRenderer feet;
	public ModelRenderer body;
	public ModelRenderer brush;
	public ModelRenderer hose;
	public ModelRenderer rightEye;
	public ModelRenderer leftEye;

	public NooNooModel() {
		textureWidth = 128;
		textureHeight = 64;

		feet = new ModelRenderer(this);
		feet.setRotationPoint(0.5F, 22.25F, 4.0F);
		feet.addBox(null, -4.5F, -1.25F, -10.0F, 9, 1, 20, 0.0F, 66, 43);
		feet.addBox(null, -5.5F, -0.25F, -11.0F, 11, 2, 22, 0.0F, 46, 7);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.5F, 15.5F, 9.2F);
		body.addBox(null, -6.5F, -4.5F, -16.2F, 1, 9, 22, 0.0F, 0, 0);
		body.addBox(null, 5.5F, -4.5F, -16.2F, 1, 9, 22, 0.0F, 0, 0);
		body.addBox(null, -5.5F, -5.5F, -16.2F, 11, 11, 22, 0.0F, 0, 31);
		body.addBox(null, -3.5F, -3.5F, 5.8F, 7, 7, 2, 0.0F, 0, 31);
		body.addBox(null, -1.5F, -1.5F, 7.8F, 3, 3, 2, 0.0F, 0, 40);

		brush = new ModelRenderer(this);
		brush.setRotationPoint(0.5F, 10.0F, -2.5F);
		brush.addBox(null, -0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F, 10, 50);
		brush.addBox(null, -1.5F, -3.0F, -1.5F, 3, 1, 3, 0.0F, 10, 46);

		hose = new ModelRenderer(this);
		hose.setRotationPoint(0.5F, 17.0F, -8.0F);
		hose.addBox(null, -1.5F, -2.0F, -8.0F, 3, 3, 9, 0.0F, 66, 31);
		hose.addBox(null, -1.5F, 1.0F, -8.0F, 3, 5, 3, 0.0F, 66, 43);
		hose.addBox(null, -2.5F, 6.0F, -9.0F, 5, 1, 5, 0.0F, 66, 51);

		rightEye = new ModelRenderer(this);
		rightEye.setRotationPoint(-1.5F, 11.5F, -8.0F);
		rightEye.addBox(null, 0.5F, -0.5F, -3.0F, 1, 1, 4, 0.0F, 0, 0);
		rightEye.addBox(null, -1.5F, -0.5F, -3.0F, 1, 1, 4, 0.0F, 0, 0);
		rightEye.addBox(null, -0.5F, -1.5F, -3.0F, 1, 3, 4, 0.0F, 0, 5);

		leftEye = new ModelRenderer(this);
		leftEye.setRotationPoint(2.5F, 11.5F, -8.0F);
		leftEye.addBox(null, 0.5F, -0.5F, -3.0F, 1, 1, 4, 0.0F, 0, 0);
		leftEye.addBox(null, -1.5F, -0.5F, -3.0F, 1, 1, 4, 0.0F, 0, 0);
		leftEye.addBox(null, -0.5F, -1.5F, -3.0F, 1, 3, 4, 0.0F, 0, 5);
	}

	@Override
	public void setRotationAngles(NooNooEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		hose.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount * 0.7F;

		rightEye.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		rightEye.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);

		leftEye.rotateAngleX = rightEye.rotateAngleX;
		leftEye.rotateAngleY = rightEye.rotateAngleY;	
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		feet.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		brush.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		hose.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		rightEye.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		leftEye.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}
