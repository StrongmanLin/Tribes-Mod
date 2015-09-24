package tutorial.basic;
 
import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import tutorial.basic.CommonProxy;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.world.biome.BiomeGenBase;
 
@Mod(modid="TribesMod", name="TribesMod", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class TribesMod {
	// add a new block
	
	//public static final ItemArmor helmetThunder = (ItemArmor)(new ItemArmor(429));
	
        // The instance of your mod that Forge uses.
        @Instance("TribesMod")
        public static TribesMod instance;
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="tutorial.basic.ClientProxy", serverSide="tutorial.basic.CommonProxy")
        public static CommonProxy proxy;
        
        public static int idHoney = 241;
        public static Fluid honeyFluid;
        public static Material materialHoney;
        public static Block blockHoney;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event)throws IOException
        {
        	honeyFluid = new Fluid("honeyFluid").setBlockID(idHoney).setViscosity(8000).setDensity(80000);
        	materialHoney = new MaterialLiquid(MapColor.sandColor);
        	FluidRegistry.registerFluid(honeyFluid);
            blockHoney = (new BlockHoney(idHoney)).setUnlocalizedName("honeyFluid").setTextureName("tribesmod:honeyFluid");
        }
        
        // sounds
        public static final StepSound soundStoneFootstep = new StepSound("stone", 1.0F, 1.0F);
        
        // fluids
        
        // blocks
    	public static final Block thunderFurnaceIdle = (new BlockThunderFurnace(423, false).setHardness(40.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("thunderFurnaceIdle").setCreativeTab(CreativeTabs.tabDecorations));
    	public static final Block thunderFurnaceBurning = (new BlockThunderFurnace(424, true).setHardness(40.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("thunderFurnaceBurning").setLightValue(0.9F));
    	public static final Block oreNickel = (new BlockOre(425)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreNickel").setTextureName("tribesmod:oreNickel");
    	public static final Block oreManganese = (new BlockOre(426)).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreManganese").setTextureName("tribesmod:oreManganese");
    	public static final Block oreChromium = (new BlockOre(427)).setHardness(8.0F).setResistance(6.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreChromium").setTextureName("tribesmod:oreChromium");
    	public static final Block oreUnicron = (new BlockOre(439)).setHardness(4.0F).setResistance(8.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreUnicron").setTextureName("tribesmod:oreUnicron");
    	public static final Block hive = (new Block(240, Material.ground)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.3F).setUnlocalizedName("hive").setCreativeTab(CreativeTabs.tabMisc).setTextureName("tribesmod:hive");
    	public static final Block breezyStone = (new BlockBreezyStone(242)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("breezyStone").setTextureName("tribesmod:breezyStone");
    	public static final Block breezyCobblestone = (new Block(243, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("breezyCobblestone").setCreativeTab(CreativeTabs.tabBlock).setTextureName("tribesmod:breezyCobblestone");
    	public static final BlockFlower blueFlower = (BlockBlueFlower)(new BlockBlueFlower(244)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("blueFlower").setCreativeTab(CreativeTabs.tabDecorations).setTextureName("tribesmod:blueFlower");
    	public static final Block blockSugar = (new BlockSand(245)).setHardness(0.5F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("blockSugar").setTextureName("tribesmod:blockSugar");
    	public static final Block frozenHoney = (new BlockFrozenHoney(246)).setHardness(0.5F).setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("frozenHoney").setTextureName("tribesmod:frozenHoney");
    	public static final Block powderedSugar = (new BlockPowderedSugar(247)).setHardness(0.1F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("powderedSugar").setLightOpacity(0).setTextureName("tribesmod:blockSugar");
    	public static final Block breezyLog = (new BreezyLog(248)).setHardness(1.5F).setUnlocalizedName("breezyLog").setCreativeTab(CreativeTabs.tabBlock).setTextureName("tribesmod:breezyLog");
    	public static final BreezyLeaf breezyLeaf = (BreezyLeaf)(new BreezyLeaf(249)).setHardness(1.5F).setUnlocalizedName("breezyLeaf").setCreativeTab(CreativeTabs.tabBlock).setTextureName("tribesmod:breezyLeaf");
    	public static final Block breezySapling = (new BreezySapling(459)).setHardness(1.5F).setUnlocalizedName("breezySapling").setCreativeTab(CreativeTabs.tabDecorations).setTextureName("tribesmod:breezySapling");
    	public static final Block breezyPortalFrame = (new Block(447, Material.rock)).setHardness(2.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("breezyPortalFrame").setCreativeTab(CreativeTabs.tabMisc).setTextureName("tribesmod:breezyPortalFrame");
    	public static final Block breezyPortal = (new BlockBreezyPortal(448)).setUnlocalizedName("breezyPortal").setCreativeTab(CreativeTabs.tabMisc);
    	public static final Block breezyDirt = (new BlockBreezyDirt(250)).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("breezyDirt").setTextureName("dirt");
    	public static final BlockBreezyGrass breezyGrass = (BlockBreezyGrass)(new BlockBreezyGrass(251)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("breezyGrass").setTextureName("tribesmod:breezyGrass");
    	public static final BlockFire greenFire = (BlockFire) (new BlockGreenFire(461)).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("greenFire").setTextureName("tribesmod:greenFire");
    	
    	
    	// materials
    	public static EnumArmorMaterial armorMaterialThunder = EnumHelper.addArmorMaterial("ThunderArmorMaterial", 22, new int[]{2, 7, 6, 2}, 15);
    	public static EnumToolMaterial toolThunder = EnumHelper.addToolMaterial("ThunderToolMaterial", 3, 1000, 15.0F, 4.0F, 15);
    	public static EnumArmorMaterial armorMaterialUnicron = EnumHelper.addArmorMaterial("UnicronArmorMaterial", 44, new int[]{4, 9, 8, 4}, 15);
    	public static EnumToolMaterial toolUnicron = EnumHelper.addToolMaterial("UnicronToolMaterial", 4, 3000, 20.0F, 6.0F, 15);
    	public static EnumArmorMaterial armorMaterialElemental = EnumHelper.addArmorMaterial("ElementalArmormaterial", 100, new int[]{5, 10, 9, 5}, 15);
    	public static EnumToolMaterial toolElemental = EnumHelper.addToolMaterial("ElementalToolMaterial", 5, 10000, 25.0F, 46.0F, 15);
    	
    	// items
    	public static final Item thunderForgedSteel = (new Item(428).setUnlocalizedName("thunderForgedSteel").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("tribesmod:thunderForgedSteel"));
    	public static final Item ingotNickel = (new Item(429).setUnlocalizedName("ingotNickel").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("tribesmod:ingotNickel"));
    	public static final Item ingotManganese = (new Item(430).setUnlocalizedName("ingotManganese").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("tribesmod:ingotManganese"));
    	public static final Item ingotChromium = (new Item(431).setUnlocalizedName("ingotChromium").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("tribesmod:ingotChromium"));
    	public static final Item helmetThunder = (new ItemThunderArmor(432, armorMaterialThunder, 0, 0, "thunder")).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("helmetThunder").setTextureName("tribesmod:helmetThunder");
    	public static final Item chestThunder = (new ItemThunderArmor(433, armorMaterialThunder, 0, 1, "thunder")).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("chestThunder").setTextureName("tribesmod:chestThunder");
    	public static final Item legsThunder = (new ItemThunderArmor(434, armorMaterialThunder, 0, 2, "thunder")).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("legsThunder").setTextureName("tribesmod:legsThunder");
    	public static final Item bootsThunder = (new ItemThunderArmor(435, armorMaterialThunder, 0, 3, "thunder")).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("bootsThunder").setTextureName("tribesmod:bootsThunder");
    	public static final Item swordThunder = (new ItemSword(436, toolThunder).setUnlocalizedName("swordThunder").setCreativeTab(CreativeTabs.tabCombat).setTextureName("tribesmod:swordThunder"));
    	public static final Item ingotUnicron = (new Item(437).setUnlocalizedName("ingotUnicron").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("tribesmod:ingotUnicron"));
    	public static final Item crystalHeart = (new Item(438).setUnlocalizedName("crystalHeart").setCreativeTab(CreativeTabs.tabMisc).setTextureName("tribesmod:crystalHeart"));
    	public static final Item thunderPickaxe = (new ItemPickaxe(440, toolThunder)).setUnlocalizedName("thunderPickaxe").setCreativeTab(CreativeTabs.tabTools).setTextureName("tribesmod:thunderPickaxe");
    	public static final Item helmetUnicron = (new ItemUnicronArmor(441, armorMaterialUnicron, 0, 0, "unicron")).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("helmetUnicron").setTextureName("tribesmod:helmetUnicron");
    	public static final Item chestUnicron = (new ItemUnicronArmor(442, armorMaterialUnicron, 0, 1, "unicron")).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("chestUnicron").setTextureName("tribesmod:chestUnicron");
    	public static final Item legsUnicron = (new ItemUnicronArmor(443, armorMaterialUnicron, 0, 2, "unicron")).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("legsUnicron").setTextureName("tribesmod:legsUnicron");
    	public static final Item bootsUnicron = (new ItemUnicronArmor(444, armorMaterialUnicron, 0, 3, "unicron")).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("bootsUnicron").setTextureName("tribesmod:bootsUnicron");
    	public static final Item unicronPickaxe = (new ItemPickaxe(445, toolUnicron)).setUnlocalizedName("unicronPickaxe").setCreativeTab(CreativeTabs.tabTools).setTextureName("tribesmod:unicronPickaxe");
    	public static final Item unicronSword = (new ItemSword(463, toolUnicron)).setUnlocalizedName("unicronSword").setCreativeTab(CreativeTabs.tabCombat).setTextureName("tribesmod:unicronSword");
    	public static final Item arcaneRod = (new ItemWand(446)).setUnlocalizedName("arcaneRod").setCreativeTab(CreativeTabs.tabMisc).setTextureName("tribesmod:arcaneWand");
    	public static final Item breezyPollen = (new Item(453)).setUnlocalizedName("breezyPollen").setCreativeTab(CreativeTabs.tabMisc).setTextureName("tribesmod:breezyPollen");
    	public static final Item breezyPowder = (new BreezyPowder(460)).setUnlocalizedName("breezyPowder").setCreativeTab(CreativeTabs.tabMisc).setTextureName("tribesmod:breezyPowder"); // the stuff to light the portal
    	public static final Item ingotPearthony = (new Item(462)).setUnlocalizedName("ingotPearthony").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("tribesmod:ingotPearthony");
    	public static final Item helmetElemental = (new ItemElementalArmor(464, armorMaterialElemental, 0, 0, "elemental")).setUnlocalizedName("helmetElemental").setTextureName("tribesmod:helmetElemental");
    	public static final Item chestElemental = (new ItemElementalArmor(465, armorMaterialElemental, 0, 1, "elemental")).setUnlocalizedName("chestElemental").setTextureName("tribesmod:chestElemental");
    	public static final Item legsElemental = (new ItemElementalArmor(466, armorMaterialElemental, 0, 2, "elemental")).setUnlocalizedName("legsElemental").setTextureName("tribesmod:legsElemental");
    	public static final Item bootsElemental = (new ItemElementalArmor(467, armorMaterialElemental, 0, 3, "elemental")).setUnlocalizedName("bootsElemental").setTextureName("tribesmod:bootsElemental");
    	public static final Item elementalSword = (new ItemSword(468, toolElemental)).setUnlocalizedName("elementalSword").setCreativeTab(CreativeTabs.tabCombat).setTextureName("tribesMod:elementalSword");
    	public static final Item elementsOfHarm = (new Item(469)).setUnlocalizedName("elementsOfHarm").setCreativeTab(CreativeTabs.tabMisc).setTextureName("tribesmod:elementsOfHarm");
    	public static final Item spawnTirek = (new ItemSpawnTirek(470)).setUnlocalizedName("spawnTirek").setCreativeTab(CreativeTabs.tabMisc).setTextureName("tribesmod:spawnTirek");
    	// public static Item bucketHoney = (new ItemHoneyBucket(453, 241)).setUnlocalizedName("bucketHoney").setMaxStackSize(16).setContainerItem(Item.bucketEmpty);
    	
    	// gui stuff
    	public static final int guiIDThunderFurnace = 0;
    	
    	// dimension stuff
    	public static final int breezyDimensionID = 2;
    	
    	// biome stuff
    	public static BiomeGenBase biomeHive;
    	public static BiomeGenBase biomeBreezyPlains;
    	public static BiomeGenBase biomeBreezyForest;
    	public static BiomeGenBase biomeColdBreezyPlains;
    	
    	EventManager eventManager = new EventManager();
        
        @EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                
                biomeHive = new BiomeGenHive(23).setBiomeName("Changeling Hive").setMinMaxHeight(1.5F, 2F).setDisableRain();
                biomeBreezyPlains = new BiomeGenBreezyPlains(24).setColor(9286496).setBiomeName("Breezy Plains");
                biomeColdBreezyPlains = new BiomeGenColdBreezyPlains(25).setColor(9286496).setBiomeName("Breezy Plains");
                biomeBreezyForest = new BiomeGenBreezyForest(26).setColor(9286496).setBiomeName("Breezy Forest");
                
                // register items
                GameRegistry.registerItem(thunderForgedSteel, "thunderForgedSteel");
                LanguageRegistry.addName(thunderForgedSteel, "Thunder Forged Steel");
                GameRegistry.registerItem(ingotNickel, "ingotNickel");
                LanguageRegistry.addName(ingotNickel, "Nickel Ingot");
                GameRegistry.registerItem(ingotManganese, "ingotManganese");
                LanguageRegistry.addName(ingotManganese, "Manganese Ingot");
                GameRegistry.registerItem(ingotChromium, "ingotChromium");
                LanguageRegistry.addName(ingotChromium, "Chromium Ingot");
                GameRegistry.registerItem(helmetThunder, "helmetThunder");
                LanguageRegistry.addName(helmetThunder, "Volagasus Helmet");
                GameRegistry.registerItem(chestThunder, "chestThunder");
                LanguageRegistry.addName(chestThunder, "Volagasus Chest");
                GameRegistry.registerItem(legsThunder, "legsThunder");
                LanguageRegistry.addName(legsThunder, "Volagasus Legs");
                GameRegistry.registerItem(bootsThunder, "bootsThunder");
                LanguageRegistry.addName(bootsThunder, "Volagasus Boots");
                GameRegistry.registerItem(swordThunder, "swordThunder");
                LanguageRegistry.addName(swordThunder, "Volagasus Sword");
                GameRegistry.registerItem(ingotUnicron, "ingotUnicron");
                LanguageRegistry.addName(ingotUnicron, "Unicron Ingot");
                GameRegistry.registerItem(crystalHeart, "crystalHeart");
                LanguageRegistry.addName(crystalHeart, "Crystal Heart");
                GameRegistry.registerItem(thunderPickaxe, "thunderPickaxe");
                LanguageRegistry.addName(thunderPickaxe, "Volagasus Pickaxe");
                GameRegistry.registerItem(helmetUnicron, "helmetUnicron");
                LanguageRegistry.addName(helmetUnicron, "Unicron Helmet");
                GameRegistry.registerItem(chestUnicron, "chestUnicron");
                LanguageRegistry.addName(chestUnicron, "Unicron Chest");
                GameRegistry.registerItem(legsUnicron, "legsUnicron");
                LanguageRegistry.addName(legsUnicron, "Unicron Legs");
                GameRegistry.registerItem(bootsUnicron, "bootsUnicron");
                LanguageRegistry.addName(bootsUnicron, "Unicron Boots");
                GameRegistry.registerItem(unicronPickaxe, "unicronPickaxe");
                LanguageRegistry.addName(unicronPickaxe, "Unicron Pickaxe");
                GameRegistry.registerItem(arcaneRod, "arcaneRod");
                LanguageRegistry.addName(arcaneRod, "Transmogrification Wand");
                GameRegistry.registerItem(breezyPollen, "breezyPollen");
                LanguageRegistry.addName(breezyPollen, "Breezy Pollen");
                GameRegistry.registerItem(breezyPowder, "breezyPowder");
                LanguageRegistry.addName(breezyPowder, "Breezy Powder");
                GameRegistry.registerItem(unicronSword, "unicronSword");
                LanguageRegistry.addName(unicronSword, "Unicron Sword");
                GameRegistry.registerItem(ingotPearthony, "ingotPearthony");
                LanguageRegistry.addName(ingotPearthony, "Pearthony Ingot");
                GameRegistry.registerItem(helmetElemental, "helmetElemental");
                LanguageRegistry.addName(helmetElemental, "Elemental Helmet");
                GameRegistry.registerItem(chestElemental, "chestElemental");
                LanguageRegistry.addName(chestElemental, "Elemental Chest");
                GameRegistry.registerItem(legsElemental, "legsElemental");
                LanguageRegistry.addName(legsElemental, "Elemental Leggings");
                GameRegistry.registerItem(bootsElemental, "bootsElemental");
                LanguageRegistry.addName(bootsElemental, "Elemental Boots");
                GameRegistry.registerItem(elementalSword, "elementalSword");
                LanguageRegistry.addName(elementalSword, "Elemental Sword");
                GameRegistry.registerItem(elementsOfHarm, "elementsOfharm");
                LanguageRegistry.addName(elementsOfHarm, "Elements Of Harm");
                GameRegistry.registerItem(spawnTirek, "spawnTirek");
                LanguageRegistry.addName(spawnTirek, "Summon The Dark Lord Tirek");
                
                // register blocks
                GameRegistry.registerBlock(thunderFurnaceIdle, "thunderFurnaceIdle");
                LanguageRegistry.addName(thunderFurnaceIdle, "Thunder Furnace");
                MinecraftForge.setBlockHarvestLevel(thunderFurnaceIdle, "pickaxe", 3);
                GameRegistry.registerBlock(thunderFurnaceBurning, "thunderFurnaceBurning");
                LanguageRegistry.addName(thunderFurnaceBurning, "Burning Thunder Furnace");
                MinecraftForge.setBlockHarvestLevel(thunderFurnaceBurning, "pickaxe", 3);
                GameRegistry.registerBlock(oreNickel, "oreNickel");
                LanguageRegistry.addName(oreNickel, "Nickel Ore");
                MinecraftForge.setBlockHarvestLevel(oreNickel, "pickaxe", 2);
                GameRegistry.registerBlock(oreManganese, "oreManganese");
                LanguageRegistry.addName(oreManganese, "Manganese Ore");
                MinecraftForge.setBlockHarvestLevel(oreManganese, "pickaxe", 2);
                GameRegistry.registerBlock(oreChromium, "oreChromium");
                LanguageRegistry.addName(oreChromium, "Chromium Ore");
                MinecraftForge.setBlockHarvestLevel(oreChromium, "pickaxe", 2);
                GameRegistry.registerBlock(oreUnicron, "oreUnicron");
                LanguageRegistry.addName(new ItemStack(oreUnicron, 1, 1), "Unicron Ore");
                MinecraftForge.setBlockHarvestLevel(oreUnicron, "pickaxe", 3);
                GameRegistry.registerBlock(hive, "hive");
                LanguageRegistry.addName(hive, "Hive");
                GameRegistry.registerBlock(breezyPortalFrame, "breezyPortalFrame");
                LanguageRegistry.addName(breezyPortalFrame, "Breezy Portal Frame");
                GameRegistry.registerBlock(breezyPortal, "breezyPortal");
                LanguageRegistry.addName(breezyPortal, "Breezy Portal");
                GameRegistry.registerBlock(blueFlower, "blueFlower");
                LanguageRegistry.addName(blueFlower, "Blue Flower");
                GameRegistry.registerBlock(breezyCobblestone, "breezyCobblestone");
                LanguageRegistry.addName(breezyCobblestone, "Breezy Cobblestone");
                GameRegistry.registerBlock(breezyStone, "breezyStone");
                LanguageRegistry.addName(breezyStone, "Breezy Stone");
                GameRegistry.registerBlock(blockSugar, "blockSugar");
                LanguageRegistry.addName(blockSugar, "Sugar Block");
                GameRegistry.registerBlock(frozenHoney, "frozenHoney");
                LanguageRegistry.addName(frozenHoney, "Frozen Honey");
                GameRegistry.registerBlock(breezyLog, "breezyLog");
                LanguageRegistry.addName(breezyLog, "Breezy Log");
                GameRegistry.registerBlock(breezyLeaf, "breazyLeaf");
                LanguageRegistry.addName(breezyLeaf, "Breezy Leaf");
                GameRegistry.registerBlock(breezySapling, "breezySapling");
                LanguageRegistry.addName(breezySapling, "Breezy Sapling");
                GameRegistry.registerBlock(breezyDirt, "breezyDirt");
                LanguageRegistry.addName(breezyDirt, "Breezy Dirt");
                GameRegistry.registerBlock(breezyGrass, "breezyGrass");
                LanguageRegistry.addName(breezyGrass, "Breezy Grass");
                
                // register fluids
                GameRegistry.registerBlock(blockHoney, "honey");
                LanguageRegistry.addName(blockHoney, "Honey");
                
                // register mobs
                EntityRegistry.registerGlobalEntityID(Changeling.class, "Changeling", 2, 0x000000, 0x66ffff);
                EntityRegistry.registerModEntity(Changeling.class, "Changeling", 2, this, 80, 1, true);
                EntityRegistry.addSpawn(Changeling.class, 20, 12, 12, EnumCreatureType.monster, biomeHive);
                proxy.registerRenderers();
                LanguageRegistry.instance().addStringLocalization("entity.Changeling.name", "en_US", "Changeling");
                EntityRegistry.registerGlobalEntityID(Breezy.class, "Breezy", 1, 0x0eb7eb, 0x333333);
                EntityRegistry.registerModEntity(Breezy.class, "Breezy", 1, this, 80, 1, true);
                EntityRegistry.addSpawn(Breezy.class, 200, 10, 12, EnumCreatureType.creature, biomeBreezyForest);
                LanguageRegistry.instance().addStringLocalization("entity.Breezy.name", "en_US", "Breezy");
                proxy.registerRenderers();
                EntityRegistry.registerGlobalEntityID(Tirek.class, "The Dark Lord Tirek", 0, 0xcc3c46, 0x262a31);
                EntityRegistry.registerModEntity(Tirek.class, "The Dark Lord Tirek", 0, this, 80, 1, true);
                proxy.registerRenderers();
                LanguageRegistry.instance().addStringLocalization("entity.The Dark Lord Tirek.name", "en_US", "The Dark Lord Tirek");
                
                // register smelting
                GameRegistry.addSmelting(oreNickel.blockID, new ItemStack(ingotNickel), 0.7F);
                GameRegistry.addSmelting(oreManganese.blockID, new ItemStack(ingotManganese), 0.7F);
                GameRegistry.addSmelting(oreChromium.blockID, new ItemStack(ingotChromium), 0.7F);
                GameRegistry.addSmelting(oreUnicron.blockID, new ItemStack(ingotUnicron), 0.7F);
                
                // register recipes
                GameRegistry.addRecipe(new ItemStack(thunderFurnaceIdle), "xyx", "aba", "xyx", 'x', Item.gunpowder, 'y', Item.bucketLava, 'a', Item.magmaCream, 'b', Block.furnaceIdle);
                ItemStack thunderHelmet = new ItemStack(helmetThunder);
                thunderHelmet.addEnchantment(Enchantment.blastProtection, 3);
                thunderHelmet.addEnchantment(Enchantment.fireProtection, 3);
                ItemStack thunderChest = new ItemStack(chestThunder);
                thunderChest.addEnchantment(Enchantment.blastProtection, 3);
                thunderChest.addEnchantment(Enchantment.fireProtection, 3);
                ItemStack thunderLegs = new ItemStack(legsThunder);
                thunderLegs.addEnchantment(Enchantment.blastProtection, 3);
                thunderLegs.addEnchantment(Enchantment.fireProtection, 3);
                ItemStack thunderBoots = new ItemStack(bootsThunder);
                thunderBoots.addEnchantment(Enchantment.blastProtection, 3);
                thunderBoots.addEnchantment(Enchantment.fireProtection, 3);
                GameRegistry.addRecipe(thunderHelmet, "xxx", "x x", 'x', thunderForgedSteel);
                GameRegistry.addRecipe(thunderChest, "x x", "xxx", "xxx", 'x', thunderForgedSteel);
                GameRegistry.addRecipe(thunderLegs, "xxx", "x x", "x x", 'x', thunderForgedSteel);
                GameRegistry.addRecipe(thunderBoots, "x x", "x x", 'x', thunderForgedSteel);
                GameRegistry.addRecipe(new ItemStack(swordThunder), "x", "x", "y", 'x', thunderForgedSteel, 'y', Item.stick);
                GameRegistry.addRecipe(new ItemStack(thunderPickaxe), "xxx", " y ", " y ", 'x', thunderForgedSteel, 'y', Item.stick);
                GameRegistry.addRecipe(new ItemStack(helmetUnicron), "xxx", "xyx", 'x', ingotUnicron, 'y', crystalHeart);
                GameRegistry.addRecipe(new ItemStack(chestUnicron), "xyx", "xxx", "xxx", 'x', ingotUnicron, 'y', crystalHeart);
                GameRegistry.addRecipe(new ItemStack(legsUnicron), "xxx", "xyx", "x x", 'x', ingotUnicron, 'y', crystalHeart);
                GameRegistry.addRecipe(new ItemStack(bootsUnicron), "xyx", "x x", 'x', ingotUnicron, 'y', crystalHeart);
                ItemStack blueFlowerStack = new ItemStack(blueFlower);
                GameRegistry.addShapelessRecipe(new ItemStack(breezyPollen), blueFlowerStack, blueFlowerStack, blueFlowerStack, blueFlowerStack);
                GameRegistry.addShapelessRecipe(new ItemStack(Item.sugar, 9), new ItemStack(blockSugar));
                GameRegistry.addRecipe(new ItemStack(breezyPowder), "xxx", "xxx", "xxx", 'x', breezyPollen);
                GameRegistry.addRecipe(new ItemStack(unicronSword), "x", "x", "y", 'x', ingotUnicron, 'y', Item.stick);
                ItemStack elementalHelmet = new ItemStack(helmetElemental);
                elementalHelmet.addEnchantment(Enchantment.blastProtection, 3);
                elementalHelmet.addEnchantment(Enchantment.fireProtection, 3);
                elementalHelmet.addEnchantment(Enchantment.protection, 3);
                elementalHelmet.addEnchantment(Enchantment.unbreaking, 10);
                ItemStack elementalChest = new ItemStack(chestElemental);
                elementalChest.addEnchantment(Enchantment.blastProtection, 3);
                elementalChest.addEnchantment(Enchantment.fireProtection, 3);
                elementalChest.addEnchantment(Enchantment.protection, 3);
                elementalChest.addEnchantment(Enchantment.unbreaking, 10);
                ItemStack elementalLegs = new ItemStack(legsElemental);
                elementalLegs.addEnchantment(Enchantment.blastProtection, 3);
                elementalLegs.addEnchantment(Enchantment.fireProtection, 3);
                elementalLegs.addEnchantment(Enchantment.protection, 10);
                elementalLegs.addEnchantment(Enchantment.unbreaking, 10);
                ItemStack elementalBoots = new ItemStack(bootsElemental);
                elementalBoots.addEnchantment(Enchantment.blastProtection, 3);
                elementalBoots.addEnchantment(Enchantment.fireProtection, 3);
                elementalBoots.addEnchantment(Enchantment.protection, 3);
                elementalBoots.addEnchantment(Enchantment.unbreaking, 10);
                GameRegistry.addRecipe(elementalHelmet, "yxz", "x x", 'x', ingotPearthony, 'y', thunderHelmet, 'z', helmetUnicron);
                GameRegistry.addRecipe(elementalChest, "x x", "xyx", "xzx", 'x', ingotPearthony, 'y', thunderChest, 'z', chestUnicron);
                GameRegistry.addRecipe(elementalLegs, "xyx", "xzx", "x x", 'x', ingotPearthony, 'y', thunderLegs, 'z', legsUnicron);
                GameRegistry.addRecipe(elementalBoots, "y z", "x x", 'x', ingotPearthony, 'y', thunderBoots, 'z', bootsUnicron);
                ItemStack swordElemental = new ItemStack(elementalSword);
                swordElemental.addEnchantment(Enchantment.looting, 10);
                swordElemental.addEnchantment(Enchantment.sharpness, 10);
                swordElemental.addEnchantment(Enchantment.unbreaking, 10);
                swordElemental.addEnchantment(Enchantment.knockback, 1);
                GameRegistry.addRecipe(swordElemental, "x", "y", "z", 'x', swordThunder, 'y', unicronSword, 'z', ingotPearthony);
                GameRegistry.addRecipe(new ItemStack(arcaneRod), "  x", " y ", "y  ", 'x', Item.stick, 'y', ingotUnicron);
                GameRegistry.addRecipe(new ItemStack(spawnTirek), "aba", "cde", "afa", 'a', Block.obsidian, 'b', thunderForgedSteel, 'c', breezyPollen, 'd', crystalHeart, 'e', ingotPearthony, 'f', arcaneRod);
                
                // register tile entities
                GameRegistry.registerTileEntity(TileEntityThunderFurnace.class, "tileEntityThunderFurnace");
                
                // other registers
                GuiHandler guiHandler = new GuiHandler();
                GameRegistry.addBiome(biomeHive);
                GameRegistry.addBiome(biomeBreezyPlains);
                GameRegistry.addBiome(biomeColdBreezyPlains);
                DimensionManager.registerProviderType(breezyDimensionID, WorldProviderBreezy.class, false);
                DimensionManager.registerDimension(breezyDimensionID, breezyDimensionID);
                
                LanguageRegistry.instance().addStringLocalization("container.thunderFurnace", "Thunder Furnace");
                
                GameRegistry.registerWorldGenerator(eventManager);
        }
       
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
        
        public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor)
        {
            int id = EntityRegistry.findGlobalUniqueEntityId();

            EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
            EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bkEggColor, fgEggColor));
        }
     
        public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes)
        {
            if (spawnProb > 0)
            {
                    EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
            }
        }
}