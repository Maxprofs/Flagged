package com.gendeathrow.flagged.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {

	public static void registerOreDic() {

	}

	public static void addRecipes() {	
	    IRecipe flagRecipe = new ShapedOreRecipe(new ItemStack(ModItems.flag), new Object[] {
	            "xxS",
	            "xSC",
	            "SxC",
	            'S', "stickWood",   // can use ordinary items, blocks, itemstacks in ShapedOreRecipe
	            'C', Blocks.CARPET,  // look in OreDictionary for vanilla definitions
		    });
		    GameRegistry.addRecipe(flagRecipe); 
		    
	}
}
