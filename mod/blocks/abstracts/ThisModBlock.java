package mod.blocks.abstracts;

import java.util.ArrayList;

import mod.ModInformation;
import mod.TheMod;
import mod.util.NumberUtil;
import mod.util.PlayerUtil;
import mod.util.interfaces.IFourWayRotatable;
import mod.util.interfaces.ILightEmitter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ThisModBlock extends Block {

	private TextureHelper textureHelper;
	
	public ThisModBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(TheMod.TheModCreativeTab);
		this.textureHelper = getTextureHelper();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		if(textureHelper != null) {
			textureHelper.registerIcons(register);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int face, int meta) {
		if(textureHelper != null) {
			return textureHelper.getIcon(3, face, meta); //Default for correct ItemStack rendering!
		}
		return null;
	}
	
	public abstract String getUnlocalizedName(ItemStack blockStack);
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess iba, int x, int y, int z, int face) {
		TileEntity te = iba.getBlockTileEntity(x, y, z);
		int meta = iba.getBlockMetadata(x, y, z);
		int rotation = 3; //Default for non-rotatables!
		if(te != null && te instanceof IFourWayRotatable) {
			rotation = ((IFourWayRotatable)te).getFacing();
		}
		if(textureHelper != null) {
			return textureHelper.getIcon(rotation, face, meta);
		}
		return null;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livBase, ItemStack stack) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof IFourWayRotatable) {
			((IFourWayRotatable)te).setFacing(PlayerUtil.getPlayerFacing(livBase));
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int blockID;
		
		if((blockID = world.getBlockId(x, y, z)) >= 0) {
			Block b = Block.blocksList[blockID];
			if(b instanceof ILightEmitter) {
				return ((ILightEmitter)b).getLightLevel(world.getBlockMetadata(x, y, z));
			}
		}
		
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof ILightEmitter) {
			return ((ILightEmitter)te).getLightLevel();
		}
		return 0;
	}
	
	public abstract TextureHelper getTextureHelper();
	
	@Override
	public abstract int damageDropped(int meta);

}
