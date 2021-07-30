package teletubbies.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teletubbies.Teletubbies;
import teletubbies.client.renderer.RenderHandler;
import teletubbies.client.renderer.entity.model.TiddlytubbyModel;
import teletubbies.entity.baby.TiddlytubbyEntity;

public class TiddlytubbyRenderer extends MobRenderer<TiddlytubbyEntity, TiddlytubbyModel<TiddlytubbyEntity>> {
	private final String name;

	public TiddlytubbyRenderer(Context ctx, String name) {
		super(ctx, new TiddlytubbyModel<>(ctx.bakeLayer(RenderHandler.TIDDLYTUBBY_LAYER)), 0.3F);
		this.name = name;
	}

	@Override
	public ResourceLocation getTextureLocation(TiddlytubbyEntity entity) {
		return new ResourceLocation(Teletubbies.MODID, "textures/entity/" + name + ".png");
	}
}
