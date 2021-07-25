package teletubbies.entity.baby;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import teletubbies.init.TeletubbiesSounds;

public class MiMiEntity extends TiddlytubbyEntity {

	public MiMiEntity(EntityType<? extends PathfinderMob> type, Level world) {
		super(type, world);
	}
	
	@Override
    public SoundEvent getAmbientSound() {
		return TeletubbiesSounds.ENTITY_MIMI_VOICE.get();
    }
}
