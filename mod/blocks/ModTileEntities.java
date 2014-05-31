package mod.blocks;

import mod.blocks.concretes.TileSelfBreakingBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

	public static void initTileEntities() {
		GameRegistry.registerTileEntity(TileSelfBreakingBlock.class, "ModSelfBreakingBlock");
	}
	
}