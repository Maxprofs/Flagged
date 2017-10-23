package com.gendeathrow.flagged.init;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import com.gendeathrow.flagged.entity.EntityItemFlag;
import com.gendeathrow.flagged.items.ItemFlag;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class ModItems {
	
	//Example entry you just need to add 1 Line for the Item Now!!!!
//	public static final Item weighted_club = new WeightedClub();
	public static final Item flag = new ItemFlag(EntityItemFlag.class).setRegistryName(new ResourceLocation(ModReference.MOD_ID, "flag")).setUnlocalizedName(ModReference.MOD_ID + ".flag" );
  
	public static IForgeRegistry<Item> itemRegistry;
	
	@SubscribeEvent
	public static void itemRegistry(RegistryEvent.Register<Item> event) {
		 itemRegistry = event.getRegistry();
		 itemRegistry.register(flag);
	}

	public static void registerRenderers() {
		try {
			for (Field field : ModItems.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Item) {
					Item item = (Item) obj;
					
	
					if (item instanceof ISubItemsItem) {
						List<String> models = ((ISubItemsItem) item).getModels();
						for (int i = 0; i < models.size(); i++)
							ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(ModReference.MOD_ID + ":" + models.get(i), "inventory"));
					} else {
						String name = field.getName().toLowerCase(Locale.ENGLISH);
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(ModReference.MOD_ID + ":" + name, "inventory"));
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
	}

	public static interface ISubItemsItem {
		List<String> getModels();
	}
	
	
	///BOWL

    


}
