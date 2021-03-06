package de.ativelox.leaguestats.util;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import de.ativelox.leaguestats.constants.ECachedObject;
import de.ativelox.leaguestats.logging.ELogLevel;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.constant.staticdata.ItemListData;
import net.rithms.riot.constant.staticdata.MasteryListData;
import net.rithms.riot.constant.staticdata.RuneListData;
import net.rithms.riot.constant.staticdata.SpellData;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Static.Item;
import net.rithms.riot.dto.Static.ItemList;
import net.rithms.riot.dto.Static.Mastery;
import net.rithms.riot.dto.Static.MasteryList;
import net.rithms.riot.dto.Static.Rune;
import net.rithms.riot.dto.Static.RuneList;
import net.rithms.riot.dto.Static.SummonerSpell;
import net.rithms.riot.dto.Static.SummonerSpellList;

/**
 * Maps the IDs of Objects with images to their respective image.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 * 
 * @see ImageIDMapper#MASTERIES
 * @see ImageIDMapper#RUNES
 * @see ImageIDMapper#ITEMS
 * @see ImageIDMapper#SUMMONER_SPELLS
 * @see ImageIDMapper#CHAMPIONS
 *
 */
public final class ImageIDMapper {

	/**
	 * A Map which maps every Champion ID to its respective Image.
	 */
	public static Map<Long, Image> CHAMPIONS;

	/**
	 * A Map which maps every Item ID to its respective Image.
	 */
	public static Map<Long, Image> ITEMS;

	/**
	 * A Map which maps every Mastery ID to its respective Image.
	 */
	public static Map<Long, Image> MASTERIES;

	/**
	 * A Map which maps every Rune ID to its respective Image.
	 */
	public static Map<Long, Image> RUNES;

	/**
	 * A Map which maps every Summoner Spell ID to its respective Image.
	 */
	public static Map<Long, Image> SUMMONER_SPELLS;

	/**
	 * The logger used for logging.
	 */
	private final static ILogger logger = LoggerFactory.getLogger();

	/**
	 * Creates a new Cache for all the champion images and serializes it.
	 * 
	 * @param mChampionList
	 *            The list of all the champions.
	 * @param mCache
	 *            The cache which to use for serialization.
	 */
	public static void createChampionCache(final ChampionList mChampionList, final ImageCache mCache) {
		logger.log("Creating cache for champion images...", ELogLevel.INFO);

		for (final Entry<String, Champion> entry : mChampionList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	/**
	 * Creates a new Cache for all the item images and serializes it.
	 * 
	 * @param mItemList
	 *            The list of all the items.
	 * @param mCache
	 *            The cache which to use for serialization.
	 */
	public static void createItemCache(final ItemList mItemList, final ImageCache mCache) {
		logger.log("Creating cache for item images...", ELogLevel.INFO);

		for (final Entry<String, Item> entry : mItemList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	/**
	 * Creates a new Cache for all the mastery images and serializes it.
	 * 
	 * @param mMasteryList
	 *            The list of all the masteries.
	 * @param mCache
	 *            The cache which to use for serialization.
	 */
	public static void createMasteryCache(final MasteryList mMasteryList, final ImageCache mCache) {
		logger.log("Creating cache for mastery images...", ELogLevel.INFO);

		for (final Entry<String, Mastery> entry : mMasteryList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	/**
	 * Creates a new Cache for all the rune images and serializes it.
	 * 
	 * @param mRuneList
	 *            The list of all the runes.
	 * @param mCache
	 *            The cache which to use for serialization.
	 */
	public static void createRuneCache(final RuneList mRuneList, final ImageCache mCache) {
		logger.log("Creating cache for rune images...", ELogLevel.INFO);

		for (final Entry<String, Rune> entry : mRuneList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	/**
	 * Creates a new Cache for all the summoner spell images and serializes it.
	 * 
	 * @param mSummonerSpellList
	 *            The list of all the summoner spells.
	 * @param mCache
	 *            The cache which to use for serialization.
	 */
	public static void createSummonerSpellCache(final SummonerSpellList mSummonerSpellList, final ImageCache mCache) {
		logger.log("Creating cache for summoner spell images...", ELogLevel.INFO);

		for (final Entry<String, SummonerSpell> entry : mSummonerSpellList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	/**
	 * Initializes every Map by first checking if the cache for the object
	 * exists, and if not creating that cache.
	 * 
	 * @param mApi
	 *            The api of which to get GET-request from.
	 * 
	 * @throws RiotApiException
	 *             if any error code got sent from any GET-request on this API.
	 */
	public static void init(final RiotApi mApi) throws RiotApiException {

		if (!ImageCache.hasSerializedCache(ECachedObject.CHAMPION)) {
			createChampionCache(mApi.getDataChampionList(null, null, false, ChampData.IMAGE),
					new ImageCache(ECachedObject.CHAMPION));

		}
		if (!ImageCache.hasSerializedCache(ECachedObject.MASTERY)) {
			createMasteryCache(mApi.getDataMasteryList(null, null, MasteryListData.IMAGE),
					new ImageCache(ECachedObject.MASTERY));

		}

		if (!ImageCache.hasSerializedCache(ECachedObject.RUNE)) {
			createRuneCache(mApi.getDataRuneList(null, null, RuneListData.IMAGE), new ImageCache(ECachedObject.RUNE));

		}

		if (!ImageCache.hasSerializedCache(ECachedObject.SUMMONERSPELL)) {
			createSummonerSpellCache(mApi.getDataSummonerSpellList(null, null, false, SpellData.IMAGE),
					new ImageCache(ECachedObject.SUMMONERSPELL));

		}

		if (!ImageCache.hasSerializedCache(ECachedObject.ITEM)) {
			createItemCache(mApi.getDataItemList(null, null, ItemListData.IMAGE), new ImageCache(ECachedObject.ITEM));

		}

		logger.log("Getting image data from the champion cache...", ELogLevel.INFO);
		ImageIDMapper.CHAMPIONS = ImageCache.deserialize(ECachedObject.CHAMPION).getCache();

		logger.log("Getting image data from the mastery cache...", ELogLevel.INFO);
		ImageIDMapper.MASTERIES = ImageCache.deserialize(ECachedObject.MASTERY).getCache();

		logger.log("Getting image data from the rune cache...", ELogLevel.INFO);
		ImageIDMapper.RUNES = ImageCache.deserialize(ECachedObject.RUNE).getCache();

		logger.log("Getting image data from the summoner spell cache...", ELogLevel.INFO);
		ImageIDMapper.SUMMONER_SPELLS = ImageCache.deserialize(ECachedObject.SUMMONERSPELL).getCache();

		logger.log("Getting image data from the item cache...", ELogLevel.INFO);
		ImageIDMapper.ITEMS = ImageCache.deserialize(ECachedObject.ITEM).getCache();
	}

	public static void deleteCaches() {
		try {
			Files.delete(Paths.get(ImageCache.FILEPATH_SERIALIZATION_PRE + ECachedObject.CHAMPION
					+ ImageCache.FILEPATH_SERIALIZATION_SUFF));

			Files.delete(Paths.get(ImageCache.FILEPATH_SERIALIZATION_PRE + ECachedObject.ITEM
					+ ImageCache.FILEPATH_SERIALIZATION_SUFF));

			Files.delete(Paths.get(ImageCache.FILEPATH_SERIALIZATION_PRE + ECachedObject.MASTERY
					+ ImageCache.FILEPATH_SERIALIZATION_SUFF));

			Files.delete(Paths.get(ImageCache.FILEPATH_SERIALIZATION_PRE + ECachedObject.RUNE
					+ ImageCache.FILEPATH_SERIALIZATION_SUFF));

			Files.delete(Paths.get(ImageCache.FILEPATH_SERIALIZATION_PRE + ECachedObject.SUMMONERSPELL
					+ ImageCache.FILEPATH_SERIALIZATION_SUFF));
			
		} catch (IOException e) {
			logger.log("Error occured while trying to delete caches...", ELogLevel.ERROR);

		}
	}

	/**
	 * Utility class, no initialization needed.
	 */
	private ImageIDMapper() {

	}

}
