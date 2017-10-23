package com.gendeathrow.flagged.items;

import javax.annotation.Nullable;

import com.gendeathrow.flagged.FlaggedMod;
import com.gendeathrow.flagged.entity.EntityHangingSpecial;
import com.gendeathrow.flagged.entity.EntityItemFlag;
import com.gendeathrow.flagged.init.ModReference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFlag extends Item{

	private Class<? extends EntityHangingSpecial>  hangingEntityClass; 

    public ItemFlag(Class <? extends EntityHangingSpecial > entityClass)
    {
        this.hangingEntityClass = entityClass;
        this.setCreativeTab(FlaggedMod.TAB);
    }
    

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        BlockPos blockpos = pos.offset(facing);

        if (facing != EnumFacing.DOWN && facing != EnumFacing.UP && player.canPlayerEdit(blockpos, facing, itemstack))
        {
        	EntityHangingSpecial entityhanging = this.createEntity(worldIn, blockpos, facing);

            if (entityhanging != null)
            {
                if (!worldIn.isRemote)
                {
                    entityhanging.playPlaceSound();
                    worldIn.spawnEntity(entityhanging);
                }

                itemstack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
    
	@Nullable
    private EntityHangingSpecial createEntity(World worldIn, BlockPos pos, EnumFacing clickedSide)
    {
        return new EntityItemFlag(worldIn, pos, clickedSide);
    }
}
