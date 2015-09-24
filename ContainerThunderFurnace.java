package tutorial.basic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerThunderFurnace extends Container
{
	private TileEntityThunderFurnace thunderFurnace;
	
	public int lastBurnTime;
	
	public int lastItemBurnTime;
	
	public int lastCookTime;
	
	public ContainerThunderFurnace(InventoryPlayer inventory, TileEntityThunderFurnace tileEntity)
	{
		this.thunderFurnace = tileEntity;
		
		this.addSlotToContainer(new Slot(tileEntity, 0, 30, 7));
		this.addSlotToContainer(new Slot(tileEntity, 1, 30, 25));
		this.addSlotToContainer(new Slot(tileEntity, 2, 48, 7));
		this.addSlotToContainer(new Slot(tileEntity, 3, 48, 25));
		this.addSlotToContainer(new Slot(tileEntity, 4, 39, 61));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileEntity, 5, 116, 35));
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + 18 * j, 84 + 18 * i));
			}
		for(int i = 0; i < 9; i++)
			this.addSlotToContainer(new Slot(inventory, i, 8 + 18 * i, 142));
	}
	
	public void addCraftingToCrafters(ICrafting icrafting)
	{
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.thunderFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.thunderFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 2, this.thunderFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 3, this.thunderFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 4, this.thunderFurnace.burnTime);
		icrafting.sendProgressBarUpdate(this, 5, this.thunderFurnace.currentItemBurnTime);
	}
	
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			
			if(this.lastCookTime != this.thunderFurnace.cookTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.thunderFurnace.cookTime);
				icrafting.sendProgressBarUpdate(this, 1, this.thunderFurnace.cookTime);
				icrafting.sendProgressBarUpdate(this, 2, this.thunderFurnace.cookTime);
				icrafting.sendProgressBarUpdate(this, 3, this.thunderFurnace.cookTime);
			}
			if(this.lastBurnTime != this.thunderFurnace.burnTime)
			{
				icrafting.sendProgressBarUpdate(this, 4, this.thunderFurnace.burnTime);
			}
			if(this.lastItemBurnTime != this.thunderFurnace.currentItemBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 5, this.thunderFurnace.currentItemBurnTime);
			}
		}
		
		this.lastCookTime = this.thunderFurnace.cookTime;
		this.lastBurnTime = this.thunderFurnace.burnTime;
		this.lastItemBurnTime = this.thunderFurnace.currentItemBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue)
	{
		if(slot == 0 || slot == 1 || slot == 2 || slot == 3)
		{
			this.thunderFurnace.cookTime = newValue;
		}
		if(slot == 4)
		{
			this.thunderFurnace.burnTime = newValue;
		}
		if(slot == 5)
		{
			this.thunderFurnace.currentItemBurnTime = newValue;
		}
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int par2)
	{
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 5)
            {
                if (!this.mergeItemStack(itemstack1, 6, 42, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 4 && par2 != 3 && par2 != 2 && par2 != 1 && par2 != 0)
            {
                if (itemstack1.getItem().itemID == TribesMod.ingotChromium.itemID || itemstack1.getItem().itemID == TribesMod.ingotNickel.itemID || itemstack1.getItem().itemID == TribesMod.ingotManganese.itemID || itemstack1.getItem().itemID == Item.ingotIron.itemID)
                {
                	if(!this.mergeItemStack(itemstack1, 0, 1, false) && !this.mergeItemStack(itemstack1, 1, 2, false) && !this.mergeItemStack(itemstack1, 2, 3, false) && !this.mergeItemStack(itemstack1, 3, 4, false))
                	{
                        return null;
                    }
                }
                else if (itemstack1.getItem().itemID == Item.bucketLava.itemID)
                {
                    if (!this.mergeItemStack(itemstack1, 4, 5, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 6 && par2 < 33)
                {
                    if (!this.mergeItemStack(itemstack1, 33, 42, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 33 && par2 < 42 && !this.mergeItemStack(itemstack1, 6, 33, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 6, 42, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
	}
	
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return this.thunderFurnace.isUseableByPlayer(entityplayer);
	}

}
