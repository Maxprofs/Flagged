package com.gendeathrow.flagged.client.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.gendeathrow.flagged.api.FlagEntry;
import com.gendeathrow.flagged.api.FlagRegistry;
import com.gendeathrow.flagged.api.FlagTab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;

public class GuiFlagList extends GuiListExtended {

    private List<GuiFlagListEntry> flaglist;
    
    private GuiFlagInventory parent;
    
	public GuiFlagList(Minecraft mcIn, GuiFlagInventory guiFlagInventory, int widthIn, int heightIn, int x, int topIn, int bottomIn, int slotHeightIn) {
		
		super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
		
		this.left = x;
		this.parent = guiFlagInventory;
		this.setShowSelectionBox(true);
		
		updateLoadedFlags(FlagTab.FLAG_TAB_ARRAY.get(this.parent.selectedCategory));
			
	}

	
	public void updateLoadedFlags(FlagTab tabIn){

		flaglist = new ArrayList<GuiFlagListEntry>();
		
		for(FlagEntry flag : FlagRegistry.getFlagsForTab(tabIn))
			flaglist.add(new GuiFlagListEntry(flag, this));
		
		for(int i = 0; i < flaglist.size(); i++ )
		{
			if(getListEntry(i).flag.getRegistryName() == this.parent.CurrentFlagEntity.FLAGENTRY.getRegistryName())
			{
				this.setSelectedElement(i);
			}
		}
	}
	
	public void selectNextFlag(){
		this.selectedElement = (this.selectedElement+1) > (flaglist.size()-1)  ?  0 : this.selectedElement+1;
	}
	
	public void selectPrevFlag() {
		if(this.selectedElement > this.flaglist.size()-1)
			this.selectedElement = 0;
		
		this.selectedElement = (this.selectedElement-1) < 0 ? (flaglist.size()-1) : this.selectedElement-1;
	}
	
	protected boolean isSelected(int slotIndex){
		return this.selectedElement == slotIndex;
	}
   
	public void setSelectedElement(int slotIndex){
		this.selectedElement = slotIndex;
	}
   
	public int getSelectedElement(){
		return this.selectedElement;
	}
   
	@Override
	public GuiFlagListEntry getListEntry(int index) {
		if(index < 0 || index >= flaglist.size())
			return null;
	   
		return flaglist.get(index);
	}

	@Override
	protected int getSize() {
		
		return flaglist.size();
	}
	
	 @Override
	 public int getListWidth()
	 {
		 return this.width;
	 }
	 
	 protected int getScrollBarX()
	 {
		 return this.width + this.left - 6;
	 }

	@Override
	protected void overlayBackground(int p_148136_1_, int p_148136_2_, int p_148136_3_, int p_148136_4_)
	{
	    
	}
	
	@Override
	protected void drawContainerBackground(Tessellator tessellator)
    {
		
    }
	
    public boolean mouseClicked(int mouseX, int mouseY, int mouseEvent)
    {
        if (this.isMouseYWithinSlotBounds(mouseY))
        {
            int i = this.getSlotIndexFromScreenCoords(mouseX, mouseY);

            if (i >= 0)
            {
                int j = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
                int k = this.top + 4 - this.getAmountScrolled() + i * this.slotHeight + this.headerPadding;
                int l = mouseX - j;
                int i1 = mouseY - k;

                if (this.getListEntry(i).mousePressed(i, mouseX, mouseY, mouseEvent, l, i1))
                {
                    this.setEnabled(false);
                    return true;
                }
            }
        }

        return false;
    }
    
    
	
    public int getSlotIndexFromScreenCoords(int p_148124_1_, int p_148124_2_)
    {
        int i = this.left + this.width / 2 - this.getListWidth() / 2;
        int j = this.left + this.width / 2 + this.getListWidth() / 2;
        int k = p_148124_2_ - this.top - this.headerPadding + (int)this.getAmountScrolled() - 4;
        int l = k / this.slotHeight;
        return p_148124_1_ < this.getScrollBarX() && p_148124_1_ >= i && p_148124_1_ <= j && l >= 0 && k >= 0 && l < this.getSize() ? l : -1;
    }

    public boolean isMouseYWithinSlotBounds(int p_148141_1_)
    {
    	
        return true;
    }
    
    public class GuiFlagListEntry implements GuiListExtended.IGuiListEntry{
    	
    	private GuiFlagList parent;
    	public FlagEntry flag;
    	
    	public GuiFlagListEntry(FlagEntry flagIn, GuiFlagList parentIn) {
    		flag = flagIn;
    		this.parent = parentIn;
    	}

    	@Override
    	public void setSelected(int slotIndex, int insideLeft, int yPos) {
    		
    	}

    	@Override
    	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
    		
    		if(this.parent.isSelected(slotIndex))
    			Minecraft.getMinecraft().fontRenderer.drawString(flag.getLocalizedName(), x, y, Color.YELLOW.getRGB());
    		else
    			Minecraft.getMinecraft().fontRenderer.drawString(flag.getLocalizedName(), x, y, Color.WHITE.getRGB());
    	}

    	@Override
    	public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
    		this.parent.setSelectedElement(slotIndex);
    		return true;
    	}

    	@Override
    	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
    		
    	}
    }
}
