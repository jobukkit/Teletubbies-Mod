package teletubbies.client.renderer.item.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TutuModel extends BipedModel<LivingEntity> {
	
	public static TutuModel model = new TutuModel();
	
	private final RendererModel tutu;
	private final RendererModel segment1;
	private final RendererModel plane0;
	private final RendererModel plane1;
	private final RendererModel segment2;
	private final RendererModel plane2;
	private final RendererModel plane3;
	private final RendererModel segment3;
	private final RendererModel plane4;
	private final RendererModel plane5;
	private final RendererModel segment4;
	private final RendererModel plane6;
	private final RendererModel plane7;
	private final RendererModel segment5;
	private final RendererModel plane8;
	private final RendererModel plane9;
	private final RendererModel segment6;
	private final RendererModel plane10;
	private final RendererModel plane11;
	private final RendererModel segment7;
	private final RendererModel plane12;
	private final RendererModel plane13;
	private final RendererModel segment8;
	private final RendererModel plane14;
	private final RendererModel plane15;

	public TutuModel() {
		textureWidth = 32;
		textureHeight = 32;

		tutu = new RendererModel(this);
		tutu.setRotationPoint(0.0F, 9.0F, 0.0F);

		segment1 = new RendererModel(this);
		segment1.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotationAngle(segment1, -0.3491F, 0.0F, 0.0F);
		tutu.addChild(segment1);

		plane0 = new RendererModel(this);
		plane0.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane0, 0.0F, -0.7854F, 0.0F);
		segment1.addChild(plane0);
		plane0.cubeList.add(new ModelBox(plane0, 0, 0, -0.7582F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		plane1 = new RendererModel(this);
		plane1.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane1, 0.0F, 0.7854F, 0.0F);
		segment1.addChild(plane1);
		plane1.cubeList.add(new ModelBox(plane1, 0, 0, -3.2418F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		segment2 = new RendererModel(this);
		segment2.setRotationPoint(2.0F, -2.0F, 0.0F);
		setRotationAngle(segment2, -0.3491F, -0.4363F, 0.0F);
		tutu.addChild(segment2);

		plane2 = new RendererModel(this);
		plane2.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane2, 0.0F, -0.7854F, 0.0F);
		segment2.addChild(plane2);
		plane2.cubeList.add(new ModelBox(plane2, 0, 10, -0.7582F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		plane3 = new RendererModel(this);
		plane3.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane3, 0.0F, 0.7854F, 0.0F);
		segment2.addChild(plane3);
		plane3.cubeList.add(new ModelBox(plane3, 0, 10, -3.2418F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		segment3 = new RendererModel(this);
		segment3.setRotationPoint(-2.0F, -2.0F, 0.0F);
		setRotationAngle(segment3, -0.3491F, 0.4363F, 0.0F);
		tutu.addChild(segment3);

		plane4 = new RendererModel(this);
		plane4.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane4, 0.0F, -0.7854F, 0.0F);
		segment3.addChild(plane4);
		plane4.cubeList.add(new ModelBox(plane4, 8, 0, -0.7582F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		plane5 = new RendererModel(this);
		plane5.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane5, 0.0F, 0.7854F, 0.0F);
		segment3.addChild(plane5);
		plane5.cubeList.add(new ModelBox(plane5, 8, 0, -3.2418F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		segment4 = new RendererModel(this);
		segment4.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotationAngle(segment4, -0.3491F, 3.1416F, 0.0F);
		tutu.addChild(segment4);

		plane6 = new RendererModel(this);
		plane6.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane6, 0.0F, -0.7854F, 0.0F);
		segment4.addChild(plane6);
		plane6.cubeList.add(new ModelBox(plane6, 8, 10, -0.7582F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		plane7 = new RendererModel(this);
		plane7.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane7, 0.0F, 0.7854F, 0.0F);
		segment4.addChild(plane7);
		plane7.cubeList.add(new ModelBox(plane7, 8, 10, -3.2418F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		segment5 = new RendererModel(this);
		segment5.setRotationPoint(-2.0F, -2.0F, 0.0F);
		setRotationAngle(segment5, -0.3491F, 2.7053F, 0.0F);
		tutu.addChild(segment5);

		plane8 = new RendererModel(this);
		plane8.setRotationPoint(-2.0F, 17.0F, -2.0F);
		setRotationAngle(plane8, 0.0F, -0.7854F, 0.0F);
		segment5.addChild(plane8);
		plane8.cubeList.add(new ModelBox(plane8, 16, 0, 0.6561F, -14.0603F, -2.1724F, 4, 10, 0, 0.0F, false));

		plane9 = new RendererModel(this);
		plane9.setRotationPoint(-2.0F, 17.0F, -2.0F);
		setRotationAngle(plane9, 0.0F, 0.7854F, 0.0F);
		segment5.addChild(plane9);
		plane9.cubeList.add(new ModelBox(plane9, 16, 0, -1.8276F, -14.0603F, 0.6561F, 4, 10, 0, 0.0F, false));

		segment6 = new RendererModel(this);
		segment6.setRotationPoint(2.0F, -2.0F, 0.0F);
		setRotationAngle(segment6, -0.3491F, -2.7053F, 0.0F);
		tutu.addChild(segment6);

		plane10 = new RendererModel(this);
		plane10.setRotationPoint(2.0F, 17.0F, -2.0F);
		setRotationAngle(plane10, 0.0F, -0.7854F, 0.0F);
		segment6.addChild(plane10);
		plane10.cubeList.add(new ModelBox(plane10, 16, 10, -2.1724F, -14.0603F, 0.6561F, 4, 10, 0, 0.0F, false));

		plane11 = new RendererModel(this);
		plane11.setRotationPoint(2.0F, 17.0F, -2.0F);
		setRotationAngle(plane11, 0.0F, 0.7854F, 0.0F);
		segment6.addChild(plane11);
		plane11.cubeList.add(new ModelBox(plane11, 16, 10, -4.6561F, -14.0603F, -2.1724F, 4, 10, 0, 0.0F, false));

		segment7 = new RendererModel(this);
		segment7.setRotationPoint(2.0F, -2.0F, 0.0F);
		setRotationAngle(segment7, 0.0F, -1.5708F, -0.3491F);
		tutu.addChild(segment7);

		plane12 = new RendererModel(this);
		plane12.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane12, 0.0F, -0.7854F, 0.0F);
		segment7.addChild(plane12);
		plane12.cubeList.add(new ModelBox(plane12, 24, 0, -0.7582F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		plane13 = new RendererModel(this);
		plane13.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane13, 0.0F, 0.7854F, 0.0F);
		segment7.addChild(plane13);
		plane13.cubeList.add(new ModelBox(plane13, 24, 0, -3.2418F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		segment8 = new RendererModel(this);
		segment8.setRotationPoint(-2.0F, -2.0F, 0.0F);
		setRotationAngle(segment8, 0.0F, 1.5708F, 0.3491F);
		tutu.addChild(segment8);

		plane14 = new RendererModel(this);
		plane14.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane14, 0.0F, -0.7854F, 0.0F);
		segment8.addChild(plane14);
		plane14.cubeList.add(new ModelBox(plane14, 24, 10, -0.7582F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));

		plane15 = new RendererModel(this);
		plane15.setRotationPoint(0.0F, 17.0F, -2.0F);
		setRotationAngle(plane15, 0.0F, 0.7854F, 0.0F);
		segment8.addChild(plane15);
		plane15.cubeList.add(new ModelBox(plane15, 24, 10, -3.2418F, -14.0603F, -0.7582F, 4, 10, 0, 0.0F, false));
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		tutu.render(scale);
	}
	
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		if (entity.getPose() == Pose.SNEAKING) {
			tutu.rotationPointY = 8.0F;
			tutu.rotationPointZ = 2.0F;
			tutu.rotateAngleX = (float) (Math.PI * 0.16667F);
		} else {
			tutu.rotationPointY = 9.0F;
			tutu.rotationPointZ = 0;
			tutu.rotateAngleX = 0;
		}
		
		final float f1 = 0.065F;
		final float f2 = 0.125F;
		segment1.rotateAngleX = -0.3491F + f1 * MathHelper.cos(f2 * ageInTicks);
		segment2.rotateAngleX = -0.3491F + f1 * MathHelper.cos(f2 * ageInTicks);
		segment3.rotateAngleX = -0.3491F + f1 * MathHelper.cos(f2 * ageInTicks);
		segment4.rotateAngleX = -0.3491F + f1 * MathHelper.cos(f2 * ageInTicks);
		segment5.rotateAngleX = -0.3491F + f1 * MathHelper.cos(f2 * ageInTicks);
		segment6.rotateAngleX = -0.3491F + f1 * MathHelper.cos(f2 * ageInTicks);

		segment7.rotateAngleZ = -0.3491F + f1 * MathHelper.cos(f2 * ageInTicks);
		segment8.rotateAngleZ = 0.3491F - f1 * MathHelper.cos(f2 * ageInTicks);
	}
}