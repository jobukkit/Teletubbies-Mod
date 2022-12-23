package teletubbies.inventory.container.handler;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.items.ItemStackHandler;
import teletubbies.inventory.container.slot.SpecificItemSlot;
import teletubbies.item.ItemList;

public class CustardMachineItemHandler extends ItemStackHandler {
	
	public CustardMachineItemHandler(int size) {
		super(size);
	}
	
    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
    	if (this.stacks.size() == 7) {
	        switch (slot) {
	        case 0:
	        case 1:
	        case 2:
	        case 3:
	            return ItemTags.getCollection().getOrCreate(SpecificItemSlot.MILK).contains(stack.getItem());
	        case 4:
	            return ItemTags.getCollection().getOrCreate(SpecificItemSlot.SUGAR).contains(stack.getItem());
	        case 5:
	            return ItemTags.getCollection().getOrCreate(SpecificItemSlot.EGG).contains(stack.getItem());
	        case 6:
	            return stack.getItem().equals(ItemList.BOWL);
	        }
    	}
    	else if (this.stacks.size() == 5) {
    		switch (slot) {
	        case 0:
	        case 1:
	        case 2:
	        case 3:
	            return stack.getItem().equals(ItemList.CUSTARD);
	        case 4:
	            return stack.getItem().equals(Items.BUCKET);
	        }
    	}
    		
        return false;
    }
}
