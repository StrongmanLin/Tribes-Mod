package tutorial.basic;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderBreezy extends WorldProvider
{
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(TribesMod.biomeBreezyForest, dimensionId, dimensionId);
		this.dimensionId = TribesMod.breezyDimensionID;
	}
	
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderBreezy(worldObj, worldObj.getSeed(), true);
	}
	
	@Override
	public String getDimensionName()
	{
		return "Breezy Dimension";
	}
}