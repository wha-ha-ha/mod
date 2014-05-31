package mod.client.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EmptyRenderer extends Render {

	public EmptyRenderer() {
		super();
	}

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    	
    }
    
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
    	return null;		
    }
    
}
