package mod.network;



import java.util.ArrayList;

import mod.items.ModItems;
import mod.util.NumberUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {
	
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		EntityPlayer entPlayer = (EntityPlayer)player;

		switch(packet.data[0]) {
			case 0:
				ArrayList<Integer> coords = NumberUtil.getIntListFromBytes(1, 13, packet.data);
				int x = coords.get(0);
				int y = coords.get(1);
				int z = coords.get(2);
				World world = entPlayer.worldObj;
				world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				for (int l = 0; l < 8; ++l) {
		        	world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
		        }
				break;
			case 1:
				ArrayList<Integer> coords1 = NumberUtil.getIntListFromBytes(1, 13, packet.data);
				int x1 = coords1.get(0);
				int y1 = coords1.get(1);
				int z1 = coords1.get(2);
				entPlayer.worldObj.spawnEntityInWorld(new EntityItem(entPlayer.worldObj, x1, y1, z1, new ItemStack(ModItems.baseResource, 1, 0)));
				break;
		}
	}
}