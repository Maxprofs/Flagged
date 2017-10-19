package com.gendeathrow.flagged.proxy;

import com.gendeathrow.flagged.client.entity.EntityFlagRenderer;
import com.gendeathrow.flagged.entity.EntityItemFlag;
import com.gendeathrow.flagged.init.ModBlocks;
import com.gendeathrow.flagged.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemAndBlockRenderers() {
		ModItems.registerRenderers();
		ModBlocks.registerRenderers();
		ModBlocks.registerTileEntityRenderer();
	}

	@Override
	public void setCustomStateMap(Block block, StateMap stateMap) {
		ModelLoader.setCustomStateMapper(block, stateMap);
		
	}
	
	@Override
	public void preInit() {
		   
		RenderingRegistry.registerEntityRenderingHandler(EntityItemFlag.class, EntityFlagRenderer::new);
	}
	
	@Override	  
	public void init() {
		
	
	}
	
	@Override
	public void postInit() {

	}
}
