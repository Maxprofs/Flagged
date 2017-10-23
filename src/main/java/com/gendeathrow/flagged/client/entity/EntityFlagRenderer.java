package com.gendeathrow.flagged.client.entity;

import org.lwjgl.opengl.GL11;

import com.gendeathrow.flagged.entity.EntityItemFlag;
import com.gendeathrow.flagged.init.ModReference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFlagRenderer extends Render<EntityItemFlag> {

	private static final ResourceLocation DEFAULT = new ResourceLocation(ModReference.MOD_ID, "textures/flags/american.png");
	protected ModelBase modelMinecart = new ModelMinecart();
	
	public EntityFlagRenderer(RenderManager renderManager) 
	{
		super(renderManager);  
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityItemFlag flag) 
	{
		return new ResourceLocation(ModReference.MOD_ID, "textures/flags/"+flag.getFlagEntry()+".png");
	}
	
    public boolean shouldRender(EntityItemFlag livingEntity, ICamera camera, double camX, double camY, double camZ)
    {
      	return true;
    }
	@Override
    public void doRender(EntityItemFlag flagEntiy, double x, double y, double z, float entityYaw, float partialTicks)
    {
		GlStateManager.pushMatrix(); 

			BlockPos blockpos = flagEntiy.getHangingPosition();
			double d0 = (double)blockpos.getX() - flagEntiy.posX + x;
			double d1 = (double)blockpos.getY() - flagEntiy.posY + y;
			double d2 = (double)blockpos.getZ() - flagEntiy.posZ + z;
		 
			GlStateManager.translate(d0 + 0.5F, d1, d2 + 2.5F);
        
			Tessellator tessellator = Tessellator.getInstance();

			BufferBuilder worldrenderer = tessellator.getBuffer();
			RenderHelper.disableStandardItemLighting();

			GlStateManager.shadeModel(GL11.GL_SMOOTH);
			GlStateManager.enableCull();
			GlStateManager.depthMask(true);
			
			GlStateManager.pushMatrix();
			
				GlStateManager.translate(0.0F, 18F, -2.0F);
				GlStateManager.rotate(90.0F - flagEntiy.rotationYaw, 0, 1, 0);
				GlStateManager.translate(0.2F, 0f, 0f);
				GlStateManager.scale(0.15, 4, 0.10);
				GlStateManager.scale(-1, 1, 1);
		
				GL11.glEnable(GL11.GL_CULL_FACE);
				
				this.bindTexture(flagEntiy.FLAGENTRY != null ? flagEntiy.FLAGENTRY.getFrontTexture() : DEFAULT);
				
					GL11.glCullFace(GL11.GL_BACK);
					drawFlag(tessellator, worldrenderer, flagEntiy.getVecPointsArray(), flagEntiy.pointWidth, flagEntiy.pointHeight);
				
					if(flagEntiy.FLAGENTRY != null && flagEntiy.FLAGENTRY.hasBackTexture())
						this.bindTexture(flagEntiy.FLAGENTRY.getBackTexture());
					
					GL11.glCullFace(GL11.GL_FRONT);
					GL11.glFrontFace(GL11.GL_CW);  
					drawFlag(tessellator, worldrenderer, flagEntiy.getVecPointsArray(), flagEntiy.pointWidth, flagEntiy.pointHeight);
				GL11.glDisable(GL11.GL_CULL_FACE);
        
				flagEntiy.progressWave();

			GlStateManager.popMatrix();
		
			GlStateManager.depthMask(true);
			GlStateManager.disableCull();
			GlStateManager.shadeModel(GL11.GL_FLAT);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			RenderHelper.enableStandardItemLighting();
	
		GlStateManager.popMatrix();
    }
	
	public void drawFlag(Tessellator tessellator, BufferBuilder worldrenderer, float[][][] pointMesh, int meshWidth, int meshHeight)
	{
		int x1, y1;
	    float float_x, float_y, float_xb, float_yb;  


		for(x1 = (meshWidth-2); x1 >= 0 ; x1-- )                // Loop Through The X Plane (44 Points)
		{
		    for( y1 = 0; y1 < (meshHeight-1); y1++ )            // Loop Through The Y Plane (44 Points)
		    {
		    	
				float_x = (float)(x1)/meshWidth;       // Create A Floating Point X Value
				float_y = (float)(y1)/meshHeight;       // Create A Floating Point Y Value
				float_xb = (float)(x1+1)/meshWidth;        // Create A Floating Point Y Value+0.0227f
				float_yb = (float)(y1+2)/meshHeight; // Create A Floating Point Y Value+0.0227f
				
				//System.out.println(y1 +" "+ float_x +" "+ float_y +" "+ float_xb +" "+ float_yb);
				
//				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
//				
//				worldrenderer.pos(pointMesh[x1][y1][0], pointMesh[x1][y1][1], pointMesh[x1][y1][2]).tex(float_x, float_y).endVertex();
//				worldrenderer.pos( pointMesh[x1][y1+1][0], pointMesh[x1][y1+1][1], pointMesh[x1][y1+1][2]).tex(float_x, float_yb).endVertex();
//				worldrenderer.pos(pointMesh[x1+1][y1+1][0], pointMesh[x1+1][y1+1][1], pointMesh[x1+1][y1+1][2]).tex(float_xb, float_yb).endVertex();
//				worldrenderer.pos(pointMesh[x1+1][y1][0], pointMesh[x1+1][y1][1], pointMesh[x1+1][y1][2]).tex(float_xb, float_y).endVertex();
//				
//				tessellator.draw();
				
				float xx= 0.02f;
				float ZQuad1 = (float) (pointMesh[x1][y1][2] * (x1 * xx));
				float ZQuad2 = (float) (pointMesh[x1][y1+1][2] * (x1 * xx));
				float ZQuad3 = (float) (pointMesh[x1+1][y1+1][2] * ((x1+1) * xx));
				float ZQuad4 = (float) (pointMesh[x1+1][y1][2] * ((x1+1) * xx));

			    GlStateManager.glBegin(GL11.GL_QUADS);	
			    
		        GL11.glTexCoord2f( float_x, float_yb);    // First Texture Coordinate (Bottom Left)
		        GL11.glVertex3f( pointMesh[x1][y1][0], pointMesh[x1][y1][1], ZQuad1 );
		         
		        GL11.glTexCoord2f( float_x, float_y );  // Second Texture Coordinate (Top Left)
		        GL11.glVertex3f( pointMesh[x1][y1+1][0], pointMesh[x1][y1+1][1],ZQuad2 );
		         
		        GL11.glTexCoord2f( float_xb, float_y ); // Third Texture Coordinate (Top Right)
		        GL11.glVertex3f( pointMesh[x1+1][y1+1][0], pointMesh[x1+1][y1+1][1], ZQuad3 );
		         
		        GL11.glTexCoord2f( float_xb, float_yb );  // Fourth Texture Coordinate (Bottom Right)
		        GL11.glVertex3f( pointMesh[x1+1][y1][0], pointMesh[x1+1][y1][1], ZQuad4 );
		        
				GlStateManager.glEnd();
				
				
//			    GlStateManager.glBegin(GL11.GL_QUADS);	
//		        GL11.glTexCoord2f( float_x, float_y);    // First Texture Coordinate (Bottom Left)
//		        GL11.glVertex3f( pointMesh[x1][y1][0], pointMesh[x1][y1][1], pointMesh[x1][y1][2] );
//		         
//		        GL11.glTexCoord2f( float_x, float_yb );  // Second Texture Coordinate (Top Left)
//		        GL11.glVertex3f( pointMesh[x1][y1+1][0], pointMesh[x1][y1+1][1], pointMesh[x1][y1+1][2] );
//		         
//		        GL11.glTexCoord2f( float_xb, float_yb ); // Third Texture Coordinate (Top Right)
//		        GL11.glVertex3f( pointMesh[x1+1][y1+1][0], pointMesh[x1+1][y1+1][1], pointMesh[x1+1][y1+1][2] );
//		         
//		        GL11.glTexCoord2f( float_xb, float_y );  // Fourth Texture Coordinate (Bottom Right)
//		        GL11.glVertex3f( pointMesh[x1+1][y1][0], pointMesh[x1+1][y1][1], pointMesh[x1+1][y1][2] );
//		        
//				GlStateManager.glEnd();
		    }
		}

	
	}
	
}
