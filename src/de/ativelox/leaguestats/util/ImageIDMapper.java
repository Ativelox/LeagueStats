package de.ativelox.leaguestats.util;

import java.awt.Image;
import java.util.Map;
import java.util.Map.Entry;

import de.ativelox.leaguestats.constants.ECachedObject;
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
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class ImageIDMapper {

	public static Map<Long, Image> MASTERIES;
	public static Map<Long, Image> KEYSTONES;
	public static Map<Long, Image> RUNES;
	public static Map<Long, Image> ITEMS;
	public static Map<Long, Image> SUMMONER_SPELLS;
	public static Map<Long, Image> CHAMPIONS;

	public static void init(final RiotApi mApi) throws RiotApiException {

		if (!ImageCache.hasSerializedCache(ECachedObject.CHAMPION)) {
			initChampions(mApi.getDataChampionList(null, null, false, ChampData.IMAGE),
					new ImageCache(ECachedObject.CHAMPION));

		}
		if (!ImageCache.hasSerializedCache(ECachedObject.MASTERY)) {
			// FIXME: handle keystones!!!
			initMasteries(mApi.getDataMasteryList(null, null, MasteryListData.IMAGE),
					new ImageCache(ECachedObject.MASTERY));

		}

		if (!ImageCache.hasSerializedCache(ECachedObject.RUNE)) {
			initRunes(mApi.getDataRuneList(null, null, RuneListData.IMAGE), new ImageCache(ECachedObject.RUNE));

		}

		if (!ImageCache.hasSerializedCache(ECachedObject.SUMMONERSPELL)) {
			initSummonerSpells(mApi.getDataSummonerSpellList(null, null, false, SpellData.IMAGE),
					new ImageCache(ECachedObject.SUMMONERSPELL));

		}

		if (!ImageCache.hasSerializedCache(ECachedObject.ITEM)) {
			initItems(mApi.getDataItemList(null, null, ItemListData.IMAGE), new ImageCache(ECachedObject.ITEM));

		}

		ImageIDMapper.CHAMPIONS = ImageCache.deserialize(ECachedObject.CHAMPION).getCache();
		ImageIDMapper.MASTERIES = ImageCache.deserialize(ECachedObject.MASTERY).getCache();
		ImageIDMapper.RUNES = ImageCache.deserialize(ECachedObject.RUNE).getCache();
		ImageIDMapper.SUMMONER_SPELLS = ImageCache.deserialize(ECachedObject.SUMMONERSPELL).getCache();
		ImageIDMapper.ITEMS = ImageCache.deserialize(ECachedObject.ITEM).getCache();
	}

	public static void initChampions(final ChampionList mChampionList, final ImageCache mCache) {

		for (final Entry<String, Champion> entry : mChampionList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	public static void initMasteries(final MasteryList mMasteryList, final ImageCache mCache) {

		for (final Entry<String, Mastery> entry : mMasteryList.getData().entrySet()) {
			//TODO: Remove Debug Print!
			System.out.println(entry.getValue().getName() + "\t" + entry.getValue().getId());
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	public static void initRunes(final RuneList mRuneList, final ImageCache mCache) {

		for (final Entry<String, Rune> entry : mRuneList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	public static void initSummonerSpells(final SummonerSpellList mSummonerSpellList, final ImageCache mCache) {

		for (final Entry<String, SummonerSpell> entry : mSummonerSpellList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	public static void initItems(final ItemList mItemList, final ImageCache mCache) {

		for (final Entry<String, Item> entry : mItemList.getData().entrySet()) {
			mCache.putImage(entry.getValue().getId(), entry.getValue().getImage().getFull());

		}
		mCache.serialize();
	}

	/**
	 * 
	 */
	private ImageIDMapper() {

	}

}
