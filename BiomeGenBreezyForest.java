package tutorial.basic;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenBreezyForest extends BiomeGenBase
{
	protected WorldGenBreezyTrees worldGeneratorBreezyTrees = new WorldGenBreezyTrees(false, 12);
	
    public BiomeGenBreezyForest(int par1)
    {
        super(par1);
        this.topBlock = (byte)TribesMod.breezyGrass.blockID;
        this.fillerBlock = (byte)TribesMod.breezyDirt.blockID;
        this.theBiomeDecorator.treesPerChunk = 20;
        this.spawnableCreatureList.removeAll(spawnableCreatureList);
        this.spawnableMonsterList.removeAll(spawnableMonsterList);
        this.spawnableCaveCreatureList.removeAll(spawnableCaveCreatureList);
        this.spawnableWaterCreatureList.removeAll(spawnableWaterCreatureList);
        this.spawnableCreatureList.add(new SpawnListEntry(Breezy.class, 200, 10, 12));
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(this.worldGeneratorBreezyTrees);
    }
}
