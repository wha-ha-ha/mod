package mod.crafting;

import java.util.ArrayList;

import mod.items.ModItemInfo;
import mod.items.ModItems;
import mod.util.StackUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry {

	public static void addRecipes() {
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionary.getOres(ModItemInfo.VESSELSLASHED_OREDICT).get(0).copy(), ModItemInfo.BLADE_OREDICT, ModItemInfo.VESSEL_OREDICT));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionary.getOres(ModItemInfo.VESSELOFFCUTS_OREDICT).get(0).copy(), ModItemInfo.BLADE_OREDICT, ModItemInfo.VESSELSLASHED_OREDICT));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(OreDictionary.getOres(ModItemInfo.VESSELCRUSHED_OREDICT).get(0).copy(), ModItemInfo.BLADE_OREDICT, ModItemInfo.VESSELOFFCUTS_OREDICT));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.ghastTear, 1), ModItemInfo.BLADE_OREDICT, ModItemInfo.VESSELCRUSHED_OREDICT));
	
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.blade, new Object[]{
			"#RI",
			"SVR",
			"SS#",
			'R', Item.silk,
			'I', Item.ingotIron,
			'S', "stickWood",
			'V', ModItemInfo.VESSEL_OREDICT
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.blade, new Object[]{
			"IR#",
			"RVS",
			"#SS",
			'R', Item.silk,
			'I', Item.ingotIron,
			'S', "stickWood",
			'V', ModItemInfo.VESSEL_OREDICT
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.blade, new Object[]{
			"SS#",
			"SVR",
			"#RI",
			'R', Item.silk,
			'I', Item.ingotIron,
			'S', "stickWood",
			'V', ModItemInfo.VESSEL_OREDICT
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.blade, new Object[]{
			"#SS",
			"RVS",
			"IR#",
			'R', Item.silk,
			'I', Item.ingotIron,
			'S', "stickWood",
			'V', ModItemInfo.VESSEL_OREDICT
		}));
	
	}
	
}