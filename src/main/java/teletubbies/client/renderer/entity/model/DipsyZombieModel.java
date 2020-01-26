package teletubbies.client.renderer.entity.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teletubbies.entity.monster.DipsyZombieEntity;

@OnlyIn(Dist.CLIENT)
public class DipsyZombieModel extends TeletubbyZombieModel<DipsyZombieEntity> {
	public ModelRenderer stick;

	public DipsyZombieModel() {
		stick = new ModelRenderer(this);
		stick.setRotationPoint(0.0F, 24.0F, 0.0F);
		stick.addBox(null, -1.0F, -38.0F, -1.0F, 2, 6, 2, 0.0F, 27, 20);
		
		bipedHead.addChild(stick);
	}
}
