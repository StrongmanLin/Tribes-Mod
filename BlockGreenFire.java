package tutorial.basic;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGreenFire extends BlockFire
{
	@SideOnly(Side.CLIENT)
    private Icon[] iconArray;

	
	protected BlockGreenFire(int par1)
	{
		super(par1);
	}
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (!(par1World.provider.dimensionId == 0 || par1World.provider.dimensionId != TribesMod.breezyDimensionID) || par1World.getBlockId(par2, par3 - 1, par4) != TribesMod.breezyPortalFrame.blockID || !((BlockBreezyPortal)TribesMod.breezyPortal).tryToCreatePortal(par1World, par2, par3, par4))
        {
            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !this.canNeighborBurn(par1World, par2, par3, par4))
            {
                par1World.setBlockToAir(par2, par3, par4);
            }
            else
            {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World) + par1World.rand.nextInt(10));
            }
        }
    }
	
	private boolean canNeighborBurn(World par1World, int par2, int par3, int par4)
    {
        return canBlockCatchFire(par1World, par2 + 1, par3, par4, WEST ) ||
               canBlockCatchFire(par1World, par2 - 1, par3, par4, EAST ) ||
               canBlockCatchFire(par1World, par2, par3 - 1, par4, UP   ) ||
               canBlockCatchFire(par1World, par2, par3 + 1, par4, DOWN ) ||
               canBlockCatchFire(par1World, par2, par3, par4 - 1, SOUTH) ||
               canBlockCatchFire(par1World, par2, par3, par4 + 1, NORTH);
    }
	
	@SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[] {par1IconRegister.registerIcon(this.getTextureName() + "_layer_0"), par1IconRegister.registerIcon(this.getTextureName() + "_layer_1")};
    }

    @SideOnly(Side.CLIENT)
    public Icon getFireIcon(int par1)
    {
        return this.iconArray[par1];
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return this.iconArray[0];
    }
}
