package teletubbies.inventory.container.handler;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.items.ItemStackHandler;

public class TinkyWinkyBagItemHandler extends ItemStackHandler {
	
	private ItemStack stack;
	
	public TinkyWinkyBagItemHandler(ItemStack stack) {
		super(54);
		this.stack = stack;
	}
	
    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        save();
    }
    
    public void save() {
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.put("Inventory", serializeNBT());
    }
    
    public void load() {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains("Inventory")) {
            deserializeNBT(nbt.getCompound("Inventory"));
        }
    }
    
	public void fillInventory(LootTable table, LootContext context) {
		List<ItemStack> list = table.generate(context);
		Random rand = context.getRandom();
		List<Integer> list1 = getEmptySlotsRandomized(rand);
		shuffleItems(list, list1.size(), rand);

		for (ItemStack itemstack : list) {
			if (list1.isEmpty()) {
				return;
			}

			if (itemstack.isEmpty()) {
				setStackInSlot(list1.remove(list1.size() - 1), ItemStack.EMPTY);
			} else {
				setStackInSlot(list1.remove(list1.size() - 1), itemstack);
			}
		}
	}
	
	private List<Integer> getEmptySlotsRandomized(Random rand) {
		List<Integer> list = Lists.newArrayList();

		for (int i = 0; i < stacks.size(); ++i) {
			if (getStackInSlot(i).isEmpty()) {
				list.add(i);
			}
		}

		Collections.shuffle(list, rand);
		return list;
	}
	
	private void shuffleItems(List<ItemStack> stacks, int size, Random rand) {
		List<ItemStack> list = Lists.newArrayList();
		Iterator<ItemStack> iterator = stacks.iterator();

		while (iterator.hasNext()) {
			ItemStack itemstack = iterator.next();
			if (itemstack.isEmpty()) {
				iterator.remove();
			} else if (itemstack.getCount() > 1) {
				list.add(itemstack);
				iterator.remove();
			}
		}

		while (size - stacks.size() - list.size() > 0 && !list.isEmpty()) {
			ItemStack itemstack2 = list.remove(MathHelper.nextInt(rand, 0, list.size() - 1));
			int i = MathHelper.nextInt(rand, 1, itemstack2.getCount() / 2);
			ItemStack itemstack1 = itemstack2.split(i);
			if (itemstack2.getCount() > 1 && rand.nextBoolean()) {
				list.add(itemstack2);
			} else {
				stacks.add(itemstack2);
			}

			if (itemstack1.getCount() > 1 && rand.nextBoolean()) {
				list.add(itemstack1);
			} else {
				stacks.add(itemstack1);
			}
		}

		stacks.addAll(list);
		Collections.shuffle(stacks, rand);
	}
}
