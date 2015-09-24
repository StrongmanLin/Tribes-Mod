package tutorial.basic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemThunderArmor extends ItemArmor
{
	private String texturePath = "tribesmod:textures/model/armor/";
	
	public ItemThunderArmor(int i, EnumArmorMaterial material, int j, int type, String name)
	{
		super(i, material, j, type);
		this.setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabCombat);
		this.setTextureName(name, type);
	}
	
	private void setTextureName(String type, int part)
	{
		if(part == 0 || part == 1 || part == 3)
			this.texturePath += "thunder" + "_layer_1.png";
		else
			this.texturePath += "thunder" + "_layer_2.png";
	}
	
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer)
	{
		return texturePath;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register)
	{
		this.itemIcon = register.registerIcon("tribesmod:" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
}
