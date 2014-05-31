package mod.items.concretes;

import mod.items.ModItemInfo;
import mod.items.abstracts.ThisModItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlade extends ThisModItem {

	public ItemBlade(int par1, String iconName, String unlocalizedName) {
		super(par1, iconName, unlocalizedName);
		setMaxStackSize(1);
		setNoRepair();
		setFull3D();
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(!attacker.worldObj.isRemote && target != null && attacker != null) {
			
			int y = (int)attacker.posY;
			while(y > 0 && attacker.worldObj.isAirBlock((int)attacker.posX, y--, (int)attacker.posZ)) {
				//loop down until you hit ground
			}
			attacker.setHealth(2);
			attacker.attackEntityFrom(DamageSource.fallingBlock, 10);
			if(attacker.getHealth() > 0) {
				target.setHealth(Math.max(1, target.getHealth() - 40));
				target.attackEntityFrom(DamageSource.fallingBlock, 10);
				
				attacker.addPotionEffect(new PotionEffect(Potion.blindness.id, 50, 10));
				attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 10));
			}

		}
		attacker.motionY = -50;
		return true;
	}
	
	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}
	
	@Override
	public ItemStack getContainerItemStack(ItemStack source) {
		return source;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack s) {
		return "item." + this.unlocalizedName;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int dmg) {
		return icons[0];
	}
	
	@Override
	public String getOreDictionaryName(int damageValue) {
		return ModItemInfo.BLADE_OREDICT;
	}

	@Override
	public int[] getDamagesForOreDict() {
		return new int[]{Short.MAX_VALUE};
	}

	@Override
	public boolean doesSubItemWantToBeSeen(int damage) {
		return true;
	}

	@Override
	public int getSubItemCount() {
		return 1;
	}

}