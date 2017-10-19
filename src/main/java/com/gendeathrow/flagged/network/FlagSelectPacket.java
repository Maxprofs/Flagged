package com.gendeathrow.flagged.network;

import com.gendeathrow.flagged.entity.EntityItemFlag;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FlagSelectPacket implements IMessage 
{

	ResourceLocation flagRegistryName;
	int entityID;
	public FlagSelectPacket() {}
	
	
	public FlagSelectPacket(Entity entity, ResourceLocation flagRegistryNameIn)
	{
		entityID = entity.getEntityId();
		flagRegistryName = flagRegistryNameIn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		flagRegistryName = new ResourceLocation(ByteBufUtils.readUTF8String(buf));
		
		entityID = ByteBufUtils.readVarInt(buf, Integer.BYTES);

	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, flagRegistryName.toString());
		
		ByteBufUtils.writeVarInt(buf, entityID, Integer.BYTES);
	}
	
	
	/////////////////////////////////////////////
	// Server Message
	////////////////////////////////////////////
	public static class ServerHandler implements IMessageHandler<FlagSelectPacket, IMessage> 
	{

		@Override
		public IMessage onMessage(FlagSelectPacket message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
	          
			mainThread.addScheduledTask(new Runnable() 
				{
					@Override
	                public void run() 
					{
						
						Entity flagEntity = ctx.getServerHandler().player.world.getEntityByID(message.entityID);
						
						if(flagEntity != null && flagEntity instanceof EntityItemFlag)
						{
							((EntityItemFlag)flagEntity).setTexture(message.flagRegistryName.toString());
						}
						
					}
                });
			return null;
		}

	
	}
}
