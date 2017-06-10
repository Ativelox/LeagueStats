package de.ativelox.leaguestats.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import de.ativelox.leaguestats.constants.EImageStyle;
import de.ativelox.leaguestats.constants.EImageType;
import de.ativelox.leaguestats.exceptions.InvalidURLException;
import de.ativelox.leaguestats.exceptions.UnsupportedImageStyleException;

/**
 * Provides methods to interact with the Data Dragon of LOL.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 * 
 * @see DDragon#getImage(EImageType, EImageStyle, String)
 *
 */
public final class DDragon {

	/**
	 * The URL for abilities.
	 */
	private static final String ABILITIES_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/";

	/**
	 * The URL for loading arts of champions.
	 */
	private static final String ART_LOADING_URL = "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/";

	/**
	 * The URL for full splash arts of champions.
	 */
	private static final String ART_SPLASH_URL = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";

	/**
	 * The URL for square arts of champions.
	 */
	private static final String ART_SQUARE_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/";

	/**
	 * The URL for items.
	 */
	private static final String ITEM_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/";

	/**
	 * The URL for maps.
	 */
	private static final String MAP_URL = "http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/";

	/**
	 * The URL for masteries.
	 */
	private static final String MASTERY_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/";

	/**
	 * The regex used to match the names/ids for the given relative image paths.
	 */
	private static final String NAME_ID_MATCHER = "^(.+)\\.png$";

	/**
	 * The URL for passive abilities.
	 */
	private static final String PASSIVE_ABILITIES_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/";

	/**
	 * The URL for profile icons.
	 */
	private static final String PROFILE_ICON_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/";

	/**
	 * The URL for runes.
	 */
	private static final String RUNE_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/";

	/**
	 * The URL for summoner spells.
	 */
	private static final String SUMMONER_SPELL_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/";

	/**
	 * Gets an image from the Data Dragon from LOL.
	 * 
	 * @param mImageType
	 *            The {@link EImageType} which to fetch
	 * 
	 * @param mImageStyle
	 *            The {@link EImageStyle} which to fetch
	 * 
	 * @param mFullName
	 *            The name/id of which to fetch the image from.
	 * 
	 * @return The Image for the given name/id.
	 * 
	 * @throws UnsupportedImageStyleException
	 *             if the given {@link EImageStyle} wasn't supported by Riot.
	 * 
	 * @throws InvalidURLException
	 *             if the built URL wasn't valid, e.g. there was no image to
	 *             fetch with the given data.
	 * 
	 * @throws IOException
	 *             if an IOException occured while trying to fetch the image.
	 */
	public static BufferedImage getImage(final EImageType mImageType, final EImageStyle mImageStyle,
			final String mFullName) throws UnsupportedImageStyleException, InvalidURLException, IOException {

		Pattern pattern = Pattern.compile(NAME_ID_MATCHER);
		Matcher matcher = pattern.matcher(mFullName);

		if (!matcher.matches()) {
			throw new InvalidURLException();

		}

		final String name = matcher.group(1);

		String baseURL = "";
		String extension = "";
		String finalURL = "";

		if (mImageType == EImageType.CHAMPION) {
			if (mImageStyle == EImageStyle.SPLASH) {
				baseURL = ART_SPLASH_URL;
				extension = "_0.jpg";

			} else if (mImageStyle == EImageStyle.LOADING) {
				baseURL = ART_LOADING_URL;
				extension = "_0.jpg";

			} else if (mImageStyle == EImageStyle.SQUARE) {
				baseURL = ART_SQUARE_URL;
				extension = ".png";

			} else {
				throw new UnsupportedImageStyleException("The given image type isn't supported!");

			}

		} else if (mImageType == EImageType.SUMMONERSPELL) {
			baseURL = SUMMONER_SPELL_URL;
			extension = ".png";

		} else if (mImageType == EImageType.ABILITY) {
			baseURL = ABILITIES_URL;
			extension = ".png";

		} else if (mImageType == EImageType.PASSIVEABILITY) {
			baseURL = PASSIVE_ABILITIES_URL;
			extension = ".png";

		} else if (mImageType == EImageType.MASTERY) {
			baseURL = MASTERY_URL;
			extension = ".png";

		} else if (mImageType == EImageType.RUNE) {
			baseURL = RUNE_URL;
			extension = ".png";

		} else if (mImageType == EImageType.ITEM) {
			baseURL = ITEM_URL;
			extension = ".png";

		} else if (mImageType == EImageType.PROFILEICON) {
			baseURL = PROFILE_ICON_URL;
			extension = ".png";

		} else if (mImageType == EImageType.MAP) {
			baseURL = MAP_URL + "map";
			extension = ".png";

		}

		finalURL = baseURL + name + extension;

		System.out.println(finalURL);

		URL imageURL = null;

		try {
			imageURL = new URL(finalURL);

		} catch (final MalformedURLException e) {
			throw new InvalidURLException(
					"The URL-format got changed on RIOTs end. Check Riots documentation for further information.");

		}
		return ImageIO.read(imageURL);

	}

	/**
	 * Utility class, no initialization needed.
	 */
	private DDragon() {

	}

}
