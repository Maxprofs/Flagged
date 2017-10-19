package com.gendeathrow.flagged.entity;

import com.gendeathrow.flagged.api.FlagEntry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerFlagInventory extends Container {

	private final EntityItemFlag FLAGCONTAINER;
	
	private FlagEntry selectedFlag;

	public ContainerFlagInventory(InventoryPlayer playerInventory, Entity entityInventory) {
		FLAGCONTAINER = (EntityItemFlag) entityInventory;
		int i;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack stack = null;
		return stack;
	}

	@Override
	public void addListener(IContainerListener listener) {
		if (this.listeners.contains(listener)) {
			throw new IllegalArgumentException("Listener already listening");
		} else {
			this.listeners.add(listener);
			this.detectAndSendChanges();
		}
	}
	
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {

	}

	@Override
    public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
	
    }
	
	

	@Override
    public void onCraftMatrixChanged(IInventory inv) {
        detectAndSendChanges();
    }

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
	}
}