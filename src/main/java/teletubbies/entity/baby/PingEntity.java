package teletubbies.entity.baby;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class PingEntity extends TiddlytubbyEntity {

	public PingEntity(EntityType<? extends PathfinderMob> type, Level world) {
		super(type, world);
	}
}