package tutorial.basic;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeGenColdBreezyPlains extends BiomeGenBase
{
	public BiomeGenColdBreezyPlains(int par1)
    {
        super(par1);
        this.topBlock = (byte)TribesMod.breezyGrass.blockID;
        this.fillerBlock = (byte)TribesMod.breezyDirt.blockID;
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.temperature = 0.1F;
    }
	
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		WorldGenFlowers blueFlowerGenerator = new WorldGenFlowers(TribesMod.blueFlower.blockID);
		
		boolean doGen = TerrainGen.decorate(par1World, par2Random, par3, par4, FLOWERS);
        for (int j = 0; doGen && j < 20; ++j) // change j to < 1
        {
            int k = par3 + par2Random.nextInt(16) + 8;
            int l = par2Random.nextInt(128);
            int i1 = par4 + par2Random.nextInt(16) + 8;
            blueFlowerGenerator.generate(par1World, par2Random, k, l, i1);
        }
	}
}
