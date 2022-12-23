package teletubbies.inventory.container.slot;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SpecificItemSlot extends SlotItemHandler {

	public static final ResourceLocation GRAIN = new ResourceLocation("forge", "grain");
	public static final ResourceLocation MILK = new ResourceLocation("forge", "milk");
	public static final ResourceLocation SUGAR = new ResourceLocation("forge", "sugar");
	public static final ResourceLocation EGG = new ResourceLocation("forge", "egg");
	public static final ResourceLocation BOWL = new ResourceLocation("teletubbies", "bowl");

	private final ResourceLocation item;
	
	public SpecificItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, ResourceLocation item) {
		super(itemHandler, index, xPosition, yPosition);
		this.item = item;
	}
	
    @Override
	public boolean isItemValid(@Nonnull ItemStack stack) {
    	return ItemTags.getCollection().getOrCreate(item).contains(stack.getItem());
    }
}
