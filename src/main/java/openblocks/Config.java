package openblocks;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import openblocks.OpenBlocks.Enchantments;
import openblocks.asm.EntityPlayerVisitor;
import openblocks.client.radio.RadioManager;
import openblocks.client.radio.RadioManager.RadioStation;
import openblocks.common.Stencil;
import openblocks.common.TrophyHandler;
import openblocks.common.item.*;
import openblocks.common.recipe.*;
import openblocks.enchantments.*;
import openmods.config.*;
import openmods.utils.ColorUtils;
import openmods.utils.ColorUtils.ColorMeta;

public class Config {

	public static final FeatureManager FEATURES = new FeatureManager();

	@OnLineModifiable
	@ConfigProperty(category = "dropblock", name = "searchDistance", comment = "The range of the drop block")
	public static int elevatorTravelDistance = 20;

	@OnLineModifiable
	@ConfigProperty(category = "dropblock", name = "ignoreHalfBlocks", comment = "The elevator will ignore half blocks when counting the blocks it can pass through")
	public static boolean elevatorIgnoreHalfBlocks = false;

	@OnLineModifiable
	@ConfigProperty(category = "dropblock", name = "maxPassThrough", comment = "The maximum amount of blocks the elevator can pass through before the teleport fails. -1 disables this")
	public static int elevatorMaxBlockPassCount = 4;

	@OnLineModifiable
	@ConfigProperty(category = "dropblock", name = "specialBlockRules", comment = "Defines blocks that are handled specially by elevators. Entries are in form <modId>:<blockName>:<action> or id:<blockId>:<action>. Possible actions: abort (elevator can't pass block), increment (counts for elevatorMaxBlockPassCount limit) and ignore")
	public static String[] elevatorRules = new String[0];

	@OnLineModifiable
	@ConfigProperty(category = "dropblock", name = "elevatorDrainsXP", comment = "Does the elevator drain player XP when used?")
	public static boolean elevatorDrainsXP = true;

	@ConfigProperty(category = "tanks", name = "bucketsPerTank", comment = "The amount of buckets each tank can hold")
	public static int bucketsPerTank = 16;

	@OnLineModifiable
	@ConfigProperty(category = "tanks", name = "tankTicks", comment = "Should tanks try to balance liquid amounts with neighbours")
	public static boolean shouldTanksUpdate = true;

	@ConfigProperty(category = "hacks", name = "tryHookPlayerRenderer", comment = "Allow OpenBlocks to hook the player renderer to apply special effects")
	public static boolean tryHookPlayerRenderer = true;

	@OnLineModifiable
	@ConfigProperty(category = "trophy", name = "trophyDropChance", comment = "The chance (from 0 to 1) of a trophy drop. for example, 0.001 for 1/1000")
	public static double trophyDropChance = 0.001;

	@OnLineModifiable
	@ConfigProperty(category = "dropblock", name = "irregularBlocksArePassable", comment = "The elevator will try to pass through blocks that have custom collision boxes")
	public static boolean irregularBlocksArePassable = true;

	@OnLineModifiable
	@ConfigProperty(category = "tanks", name = "emitLight", comment = "Tanks will emit light when they contain a liquid that glows (eg. lava)")
	public static boolean tanksEmitLight = true;

	@OnLineModifiable
	@ConfigProperty(category = "sprinkler", name = "fertilizeChance", comment = "1/chance that crops will be fertilized without bonemeal")
	public static int sprinklerFertilizeChance = 500;

	@OnLineModifiable
	@ConfigProperty(category = "sprinkler", name = "bonemealFertilizeChance", comment = "1/chance that crops will be fertilized with bonemeal")
	public static int sprinklerBonemealFertizizeChance = 200;

	@OnLineModifiable
	@ConfigProperty(category = "sprinkler", name = "effectiveRange", comment = "The range in each cardinal direction that crops will be affected.")
	public static int sprinklerEffectiveRange = 4;

	@OnLineModifiable
	@ConfigProperty(category = "glasses", name = "opacity", comment = "0.0 - no visible change to world, 1.0 - world fully obscured")
	public static double sonicGlassesOpacity = 0.95;

	@OnLineModifiable
	@ConfigProperty(category = "glasses", name = "useTexture", comment = "Use texture for obscuring world")
	public static boolean sonicGlassesUseTexture = true;

	@OnLineModifiable
	@ConfigProperty(category = "imaginary", name = "fadingSpeed", comment = "Speed of imaginary blocks fading/appearing")
	public static float imaginaryFadingSpeed = 0.0075f;

	@ConfigProperty(category = "imaginary", name = "numberOfUses", comment = "Number of newly created crayon/pencil uses")
	public static float imaginaryItemUseCount = 10;

	@ConfigProperty(category = "crane", name = "doCraneCollisionCheck", comment = "Enable collision checking of crane arm")
	public static boolean doCraneCollisionCheck = false;

	@OnLineModifiable
	@ConfigProperty(category = "crane", name = "boringMode", comment = "Use shift to control crane direction (otherwise, toggle every time)")
	public static boolean craneShiftControl = true;

	@ConfigProperty(category = "crane", name = "turtleMagnetRange", comment = "Range of magnet CC peripheral")
	public static double turtleMagnetRange = 4;

	@ConfigProperty(category = "crane", name = "addTurtles", comment = "Enable magnet turtles")
	public static boolean enableCraneTurtles = true;

	@ConfigProperty(category = "crane", name = "showTurtles", comment = "Show magnet turtles in creative list")
	public static boolean showCraneTurtles = true;

	@ConfigProperty(category = "hacks", name = "enableExperimentalFeatures", comment = "Enable experimental features that may be buggy or broken entirely")
	public static boolean experimentalFeatures = false;

	@ConfigProperty(category = "tomfoolery", name = "weAreSeriousPeople", comment = "Are you serious too?")
	public static boolean soSerious = true;

	@OnLineModifiable
	@ConfigProperty(category = "tomfoolery", name = "doItWhileTyping", comment = "You know, THAT thing! That you shouldn't do in public!")
	public static boolean fartTypying = false;

	@OnLineModifiable
	@ConfigProperty(category = "tomfoolery", name = "flimFlamBlacklist", comment = "Blacklist for effects used by flim-flam enchantment")
	public static String[] flimFlamBlacklist = new String[0];

	@OnLineModifiable
	@ConfigProperty(category = "tomfoolery", name = "safeOnly", comment = "Allow only flimflams that don't cause death (or at least very rarely)")
	public static boolean safeFlimFlams = false;

	@OnLineModifiable
	@ConfigProperty(category = "debug", name = "goldenEyeDebug", comment = "Show structures found by golden eye")
	public static boolean eyeDebug = false;

	@OnLineModifiable
	@ConfigProperty(category = "debug", name = "enableChangelogBooks", comment = "Enable the changelog books")
	public static boolean enableChangelogBooks = true;

	@OnLineModifiable
	@ConfigProperty(category = "debug", name = "gravesDebug", comment = "Dump extra amount of data, every time grave is created")
	public static boolean debugGraves = false;

	@ConfigProperty(category = "features", name = "explosiveEnchantmentId", comment = "Id of explosive enchantment")
	public static int explosiveEnchantmentId = 211;

	@ConfigProperty(category = "features", name = "lastStandEnchantmentId", comment = "Id of last stand enchantment")
	public static int lastStandEnchantmentId = 212;

	@ConfigProperty(category = "features", name = "flimFlamEnchantmentId", comment = "Id of flim flam enchantment")
	public static int flimFlamEnchantmentId = 213;

	@ConfigProperty(category = "features", name = "explosiveEnchantGrief", comment = "Explosive enchant can break blocks at level 3")
	public static boolean explosiveEnchantGrief = true;

	@ConfigProperty(category = "cursor", name = "cursorMaxDamage", comment = "Amount of damage a cursor can take")
	public static int cursorMaxDamage = 128;

	@OnLineModifiable
	@ConfigProperty(category = "additional", name = "disableMobNames", comment = "List any mob names you want disabled on the server")
	public static String[] disableMobNames = new String[0];

	@OnLineModifiable
	@ConfigProperty(category = "additional", name = "dumpDeadPlayersInventories", comment = "Should player inventories be stored after death (can later be restored with command)")
	public static boolean dumpStiffsStuff = true;

	@OnLineModifiable
	@ConfigProperty(category = "radio", name = "radioStations", comment = "List any radio stations you want")
	public static String[] radioStations = new String[] {
			"http://idobiradio.idobi.com;idobi Radio (idobi.com);Blue",
			"http://192.184.9.79:8006;CINEMIX;Blue",
			"http://radiorivendell.de:80/;Radio Rivendell;Blue",
			"http://205.164.62.15:10052;1.fm Love Classics;Blue",
			"http://theradio.cc:8000/trcc-stream.mp3;TheRadioCC;Red",
			"http://streaming202.radionomy.com:80/abacusfm-vintage-jazz;Vintage Jazz;Red",
			"http://s1.stream-experts.net:8032/;Nature sounds;Red",
			"http://91.121.166.38:7016/;British Comedy;Red",
			"http://50.7.173.162:8010;Audiophile Baroque;Red"
	};

	@ConfigProperty(category = "radio", name = "replaceList", comment = "List of URLs that need to be updated (url, whitespace, replacement")
	public static String[] derpList = new String[] {
			"http://69.46.75.101:80 http://idobiradio.idobi.com"
	};

	@OnLineModifiable
	@ConfigProperty(category = "cartographer", name = "blockBlacklist", comment = "List of blocks that should be invisible to cartographer. Example: id:3,  OpenBlocks:openblocks_radio (case sensitive)")
	public static String[] mapBlacklist = new String[] {
			"Natura:Cloud"
	};

	@ConfigProperty(category = "radio", name = "enableChestLoot", comment = "Add tuned crystals as loot in chests")
	public static boolean radioChests = true;

	@ConfigProperty(category = "radio", name = "radioVillagerId", comment = "Tuned crystals merchant id (-1 to disable)")
	public static int radioVillagerId = 6156;

	@OnLineModifiable
	@ConfigProperty(category = "radio", name = "radioVillagerSellsRecords", comment = "Should tuned crystals sell records too")
	public static boolean radioVillagerRecords = true;

	@OnLineModifiable
	@ConfigProperty(category = "radio", name = "maxSources", comment = "Maximum number of sources playing at one time")
	public static int maxRadioSources = 3;

	@OnLineModifiable
	@ConfigProperty(category = "fan", name = "fanForce", comment = "Maximum force applied every tick to entities nearby (linear decay)")
	public static double fanForce = 0.05;

	@OnLineModifiable
	@ConfigProperty(category = "fan", name = "fanRange", comment = "Range of fan in blocks")
	public static double fanRange = 10;

	@OnLineModifiable
	@ConfigProperty(category = "fan", name = "isRedstoneActivated", comment = "Is fan force controlled by redstone current")
	public static boolean redstoneActivatedFan = true;

	@ConfigProperty(category = "sponge", name = "spongeStickUseCount", comment = "SpongeOnAStick use count")
	public static int spongeMaxDamage = 256;

	@OnLineModifiable
	@ConfigProperty(category = "sponge", name = "spongeRange", comment = "Sponge block range (distance from center)")
	public static int spongeRange = 3;

	@OnLineModifiable
	@ConfigProperty(category = "sponge", name = "spongeRange", comment = "Sponge block range (distance from center)")
	public static int spongeStickRange = 3;

	public static void processFeatures(Configuration config) {
		FEATURES.collectFromBlocks(OpenBlocks.Blocks.class);
		FEATURES.collectFromItems(OpenBlocks.Items.class);
		FEATURES.loadFromConfiguration(config);
	}

	public static void register() {
		final String modId = "openblocks";

		final FactoryRegistry<Item> itemFactory = new FactoryRegistry<Item>();
		final FactoryRegistry<Block> blockFactory = new FactoryRegistry<Block>();

		@SuppressWarnings("unchecked")
		final List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

		itemFactory.registerFactory("pencilGlasses", new FactoryRegistry.Factory<Item>() {
			@Override
			public Item construct() {
				return new ItemImaginationGlasses(ItemImaginationGlasses.Type.PENCIL);
			}
		});

		itemFactory.registerFactory("technicolorGlasses", new FactoryRegistry.Factory<Item>() {
			@Override
			public Item construct() {
				return new ItemImaginationGlasses(ItemImaginationGlasses.Type.TECHNICOLOR);
			}
		});

		itemFactory.registerFactory("seriousGlasses", new FactoryRegistry.Factory<Item>() {
			@Override
			public Item construct() {
				return new ItemImaginationGlasses(ItemImaginationGlasses.Type.BASTARD);
			}
		});

		ConfigProcessing.registerItems(OpenBlocks.Items.class, modId, FEATURES, itemFactory);
		ConfigProcessing.registerBlocks(OpenBlocks.Blocks.class, modId, FEATURES, blockFactory);

		// There is no fail checking here because if the Generic item fails,
		// then I doubt anyone wants this to be silent.
		// Too many items would suffer from this. - NC
		MetasGeneric.registerItems();
		OpenBlocks.Items.generic.initRecipes();

		MetasGenericUnstackable.registerItems();
		OpenBlocks.Items.genericUnstackable.initRecipes();

		if (OpenBlocks.Blocks.ladder != null) {
			recipeList.add(new ShapelessOreRecipe(OpenBlocks.Blocks.ladder, Blocks.ladder, Blocks.trapdoor));
		}

		if (OpenBlocks.Blocks.guide != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.guide, "ggg", "gtg", "ggg", 'g', Blocks.glass, 't', Blocks.torch));
		}

		if (OpenBlocks.Blocks.elevator != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.elevator, "www", "wew", "www", 'w', Blocks.wool, 'e', Items.ender_pearl));
		}

		if (OpenBlocks.Blocks.target != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.target, "www", "www", "s s", 'w', Blocks.wool, 's', "stickWood"));
		}

		if (OpenBlocks.Blocks.flag != null) {
			recipeList.add(new ShapedOreRecipe(new ItemStack(OpenBlocks.Blocks.flag, 3), "scc", "sc ", "s  ", 'c', Blocks.carpet, 's', "stickWood"));
		}
		if (OpenBlocks.Blocks.tank != null) {
			recipeList.add(new ShapedOreRecipe(new ItemStack(OpenBlocks.Blocks.tank, 2), "ogo", "ggg", "ogo", 'g', Blocks.glass_pane, 'o', Blocks.obsidian));
		}
		if (OpenBlocks.Blocks.trophy != null) {
			MinecraftForge.EVENT_BUS.register(new TrophyHandler());
		}
		if (OpenBlocks.Blocks.bearTrap != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.bearTrap, "fif", "fif", "fif", 'f', Blocks.iron_bars, 'i', Items.iron_ingot));
		}

		if (OpenBlocks.Blocks.sprinkler != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.sprinkler, "ifi", "iri", "ifi", 'i', Items.iron_ingot, 'r', Blocks.redstone_torch, 'f', Blocks.iron_bars));
		}

		if (OpenBlocks.Blocks.cannon != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.cannon, " d ", " f ", "iri", 'd', Blocks.dispenser, 'f', Blocks.iron_bars, 'i', Items.iron_ingot, 'r', Blocks.redstone_block));
		}

		if (OpenBlocks.Blocks.vacuumHopper != null) {
			recipeList.add(new ShapelessOreRecipe(OpenBlocks.Blocks.vacuumHopper, Blocks.hopper, Blocks.obsidian, Items.ender_eye));
		}

		if (OpenBlocks.Blocks.sponge != null) {
			recipeList.add(new ShapelessOreRecipe(OpenBlocks.Blocks.sponge, Blocks.wool, Items.slime_ball));
		}

		if (OpenBlocks.Blocks.bigButton != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.bigButton, "bb", "bb", 'b', Blocks.stone_button));
		}

		if (OpenBlocks.Blocks.imaginary != null) {
			{
				ItemStack pencil = ItemImaginary.setupValues(null, new ItemStack(OpenBlocks.Blocks.imaginary, 1, ItemImaginary.DAMAGE_PENCIL));
				recipeList.add(new ShapelessOreRecipe(pencil, Items.coal, "stickWood", Items.ender_eye, Items.slime_ball));
			}

			for (ColorMeta color : ColorUtils.getAllColors()) {
				ItemStack crayon = ItemImaginary.setupValues(color.rgb, new ItemStack(OpenBlocks.Blocks.imaginary, 1, ItemImaginary.DAMAGE_CRAYON));
				recipeList.add(new ShapelessOreRecipe(crayon, color.oreName, Items.paper, Items.ender_eye, Items.slime_ball));
			}

			recipeList.add(new CrayonMixingRecipe());
		}

		if (OpenBlocks.Blocks.fan != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.fan, "f", "i", "s", 'f', Blocks.iron_bars, 'i', Items.iron_ingot, 's', Blocks.stone_slab));
		}

		if (OpenBlocks.Blocks.xpBottler != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.xpBottler, "iii", "ibi", "iii", 'i', Items.iron_ingot, 'b', Items.glass_bottle));
		}

		if (OpenBlocks.Blocks.villageHighlighter != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.villageHighlighter, "www", "wew", "ccc", 'w', "plankWood", 'e', Items.emerald, 'c', Blocks.cobblestone));
		}

		if (OpenBlocks.Blocks.path != null) {
			recipeList.add(new ShapelessOreRecipe(new ItemStack(OpenBlocks.Blocks.path, 2), "stone", "cobblestone"));
		}

		if (OpenBlocks.Blocks.autoAnvil != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.autoAnvil, "iii", "iai", "rrr", 'i', Items.iron_ingot, 'a', Blocks.anvil, 'r', Items.redstone));
		}

		if (OpenBlocks.Blocks.autoEnchantmentTable != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.autoEnchantmentTable, "iii", "iei", "rrr", 'i', Items.iron_ingot, 'e', Blocks.enchanting_table, 'r', Items.redstone));
		}

		if (OpenBlocks.Blocks.xpDrain != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.xpDrain, "iii", "iii", "iii", 'i', Blocks.iron_bars));
		}
		if (OpenBlocks.Blocks.blockBreaker != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.blockBreaker, "icc", "src", "icc", 'i', Items.iron_ingot, 'c', Blocks.cobblestone, 'r', Items.redstone, 's', Items.diamond_pickaxe));
		}

		if (OpenBlocks.Blocks.blockPlacer != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.blockPlacer, "icc", "src", "icc", 'i', Items.iron_ingot, 'c', Blocks.cobblestone, 'r', Items.redstone, 's', Blocks.piston));
		}

		if (OpenBlocks.Blocks.itemDropper != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.itemDropper, "icc", "src", "icc", 'i', Items.iron_ingot, 'c', Blocks.cobblestone, 'r', Items.redstone, 's', Blocks.hopper));
		}

		if (OpenBlocks.Blocks.ropeLadder != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.ropeLadder, "sts", "sts", "sts", 't', "stickWood", 's', Items.string));
		}

		if (OpenBlocks.Blocks.donationStation != null) {
			WeightedRandomChestContent drop = new WeightedRandomChestContent(new ItemStack(OpenBlocks.Blocks.donationStation), 1, 1, 2);
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(drop);
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(drop);
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.donationStation, "ppp", "pcp", "ppp", 'p', Items.porkchop, 'c', Blocks.chest));
		}

		if (OpenBlocks.Blocks.paintMixer != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.paintMixer, "ooo", "i i", "iii", 'o', Blocks.obsidian, 'i', Items.iron_ingot));
		}

		if (OpenBlocks.Blocks.canvas != null) {
			recipeList.add(new ShapedOreRecipe(new ItemStack(OpenBlocks.Blocks.canvas, 9), "ppp", "pfp", "ppp", 'p', Items.paper, 'f', Blocks.fence));
		}

		if (OpenBlocks.Blocks.projector != null) {
			final ItemStack lapis = new ItemStack(Items.dye, 1, ColorUtils.BLUE);
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.projector, "grl", "iri", "srs", 's', Blocks.stone_slab, 'r', Items.redstone, 'g', Items.glowstone_dust, 'i', Items.iron_ingot, 'l', lapis));
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.projector, "lrg", "iri", "srs", 's', Blocks.stone_slab, 'r', Items.redstone, 'g', Items.glowstone_dust, 'i', Items.iron_ingot, 'l', lapis));
		}

		if (OpenBlocks.Blocks.goldenEgg != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.goldenEgg, "ggg", "geg", "ggg", 'g', Items.gold_ingot, 'e', Items.egg));
		}

		if (OpenBlocks.Blocks.sky != null) {
			final ItemStack normal6 = new ItemStack(OpenBlocks.Blocks.sky, 6, 0);
			final ItemStack normal = new ItemStack(OpenBlocks.Blocks.sky, 1, 1);
			final ItemStack inverted = new ItemStack(OpenBlocks.Blocks.sky, 1, 0);
			recipeList.add(new ShapedOreRecipe(normal6, "geg", "gsg", "geg", 'g', Blocks.glass, 'e', Items.ender_eye, 's', Blocks.end_stone));
			recipeList.add(new ShapelessOreRecipe(inverted, normal, Blocks.redstone_torch));
			recipeList.add(new ShapelessOreRecipe(normal, inverted, Blocks.redstone_torch));
		}

		if (OpenBlocks.Blocks.drawingTable != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.drawingTable, "sks", "pcp", "ppp", 'p', "plankWood", 'c', Blocks.crafting_table, 's', MetasGeneric.unpreparedStencil.newItemStack(), 'k', MetasGeneric.sketchingPencil.newItemStack()));
		}

		if (OpenBlocks.Blocks.radio != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.radio, "pbp", "prp", "pgp", 'p', "plankWood", 'b', Blocks.iron_bars, 'r', Items.redstone, 'g', Items.gold_ingot));
		}

		if (OpenBlocks.Blocks.xpShower != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Blocks.xpShower, "iii", "  o", 'i', Items.iron_ingot, 'o', Blocks.obsidian));
		}

		if (OpenBlocks.Items.hangGlider != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.hangGlider, "wsw", 'w', MetasGeneric.gliderWing.newItemStack(), 's', "stickWood"));
		}

		if (OpenBlocks.Items.luggage != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.luggage, "sds", "scs", "sss", 's', "stickWood", 'd', Items.diamond, 'c', Blocks.chest));
		}

		if (OpenBlocks.Items.sonicGlasses != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.sonicGlasses, "ihi", "oso", "   ", 's', "stickWood", 'h', Items.iron_helmet, 'o', Blocks.obsidian, 'i', Items.iron_ingot));
			ItemStack stack = new ItemStack(OpenBlocks.Items.sonicGlasses);
			WeightedRandomChestContent drop = new WeightedRandomChestContent(stack, 1, 1, 2);
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(drop);
		}

		if (OpenBlocks.Blocks.imaginary != null) {
			if (OpenBlocks.Items.pencilGlasses != null) {
				ItemStack block = new ItemStack(OpenBlocks.Blocks.imaginary, 1, ItemImaginary.DAMAGE_PENCIL);
				ItemImaginary.setupValues(null, block);
				recipeList.add(new ShapelessOreRecipe(OpenBlocks.Items.pencilGlasses, block, Items.paper));
			}

			if (OpenBlocks.Items.crayonGlasses != null) {
				recipeList.add(new CrayonGlassesRecipe());
			}

			if (OpenBlocks.Items.technicolorGlasses != null) {
				WeightedRandomChestContent drop = new WeightedRandomChestContent(new ItemStack(OpenBlocks.Items.technicolorGlasses), 1, 1, 2);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(drop);
				ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(drop);
			}
		}

		if (OpenBlocks.Items.craneControl != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.craneControl, "ili", "grg", "iri", 'i', Items.iron_ingot, 'g', Items.gold_nugget, 'l', Items.glowstone_dust, 'r', Items.redstone));
		}

		if (OpenBlocks.Items.craneBackpack != null) {
			ItemStack line = MetasGeneric.line.newItemStack();
			ItemStack beam = MetasGeneric.beam.newItemStack();
			recipeList.add(new ShapelessOreRecipe(OpenBlocks.Items.craneBackpack, MetasGeneric.craneEngine.newItemStack(), MetasGeneric.craneMagnet.newItemStack(), beam, beam, line, line, line, Items.leather));
		}

		if (OpenBlocks.Items.slimalyzer != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.slimalyzer, "igi", "isi", "iri", 'i', Items.iron_ingot, 'g', Blocks.glass_pane, 's', Items.slime_ball, 'r', Items.redstone));
		}

		if (OpenBlocks.Items.sleepingBag != null && EntityPlayerVisitor.IsInBedHookSuccess) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.sleepingBag, "cc ", "www", "ccw", 'c', Blocks.carpet, 'w', Blocks.wool));
		}

		if (OpenBlocks.Items.paintBrush != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.paintBrush, "w  ", " s ", "  s", 'w', Blocks.wool, 's', "stickWood"));
			int[] colors = new int[] { 0xFF0000, 0x00FF00, 0x0000FF };
			for (int color : colors) {
				ItemStack stack = ItemPaintBrush.createStackWithColor(color);
				WeightedRandomChestContent drop = new WeightedRandomChestContent(stack, 1, 1, 2);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(drop);
				ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(drop);
				ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(drop);
				ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(drop);
			}
		}

		if (OpenBlocks.Items.stencil != null) {
			for (Stencil stencil : Stencil.values()) {
				WeightedRandomChestContent drop = new WeightedRandomChestContent(new ItemStack(OpenBlocks.Items.stencil, 1, stencil.ordinal()), 1, 1, 2);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(drop);
				ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(drop);
			}
		}

		if (OpenBlocks.Items.squeegee != null) {
			if (OpenBlocks.Blocks.sponge != null) {
				recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.squeegee, "sss", " w ", " w ", 's', OpenBlocks.Blocks.sponge, 'w', "stickWood"));
			} else {
				recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.squeegee, "sss", " w ", " w ", 's', Items.slime_ball, 'w', "stickWood"));
			}
		}

		if (OpenBlocks.Items.emptyMap != null) {
			if (OpenBlocks.Items.heightMap != null) {
				recipeList.add(new MapCloneRecipe());
			}

			recipeList.add(new MapResizeRecipe());

			ItemStack memory = MetasGeneric.mapMemory.newItemStack(2);
			ItemStack cpu = MetasGeneric.mapController.newItemStack(1);
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.emptyMap.createMap(0),
					" m ", "mcm", " m ", 'm', memory, 'c', cpu));
		}

		if (OpenBlocks.Items.cartographer != null) {
			recipeList.add(new ShapelessOreRecipe(OpenBlocks.Items.cartographer, MetasGeneric.assistantBase.newItemStack(), Items.ender_eye));
		}

		if (OpenBlocks.Items.goldenEye != null) {
			recipeList.add(new GoldenEyeRechargeRecipe());
			recipeList.add(new ShapedOreRecipe(new ItemStack(OpenBlocks.Items.goldenEye, 1, ItemGoldenEye.MAX_DAMAGE), "ggg", "geg", "ggg", 'g', Items.gold_nugget, 'e', Items.ender_eye));
		}

		if (OpenBlocks.Items.tastyClay != null) {
			final ItemStack cocoa = new ItemStack(Items.dye, 1, ColorUtils.BROWN);
			recipeList.add(new ShapelessOreRecipe(new ItemStack(OpenBlocks.Items.tastyClay, 2), Items.clay_ball, Items.milk_bucket, cocoa));
		}

		if (OpenBlocks.Items.cursor != null) {
			final ItemStack whiteWool = ColorUtils.createDyedWool(ColorUtils.WHITE);
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.cursor, "w  ", "www", "www", 'w', whiteWool));
		}

		if (OpenBlocks.Items.tunedCrystal != null) {
			for (RadioStation station : RadioManager.instance.getRadioStations()) {
				WeightedRandomChestContent drop = new WeightedRandomChestContent(station.getStack().copy(), 1, 1, 2);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(drop);
				ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(drop);
			}
		}

		if (OpenBlocks.Items.infoBook != null) {
			recipeList.add(new ShapelessOreRecipe(new ItemStack(OpenBlocks.Items.infoBook), Items.clay_ball, Items.book));
		}

		if (OpenBlocks.Items.devNull != null) {
			MinecraftForge.EVENT_BUS.register(OpenBlocks.Items.devNull);
			recipeList.add(new ShapelessOreRecipe(new ItemStack(OpenBlocks.Items.devNull), Blocks.cobblestone, Items.apple));
		}

		if (OpenBlocks.Items.spongeonastick != null) {
			OpenBlocks.Items.spongeonastick = new ItemSpongeOnAStick();
			if (OpenBlocks.Blocks.sponge != null) {
				recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.spongeonastick, " s ", " w ", " w ", 's', OpenBlocks.Blocks.sponge, 'w', "stickWood"));
			}
		}

		if (OpenBlocks.Items.pedometer != null) {
			recipeList.add(new ShapedOreRecipe(OpenBlocks.Items.pedometer, "www", "rcr", "www", 'w', "plankWood", 'r', Items.redstone, 'c', Items.clock));
		}

		OpenBlocks.Fluids.xpJuice = new Fluid("xpjuice").setLuminosity(10).setDensity(800).setViscosity(1500).setUnlocalizedName("OpenBlocks.xpjuice");
		FluidRegistry.registerFluid(OpenBlocks.Fluids.xpJuice);

		if (OpenBlocks.Items.filledBucket != null) {
			MetasBucket.registerItems();
		}

		if (explosiveEnchantmentId > 0) {
			MinecraftForge.EVENT_BUS.register(new ExplosiveEnchantmentsHandler());
			Enchantments.explosive = new EnchantmentExplosive(explosiveEnchantmentId);
		}

		if (lastStandEnchantmentId > 0) {
			MinecraftForge.EVENT_BUS.register(new LastStandEnchantmentsHandler());
			Enchantments.lastStand = new EnchantmentLastStand(lastStandEnchantmentId);
		}

		if (flimFlamEnchantmentId > 0) {
			MinecraftForge.EVENT_BUS.register(new FlimFlamEnchantmentsHandler());
			Enchantments.flimFlam = new EnchantmentFlimFlam(flimFlamEnchantmentId);

			for (int i = 0; i < 4; i++) {
				int emeraldCount = 1 << i;
				ItemStack result = Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(Enchantments.flimFlam, i + 1));
				Object recipe[] = new Object[emeraldCount + 1];
				recipe[0] = Items.book;
				Arrays.fill(recipe, 1, recipe.length, Items.emerald);
				recipeList.add(new ShapelessOreRecipe(result, recipe));
			}

		}
	}
}
