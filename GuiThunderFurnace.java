package tutorial.basic;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.I18n;

public class GuiThunderFurnace extends GuiContainer
{
	public static final ResourceLocation texture = new ResourceLocation("tribesmod:textures/gui/thunderFurnace.png");
	
	public TileEntityThunderFurnace thunderFurnace;
	
	public GuiThunderFurnace(InventoryPlayer inventoryPlayer, TileEntityThunderFurnace entity)
	{
		super(new ContainerThunderFurnace(inventoryPlayer, entity));
		
		this.thunderFurnace = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String name = this.thunderFurnace.isInvNameLocalized() ? this.thunderFurnace.getInvName() : I18n.getString(this.thunderFurnace.getInvName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2 + 35, 6, 4210752);
		this.fontRenderer.drawString(I18n.getString("container.inventory"), 75, this.ySize - 96 + 2, 4210752);
	}
	
	public void drawGuiContainerBackgroundLayer(float f, int i, int j) 
	{
		GL11.glColor4f(1F, 1F, 1F, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if(this.thunderFurnace.isBurning())
		{
			int k = this.thunderFurnace.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(guiLeft + 42, guiTop + 44 + 12 - k, 176, 12 - k, 8, k + 2);
		}
		
		int k = this.thunderFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, k + 1, 16);
	}

}
