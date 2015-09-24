package tutorial.basic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBreezyDirt extends Block
{
    protected BlockBreezyDirt(int par1)
    {
        super(par1, Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}