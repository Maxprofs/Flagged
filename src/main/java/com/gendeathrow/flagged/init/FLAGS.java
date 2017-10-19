package com.gendeathrow.flagged.init;

import com.gendeathrow.flagged.api.FlagEntry;
import com.gendeathrow.flagged.api.FlagRegistry;
import com.gendeathrow.flagged.api.FlagTab;

import net.minecraft.util.ResourceLocation;

public class FLAGS {

	
	   public static void init(){
		   
		   registerMinecraftFlag(ModReference.MOD_ID, "sword", new ResourceLocation(ModReference.MOD_ID, "textures/flags/minecraft/sword.png"));
		   registerMinecraftFlag(ModReference.MOD_ID, "alex", new ResourceLocation(ModReference.MOD_ID, "textures/flags/minecraft/alex.png"));
		   registerMinecraftFlag(ModReference.MOD_ID, "steve", new ResourceLocation(ModReference.MOD_ID, "textures/flags/minecraft/steve.png"));
		   registerMinecraftFlag(ModReference.MOD_ID, "cakelie", new ResourceLocation(ModReference.MOD_ID, "textures/flags/minecraft/cake.png"));
		   registerMinecraftFlag(ModReference.MOD_ID, "creeper", new ResourceLocation(ModReference.MOD_ID, "textures/flags/minecraft/creeper.png"));
		   registerMinecraftFlag(ModReference.MOD_ID, "skele", new ResourceLocation(ModReference.MOD_ID, "textures/flags/minecraft/skele.png"));
		   
		   // Register World Flags
		   registerWorldFlag(ModReference.MOD_ID, "american", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/am.png"));
		   registerWorldFlag(ModReference.MOD_ID, "france", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/fr.png"));
		   registerWorldFlag(ModReference.MOD_ID, "canada", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/ca.png"));
		   registerWorldFlag(ModReference.MOD_ID, "united_kingdom", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/gb.png"));
		   registerWorldFlag(ModReference.MOD_ID, "japan", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/jp.png"));
		   registerWorldFlag(ModReference.MOD_ID, "germany", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/de.png"));
		   registerWorldFlag(ModReference.MOD_ID, "brazil", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/br.png"));
		   registerWorldFlag(ModReference.MOD_ID, "italy", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/it.png"));
		   registerWorldFlag(ModReference.MOD_ID, "south_korea", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/kr.png"));
		   registerWorldFlag(ModReference.MOD_ID, "australia", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/au.png"));
		   registerWorldFlag(ModReference.MOD_ID, "china", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/cn.png"));
		   registerWorldFlag(ModReference.MOD_ID, "israel", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/il.png"));
		   registerWorldFlag(ModReference.MOD_ID, "russia", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/ru.png"));
		   registerWorldFlag(ModReference.MOD_ID, "mexico", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/mx.png"));
		   registerWorldFlag(ModReference.MOD_ID, "switzerland", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/ch.png"));
		   registerWorldFlag(ModReference.MOD_ID, "ireland", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/ie.png"));
		   registerWorldFlag(ModReference.MOD_ID, "finland", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/fi.png"));
		   registerWorldFlag(ModReference.MOD_ID, "belgium", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/be.png"));
		   registerWorldFlag(ModReference.MOD_ID, "denmark", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/dk.png"));
		   registerWorldFlag(ModReference.MOD_ID, "egypt", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/eg.png"));
		   registerWorldFlag(ModReference.MOD_ID, "india", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/in.png"));
		   registerWorldFlag(ModReference.MOD_ID, "sweden", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/se.png"));
		   registerWorldFlag(ModReference.MOD_ID, "iceland", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/is.png"));
		   registerWorldFlag(ModReference.MOD_ID, "spain", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/sp.png"));
		   registerWorldFlag(ModReference.MOD_ID, "jamaica", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/jm.png"));
		   registerWorldFlag(ModReference.MOD_ID, "england", new ResourceLocation(ModReference.MOD_ID, "textures/flags/world/e.png"));
		   
		   registerMiscFlag(ModReference.MOD_ID, "pirate", new ResourceLocation(ModReference.MOD_ID, "textures/flags/misc/pirate.png"));
		   registerMiscFlag(ModReference.MOD_ID, "genericflag", new ResourceLocation(ModReference.MOD_ID, "textures/flags/misc/genericflag.png"));
		   registerMiscFlag(ModReference.MOD_ID, "trees", new ResourceLocation(ModReference.MOD_ID, "textures/flags/misc/trees-wind.png"));

	   }
	   
	   
	   private static void registerWorldFlag(String modId, String name, ResourceLocation texture) {
		   ResourceLocation registryName = new ResourceLocation(ModReference.MOD_ID, name);
		   FlagRegistry.RegisterFlag(registryName,  new FlagEntry(registryName, texture).setFlagTab(FlagTab.WORLD_FLAGS));
	   }  
	   
	   private static void registerMinecraftFlag(String modId, String name, ResourceLocation texture) {
		   ResourceLocation registryName = new ResourceLocation(ModReference.MOD_ID, name);
		   FlagRegistry.RegisterFlag(registryName,  new FlagEntry(registryName, texture).setFlagTab(FlagTab.MINECRAFT_FLAGS));
	   }  
	   
	   private static void registerMiscFlag(String modId, String name, ResourceLocation texture) {
		   ResourceLocation registryName = new ResourceLocation(ModReference.MOD_ID, name);
		   FlagRegistry.RegisterFlag(registryName,  new FlagEntry(registryName, texture).setFlagTab(FlagTab.MISC_FLAGS));
	   }  
}
