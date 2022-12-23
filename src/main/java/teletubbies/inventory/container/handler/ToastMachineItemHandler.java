package teletubbies.inventory.container.handler;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.items.ItemStackHandler;
import teletubbies.inventory.container.slot.SpecificItemSlot;

public class ToastMachineItemHandler extends ItemStackHandler {

	public ToastMachineItemHandler() {
		super(1);
	}
	
    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return ItemTags.getCollection().getOrCreate(SpecificItemSlot.GRAIN).contains(stack.getItem());
    }
}
