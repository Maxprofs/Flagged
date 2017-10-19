package com.gendeathrow.flagged.client.gui;

import com.gendeathrow.flagged.entity.ContainerFlagInventory;
import com.gendeathrow.flagged.entity.EntityItemFlag;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static int FLAG_GUI = 0;
	
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == FLAG_GUI)
		{
			Entity entity = world.getEntityByID(x);
			if (entity != null && entity instanceof EntityItemFlag)
				return new ContainerFlagInventory(player.inventory, (EntityItemFlag) entity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == FLAG_GUI)
		{
			Entity entity = world.getEntityByID(x);
			if (entity != null && entity instanceof EntityItemFlag)
				return new GuiFlagInventory(player.inventory, entity);
		}
		
		return null;
	}

}
