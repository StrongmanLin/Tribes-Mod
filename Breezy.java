package tutorial.basic;

import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class Breezy extends EntityVillager implements IMerchant
{
	private EntityPlayer buyingPlayer;
	private MerchantRecipeList buyingList;
	private int timeUntilReset;
	private boolean needsInitilization;
	private float field_82191_bN;
	
	public Breezy(World par1World)
	{
		super(par1World);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new BreezyAITradePlayer(this));
        this.tasks.addTask(1, new BreezyAILookAtTradePlayer(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}
	
	
	public boolean isAIEnabled()
    {
        return true;
    }
	
	protected void updateAITick()
    {
        if (!this.isTrading() && this.timeUntilReset > 0)
        {
            --this.timeUntilReset;

            if (this.timeUntilReset <= 0)
            {
                if (this.needsInitilization)
                {
                    if (this.buyingList.size() > 1)
                    {
                        Iterator iterator = this.buyingList.iterator();

                        while (iterator.hasNext())
                        {
                            MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();

                            if (merchantrecipe.func_82784_g())
                            {
                                merchantrecipe.func_82783_a(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                            }
                        }
                    }

                    this.addDefaultEquipmentAndRecipies(1);
                    this.needsInitilization = false;
                }

                this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
            }
        }

        super.updateAITick();
    }
	
	public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
        boolean flag = itemstack != null && itemstack.itemID == Item.monsterPlacer.itemID;

        if (!flag && this.isEntityAlive() && !this.isTrading() && !par1EntityPlayer.isSneaking())
        {
            if (!this.worldObj.isRemote)
            {
                this.setCustomer(par1EntityPlayer);
                par1EntityPlayer.displayGUIMerchant(this, this.getCustomNameTag());
            }

            return true;
        }
        else
        {
            return super.interact(par1EntityPlayer);
        }
    }
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (this.buyingList != null)
        {
            par1NBTTagCompound.setCompoundTag("Offers", this.buyingList.getRecipiesAsTags());
        }
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("Offers"))
        {
            NBTTagCompound nbttagcompound1 = par1NBTTagCompound.getCompoundTag("Offers");
            this.buyingList = new MerchantRecipeList(nbttagcompound1);
        }
    }
	
	protected boolean canDespawn()
    {
        return true;
    }
	
	protected String getLivingSound()
    {
		return "mob.breezy.breezyIdle";
    }
	
	protected String getHurtSound()
    {
		return "mob.breezy.breezyHurt";
    }
	
	protected String getDeathSound()
    {
		return "mob.breezy.breezyDead";
    }
	
	
	
	public void moveEntityWithHeading(float par1, float par2)
    {
        if (this.isInWater())
        {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.handleLavaMovement())
        {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float f2 = 0.91F;

            if (this.onGround)
            {
                f2 = 0.54600006F;
                int i = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

                if (i > 0)
                {
                    f2 = Block.blocksList[i].slipperiness * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            this.moveFlying(par1, par2, this.onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;

            if (this.onGround)
            {
                f2 = 0.54600006F;
                int j = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

                if (j > 0)
                {
                    f2 = Block.blocksList[j].slipperiness * 0.91F;
                }
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f2;
            this.motionY *= (double)f2;
            this.motionZ *= (double)f2;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d0 = this.posX - this.prevPosX;
        double d1 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }

        this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    public boolean isOnLadder()
    {
        return false;
    }
    
    public void setCustomer(EntityPlayer par1EntityPlayer)
    {
        this.buyingPlayer = par1EntityPlayer;
    }
    
    public boolean isTrading()
    {
        return this.buyingPlayer != null;
    }

    public EntityPlayer getCustomer()
    {
        return this.buyingPlayer;
    }
    
    private float adjustProbability(float par1)
    {
        float f1 = par1 + this.field_82191_bN;
        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }
    
    public static void addMerchantItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3)
    {
        if (par2Random.nextFloat() < par3)
        {
            par0MerchantRecipeList.add(new MerchantRecipe(getRandomSizedStack(par1, par2Random), TribesMod.ingotPearthony));
        }
    }
    
    private static ItemStack getRandomSizedStack(int par0, Random par1Random)
    {
        return new ItemStack(par0, getRandomCountForItem(par0, par1Random), 0);
    }
    
    private static int getRandomCountForItem(int par0, Random par1Random)
    {
        return 1;
    }
    
    private void addDefaultEquipmentAndRecipies(int par1)
    {
    	if (this.buyingList != null)
        {
            this.field_82191_bN = MathHelper.sqrt_float((float)this.buyingList.size()) * 0.2F;
        }
        else
        {
            this.field_82191_bN = 0.0F;
        }

        MerchantRecipeList merchantrecipelist;
        merchantrecipelist = new MerchantRecipeList();
        VillagerRegistry.manageVillagerTrades(merchantrecipelist, this, 0, this.rand);

        addMerchantItem(merchantrecipelist, TribesMod.breezyPollen.itemID, this.rand, this.adjustProbability(0.9F));
        merchantrecipelist.add(new MerchantRecipe(new ItemStack(TribesMod.breezyPollen), new ItemStack(TribesMod.ingotPearthony)));
               

        if (merchantrecipelist.isEmpty())
        {
            addMerchantItem(merchantrecipelist, Item.ingotGold.itemID, this.rand, 1.0F);
        }

        Collections.shuffle(merchantrecipelist);

        if (this.buyingList == null)
        {
            this.buyingList = new MerchantRecipeList();
        }

        for (int j1 = 0; j1 < par1 && j1 < merchantrecipelist.size(); ++j1)
        {
            this.buyingList.addToListWithCheck((MerchantRecipe)merchantrecipelist.get(j1));
        }
    }

	@Override
	public MerchantRecipeList getRecipes(EntityPlayer entityplayer) {
		if (this.buyingList == null)
        {
            this.addDefaultEquipmentAndRecipies(1);
        }

        return this.buyingList;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setRecipes(MerchantRecipeList merchantrecipelist) {}

	@Override
	public void useRecipe(MerchantRecipe merchantrecipe) {}

	@Override
	public void func_110297_a_(ItemStack itemstack) {}
    
    
}
