package tutorial.basic;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTirek extends RenderLiving
{
  	private static final ResourceLocation tirek = new ResourceLocation("tribesmod", "/textures/mobs/tirek.png");
	public RenderTirek(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}
	
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return tirek;
	}
}