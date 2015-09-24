package tutorial.basic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler
{
	public GuiHandler()
	{
		NetworkRegistry.instance().registerGuiHandler(TribesMod.instance, this);
	}
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null)
		{
			switch(ID)
			{
				case TribesMod.guiIDThunderFurnace:
					if(entity instanceof TileEntityThunderFurnace)
					{
						return new ContainerThunderFurnace(player.inventory, (TileEntityThunderFurnace) entity);
					}
			}
		}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null)
		{
			switch(ID)
			{
				case TribesMod.guiIDThunderFurnace:
					if(entity instanceof TileEntityThunderFurnace)
					{
						return new GuiThunderFurnace(player.inventory, (TileEntityThunderFurnace) entity);
					}
			}
		}
		return null;
	}
	
}
