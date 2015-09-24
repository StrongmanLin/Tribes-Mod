package tutorial.basic;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

public class ItemHoneyBucket extends ItemBucket
{
	private int liquidID;
	
	public ItemHoneyBucket(int itemID, int liquidID)
	{
		super(itemID, liquidID);
		
		this.liquidID = liquidID;
	}

}
