package mod.items.abstracts;

import java.util.List;

import mod.ModInformation;
import mod.TheMod;
import mod.items.ModItemInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ThisModItem extends Item {

	private String iconName;
	
	@SideOnly(Side.CLIENT)
	protected Icon[] icons;
	
	private int subItemCount;
	
	public ThisModItem(int par1, String iconName, String unlocalizedName) {
		super(par1);
		this.iconName = iconName;
		setUnlocalizedName(unlocalizedName);
		subItemCount = getSubItemCount();
		setCreativeTab(TheMod.TheModCreativeTab);
	}
	
	public abstract String getOreDictionaryName(int damageValue);
	
	public abstract int[] getDamagesForOreDict();
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icons = new Icon[subItemCount];
		for(int i = 0; i < subItemCount; i++) {
			icons[i] = register.registerIcon(ModInformation.TEXTURE_LOCATION+":"+iconName+i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for(int i = 0; i < subItemCount; i++) {
			if(doesSubItemWantToBeSeen(i)) {
				list.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
		return false;
	}
	
	public abstract boolean doesSubItemWantToBeSeen(int damage);

	@Override
	public String getUnlocalizedName(ItemStack s) {
		return super.getUnlocalizedName()+s.getItemDamage();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int dmg) {
		return icons[dmg];
	}
	
	public abstract int getSubItemCount();
	
}