package com.gendeathrow.flagged.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

@EventBusSubscriber
public class ModRecipes {

	public static void registerOreDic() {

	}

	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		
		
		ShapedOreRecipe flagrecipe = new ShapedOreRecipe(
				new ResourceLocation("flagrecipe"), 
				new ItemStack(ModItems.flag), 
				new Object[] {
						"  S",
						" SC",
						"S C",
						'S', "stickWood",   // can use ordinary items, blocks, itemstacks in ShapedOreRecipe
						'C', new ItemStack(Blocks.CARPET),  // look in OreDictionary for vanilla definitions
		    });
		
		event.getRegistry().register(flagrecipe.setRegistryName(new ResourceLocation(ModReference.MOD_ID, "flagrecipe")));
	}
	
		
	public static void addRecipes() {	
//	    IRecipe flagRecipe = new ShapedOreRecipe(new ItemStack(ModItems.flag), new Object[] {
//	            "xxS",
//	            "xSC",
//	            "SxC",
//	            'S', "stickWood",   // can use ordinary items, blocks, itemstacks in ShapedOreRecipe
//	            'C', Blocks.CARPET,  // look in OreDictionary for vanilla definitions
//		    });
//		    GameRegistry.addShapedRecipe((new ResourceLocation(ModReference.MOD_ID, "flagrecipe"), 
//		    		
//		    		group, output, params);
		    
		    
		    
		    //.addRecipe(new ResourceLocation(ModReference.MOD_ID, "flagrecipe"), flagRecipe); 
		    
	}
}
