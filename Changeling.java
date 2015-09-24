package tutorial.basic;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDummyContainer;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.Event.Result;

public class Changeling extends EntityMob
{
    public float field_70886_e;
    public float destPos;
    public float field_70884_g;
    public float field_70888_h;
    public float field_70889_i = 1.0F;
    public int prevAttackCounter;
    public int attackCounter;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    public int courseChangeCooldown;
    private Entity targetedEntity;
    private int aggroCooldown;
    private int explosionStrength = 1;
    public int livingSoundTime;

    public Changeling(World par1World)
    {
        super(par1World);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }
    
    protected boolean isValidLightLevel()
    {
    	return true;
    }
    
    public int getTotalArmorValue()
    {
        return 0;
    }
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
    
    public int getTalkInterval()
    {
        if(this.onGround)
        	return 80;
        else
        	return 0;
    }
    
    public void onEntityUpdate()
    {
        super.onEntityUpdate();
        this.worldObj.theProfiler.startSection("mobBaseTick");

        if (this.isEntityAlive() && this.rand.nextInt(1000) < this.livingSoundTime++)
        {
            this.livingSoundTime = -this.getTalkInterval();
            this.playLivingSound();
        }

        this.worldObj.theProfiler.endSection();
    }
    
    public void playLivingSound()
    {
        String s = this.getLivingSound();

        if (s != null)
        {
            this.playSound(s, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
    	if(findPlayerToAttack() != null)
    	{
    		this.prevAttackCounter = this.attackCounter;
            double d0 = this.waypointX - this.posX;
            double d1 = this.waypointY - this.posY;
            double d2 = this.waypointZ - this.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d3 < 1.0D || d3 > 3600.0D)
            {
                this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
                this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
                this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            }

            if (this.courseChangeCooldown-- <= 0)
            {
                this.courseChangeCooldown += this.rand.nextInt(5) + 2;
                d3 = (double)MathHelper.sqrt_double(d3);

                if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
                {
                    this.motionX += d0 / d3 * 0.1D;
                    this.motionY += d1 / d3 * 0.1D;
                    this.motionZ += d2 / d3 * 0.1D;
                }
                else
                {
                    this.waypointX = this.posX;
                    this.waypointY = this.posY;
                    this.waypointZ = this.posZ;
                }
            }

            if (this.targetedEntity != null && this.targetedEntity.isDead)
            {
                this.targetedEntity = null;
            }

            if (this.targetedEntity == null || this.aggroCooldown-- <= 0)
            {
                this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

                if (this.targetedEntity != null)
                {
                    this.aggroCooldown = 20;
                }
            }

            double d4 = 64.0D;

            if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
            {
                double d5 = this.targetedEntity.posX - this.posX;
                double d6 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
                double d7 = this.targetedEntity.posZ - this.posZ;
                this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

                if (this.canEntityBeSeen(this.targetedEntity))
                {
                    if (this.attackCounter == 10)
                    {
                        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                    }

                    ++this.attackCounter;

                    if (this.attackCounter == 20)
                    {
                        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                        EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, d5, d6, d7);
                        entitylargefireball.field_92057_e = this.explosionStrength;
                        double d8 = 4.0D;
                        Vec3 vec3 = this.getLook(1.0F);
                        entitylargefireball.posX = this.posX + vec3.xCoord * d8;
                        entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
                        entitylargefireball.posZ = this.posZ + vec3.zCoord * d8;
                        this.worldObj.spawnEntityInWorld(entitylargefireball);
                        this.attackCounter = -40;
                    }
                }
                else if (this.attackCounter > 0)
                {
                    --this.attackCounter;
                }
            }
            else
            {
                this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;

                if (this.attackCounter > 0)
                {
                    --this.attackCounter;
                }
            }

    	}
        super.onLivingUpdate();
        this.field_70888_h = this.field_70886_e;
        this.field_70884_g = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);

        if (this.destPos < 0.0F)
        {
            this.destPos = 0.0F;
        }

        if (this.destPos > 1.0F)
        {
            this.destPos = 1.0F;
        }

        if (!this.onGround && this.field_70889_i < 1.0F)
        {
            this.field_70889_i = 1.0F;
        }

        this.field_70889_i = (float)((double)this.field_70889_i * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        this.field_70886_e += this.field_70889_i * 2.0F;
    }
    
    private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
    {
        double d4 = (this.waypointX - this.posX) / par7;
        double d5 = (this.waypointY - this.posY) / par7;
        double d6 = (this.waypointZ - this.posZ) / par7;
        AxisAlignedBB axisalignedbb = this.boundingBox.copy();

        for (int i = 1; (double)i < par7; ++i)
        {
            axisalignedbb.offset(d4, d5, d6);

            if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
            {
                return false;
            }
        }

        return true;
    }
    
    public void moveEntityWithHeading(float par1, float par2)
    {
    	if(findPlayerToAttack() != null)
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
    	else
    	{
    		double d0;

            if (this.isInWater())
            {
                d0 = this.posY;
                this.moveFlying(par1, par2, this.isAIEnabled() ? 0.04F : 0.02F);
                this.moveEntity(this.motionX, this.motionY, this.motionZ);
                this.motionX *= 0.800000011920929D;
                this.motionY *= 0.800000011920929D;
                this.motionZ *= 0.800000011920929D;
                this.motionY -= 0.02D;

                if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
                {
                    this.motionY = 0.30000001192092896D;
                }
            }
            else if (this.handleLavaMovement())
            {
                d0 = this.posY;
                this.moveFlying(par1, par2, 0.02F);
                this.moveEntity(this.motionX, this.motionY, this.motionZ);
                this.motionX *= 0.5D;
                this.motionY *= 0.5D;
                this.motionZ *= 0.5D;
                this.motionY -= 0.02D;

                if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
                {
                    this.motionY = 0.30000001192092896D;
                }
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
                float f4;

                if (this.onGround)
                {
                    f4 = this.getAIMoveSpeed() * f3;
                }
                else
                {
                    f4 = this.jumpMovementFactor;
                }

                this.moveFlying(par1, par2, f4);
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

                if (this.isOnLadder())
                {
                    float f5 = 0.15F;

                    if (this.motionX < (double)(-f5))
                    {
                        this.motionX = (double)(-f5);
                    }

                    if (this.motionX > (double)f5)
                    {
                        this.motionX = (double)f5;
                    }

                    if (this.motionZ < (double)(-f5))
                    {
                        this.motionZ = (double)(-f5);
                    }

                    if (this.motionZ > (double)f5)
                    {
                        this.motionZ = (double)f5;
                    }

                    this.fallDistance = 0.0F;

                    if (this.motionY < -0.15D)
                    {
                        this.motionY = -0.15D;
                    }

                    boolean flag = this.isSneaking();

                    if (flag && this.motionY < 0.0D)
                    {
                        this.motionY = 0.0D;
                    }
                }

                this.moveEntity(this.motionX, this.motionY, this.motionZ);

                if (this.isCollidedHorizontally && this.isOnLadder())
                {
                    this.motionY = 0.2D;
                }

                if (this.worldObj.isRemote && (!this.worldObj.blockExists((int)this.posX, 0, (int)this.posZ) || !this.worldObj.getChunkFromBlockCoords((int)this.posX, (int)this.posZ).isChunkLoaded))
                {
                    if (this.posY > 0.0D)
                    {
                        this.motionY = -0.1D;
                    }
                    else
                    {
                        this.motionY = 0.0D;
                    }
                }
                else
                {
                    this.motionY -= 0.08D;
                }

                this.motionY *= 0.9800000190734863D;
                this.motionX *= (double)f2;
                this.motionZ *= (double)f2;
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            d0 = this.posX - this.prevPosX;
            double d1 = this.posZ - this.prevPosZ;
            float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

            if (f6 > 1.0F)
            {
                f6 = 1.0F;
            }

            this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
    	}
    }
    
    public void moveFlying(float par1, float par2, float par3)
    {
        float f3 = par1 * par1 + par2 * par2;

        if (f3 >= 1.0E-4F)
        {
            f3 = MathHelper.sqrt_float(f3);

            if (f3 < 1.0F)
            {
                f3 = 1.0F;
            }

            f3 = par3 / f3;
            par1 *= f3;
            par2 *= f3;
            float f4 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
            float f5 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);
            this.motionX += (double)(par1 * f5 - par2 * f4);
            this.motionZ += (double)(par2 * f5 + par1 * f4);
        }
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (!super.attackEntityFrom(par1DamageSource, par2))
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && this.getEntityToAttack() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)this.getEntityToAttack();
            }

            if (entitylivingbase == null && par1DamageSource.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)par1DamageSource.getEntity();
            }

            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ);

            return true;
        }
    }
    
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag && this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float)this.worldObj.difficultySetting * 0.3F)
        {
            par1Entity.setFire(2 * this.worldObj.difficultySetting);
        }

        return flag;
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1) {}

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        if(this.onGround)
        	return "mob.changeling.changelingGroundIdleSound";
        else
        	return "mob.changeling.changelingAirIdleSound";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.changeling.changelingHurtSound";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.silverfish.kill";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.chicken.step", 0.15F, 1.0F);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return TribesMod.crystalHeart.itemID;
    }

    /**
     * Drop 0-1 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
    	int j = this.getDropItemId();

        if (j > 0)
        {
            int k = this.rand.nextInt(2);

            if (par2 > 0)
            {
                k += this.rand.nextInt(par2 + 1);
            }

            for (int l = 0; l < k; l++)
            {
                this.dropItem(j, 1);
            }
        }
    }
}
