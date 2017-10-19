package com.gendeathrow.flagged.client.item;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelBakeEventHandler {

		  public static final ModelBakeEventHandler instance = new ModelBakeEventHandler();

		  private ModelBakeEventHandler() {};

		  // Called after all the other baked models have been added to the modelRegistry
		  // Allows us to manipulate the modelRegistry before BlockModelShapes caches them.
		  @SubscribeEvent
		  public void onModelBakeEvent(ModelBakeEvent event)
		  {
			  
		  }
}
