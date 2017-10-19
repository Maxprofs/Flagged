package com.gendeathrow.flagged.entity;

import javax.annotation.Nullable;

import com.gendeathrow.flagged.FlaggedMod;
import com.gendeathrow.flagged.api.FlagEntry;
import com.gendeathrow.flagged.api.FlagRegistry;
import com.gendeathrow.flagged.client.gui.GuiHandler;
import com.gendeathrow.flagged.init.ModItems;
import com.gendeathrow.flagged.init.ModReference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityItemFlag extends EntityHangingSpecial{

    private static final DataParameter<String> FLAG_REGISTRY_NAME = EntityDataManager.<String>createKey(EntityItemFlag.class, DataSerializers.STRING);
    
    
	public int pointWidth = 45;
	public int pointHeight = 2;
	int x1, y1;
	
	private float[][][] points = new float[pointWidth][pointHeight][3]; // The Array For The Points On The Grid Of Our "Wave"
	private float hold;
	
	public FlagEntry FLAGENTRY;
	
	public EntityItemFlag(World worldIn) 
	{
		super(worldIn);
		setupVecPoints();
		for(int st = rand.nextInt(20); st > 0; st--);
			shiftWaveArray();
	}
	
	public EntityItemFlag(World worldIn, BlockPos pos, EnumFacing facing) 
	{
		super(worldIn, pos);
		FLAGENTRY = FlagRegistry.getFlag(new ResourceLocation(ModReference.MOD_ID + ":cakelie"));
        this.updateFacingWithBoundingBox(facing);
		setupVecPoints();
		for(int st = rand.nextInt(20); st > 0; st--);
			shiftWaveArray();
	}
	
    protected void entityInit()
    {
        this.getDataManager().register(FLAG_REGISTRY_NAME, ModReference.MOD_ID + ":cakelie");
    }
    
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        

//        if (this.tickCounter1++ == 100 && !this.world.isRemote)
//        {
//            this.tickCounter1 = 0;
//
//            if (!this.isDead && !this.onValidSurface())
//            {
//                this.setDead();
//                this.onBroken((Entity)null);
//            }
//        }
    	//super.onUpdate();
    }
    
    public boolean canBeCollidedWith()
    {
        return true;
    }
    
    public void setBoundingBox()
    {
    	setEntityBoundingBox(new AxisAlignedBB(this.posX - 1.2, this.posY-0.75, this.posZ - .2, this.posX + 1, this.posY + 0.75, this.posZ + 0.75));
    }
    
    
	public float[][][] getVecPointsArray(){
		return points;
	}
	
	private long lastTime = 0;
	public void progressWave()
	{	
		int tpw = this.world.isRainingAt(this.getPosition()) ? 20 : 50;
		
		if((Minecraft.getSystemTime() - lastTime) > tpw)
		{
			shiftWaveArray();
			lastTime = Minecraft.getSystemTime();
		}
	}

	private void setupVecPoints()
	{
		for(x1 = 0; x1<pointWidth; x1++)
		{
			// Loop Through The Y Plane
			for(y1=0; y1<pointHeight; y1++)
			{
				// Apply The Wave To Our Mesh
				points[x1][y1][0]=(float)((x1/5.0f)-4.5f);
				points[x1][y1][1]=(float)((y1/5.0f)-4.5f);
//				points[x1][y1][2]=(float)(Math.sin((((x1/5.0f)*40.0f)/360.0f)*3.141592654*2.0f));
				points[x1][y1][2]=(float)(Math.sin((((x1/5.0f)*40.0f)/360.0f)*3.141592654*2));
				
			}
		}
	}
	
	private void shiftWaveArray()
	{
		 for( y1 = 0; y1 < pointHeight; y1++ )            // Loop Through The Y Plane
		 {
		        hold=points[0][y1][2];           // Store Current Value One Left Side Of Wave
		        for( x1 = 0; x1 < (pointWidth-1); x1++)     // Loop Through The X Plane
		        {
		            // Current Wave Value Equals Value To The Right
		            points[x1][y1][2] = points[x1+1][y1][2];
		        }
		        points[(pointWidth-1)][y1][2]=hold;          // Last Value Becomes The Far Left Stored Value
		 }
	}

    public float getCollisionBorderSize()
    {
        return 0.0F;
    }
    
    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }
    
	@Override
	public int getWidthPixels() {
		return 12;
	}

	@Override
	public int getHeightPixels() {
		return 12;
	}
	
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = 16.0D;
        d0 = d0 * 64.0D * getRenderDistanceWeight();
        return distance < d0 * d0;
    }

	@Override
	public void onBroken(Entity brokenEntity) {
        this.playSound(SoundEvents.ENTITY_ITEMFRAME_BREAK, 1.0F, 1.0F);
        this.dropItemOrSelf(brokenEntity, true);	
	}

	@Override
	public void playPlaceSound() {
		this.playSound(SoundEvents.ENTITY_ITEMFRAME_PLACE, 1.0F, 1.0F);
	}
	
	public void dropItemOrSelf(@Nullable Entity entityIn, boolean p_146065_2_)
    {
        if (p_146065_2_)
        {
            this.entityDropItem(new ItemStack(ModItems.flag), 0.0F);
        }  
    }
	

    public void notifyDataManagerChange(DataParameter<?> key)
    {
        if (key.equals(FLAG_REGISTRY_NAME))
        {
        	this.setFlagEntry(new ResourceLocation(this.getFlagEntry()));
        }
    }
    
    
    public String getFlagEntry()
    {
        return ((String)this.getDataManager().get(FLAG_REGISTRY_NAME));
    }
    
    
    public void setFlagEntry(ResourceLocation flagID)
    {
    	this.FLAGENTRY = FlagRegistry.FLAGS.get(flagID);
    }
	
	@Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {

		if (!this.world.isRemote && player.isSneaking()) 
		{
				player.openGui(FlaggedMod.INSTANCE, GuiHandler.FLAG_GUI, player.world, getEntityId(), 0, 0);
				
				return true;
		}
		return super.processInitialInteract(player, hand);
	}
	
    //TODO Temp
    public ResourceLocation getResourceTexture()
    {
    	return new ResourceLocation(ModReference.MOD_ID, "blocks/flags/"+getFlagEntry()+".png");
    }

    public void setTexture(String texture)
    {
    	this.getDataManager().set(FLAG_REGISTRY_NAME, texture);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setString("Flag", this.getFlagEntry());

        super.writeEntityToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        this.setTexture(compound.getString("Flag"));

        super.readEntityFromNBT(compound);
    }

}
