package com.gendeathrow.flagged.api;

import java.util.ArrayList;
import java.util.UUID;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;


public class FlagEntry {
	
	private ResourceLocation textureFront;
	private ResourceLocation textureBack = null;
	
	private ArrayList<ResourceLocation> textureLayers;
	
	private ResourceLocation registryName;
	
	private ArrayList<UUID> playerLocked;
	
	private FlagTab flagtab = FlagTab.WORLD_FLAGS;
	
	public FlagEntry(ResourceLocation registryName, ResourceLocation frontTextureIn) {

		this.registryName = registryName;		
		this.textureFront = frontTextureIn;
	}
	
	/**
	 * Set Flags Tab/Category type 
	 * 
	 * @param tabIn
	 * @return 
	 */
	public FlagEntry setFlagTab(FlagTab tabIn){
		this.flagtab = tabIn;
		return this;
	}
	
	public FlagTab GetFlagTab(){
		return this.flagtab;
	}
	
	public String getUnlocolizedName()
	{
		return "flags."+ registryName.getResourceDomain() +"."+ registryName.getResourcePath();
	}
	
	public String getLocalizedName(){
		return I18n.format(getUnlocolizedName(), new Object[0]);
	}
	
	/**
	 * Set new texture for back side of the flag.
	 * 
	 * @param backTextureIn
	 */
	public FlagEntry setBackTexture(ResourceLocation backTextureIn){
		this.textureBack = backTextureIn;
		return this;
	}
	
	/**
	 * Checks to see if it has a back texture for the flag
	 * @return
	 */
	public boolean hasBackTexture(){
		return this.textureBack != null;
	}
	
	/**
	 * Get Flags Front Texture
	 * 
	 * @return ResourceLocation
	 */
	public ResourceLocation getFrontTexture() {
		return this.textureFront;
	}
	
	/**
	 * Get Flags Back Texture
	 * 
	 * @return ResourceLocation
	 */
	public ResourceLocation getBackTexture() {
		if(this.textureBack == null)
			return this.textureFront;
		
		return this.textureBack;
	}
	
	public ResourceLocation getRegistryName(){
		return this.registryName;
	}
}
