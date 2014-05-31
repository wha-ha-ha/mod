package mod.util;

import java.lang.reflect.Field;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerUtil {
	
	public static int getPlayerFacing(EntityLivingBase player) {
		return MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	}
	
	public static boolean isItemStackOnHotbar(EntityPlayer player, ItemStack s) {
		for(int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
			if(ItemStack.areItemStacksEqual(s, player.inventory.getStackInSlot(i))) {
				return true;
			}
		}
		return false;
	}
	
	public static void setFlammable(EntityPlayer player, boolean flammable) {
		try {
			Field f = Entity.class.getDeclaredField("field_70178_ae");
			f.setAccessible(true);
			f.setBoolean(player, !flammable);
		} catch (Throwable t) {
			try {
				Field f = Entity.class.getDeclaredField("isImmuneToFire");
				f.setAccessible(true);
				f.setBoolean(player, !flammable);
			}catch(Throwable g){}
		}
	}
	
	public static MovingObjectPosition getMOPLookedAtWithRange(double range, EntityPlayer player, boolean includeLiquids) {
		
		World world = player.worldObj;
		float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(world.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
        Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        
        return world.rayTraceBlocks_do_do(vec3, vec31, includeLiquids, false);
        
	}
	
}
