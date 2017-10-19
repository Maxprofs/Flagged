package com.gendeathrow.flagged.api;

import java.util.ArrayList;

public class FlagTab {

	public static ArrayList<FlagTab> FLAG_TAB_ARRAY = new  ArrayList<FlagTab>();
	
	public static FlagTab WORLD_FLAGS = new FlagTab("worldflags");
	public static FlagTab MINECRAFT_FLAGS = new FlagTab("minecraftflags");
	public static FlagTab MISC_FLAGS = new FlagTab("miscflags");
	
	private String label;
	
	public FlagTab(String labelIn) {
		this(getNextID(), labelIn);
	}
	
	public FlagTab(int index, String labelIn) {
		this.label = labelIn;
		FLAG_TAB_ARRAY.add(index, this);
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public String unlocalizedName()
	{
		return "flagcategory."+ this.label +".name";
	}
	
    public static int getNextID()
    {
        return FLAG_TAB_ARRAY.size();
    }
}
