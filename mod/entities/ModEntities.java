package mod.entities;

import java.util.ArrayList;

import mod.TheMod;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {

	public static void initEntities() {
		
		EntityRegistry.registerModEntity(EntityItemLurker.class,"entityItemLurker", 0, TheMod.instance, 20, 1, false);
		
		ArrayList<BiomeGenBase> validBiomes = new ArrayList<BiomeGenBase>();
		for(BiomeGenBase biome : BiomeGenBase.biomeList) {
			if(biome != null) {
				validBiomes.add(biome);
			}
		}
		EntityRegistry.addSpawn(EntityItemLurker.class, 5, 1, 1, EnumCreatureType.monster, validBiomes.toArray(new BiomeGenBase[]{}));
	}

}