package tutorial.basic;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BiomeGenHive extends BiomeGenBase
{

	public BiomeGenHive(int id)
	{
		super(id);
		
		this.topBlock = (byte)TribesMod.hive.blockID;
        this.fillerBlock = (byte)TribesMod.hive.blockID;
        biomeList[id] = this;
        this.spawnableCreatureList.removeAll(spawnableCreatureList);
        this.spawnableMonsterList.removeAll(spawnableMonsterList);
        this.spawnableMonsterList.add(new SpawnListEntry(Changeling.class, 1000, 40, 40));
        this.spawnableWaterCreatureList.removeAll(spawnableWaterCreatureList);
        this.spawnableCaveCreatureList.removeAll(spawnableCaveCreatureList);
        this.spawnableCaveCreatureList.add(new SpawnListEntry(Changeling.class, 100, 20, 20));
	}

}
