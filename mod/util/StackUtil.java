package mod.util;

import net.minecraft.item.ItemStack;

public class StackUtil {

	public static ItemStack getDuplicateStackOfSize(int size, ItemStack sourceStack) {
		ItemStack out = sourceStack.copy();
		out.stackSize = size;
		return out;
	}
	
	public static boolean canStacksCombineToOne(ItemStack stack1, ItemStack stack2) {
		if(stack1 != null && stack2 != null) {
			if(canItemsStackTogether(stack1, stack2)) {
				return stack1.stackSize + stack2.stackSize <= stack1.getMaxStackSize();
			}
		}
		return false;
	}
	
	public static ItemStack[] combineStackIntoAnother(ItemStack sourceStack, ItemStack destinationStack) {
		
		if(sourceStack != null) {
			
			if(destinationStack == null) {
				
				return new ItemStack[]{null, sourceStack};
				
			}else if(canItemsStackTogether(sourceStack, destinationStack)) {

				int sizeSum = sourceStack.stackSize + destinationStack.stackSize;
				
				if(sizeSum <= destinationStack.getMaxStackSize()) {
					destinationStack.stackSize = sizeSum;
					return new ItemStack[]{null, destinationStack};
				}else {
					int leftovers = sizeSum - destinationStack.getMaxStackSize();
					sourceStack.stackSize = leftovers;
					destinationStack.stackSize = destinationStack.getMaxStackSize();
					return new ItemStack[]{sourceStack, destinationStack};
				}
				
			}
			
		}
		
		return null;
		
	}
	
	public static boolean canItemsStackTogether(ItemStack stack1, ItemStack stack2) {
		
		if(stack1 == null ^ stack2 == null) {
			return true;
		}
		
		if(stack1 != null && stack2 != null) {
			ItemStack dummy1 = getDuplicateStackOfSize(1, stack1);
			ItemStack dummy2 = getDuplicateStackOfSize(1, stack2);
			return ItemStack.areItemStacksEqual(dummy1, dummy2);
		}
		
		return false;
		
	}
	
}
