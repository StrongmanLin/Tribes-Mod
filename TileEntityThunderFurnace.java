package tutorial.basic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityThunderFurnace extends TileEntity implements ISidedInventory
{
	private String localizedName;
	
	private static final int[] slots_top = {0, 1, 2, 3};
	private static final int[] slots_bottom = {5};
	private static final int[] slots_sides = {4};
	
	private ItemStack[] slots = new ItemStack[6];
	
	public int furnaceSpeed = 200;
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;
	
	public int getSizeInventory()
	{
		return this.slots.length;
	}
	
	public String getInvName()
	{
		return this.isInvNameLocalized() ? this.localizedName : "container.thunderFurnace";
	}
	
	public boolean isInvNameLocalized()
	{
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	
	public void setGuiDisplayName(String displayName)
	{
		this.localizedName = displayName;
	}

	public ItemStack getStackInSlot(int i)
	{
		return this.slots[i];
	}

	public ItemStack decrStackSize(int i, int j)
	{
		if (this.slots[i] != null)
        {
            ItemStack itemstack;

            if (this.slots[i].stackSize <= j)
            {
                itemstack = this.slots[i];
                this.slots[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.slots[i].splitStack(j);

                if (this.slots[i].stackSize == 0)
                {
                    this.slots[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	public ItemStack getStackInSlotOnClosing(int i)
	{
		if (this.slots[i] != null)
        {
            ItemStack itemstack = this.slots[i];
            this.slots[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.slots[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
	}

	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		NBTTagList list = nbt.getTagList("Items");
		this.slots = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound compound = (NBTTagCompound) list.tagAt(i);
			byte b = compound.getByte("Slot");
			if(b >= 0 && b < this.slots.length)
			{
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		
		this.burnTime = nbt.getShort("burnTime");
		this.cookTime = nbt.getShort("cookTime");
		this.currentItemBurnTime = getItemBurnTime(this.slots[4]);
		
		if(nbt.hasKey("CustomName"))
		{
			this.localizedName = nbt.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setShort("burnTime", (short) this.burnTime);
		nbt.setShort("cookTime", (short) this.cookTime);
		
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < this.slots.length; i++)
		{
			if(this.slots[i] != null)
			{
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		nbt.setTag("Items", list);
		
		if(this.isInvNameLocalized())
		{
			nbt.setString("CustomName", this.localizedName);
		}
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openChest(){}

	public void closeChest() {}
	
	public ItemStack getSmeltingResult(ItemStack a, ItemStack b, ItemStack c, ItemStack d)
	{
        if (this.slots[0] == null || this.slots[1] == null || this.slots[2] == null || this.slots[3] == null)
        {
            return null;
        }
        boolean ch = false;
		boolean ir = false;
		boolean ma = false;
		boolean ni = false;
		for(int i = 0; i < 4; i++)
	    {
			if(slots[i].getItem().itemID == TribesMod.ingotChromium.itemID)
				ch = true;
			if(slots[i].getItem().itemID == TribesMod.ingotManganese.itemID)
				ma = true;
			if(slots[i].getItem().itemID == TribesMod.ingotNickel.itemID)
				ni = true;
			if(slots[i].getItem().itemID == Item.ingotIron.itemID)
				ir = true;
		}
		if(ch == ma == ni == ir == true)
			return new ItemStack(TribesMod.thunderForgedSteel);
		return null;
	}
	
	private boolean canSmelt()
    {
		if (this.slots[0] == null || this.slots[1] == null || this.slots[2] == null || this.slots[3] == null)
        {
            return false;
        }
		else
        {
            ItemStack itemstack = getSmeltingResult(this.slots[0], this.slots[1], this.slots[2], this.slots[3]);
            if (itemstack == null) return false;
            if (this.slots[5] == null) return true;
            if (!this.slots[5].isItemEqual(itemstack)) return false;
            int result = slots[5].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
	
	public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = getSmeltingResult(this.slots[0], this.slots[1], this.slots[2], this.slots[3]);

            if (this.slots[5] == null)
            {
                this.slots[5] = itemstack.copy();
            }
            else if (this.slots[5].isItemEqual(itemstack))
            {
                slots[5].stackSize += itemstack.stackSize;
            }

            this.slots[0].stackSize--;
            this.slots[1].stackSize--;
            this.slots[2].stackSize--;
            this.slots[3].stackSize--;

            if (this.slots[0].stackSize <= 0)
            {
                this.slots[0] = null;
            }
            if (this.slots[1].stackSize <= 0)
            {
                this.slots[1] = null;
            }
            if (this.slots[2].stackSize <= 0)
            {
                this.slots[2] = null;
            }
            if (this.slots[3].stackSize <= 0)
            {
                this.slots[3] = null;
            }
        }
    }
	
	public boolean isBurning()
    {
        return this.burnTime > 0;
    }
	
	public void updateEntity()
	{
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		
		if(this.burnTime > 0)
			this.burnTime--;
		if(!this.worldObj.isRemote)
		{
			if(this.burnTime == 0 && this.canSmelt())
			{
				this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[4]);
				
				if(this.burnTime > 0)
				{
					flag1 = true;
					if(this.slots[4] != null)
					{
						this.slots[4].stackSize--;
						
						if(this.slots[4].stackSize == 0)
						{
							this.slots[4] = this.slots[4].getItem().getContainerItemStack(this.slots[4]);
						}
					}
				}
			}
			if(this.isBurning() && this.canSmelt())
			{
				this.cookTime++;
				if(this.cookTime == this.furnaceSpeed)
				{
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
			{
				this.cookTime = 0;
			}
			if(flag  != this.burnTime > 0)
			{
				BlockThunderFurnace.updateFurnaceBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
				flag1 = true;
			}
		}
		if(flag1)
		{
			this.onInventoryChanged();
		}
	}
	
	public static int getItemBurnTime(ItemStack itemstack)
	{
		if (itemstack == null)
        {
            return 0;
        }
		if(itemstack.getItem() != Item.bucketLava)
			return 0;
		else
			return 2000;
	}
	
	public boolean isItemThunderable(ItemStack itemstack)
	{
		return itemstack.getItem().itemID == TribesMod.ingotChromium.itemID || itemstack.getItem().itemID == TribesMod.ingotManganese.itemID || itemstack.getItem().itemID == TribesMod.ingotNickel.itemID ? true : false;
	}
	
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return i == 5 ? false : (i == 4 ? (itemstack.getItem() == Item.bucketLava ? true : false) : (isItemThunderable(itemstack) ? true : false));
	}

	public int[] getAccessibleSlotsFromSide(int var1)
	{
		return (var1 < 4 && var1 > -1) ? slots_top : (var1 == 5 ? slots_bottom : slots_sides);
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return this.isItemValidForSlot(i, itemstack);
	}

	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return j != 0 || i != 1 || itemstack.itemID == Item.bucketEmpty.itemID;
	}

	public int getBurnTimeRemainingScaled(int i)
	{
		if(this.currentItemBurnTime == 0)
			this.currentItemBurnTime = this.furnaceSpeed;
		return this.burnTime * i / this.currentItemBurnTime;
	}
	
	public int getCookProgressScaled(int i)
	{
		return this.cookTime * i / this.furnaceSpeed;
	}
}