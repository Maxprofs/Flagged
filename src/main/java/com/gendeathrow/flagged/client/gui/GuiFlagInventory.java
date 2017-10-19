package com.gendeathrow.flagged.client.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.gendeathrow.flagged.FlaggedMod;
import com.gendeathrow.flagged.api.FlagTab;
import com.gendeathrow.flagged.entity.ContainerFlagInventory;
import com.gendeathrow.flagged.entity.EntityItemFlag;
import com.gendeathrow.flagged.init.ModReference;
import com.gendeathrow.flagged.network.FlagSelectPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiFlagInventory extends GuiContainer {

	private static final ResourceLocation GUI_FLAG_INVENTORY = new ResourceLocation(ModReference.MOD_ID, "textures/gui/flagselect.png");
	public EntityItemFlag CurrentFlagEntity;
	
	public GuiFlagList flaglist;
	public int selectedFlag; 
	
	public int selectedCategory;
	
	private ContainerFlagInventory container;
	public GuiFlagInventory(InventoryPlayer inventory, Entity entityInventory) {
		super(new ContainerFlagInventory(inventory, entityInventory));

		xSize = 215;
		ySize = 166;
		
		CurrentFlagEntity = (EntityItemFlag) entityInventory;
		
		container = (ContainerFlagInventory) this.inventorySlots;
	}
	
	GuiButton Select;
	
	GuiButton worldLeft;
	GuiButton worldRight;
	
	String header;
	int headerX;
	
	@Override
	public void initGui()
	{
		super.initGui();

		this.flaglist = new GuiFlagList(mc, this, 113, 133, this.guiLeft + 11, this.guiTop + 27, this.guiTop + 133 + 26,  15);
		
		//add select
		this.addButton(Select = new GuiButton(2 , this.guiLeft + 165 - (this.fontRenderer.getStringWidth("Select")/2), this.guiTop + 82, this.fontRenderer.getStringWidth("Select") + 6, 20, "Select"));
		
		//add right Flag
		this.addButton(new GuiButton(1 , Select.x + Select.width + 5 ,  Select.y , 15, 20, ">>"));
		
		//add left Flag
		this.addButton(new GuiButton(0 , Select.x - 20 , Select.y , 15, 20, "<<"));		
		
		
		this.addButton(new GuiButton(3 , this.guiLeft + 165+ - (this.fontRenderer.getStringWidth("Close")/2), this.guiTop + 138 , this.fontRenderer.getStringWidth("Close") + 6, 20, "Close"));

		
		//add right World
		this.addButton(worldRight = new GuiButton(4 , headerX + this.fontRenderer.getStringWidth(header) + 5 ,  this.guiTop + 3, 15, 20, ">>"));
		
		//add left  World
		this.addButton(worldLeft = new GuiButton(5 , headerX - 20 , this.guiTop + 3 , 15, 20, "<<"));
	}
	
	@Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.flaglist.handleMouseInput();
    }

	@Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
    	switch(button.id)
    	{
			case 0:
				this.flaglist.selectPrevFlag();
				break;
			case 1:
				this.flaglist.selectNextFlag();
				break;
    		case 4:
    			selectedCategory = (selectedCategory-1) < 0 ? (FlagTab.FLAG_TAB_ARRAY.size()-1) : selectedCategory-1;
    			
    			this.flaglist.updateLoadedFlags(FlagTab.FLAG_TAB_ARRAY.get(selectedCategory));
    			break;
    		case 5:
    			selectedCategory = (selectedCategory+1) > (FlagTab.FLAG_TAB_ARRAY.size()-1)  ?  0 : selectedCategory+1;
    			this.flaglist.updateLoadedFlags(FlagTab.FLAG_TAB_ARRAY.get(selectedCategory));
    			break;
    		case 2:
    			FlaggedMod.NETWORK_WRAPPER.sendToServer(new FlagSelectPacket(CurrentFlagEntity, this.flaglist.getListEntry(this.flaglist.getSelectedElement()).flag.getRegistryName()));
    			Minecraft.getMinecraft().displayGuiScreen(null);
    			return;
    		case 3:
    			Minecraft.getMinecraft().displayGuiScreen(null);
    			break;
    		default:
    			break;
    	}
    	
    }
    
	@Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.flaglist.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
	@Override
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);
        this.flaglist.mouseReleased(mouseX, mouseY, state);
    }
	
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {	
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int x, int y) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_FLAG_INVENTORY);
		int xOffSet = (width - xSize) / 2;
		int yOffSet = (height - ySize) / 2;
		drawTexturedModalRect(xOffSet, yOffSet, 0, 0, xSize, ySize);
		
		
		
		if(flaglist.getListEntry(flaglist.getSelectedElement()) != null)
		{
			mc.getTextureManager().bindTexture(flaglist.getListEntry(flaglist.getSelectedElement()).flag.getFrontTexture());
			Gui.drawModalRectWithCustomSizedTexture(xOffSet + 139, yOffSet + 30, 0f, 0f, 60, 40, 60, 40);
		}
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        
		
		header = I18n.format(FlagTab.FLAG_TAB_ARRAY.get(selectedCategory).unlocalizedName().toLowerCase());
		headerX = this.guiLeft + (this.xSize/2) - (this.fontRenderer.getStringWidth(header)/2);
		
        this.fontRenderer.drawString(header, headerX, this.guiTop + 9, Color.BLACK.getRGB());
        
        this.worldLeft.x =  headerX - 20;
        this.worldRight.x = headerX + this.fontRenderer.getStringWidth(header) + 5;
        
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        	guiScissor(Minecraft.getMinecraft(), this.flaglist.left, this.flaglist.top, 113, 131);
        	this.flaglist.drawScreen(mouseX, mouseY, partialTicks);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }
	
	
	public static void guiScissor(Minecraft mc, int x, int y, int w, int h)
	{
		ScaledResolution r = new ScaledResolution(mc);
		int f = r.getScaleFactor();
		
		GL11.glScissor(x * f, (r.getScaledHeight() - y - h)*f, w * f, h * f);
	}
}