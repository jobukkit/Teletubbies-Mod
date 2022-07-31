package com.rexbas.teletubbies.entity.passive;

import com.rexbas.teletubbies.init.TeletubbiesEntityTypes;
import com.rexbas.teletubbies.init.TeletubbiesItems;
import com.rexbas.teletubbies.init.TeletubbiesSounds;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class PoEntity extends TeletubbyEntity {

	public PoEntity(EntityType<? extends CreatureEntity> type, World world) {
		super(type, world);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return TeletubbiesSounds.ENTITY_PO_VOICE.get();
	}
	
	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(difficulty);
		int i = this.random.nextInt(10);
		ItemStack stack = null;
		int damage = 0;
		switch (i) {
		case 0:
			stack = new ItemStack(TeletubbiesItems.PO_BIB.get());
			damage = this.random.nextInt(stack.getMaxDamage() - 5 + 1) + 5;
			stack.setDamageValue(damage);
			this.setItemSlot(EquipmentSlotType.CHEST, stack);
			break;
		case 1:
			stack = new ItemStack(TeletubbiesItems.PO_HELMET.get());
			damage = this.random.nextInt(stack.getMaxDamage() - 5 + 1) + 5;
			stack.setDamageValue(damage);
			this.setItemSlot(EquipmentSlotType.HEAD, stack);
			break;
		}
		
		if (this.random.nextInt(20) == 0) {
			this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(TeletubbiesItems.PO_SCOOTER.get()));
		}
	}

	@Override
	public EntityType<?> getZombie() {
		return TeletubbiesEntityTypes.PO_ZOMBIE.get();
	}
}