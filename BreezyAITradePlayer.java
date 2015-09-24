package tutorial.basic;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class BreezyAITradePlayer extends EntityAIBase
{
    private Breezy breezy;

    public BreezyAITradePlayer(Breezy par1Breezy)
    {
        this.breezy = par1Breezy;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.breezy.isEntityAlive())
        {
            return false;
        }
        else if (this.breezy.isInWater())
        {
            return false;
        }
        else
        {
            EntityPlayer entityplayer = (this.breezy).getCustomer();
            return entityplayer == null ? false : (this.breezy.getDistanceSqToEntity(entityplayer) > 16.0D ? false : entityplayer.openContainer instanceof Container);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.breezy.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        (this.breezy).setCustomer((EntityPlayer)null);
    }
}
