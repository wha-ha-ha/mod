package mod.blocks.concretes;

import mod.blocks.abstracts.ThisModBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemThisModBlock extends ItemBlockWithMetadata {
	
	private ThisModBlock block;
	
	public ItemThisModBlock(int par1, Block par2Block) {
		super(par1, par2Block);
		block = (ThisModBlock)par2Block;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return "tile."+block.getUnlocalizedName(par1ItemStack);
	}
	
}