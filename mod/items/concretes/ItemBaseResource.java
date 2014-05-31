package mod.items.concretes;

import ic2.core.util.StackUtil;
import mod.entities.EntityItemLurker;
import mod.items.ModItemInfo;
import mod.items.abstracts.ThisModItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemBaseResource extends ThisModItem {
	
	public ItemBaseResource(int par1, String iconName, String unLocName) {
		super(par1, iconName, unLocName);
	}
	
	@Override
	public int getSubItemCount() {
		return 4;
	}
	
	@Override
	public String getOreDictionaryName(int damageValue) {
		switch(damageValue) {
			case 0:
				return ModItemInfo.VESSEL_OREDICT;
			case 1:
				return ModItemInfo.VESSELSLASHED_OREDICT;
			case 2:
				return ModItemInfo.VESSELOFFCUTS_OREDICT;
			case 3:
				return ModItemInfo.VESSELCRUSHED_OREDICT;
		}
		return null;
	}

	@Override
	public int[] getDamagesForOreDict() {
		return new int[]{0, 1, 2, 3};
	}

	@Override
	public boolean doesSubItemWantToBeSeen(int damage) {
		return damage == 0;
	}
	
}