package mod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import mod.blocks.concretes.ItemThisModBlock;
import mod.blocks.concretes.RFKillerBlock;
import net.minecraft.block.Block;

public class ModBlocks {

	public static Block coolBlock;
	
	public static void initBlocks() {
		coolBlock = new RFKillerBlock();
		GameRegistry.registerBlock(coolBlock, ItemThisModBlock.class, "RFDestroyerBlock");
	}
	
}
