package de.ativelox.leaguestats.constants;

/**
 * Provides enums to determine what sort of champion image to fetch from the
 * data server.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public enum EImageStyle {

	/**
	 * The loading art of a given champion.
	 */
	LOADING,

	/**
	 * Indicates that none of the arts should be used.
	 */
	NONE,

	/**
	 * The full splash art of a given champion.
	 */
	SPLASH,

	/**
	 * The square art, e.g. the icon seen when in-game, of the a champion.
	 */
	SQUARE;

}
