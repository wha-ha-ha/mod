package mod.blocks.concretes;

import mod.blocks.abstracts.TextureHelper;
import mod.blocks.abstracts.VaporizeRFBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class RFKillerBlock extends VaporizeRFBlock {

	public RFKillerBlock() {
		super(3512, Material.clay);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack blockStack) {
		return "fags";
	}

	@Override
	public TextureHelper getTextureHelper() {
		return null;
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}

}