package com.gendeathrow.flagged.client.item;

import java.util.List;

import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemOverrideList;

public class FlagItemOverrideList extends ItemOverrideList {
	
	public FlagItemOverrideList(List<ItemOverride> overridesIn)
	{
		super(overridesIn);
	}
	
	
//	
//	  @Override
//	  public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity)
//	  {
//	    int numberOfChessPieces = 0;
//	    if (stack != null) {
//	      numberOfChessPieces = stack.func_190916_E();  // func_190916_E() will probably be called getStackSize() soon
//	    }
//	    return new ChessboardFinalisedModel(originalModel, numberOfChessPieces);
//	  }

}
