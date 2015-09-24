package tutorial.basic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockJukeBox;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWand extends Item
{

	public ItemWand(int par1)
	{
		super(par1);
		setMaxStackSize(1);
	}
	
	public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.obsidian ? true : false;
    }
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if (par3World.getBlockId(par4, par5, par6) == Block.blockGold.blockID)
        {
			changeBlock(null, par3World, par4, par5, par6);
			return true;
        }
        else
        {
            return false;
        }
    }
	
	public void changeBlock(Block par1Block, World par2World, int x, int y, int z)
	{
		par2World.removeBlockTileEntity(x, y, z);
		par2World.setBlock(x, y, z, TribesMod.breezyPortalFrame.blockID);
	}
}