package com.gendeathrow.flagged.client.gui;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class ScrollingGuiContainer extends GuiContainer{

	
	public ScrollingGuiContainer(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}

	public ArrayList<GuiListExtended> extendedList = new ArrayList<GuiListExtended>();

	@Override
    public void initGui()
    {
		super.initGui();
    }
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		super.actionPerformed(button);
		
		
		for(GuiListExtended list : extendedList)
	    {
	    	list.actionPerformed(button);
	    }
	}
	 
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
	    super.mouseClicked(mouseX, mouseY, mouseButton);
	    	

		for(GuiListExtended list : extendedList)
	    {
	    	list.mouseClicked(mouseX, mouseY, mouseButton);
	    }
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		
		for(GuiListExtended list : extendedList)
	    {
			GL11.glEnable(GL11.GL_SCISSOR_TEST);
			guiScissor(mc, list.left, list.top, list.width, list.height);
	    		list.drawScreen(mouseX, mouseY, partialTicks);
	    	GL11.glDisable(GL11.GL_SCISSOR_TEST);
	    		
	    }
	}
	
	
	
	
	
	
	
	/**
	 * Performs a OpenGL scissor based on Minecraft's resolution instead of display resolution
	 */
	public static void guiScissor(Minecraft mc, int x, int y, int w, int h)
	{
		ScaledResolution r = new ScaledResolution(mc);
		int f = r.getScaleFactor();
		
		GL11.glScissor(x * f, (r.getScaledHeight() - y - h)*f, w * f, h * f);
	}
	 
}
