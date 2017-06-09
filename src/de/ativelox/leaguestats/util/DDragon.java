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
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class DDragon {

	private static final String NAME_ID_MATCHER = "^(.+)\\.png$";

	private static final String ART_SPLASH_URL = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";
	private static final String ART_LOADING_URL = "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/";
	private static final String ART_SQUARE_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/";
	private static final String SUMMONER_SPELL_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/";
	private static final String ABILITIES_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/";
	private static final String PASSIVE_ABILITIES_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/passive/";

	private static final String MASTERY_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/mastery/";
	private static final String RUNE_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/rune/";
	private static final String ITEM_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/";
	private static final String MAP_URL = "http://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/";
	private static final String PROFILE_ICON_URL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/";

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

		} else if (mImageType == EImageType.ABILITIE) {
			baseURL = ABILITIES_URL;
			extension = ".png";

		} else if (mImageType == EImageType.PASSIVEABILITIE) {
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
	 * 
	 */
	private DDragon() {
		// TODO Auto-generated constructor stub
	}

}
