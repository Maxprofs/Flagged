package com.gendeathrow.flagged;

import java.io.IOException;

import com.gendeathrow.flagged.client.gui.GuiHandler;
import com.gendeathrow.flagged.entity.EntityItemFlag;
import com.gendeathrow.flagged.init.FLAGS;
import com.gendeathrow.flagged.init.ModRecipes;
import com.gendeathrow.flagged.init.ModReference;
import com.gendeathrow.flagged.network.FlagSelectPacket;
import com.gendeathrow.flagged.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;



@Mod(modid = ModReference.MOD_ID, name = ModReference.NAME, version = ModReference.VERSION)
public class FlaggedMod {

	@Instance(ModReference.MOD_ID)
	public static FlaggedMod INSTANCE;

	@SidedProxy(clientSide = ModReference.PROXY_PATH + ".ClientProxy", serverSide = ModReference.PROXY_PATH + ".CommonProxy")
	public static CommonProxy PROXY;

	public static SimpleNetworkWrapper NETWORK_WRAPPER;

	public static CreativeTabs TAB = new CreativeTabs(ModReference.MOD_ID) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.BANNER);
		}

	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		PROXY.registerTileEntities();
		PROXY.registerItemAndBlockRenderers();
		ModRecipes.addRecipes();
		
		NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(ModReference.CHANNEL_NAME);
		
		NETWORK_WRAPPER.registerMessage(FlagSelectPacket.ServerHandler.class, FlagSelectPacket.class, 0, Side.SERVER);

		EntityRegistry.registerModEntity(new ResourceLocation(ModReference.MOD_ID, "flag"), EntityItemFlag.class, "flag", 1, this, 120, 5, false);
		
		PROXY.preInit(); 

	}

	@EventHandler
	public void init(FMLInitializationEvent event) throws IOException {
		PROXY.init();
		
		FLAGS.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(FlaggedMod.INSTANCE, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		PROXY.postInit();
	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {

	}
}