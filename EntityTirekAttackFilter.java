package tutorial.basic;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.*;

final class EntityTirekAttackFilter implements IEntitySelector
{
    /**
     * Return whether the specified entity is applicable to this filter.
     */
    public boolean isEntityApplicable(Entity par1Entity)
    {
        return par1Entity instanceof EntityLivingBase && !(par1Entity instanceof EntitySquid);
    }
}
