package mod.blocks.concretes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileSelfBreakingBlock extends TileEntity {

	private int timer = 0;
	
	@Override
	public void updateEntity() {
		if(worldObj.isRemote) {
			return;
		}
		if(timer <= 0) {
			this.worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		}else {
			timer--;
		}
	}
	
	public void setTimer(int ticksToLive) {
		timer = ticksToLive;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("timer", (short)timer);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		timer = nbt.getShort("timer");
	}
	
}