package de.ativelox.leaguestats.constants;

/**
 * Has enums for cacheable objects, e.g. the images for this cached object. For
 * example every Champion has an image, every mastery has an image, e.t.c.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public enum ECachedObject {

	/**
	 * The cached object for the images for champions.
	 */
	CHAMPION,

	/**
	 * The cached object for the images for the items.
	 */
	ITEM,

	/**
	 * The cached object for the images for the masteries.
	 */
	MASTERY,

	/**
	 * The cached object for the images for the runes.
	 */
	RUNE,

	/**
	 * The cached object for the images for the summoner spells.
	 */
	SUMMONERSPELL;

}
