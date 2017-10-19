package com.gendeathrow.flagged.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;

public class FlagRegistry {

	public static final HashMap<ResourceLocation, FlagEntry> FLAGS = new HashMap<ResourceLocation, FlagEntry>();
	
    public static void RegisterFlag(String modID, String flagName, ResourceLocation textureIn){
    	ResourceLocation registryName = new ResourceLocation(modID, flagName);
    	RegisterFlag(registryName, new FlagEntry(registryName, textureIn));
    }
    
    public static void RegisterFlag(){
    	
    }
   
    public static void RegisterFlag(ResourceLocation registryName, FlagEntry entry){
    	addFlagEntry(registryName, entry);
    	
    }
    
    /**
     * Register a flag with different front and back textures.
     *  
     * @param modID
     * @param flagName
     * @param textureFront
     * @param textureBack
     */
	private static void RegisterMultiSidedFlag(String modID, String flagName, ResourceLocation textureFront, ResourceLocation textureBack) 
	{
	   	ResourceLocation registryName = new ResourceLocation(modID, flagName);
	    
		addFlagEntry(registryName, new FlagEntry(registryName, textureFront).setBackTexture(textureBack));
	}
		    
    private static void addFlagEntry(ResourceLocation registryName, FlagEntry Entry)
    {
    	if(!containsFlag(registryName))
    	{
    		FLAGS.put(registryName, Entry);
    	}else
    	{
    		//PRINT ERROR
    	}
    }
    
	protected static boolean containsFlag(ResourceLocation flagIn)
	{
		for(Entry<ResourceLocation, FlagEntry> flag : FLAGS.entrySet())
		{
			if(flag.getKey().toString().equalsIgnoreCase(flagIn.toString()))
				return true;
		}
		
		return false;
	}  
	
	public static Collection<FlagEntry> getFlagList()
	{
		return FLAGS.values();
	}
	
	public static ArrayList<FlagEntry> getFlagsForTab(FlagTab tabIn)
	{
		ArrayList<FlagEntry> list = new ArrayList<FlagEntry>();
		
		for(FlagEntry flag : FLAGS.values()){
			if(flag.GetFlagTab() == tabIn)
				list.add(flag);
		}
		return list;
	}
	
	@Nullable
	public static FlagEntry getFlag(ResourceLocation idIn)
	{
		for(ResourceLocation flagItem : FLAGS.keySet())
		{
			if(idIn.toString() == flagItem.toString())
			{
				return FLAGS.get(flagItem);
			}
		}
		return null;
	}
			  
}
