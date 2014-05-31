package mod;

import java.io.File;

import mod.blocks.ModBlocks;
import mod.blocks.ModTileEntities;
import mod.config.ConfigurationHandler;
import mod.crafting.RecipeRegistry;
import mod.entities.ModEntities;
import mod.items.ModItems;
import mod.network.PacketHandler;
import mod.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModInformation.MODID, name = ModInformation.MODNAME, version = ModInformation.VERSION)
@NetworkMod(serverSideRequired = true, clientSideRequired = true, channels = { ModInformation.CHANNEL }, packetHandler = PacketHandler.class)
public class TheMod {

	@Instance("Mod.")
	public static TheMod instance;
	
	@SidedProxy(clientSide = "mod.proxy.ClientProxy", serverSide = "mod.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static final CreativeTabs TheModCreativeTab = new CreativeTabs(ModInformation.MODNAME) {
		public ItemStack getIconItemStack() {
			return new ItemStack(ModItems.baseResource, 1, 0);
		}
	};
	
	public static String configPath;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		configPath = event.getModConfigurationDirectory().getAbsolutePath()+"/Mod./";
		ConfigurationHandler.init(new File(configPath+"Mod..cfg"));
		ModItems.initItems();
		ModBlocks.initBlocks();
		ModTileEntities.initTileEntities();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModEntities.initEntities();
		proxy.initRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ModItems.registerOreDict();
		RecipeRegistry.addRecipes();
	}
	
}
