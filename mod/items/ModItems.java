package mod.items;

import java.util.LinkedList;

import mod.items.abstracts.ThisModItem;
import mod.items.concretes.ItemBaseResource;
import mod.items.concretes.ItemBlade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


public class ModItems {
	
	private static LinkedList<ThisModItem> items = new LinkedList<ThisModItem>();
	
	public static ThisModItem baseResource;
	public static ThisModItem blade;
	
	public static void initItems() {
		items.add((baseResource = new ItemBaseResource(ModItemInfo.BASEITEM_ID, ModItemInfo.BASEITEM_ICON, ModItemInfo.BASEITEM_UNLOCALIZED_NAME)));
		
		items.add((blade = new ItemBlade(ModItemInfo.BLADE_ID, ModItemInfo.BLADE_ICON, ModItemInfo.BLADE_UNLOCALIZED_NAME)));
		blade.setContainerItem(blade);
	}
	
	public static void registerOreDict() {
		for(ThisModItem item : items) {
			for(int damageValue : item.getDamagesForOreDict()) {
				String dictName = item.getOreDictionaryName(damageValue);
				OreDictionary.registerOre(dictName, new ItemStack(item, 1, damageValue));
			}
		}
	}
	
}