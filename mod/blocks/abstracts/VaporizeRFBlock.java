package mod.blocks.abstracts;

import java.util.ArrayList;

import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import mod.ModInformation;
import mod.util.NumberUtil;
import mod.util.PlayerUtil;
import mod.util.interfaces.IFourWayRotatable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class VaporizeRFBlock extends ThisModBlock {

	public VaporizeRFBlock(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livBase, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, livBase, stack);
		vaporizeRedstoneFlux(world, x, y, z);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		vaporizeRedstoneFlux(world, x, y, z);
	}
	
	private void vaporizeRedstoneFlux(World world, int x, int y, int z) {
		if(!world.isRemote) {
			int originX = x;
			int originY = y;
			int originZ = z;
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				x = originX + dir.offsetX;
				y = originY + dir.offsetY;
				z = originZ + dir.offsetZ;
				TileEntity te = world.getBlockTileEntity(x, y, z);
				if(te != null && te instanceof IEnergyHandler) {
					world.setBlockToAir(x, y, z);
					
					Packet250CustomPayload packet = new Packet250CustomPayload();
					packet.channel = ModInformation.CHANNEL;
					ArrayList<Byte> bytes = new ArrayList<Byte>();
					bytes.add((byte)0);
					bytes.addAll(NumberUtil.getByteListFromInt(x));
					bytes.addAll(NumberUtil.getByteListFromInt(y));
					bytes.addAll(NumberUtil.getByteListFromInt(z));
					
					packet.length = bytes.size();
					packet.data = NumberUtil.getByteArrayFromList(bytes);
					
					PacketDispatcher.sendPacketToAllAround(x, y, z, 40, world.provider.dimensionId, packet);
				}
			}
		}
	}

}
