package mod.blocks.abstracts;

import java.util.HashMap;

import mod.ModInformation;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class TextureHelper {

	protected HashMap<String, Icon> icons;
	private static final int[][] playerFacingToOrientationMap = new int[][]{{2, 5, 4, 3}, {5, 3, 2, 4}, {3, 4, 5, 2}, {4, 2, 3, 5}};
	
	private String[] textures;
	
	public TextureHelper() {
		icons = new HashMap<String, Icon>();
		textures = getTextureStrings();
	}
	
	public abstract String[] getTextureStrings();
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int frontFacing, int actualFace, int meta) {
		
		int[] orientationSet = playerFacingToOrientationMap[frontFacing];
		
		if(actualFace == orientationSet[0]) {
			
			return getFront(meta);
			
		}else if(actualFace == orientationSet[1]) {
			
			return getLeft(meta);
			
		}else if(actualFace == orientationSet[2]) {
			
			return getRight(meta);
			
		}else if(actualFace == orientationSet[3]){
			
			return getBack(meta);
			
		}else if(actualFace == 1) {
			
			return getTop(meta);
			
		}else {
			
			return getBottom(meta);
			
		}

	}

	public abstract Icon getTop(int meta);
	
	public abstract Icon getBottom(int meta);

	public abstract Icon getBack(int meta);
	
	public abstract Icon getLeft(int meta);

	public abstract Icon getRight(int meta);

	public abstract Icon getFront(int meta);

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		for(int i = 0; i < textures.length; i++) {
			icons.put(textures[i], register.registerIcon(ModInformation.TEXTURE_LOCATION+":"+textures[i]));
		}
	}
	
}