package com.gendeathrow.flagged.proxy;

import com.gendeathrow.flagged.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;

public class CommonProxy {

	public void registerItemAndBlockRenderers() {

	}

	public void setCustomStateMap(Block block, StateMap stateMap) {

	}

	public void registerTileEntities() {
		ModBlocks.registerTileEntity();
	}

	public void preInit() {
		
	}
	
	public void init() {
		
	}
	
	public void postInit() {

	}

}
