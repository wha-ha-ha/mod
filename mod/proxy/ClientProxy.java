package mod.proxy;

import mod.client.renderers.EmptyRenderer;
import mod.entities.EntityItemLurker;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityItemLurker.class, new EmptyRenderer());
	}
	
}