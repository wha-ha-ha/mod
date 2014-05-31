package mod.entities;

import java.util.ArrayList;

import mod.ModInformation;
import mod.util.NumberUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class EntityItemLurker extends EntityLiving implements IMob {

	public EntityItemLurker(World par1World) {
		super(par1World);
	}
	
	@Override
	public void onUpdate() {
		if(!this.isDead && worldObj.rand.nextInt(5) == 0 && worldObj.getBlockLightValue((int)posX, (int)posY+1, (int)posZ) <= 7 && worldObj.isAirBlock((int)posX, (int)posY+1, (int)posZ)) {
			System.out.println("DID SPAWN THING "+worldObj.getBlockLightValue((int)posX, (int)posY+1, (int)posZ));
			Packet250CustomPayload packet = new Packet250CustomPayload();
			ArrayList<Byte> bytes = new ArrayList<Byte>();
			bytes.add((byte)1);
			bytes.addAll(NumberUtil.getByteListFromInt((int)posX));
			bytes.addAll(NumberUtil.getByteListFromInt((int)posY));
			bytes.addAll(NumberUtil.getByteListFromInt((int)posZ));
			
			packet.channel = ModInformation.CHANNEL;
			packet.length = bytes.size();
			packet.data = NumberUtil.getByteArrayFromList(bytes);
			
			PacketDispatcher.sendPacketToServer(packet);
			
		}
		setDead();
	}
	
}