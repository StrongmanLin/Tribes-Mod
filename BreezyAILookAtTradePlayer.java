package tutorial.basic;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class BreezyAILookAtTradePlayer extends EntityAIWatchClosest
{
    private final Breezy theMerchant;

    public BreezyAILookAtTradePlayer(Breezy par1Breezy)
    {
        super(par1Breezy, EntityPlayer.class, 8.0F);
        this.theMerchant = par1Breezy;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.theMerchant.isTrading())
        {
            this.closestEntity = this.theMerchant.getCustomer();
            return true;
        }
        else
        {
            return false;
        }
    }
}
